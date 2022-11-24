package fr.camposcosta.kotlinspringbootdemo.controller

import fr.camposcosta.kotlinspringbootdemo.repository.ArticleRepository
import fr.camposcosta.kotlinspringbootdemo.repository.UserRepository
import org.springframework.http.HttpStatus
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController
import org.springframework.web.server.ResponseStatusException

@RestController
@RequestMapping("/api/article")
class ArticleController(private val articleRepository: ArticleRepository) {

    @GetMapping("", "/")
    fun findAll() = articleRepository.findAllByOrderByAddedAt()

    @GetMapping("/{slug}")
    fun findBySlug(@PathVariable slug: String) = articleRepository.findBySlug(slug)
        ?: throw ResponseStatusException(HttpStatus.NOT_FOUND, "This article doesn't exist")
}

@RestController
@RequestMapping("/api/user")
class UserController(private val userRepository: UserRepository) {

    @GetMapping("", "/")
    fun findAll() = userRepository.findAll();

    @GetMapping("/{login}")
    fun findByLogin(@PathVariable login: String) = userRepository.findByLogin(login)
        ?: throw ResponseStatusException(HttpStatus.NOT_FOUND, "This user doesn't exist")
}