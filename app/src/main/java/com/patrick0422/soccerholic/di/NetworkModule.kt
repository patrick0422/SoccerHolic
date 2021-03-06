package com.patrick0422.soccerholic.di

import com.patrick0422.soccerholic.data.remote.api.SearchApi
import com.patrick0422.soccerholic.util.Constants.API_HOST
import com.patrick0422.soccerholic.util.Constants.API_KEY
import com.patrick0422.soccerholic.util.Constants.BASE_URL
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit
import javax.inject.Singleton


@Module
@InstallIn(SingletonComponent::class)
object NetworkModule {
    @Singleton
    @Provides
    fun provideHttpLoggingInterceptor(): HttpLoggingInterceptor = HttpLoggingInterceptor()
        .setLevel(HttpLoggingInterceptor.Level.BODY)

    @Singleton
    @Provides
    fun provideHeaderInterceptor(): Interceptor = Interceptor {
        chain -> chain.run {
            proceed(
                request()
                    .newBuilder()
                    .addHeader("x-rapidapi-host", API_HOST)
                    .addHeader("x-rapidapi-key", API_KEY)
                    .build()
            )
        }
    }

    @Singleton
    @Provides
    fun provideOkHttpsClient(
        httpLoggingInterceptor: HttpLoggingInterceptor,
        headerInterceptor: Interceptor
    ): OkHttpClient = OkHttpClient.Builder()
        .connectTimeout(30, TimeUnit.SECONDS)
        .readTimeout(30, TimeUnit.SECONDS)
        .writeTimeout(30, TimeUnit.SECONDS)
        .addInterceptor(httpLoggingInterceptor)
        .addInterceptor(headerInterceptor)
        .build()

    @Singleton
    @Provides
    fun provideGsonConverterFactory(): GsonConverterFactory = GsonConverterFactory.create()

    @Singleton
    @Provides
    fun provideRetrofitInstance(
        okHttpClient: OkHttpClient,
        gsonConverterFactory: GsonConverterFactory
    ): Retrofit = Retrofit.Builder()
        .baseUrl(BASE_URL)
        .client(okHttpClient)
        .addConverterFactory(gsonConverterFactory)
        .build()

    @Singleton
    @Provides
    fun provideSearchApi(retrofit: Retrofit): SearchApi = retrofit.create(SearchApi::class.java)
}