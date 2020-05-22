package com.vishal.paytminsider.common.di.network

import com.vishal.paytminsider.BuildConfig
import com.vishal.paytminsider.data.network.repository.PaytmRepository
import com.vishal.paytminsider.data.network.repository.PaytmRepositoryImpl
import com.vishal.paytminsider.data.network.services.PaytmService
import dagger.Module
import dagger.Provides
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit
import javax.inject.Singleton

@Module
class NetworkModule {

    @Provides
    @Singleton
    fun providesRetrofit(okHttpClient: OkHttpClient): Retrofit =
        Retrofit.Builder()
            .baseUrl(BuildConfig.BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .client(okHttpClient)
            .build()

    @Provides
    fun providesOkHttpClient(
        okHttpClientBuilder: OkHttpClient.Builder,
        httpLoggingInterceptor: HttpLoggingInterceptor
    ): OkHttpClient {


        if (BuildConfig.DEBUG) {
            httpLoggingInterceptor.level = HttpLoggingInterceptor.Level.BODY
            okHttpClientBuilder.addNetworkInterceptor(httpLoggingInterceptor)
        }

        okHttpClientBuilder/*.cookieJar(cookieJar)*/
            .connectTimeout(20, TimeUnit.SECONDS)
            .readTimeout(20, TimeUnit.SECONDS)

        return okHttpClientBuilder.build()
    }

    @Provides
    fun provideOkHttpClientBuilder(): OkHttpClient.Builder = OkHttpClient.Builder()

    @Provides
    fun provideHttpLoggingInterceptor(): HttpLoggingInterceptor = HttpLoggingInterceptor()

    @Provides
    fun providePaytmService(retrofit: Retrofit): PaytmService =
        retrofit.create(PaytmService::class.java)

    @Provides
    fun providePaytmRepository(paytmService: PaytmService): PaytmRepository =
        PaytmRepositoryImpl(paytmService)

}