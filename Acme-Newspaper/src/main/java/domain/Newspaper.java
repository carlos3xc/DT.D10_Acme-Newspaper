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
import java.util.Date;

import javax.persistence.Access;
import javax.persistence.AccessType;
import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.OneToMany;
import javax.validation.Valid;
import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.NotBlank;
import org.hibernate.validator.constraints.URL;

@Entity
@Access(AccessType.PROPERTY)
public class Newspaper extends DomainEntity {
	
	// Attributes -------------------------------------------------------------

		private String		title;
		private String 		description;
		private Date 		publicationDate;
		private String		picture;
		
		@NotBlank
		public String getTitle() {
			return title;
		}
		
		public void setTitle(String title) {
			this.title = title;
		}
		
		
		@NotBlank
		public String getDescription() {
			return description;
		}
		
		public void setDescription(String description) {
			this.description = description;
		}
		
		
		public Date getPublicationDate() {
			return publicationDate;
		}
		
		public void setPublicationDate(Date publicationDate) {
			this.publicationDate = publicationDate;
		}
		
		@URL
		public String getPicture() {
			return picture;
		}
		public void setPicture(String picture) {
			this.picture = picture;
		}
		
	// Relationships ----------------------------------------------------------
		
		private Collection<Article> articles;
		
		@Valid
		@NotNull
		@OneToMany(cascade = CascadeType.ALL)
		public Collection<Article> getArticles() {
			return articles;
		}

		public void setArticles(Collection<Article> articles) {
			this.articles = articles;
		}	
	
	
}
