package se.gorymoon.hdopen

import android.app.Application
import android.content.Context
import android.os.Build.VERSION.SDK_INT
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.preferencesDataStore
import coil.ImageLoader
import coil.ImageLoaderFactory
import coil.decode.GifDecoder
import coil.decode.ImageDecoderDecoder
import se.gorymoon.hdopen.services.SettingsRepository
import se.gorymoon.hdopen.ui.models.SettingsState.setupPersistentSettings

private val Context.datastore: DataStore<Preferences> by preferencesDataStore("settings")

class AppConfig: Application(), ImageLoaderFactory {
    lateinit var settingsRepository: SettingsRepository

    override fun onCreate() {
        super.onCreate()
        settingsRepository = SettingsRepository(datastore)
        setupPersistentSettings()
    }

    override fun newImageLoader(): ImageLoader
    = ImageLoader.Builder(applicationContext)
        .components {
            if(SDK_INT >= 28){
                add(ImageDecoderDecoder.Factory())
            } else{
                add(GifDecoder.Factory())
            }
        }.crossfade(true)
        .build()

}