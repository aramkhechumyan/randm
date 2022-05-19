package com.task.rickandmorty.data.networck.retrofit


import org.koin.dsl.module
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

val networkModule = module {
    single { provideRetrofit() }
    single { provideTaskAPIService(get()) }
}

fun provideRetrofit(): Retrofit {
    return Retrofit.Builder()
        .baseUrl("https://rickandmortyapi.com/")
        .addConverterFactory(GsonConverterFactory.create())
        .build()
}

fun provideTaskAPIService(retrofit: Retrofit): APIService {
    return retrofit.create(APIService::class.java)
}