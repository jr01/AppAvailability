package com.ohh2ahh.appavailability;

import java.util.List;

import org.apache.cordova.CallbackContext;
import org.apache.cordova.CordovaPlugin;
import org.json.JSONArray;
import org.json.JSONException;

import android.content.Context;
import android.content.pm.PackageManager;
import android.content.pm.ResolveInfo;
import android.content.Intent;
import android.net.Uri;

public class AppAvailability extends CordovaPlugin {
	@Override
	public boolean execute(String action, JSONArray args, CallbackContext callbackContext) throws JSONException {
		if (action.equals("appavailability")) {
			String uri = args.getString(0);
			this.appavailability(uri, callbackContext);
			return true;
		}
		return false;
	}
	
	public boolean appInstalled(String uriScheme) {
                Context ctx = this.cordova.getActivity().getApplicationContext();
                PackageManager pm = ctx.getPackageManager();
                Uri uri = Uri.parse(uriScheme);
                Intent intent = new Intent(Intent.ACTION_VIEW, uri);
                List<ResolveInfo> activities = pm.queryIntentActivities(intent, 0);
                boolean canLaunch = activities.size() > 0;
                return canLaunch;
        }

	private void appavailability(String uri, CallbackContext callbackContext) {
		if(appInstalled(uri)) {
			callbackContext.success();
		}
		else {
			callbackContext.error("App not available");
		}
	}
}
