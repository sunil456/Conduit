package io.sunil.api.services

import io.sunil.api.models.requests.UserUpdateRequest
import io.sunil.api.models.responses.ArticleResponse
import io.sunil.api.models.responses.ArticlesResponse
import io.sunil.api.models.responses.ProfileResponse
import io.sunil.api.models.responses.UserResponse
import retrofit2.Response
import retrofit2.http.*

interface ConduitAuthAPI {

    @GET("user")
    suspend fun getCurrentUser(): Response<UserResponse>

    @PUT("user")
    suspend fun updateCurrentUser(
        @Body updateRequest: UserUpdateRequest
    ): Response<UserResponse>

    @GET("profiles/{username}")
    suspend fun getProfile(
        @Path("username") username: String
    ): Response<ProfileResponse>

    @POST("profiles/{username}/follow")
    suspend fun followProfile(
        @Path("username") username: String
    ): Response<ProfileResponse>

    @DELETE("profile/{username}/follow")
    suspend fun unfollowProfile(
        @Path("username") username: String
    ): Response<ProfileResponse>

    @GET("articles/feed")
    suspend fun getFeedArticles(): retrofit2.Response<ArticlesResponse>

    @POST("articles/{slug}/favorite")
    suspend fun favoriteArticle(
        @Path("slug") slug: String
    ) : Response<ArticleResponse>

    @DELETE("articles/{slug}/favorite")
    suspend fun unFavoriteArticle(
        @Path("slug") slug: String
    ) : Response<ArticleResponse>
}