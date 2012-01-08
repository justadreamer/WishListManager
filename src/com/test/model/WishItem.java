package com.test.model;

import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.table.DatabaseTable;

@DatabaseTable
public class WishItem {
	@DatabaseField(generatedId=true)
	private int id;
	
	@DatabaseField
	private String name;

	@DatabaseField
	private String description;

	@DatabaseField(foreign=true,foreignAutoRefresh=true)
	private WishList list;

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

	public void setList(WishList list) {
		this.list = list;
	}

	public WishList getList() {
		return list;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getDescription() {
		return description;
	}
}
