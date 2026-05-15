package com.jaesun.cloudphotos.views

import android.content.Context
import android.graphics.Bitmap
import android.graphics.drawable.BitmapDrawable
import android.graphics.drawable.Drawable
import android.util.Log
import com.squareup.picasso.Picasso
import com.squareup.picasso.Target
import com.tencent.kuikly.core.render.android.export.IKuiklyRenderViewExport
import com.tencent.kuikly.core.render.android.export.KuiklyRenderCallback

// 继承原生 View 并实现 IKuiklyRenderViewExport
class HRImageView(context: Context) : androidx.appcompat.widget.AppCompatImageView(context), IKuiklyRenderViewExport {
    private var src = ""
    private var loadSuccessCallback: KuiklyRenderCallback? = null

    // 处理属性和事件
    override fun setProp(propKey: String, propValue: Any): Boolean {
        return when (propKey) {
            "src" -> {
                setSrc(propValue as String)
                true
            }
            "loadSuccess" -> {
                loadSuccessCallback = propValue as KuiklyRenderCallback
                true
            }
            else -> super.setProp(propKey, propValue)
        }
    }

    // 处理组件方法调用
    override fun call(method: String, params: String?, callback: KuiklyRenderCallback?): Any? {
        return when (method) {
            "test" -> {
                Log.d("HRImageView", "test method called: $params")
                null
            }
            else -> super.call(method, params, callback)
        }
    }

    private fun setSrc(url: String) {
        if (src == url) return
        src = url

        // 使用 Picasso 加载图片
        Picasso.get().load(src).into(object : Target {
            override fun onBitmapLoaded(bitmap: Bitmap, from: Picasso.LoadedFrom) {
                setImageDrawable(BitmapDrawable(resources, bitmap))
                // 触发加载成功事件
                loadSuccessCallback?.invoke(mapOf("src" to src))
            }

            override fun onBitmapFailed(e: Exception, errorDrawable: Drawable?) {
                Log.e("HRImageView", "Image load failed: $src", e)
            }

            override fun onPrepareLoad(placeHolderDrawable: Drawable?) {
                // 可在此设置占位图
            }
        })
    }
}