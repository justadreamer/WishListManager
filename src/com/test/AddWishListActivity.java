package com.test;

import android.app.Activity;
import android.app.AlertDialog;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;

import com.test.db.DatabaseManager;
import com.test.model.WishList;

public class AddWishListActivity extends Activity {
	private EditText edit;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
        ViewGroup contentView = (ViewGroup) getLayoutInflater().inflate(R.layout.add_wish_list, null);
        edit = (EditText) contentView.findViewById(R.id.edit);

        Button btn = (Button) contentView.findViewById(R.id.button_save);
        setupButton(btn);

        setContentView(contentView);
	}
	
	private void setupButton(Button btn) {
		final Activity activity = this;
		btn.setOnClickListener(new OnClickListener() {
			public void onClick(View v) {
				String name = edit.getText().toString();
				if (null!=name && name.length()>0) {
					createNewWishList(name);
					finish();
				} else {
					new AlertDialog.Builder(activity)
					.setTitle("Error")
					.setMessage("Invalid name!")
					.show();
				}
			}
		});
	}
	
	private void createNewWishList(String name) {
		WishList l = new WishList();
		l.setName(name);
		DatabaseManager.getInstance().addWishList(l);
	}
}
