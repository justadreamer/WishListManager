package com.test.db;

import java.sql.SQLException;
import java.util.List;

import android.content.Context;

import com.test.model.WishList;

public class DatabaseManager {

	static private DatabaseManager instance;

	static public void init(Context ctx) {
		if (null==instance) {
			instance = new DatabaseManager(ctx);
		}
	}

	static public DatabaseManager getInstance() {
		return instance;
	}

	private DatabaseHelper helper;
	private DatabaseManager(Context ctx) {
		helper = new DatabaseHelper(ctx);
	}

	private DatabaseHelper getHelper() {
		return helper;
	}

	public List<WishList> getAllWishLists() {
		List<WishList> wishLists = null;
		try {
			wishLists = getHelper().getWishListDao().queryForAll();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return wishLists;
	}

	public void addWishList(WishList l) {
		try {
			getHelper().getWishListDao().create(l);
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
}