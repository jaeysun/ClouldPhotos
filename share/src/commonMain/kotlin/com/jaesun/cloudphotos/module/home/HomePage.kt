package com.jaesun.cloudphotos.module.home

import cn.lcsw.lcpay.saas.module.home.TitleValueTextView
import com.jaesun.cloudphotos.base.BasePager
import com.jaesun.cloudphotos.router.Router
import com.jaesun.cloudphotos.utils.colors.color_ff157cfa
import com.jaesun.cloudphotos.views.MyImage
import com.tencent.kuikly.core.annotations.Page
import com.tencent.kuikly.core.base.Color
import com.tencent.kuikly.core.base.ViewBuilder
import com.tencent.kuikly.core.base.ViewRef
import com.tencent.kuikly.core.base.attr.ImageUri
import com.tencent.kuikly.core.log.KLog
import com.tencent.kuikly.core.module.MemoryCacheModule
import com.tencent.kuikly.core.module.TurboDisplayModule
import com.tencent.kuikly.core.nvi.serialization.json.JSONObject
import com.tencent.kuikly.core.reactive.handler.observable
import com.tencent.kuikly.core.views.Image
import com.tencent.kuikly.core.views.PageList
import com.tencent.kuikly.core.views.PageListView
import com.tencent.kuikly.core.views.Text
import com.tencent.kuikly.core.views.View

@Page(Router.HOME_PAGE)
internal class HomePage : BasePager() {

    private lateinit var bannerRef: ViewRef<PageListView<*, *>>
    private var currentPageIndex by observable(0)
    // 当前 offset
    private var currentOffsetX by observable(0f)
    private var currentOffsetY by observable(0f)

    private var imgList by observable(mutableListOf<String>())

    override fun created() {
        super.created()

        imgList.add("http://img.daimg.com/uploads/allimg/251209/1-2512091Q230.jpg")
        imgList.add("http://img.daimg.com/uploads/allimg/251209/1-2512091KK9.jpg")
        imgList.add("http://img.daimg.com/uploads/allimg/251208/1-25120QZ002.jpg")



        // 方式1: 获取 Module,找不到会抛异常
        val cacheModule = acquireModule<MemoryCacheModule>(MemoryCacheModule.MODULE_NAME)


        // 方式2: 获取 Module,找不到返回 null
        val cacheModule1 = getModule<MemoryCacheModule>(MemoryCacheModule.MODULE_NAME)

        cacheModule.setObject("key", "value")

    }

    override fun body(): ViewBuilder {
        val ctx = this
        val statusHeight = pageData.statusBarHeight
        val screenWidth = pageData.pageViewWidth

        val count = imgList.count()
        return {
            View {
                attr {
                    flexDirectionColumn()  // 设置为垂直方向
                    margin(12f)
                    borderRadius(12f)
                    backgroundColor(color_ff157cfa)
                }
                Text {
                    attr {
                        height(50f)
                        color(Color.WHITE)
                        text("Banner")
                    }
                }
                Image {
                    attr {
                        width(screenWidth)
                        height(200f)
                        borderRadius(12f)
                        backgroundColor(Color.WHITE)
//                        src(ImageUri.pageAssets("splash_bg.png"))
                        src(ImageUri.commonAssets("sample.png"))
                    }
                }

                TitleValueTextView {
                    attr {
                        backgroundColor(Color.RED)
                        width(100f)
                        height(80f)
                        item = "今日售卖" to "1000万元"
                    }
                }

                MyImage {
                    attr {
                        size(200f, 200f)
                        src("http://img.daimg.com/uploads/allimg/251209/1-2512091Q230.jpg")
                    }
                    event {
                        loadSuccess { params ->
                            println("图片加载成功: ${params.src}")
                        }
                    }
                }
            }
        }
    }
}