package io.sunil.conduit.ui.feed

import android.util.Log
import android.widget.Toast
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import io.sunil.api.models.entities.Article
import io.sunil.conduit.data.ArticlesRepo
import kotlinx.coroutines.launch

class FeedViewModel: ViewModel() {

    private val _feed = MutableLiveData<List<Article>>()

    val feed: LiveData<List<Article>> = _feed

    fun getGlobalFeed() = viewModelScope.launch {
        ArticlesRepo.getGlobalFeed()?.let {
            _feed.postValue(it)
        }

    }

    fun getMyFeed() = viewModelScope.launch {
        ArticlesRepo.getMyFeeds()?.let {
            _feed.postValue(it)
        }
    }


}