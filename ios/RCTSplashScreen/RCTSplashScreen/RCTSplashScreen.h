#import "RCTBridgeModule.h"
#import "RCTRootView.h"

@interface RCTSplashScreen : NSObject <RCTBridgeModule>

+ (void)open:(RCTRootView *)v;

@end
