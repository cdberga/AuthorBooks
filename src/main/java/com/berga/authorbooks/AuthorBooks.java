package com.berga.authorbooks;

import java.util.List;
import java.util.Optional;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import com.berga.authorbooks.model.Author;
import com.berga.authorbooks.model.Book;
import com.berga.authorbooks.repository.AuthorRepository;
import com.berga.authorbooks.repository.BookRepository;

public class AuthorBooks {

	public static void main(String[] args) {
		// Create Entity manager and Factory
		EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("BookAuthor");
		EntityManager entityManager = entityManagerFactory.createEntityManager();
		
		// Create repositories
		BookRepository bookRepository = new BookRepository(entityManager);
		AuthorRepository authorRepository = new AuthorRepository(entityManager);
		
		// Create an author and 3 books to his list
		Author author1 = new Author("Author 1");
		author1.addBook(new Book("Book 1"));
		author1.addBook(new Book("Book 2"));
		author1.addBook(new Book("Book 3"));
		
		Optional<Author> savedAuthor = authorRepository.save(author1);
		System.out.println("Saved Author: " + savedAuthor.get());
		
		// find all Authors
		List<Author> authors = authorRepository.findAll();
		System.out.println("Authors: ");
		authors.forEach(System.out::println);
		
		// find author by name
		Optional<Author> authorByName = authorRepository.findByName("Author 1");
		System.out.println("Searching for an author by name: ");
		authorByName.ifPresent(System.out::println);
		
		// Search for a book by id
		Optional<Book> bookById = bookRepository.findById(2);
		bookById.ifPresent(System.out::println);
		
		// Search for a book with an invalid id
		Optional<Book> bookByInvalidId = bookRepository.findById(99);
		bookByInvalidId.ifPresent(System.out::println);
		
		// List all books
		List<Book> allBooks = bookRepository.findAll();
		System.out.println("All books in database: ");
		allBooks.forEach(System.out::println);
		
		// Find a book by name
		Optional<Book> bookByName = bookRepository.findByName("Book 2");
		System.out.println("Search a book by name: ");
		bookByName.ifPresent(System.out::println);

		// Find a book by name with named query
		Optional<Book> bookByNamedQuery = bookRepository.findByNameNamedQuery("Book 3");
		System.out.println("Search a book by named query: ");
		bookByNamedQuery.ifPresent(System.out::println);

		// Add a book to author 1
		Optional<Author> authorById = authorRepository.findById(1);
		authorById.ifPresent(a -> {
			a.addBook(new Book("Book 4"));
			System.out.println("Saved author: " + authorRepository.save(a));
		});
        // Close the entity manager and associated factory
        entityManager.close();
        entityManagerFactory.close();
	}
}
