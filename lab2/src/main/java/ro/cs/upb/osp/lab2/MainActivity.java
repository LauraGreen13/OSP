package ro.cs.upb.osp.lab2;

import android.app.Activity;
import android.content.ContentValues;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;


public class MainActivity extends Activity {

    private Context context;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        context = this;

        Button button = (Button) findViewById(R.id.goToActivityButton);

        //Task 1
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, NewActivity.class);
                intent.putExtra("MSG", "Hey! This is my message");
                startActivity(intent);

            }
        });

        //Task 3 - Data Storage

        final EditText itemName = (EditText) findViewById(R.id.item_name_edit);
        final EditText itemDescription = (EditText) findViewById(R.id.item_description_edit);
        final EditText itemPrice = (EditText) findViewById(R.id.item_price_edit);

        Button addItem = (Button) findViewById(R.id.add_item);

        addItem.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (!checkInput(itemName, itemDescription, itemPrice)) {
                    Toast.makeText(context, "INVALID INPUT", Toast.LENGTH_LONG).show();
                    return;
                }
                ContentValues contentValues = new ContentValues();
                contentValues.put(DatabaseHelper.NAME, itemName.getText().toString());
                contentValues.put(DatabaseHelper.DESCRIPTION, itemDescription.getText().toString());
                contentValues.put(DatabaseHelper.PRICE, Float.parseFloat(itemPrice.getText().toString()));

                Uri uri = context.getContentResolver().insert(Uri.parse(ItemsProvider.tableUri), contentValues);

                Log.d("ADD ITEM RESULT: ", uri.toString());

                //clear edit texts
                itemName.setText("");
                itemDescription.setText("");
                itemPrice.setText("");

                hideKeybord();

                Toast.makeText(context, "Item was added", Toast.LENGTH_LONG).show();
            }
        });
    }


    private boolean checkInput(EditText itemName, EditText itemDescription, EditText itemPrice) {
        return !(itemName.getText() == null || itemName.getText().toString().equals(""))
                && !(itemDescription.getText() == null || itemDescription.getText().toString().equals(""))
                && !(itemPrice.getText() == null || itemPrice.getText().toString().equals(""));

    }

    private void hideKeybord() {
        InputMethodManager inputManager = (InputMethodManager)
                getSystemService(Context.INPUT_METHOD_SERVICE);

        inputManager.hideSoftInputFromWindow(getCurrentFocus().getWindowToken(),
                InputMethodManager.HIDE_NOT_ALWAYS);
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
