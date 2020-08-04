package com.berga.authorbooks.repository;

import java.util.List;
import java.util.Optional;

import javax.persistence.EntityManager;

import com.berga.authorbooks.model.Author;

public class AuthorRepository {

	private EntityManager entityManager;
	
	public AuthorRepository(EntityManager entityManager) {
		this.entityManager = entityManager;
	}
	
	@SuppressWarnings("unchecked")
	public Optional<Author> findById(Integer id) {
		Author author = entityManager.find(Author.class, id);
		return (Optional<Author>) (author != null? Optional.of(author) : Optional.empty());
	}
	
	@SuppressWarnings("unchecked")
	public List<Author> findAll() {
		return entityManager.createQuery("from Author").getResultList();
	}
	
	@SuppressWarnings("unchecked")
	public Optional<Author> findByName(String name) {
		Author author = entityManager.createNamedQuery("Author.findByName", Author.class)
				.setParameter("name", name)
				.getSingleResult();
		
		return (Optional<Author>) (author != null ? Optional.of(author) : Optional.empty());
	}
	
	public Optional<Author> save(Author author) {
		try {
			entityManager.getTransaction().begin();
			entityManager.persist(author);
			entityManager.getTransaction().commit();
			return Optional.of(author);
		} catch(Exception e) {
			e.printStackTrace();
		}
		return Optional.empty();
	}
}
