package io.sunil.conduit.ui.auth

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import io.sunil.api.models.entities.User
import io.sunil.conduit.data.AuthRepo
import kotlinx.coroutines.launch

class AuthViewModel : ViewModel() {

    private val _user = MutableLiveData<User?>()

    val user: LiveData<User?> = _user

    fun getCurrentUser(token: String) = viewModelScope.launch {
        AuthRepo.getCurrentUser(token)?.let {
            _user.postValue(it)
        }
    }

    fun login(email: String, password: String) = viewModelScope.launch {
        AuthRepo.login(email, password)?.let {
            _user.postValue(it)
        }
    }

    fun signup(userName:String, email: String, password: String)  = viewModelScope.launch {
        AuthRepo.signup(userName, email, password)?.let {
            _user.postValue(it)
        }
    }

    fun update(bio:String? , userName: String?, image: String?, email: String?, password: String?) = viewModelScope.launch {
        AuthRepo.update(bio, userName, image, email, password)?.let {
            _user.postValue(it)
        }
    }

    fun logout() {
        _user.postValue(null)
    }

}