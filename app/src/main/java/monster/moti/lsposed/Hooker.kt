package monster.moti.lsposed
import de.robv.android.xposed.IXposedHookLoadPackage
import de.robv.android.xposed.callbacks.XC_LoadPackage.LoadPackageParam
import monster.moti.lsposed.telegram.telegramCallKiller


class Hooker: IXposedHookLoadPackage {
    @Throws(Throwable::class)
    override fun handleLoadPackage(lpparam: LoadPackageParam) {

        telegramCallKiller(lpparam);
    }
}