package fr.camposcosta.kotlinspringbootdemo.model

import fr.camposcosta.kotlinspringbootdemo.extension.toSlug
import java.time.LocalDateTime
import javax.persistence.Entity
import javax.persistence.GeneratedValue
import javax.persistence.Id
import javax.persistence.ManyToOne
import javax.persistence.Table

/**
 * Note : JPA is not designed to work w/ data classes with immutable "val" attributes
 */
@Entity
@Table(name = "blg_article")
class Article(
    var title: String,
    var headline: String,
    var content: String,
    @ManyToOne var author: User,
    var slug: String = title.toSlug(),
    var addedAt: LocalDateTime = LocalDateTime.now(),
    // Id is optional because when we create instance, id is actually null and generated after by db
    // we define it last so that we can omit it from object initialization
    @Id @GeneratedValue var id: Long? = null
)

@Entity
@Table(name = "blg_user")
class User(
    var login: String,
    var firstName: String,
    var lastName: String,
    var description: String? = null,
    @Id @GeneratedValue var id: Long? = null
)