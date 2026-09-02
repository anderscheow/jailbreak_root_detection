## 1.0.0

Initial release, forked from
[jailbreak_root_detection](https://github.com/w3connext/jailbreak_root_detection) 1.2.0+1 and
republished as `jailbreak_root_detection_plus`.

### Renamed identifiers

The Dart API is unchanged — `JailbreakRootDetection.instance` and all of its members keep their
names and behaviour. Migrating from `jailbreak_root_detection` requires:

* `import 'package:jailbreak_root_detection_plus/jailbreak_root_detection_plus.dart';`
* The Android Kotlin package is now `com.w3conext.jailbreak_root_detection_plus`.
* The method channel is now `jailbreak_root_detection_plus`, so this package can be installed
  alongside the original without colliding.

### Android build changes

* Migrates to built-in Kotlin. The plugin no longer applies the Kotlin Gradle Plugin
  (`kotlin-android`), which is required to build with Android Gradle Plugin 9.0+.
  See https://docs.flutter.dev/release/breaking-changes/migrate-to-built-in-kotlin
* Updates the Android Gradle Plugin to 9.0.1, Kotlin to 2.3.20 and Gradle to 9.1.0.
* Raises `compileSdk` from 34 to 36 and the Java/Kotlin JVM target from 11 to 17 (AGP 9 requires JDK 17).
* Keeps the Android `minSdk` at 21.
* Updates RootBeer from 0.1.1 to 0.1.2.
* Drops `android.enableJetifier` and the `package` attribute from `AndroidManifest.xml`, both
  removed in AGP 9.
* Note: on AGP 9+ with Flutter 3.44-3.46 you must set `android.builtInKotlin=false` and
  `android.newDsl=false` in `android/gradle.properties`. Flutter 3.47+ needs no flags.
  See the "Android build requirements" section in the README.

### iOS build changes

* Supports both Swift Package Manager and CocoaPods.
* Declares the `FlutterFramework` Swift package dependency in
  `ios/jailbreak_root_detection_plus/Package.swift`, required by Flutter 3.44+ for Swift Package
  Manager plugins.
* Raises the iOS deployment target from 12.0 to 13.0, matching Flutter 3.44's minimum.
* Updates IOSSecuritySuite from 1.9.10 to the latest available on each channel: `2.3.0` for Swift
  Package Manager, `2.2.0` for CocoaPods (2.3.0 was never published to the CocoaPods trunk).

### Requirements

Flutter 3.44 / Dart 3.12 or newer.

---

<details>
<summary>Inherited history from <code>jailbreak_root_detection</code></summary>

### 1.2.0+1

* Improve Android and SDK Compatibility Without Over-Upgrading
* Acknowledgements: Thanks to @vicajilau for their PRs 🙏

### 1.2.0

* Added support for 16 KB page sizes
* Upgraded compileSdk and Gradle version
* Added background execution support
* Added null-safety support in `ExternalStorageCheck.kt`
* Acknowledgements: Thanks to @n0ks, @maeddin, @vicajilau, @anderscheow, and @bjlf12 for their PRs 🙏

### 1.1.6

* fix: TARGET_OS_SIMULATOR is deprecated in swift

### 1.1.5

* check has property namespace for Android

### 1.1.4

* Enhance security by adding more suspicious paths in iOS

### 1.1.3

* Add function is debugger on iOS and Android

### 1.1.2

* Check debugger on android

### 1.1.1

* Fixed bug pixel

### 1.1.0

* Add method `checkForIssues`

### 1.0.0

* Remove the function 'checkPSelectFlag' for the reverse engineering checker

### 0.0.7

* Update version IOSSecuritySuite to 1.9.10

### 0.0.6

* Change logic check detect frida to jvm

### 0.0.5

* Change library for check detect frida

### 0.0.4

* Config jniLibs by "armeabi-v7a", "arm64-v8a", "x86", "x86_64"

### 0.0.3

* Config ndk abiFilters "armeabi-v7a", "arm64-v8a", "x86", "x86_64"

### 0.0.2

* Remove config application in AndroidManifest.xml

### 0.0.1

* Uses RootBeer + DetectFrida for Android root detection and IOSSecuritySuite for iOS jailbreak detection.

</details>
