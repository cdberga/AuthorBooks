package com.berga.authorbooks.model;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name="authors")
@NamedQueries({
	@NamedQuery(name="Author.findByName",
			query="SELECT a FROM Authors a WHERE a.name=:name")
})
public class Author {
	
	@Id
	@GeneratedValue
	private Integer id;
	private String name;
	@OneToMany(mappedBy="author", cascade = CascadeType.ALL)
	private List<Book> books;

}
