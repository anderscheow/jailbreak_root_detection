# jailbreak_root_detection_plus

[![pub package](https://img.shields.io/pub/v/jailbreak_root_detection_plus.svg)](https://pub.dev/packages/jailbreak_root_detection_plus)

Uses [RootBeer](https://github.com/scottyab/rootbeer) + DetectFrida for Android root detection and [IOSSecuritySuite (~> 2.3.0)](https://github.com/securing/IOSSecuritySuite/tree/2.3.0) for iOS jailbreak detection.

A fork of [jailbreak_root_detection](https://github.com/w3connext/jailbreak_root_detection), updated for
Android Gradle Plugin 9 and Flutter 3.44+. See [Migrating from jailbreak_root_detection](#migrating-from-jailbreak_root_detection).

## Getting started

In your flutter project add the dependency:

```yaml
jailbreak_root_detection_plus: "^1.0.0"
```

## Usage

### Important: Test on Real Devices Only

This package must be tested on a real device (physical device).

Running on an emulator or simulator may cause false positives — for example, the detection may incorrectly report that the device is jailbroken or rooted.

### Android

```dart
import 'package:jailbreak_root_detection_plus/jailbreak_root_detection_plus.dart';

final isNotTrust = await JailbreakRootDetection.instance.isNotTrust;
final isJailBroken = await JailbreakRootDetection.instance.isJailBroken;
final isRealDevice = await JailbreakRootDetection.instance.isRealDevice;
final isOnExternalStorage = await JailbreakRootDetection.instance.isOnExternalStorage;
final checkForIssues = await JailbreakRootDetection.instance.checkForIssues;
final isDevMode = await JailbreakRootDetection.instance.isDevMode;
```

### Android build requirements

Requires Flutter 3.44 or newer, and `minSdk` 21 or higher.

This plugin has migrated to [built-in Kotlin](https://developer.android.com/build/migrate-to-built-in-kotlin):
it no longer applies the Kotlin Gradle Plugin itself, which is what Android Gradle Plugin 9.0+ requires.

| Your Android setup | What you need to do |
| --- | --- |
| AGP 8.x | Nothing. Flutter applies the Kotlin Gradle Plugin for this plugin automatically. |
| AGP 9+ on Flutter 3.44–3.46 | Set `android.builtInKotlin=false` and `android.newDsl=false` in `android/gradle.properties`. Flutter's own project template already does this. |
| AGP 9+ on Flutter 3.47+ | Nothing. Both `android.builtInKotlin=true` and `false` work. |

If you see *"The 'org.jetbrains.kotlin.android' plugin is no longer required for Kotlin support since AGP 9.0"*,
you are on AGP 9 with Flutter 3.44–3.46 and `android.builtInKotlin=true`; either set it to `false` or upgrade to Flutter 3.47+.

### iOS

- Update `Info.plist`

```xml
<key>LSApplicationQueriesSchemes</key>
<array>
    <string>undecimus</string>
    <string>sileo</string>
    <string>zbra</string>
    <string>filza</string>
    <string>activator</string>
    <string>cydia</string>
</array>
```

```dart
import 'package:jailbreak_root_detection_plus/jailbreak_root_detection_plus.dart';

final isNotTrust = await JailbreakRootDetection.instance.isNotTrust;
final isJailBroken = await JailbreakRootDetection.instance.isJailBroken;
final isRealDevice = await JailbreakRootDetection.instance.isRealDevice;
final checkForIssues = await JailbreakRootDetection.instance.checkForIssues;

final bundleId = 'my-bundle-id'; // Ex: final bundleId = 'com.w3conext.jailbreakRootDetectionPlusExample'
final isTampered = await JailbreakRootDetection.instance.isTampered(bundleId);
```

### iOS build requirements

Requires iOS 13.0 or higher. Both Swift Package Manager and CocoaPods are supported — no extra
configuration is needed for either.

`IOSSecuritySuite` resolves to the newest release available on your dependency manager: `2.3.0`
under Swift Package Manager, `2.2.0` under CocoaPods (2.3.0 has not been published to the CocoaPods
trunk). 2.3.0 adds jailbreak detection checks, so Swift Package Manager builds detect slightly more.

## Migrating from jailbreak_root_detection

The Dart API is unchanged: `JailbreakRootDetection.instance` and all of its members keep their
names and behaviour. Only the identifiers changed.

1. Swap the dependency in `pubspec.yaml`:

   ```yaml
   dependencies:
   -  jailbreak_root_detection: ^1.2.0+1
   +  jailbreak_root_detection_plus: ^1.0.0
   ```

2. Update your imports:

   ```dart
   -import 'package:jailbreak_root_detection/jailbreak_root_detection.dart';
   +import 'package:jailbreak_root_detection_plus/jailbreak_root_detection_plus.dart';
   ```

3. Check the [Android build requirements](#android-build-requirements) table if you are on AGP 9+.

The method channel was renamed to `jailbreak_root_detection_plus` and the Android Kotlin package to
`com.w3conext.jailbreak_root_detection_plus`, so both packages can be installed in the same app
without colliding.

### Reference

- https://github.com/anish-adm/trust_fall
- https://github.com/w3connext/jailbreak_root_detection
