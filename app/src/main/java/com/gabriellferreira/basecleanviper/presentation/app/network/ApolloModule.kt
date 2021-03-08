package com.gabriellferreira.basecleanviper.presentation.app.network

import com.apollographql.apollo.ApolloClient
import com.apollographql.apollo.fetcher.ApolloResponseFetchers
import com.gabriellferreira.basecleanviper.BuildConfig.BASE_GRAPHQL_URL
import dagger.Module
import dagger.Provides
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import okhttp3.Request
import okhttp3.logging.HttpLoggingInterceptor
import java.util.concurrent.TimeUnit
import javax.inject.Named

@Module
class ApolloModule {

    @Provides
    @Named(APOLLO_QUERY_INTERCEPTOR)
    fun apolloQueryParameterInterceptor() = Interceptor { chain ->
        val url = chain.request().url.newBuilder().apply {
            //ADD QUERY PARAMETERS HERE
        }.build()

        val request = chain.request().newBuilder().url(url).build()

        return@Interceptor chain.proceed(request)
    }


    @Provides
    @Named(APOLLO_HEADER_INTERCEPTOR)
    fun apolloHeaderInterceptor() = Interceptor { chain ->
        val request: Request = chain.request().newBuilder().apply {
            //ADD HEADER PARAMETERS HERE
        }.build()
        return@Interceptor chain.proceed(request)
    }

    @Provides
    @Named(APOLLO_OK_HTTP_CLIENT)
    fun provideClient(
        logInterceptor: HttpLoggingInterceptor,
        @Named(APOLLO_HEADER_INTERCEPTOR)
        headerInterceptor: Interceptor,
        @Named(APOLLO_QUERY_INTERCEPTOR)
        queryInterceptor: Interceptor
    ): OkHttpClient {
        val builder = OkHttpClient.Builder().apply {
            addInterceptor(logInterceptor)
            addInterceptor(headerInterceptor)
            addInterceptor(queryInterceptor)
        }
        val clientBuilder = builder
            .readTimeout(API_TIMEOUT, TimeUnit.SECONDS)
            .connectTimeout(API_TIMEOUT, TimeUnit.SECONDS)
        return clientBuilder.build()
    }

    @Provides
    fun provideApollo(
        @Named(APOLLO_OK_HTTP_CLIENT)
        okHttpClient: OkHttpClient
    ): ApolloClient {
        val apolloBuilder = ApolloClient.builder()
            .defaultResponseFetcher(ApolloResponseFetchers.NETWORK_FIRST)
            .serverUrl(BASE_GRAPHQL_URL)
            .okHttpClient(okHttpClient)
        return apolloBuilder.build()
    }

    companion object {
        private const val API_TIMEOUT: Long = 30
        private const val APOLLO_HEADER_INTERCEPTOR = "APOLLO_HEADER_INTERCEPTOR"
        private const val APOLLO_OK_HTTP_CLIENT = "APOLLO_OK_HTTP_CLIENT"
        private const val APOLLO_QUERY_INTERCEPTOR = "APOLLO_QUERY_INTERCEPTOR"
    }
}