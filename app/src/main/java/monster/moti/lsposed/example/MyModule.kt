package monster.moti.lsposed.example;

import de.robv.android.xposed.XposedHelpers.findAndHookMethod
import de.robv.android.xposed.IXposedHookLoadPackage
import de.robv.android.xposed.XC_MethodHook
import de.robv.android.xposed.XC_MethodReplacement
import de.robv.android.xposed.XposedHelpers.callMethod
import de.robv.android.xposed.XposedHelpers.getObjectField
import de.robv.android.xposed.XposedHelpers.setBooleanField
import de.robv.android.xposed.XposedHelpers.setIntField
import de.robv.android.xposed.XposedHelpers.setObjectField
import de.robv.android.xposed.callbacks.XC_LoadPackage.LoadPackageParam

fun LoG(input:Any){
    println("HOOKER: " +input);
}

class MyModule : IXposedHookLoadPackage {
    @Throws(Throwable::class)
    override fun handleLoadPackage(lpparam: LoadPackageParam) {
        if (!lpparam.packageName.equals("org.telegram.messenger.web")) return

        findAndHookMethod(
            "org.telegram.ui.ActionBar.ActionBarMenu.LazyItem",
            lpparam.classLoader,
            "setVisibility",
            "int",
            object : XC_MethodHook() {
                @Throws(Throwable::class)
                override fun beforeHookedMethod(param: MethodHookParam) {
                    val id = getObjectField(param.thisObject,"id");
                    LoG("item id: $id")
                    try {
                        if(id==32){
                            param.setResult(null)
                            LoG("blocked call icon visibility")
                        }
                    } catch (e:Throwable){
                        LoG(e)
                    }

                }
            }
        )
//        findAndHookMethod(
//            "org.telegram.ui.ChatActivity",
//            lpparam.classLoader,
//            "createView",
//            "android.content.Context",
//            object : XC_MethodHook() {
//                @Throws(Throwable::class)
//                override fun afterHookedMethod(param: MethodHookParam) {
//                    val callName="audioCallIconItem"
//                    val tv = param.thisObject
//                    try {
////                        setIntField(getObjectField(tv,callName),"visibility",8)
//                        setObjectField(tv,callName,null)
//                        setBooleanField(tv,"showAudioCallAsIcon",false)
//                        LoG("hook done")
//                    } catch (e:Throwable){
//                        LoG(e)
//                    }
//
//                }
//            }
//        )
    }
}