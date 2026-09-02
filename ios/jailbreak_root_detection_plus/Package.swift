// swift-tools-version: 5.9
import PackageDescription

let package = Package(
    name: "jailbreak_root_detection_plus",
    platforms: [
        .iOS(.v13),
    ],
    products: [
        .library(name: "jailbreak-root-detection-plus", targets: ["jailbreak_root_detection_plus"]),
    ],
    dependencies: [
        .package(name: "FlutterFramework", path: "../FlutterFramework"),
        .package(url: "https://github.com/securing/IOSSecuritySuite", from: "2.3.0"),
    ],
    targets: [
        .target(
            name: "jailbreak_root_detection_plus",
            dependencies: [
                .product(name: "FlutterFramework", package: "FlutterFramework"),
                .product(name: "IOSSecuritySuite", package: "IOSSecuritySuite"),
            ]
        ),
    ]
)
