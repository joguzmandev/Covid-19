package com.softhk.covid19.data.remote.di

import com.softhk.covid19.data.Repository
import com.softhk.covid19.data.remote.RemoteDataSource
import com.softhk.covid19.data.remote.RemoteDataSourceImpl
import com.softhk.covid19.data.remote.api.ApiCountryService
import com.softhk.covid19.data.remote.api.ApiCovidCountryService
import dagger.Module
import dagger.Provides
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Named
import javax.inject.Singleton

@Module
class RemoteModule {

    @Provides
    @Singleton
    @Named("retrofitCovidCountry")
    fun providesRetrofitCovidCountry() = Retrofit.Builder()
        .baseUrl("http://apidashboard.somee.com/api/")
        .addConverterFactory(GsonConverterFactory.create())
        .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
        .build()

    @Provides
    @Singleton
    @Named("retrofitCountry")
    fun providesRetrofitCountry() = Retrofit.Builder()
        .baseUrl("https://restcountries.eu/rest/v2/")
        .addConverterFactory(GsonConverterFactory.create())
        .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
        .build()

    @Provides
    @Singleton
    fun providesCovidCountryService(@Named("retrofitCovidCountry") retrofit: Retrofit): ApiCovidCountryService {
        return retrofit.create(ApiCovidCountryService::class.java)
    }

    @Provides
    @Singleton
    fun providesCountryService(@Named("retrofitCountry") retrofit: Retrofit): ApiCountryService {
        return retrofit.create(ApiCountryService::class.java)
    }

    @Provides
    @Singleton
    fun providesRemoteDataSource(apiCovidCountryService: ApiCovidCountryService,apiCountryService: ApiCountryService): RemoteDataSource =
        RemoteDataSourceImpl(apiCovidCountryService,apiCountryService)

    @Provides
    @Singleton
    fun providesRepository(remoteDataSource: RemoteDataSource) = Repository(remoteDataSource)

}