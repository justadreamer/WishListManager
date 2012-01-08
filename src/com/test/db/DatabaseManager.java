package com.test.db;

import java.sql.SQLException;
import java.util.List;

import android.content.Context;

import com.test.model.WishItem;
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

	public WishList getWishListWithId(int wishListId) {
		WishList wishList = null;
		try {
			wishList = getHelper().getWishListDao().queryForId(wishListId);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return wishList;
	}
	

	public WishItem getWishItemWithId(int wishItemId) {
		WishItem wishList = null;
		try {
			wishList = getHelper().getWishItemDao().queryForId(wishItemId);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return wishList;
	}

	public WishItem newWishItem() {
		WishItem wishItem = new WishItem();
		try {
			getHelper().getWishItemDao().create(wishItem);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return wishItem;
	}

	public void deleteWishList(WishList wishList) {
		try {
			getHelper().getWishListDao().delete(wishList);
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public void deleteWishItem(WishItem wishItem) {
		try {
			getHelper().getWishItemDao().delete(wishItem);
		} catch (SQLException e) {
			e.printStackTrace();
		}		
	}

	public void updateWishItem(WishItem item) {
		try {
			getHelper().getWishItemDao().update(item);
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public void refreshWishList(WishList wishList) {
		try {
			getHelper().getWishListDao().refresh(wishList);
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public void updateWishList(WishList wishList) {
		try {
			getHelper().getWishListDao().update(wishList);
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}


}