package net.xjcook.xposed.showdialpad;

import static de.robv.android.xposed.XposedHelpers.findAndHookMethod;
import static de.robv.android.xposed.XposedHelpers.findMethodExact;

import de.robv.android.xposed.IXposedHookLoadPackage;
import de.robv.android.xposed.XC_MethodHook;
import de.robv.android.xposed.callbacks.XC_LoadPackage.LoadPackageParam;

public class ShowDialpad implements IXposedHookLoadPackage {

	static final String GOOGLE_DIALER = "com.google.android.dialer";
	static final String AOSP_DIALER = "com.android.dialer";
	
	@Override
	public void handleLoadPackage(LoadPackageParam lpparam) throws Throwable {
		if (lpparam.packageName.equals(GOOGLE_DIALER)) {
		
			findAndHookMethod("com.android.dialer.DialtactsActivity", lpparam.classLoader, "onResume", new XC_MethodHook() {
				@Override	
	    		protected void afterHookedMethod(MethodHookParam param) throws Throwable {
					final Object thisObject = param.thisObject;
					findMethodExact(thisObject.getClass().getSuperclass(), "showDialpadFragment", boolean.class)
							.invoke(thisObject, false);
	    		}
			});
		
		} else if (lpparam.packageName.equals(AOSP_DIALER)) {
			
			findAndHookMethod("com.android.dialer.DialtactsActivity", lpparam.classLoader, "onResume", new XC_MethodHook() {
				@Override	
	    		protected void afterHookedMethod(MethodHookParam param) throws Throwable {
					final Object thisObject = param.thisObject;
					findMethodExact(thisObject.getClass(), "showDialpadFragment", boolean.class)
							.invoke(thisObject, false);
	    		}
			});
			
		}
	}

}
