package com.test;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;

import com.test.db.DatabaseManager;
import com.test.model.WishItem;
import com.test.model.WishList;

public class AddWishItemActivity extends Activity {
	private EditText editName;
	private EditText editDescription;
	private WishList wishList;
	private WishItem wishItem;
	private Button deleteButton;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
        ViewGroup contentView = (ViewGroup) getLayoutInflater().inflate(R.layout.add_wish_item, null);
        editName = (EditText) contentView.findViewById(R.id.edit_name);
        editDescription = (EditText) contentView.findViewById(R.id.edit_description);
        deleteButton = (Button) contentView.findViewById(R.id.button_delete);

        Button btn = (Button) contentView.findViewById(R.id.button_save);
        setupSaveButton(btn);

        setContentView(contentView);
        
        setupWishList();
        setupWishItem();
	}
	
	private void setupWishList() {
		Bundle bundle = getIntent().getExtras();
		if (null!=bundle && bundle.containsKey(Constants.keyWishListId)) {
			int wishListId = bundle.getInt(Constants.keyWishListId);
	        wishList = DatabaseManager.getInstance().getWishListWithId(wishListId);	
		}
	}

	private void setupWishItem() {
		Bundle bundle = getIntent().getExtras();
		if (null!=bundle && bundle.containsKey(Constants.keyWishItemId)) {
			int wishItemId = bundle.getInt(Constants.keyWishItemId);
			wishItem = DatabaseManager.getInstance().getWishItemWithId(wishItemId);
			editName.setText(wishItem.getName());
			editDescription.setText(wishItem.getDescription());
			deleteButton.setVisibility(View.VISIBLE);
			setupDeleteButton();
		} else {
			deleteButton.setVisibility(View.INVISIBLE);
		}
	}

	private void setupSaveButton(Button btn) {
		final Activity activity = this;
		btn.setOnClickListener(new OnClickListener() {
			public void onClick(View v) {
				String name = editName.getText().toString();
				String description = editDescription.getText().toString();
				boolean isValid = notEmpty(name) && notEmpty(description);
				if (isValid) {
					if (null==wishItem) {
						createNewWishItem(name,description);
					} else {
						updateWishItem(name,description);
					}
					finish();
				} else {
					new AlertDialog.Builder(activity)
					.setTitle("Error")
					.setMessage("All fields must be filled")
					.setNegativeButton("OK", new DialogInterface.OnClickListener() {
						public void onClick(DialogInterface dialog, int which) {
							dialog.dismiss();
						}
					})
					.show();
				}
			}
		});
	}
	
	private void setupDeleteButton() {
		if (null!=deleteButton) {
			final Activity activity = this;
			deleteButton.setOnClickListener(new OnClickListener() {
				public void onClick(View v) {
					new AlertDialog.Builder(activity)
					.setTitle("Warning")
					.setMessage("Are you sure you would like to delete wish '"+wishItem.getName()+"'?")
					.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
						public void onClick(DialogInterface dialog, int which) {
							DatabaseManager.getInstance().deleteWishItem(wishItem);
							dialog.dismiss();
							activity.finish();
						}
					})
					.setNegativeButton("No", new DialogInterface.OnClickListener() {
						public void onClick(DialogInterface dialog, int which) {
							dialog.dismiss();
						}
					})
					.show();
				}
			});
		}
	}

	protected void updateWishItem(String name, String description) {
		wishItem.setName(name);
		wishItem.setDescription(description);
		DatabaseManager.getInstance().updateWishItem(wishItem);
	}

	boolean notEmpty(String s) {
		return null!=s && s.length()>0;
	}

	private void createNewWishItem(String name,String description) {
		if (null!=wishList) {
			WishItem item = DatabaseManager.getInstance().newWishItem();
			item.setName(name);
			item.setDescription(description);
			item.setList(wishList);
			DatabaseManager.getInstance().updateWishItem(item);
		}
	}
}