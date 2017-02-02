package com.reactnativecomponent.splashscreen;

import android.os.Handler;
import android.support.annotation.Nullable;

import com.facebook.react.bridge.ReactApplicationContext;
import com.facebook.react.bridge.ReactContextBaseJavaModule;
import com.facebook.react.bridge.ReactMethod;
import com.facebook.react.bridge.ReadableMap;

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
    public void close(ReadableMap options) {

        int animationType = RCTSplashScreen.UIAnimationNone;
        int duration = 0;
        int delay = 0;

        if(options != null) {
            if(options.hasKey("animationType")) {
                animationType = options.getInt("animationType");
            }
            if(options.hasKey("duration")) {
                duration = options.getInt("duration");
            }
            if(options.hasKey("delay")) {
                delay = options.getInt("delay");
            }
        }

        if(animationType == RCTSplashScreen.UIAnimationNone) {
            delay = 0;
        }

        final int final_animationType = animationType;
        final int final_duration = duration;

        final Handler handler = new Handler();
        handler.postDelayed(new Runnable() {
            public void run() {
                RCTSplashScreen.removeSplashScreen(getCurrentActivity(), final_animationType, final_duration);
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
