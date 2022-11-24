package fr.camposcosta.kotlinspringbootdemo.model

import fr.camposcosta.kotlinspringbootdemo.extension.format

fun Article.render() = RenderedArticle(
    slug,
    title,
    headline,
    content,
    author,
    addedAt.format()
)

data class RenderedArticle(
    val slug: String,
    val title: String,
    val headline: String,
    val content: String,
    val author: User,
    val addedAt: String
)