package fr.camposcosta.kotlinspringbootdemo.repository

import fr.camposcosta.kotlinspringbootdemo.model.Article
import fr.camposcosta.kotlinspringbootdemo.model.User
import org.springframework.data.repository.CrudRepository

interface ArticleRepository: CrudRepository<Article, Long> {
    fun findBySlug(slug: String): Article?
    fun findAllByOrderByAddedAt(): Iterable<Article>
}

interface UserRepository: CrudRepository<User, Long> {
    fun findByLogin(login: String): User?
}