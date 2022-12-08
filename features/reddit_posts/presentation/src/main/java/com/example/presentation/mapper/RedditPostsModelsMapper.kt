package com.example.presentation.mapper

import com.example.domain.model.RedditPostsModel
import com.example.presentation.R
import com.example.presentation.composables.HomeUiState
import com.example.presentation.data.Metadata
import com.example.presentation.data.Post
import com.example.presentation.data.PostAuthor
import com.example.presentation.data.PostsFeed
import com.example.presentation.data.Publication
import com.example.presentation.data.mockPost
import com.example.utils.DataMapper
import javax.inject.Inject

internal class RedditPostsModelsMapper @Inject constructor() : DataMapper<List<RedditPostsModel>, HomeUiState> {
    override fun map(input: List<RedditPostsModel>): HomeUiState {
        val posts =  input.map {
            Post(
                id = "ac552dcc1741",
                title = it.title,
                subtitle = "Learn how to get started converting Java Programming Language code to Kotlin, making it more idiomatic and avoid common pitfalls, by…",
                url = "https://medium.com/androiddevelopers/from-java-programming-language-to-kotlin-the-idiomatic-way-ac552dcc1741",
                publication = Publication(
                    "Android Developers",
                    "https://cdn-images-1.medium.com/max/258/1*u7oZc2_5mrkcFaxkXEyfYA@2x.png"
                ),
                metadata = Metadata(
                    author = PostAuthor(it.authorFullName ?: "", "https://medium.com/@JoseAlcerreca"),
                    date = "July 09",
                    readTimeMinutes = it.subreddit ?: ""
                ),
                paragraphs = listOf(),
                imageId = R.drawable.ic_jetnews_logo,
                imageThumbId = R.drawable.ic_jetnews_wordmark
            )
        }
        val postsFeed = PostsFeed(
            highlightedPost = mockPost,
            recommendedPosts = posts,
            popularPosts = posts,
            recentPosts = posts,
        )
        return HomeUiState.HasPosts(
            postsFeed = postsFeed,
            selectedPost = postsFeed.highlightedPost,
            isArticleOpen = false,
            favorites = emptySet(),
            isLoading = false,
            errorMessages = emptyList(),
            searchInput = ""
        )
    }
}