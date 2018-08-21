declare module 'react-native-smart-splash-screen' {
  type UIAnimationNone = 0;
  type UIAnimationFade = 1;
  type UIAnimationScale = 2;

  type AnimationType =
    | UIAnimationNone
    | UIAnimationFade
    | UIAnimationScale;

  export interface Options {
    animationType: AnimationType,
    duration: number,
    delay: number,
  };

  export class SplashScreen {
    static close(options: Options): void;
  }
}
