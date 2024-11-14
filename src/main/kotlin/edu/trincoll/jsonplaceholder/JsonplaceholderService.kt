package edu.trincoll.jsonplaceholder

import io.ktor.client.HttpClient
import io.ktor.client.call.body
import io.ktor.client.engine.cio.CIO
import io.ktor.client.plugins.contentnegotiation.ContentNegotiation
import io.ktor.client.request.accept
import io.ktor.client.request.delete
import io.ktor.client.request.get
import io.ktor.client.request.post
import io.ktor.client.request.put
import io.ktor.client.request.setBody
import io.ktor.client.statement.HttpResponse
import io.ktor.http.ContentType
import io.ktor.http.contentType
import io.ktor.serialization.kotlinx.json.json
import kotlinx.serialization.Serializable
import kotlinx.serialization.json.Json

@Serializable
data class BlogPost(
    //val userId: Int,
    val id: Int,
    val title: String,
    val body: String,
    val model: String, 
    val prompt: String,
    val stream: Boolean = false
)

class ModelClient {
    private val client = HttpClient(CIO) {
        install(ContentNegotiation) {
            json(Json {
                ignoreUnknownKeys = true
            })
        }
    }
    suspend fun callModel(request: BlogPost): BlogPost {
        return client.post("http://localhost:11434/api/generate") {
            contentType(ContentType.Application.Json)
            accept(ContentType.Application.Json)
            setBody(request)
        }.body()
    }
}
class JsonPlaceholderService {
    private val baseUrl = "https://jsonplaceholder.typicode.com"

    private fun createHttpClient(): HttpClient {
        return HttpClient(CIO) {
            install(ContentNegotiation) {
                json(Json {
                    ignoreUnknownKeys = true
                })
            }
        }
    }

    suspend fun getPosts(): List<BlogPost> =
        createHttpClient().use { client ->
            client.get("$baseUrl/posts") {
                accept(ContentType.Application.Json)
            }.body()
        }

    suspend fun getPost(index: Int): HttpResponse =
        createHttpClient().use { client ->
            client.get("$baseUrl/posts/$index") {
                accept(ContentType.Application.Json)
            }
        }

    suspend fun insertPost(post: BlogPost): HttpResponse =
        createHttpClient().use { client ->
            client.post("$baseUrl/posts") {
                contentType(ContentType.Application.Json)
                accept(ContentType.Application.Json)
                setBody(post)
            }
        }

    suspend fun updatePost(post: BlogPost): BlogPost =
        createHttpClient().use { client ->
            client.put("$baseUrl/posts/${post.id}") {
                contentType(ContentType.Application.Json)
                accept(ContentType.Application.Json)
                setBody(post)
            }.body()
        }

    suspend fun deletePost(index: Int): HttpResponse =
        createHttpClient().use { client ->
            client.delete("$baseUrl/posts/$index")
        }
}
fun main() = runBlocking {
    val modelClient = ModelClient()

    val request = BlogPost(
        id = 1,
        title = "Welcome",
        body = "Body",
        model = "llama3.2",
        prompt = "Is The Sky Blue?",
        stream = false
    )
     val response = modelClient.callModel(request)
    println("Response from model: ${response.body}")
}
