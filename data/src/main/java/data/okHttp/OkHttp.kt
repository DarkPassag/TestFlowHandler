package data.okHttp


import data.PostModel
import kotlinx.serialization.encodeToString
import kotlinx.serialization.json.Json
import okhttp3.Call
import okhttp3.HttpUrl.Companion.toHttpUrl
import okhttp3.MediaType.Companion.toMediaType
import okhttp3.OkHttpClient
import okhttp3.Request
import okhttp3.RequestBody.Companion.toRequestBody
import okhttp3.logging.HttpLoggingInterceptor
import org.json.JSONObject

const val BASE_URL = "https://jsonplaceholder.typicode.com/"

object OkHttp {

    private val logging =
        HttpLoggingInterceptor()
        .setLevel(HttpLoggingInterceptor.Level.HEADERS)


    private val client =
        OkHttpClient.Builder()
        .addInterceptor(logging)
        .build()



    fun getListOfUsers(): Call {
        val newUrl = BASE_URL.toHttpUrl().newBuilder().addPathSegment("users")
        val getRequest = Request.Builder()
            .url(newUrl.build())
            .build()

        return client.newCall(getRequest)
    }

    fun getListPostsByUser(id: Int): Call{
       val newUrl = BASE_URL.toHttpUrl().newBuilder()
           .addPathSegment("posts")
           .addQueryParameter("userId", "$id")


        val requestPosts = Request.Builder()
            .url(newUrl.build())
            .build()
        return client.newCall(requestPosts)
    }

    fun addNewPost(postModel: PostModel): Call {

        val newUrl = BASE_URL.toHttpUrl().newBuilder().addPathSegment("posts")

        val body = Json.encodeToString(postModel)

        val addPost = Request.Builder()
            .url(newUrl.build())
            .post(body.toRequestBody())
            .addHeader("Content-type", "application/json; charset=UTF-8")
            .build()

        return client.newCall(addPost)

    }

    fun updatePost(postModel: PostModel): Call{

        val newUrl = BASE_URL.toHttpUrl().newBuilder().apply {
            addPathSegment("posts")
            addPathSegment(postModel.id.toString())
        }


        val body = Json.encodeToString(postModel)

        val updatePost = Request.Builder()
            .url(newUrl.build())
            .put(body.toRequestBody())
            .addHeader("Content-type", "application/json; charset=UTF-8")
            .build()

        return client.newCall(updatePost)

    }

    fun patchPost(id: Int, title: String): Call{

        val newUrl = BASE_URL.toHttpUrl().newBuilder().apply {
            addPathSegment("posts")
            addPathSegment(id.toString())
        }

            val body = JSONObject()
            body.put("title", title)

            val mediaType = "application/json; charset=UTF-8".toMediaType()

            val request = Request.Builder()
                .url(newUrl.build())
                .patch(body.toString().toRequestBody(mediaType))
                .build()

            return client.newCall(request)
        }
    }





