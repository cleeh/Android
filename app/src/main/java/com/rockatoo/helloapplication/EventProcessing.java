package com.rockatoo.helloapplication;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ListView;

import java.util.ArrayList;

public class EventProcessing extends AppCompatActivity {
    // Widget Reference
    EditText itemEditText;
    ListView todoListView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_event_processing);

        // Get Object Reference
        itemEditText = (EditText)findViewById(R.id.todo_item_edit);
        todoListView = (ListView)findViewById(R.id.todo_list_view);

        /*
         * ListView Setting
         */
        // Set Data Structure to save items
        final ArrayList<String> itemList = new ArrayList<String>();
        final ArrayAdapter<String> adapter = new ArrayAdapter<String>(this,
                android.R.layout.simple_list_item_1, itemList);

        // Connect Adapter to ListView
        todoListView.setAdapter(adapter); // without this, ListView doesn't show items

        // Add Test Value
        itemList.add("Rockatoo Attends Lecture");
        itemList.add(" Making Todo List App");

        // Event Process: User can add Input Values into ListView
        itemEditText.setOnKeyListener(new View.OnKeyListener(){
            @Override
            public boolean onKey(View v, int keyCode, KeyEvent event) {
                // on Key stroke
                if(event.getAction() == KeyEvent.ACTION_DOWN){
                    // If key is 'Enter' stroke
                    if(keyCode == KeyEvent.KEYCODE_ENTER){
                        // Read Value in EditText
                        String item = itemEditText.getText().toString();
                        /*
                        * // Add Value which is read
                        * itemList.add(item);
                        */
                        // Add Value which is read on top
                        itemList.add(0, item);
                        // Notice Adapter to update values
                        adapter.notifyDataSetChanged();
                        // Erase itemEditText for next input
                        itemEditText.setText("");
                    }
                }
                return false;
            }
        });
    }
}
