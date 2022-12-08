package com.example.data.entity

import com.google.gson.annotations.SerializedName

data class RedditPostResponseDTO(
    val data: RedditPostDataResponseDTO
)

data class RedditPostDataResponseDTO(
    val children: List<RedditPostDTO>
)

data class RedditPostDTO(
    val data:RedditPostDataDTO
)

data class RedditPostDataDTO(
    @SerializedName("title")
    val title:String?,
     @SerializedName("subreddit")
     val subreddit:String?,
     @SerializedName("author_fullname")
     val authorFullName:String?,
     @SerializedName("subreddit_name_prefixed")
     val subredditNamePrefixed:String?,
     @SerializedName("thumbnail_height")
     val thumbnailHeight:Int?,
     @SerializedName("thumbnail_width")
     val thumbnailWidth:Int?,
     @SerializedName("ups")
     val ups:Int?,
     @SerializedName("thumbnail")
     val thumbnail:String?,
)