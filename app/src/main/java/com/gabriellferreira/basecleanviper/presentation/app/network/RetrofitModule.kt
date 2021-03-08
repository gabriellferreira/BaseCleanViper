package com.gabriellferreira.basecleanviper.presentation.app.network

import com.gabriellferreira.basecleanviper.BuildConfig
import com.gabriellferreira.basecleanviper.BuildConfig.BASE_REST_URL
import com.google.gson.GsonBuilder
import dagger.Module
import dagger.Provides
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import okhttp3.Request
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit
import javax.inject.Named

@Module
class RetrofitModule {

    @Provides
    fun provideLogInterceptor(): HttpLoggingInterceptor {
        val interceptor = HttpLoggingInterceptor()
        interceptor.level = if (BuildConfig.DEBUG) {
            HttpLoggingInterceptor.Level.BODY
        } else {
            HttpLoggingInterceptor.Level.NONE
        }
        return interceptor
    }

    @Provides
    @Named(HEADER_INTERCEPTOR)
    fun headerInterceptor() = Interceptor { chain ->
        val request: Request = chain.request().newBuilder().apply {
            //ADD HEADER PARAMETERS HERE
        }.build()
        chain.proceed(request)
    }

    @Provides
    @Named(QUERY_INTERCEPTOR)
    fun queryParameterInterceptor() = Interceptor { chain ->
        val url = chain.request().url.newBuilder().apply {
            //ADD QUERY PARAMETERS HERE
        }.build()

        val request = chain.request().newBuilder().url(url).build()

        chain.proceed(request)
    }

    @Provides
    fun provideRetrofit(
        logInterceptor: HttpLoggingInterceptor,
        @Named(HEADER_INTERCEPTOR)
        headerInterceptor: Interceptor,
        @Named(QUERY_INTERCEPTOR)
        queryInterceptor: Interceptor
    ): Retrofit {
        val builder = OkHttpClient.Builder()
        builder.addInterceptor(logInterceptor)
        builder.addInterceptor(headerInterceptor)
        builder.addInterceptor(queryInterceptor)

        val clientBuilder = builder
            .readTimeout(API_TIMEOUT, TimeUnit.SECONDS)
            .connectTimeout(API_TIMEOUT, TimeUnit.SECONDS)

        val client = clientBuilder.build()
        val retrofitBuilder = Retrofit.Builder()
            .baseUrl(BASE_REST_URL)
            .client(client)
            .addConverterFactory(GsonConverterFactory.create(GsonBuilder().setLenient().create()))

        return retrofitBuilder.build()
    }

    companion object {
        private const val API_TIMEOUT: Long = 30
        private const val HEADER_INTERCEPTOR = "HEADER_INTERCEPTOR"
        private const val QUERY_INTERCEPTOR = "QUERY_INTERCEPTOR"
    }
}