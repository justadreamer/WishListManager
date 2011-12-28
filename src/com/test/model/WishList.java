package com.test.model;

import java.util.ArrayList;
import java.util.List;

import com.j256.ormlite.dao.ForeignCollection;
import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.field.ForeignCollectionField;
import com.j256.ormlite.table.DatabaseTable;

@DatabaseTable
public class WishList {
	@DatabaseField(generatedId=true)
	private int id;
	
	@DatabaseField
	private String name;
	
	@ForeignCollectionField
	private ForeignCollection<WishItem> items;

	public void setId(int id) {
		this.id = id;
	}

	public int getId() {
		return id;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getName() {
		return name;
	}

	public void setItems(ForeignCollection<WishItem> items) {
		this.items = items;
	}

	public List<WishItem> getItems() {
		ArrayList<WishItem> itemList = new ArrayList<WishItem>();
		for (WishItem item : items) {
			itemList.add(item);
		}
		return itemList;
	}
}
