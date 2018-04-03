/*
 * Actor.java
 * 
 * Copyright (C) 2014 Universidad de Sevilla
 * 
 * The use of this project is hereby constrained to the conditions of the
 * TDG Licence, a copy of which you may download from
 * http://www.tdg-seville.info/License.html
 */

package domain;
import java.util.Collection;

import javax.persistence.Access;
import javax.persistence.AccessType;
import javax.persistence.Entity;
import javax.persistence.OneToMany;
import javax.validation.Valid;
import javax.validation.constraints.NotNull;

@Entity
@Access(AccessType.PROPERTY)
public class User extends Actor {
	
	
	
	// Relationships ----------------------------------------------------------
	
	private Collection<Newspaper> newspapers;
	private Collection<Article>   articles;

	@Valid
	@NotNull
	@OneToMany
	public Collection<Newspaper> getNewspapers() {
		return newspapers;
	}

	public void setNewspapers(Collection<Newspaper> newspapers) {
		this.newspapers = newspapers;
	}

	@Valid
	@NotNull
	@OneToMany
	public Collection<Article> getArticles() {
		return articles;
	}

	public void setArticles(Collection<Article> articles) {
		this.articles = articles;
	}
	
}
