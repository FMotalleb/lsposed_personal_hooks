package monster.moti.lsposed.telegram;

import android.util.Log
import de.robv.android.xposed.XposedHelpers.findAndHookMethod
import de.robv.android.xposed.XC_MethodHook
import de.robv.android.xposed.XposedHelpers.getObjectField
import de.robv.android.xposed.callbacks.XC_LoadPackage.LoadPackageParam

fun telegramCallKiller(lpparam: LoadPackageParam){
    if (!lpparam.packageName.equals("org.telegram.messenger.web")) {
        return
    }
    findAndHookMethod(
        "org.telegram.ui.ActionBar.ActionBarMenu.LazyItem",
        lpparam.classLoader,
        "setVisibility",
        "int",
        object : XC_MethodHook() {
            @Throws(Throwable::class)
            override fun beforeHookedMethod(param: MethodHookParam) {
                val id = getObjectField(param.thisObject,"id");
                Log.d("TelegramHooks","item id: $id")
                try {
                    if(id==32){
                        param.result = null
                        Log.d("TelegramHooks","blocked call icon")
                    }
                } catch (e:Throwable){
                    Log.e("TelegramHooks",e.toString())
                }
            }
        }
    )
    Log.d("TelegramHooks", "Hook Initialized")
}
