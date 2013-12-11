package com.redradix.ping;

import org.json.JSONArray;
import org.json.JSONException;

import org.apache.cordova.CordovaPlugin;
import org.apache.cordova.CallbackContext;

public class PingPlugin extends CordovaPlugin {
  @Override
  public boolen execute(String action, JSONArray args, CallbackContext callbackContext) {
    if ("ping".equals(action)) {
      callbackContext.success("pong!");
      return true;
    }
    callbackContext.error("oops!");
    return false;
  }
};
