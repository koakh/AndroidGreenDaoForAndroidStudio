package koakh.com.greendaoexample.backend.repositories;

import android.content.Context;

import java.util.List;

import greendao.Box;
import greendao.BoxDao;

import koakh.com.greendaoexample.DaoExampleApplication;

public class BoxRepository {

  public static void insertOrUpdate(Context context, Box box) {
    getBoxDao(context).insertOrReplace(box);
  }

  public static void clearBoxes(Context context) {
    getBoxDao(context).deleteAll();
  }

  public static void deleteBoxWithId(Context context, long id) {
    getBoxDao(context).delete(getBoxForId(context, id));
  }

  public static Box getBoxForId(Context context, long id) {
    return getBoxDao(context).load(id);
  }

  public static List<Box> getAllBoxes(Context context) {
    return getBoxDao(context).loadAll();
  }

  private static BoxDao getBoxDao(Context context) {
    return ((DaoExampleApplication) context.getApplicationContext()).getDaoSession().getBoxDao();
  }
}
