package net.xjcook.xposed.showdialpad;

import static de.robv.android.xposed.XposedHelpers.findAndHookMethod;
import static de.robv.android.xposed.XposedHelpers.findMethodExact;

import de.robv.android.xposed.IXposedHookLoadPackage;
import de.robv.android.xposed.XC_MethodHook;
import de.robv.android.xposed.callbacks.XC_LoadPackage.LoadPackageParam;

public class ShowDialpad implements IXposedHookLoadPackage {

	@Override
	public void handleLoadPackage(LoadPackageParam lpparam) throws Throwable {
		if (!lpparam.packageName.equals("com.google.android.dialer"))
            return;

		findAndHookMethod("com.android.dialer.DialtactsActivity", lpparam.classLoader, "onResume", new XC_MethodHook() {
			@Override	
    		protected void afterHookedMethod(MethodHookParam param) throws Throwable {
				final Object thisObject = param.thisObject;
				findMethodExact(thisObject.getClass().getSuperclass(), "showDialpadFragment", boolean.class)
						.invoke(thisObject, false);
    		}
		});
	}

}
