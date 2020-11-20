package com.jianyushanshe.wildeye.util

import android.content.Context
import com.bumptech.glide.Glide
import com.bumptech.glide.Registry
import com.bumptech.glide.annotation.GlideModule
import com.bumptech.glide.integration.okhttp3.OkHttpUrlLoader
import com.bumptech.glide.load.model.GlideUrl
import com.bumptech.glide.module.AppGlideModule
import com.jianyushanshe.wildeye.logic.network.ServiceCreator
import java.io.InputStream

/**
 * Author:wangjianming
 * Time:2020/11/20 16:35
 * Description:WildEyeGglideModule
 */
@GlideModule
class WildEyeGglideModule : AppGlideModule() {
    override fun isManifestParsingEnabled(): Boolean {
        return false
    }

    override fun registerComponents(context: Context, glide: Glide, registry: Registry) {
        super.registerComponents(context, glide, registry)
        registry.replace(
            GlideUrl::class.java,
            InputStream::class.java,
            OkHttpUrlLoader.Factory(ServiceCreator.httpClient)
        )
    }
}