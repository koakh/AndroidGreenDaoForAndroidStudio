package koakh.com.greendaoexample.backend.util;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Random;

import android.content.Context;
import android.util.Log;
import android.widget.TextView;
import android.widget.Toast;

import koakh.com.greendaoexample.DaoExampleApplication;
import koakh.com.greendaoexample.R;

/**
 * Created by mario on 27/09/2014.
 * Koakh utils library
 */
public class Util {

  public static void Log(DaoExampleApplication app, TextView textview, String message) {
    Log(app, textview, message, false);
  }

  public static void Log(DaoExampleApplication app, TextView textview, String message, Boolean showToast) {
    String messageFinal = String.format("# %s\n%s\n", GetDate(app), message);

    if (textview != null) textview.setText(String.format("%s\n%s", textview.getText(),messageFinal));
    if (showToast) Toast.makeText(app, message, Toast.LENGTH_SHORT).show();
    Log.i(app.getTag(), String.format(message));
  }

  public static String GetDate(Context context) {
    SimpleDateFormat simpleDateFormat = new SimpleDateFormat(context.getString(R.string.formater_datetime_format));
    return simpleDateFormat.format(new Date());
  }

  public static String GetRandomString(Integer noOfChars) {


    char[] chars = "ABCDEF012GHIJKL345MNOPQR678STUVWXYZ9".toCharArray();
    StringBuilder stringBuilder = new StringBuilder();
    Random random1 = new Random();
    for (int i = 0; i < noOfChars; i++)
    {
      char c = chars[random1.nextInt(chars.length)];
      stringBuilder.append(c);
    }
    return  stringBuilder.toString();
  }
}
