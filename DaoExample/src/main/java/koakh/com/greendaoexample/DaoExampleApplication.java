package koakh.com.greendaoexample;

import android.app.Application;
import android.database.sqlite.SQLiteDatabase;

import greendao.DaoMaster;
import greendao.DaoSession;

public class DaoExampleApplication extends Application {

  public final String TAG = "GreenDaoExample";

  //Create DaoSession object which is avaliable during whole lifecycle of application and create getter for it.
  public DaoSession daoSession;

  @Override
  public void onCreate() {
    super.onCreate();
    setupDatabase();
  }

  private void setupDatabase() {
    DaoMaster.DevOpenHelper helper = new DaoMaster.DevOpenHelper(this, "example-db", null);
    SQLiteDatabase db = helper.getWritableDatabase();
    DaoMaster daoMaster = new DaoMaster(db);
    daoSession = daoMaster.newSession();
  }

  public DaoSession getDaoSession() {
    return daoSession;
  }
  public String getTag() {
    return TAG;
  }
}
