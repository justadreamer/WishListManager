package com.test;

import java.util.ArrayList;
import java.util.List;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;

import com.test.db.DatabaseManager;
import com.test.model.WishItem;
import com.test.model.WishList;

public class WishItemListActivity extends Activity {
	ListView listView;
	WishList wishList;

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
        
        Button btnDeleteList = (Button) contentView.findViewById(R.id.button_delete);
        setupDeleteButton(btnDeleteList);

        setContentView(contentView);
	}
	
	@Override
	protected void onStart() {
		super.onStart();
		int wishListId = getIntent().getExtras().getInt(Constants.keyWishListId);
		wishList = DatabaseManager.getInstance().getWishListWithId(wishListId);
		Log.i("WishList","wishList="+wishList+" wishListId="+wishListId);
		setupListView();
		setTitle("Wish list '"+wishList.getName()+"'");
	}
	
	private void setupListView() {
		if (null != wishList) {
			final List<WishItem> wishItems = wishList.getItems();
			List<String> titles = new ArrayList<String>();
			for (WishItem wishItem : wishItems) {
				titles.add(wishItem.getName());
			}
			ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, titles);
			listView.setAdapter(adapter);
			final Activity activity = this;
			listView.setOnItemClickListener(new OnItemClickListener() {
				public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
					WishItem item = wishItems.get(position);
					Intent intent = new Intent(activity,AddWishItemActivity.class);
					intent.putExtra(Constants.keyWishItemId, item.getId());
					startActivity(intent);
				}
			});
		}
	}

	private void setupAddButton(Button btnAdd) {
		final Activity activity = this;
		btnAdd.setOnClickListener(new OnClickListener() {
			public void onClick(View v) {
				Intent intent = new Intent (activity,AddWishItemActivity.class);
				intent.putExtra(Constants.keyWishListId, wishList.getId());
				startActivity(intent);
			}
		});
	}

	private void setupEditButton(Button btnEditList) {
		final Activity activity = this;
		btnEditList.setOnClickListener(new OnClickListener() {
			public void onClick(View v) {
				Intent intent = new Intent (activity,AddWishListActivity.class);
				intent.putExtra(Constants.keyWishListId,wishList.getId());
				startActivity(intent);
			}
		});
	}

	private void setupDeleteButton (Button btnDeleteList) {
		final Activity activity = this;
		btnDeleteList.setOnClickListener(new OnClickListener() {
			public void onClick(View v) {
				new AlertDialog.Builder(activity)
				.setMessage("Are you sure you would like to delete list '"+wishList.getName()+"'?")
				.setNegativeButton("No", new DialogInterface.OnClickListener() {
					public void onClick(DialogInterface dialog, int which) {
						dialog.dismiss();
					}
				})
				.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
					public void onClick(DialogInterface dialog, int which) {
						dialog.dismiss();
						deleteWishList();
					}
				})
				.create()
				.show();
			}
		});
	}

	private void deleteWishList() {
		DatabaseManager.getInstance().deleteWishList(wishList);
		finish();
	}
}