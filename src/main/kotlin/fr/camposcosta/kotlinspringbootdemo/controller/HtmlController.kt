package fr.camposcosta.kotlinspringbootdemo.controller

import fr.camposcosta.kotlinspringbootdemo.configuration.BlogProperties
import fr.camposcosta.kotlinspringbootdemo.model.render
import fr.camposcosta.kotlinspringbootdemo.repository.ArticleRepository
import org.springframework.http.HttpStatus
import org.springframework.stereotype.Controller
import org.springframework.ui.Model
import org.springframework.ui.set
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.server.ResponseStatusException

/**
 * Implicit constructor autowire here
 */
@Controller
class HtmlController(
    private val articleRepository: ArticleRepository,
    private val blogProperties: BlogProperties
) {

    @GetMapping("/")
    fun blog(model: Model): String {
        model["title"] = blogProperties.title // equivalent to model.addAttribute("title", "Blog"). Kotlin extension made by Spring
        model["banner"] = blogProperties.banner
        model["articles"] = articleRepository.findAllByOrderByAddedAt()
            .map { it.render() }

        return "blog"
    }

    @GetMapping("/article/{slug}")
    fun article(@PathVariable slug: String, model: Model): String {
        val article = articleRepository.findBySlug(slug)
            ?.render()
            ?: throw ResponseStatusException(HttpStatus.NOT_FOUND, "This article doesn't exist")

        model["title"] = article.title
        model["article"] = article

        return "article"
    }
}