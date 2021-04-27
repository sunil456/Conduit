package io.sunil.conduit.data

import io.sunil.api.ConduitClient
import io.sunil.api.models.entities.User
import io.sunil.api.models.entities.UserUpdateData
import io.sunil.api.models.requests.LoginRequest
import io.sunil.api.models.requests.SignupRequest
import io.sunil.api.models.requests.UserCreds
import io.sunil.api.models.requests.UserUpdateRequest
import io.sunil.api.models.responses.UserResponse

object AuthRepo {

    val api = ConduitClient.publicApi
    val authApi = ConduitClient.authApi

    suspend fun login(email: String, password: String): User? {
        val response = api.loginUser(LoginRequest(UserCreds(email = email, password = password, username = null)))
        ConduitClient.authToken = response.body()?.user?.token
        return response.body()?.user
    }

    suspend fun signup(userName:String,email: String, password: String) : User? {
        val response = api.signupUser(SignupRequest(UserCreds(email = email, password = password, username = userName)))
        ConduitClient.authToken = response.body()?.user?.token

        return response.body()?.user
    }

    suspend fun update(bio:String? , userName: String?, image: String?, email: String?, password: String?): User?{
        val response = authApi.updateCurrentUser(
                UserUpdateRequest(UserUpdateData(bio = bio, email = email, image = image, password = password, username = userName))
        )

        return response.body()?.user
    }


    suspend fun getCurrentUser(token: String): User? {
        ConduitClient.authToken = token
        return authApi.getCurrentUser().body()?.user
    }

    suspend fun getUserProfile() = authApi.getCurrentUser().body()?.user

}