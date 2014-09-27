package greendao;

import android.database.sqlite.SQLiteDatabase;

import java.util.Map;

import de.greenrobot.dao.AbstractDao;
import de.greenrobot.dao.AbstractDaoSession;
import de.greenrobot.dao.identityscope.IdentityScopeType;
import de.greenrobot.dao.internal.DaoConfig;
import greendao.Box;
import greendao.BoxDao;

// THIS CODE IS GENERATED BY greenDAO, DO NOT EDIT.

/**
 * {@inheritDoc}
 *
 * @see de.greenrobot.dao.AbstractDaoSession
 */
public class DaoSession extends AbstractDaoSession {

  private final DaoConfig boxDaoConfig;

  private final BoxDao boxDao;

  public DaoSession(SQLiteDatabase db, IdentityScopeType type, Map<Class<? extends AbstractDao<?, ?>>, DaoConfig>
    daoConfigMap) {
    super(db);

    boxDaoConfig = daoConfigMap.get(BoxDao.class).clone();
    boxDaoConfig.initIdentityScope(type);

    boxDao = new BoxDao(boxDaoConfig, this);

    registerDao(Box.class, boxDao);
  }

  public void clear() {
    boxDaoConfig.getIdentityScope().clear();
  }

  public BoxDao getBoxDao() {
    return boxDao;
  }

}
