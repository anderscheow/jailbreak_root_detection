package com.w3conext.jailbreak_root_detection

import android.app.Activity
import android.content.ComponentName
import android.content.Context
import android.content.Intent
import android.content.ServiceConnection
import android.os.IBinder
import android.util.Log
import com.anish.trust_fall.emulator.EmulatorCheck
import com.anish.trust_fall.externalstorage.ExternalStorageCheck
import com.anish.trust_fall.rooted.RootedCheck
import com.w3conext.jailbreak_root_detection.frida.NativeLoader
import com.w3conext.jailbreak_root_detection.frida.RemoteService

import io.flutter.embedding.engine.plugins.FlutterPlugin
import io.flutter.embedding.engine.plugins.activity.ActivityAware
import io.flutter.embedding.engine.plugins.activity.ActivityPluginBinding
import io.flutter.plugin.common.MethodCall
import io.flutter.plugin.common.MethodChannel
import io.flutter.plugin.common.MethodChannel.MethodCallHandler
import io.flutter.plugin.common.MethodChannel.Result
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch

/** JailbreakRootDetectionPlugin */
class JailbreakRootDetectionPlugin : FlutterPlugin, MethodCallHandler, ActivityAware,
    ServiceConnection {

    /// The MethodChannel that will the communication between Flutter and native Android
    ///
    /// This local reference serves to register the plugin with the Flutter Engine and unregister it
    /// when the Flutter Engine is detached from the Activity
    private lateinit var channel: MethodChannel

    private var service: IRemoteService? = null
    private var result: Result? = null
    private var activity: Activity? = null

    override fun onAttachedToEngine(flutterPluginBinding: FlutterPlugin.FlutterPluginBinding) {
        channel = MethodChannel(flutterPluginBinding.binaryMessenger, "jailbreak_root_detection")
        channel.setMethodCallHandler(this)

//        val context = flutterPluginBinding.applicationContext
//        val intent = Intent(context, RemoteService::class.java)
//        context.bindService(intent, this, Context.BIND_AUTO_CREATE)
    }

    override fun onMethodCall(call: MethodCall, result: Result) {
        when (call.method) {
            "isJailBroken" -> processJailBroken(result)
            "isRealDevice" -> result.success(!EmulatorCheck.isEmulator)
            "isOnExternalStorage" -> result.success(
                ExternalStorageCheck.isOnExternalStorage(activity)
            )
            else -> result.notImplemented()
        }
    }

    override fun onDetachedFromEngine(binding: FlutterPlugin.FlutterPluginBinding) {
        channel.setMethodCallHandler(null)
    }

    override fun onAttachedToActivity(binding: ActivityPluginBinding) {
        activity = binding.activity
    }

    override fun onDetachedFromActivityForConfigChanges() {}

    override fun onReattachedToActivityForConfigChanges(binding: ActivityPluginBinding) {
        activity = binding.activity
    }

    override fun onDetachedFromActivity() {
        activity = null
    }

    override fun onServiceConnected(name: ComponentName?, binder: IBinder?) {
//        service = IRemoteService.Stub.asInterface(binder)
//        if (service != null) {
//            result?.success(getResult())
//            result = null
//        }
    }

    override fun onServiceDisconnected(name: ComponentName?) {
        service = null
    }

    private fun processJailBroken(result: Result) {
//        if (service == null) {
//            this.result = result
//        } else {
//            result.success(getResult())
//        }

        val scope = CoroutineScope(Job() + Dispatchers.Default)
        scope.launch {
            val isRootBeer = RootedCheck.isJailBroken(activity)
            val frida = NativeLoader.detectFrida()
            val isSu = NativeLoader.isSu() == 0

            Log.i("", "isRootBeer: $isRootBeer")
            Log.i("", "frida: $frida")
            Log.i("", "isSu: $isSu")

            val isRooted = frida || isSu || isRootBeer

            result.success(isRooted)
        }
    }

    private fun getResult(): Boolean {
        val isRootBeer = RootedCheck.isJailBroken(activity)
        val frida = NativeLoader.detectFrida()
        val isSu = service!!.haveSu()
        return !frida && !isSu && isRootBeer
    }
}
