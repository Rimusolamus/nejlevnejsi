package cz.rimu.nejlevnejsi.di

import cz.rimu.nejlevnejsi.rest.Api
import cz.rimu.nejlevnejsi.BuildConfig.API_URL
import org.koin.dsl.module
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

val networkModule = module {
    factory { provideRestApi(get()) }
    single { provideRetrofit() }
}

fun provideRetrofit(): Retrofit {
    return Retrofit.Builder().baseUrl(API_URL)
        .addConverterFactory(GsonConverterFactory.create()).build()
}

fun provideRestApi(retrofit: Retrofit): Api = retrofit.create(Api::class.java)