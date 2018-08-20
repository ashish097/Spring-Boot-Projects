package com.boot.controller;
import java.util.List;

import javax.validation.Valid;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
//import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
/*import org.springframework.web.bind.annotation.RequestMapping;*/
import org.springframework.web.util.UriComponentsBuilder;

import com.boot.Article.ArticletNotFoundException;
import com.boot.entity.Article;
import com.boot.service.IArticleService;

@RestController
//@RequestMapping("/user")
public class ArticleController {
	
	private static final Logger LOGGER = LoggerFactory.getLogger(ArticleController.class);

	
	@Autowired
	private IArticleService articleService;
	
	@GetMapping("/article/{id}")
	public ResponseEntity<Article> getArticleById(@PathVariable("id") Integer id) {
		boolean present = articleService.ispresent(id);
		if(present)
		{
			Article article = articleService.getArticleById(id);
			return new ResponseEntity<Article>(article, HttpStatus.OK);
		}
		else
        {
	        throw new ArticletNotFoundException("No Records found with this id-" + id);
        }		
	}
	
	
	@GetMapping("/article/")
	public ResponseEntity<List<Article>> getAllArticles() {
		List<Article> list = articleService.getAllArticles();
		
		if(LOGGER.isInfoEnabled()){
			LOGGER.info("This controller is info enabled");
		}
		return new ResponseEntity<List<Article>>(list, HttpStatus.OK);
	}
	
	
	@PostMapping("/article/")
	public ResponseEntity<Void> addArticle(@Valid @RequestBody Article article, UriComponentsBuilder builder) {
        boolean flag = articleService.addArticle(article);
        
        if(LOGGER.isDebugEnabled()){
        	LOGGER.debug("This controller is debug enabled");
		}
        
        if (flag == false) {
        	return new ResponseEntity<Void>(HttpStatus.CONFLICT);
        }
        HttpHeaders headers = new HttpHeaders();
        headers.setLocation(builder.path("/article/{id}").buildAndExpand(article.getArticleId()).toUri());
        return new ResponseEntity<Void>(headers, HttpStatus.CREATED);
	}
	
	
	@PutMapping("/article/")
	public ResponseEntity<Article> updateArticle(@Valid @RequestBody Article article) {
		articleService.updateArticle(article);
		 if(LOGGER.isDebugEnabled()){
	        	LOGGER.debug("This controller is debug enabled");
			}
		if(LOGGER.isInfoEnabled()){
			LOGGER.info("This controller is info enabled");
		}
		return new ResponseEntity<Article>(article, HttpStatus.OK);
	}
	
	
	@DeleteMapping("/article/{id}")
	public ResponseEntity<Void> deleteArticle(@PathVariable("id") Integer id) {
		articleService.deleteArticle(id);
		 if(LOGGER.isDebugEnabled()){
	        	LOGGER.debug("This controller is debug enabled");
			}
		return new ResponseEntity<Void>(HttpStatus.NO_CONTENT);
	}	
} 