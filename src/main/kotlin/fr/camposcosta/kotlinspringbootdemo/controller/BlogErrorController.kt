package fr.camposcosta.kotlinspringbootdemo.controller

import fr.camposcosta.kotlinspringbootdemo.configuration.BlogProperties
import org.springframework.boot.web.servlet.error.ErrorController
import org.springframework.stereotype.Controller
import org.springframework.ui.Model
import org.springframework.ui.set
import org.springframework.web.bind.annotation.GetMapping

@Controller
class BlogErrorController(private val blogProperties: BlogProperties): ErrorController {

    @GetMapping("/error")
    fun handleError(model: Model): String {
        model["title"] = blogProperties.title
        model["statusCode"] = "404"
        model["errorMessage"] = "Item was not found"

        return "error"
    }
}