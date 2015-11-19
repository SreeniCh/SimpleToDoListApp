package com.schalamcharla.simpletodolistapp;

import java.lang.String;
import java.util.ArrayList;
import java.util.List;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.content.Intent;
import android.view.Menu;
import android.view.MenuItem;
import android.util.Log;

import com.schalamcharla.simpletodolistapp.util.Item;
import com.schalamcharla.simpletodolistapp.util.ItemsAdapter;


public class MainActivity extends AppCompatActivity {
    private static final String TAG = "SimpleToDo_LOG";
    public ArrayList<Item> items;
    private ItemsAdapter itemsAdapter;
    private ListView lvItems;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Log.i(TAG, "onStart");

        items = new ArrayList<>();
        lvItems = (ListView) findViewById(R.id.lvItems);
        itemsAdapter = new ItemsAdapter(this, items);
        lvItems.setAdapter(itemsAdapter);
        refreshUI();

        //OnClick Listener for ListView
        setupListViewOnClickListener();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main_menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle item selection
        switch (item.getItemId()) {
            case R.id.action_add_task:
                addItem();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }



    private void setupListViewOnClickListener() {
        lvItems.setOnItemClickListener(
                new AdapterView.OnItemClickListener() {
                    @Override
                    public void onItemClick(AdapterView<?> adapter,
                                            View item, int pos, long id) {
                        Item data = items.get(pos);
                        Intent intent = new Intent(MainActivity.this, ViewItemActivity.class);
                        //intent.putExtra("pos", pos);
                        intent.putExtra("id", data.getID());
                        startActivityForResult(intent, 101);

                    }
                });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent intent) {
        super.onActivityResult(requestCode, resultCode, intent);
        Log.i(TAG, "requestCode - " + requestCode);
        Log.i(TAG, "resultCode - " + resultCode);
        refreshUI();
    }

    //refresh list and notify
    private void refreshUI() {
        List<Item> listItems = Item.listAll(Item.class);
        items.clear();
        items.addAll(listItems);
        itemsAdapter.notifyDataSetChanged();

    }

    //User wants to create a task
    public void addItem() {
        Log.i(TAG, "add Item: list size: " + items.size());
        Intent intent = new Intent(MainActivity.this, AddItemActivity.class);
        startActivityForResult(intent, 102);
    }

    @Override
    public void onStart() {
        super.onStart();
        Log.i(TAG, "onStart");
    }


    @Override
    protected void onResume() {
        super.onResume();
        Log.i(TAG, "onResume");
    }


    @Override
    protected void onPause() {
        super.onPause();
        Log.i(TAG, "onPause");
    }


    @Override
    protected void onStop() {
        super.onStop();
        Log.i(TAG, "onStop");
    }


    @Override
    protected void onRestart() {
        super.onRestart();
        Log.i(TAG, "onRestart");
    }


    @Override
    protected void onDestroy() {
        super.onDestroy();
        Log.i(TAG, "onDestroy");
    }


    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        Log.i(TAG, "onSaveInstanceState");
    }


    @Override
    protected void onRestoreInstanceState(Bundle savedInstanceState) {
        super.onRestoreInstanceState(savedInstanceState);
        Log.i(TAG, "onRestoreInstanceState");
    }
}

