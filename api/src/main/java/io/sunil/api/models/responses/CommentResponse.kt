package io.sunil.api.models.responses


import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass
import io.sunil.api.models.entities.Comment

@JsonClass(generateAdapter = true)
data class CommentResponse(
    @Json(name = "comment")
    val comment: Comment
)