package com.example.presentation.data
import androidx.annotation.DrawableRes
import com.example.presentation.R

data class Post(
    val id: String,
    val title: String?,
    val subtitle: String? = null,
    val url: String,
    val publication: Publication? = null,
    val metadata: Metadata,
    val paragraphs: List<Paragraph> = emptyList(),
    @DrawableRes val imageId: Int,
    @DrawableRes val imageThumbId: Int
)

data class Metadata(
    val author: PostAuthor,
    val date: String,
    val readTimeMinutes: String
)

data class PostAuthor(
    val name: String,
    val url: String? = null
)

data class Publication(
    val name: String,
    val logoUrl: String
)

data class Paragraph(
    val type: ParagraphType,
    val text: String,
    val markups: List<Markup> = emptyList()
)

data class Markup(
    val type: MarkupType,
    val start: Int,
    val end: Int,
    val href: String? = null
)

enum class MarkupType {
    Link,
    Code,
    Italic,
    Bold,
}

enum class ParagraphType {
    Title,
    Caption,
    Header,
    Subhead,
    Text,
    CodeBlock,
    Quote,
    Bullet,
}


val mockPost =  Post(
    id = "ac552dcc1741",
    title = "From Java Programming Language to Kotlin — the idiomatic way",
    subtitle = "Learn how to get started converting Java Programming Language code to Kotlin, making it more idiomatic and avoid common pitfalls, by…",
    url = "https://medium.com/androiddevelopers/from-java-programming-language-to-kotlin-the-idiomatic-way-ac552dcc1741",
    publication = Publication(
        "Android Developers",
        "https://cdn-images-1.medium.com/max/258/1*u7oZc2_5mrkcFaxkXEyfYA@2x.png"
    ),
    metadata = Metadata(
        author = PostAuthor("Jose Alcérreca", "https://medium.com/@JoseAlcerreca"),
        date = "July 09",
        readTimeMinutes = "1"
    ),
    paragraphs = listOf(),
    imageId = R.drawable.ic_jetnews_logo,
    imageThumbId = R.drawable.ic_jetnews_wordmark
)
