package com.schalamcharla.simpletodolistapp;

import java.io.IOException;
import java.lang.String;
import java.util.ArrayList;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.content.Intent;
import java.io.File;

import android.util.Log;

import org.apache.commons.io.FileUtils;


public class MainActivity extends AppCompatActivity {
    private static final String TAG = "SimpleToDo_LOG";
    private ArrayList<String> items;
    private ArrayAdapter<String> itemsAdapter;
    private ListView lvItems;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Log.i(TAG, "onStart");

        lvItems = (ListView) findViewById(R.id.lvItems);
        //items = new ArrayList<>();
        readItems();
        itemsAdapter = new ArrayAdapter<>(this,
                android.R.layout.simple_list_item_1,
                items);
        lvItems.setAdapter(itemsAdapter);

        /*Button buttonAddItem = (Button) findViewById(R.id.btnAddItem);
        final EditText etNewItem = (EditText) findViewById(R.id.etNewItem);

        buttonAddItem.setOnClickListener(
                new Button.OnClickListener() {
                    public void onClick(View v) {
                        String text = etNewItem.getText().toString();
                        Log.i(TAG, "input text - " + text);
                        if (!text.equals("")) {
                            Log.i(TAG, "not equals");
                            itemsAdapter.add(text);
                            etNewItem.setText("");
                        }
                    }
                }
        );*/

        setupListViewOnLongListener();
        setupListViewOnClickListener();
    }

    /*public void launchEditItemActivity(View view) {
        Intent intent = new Intent(this, EditItemActivity.class);
        EditText editText = (EditText) findViewById(R.id.edit_message);
    }*/

    /*private void setupListViewListener() {
        lvItems.setOnLongClickListener(
                new AdapterView.OnLongClickListener() {
                    @Override
                    public boolean onItemLongClick(AdapterView<?> adapter,
                                                   View item,
                                                   int pos,
                                                   long id) {
                        items.remove(pos);
                        itemsAdapter.notifyDataSetChanged();
                        return true;
                    }
                }
        );
    }*/

    private void setupListViewOnLongListener() {
        lvItems.setOnItemLongClickListener(
                new AdapterView.OnItemLongClickListener() {
                    @Override
                    public boolean onItemLongClick(AdapterView<?> adapter,
                                                   View item, int pos, long id) {
                        Log.v(TAG, "pos: " + pos);
                        items.remove(pos);
                        itemsAdapter.notifyDataSetChanged();
                        writeItems();
                        return true;
                    }
                });
    }

    private void setupListViewOnClickListener() {
        lvItems.setOnItemClickListener(
                new AdapterView.OnItemClickListener() {
                    @Override
                    public void onItemClick(AdapterView<?> adapter,
                                            View item, int pos, long id) {
                        String data = items.get(pos);
                        Intent intent = new Intent(MainActivity.this, EditItemActivity.class);
                        intent.putExtra("pos", pos);
                        intent.putExtra("data", data);
                        startActivityForResult(intent, 101);

                    }
                });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent intent) {
        super.onActivityResult(requestCode, resultCode, intent);
        Log.i(TAG, "requestCode - " + requestCode);
        Log.i(TAG, "resultCode - " + resultCode);
        if (requestCode == 101) {
            if (resultCode == RESULT_OK) {
                String newData = intent.getStringExtra("data");
                int position = intent.getIntExtra("pos", -1);
                Log.i(TAG, "newData - " + newData);
                Log.i(TAG, "position - " + position);
                items.set(position, newData);
                itemsAdapter.notifyDataSetChanged();
                writeItems();
            }
        }

    }

    public void onAddItem(View v) {
        EditText etNewItem = (EditText) findViewById(R.id.etNewItem);
        String text = etNewItem.getText().toString();
        if (!text.equals("")) {
            Log.i(TAG, "not equals");
            itemsAdapter.add(text);
            etNewItem.setText("");
            writeItems();
        }

    }

    private void readItems() {
        File todoFile = new File(getFilesDir(), "todo.txt");
        try {
            items = new ArrayList<>(FileUtils.readLines(todoFile));

        } catch (IOException e) {
            items = new ArrayList<>();
        }
    }

    private void writeItems() {
        File todoFile = new File(getFilesDir(), "todo.txt");
        try {
            FileUtils.writeLines(todoFile, items);

        } catch (IOException e) {
            e.printStackTrace();
        }
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

