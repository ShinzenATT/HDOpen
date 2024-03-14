package se.gorymoon.hdopen

import android.app.Application
import android.os.Build.VERSION.SDK_INT
import coil.ImageLoader
import coil.ImageLoaderFactory
import coil.decode.GifDecoder
import coil.decode.ImageDecoderDecoder

class AppConfig: Application(), ImageLoaderFactory {
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