package io.sunil.api.services

import io.sunil.api.models.requests.LoginRequest
import io.sunil.api.models.requests.SignupRequest
import io.sunil.api.models.requests.UserCreds
import io.sunil.api.models.responses.ArticleResponse
import io.sunil.api.models.responses.ArticlesResponse
import io.sunil.api.models.responses.TagsResponse
import io.sunil.api.models.responses.UserResponse
import retrofit2.Response
import retrofit2.http.*

interface ConduitApi {

    @POST("users")
    suspend fun signupUser(
        @Body userCred: SignupRequest
    ): Response<UserResponse>

    @POST("users/login")
    suspend fun loginUser(
        @Body userCred: LoginRequest
    ): Response<UserResponse>

    @GET("articles")
   suspend fun getArticles(
        @Query("author") author: String? = null,
        @Query("favorited") favorited: String? = null,
        @Query("tag") tag: String? = null
   ): Response<ArticlesResponse>

   @GET("articles/{slug}")
   suspend fun getArticleBySlug(
       @Path("slug") slug: String
   ): Response<ArticleResponse>

   @GET("tags")
   suspend fun getTags(): Response<TagsResponse>
}