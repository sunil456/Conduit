package io.sunil.api.models.responses


import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass
import io.sunil.api.models.entities.Error

@JsonClass(generateAdapter = true)
data class ErrorsResponse(
    @Json(name = "errors")
    val error: Error
)