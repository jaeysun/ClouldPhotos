package com.jaesun.cloudphotos.module.home

import com.jaesun.cloudphotos.base.BasePager
import com.jaesun.cloudphotos.router.Router
import com.tencent.kuikly.core.annotations.Page
import com.tencent.kuikly.core.base.ViewBuilder

@Page(Router.HOME_PAGE)
internal class HomePage : BasePager() {
    
    override fun body(): ViewBuilder {
        val ctx = this
        val statusHeight = pageData.statusBarHeight
        return {

        }
    }
}