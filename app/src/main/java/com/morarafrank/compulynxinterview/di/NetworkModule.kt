package com.morarafrank.compulynxinterview.di

import com.google.gson.Gson
import com.jakewharton.retrofit2.adapter.kotlin.coroutines.CoroutineCallAdapterFactory
import com.morarafrank.compulynxinterview.data.remote.CompulynxService
import com.morarafrank.compulynxinterview.utils.Constants
import com.morarafrank.compulynxinterview.utils.Constants.BASE_URL
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit
import javax.inject.Named
import javax.inject.Singleton


@Module
@InstallIn(SingletonComponent::class)
object NetworkModule {


    @Provides
    @Singleton
    fun provideGson(): Gson = Gson()


    @Provides
    @Singleton
    fun provideGsonConverterFactory(): GsonConverterFactory =
        GsonConverterFactory.create()

    @Provides
    @Named("baseUrl")
    fun provideBaseUrl(): String =  Constants.BASE_URL


    @Singleton
    @Provides
    fun provideHttpClient(interceptors: Set<@JvmSuppressWildcards Interceptor>): OkHttpClient {
        return OkHttpClient.Builder()
            .readTimeout(60, TimeUnit.SECONDS)
            .connectTimeout(60, TimeUnit.SECONDS)
            .apply {
                interceptors().addAll(interceptors)
            }
            .build()
    }

    @Provides
    fun provideInterceptors(): Set<Interceptor> {
        return setOf(
            Interceptor { chain ->
                val request = chain.request().newBuilder()
                    .addHeader("Content-Type", "application/json")
                    .addHeader("Accept", "application/json")
                    .build()
                chain.proceed(request)
            }
        )
    }

    @Provides
    fun provideRetrofit(
        okHttpClient: OkHttpClient,
        gsonConverterFactory: GsonConverterFactory,
//        @Named("baseUrl") baseUrl: String
    ): Retrofit =
        Retrofit.Builder()
            .addConverterFactory(gsonConverterFactory)
            .addCallAdapterFactory(CoroutineCallAdapterFactory())
            .client(okHttpClient)
            .baseUrl(Constants.BASE_URL)
            .build()

    // provide compulynx service
    @Provides
    fun provideCompulynxService(retrofit: Retrofit): CompulynxService =
        retrofit.create(CompulynxService::class.java)
}