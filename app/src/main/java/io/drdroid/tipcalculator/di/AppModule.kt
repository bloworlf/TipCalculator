package io.drdroid.tipcalculator.di

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import io.drdroid.tipcalculator.data.remote.ApiCall
import io.drdroid.tipcalculator.data.remote.ApiDetails
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

@Module //states that current class is a module
@InstallIn(SingletonComponent::class) // informs the scope of class or items inside
class AppModule {

    @Provides
    fun providesOkHttpInstance(): OkHttpClient {
        return OkHttpClient.Builder()
            .addInterceptor(HttpLoggingInterceptor().apply {
                level = HttpLoggingInterceptor.Level.BODY
            })
            .build()
    }

    @Provides
    fun providesRetrofitInstance(client: OkHttpClient): Retrofit {
        return Retrofit.Builder()
            .baseUrl(ApiDetails.API_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .client(client)
            .build()
    }

    @Provides
    fun providesApiUrl(retrofit: Retrofit): ApiCall {
        return retrofit.create(ApiCall::class.java)
    }

}