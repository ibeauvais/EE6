package com.test;

import javax.persistence.*;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import java.util.List;
import java.sql.SQLException;

import org.junit.*;

import com.app.Book;


public class BookTest {


    private static EntityManagerFactory entityManagerFactory;
    private EntityManager entityManager;
    private EntityTransaction tx;


    @BeforeClass
    public static void initEntityManagerFactory() throws Exception {
        entityManagerFactory = Persistence.createEntityManagerFactory("chapter02PU");

    }

    @AfterClass
    public static void closeEntityManagerFactory() throws SQLException {
        entityManagerFactory.close();
    }

    @Before
    public void setup() {
        entityManager = entityManagerFactory.createEntityManager();
        tx = entityManager.getTransaction();
    }


    @After
    public void tearDown() throws Exception {
        entityManager.close();

    }

    @Test
    public void shouldCreateABook() throws Exception {
        // Creates an instance of book
        Book book = new Book();
        book.setTitle("The Hitchhiker's Guide to the Galaxy");
        book.setPrice(12.5F);
        book.setDescription("Science fiction comedy book");
        book.setIsbn("1-84023-742-2");
        book.setNbOfPage(354);
        book.setIllustrations(false);
        // Persists the book to the database
        tx.begin();
        entityManager.persist(book);
        tx.commit();
        assertNotNull("ID should not be null", book.getId());
        // Retrieves all the books from the database
        List<Book> books = entityManager.createNamedQuery("findAllBooks",Book.class).getResultList();
        assertEquals(1, books.size());
    }

}
