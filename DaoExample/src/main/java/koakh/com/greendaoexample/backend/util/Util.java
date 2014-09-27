package koakh.com.greendaoexample.backend.util;

import android.util.Log;
import android.widget.TextView;
import android.widget.Toast;

import koakh.com.greendaoexample.DaoExampleApplication;

/**
 * Created by mario on 27/09/2014.
 */
public class Util {

  public static void Log(DaoExampleApplication app, String message) {
    Log(app, null, message, false);
  }

  public static void Log(DaoExampleApplication app, TextView textview, String message) {
    Log(app, textview, message, false);
  }

  public static void Log(DaoExampleApplication app, TextView textview, String message, Boolean showToast) {
    if (textview != null) textview.setText(textview.getText() + "\n"+ message);
    if (showToast) Toast.makeText(app, message, Toast.LENGTH_SHORT).show();
    Log.i(app.getTag(), String.format(message));
  }
}
