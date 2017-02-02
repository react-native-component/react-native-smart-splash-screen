# react-native-smart-splash-screen

[![npm](https://img.shields.io/npm/v/react-native-smart-splash-screen.svg)](https://www.npmjs.com/package/react-native-smart-splash-screen)
[![npm](https://img.shields.io/npm/dm/react-native-smart-splash-screen.svg)](https://www.npmjs.com/package/react-native-smart-splash-screen)
[![npm](https://img.shields.io/npm/dt/react-native-smart-splash-screen.svg)](https://www.npmjs.com/package/react-native-smart-splash-screen)
[![npm](https://img.shields.io/npm/l/react-native-smart-splash-screen.svg)](https://github.com/react-native-component/react-native-smart-splash-screen/blob/master/LICENSE)

A smart splash screen for React Native apps, written in JS, oc and java for cross-platform support.
It works on iOS and Android.

## Preview

![react-native-smart-splash-screen-ios-preview][1]
![react-native-smart-splash-screen-android-preview][2]

## Installation

```
npm install react-native-smart-splash-screen --save
```

## Notice

It can only be used greater-than-equal react-native 0.4.0 for ios, if you want to use the package less-than react-native 0.4.0, use `npm install react-native-smart-splash-screen@untilRN0.40 --save`

## Installation (iOS)

* Drag RCTSplashScreen.xcodeproj to your project on Xcode.

* Click on your main project file (the one that represents the .xcodeproj) select Build Phases and drag libRCTSplashScreen.a from the Products folder inside the RCTSplashScreen.xcodeproj.

* Look for Header Search Paths and make sure it contains $(SRCROOT)/../../../react-native/React as recursive.

* In your project, Look for Header Search Paths and make sure it contains $(SRCROOT)/../node_modules/react-native-smart-splash-screen/ios/RCTSplashScreen/RCTSplashScreen

* delete your project's LaunchScreen.xib

* Drag SplashScreenResource folder to your project *if you want change image, replace splash.png or add a image with your custom name*

* In AppDelegate.m

```

...
#import "RCTSplashScreen.h" //import interface
...
RCTRootView *rootView = [[RCTRootView alloc] initWithBundleURL:jsCodeLocation
                                                  moduleName:@"ReactNativeComponents"
                                           initialProperties:nil
                                               launchOptions:launchOptions];

//[RCTSplashScreen open:rootView];
[RCTSplashScreen open:rootView withImageNamed:@"splash"]; // activate splashscreen, imagename from LaunchScreen.xib

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
project(':react-native-smart-splashscreen').projectDir = new File(rootProject.projectDir, '../node_modules/react-native-smart-splash-screen/android')
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

* Add your own `drawable/splash.png` to `android/app/src/main/res/`, it is recommended to add `drawable-?dpi` folders that you need.

* in MainApplication.java

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
        new RCTSplashScreenPackage()    //register Module
    );
}
...

```

* in MainActivity.java
```
...
import com.reactnativecomponent.splashscreen.RCTSplashScreen;    //import RCTSplashScreen
...
@Override
protected void onCreate(Bundle savedInstanceState) {
    RCTSplashScreen.openSplashScreen(this);   //open splashscreen
    //RCTSplashScreen.openSplashScreen(this, true, ImageView.ScaleType.FIT_XY);   //open splashscreen fullscreen
    super.onCreate(savedInstanceState);
}
```

* In `android/app/**/styles.xml`

```
...
<!-- Base application theme. -->
<style name="AppTheme" parent="Theme.AppCompat.Light.NoActionBar">
    <!-- Customize your theme here. -->
    <item name="android:windowIsTranslucent">true</item>
</style>
...
```

## Full Demo

see [ReactNativeComponentDemos][0]


## Usage

```js
...
import SplashScreen from 'react-native-smart-splash-screen'
...
componentDidMount () {
     //SplashScreen.close(SplashScreen.animationType.scale, 850, 500)
     SplashScreen.close({
        animationType: SplashScreen.animationType.scale,
        duration: 850,
        delay: 500,
     })
}
...

```

## Method

* close(animationType, duration, delay)
  close splash screen with custom animation

  * animationType: determine the type of animation. enum(animationType.none, animationType.fade, animationType.scale)
  * duration: determine the duration of animation
  * delay: determine the delay of animation


[0]: https://github.com/cyqresig/ReactNativeComponentDemos
[1]: http://cyqresig.github.io/img/react-native-smart-splash-screen-preview-ios-v2.3.0.gif
[2]: http://cyqresig.github.io/img/react-native-smart-splash-screen-preview-android-v2.3.0.gif
