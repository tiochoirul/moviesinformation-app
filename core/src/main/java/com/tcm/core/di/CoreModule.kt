package com.tcm.core.di

import androidx.room.Room
import com.tcm.core.BuildConfig
import com.tcm.core.data.PopularRepository
import com.tcm.core.data.source.local.LocalDataSource
import com.tcm.core.data.source.local.room.PopularDatabase
import com.tcm.core.data.source.remote.RemoteDataSource
import com.tcm.core.data.source.remote.network.ApiService
import com.tcm.core.domain.repository.IPopularRepository
import com.tcm.core.utils.AppExecutors
import net.sqlcipher.database.SQLiteDatabase
import net.sqlcipher.database.SupportFactory
import okhttp3.CertificatePinner
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import org.koin.android.ext.koin.androidContext
import org.koin.dsl.module
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit


val databaseModule = module {
    factory { get<PopularDatabase>().popularDao() }
    single {
        val passphrase: ByteArray = SQLiteDatabase.getBytes("tcm".toCharArray())
        val factory = SupportFactory(passphrase)
        Room.databaseBuilder(
            androidContext(),
            PopularDatabase::class.java, "popular.db"
        ).fallbackToDestructiveMigration()
            .openHelperFactory(factory)
            .build()
    }
}

val networkModule = module {
    single {
        val hostname = BuildConfig.HOSTNAME
        val certificatePinner = CertificatePinner.Builder()
            .add(hostname, "sha256/jBBZifCHIJ6boQ2YT7MoYiyERNzoDGY6VZuwJWcr1So=")
            .build()

        val loggingInterceptor = if(BuildConfig.DEBUG) {
            HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.BODY)
        } else {
            HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.NONE)
        }
        OkHttpClient.Builder()
            .addInterceptor(loggingInterceptor)
            .connectTimeout(120, TimeUnit.SECONDS)
            .readTimeout(120, TimeUnit.SECONDS)
            .certificatePinner(certificatePinner)
            .build()
    }
    single {
        val retrofit = Retrofit.Builder()
            .baseUrl(BuildConfig.BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .client(get())
            .build()
        retrofit.create(ApiService::class.java)
    }
}

val repositoryModule = module {
    single { LocalDataSource(get()) }
    single { RemoteDataSource(get()) }
    factory { AppExecutors() }
    single<IPopularRepository> { PopularRepository(get(), get(), get()) }
}