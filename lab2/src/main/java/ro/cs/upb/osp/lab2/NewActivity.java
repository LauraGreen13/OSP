package ro.cs.upb.osp.lab2;

import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ListView;
import android.widget.TextView;

import java.util.LinkedList;
import java.util.List;


public class NewActivity extends ActionBarActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.new_activity);

        TextView textView = (TextView) findViewById(R.id.helloText);
        if (getIntent() != null) {
            String message = getIntent().getStringExtra("MSG");
            textView.setText(textView.getText() + "\nThe message was : " + message);
        }

        //Task 3 - Data Storage
        List<MyMenuItem> menuItems = getMenuItems();

        ListView lv = (ListView) findViewById(R.id.listView);

        //Task 2 - Lists and Adapters
        //List<MyMenuItem> items = new ArrayList<MyMenuItem>();

        menuItems.add(new MyMenuItem("Coffee", 6, "Simple black coffee"));
        menuItems.add(new MyMenuItem("Caffe latte", 7, "Coffee with milk"));
        menuItems.add(new MyMenuItem("Espresso", 7, "Simple espresso"));
        menuItems.add(new MyMenuItem("Caffe macchiatto", 8, "Espresso with foamed milk"));

        MenuAdapter adapter = new MenuAdapter(this, menuItems);
        lv.setAdapter(adapter);
    }

    // get menu items from database - this should be in a DAO
    public List<MyMenuItem> getMenuItems() {

        List<MyMenuItem> menuItems = new LinkedList<MyMenuItem>();
        Cursor cursor = getContentResolver().query(Uri.parse(ItemsProvider.tableUri), new String[]{}, "", new String[]{}, "");


        while (cursor.moveToNext()) {
            menuItems.add(getItemFromCursor(cursor));
        }

        cursor.close();

        return  menuItems;
    }


    private MyMenuItem getItemFromCursor(Cursor cursor) {
        String name = cursor.getString(cursor.getColumnIndexOrThrow(DatabaseHelper.NAME));
        String description = cursor.getString(cursor.getColumnIndexOrThrow(DatabaseHelper.DESCRIPTION));
        float price = cursor.getFloat(cursor.getColumnIndexOrThrow(DatabaseHelper.PRICE));

        return new MyMenuItem(name, price, description);
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
