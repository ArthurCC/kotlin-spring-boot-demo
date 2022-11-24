package fr.camposcosta.kotlinspringbootdemo.repository

import fr.camposcosta.kotlinspringbootdemo.model.Article
import fr.camposcosta.kotlinspringbootdemo.model.User
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager
import org.springframework.data.repository.findByIdOrNull
import org.assertj.core.api.Assertions.*;

/**
 * When we want to autowire using constructor injection, we need to append "constructor" after annotation
 * as opposed to field injection in IntegrationTests
 */
@DataJpaTest
class RepositoriesTests @Autowired constructor(
    val entityManager: TestEntityManager,
    val userRepository: UserRepository,
    val articleRepository: ArticleRepository
) {

    @Test
    fun `When findByIdOrNull then return Article`() {
        val user = User("michellemeilleur", "Michel", "Forever")
        entityManager.persist(user)

        val article = Article(
            "Comment etre le meilleur",
            "Je suis le meilleur",
            "Pour toujours, forever",
            user
        )
        entityManager.persist(article)
        entityManager.flush()

        // !! convert nullable type to non-nullable type so Long? to Long
        // throws NPE if value is actually null
        // since article was persisted ID should be generated and therefore not be null
        val result = articleRepository.findByIdOrNull(article.id!!)
        assertThat(result).isEqualTo(article)
    }

    @Test
    fun `When findByLogin then return User`() {
        val user = User("michellemeilleur", "Michel", "Forever")
        entityManager.persist(user)
        entityManager.flush()

        val result = userRepository.findByLogin(user.login)
        assertThat(result).isEqualTo(user)
    }
}