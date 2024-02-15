package monster.moti.lsposed.blu;

import android.util.Log
import de.robv.android.xposed.XposedHelpers.findAndHookMethod
import de.robv.android.xposed.XC_MethodHook
import de.robv.android.xposed.XposedHelpers.getObjectField
import de.robv.android.xposed.callbacks.XC_LoadPackage.LoadPackageParam

fun bluRootCheckKiller(lpparam: LoadPackageParam){
    if (!lpparam.packageName.equals("com.samanpr.blu")) {
        return
    }
    findAndHookMethod(
        "blu.proto.protomodels.DeviceFlags",
        lpparam.classLoader,
        "getRooted",
        object : XC_MethodHook() {
            @Throws(Throwable::class)
            override fun beforeHookedMethod(param: MethodHookParam) {
                Log.d("BluRootCheck","bypassed");
                param.result=false
            }
        }
    )
    Log.d("BluRootCheck", "Hook Initialized")
}
