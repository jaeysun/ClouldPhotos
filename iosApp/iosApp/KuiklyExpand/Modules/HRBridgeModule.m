#import "HRBridgeModule.h"

#import "KuiklyRenderViewController.h"
#import <OpenKuiklyIOSRender/NSObject+KR.h>

#define REQ_PARAM_KEY @"reqParam"
#define CMD_KEY @"cmd"
#define FROM_HIPPY_RENDER @"from_hippy_render"
// 扩展桥接接口
/*
 * @brief Native暴露接口到kotlin侧，提供kotlin侧调用native能力
 */

@implementation HRBridgeModule

@synthesize hr_rootView;

- (void)copyToPasteboard:(NSDictionary *)args {
    NSDictionary *params = [args[KR_PARAM_KEY] hr_stringToDictionary];
    NSString *content = params[@"content"];
    UIPasteboard *pasteboard = [UIPasteboard generalPasteboard];
    pasteboard.string = content;
}

- (void)log:(NSDictionary *)args {
    NSDictionary *params = [args[KR_PARAM_KEY] hr_stringToDictionary];
    NSString *content = params[@"content"];
    NSLog(@"KuiklyRender:%@", content);
}

- (void)replacePage:(NSDictionary *)args {
    NSDictionary *params = [args[KR_PARAM_KEY] hr_stringToDictionary];
    NSString *page = params[@"page"];
    NSLog(@"KuiklyRender:%@", page);
    UINavigationController *nav = [self.hr_rootView.kr_viewController navigationController];
    NSMutableArray<UIViewController *> *vcs = [[nav viewControllers] mutableCopy];
    KuiklyRenderViewController *renderViewController = [[KuiklyRenderViewController alloc] initWithPageName:page pageData:[NSDictionary new]];
    [vcs replaceObjectAtIndex:vcs.count-1 withObject:renderViewController];
    [nav setViewControllers:vcs];
}


@end