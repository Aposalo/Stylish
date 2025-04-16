package gr.aposalo.stylish.di

import android.content.Context
import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import gr.aposalo.stylish.App
import gr.aposalo.stylish.data.network.AccessInterceptor
import gr.aposalo.stylish.data.network.ApiCalls
import gr.aposalo.stylish.data.preferences.PreferencesImpl
import gr.aposalo.stylish.data.repositories.AuthRepositoryImpl
import gr.aposalo.stylish.data.repositories.ProductRepositoryImpl
import gr.aposalo.stylish.domain.preferences.Preferences
import gr.aposalo.stylish.domain.repositories.AuthRepository
import gr.aposalo.stylish.domain.repositories.ProductRepository
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import javax.inject.Provider
import javax.inject.Qualifier
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    private val moshi: Moshi = Moshi.Builder()
        .add(KotlinJsonAdapterFactory())
        .build()

    @Target(AnnotationTarget.VALUE_PARAMETER, AnnotationTarget.FUNCTION, AnnotationTarget.TYPE)
    @Qualifier
    @Retention(AnnotationRetention.BINARY)
    annotation class ApiImplNoInterceptor

    @ApiImplNoInterceptor
    @Provides
    @Singleton
    fun provideApiNoInterceptor(): ApiCalls {
        return Retrofit.Builder().baseUrl(App.databaseURL)
            .addConverterFactory(MoshiConverterFactory.create(moshi)).client(
                OkHttpClient.Builder()
                    .addInterceptor(HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.BODY))
                    .build()
            ).build().create(ApiCalls::class.java)
    }

    @Target(AnnotationTarget.VALUE_PARAMETER, AnnotationTarget.FUNCTION, AnnotationTarget.TYPE)
    @Qualifier
    @Retention(AnnotationRetention.BINARY)
    annotation class ApiImplWithInterceptor

    @ApiImplWithInterceptor
    @Provides
    @Singleton
    fun provideApi(
        preferences: Preferences
    ): ApiCalls {

        return Retrofit.Builder().baseUrl(App.databaseURL)
            .addConverterFactory(MoshiConverterFactory.create(moshi)).client(
                OkHttpClient.Builder()
                    .addInterceptor(HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.BODY))
                    .addInterceptor(AccessInterceptor(preferences))
                    .build()
            ).build().create(ApiCalls::class.java)
    }


    @Provides
    fun provideContext(@ApplicationContext context: Context): Context = context

    @Provides
    @Singleton
    fun providePreferences(
        @ApplicationContext context: Context
    ): Preferences {
        return PreferencesImpl(context)
    }

    @Provides
    @Singleton
    fun provideLazyAuthRepository(
        provider: Provider<AuthRepository>
    ): Lazy<@JvmSuppressWildcards AuthRepository> {
        return lazy { provider.get() }
    }

    @Provides
    @Singleton
    fun provideLazyProductRepository(
        provider: Provider<ProductRepository>
    ): Lazy<@JvmSuppressWildcards ProductRepository> {
        return lazy { provider.get() }
    }

    @Provides
    @Singleton
    fun provideAuthRepository(
        @ApiImplNoInterceptor apiCalls: ApiCalls,
        preferences: Preferences
    ): AuthRepository {
        return AuthRepositoryImpl(
            apiCalls, preferences
        )
    }


    @Provides
    @Singleton
    fun provideProductRepository(
        @ApiImplWithInterceptor apiCalls: ApiCalls,
    ): ProductRepository {
        return ProductRepositoryImpl(
            apiCalls
        )
    }
}