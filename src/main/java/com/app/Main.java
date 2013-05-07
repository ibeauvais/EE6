package com.app;

import javax.persistence.*;

public class Main {
	public static void main(String[] args) {
		// Creates an instance of book
		Book book = new Book();
		book.setTitle("The Hitchhiker's Guide to the Galaxy");
		book.setPrice(12.5F);
		book.setDescription("Science fiction comedy book");
		book.setIsbn("1-84023-742-2");
		book.setNbOfPage(354);
		book.setIllustrations(false);
		// Gets an entity manager and a transaction
		EntityManagerFactory emf = 	Persistence.createEntityManagerFactory("chapter02PU");
		EntityManager em = emf.createEntityManager();
		// Persists the book to the database
		EntityTransaction tx = em.getTransaction();
		tx.begin();
		em.persist(book);
		tx.commit();
		em.close();
		emf.close();
	}
}