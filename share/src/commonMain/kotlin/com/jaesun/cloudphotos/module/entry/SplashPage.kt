package com.jaesun.cloudphotos.module.entry

import com.jaesun.cloudphotos.base.BasePager
import com.jaesun.cloudphotos.base.replace
import com.jaesun.cloudphotos.router.Router
import com.tencent.kuikly.core.annotations.Page
import com.tencent.kuikly.core.base.Color
import com.tencent.kuikly.core.base.ViewBuilder
import com.tencent.kuikly.core.base.attr.ImageUri
import com.tencent.kuikly.core.timer.setTimeout
import com.tencent.kuikly.core.views.Image

@Page(Router.SPLASH_PAGE)
internal class SplashPage : BasePager() {

    override fun body(): ViewBuilder {
        val ctx = this
        setTimeout(1500) {
            ctx.replace(Router.HOME_PAGE)
        }
        // 初始化逻辑
        return {
            attr {
                flex(1f)
                allCenter()
            }

            Image {
                attr {
                    absolutePositionAllZero()
                    src(ImageUri.pageAssets("splash_bg.png"))
                    backgroundColor(Color.RED)
                }
            }
        }
    }
}