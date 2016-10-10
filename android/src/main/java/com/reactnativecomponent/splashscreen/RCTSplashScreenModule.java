package com.reactnativecomponent.splashscreen;

import android.os.Handler;
import android.support.annotation.Nullable;

import com.facebook.react.bridge.ReactApplicationContext;
import com.facebook.react.bridge.ReactContextBaseJavaModule;
import com.facebook.react.bridge.ReactMethod;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;


public class RCTSplashScreenModule extends ReactContextBaseJavaModule {

    public RCTSplashScreenModule(ReactApplicationContext reactContext) {
        super(reactContext);
    }

    @Override
    public String getName() {
        return "SplashScreen";
    }
    

    @ReactMethod
    public void close(final int animationType, final int duration, int delay) {
        if(animationType == RCTSplashScreen.UIAnimationNone) {
            delay = 0;
        }
        final Handler handler = new Handler();
        handler.postDelayed(new Runnable() {
            public void run() {
                RCTSplashScreen.removeSplashScreen(getCurrentActivity(), animationType, duration);
            }
        }, delay);
    }


    @Nullable
    @Override
    public Map<String, Object> getConstants() {
        return Collections.unmodifiableMap(new HashMap<String, Object>() {
            {
                put("animationType", getAnimationTypes());
            }
            private Map<String, Object> getAnimationTypes() {
                return Collections.unmodifiableMap(new HashMap<String, Object>() {
                    {
                        put("none", RCTSplashScreen.UIAnimationNone);
                        put("fade", RCTSplashScreen.UIAnimationFade);
                        put("scale", RCTSplashScreen.UIAnimationScale);
                    }
                });
            }
        });
    }

}
