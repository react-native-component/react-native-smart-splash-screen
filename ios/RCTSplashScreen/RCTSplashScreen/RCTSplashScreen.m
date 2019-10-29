
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
    UIImageView *view = [[UIImageView alloc]initWithFrame:[UIScreen mainScreen].bounds];

    view.image = [UIImage imageNamed:imageName];
    view.contentMode = UIViewContentModeScaleAspectFill;

    [self open:v withView:view];
}

+ (void)open:(RCTRootView *)v withXibNamed:(NSString *)xibName {
    NSArray *nibContents = [[NSBundle mainBundle] loadNibNamed:xibName owner:nil options:nil];

    UIView *view = [nibContents lastObject];
    view.frame = rootView.bounds;

    [self open:v withView:view];
}

+ (void)open:(RCTRootView *)v withView:(UIView *)view {
  rootView = v;

  [[NSNotificationCenter defaultCenter] removeObserver:rootView  name:RCTContentDidAppearNotification object:rootView];

  [rootView setLoadingView:view];
}

RCT_EXPORT_METHOD(close:(NSDictionary *)options) {
    if (!rootView) {
        return;
    }
    
    int animationType = UIAnimationNone;
    int duration = 0;
    int delay = 0;
    
    if(options != nil) {
        
        NSArray *keys = [options allKeys];
        
        if([keys containsObject:@"animationType"]) {
            animationType = [[options objectForKey:@"animationType"] intValue];
        }
        if([keys containsObject:@"duration"]) {
            duration = [[options objectForKey:@"duration"] intValue];
        }
        if([keys containsObject:@"delay"]) {
            delay = [[options objectForKey:@"delay"] intValue];
        }
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
