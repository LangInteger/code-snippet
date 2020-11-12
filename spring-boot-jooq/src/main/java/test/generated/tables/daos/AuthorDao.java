/*
 * This file is generated by jOOQ.
 */
package test.generated.tables.daos;


import java.util.List;

import javax.annotation.Generated;

import org.jooq.Configuration;
import org.jooq.impl.DAOImpl;

import test.generated.tables.Author;
import test.generated.tables.records.AuthorRecord;


/**
 * This class is generated by jOOQ.
 */
@Generated(
    value = {
        "http://www.jooq.org",
        "jOOQ version:3.11.11"
    },
    comments = "This class is generated by jOOQ"
)
@SuppressWarnings({ "all", "unchecked", "rawtypes" })
public class AuthorDao extends DAOImpl<AuthorRecord, test.generated.tables.pojos.Author, Integer> {

    /**
     * Create a new AuthorDao without any configuration
     */
    public AuthorDao() {
        super(Author.AUTHOR, test.generated.tables.pojos.Author.class);
    }

    /**
     * Create a new AuthorDao with an attached configuration
     */
    public AuthorDao(Configuration configuration) {
        super(Author.AUTHOR, test.generated.tables.pojos.Author.class, configuration);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    protected Integer getId(test.generated.tables.pojos.Author object) {
        return object.getId();
    }

    /**
     * Fetch records that have <code>id IN (values)</code>
     */
    public List<test.generated.tables.pojos.Author> fetchById(Integer... values) {
        return fetch(Author.AUTHOR.ID, values);
    }

    /**
     * Fetch a unique record that has <code>id = value</code>
     */
    public test.generated.tables.pojos.Author fetchOneById(Integer value) {
        return fetchOne(Author.AUTHOR.ID, value);
    }

    /**
     * Fetch records that have <code>first_name IN (values)</code>
     */
    public List<test.generated.tables.pojos.Author> fetchByFirstName(String... values) {
        return fetch(Author.AUTHOR.FIRST_NAME, values);
    }

    /**
     * Fetch records that have <code>last_name IN (values)</code>
     */
    public List<test.generated.tables.pojos.Author> fetchByLastName(String... values) {
        return fetch(Author.AUTHOR.LAST_NAME, values);
    }
}