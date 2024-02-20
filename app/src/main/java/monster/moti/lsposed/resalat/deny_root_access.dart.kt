package monster.moti.lsposed.resalat

import android.util.Log
import de.robv.android.xposed.XC_MethodHook
import de.robv.android.xposed.XposedHelpers
import de.robv.android.xposed.callbacks.XC_LoadPackage.LoadPackageParam

const val logKey = "ResalatHook"
fun resalatRootKiller(lpparam: LoadPackageParam) {
    if (!lpparam.packageName.equals("mob.banking.android.resalat")) {
        return
    }

    XposedHelpers.findAndHookMethod(
        "mobile.banking.util.g2",
        lpparam.classLoader,
        "a",
        object : XC_MethodHook() {
            @Throws(Throwable::class)
            override fun beforeHookedMethod(param: MethodHookParam) {
                param.result = false
                Log.i(logKey, "return false")
            }
        }
    )
    
    Log.d(logKey, "Hook Initialized")
}