package koakh.com.greendaoexample;

import android.app.Application;
import android.database.sqlite.SQLiteDatabase;
import android.text.method.ScrollingMovementMethod;
import android.widget.TextView;

import greendao.DaoMaster;
import greendao.DaoSession;

public class DaoExampleApplication extends Application {

  //App
  public final String TAG = "GreenDaoExample";
  //UI
  public TextView textviewLogger;
  //Create DaoSession object which is avaliable during whole lifecycle of application and create getter for it.
  public DaoSession daoSession;

  @Override
  public void onCreate() {
    super.onCreate();
    setupDatabase();
  }

  //Getters and Setters

  public String getTag() {
    return TAG;
  }

  public TextView getLogger() {
    return textviewLogger;
  }

  public void setLogger(TextView textviewLogger) {
    this.textviewLogger = textviewLogger;
    this.textviewLogger.setMovementMethod(new ScrollingMovementMethod());
  }

  public DaoSession getDaoSession() {
    return daoSession;
  }

  //Helper Methods

  //Initalize GreenDao Database
  private void setupDatabase() {
    DaoMaster.DevOpenHelper helper = new DaoMaster.DevOpenHelper(this, "example-db", null);
    SQLiteDatabase db = helper.getWritableDatabase();
    DaoMaster daoMaster = new DaoMaster(db);
    daoSession = daoMaster.newSession();
  }
}
