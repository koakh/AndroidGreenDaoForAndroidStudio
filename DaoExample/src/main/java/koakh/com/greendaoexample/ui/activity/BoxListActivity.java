package koakh.com.greendaoexample.ui.activity;

import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.view.Menu;
import android.view.MenuItem;

import greendao.Box;

import koakh.com.greendaoexample.R;
import koakh.com.greendaoexample.backend.repositories.BoxRepository;

public class BoxListActivity extends ActionBarActivity {

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_box_list);
  }

  @Override
  public boolean onCreateOptionsMenu(Menu menu) {
    // Inflate the menu; this adds items to the action bar if it is present.
    getMenuInflater().inflate(R.menu.box_list, menu);
    return true;
  }

  @Override
  public boolean onOptionsItemSelected(MenuItem item) {
    // Handle action bar item clicks here. The action bar will
    // automatically handle clicks on the Home/Up button, so long
    // as you specify a parent activity in AndroidManifest.xml.
    int id = item.getItemId();
    if (id == R.id.action_menu_settings) {
      return true;
    }
    else
    if (id == R.id.action_menu_test_boxdao){
      testBoxDao();
      return true;
    }
    return super.onOptionsItemSelected(item);
  }

  public void testBoxDao() {
    Box box = new Box();
    //if box with id 5 already exists in DB, it will be edited instead of created
    box.setId((long) 5);
    box.setName("My box");
    box.setSlots(39);
    box.setDescription("This is my box. I can put in it anything I wish.");
    BoxRepository.insertOrUpdate(this, box);
  }
}
