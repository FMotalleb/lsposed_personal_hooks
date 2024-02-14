package monster.moti.lsposed.example;

import android.graphics.Color
import android.widget.TextView
import de.robv.android.xposed.XposedHelpers.findAndHookMethod
import de.robv.android.xposed.IXposedHookLoadPackage
import de.robv.android.xposed.XC_MethodHook
import de.robv.android.xposed.XposedBridge
import de.robv.android.xposed.callbacks.XC_LoadPackage.LoadPackageParam
import java.lang.reflect.Type
import kotlin.reflect.typeOf


class MyModule : IXposedHookLoadPackage {

    @Throws(Throwable::class)
    override fun handleLoadPackage(lpparam: LoadPackageParam) {
        if (!lpparam.packageName.equals("com.android.systemui")) return

        findAndHookMethod(
            "com.android.systemui.statusbar.policy.Clock",
            lpparam.classLoader,
            "updateClock",
            object : XC_MethodHook() {
                @Throws(Throwable::class)
                override fun afterHookedMethod(param: MethodHookParam) {
                    val tv = param.thisObject as TextView;
                    val text = tv.text.toString()
                    tv.text = "$text :)"
                    println("Type Of: " + tv.text);
                    tv.setTextColor(Color.RED)
                }
            })
    }
}