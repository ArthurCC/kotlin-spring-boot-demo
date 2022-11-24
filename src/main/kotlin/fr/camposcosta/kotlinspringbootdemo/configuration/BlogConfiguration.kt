package fr.camposcosta.kotlinspringbootdemo.configuration

import fr.camposcosta.kotlinspringbootdemo.model.Article
import fr.camposcosta.kotlinspringbootdemo.model.User
import fr.camposcosta.kotlinspringbootdemo.repository.ArticleRepository
import fr.camposcosta.kotlinspringbootdemo.repository.UserRepository
import org.springframework.boot.ApplicationRunner
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration

@Configuration
class BlogConfiguration {

    @Bean
    fun dbInitializer(
        userRepository: UserRepository,
        articleRepository: ArticleRepository
    ) = ApplicationRunner {
        val user = userRepository.save(
            User("michellemeilleur", "Michel", "Forever")
        )

        articleRepository.save(
            Article(
                "Comment etre le meilleur",
                "Je suis le meilleur",
                "Pour toujours, forever",
                user
            )
        )
        articleRepository.save(
            Article(
                "Michel Insomnie",
                "J'ai des pb de sommeil",
                "Je ne peux pas dormir plus de 1h d'affile sinon je meurs",
                user
            )
        )
    }
}