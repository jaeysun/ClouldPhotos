package com.jaesun.cloudphotos.views

import com.tencent.kuikly.core.base.Attr
import com.tencent.kuikly.core.base.DeclarativeBaseView
import com.tencent.kuikly.core.base.ViewContainer
import com.tencent.kuikly.core.base.event.Event
import com.tencent.kuikly.core.nvi.serialization.json.JSONObject

// 1. 定义组件属性
class MyImageAttr : Attr() {
    /**
     * 设置图片源
     */
    fun src(src: String): MyImageAttr {
        "src" with src
        return this
    }
}

// 2. 定义组件事件 Event
class MyImageEvent : Event() {
    /**
     * 图片加载成功回调
     */
    fun loadSuccess(handler: (LoadSuccessParams) -> Unit) {
        register(LOAD_SUCCESS) {
            handler(LoadSuccessParams.decode(it))
        }
    }

    companion object {
        const val LOAD_SUCCESS = "loadSuccess"
    }
}

// 事件参数数据类
data class LoadSuccessParams(val src: String) {
    companion object {
        fun decode(params: Any?): LoadSuccessParams {
            val tempParams = params as? JSONObject ?: JSONObject()
            val src = tempParams.optString("src", "")
            return LoadSuccessParams(src)
        }
    }
}

// 3. 定义组件类
class MyImageView : DeclarativeBaseView<MyImageAttr, MyImageEvent>() {

    override fun createAttr(): MyImageAttr {
        return MyImageAttr()
    }

    override fun createEvent(): MyImageEvent {
        return MyImageEvent()
    }

    // 对应原生组件的名字
    override fun viewName(): String {
        return "HRImageView"
    }

    // 组件方法(调用原生侧实现)
    fun test() {
        performTaskWhenRenderViewDidLoad {
            renderView?.callMethod("test", "params")
        }
    }
}

// 4. 声明式 API(供业务方使用)
fun ViewContainer<*, *>.MyImage(init: MyImageView.() -> Unit) {
    addChild(MyImageView(), init)
}