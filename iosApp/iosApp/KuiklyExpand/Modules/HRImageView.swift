//
//  HRImageView.swift
//  iosApp
//
//  Created by lcsj on 2026/5/13.
//  Copyright © 2026 orgName. All rights reserved.
//

import Foundation
import OpenKuiklyIOSRender
import SDWebImage
// 类名必须与 Kuikly 侧 viewName() 返回的名字一致
//@objcMembers
@objc(HRImageView)
class HRImageView: UIImageView, KuiklyRenderViewExportProtocol {
    
    private var src: String = ""
    private var loadSuccessCallback: KuiklyRenderCallback?
    
    func hrv_setProp(withKey propKey: String, propValue: Any) {
        switch propKey {
        case "src":
            if let url = propValue as? String {
                setSrc(url: url)
            }
            print("set src: \(propValue)")
        case "loadSuccess":
            loadSuccessCallback = propValue as? KuiklyRenderCallback
        
        default:
            print("")
        }

    }
    
    func hrv_call(withMethod method: String, params: String?, callback: KuiklyRenderCallback? = nil) {
        switch method {
        case "test":
            print("test method called: \(params ?? "")")
        
        default:
            print("")
        }
    }
    
    private func setSrc(url: String) {
        guard src != url else { return }
        src = url
        backgroundColor = .black
        
        // 使用 SDWebImage 异步加载图片
        if let imageURL = URL(string: url) {
            self.sd_setImage(with: imageURL) { [weak self] image, error, cacheType, imageURL in
                if error == nil, image != nil {
                    // 触发加载成功事件
                    self?.loadSuccessCallback?(["src": self?.src ?? ""])
                }
            }
        }
    }
}
