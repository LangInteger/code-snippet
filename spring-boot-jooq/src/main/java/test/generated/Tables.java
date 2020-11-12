/*
 * This file is generated by jOOQ.
 */
package test.generated;


import javax.annotation.Generated;

import test.generated.tables.Author;
import test.generated.tables.AuthorBook;
import test.generated.tables.Book;


/**
 * Convenience access to all tables in jooq
 */
@Generated(
    value = {
        "http://www.jooq.org",
        "jOOQ version:3.11.11"
    },
    comments = "This class is generated by jOOQ"
)
@SuppressWarnings({ "all", "unchecked", "rawtypes" })
public class Tables {

    /**
     * The table <code>jooq.author</code>.
     */
    public static final Author AUTHOR = test.generated.tables.Author.AUTHOR;

    /**
     * The table <code>jooq.author_book</code>.
     */
    public static final AuthorBook AUTHOR_BOOK = test.generated.tables.AuthorBook.AUTHOR_BOOK;

    /**
     * The table <code>jooq.book</code>.
     */
    public static final Book BOOK = test.generated.tables.Book.BOOK;
}