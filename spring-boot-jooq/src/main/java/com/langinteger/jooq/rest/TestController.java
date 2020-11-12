package com.langinteger.jooq.rest;

import org.jooq.DSLContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

import static test.generated.tables.Author.AUTHOR;
import static test.generated.tables.AuthorBook.AUTHOR_BOOK;
import static test.generated.tables.Book.BOOK;


@RestController
public class TestController {

  @Autowired
  DSLContext dsl;

  @PostMapping(value = "/addAuthor")
  public void addAuthor() {
    dsl.insertInto(AUTHOR)
        .set(AUTHOR.ID, 4)
        .set(AUTHOR.FIRST_NAME, "Herbert")
        .set(AUTHOR.LAST_NAME, "Schildt")
        .execute();
  }

  @PostMapping(value = "/getData")
  public List<test.generated.tables.pojos.Author> getData() {
    return dsl
        .select(AUTHOR.ID, AUTHOR.LAST_NAME, BOOK.TITLE)
        .from(AUTHOR)
        .join(AUTHOR_BOOK)
        .on(AUTHOR.ID.equal(AUTHOR_BOOK.AUTHOR_ID))
        .join(BOOK)
        .on(AUTHOR_BOOK.BOOK_ID.equal(BOOK.ID))
        .where(AUTHOR.ID.equal(1))
        .fetch().into(test.generated.tables.pojos.Author.class);
  }

}
