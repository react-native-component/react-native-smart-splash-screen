
#import "RCTSplashScreen.h"

static RCTRootView *rootView = nil;

@interface RCTSplashScreen()

@end

@implementation RCTSplashScreen

RCT_EXPORT_MODULE(SplashScreen)


+ (void)open:(RCTRootView *)v {
    [RCTSplashScreen open:v withImageNamed:@"splash"];
}


+ (void)open:(RCTRootView *)v withImageNamed:(NSString *)imageName {
    rootView = v;

    UIImageView *view = [[UIImageView alloc]initWithFrame:[UIScreen mainScreen].bounds];
    
    view.image = [UIImage imageNamed:imageName];
    
    [[NSNotificationCenter defaultCenter] removeObserver:rootView  name:RCTContentDidAppearNotification object:rootView];
    
    [rootView setLoadingView:view];
}




RCT_EXPORT_METHOD(close:(NSInteger *)animationType
                   duration:(NSInteger)duration
                   delay:(NSInteger)delay) {
    if (!rootView) {
        return;
    }

    if(animationType == UIAnimationNone) {
        rootView.loadingViewFadeDelay = 0;
        rootView.loadingViewFadeDuration = 0;
    }
    else {
        rootView.loadingViewFadeDelay = delay / 1000.0;
        rootView.loadingViewFadeDuration = duration / 1000.0;
    }
    dispatch_after(dispatch_time(DISPATCH_TIME_NOW, (int64_t)(rootView.loadingViewFadeDelay * NSEC_PER_SEC)),
                   dispatch_get_main_queue(),
                   ^{
                       [UIView animateWithDuration:rootView.loadingViewFadeDuration
                                        animations:^{
                                            if(animationType == UIAnimationScale) {
                                                rootView.loadingView.transform = CGAffineTransformMakeScale(1.5, 1.5);
                                                rootView.loadingView.alpha = 0;
                                            }
                                            else {
                                                rootView.loadingView.alpha = 0;
                                            }
                                        } completion:^(__unused BOOL finished) {
                                            [rootView.loadingView removeFromSuperview];
                                        }];
                   });
    
}

- (NSDictionary *)constantsToExport
{
    return @{
             @"animationType": @{
                     @"none": @(UIAnimationNone),
                     @"fade": @(UIAnimationFade),
                     @"scale": @(UIAnimationScale),
                 }
             };
}

@end
