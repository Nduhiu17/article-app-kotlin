package com.nduhiu.kotlindemo.repository

import com.nduhiu.kotlindemo.model.Article
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository

/*
* repository that involves operations of an article
* */
@Repository
interface ArticleRepository : JpaRepository<Article,Long>
