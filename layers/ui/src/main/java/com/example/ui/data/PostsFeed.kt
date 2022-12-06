package com.example.ui.data

/**
 * A container of [Post]s, partitioned into different categories.
 */
data class PostsFeed(
    val highlightedPost: Post,
    val recommendedPosts: List<Post>,
    val popularPosts: List<Post>,
    val recentPosts: List<Post>,
) {
    /**
     * Returns a flattened list of all posts contained in the feed.
     */
    val allPosts: List<Post> =
        listOf(highlightedPost) + recommendedPosts + popularPosts + recentPosts
}

val mockPostFeed = PostsFeed(
    mockPost,
    listOf(mockPost,mockPost),
    listOf(mockPost,mockPost),
    listOf(mockPost,mockPost),
)