declare module 'react-native-smart-splash-screen' {
  declare type UIAnimationNone = 0;
  declare type UIAnimationFade = 1;
  declare type UIAnimationScale = 2;

  declare type AnimationType =
    | UIAnimationNone
    | UIAnimationFade
    | UIAnimationScale;

  declare export type Options = {
    animationType: AnimationType,
    duration: number,
    delay: number,
  };

  declare export class SplashScreen {
    static close(options: Options): void;
  }
}
