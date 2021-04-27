package io.sunil.conduit.ui.article

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import io.sunil.api.ConduitClient
import io.sunil.api.models.entities.Article
import kotlinx.coroutines.launch

class ArticleViewModel : ViewModel() {

    val publicApi = ConduitClient.publicApi

    private val _article = MutableLiveData<Article>()

    val article: LiveData<Article> = _article

    fun getArticle(slug: String) = viewModelScope.launch {
        val response = publicApi.getArticleBySlug(slug)

        response.body()?.article.let {
            _article.postValue(it)
        }
    }



}