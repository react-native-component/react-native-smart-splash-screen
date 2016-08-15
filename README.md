# react-native-smart-splash-screen
A smart splash screen for React Native apps, written in JS, oc and java for cross-platform support.
It works on iOS and Android.

This component is compatible with React Native 0.25 and newer.

## Preview

![react-native-smart-splash-screen-ios-preview][1]
![react-native-smart-splash-screen-android-preview][2]

## Installation

```
npm install react-native-smart-splash-screen --save
```

## Installation (iOS)

* Drag RCTSplashScreen.xcodeproj to your project on Xcode.

* Click on your main project file (the one that represents the .xcodeproj) select Build Phases and drag libRCTSplashScreen.a from the Products folder inside the RCTSplashScreen.xcodeproj.

* Look for Header Search Paths and make sure it contains $(SRCROOT)/../../../react-native/React as recursive.

* In your project, Look for Header Search Paths and make sure it contains $(SRCROOT)/../node_modules/react-native-splash-screen/ios/RCTSplashScreen/RCTSplashScreen

* delete your project's LaunchScreen.xib

* Dray SplashScreenResource to your project *if you want change image, replace splash.png*

* In AppDelegate.m

```

...
#import "RCTSplashScreen.h" //import interface
...
RCTRootView *rootView = [[RCTRootView alloc] initWithBundleURL:jsCodeLocation
                                                  moduleName:@"ReactNativeComponents"
                                           initialProperties:nil
                                               launchOptions:launchOptions];

[RCTSplashScreen open:rootView]; //activate splashscreen

rootView.backgroundColor = [[UIColor alloc] initWithRed:1.0f green:1.0f blue:1.0f alpha:1];

self.window = [[UIWindow alloc] initWithFrame:[UIScreen mainScreen].bounds];
UIViewController *rootViewController = [UIViewController new];
rootViewController.view = rootView;
self.window.rootViewController = rootViewController;
[self.window makeKeyAndVisible];
return YES;

```


## Installation (Android)

* In `android/settings.gradle`

```
...
include ':react-native-smart-splashscreen'
project(':react-native-smart-splashscreen').projectDir = new File(rootProject.projectDir, '../react-native-smart-splash-screen/android')
```

* In `android/app/build.gradle`

```
...
dependencies {
    ...
    // From node_modules
    compile project(':react-native-smart-splashscreen')
}
```

* if you want change image, replace res/drawable/splash.png

* In MainActivity.java

```
...
import com.reactnativecomponent.splashscreen.RCTSplashScreenPackage;    //import package
...
/**
 * A list of packages used by the app. If the app uses additional views
 * or modules besides the default ones, add more packages here.
 */
@Override
protected List<ReactPackage> getPackages() {
    return Arrays.<ReactPackage>asList(
        new MainReactPackage(),
        new RCTSplashScreenPackage(this)    //register Module
    );
}
...

```

## Usage

```js
...
import SplashScreen from 'react-native-smart-splash-screen'
...
componentDidMount () {
     SplashScreen.close('scale', 850, 500)
}
...

```

## Method

* close(animationType, duration, delay)
  close splash screen with custom animation

  * animationType: determine the type of animation. enum('opacity', 'scale')
  * duration: determine the duration of animation
  * delay: determine the delay of animation



[1]: http://cyqresig.github.io/img/react-native-smart-splash-screen-preview-ios-v1.0.0.gif
[2]: http://cyqresig.github.io/img/react-native-smart-splash-screen-preview-android-v1.0.0.gif