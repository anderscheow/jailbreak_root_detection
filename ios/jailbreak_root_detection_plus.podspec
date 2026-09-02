#
# To learn more about a Podspec see http://guides.cocoapods.org/syntax/podspec.html.
# Run `pod lib lint jailbreak_root_detection_plus.podspec` to validate before publishing.
#
Pod::Spec.new do |s|
  s.name             = 'jailbreak_root_detection_plus'
  s.version          = '1.0.0'
  s.summary          = 'Check Jailbreak and Rooted for Android and iOS.'
  s.description      = <<-DESC
Check Jailbreak and Rooted for Android and iOS.
                       DESC
  s.homepage         = 'https://github.com/anderscheow/jailbreak_root_detection_plus'
  s.license          = { :file => '../LICENSE' }
  s.author           = 'anderscheow'
  s.source           = { :path => '.' }
  s.source_files     = 'jailbreak_root_detection_plus/Sources/jailbreak_root_detection_plus/**/*.swift'
  s.dependency       'Flutter'
  s.dependency       'IOSSecuritySuite', '~> 2.2'
  s.platform         = :ios, '13.0'
  s.swift_version    = '5.9'
  s.pod_target_xcconfig = { 'DEFINES_MODULE' => 'YES' }
end
