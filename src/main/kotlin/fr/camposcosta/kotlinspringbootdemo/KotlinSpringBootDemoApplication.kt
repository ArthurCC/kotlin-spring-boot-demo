package fr.camposcosta.kotlinspringbootdemo

import fr.camposcosta.kotlinspringbootdemo.configuration.BlogProperties
import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.context.properties.EnableConfigurationProperties
import org.springframework.boot.runApplication

@SpringBootApplication
@EnableConfigurationProperties(BlogProperties::class)
class KotlinSpringBootDemoApplication

fun main(args: Array<String>) {
    runApplication<KotlinSpringBootDemoApplication>(*args)
}
