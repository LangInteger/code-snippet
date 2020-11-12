package multithread;

import java.math.BigDecimal;
import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;
import java.util.Locale;
import java.util.concurrent.CompletableFuture;

public abstract class Benchmark {

  private static final DecimalFormat format = new DecimalFormat("0.0%", DecimalFormatSymbols.getInstance(Locale.ENGLISH));
  private static final ThreadLocal<DecimalFormat> restrictTo1DecimalPlace = ThreadLocal.withInitial(() -> new DecimalFormat("0.0%", DecimalFormatSymbols.getInstance(Locale.ENGLISH)));

  public static void main(String[] args) throws Exception {
    for (int threadCount = 10; threadCount < 100; threadCount = threadCount + 10) {
      Benchmark[] marks = {
              new Benchmark("ThreadLocal", threadCount) {
                @Override
                protected Object run(int iterations, int threadCount) throws Throwable {
                  CompletableFuture[] futures = new CompletableFuture[threadCount];
                  for (int i = 0; i < threadCount; i++) {
                    CompletableFuture<StringBuilder> future = CompletableFuture.supplyAsync(() -> {
                      StringBuilder sb = new StringBuilder();
                      for (int j = 0; j < iterations; j++) {
                        sb.append(restrictTo1DecimalPlace.get().format(j * 0.01));
                      }
                      return sb;
                    });
                    futures[i] = future;
                  }
                  return CompletableFuture.allOf(futures).join();
                }

                ;
              },
              new Benchmark("NewFormat", threadCount) {
                @Override
                protected Object run(int iterations, int threadCount) throws Throwable {
                  CompletableFuture[] futures = new CompletableFuture[threadCount];
                  for (int i = 0; i < threadCount; i++) {
                    CompletableFuture<StringBuilder> future = CompletableFuture.supplyAsync(() -> {
                      StringBuilder sb = new StringBuilder();
                      for (int j = 0; j < iterations; j++) {
                        sb.append(new DecimalFormat("0.0%", DecimalFormatSymbols.getInstance(Locale.ENGLISH)).format(j * 0.01));
                      }
                      return sb;
                    });
                    futures[i] = future;
                  }
                  return CompletableFuture.allOf(futures).join();
                }

                ;
              },
              new Benchmark("Synchronize", threadCount) {
                @Override
                protected Object run(int iterations, int threadCount) throws Throwable {
                  CompletableFuture[] futures = new CompletableFuture[threadCount];
                  for (int i = 0; i < threadCount; i++) {
                    CompletableFuture<StringBuilder> future = CompletableFuture.supplyAsync(() -> {
                      StringBuilder sb = new StringBuilder();
                      for (int j = 0; j < iterations; j++) {
                        sb.append(Benchmark.getFormat().format(j * 0.01));
                      }
                      return sb;
                    });
                    futures[i] = future;
                  }
                  return CompletableFuture.allOf(futures).join();
                }

                ;
              },
      };
      for (Benchmark mark : marks) {
        System.out.println(mark);
      }
    }
  }

  private static synchronized DecimalFormat getFormat() {
    return format;
  }

  final String name;
  final int threadCount;

  public Benchmark(String name, int threadCount) {
    this.name = name;
    this.threadCount = threadCount;
  }

  @Override
  public String toString() {
    return name + "\t" + time() + " ns for max iteration of total thread count " + threadCount;
  }

  private BigDecimal time() {
    try {
      // automatically detect a reasonable iteration count (and trigger just in time compilation of the code under test)
      int iterations;
      long duration = 0;
      for (iterations = 1; iterations < 1_000_000_000 && duration < 1_000_000_000; iterations *= 2) {
        long start = System.nanoTime();
        run(iterations, threadCount);
        duration = System.nanoTime() - start;
      }
      return new BigDecimal((duration) * 1000 / iterations);
    } catch (Throwable e) {
      throw new RuntimeException(e);
    }
  }

  /**
   * Executes the code under test.
   *
   * @param iterations number of iterations to perform
   * @return any value that requires the entire code to be executed (to
   * prevent dead code elimination by the just in time compiler)
   * @throws Throwable if the test could not complete successfully
   */
  protected abstract Object run(int iterations, int threadCount) throws Throwable;

}
