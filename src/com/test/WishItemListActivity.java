package com.test;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ListView;

public class WishItemListActivity extends Activity {
	ListView listView;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
        ViewGroup contentView = (ViewGroup) getLayoutInflater().inflate(R.layout.wish_item_list, null);
        listView = (ListView) contentView.findViewById(R.id.list_view);
        
        Button btnAdd = (Button) contentView.findViewById(R.id.button_add);
        setupAddButton(btnAdd);
        
        Button btnEditList = (Button) contentView.findViewById(R.id.button_edit);
        setupEditButton(btnEditList);
        
        setContentView(contentView);
	}
	
	@Override
	protected void onStart() {
		super.onStart();
		
	}

	private void setupAddButton(Button btnAdd) {
		final Activity activity = this;
		btnAdd.setOnClickListener(new OnClickListener() {
			public void onClick(View v) {
				Intent intent = new Intent (activity,AddWishItemActivity.class);
				startActivity(intent);
			}
		});
	}

	private void setupEditButton(Button btnEditList) {
		final Activity activity = this;
		btnEditList.setOnClickListener(new OnClickListener() {
			public void onClick(View v) {
				Intent intent = new Intent (activity,AddWishListActivity.class);
				startActivity(intent);				
			}
		});
	}
}