package com.gaultmillau.cordova.audienceplus;

import org.apache.cordova.CallbackContext;
import org.apache.cordova.CordovaPlugin;
import org.apache.cordova.PluginResult;
import android.app.Application;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import android.util.Log;

import com.pcg.ape.AudiencePlus;
import com.pcg.ape.AudiencePlusSetup;

public class AudiencePlusPlugin extends CordovaPlugin {

  @Override
  protected void pluginInitialize() {
    Log.d("AudiencePlusPlugin", "Starting AudiencePlus plugin");
    super.pluginInitialize();
  }

  @Override
  public boolean execute(String action, JSONArray args, CallbackContext callbackContext) throws JSONException {
    if (action.equals("init")) {
      this.init(callbackContext);
      return true;
    }
    return false;
  }

  private void init(final CallbackContext callbackContext) {
    AudiencePlusSetup setup = new AudiencePlusSetup(
            "xxxxxx",// Firebase 'Sender ID'. (From Google)
            "https://xxxx.eu",  // BI URL.               (From Audience+)
            "https://xxxx.eu",  // Backend URL.          (From Audience+)
            1,                   // The push icon.        (From Application)
            "GM-xxxxxxxxxx"  // Your product ID.      (From Audience+)
    );

    try {
      AudiencePlus.initialize((Application) this.cordova.getActivity().getApplicationContext(), setup);
      callbackContext.success("Audience Plus successfully initialized");
    } catch (Exception e) {
      callbackContext.error(e.getMessage());
    }
  }
}
