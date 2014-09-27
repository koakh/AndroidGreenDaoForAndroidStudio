package koakh.com.greendaoexample.backend.util;

import android.util.Log;
import android.widget.Toast;

import koakh.com.greendaoexample.DaoExampleApplication;

/**
 * Created by mario on 27/09/2014.
 */
public class Util {

  public static void Log(DaoExampleApplication app, String message) {
    Toast.makeText(app, (String) message, Toast.LENGTH_SHORT).show();
    Log.d(app.getTag(), String.format(message));
  }
}
