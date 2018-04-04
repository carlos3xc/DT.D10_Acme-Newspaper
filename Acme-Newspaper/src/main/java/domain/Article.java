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
import javax.persistence.ElementCollection;
import javax.persistence.Entity;

import org.hibernate.validator.constraints.NotBlank;

@Entity
@Access(AccessType.PROPERTY)
public class Article extends DomainEntity {
	
	// Attributes -------------------------------------------------------------

		private String					title;
		private String 					summary;
		private String 					text;
		private Date 					publicationDate;
		private Collection<String>		pictures;
		private Boolean					isDraft;
		
		@NotBlank
		public String getTitle() {
			return title;
		}
		
		public void setTitle(String title) {
			this.title = title;
		}
		
		@NotBlank
		public String getSummary() {
			return summary;
		}

		public void setSummary(String summary) {
			this.summary = summary;
		}

		@NotBlank
		public String getText() {
			return text;
		}

		public void setText(String text) {
			this.text = text;
		}

		public Date getPublicationDate() {
			return publicationDate;
		}
		
		public void setPublicationDate(Date publicationDate) {
			this.publicationDate = publicationDate;
		}

		@ElementCollection
		public Collection<String> getPictures() {
			return pictures;
		}

		public void setPictures(Collection<String> pictures) {
			this.pictures = pictures;
		}

		public Boolean getIsDraft() {
			return isDraft;
		}

		public void setIsDraft(Boolean isDraft) {
			this.isDraft = isDraft;
		}

		
		
	
	
	
}
