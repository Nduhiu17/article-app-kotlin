package com.nduhiu.kotlindemo.controller

import com.nduhiu.kotlindemo.model.Article
import com.nduhiu.kotlindemo.repository.ArticleRepository
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*
import javax.validation.Valid

@RestController
@RequestMapping("/api")
class ArticleController(private val articleRepository: ArticleRepository){

    //getting all articles
    @GetMapping("/articles")
    fun getArticles(): List<Article> = articleRepository.findAll()

    //creating an article
    @PostMapping("/articles")
    fun createNewArticle(@Valid @RequestBody article: Article): Article = articleRepository.save(article)

    //getting an article by id
    @GetMapping("/articles/{id}")
    fun getArticleById(@PathVariable(value = "id")articleId: Long):ResponseEntity<Article>{

        return articleRepository.findById(articleId).map { article ->
            ResponseEntity.ok(article)
        }.orElse(ResponseEntity.notFound().build())
    }

    //updating an article
    @PutMapping("/articles/{id}")
    fun updateArticleById(@PathVariable(value = "id")articleId: Long,@Valid @RequestBody newArticle: Article):ResponseEntity<Article>{

        return articleRepository.findById(articleId).map { existingArticle ->
            val updatedArticle: Article = existingArticle
                    .copy(title = newArticle.title,content = newArticle.content)
            ResponseEntity.ok().body(articleRepository.save(updatedArticle))
        }.orElse(ResponseEntity.notFound().build())
    }

    //deleting an article by id
    @DeleteMapping("/article/{articleId}")
    fun deleteArticleById(@PathVariable(value = "id") articleId: Long): ResponseEntity<Void>{

        return articleRepository.findById(articleId).map { article ->
            articleRepository.delete(article)
            ResponseEntity<Void>(HttpStatus.OK)
        }.orElse(ResponseEntity.notFound().build())
    }

}
