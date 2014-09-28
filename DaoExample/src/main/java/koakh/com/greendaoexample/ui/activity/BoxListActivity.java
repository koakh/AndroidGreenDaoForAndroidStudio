package koakh.com.greendaoexample.ui.activity;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.TextView;

import java.util.List;
import java.util.Random;

import greendao.Box;
import greendao.BoxDao;
import koakh.com.greendaoexample.DaoExampleApplication;
import koakh.com.greendaoexample.R;
import koakh.com.greendaoexample.backend.repositories.BoxRepository;
import koakh.com.greendaoexample.backend.util.Util;
import koakh.com.greendaoexample.ui.adapter.DbItemsAdapter;

public class BoxListActivity extends ActionBarActivity {

  public DaoExampleApplication app;
  private ListView lvItemList;
  private DbItemsAdapter boxAdapter;

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_box_list);

    app = ((DaoExampleApplication) this.getApplicationContext());
    app.setLogger((TextView) findViewById(R.id.textview));

    lvItemList = (ListView) this.findViewById(R.id.lvItemList);
    boxAdapter = new DbItemsAdapter(BoxListActivity.this);
    lvItemList.setAdapter(boxAdapter);

    setupButtons();
  }

  @Override
  protected void onStart() {
    super.onStart();
    boxAdapter.updateData(BoxRepository.getAllBoxes(BoxListActivity.this));
  }

  @Override
  public boolean onCreateOptionsMenu(Menu menu) {
    // Inflate the menu; this adds items to the action bar if it is present.
    getMenuInflater().inflate(R.menu.box_list_menu, menu);
    return true;
  }

  @Override
  public boolean onOptionsItemSelected(MenuItem item) {
    // Handle action bar item clicks here. The action bar will
    // automatically handle clicks on the Home/Up button, so long
    // as you specify a parent activity in AndroidManifest.xml.
    switch (item.getItemId()) {
      case R.id.add_item:
        createItem();
        return true;
      case R.id.delete_items:
        clearAllItems();
        return true;
      case R.id.action_menu_test_boxdao_write:
        testBoxDaoWrite();
        return true;
      case R.id.action_menu_test_boxdao_read:
        testBoxDaoRead();
        return true;
      case R.id.action_menu_test_boxdao_write_random_fixtures:
        testBoxDaoWriteRandomFixtures(100);
        return true;
      case R.id.action_menu_settings:
        return true;
      default:
        return super.onOptionsItemSelected(item);
    }
  }

  //Init UI

  private void setupButtons() {
    lvItemList.setOnItemClickListener(new AdapterView.OnItemClickListener() {
      @Override
      public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        Intent editItemIntent = new Intent(BoxListActivity.this, EditBoxActivity.class);

        Box clickedBox = boxAdapter.getItem(position);
        editItemIntent.putExtra("boxId", clickedBox.getId());

        startActivity(editItemIntent);
      }
    });
  }

  private void createItem() {
    Intent addBoxActivityIntent = new Intent(BoxListActivity.this, EditBoxActivity.class);
    startActivity(addBoxActivityIntent);
  }

  private void clearAllItems() {
    if (boxAdapter.getCount() == 0) {
      Util.Log(app, app.getLogger(), getString(R.string.toast_no_items_to_delete));
    } else {
      new AlertDialog.Builder(BoxListActivity.this)
        .setTitle(getString(R.string.dialog_delete_items_title))
        .setMessage(R.string.dialog_delete_items_content)
        .setCancelable(false)
        .setPositiveButton(R.string.dialog_delete_items_confirm, new DialogInterface.OnClickListener() {
          public void onClick(DialogInterface dialog, int id) {
            BoxRepository.clearBoxes(BoxListActivity.this);
            boxAdapter.updateData(BoxRepository.getAllBoxes(BoxListActivity.this));
            dialog.cancel();
          }
        })
        .setNegativeButton(R.string.dialog_delete_items_cancel, new DialogInterface.OnClickListener() {
          public void onClick(DialogInterface dialog, int id) {
            dialog.cancel();
          }
        }).create().show();
    }
  }

  //GreenDAO

  public void testBoxDaoWrite() {
    Box box = new Box();
    //if box with id 5 already exists in DB, it will be edited instead of created
    box.setId((long) 6);
    box.setName("My Other box");
    box.setSlots(28);
    box.setDescription("This is my box. I can put in it anything I wish. How many times i Want");
    BoxRepository.insertOrUpdate(this, box);
  }

  public void testBoxDaoRead() {
    BoxDao boxDao = app.getDaoSession().getBoxDao();
    List<Box> boxs = boxDao.queryBuilder().list();
    //for (int i = 0; i < boxs.size(); i++) {
    for (Box box : boxs) {
      //Util.Log(app, app.getLogger(), String.format("Name: %s\nDescription: %s", boxs.get(i).getName(), boxs.get(i).getDescription()));
      Util.Log(app, app.getLogger(), String.format("Id: %s\nName: %s\nDescription: %s", box.getId().toString(), box.getName(), box.getDescription()));
    }
  }

  public void testBoxDaoWriteRandomFixtures(Integer noOfRecs) {
    Random random = new Random();
    for (int i = 0; i < noOfRecs; i++) {
      Box box = new Box();
      box.setId((long) i);
      box.setName(Util.GetRandomString(10));
      box.setSlots(random.nextInt());
      box.setDescription(Util.GetRandomString(20));
      BoxRepository.insertOrUpdate(this, box);
    }
  }
}
