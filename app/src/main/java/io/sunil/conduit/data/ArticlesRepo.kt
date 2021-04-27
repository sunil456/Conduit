package io.sunil.conduit.data

import io.sunil.api.ConduitClient

object ArticlesRepo {
    val api = ConduitClient.publicApi
    val authApi = ConduitClient.authApi

    suspend fun getGlobalFeed() = api.getArticles().body()?.articles

    suspend fun getMyFeeds() = authApi.getFeedArticles().body()?.articles
}