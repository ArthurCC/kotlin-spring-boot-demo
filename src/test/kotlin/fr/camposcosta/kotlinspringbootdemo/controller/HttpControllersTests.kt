package fr.camposcosta.kotlinspringbootdemo.controller

import com.ninjasquad.springmockk.MockkBean
import fr.camposcosta.kotlinspringbootdemo.model.Article
import fr.camposcosta.kotlinspringbootdemo.model.User
import fr.camposcosta.kotlinspringbootdemo.repository.ArticleRepository
import fr.camposcosta.kotlinspringbootdemo.repository.UserRepository
import io.mockk.every
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest
import org.springframework.http.MediaType
import org.springframework.test.web.servlet.MockMvc
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get
import org.springframework.test.web.servlet.result.MockMvcResultMatchers.*

/**
 * For mocking with kotlin we use Mockk library
 */
@WebMvcTest
class HttpControllersTests(@Autowired val mockMvc: MockMvc) {

    /**
     * lateinit allows for late initialization
     * Memory is allocated not when class is instanciated but later when they are actually used
     * alternative to declare as nullable and save from nullchecks
     *
     * Necessary wen used with MockBean otherwise we get compile error because property isn't initialized
     * We let mockito initialize property later
     */
    @MockkBean
    private lateinit var articleRepository: ArticleRepository

    @MockkBean
    private lateinit var userRepository: UserRepository

    @Test
    fun `List articles`() {
        val user = User("michellemeilleur", "Michel", "Forever")
        val article1 = Article(
            "Comment etre le meilleur",
            "Je suis le meilleur",
            "Pour toujours, forever",
            user
        )
        val article2 = Article(
            "Michel Insomnie",
            "J'ai des pb de sommeil",
            "Je ne peux pas dormir plus de 1h d'affile sinon je meurs",
            user
        )

        // equivalent to when(...).thenReturn(...)
        every { articleRepository.findAllByOrderByAddedAt() } returns listOf(article1, article2)

        mockMvc.perform(get("/api/article").accept(MediaType.APPLICATION_JSON))
            .andExpect(status().isOk)
            .andExpect(content().contentType(MediaType.APPLICATION_JSON))
            .andExpect(jsonPath("\$.[0].author.login").value(user.login))
            .andExpect(jsonPath("\$.[0].slug").value(article1.slug))
            .andExpect(jsonPath("\$.[1].author.login").value(user.login))
            .andExpect(jsonPath("\$.[1].slug").value(article2.slug))
    }

    @Test
    fun `list users`() {
        val user1 = User("michellemeilleur", "Michel", "Forever")
        val user2 = User("ostiedeceline", "Celine", "Dion")

        every { userRepository.findAll() } returns listOf(user1, user2)

        mockMvc.perform(get("/api/user").accept(MediaType.APPLICATION_JSON))
            .andExpect(status().isOk)
            .andExpect(content().contentType(MediaType.APPLICATION_JSON))
            .andExpect(jsonPath("\$.[0].login").value(user1.login))
            .andExpect(jsonPath("\$.[1].login").value(user2.login))
    }
}