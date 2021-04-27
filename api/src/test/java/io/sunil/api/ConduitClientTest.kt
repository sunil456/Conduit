package io.sunil.api

import io.sunil.api.models.requests.SignupRequest
import io.sunil.api.models.requests.UserCreds
import kotlinx.coroutines.runBlocking
import org.junit.Assert.assertEquals
import org.junit.Assert.assertNotNull
import org.junit.Test
import kotlin.random.Random

class ConduitClientTest {

    private val conduitClient = ConduitClient()

    @Test
    fun `GET articles`(){

        runBlocking {
            val articles = conduitClient.publicApi.getArticles()
            assertNotNull(articles.body()?.articles)
        }
    }

    @Test
    fun `GET articles by author`(){

        runBlocking {
            val articles = conduitClient.publicApi.getArticles(author = "johnjacob")
            assertNotNull(articles.body()?.articles)
        }
    }

    @Test
    fun `GET articles by tag`(){

        runBlocking {
            val articles = conduitClient.publicApi.getArticles(tag = "dragons")
            assertNotNull(articles.body()?.articles)
        }
    }

    @Test
    fun `POST users - create user`(){
        val userCred = UserCreds(
        email = "testemail${Random.nextInt(999, 9999)}@test.com",
        password = "password${Random.nextInt(999, 9999)}",
        username = "random_user_${Random.nextInt(99, 9999)}"
        )
        runBlocking {
            val response = conduitClient.publicApi.signupUser(SignupRequest(userCred))
            assertEquals(userCred.username,response.body()?.user?.username)
        }
    }
}