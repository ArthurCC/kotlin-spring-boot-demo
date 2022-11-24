package fr.camposcosta.kotlinspringbootdemo.configuration

import org.springframework.boot.context.properties.ConfigurationProperties
import org.springframework.boot.context.properties.ConstructorBinding

/**
 * ConfigurationProperties binding with Kotlin
 * Works as in java, additionally we use @ConstructorBnding to use immutable properties with data class
 * Use constructor instead of setters for injection
 */
@ConfigurationProperties("blog")
@ConstructorBinding
data class BlogProperties(val title: String, val banner: Banner) {

    data class Banner(val title: String, val content: String)
}