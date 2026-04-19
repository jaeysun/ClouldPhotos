package com.jaesun.cloudphotos.module.home

import com.jaesun.cloudphotos.base.BasePager
import com.jaesun.cloudphotos.router.Router
import com.jaesun.cloudphotos.utils.colors.color_ff157cfa
import com.tencent.kuikly.core.annotations.Page
import com.tencent.kuikly.core.base.Color
import com.tencent.kuikly.core.base.ViewBuilder
import com.tencent.kuikly.core.base.ViewRef
import com.tencent.kuikly.core.log.KLog
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
                        color(Color.WHITE)
                        text("Banner")
                    }
                }
                PageList {
                    ref {
                        ctx.bannerRef = it
                    }

                    attr {
                        flex(1f)
                        pageDirection(true)  // 横向
                        pageItemWidth(screenWidth - 24f)  // 减去两边 margin
                        showScrollerIndicator(false)
                    }

                    event {
                        pageIndexDidChanged {
                            // 自动缓存
                            KLog.i("TAG", "【PageIndex缓存】index=${ctx.currentPageIndex}")
//                            getPager().acquireModule<TurboDisplayModule>(TurboDisplayModule.MODULE_NAME)
//                                .setCurrentUIAsFirstScreenForNextLaunch(extraContent)
                        }
                        scroll {
                            ctx.currentOffsetX = it.offsetX
                            ctx.currentOffsetY = it.offsetY
                        }
                    }

                    ctx.imgList.forEachIndexed { index, string ->
                        View {
                            attr {
                                width(screenWidth - 24f)
                                height(200f)
                            }
                            Image {
                                attr {
                                    height(200f)
                                    src(string)
                                    resizeContain()
                                }
                            }
                            Text {
                                attr {
                                    fontSize(32f)
                                    fontWeightBold()
                                    color(Color.WHITE)
                                    text("Banner ${index + 1}")
                                }
                            }
                        }
                    }
                }
            }
        }
    }
}