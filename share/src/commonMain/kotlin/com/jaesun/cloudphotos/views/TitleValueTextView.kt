package cn.lcsw.lcpay.saas.module.home
import com.tencent.kuikly.core.base.ComposeAttr
import com.tencent.kuikly.core.base.ComposeEvent
import com.tencent.kuikly.core.base.ComposeView
import com.tencent.kuikly.core.base.ViewBuilder
import com.tencent.kuikly.core.base.ViewContainer
import com.tencent.kuikly.core.reactive.handler.observable
import com.tencent.kuikly.core.views.Text

internal class TitleValueView : ComposeView<HomeDataViewAttr, HomeDataViewEvent>() {

    override fun createEvent(): HomeDataViewEvent {
        return HomeDataViewEvent()
    }

    override fun createAttr(): HomeDataViewAttr {
        return HomeDataViewAttr()
    }

    override fun body(): ViewBuilder {
        val ctx = this
        return {
            attr {
                alignSelfStretch()
                flexDirectionColumn()
                justifyContentSpaceBetween()
                alignItemsFlexStart()
            }

            Text {
                attr {
                    absolutePosition()
                    left(0f)
                    top(10f)
                    right(0f)
                    text(ctx.attr.item.first)
                    lines(2)
                    fontSize(12f)
                    color(0xff666666)
                }
            }

            Text {
                attr {
                    absolutePosition()
                    left(0f)
                    top(24f)
                    right(0f)
                    fontWeightBold()
                    text((if (ctx.attr.isMoneyVisible) ctx.attr.item.second else "****"))
                    lines(1)
                    fontSize(22f)
                    color(0xff333333)
                    marginTop(4f)
                }
            }
        }
    }
}


internal class HomeDataViewAttr : ComposeAttr() {
    lateinit var item: Pair<String, String>
    var isMoneyVisible by observable(true)
}

internal class HomeDataViewEvent : ComposeEvent()

internal fun ViewContainer<*, *>.TitleValueTextView(init: TitleValueView.() -> Unit) {
    addChild(TitleValueView(), init)
}