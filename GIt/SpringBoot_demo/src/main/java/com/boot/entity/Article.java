package com.boot.entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Entity
public class Article { 
	
	@Id
	@GeneratedValue
    private int articleId;  
	
	@NotNull
	@Size(min=2, message="title should have atleast 2 characters")
    private String title;
    
	@NotNull
	@Size(min=7, message="category should have atleast 2 characters")
	private String category;
	
	public int getArticleId() {
		return articleId;
	}
	public void setArticleId(int articleId) {
		this.articleId = articleId;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getCategory() {
		return category;
	}
	public void setCategory(String category) {
		this.category = category;
	}
} 