package com.test;

import java.util.ArrayList;
import java.util.List;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;

import com.test.db.DatabaseManager;
import com.test.model.WishList;

public class WishListManagerActivity extends Activity {
	ListView listView;

    /** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        DatabaseManager.init(this);

        ViewGroup contentView = (ViewGroup) getLayoutInflater().inflate(R.layout.main, null);
        listView = (ListView) contentView.findViewById(R.id.list_view);
        
        Button btn = (Button) contentView.findViewById(R.id.button_add);
        setupButton(btn);
        setContentView(contentView);
    }

    @Override
    protected void onStart() {
    	super.onStart();
        setupListView(listView);
    }

    private void setupListView(ListView lv) {
    	List<WishList> wishLists = DatabaseManager.getInstance().getAllWishLists();
    	
    	List<String> titles = new ArrayList<String>();
    	for (WishList wl : wishLists) {
    		titles.add(wl.getName());
    	}

    	ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, titles);
    	lv.setAdapter(adapter);
    }
    
    private void setupButton(Button btn) {
    	final Activity activity = this;
    	btn.setOnClickListener(new OnClickListener() {
			public void onClick(View v) {
				Intent intent = new Intent(activity,AddWishListActivity.class);
				startActivity (intent);
			}
		});
    }
}