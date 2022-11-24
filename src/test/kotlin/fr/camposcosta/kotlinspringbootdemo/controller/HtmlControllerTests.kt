package fr.camposcosta.kotlinspringbootdemo.controller

import fr.camposcosta.kotlinspringbootdemo.extension.toSlug
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.boot.test.web.client.TestRestTemplate
import org.springframework.boot.test.web.client.getForEntity
import org.assertj.core.api.Assertions.*;
import org.junit.jupiter.api.AfterAll
import org.junit.jupiter.api.BeforeAll
import org.springframework.http.HttpStatus

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class HtmlControllerTests(@Autowired val restTemplate: TestRestTemplate) {

    /**
     * With test instance per class we can use these instance methods which normally requires to be static
     */
    @BeforeAll
    fun setup() {
        println(">> Setup...")
    }

    /**
     * Expressive function names with ` pretty cool
     */
    @Test
    fun `Assert blog page title, content and status code`() {
        println("Assert blog page title, content and status code")

        val entity = restTemplate.getForEntity<String>("/")

        assertThat(entity.statusCode).isEqualTo(HttpStatus.OK)
        assertThat(entity.body).contains("<h1>Blog</h1>")
    }

    @Test
    fun `Assert article page title, content and status code`() {
        println("Assert article page title, content and status code")

        val title = "Comment etre le meilleur"
        val entity = restTemplate.getForEntity<String>("/article/${title.toSlug()}")

        assertThat(entity.statusCode).isEqualTo(HttpStatus.OK)
        assertThat(entity.body).contains(title, "Pour toujours")
    }

    @AfterAll
    fun tearDown() {
        println(">> Tear down...")
    }
}