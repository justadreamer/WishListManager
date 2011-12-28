package com.test;

import java.util.ArrayList;
import java.util.List;

import android.app.Activity;
import android.os.Bundle;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;

import com.test.db.DatabaseManager;
import com.test.model.WishList;

public class WishListManagerActivity extends Activity {
    /** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        DatabaseManager.init(this);

        ViewGroup contentView = (ViewGroup) getLayoutInflater().inflate(R.layout.main, null);
        ListView lv = (ListView) contentView.findViewById(R.id.list_view);
        setupListView(lv);
        
        Button btn = (Button) contentView.findViewById(R.id.button_add);
        setupButton(btn);
        setContentView(contentView);
    }

    private void setupListView(ListView lv) {
    	List<WishList> wishLists = DatabaseManager.getInstance().getAllWishLists();
    	
    	List<String> titles = new ArrayList<String>();
    	for (WishList wl : wishLists) {
    		titles.add(wl.getName());
    	}

    	ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, android.R.id.text1, titles);
    	lv.setAdapter(adapter);
    }
    
    private void setupButton(Button btn) {
    	
    }
}