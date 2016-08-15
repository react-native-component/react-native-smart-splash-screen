package com.reactnativecomponent.splashscreen;

import android.app.Activity;
import android.app.Dialog;
import android.content.Context;
import android.graphics.Color;
import android.os.Handler;
import android.view.Display;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewGroup.LayoutParams;
import android.view.WindowManager;
import android.view.animation.AlphaAnimation;
import android.view.animation.Animation;
import android.view.animation.AnimationSet;
import android.view.animation.ScaleAnimation;
import android.widget.ImageView;
import android.widget.LinearLayout;

import com.facebook.react.bridge.ReactApplicationContext;
import com.facebook.react.bridge.ReactContextBaseJavaModule;
import com.facebook.react.bridge.ReactMethod;

import java.lang.reflect.Array;


public class RCTSplashScreen extends ReactContextBaseJavaModule {

    private static Dialog dialog;
    private ImageView imageView;

    private Activity activity;

    public RCTSplashScreen(ReactApplicationContext reactContext, Activity activity) {
        super(reactContext);
        this.activity = activity;
        openSplashScreen();
    }

    protected Activity getActivity() {
        return activity;
    }

    @Override
    public String getName() {
        return "SplashScreen";
    }

    @ReactMethod
    public void close(final String animationType, final int duration, final int delay) {
        final Handler handler = new Handler();
        handler.postDelayed(new Runnable() {
            public void run() {
                removeSplashScreen(animationType, duration);
            }
        }, delay);
    }


    private void removeSplashScreen(final String animationType, final int duration) {
        getActivity().runOnUiThread(new Runnable() {
            public void run() {
                if (dialog != null && dialog.isShowing()) {
                    AnimationSet animationSet = new AnimationSet(true);

                    if("scale".equals(animationType)) {
                        AlphaAnimation fadeOut = new AlphaAnimation(1, 0);
                        fadeOut.setDuration(duration);
                        animationSet.addAnimation(fadeOut);

                        ScaleAnimation scale = new ScaleAnimation(1, 1.5f, 1, 1.5f, Animation.RELATIVE_TO_SELF, 0.5f, Animation.RELATIVE_TO_SELF, 0.65f);
                        scale.setDuration(duration);
                        animationSet.addAnimation(scale);
                    }
                    else {
                        AlphaAnimation fadeOut = new AlphaAnimation(1, 0);
                        fadeOut.setDuration(duration);
                        animationSet.addAnimation(fadeOut);
                    }

                    View view = ((ViewGroup)dialog.getWindow().getDecorView()).getChildAt(0);
                    view.startAnimation(animationSet);

                    animationSet.setAnimationListener(new Animation.AnimationListener() {
                        @Override
                        public void onAnimationStart(Animation animation) {
                        }
                        @Override
                        public void onAnimationRepeat(Animation animation) {
                        }
                        @Override
                        public void onAnimationEnd(Animation animation) {
                            dialog.dismiss();
                            dialog = null;
                            imageView = null;
                        }
                    });
                }
            }
        });
    }

    private int getImageId() {
        int drawableId = getActivity().getResources().getIdentifier("splash", "drawable", getActivity().getClass().getPackage().getName());
        if (drawableId == 0) {
            drawableId = getActivity().getResources().getIdentifier("splash", "drawable", getActivity().getPackageName());
        }
        return drawableId;
    }

    private void openSplashScreen() {
        final int drawableId = getImageId();
        if ((dialog != null && dialog.isShowing())||(drawableId == 0)) {
            return;
        }
        getActivity().runOnUiThread(new Runnable() {
            public void run() {
                Display display = getActivity().getWindowManager().getDefaultDisplay();
                Context context = getActivity();

                imageView = new ImageView(context);
                imageView.setImageResource(drawableId);
                LayoutParams layoutParams = new LinearLayout.LayoutParams(LayoutParams.MATCH_PARENT, LayoutParams.MATCH_PARENT);
                imageView.setLayoutParams(layoutParams);
                imageView.setMinimumHeight(display.getHeight());
                imageView.setMinimumWidth(display.getWidth());
                imageView.setBackgroundColor(Color.BLACK);
                imageView.setScaleType(ImageView.ScaleType.FIT_XY);

                dialog = new Dialog(context, android.R.style.Theme_Translucent_NoTitleBar);
                if ((getActivity().getWindow().getAttributes().flags & WindowManager.LayoutParams.FLAG_FULLSCREEN)
                        == WindowManager.LayoutParams.FLAG_FULLSCREEN) {
                    dialog.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                            WindowManager.LayoutParams.FLAG_FULLSCREEN);
                }
                dialog.setContentView(imageView);
//                dialog.setCancelable(false);
                dialog.show();
            }
        });
    }

}
