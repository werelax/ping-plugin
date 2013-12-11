/**
 * Phonegap ClipboardManager plugin
 * Omer Saatcioglu 2011
 * enhanced by Guillaume Charhon - Smart Mobile Software 2011
 *
 */

package com.saatcioglu.phonegap.clipboardmanager;

import org.json.JSONArray;
import org.json.JSONException;

import android.content.Context;
import android.text.ClipboardManager;

import org.apache.cordova.CordovaPlugin;
import org.apache.cordova.CallbackContext;
import org.apache.cordova.PluginResult;

public class ClipboardManagerPlugin extends CordovaPlugin {
  private static final String actionCopy = "copy";
  private static final String actionPaste = "paste";
  private static final String errorParse = "Couldn't get the text to copy";
  private static final String errorUnknown = "Unknown Error";

  private ClipboardManager mClipboardManager;

  @Override
  public boolean execute(String action, JSONArray args, CallbackContext callbackContext) throws JSONException {
    // If we do not have the clipboard
    if (mClipboardManager == null)
      // get it
      mClipboardManager = (ClipboardManager)ctx.getSystemService(Context.CLIPBOARD_SERVICE);

    // Copy
    if (action.equals(actionCopy)) {
      String arg = "";
      try {
        arg = (String) args.get(0);
        mClipboardManager.setText(arg);
      } catch (JSONException e) {
        return new PluginResult(PluginResult.Status.ERROR, errorParse);
      } catch (Exception e) {
        return new PluginResult(PluginResult.Status.ERROR, errorUnknown);
      }
      return new PluginResult(PluginResult.Status.OK, arg);

    // Paste
    } else if (action.equals(actionPaste)) {
      String arg = (String) mClipboardManager.getText();
      if (arg == null) {
        arg = "";
      }
      return new PluginResult(PluginResult.Status.OK, arg);
    } else {
      return new PluginResult(PluginResult.Status.INVALID_ACTION);
    }
  }
}
