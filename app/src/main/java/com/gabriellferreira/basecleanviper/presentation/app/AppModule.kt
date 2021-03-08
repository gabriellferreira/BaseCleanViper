package com.gabriellferreira.basecleanviper.presentation.app

import android.content.Context
import android.content.SharedPreferences
import android.content.res.Resources
import androidx.security.crypto.EncryptedSharedPreferences
import androidx.security.crypto.MasterKey
import com.gabriellferreira.basecleanviper.presentation.app.coroutines.AppScope
import com.gabriellferreira.basecleanviper.presentation.app.coroutines.DefaultDispatcher
import com.gabriellferreira.basecleanviper.presentation.app.scope.ApplicationScope
import dagger.Module
import dagger.Provides
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Job
import kotlinx.coroutines.SupervisorJob
import javax.inject.Named

@Module
class AppModule(private val context: Context) {

    @Provides
    fun provideAppContext(): Context = context

    @Provides
    fun provideResources(context: Context): Resources = context.resources

    @Provides
    fun provideJob(): Job = Job()

    @Provides
    fun provideMasterKey(context: Context): MasterKey =
        MasterKey.Builder(context, MasterKey.DEFAULT_MASTER_KEY_ALIAS)
            .setKeyScheme(MasterKey.KeyScheme.AES256_GCM)
            .build()

    @Provides
    @ApplicationScope
    @Named(SESSION_SHARED_PREFERENCES)
    fun provideSessionSharedPreferences(
        context: Context,
        masterKey: MasterKey
    ): SharedPreferences =
        EncryptedSharedPreferences.create(
            context,
            SESSION_SHARED_PREFERENCES,
            masterKey,
            EncryptedSharedPreferences.PrefKeyEncryptionScheme.AES256_SIV,
            EncryptedSharedPreferences.PrefValueEncryptionScheme.AES256_GCM
        )

    @ApplicationScope
    @AppScope
    @Provides
    fun providesApplicationScope(
        @DefaultDispatcher defaultDispatcher: CoroutineDispatcher
    ): CoroutineScope = CoroutineScope(SupervisorJob() + defaultDispatcher)

    companion object {
        const val SESSION_SHARED_PREFERENCES = "SESSION_SHARED_PREFERENCES"
    }
}