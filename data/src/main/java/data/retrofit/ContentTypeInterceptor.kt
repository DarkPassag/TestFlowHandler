package data.retrofit

import okhttp3.Interceptor
import okhttp3.Response

class ContentTypeInterceptor: Interceptor {
    override fun intercept(chain: Interceptor.Chain): Response {
        val header = chain.request().newBuilder()
//            .addHeader("Content-type", "application/json; charset=UTF-8")
            .build()

        return chain.proceed(request = header)
    }
}