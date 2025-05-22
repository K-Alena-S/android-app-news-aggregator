package com.example.newsaggregator.di

import com.example.newsaggregator.data.repository.NewsRepository
import com.example.newsaggregator.data.repository.NewsRepositoryImpl
import com.example.newsaggregator.data.rss.RssFeed
import com.example.newsaggregator.viewmodel.NewsListViewModel
import com.jakewharton.retrofit2.converter.kotlinx.serialization.asConverterFactory
import nl.adaptivity.xmlutil.serialization.XML
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module
import retrofit2.Retrofit
import okhttp3.MediaType.Companion.toMediaType

val appModule = module {

    single {
        Retrofit.Builder()
            .baseUrl("https://www.theguardian.com")
            .addConverterFactory(
                XML.asConverterFactory(
                    "application/xml; charset=UTF8".toMediaType()
                )
            ).build()

    }
    single<RssFeed> { get<Retrofit>().create(RssFeed::class.java) }
    single<NewsRepository> { NewsRepositoryImpl(get()) }
    viewModel { NewsListViewModel(get()) }
}