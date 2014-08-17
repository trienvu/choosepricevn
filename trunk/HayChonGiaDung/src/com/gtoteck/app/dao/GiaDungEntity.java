package com.gtoteck.app.dao;

import java.io.Serializable;

import android.database.Cursor;

public class GiaDungEntity  implements Serializable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private int id;
	
	private String name;
	
	private String image;
	
	private int price;
	
	private String madeIn;
	
	private String vendor;
	
	private String quantity;
	
	private String desc;
	
	public GiaDungEntity
	(){
		
	}
	 
	public GiaDungEntity(int id, String name, String image, int price,
			String madeIn, String vendor, String quantity, String desc) { 
		this.id = id;
		this.name = name;
		this.image = image;
		this.price = price;
		this.madeIn = madeIn;
		this.vendor = vendor;
		this.quantity = quantity;
		this.desc = desc;
	}
	
	public GiaDungEntity(Cursor cursor){
		this.id = cursor.getInt(0);
		this.name = cursor.getString(1);
		this.image = cursor.getString(2);
		this.price = cursor.getInt(3);
		this.madeIn = cursor.getString(4);
		this.vendor = cursor.getString(5);
		this.quantity = cursor.getString(6);
		this.desc  = cursor.getString(7);
	}
 

	public int getId() {
		return id;
	}



	public void setId(int id) {
		this.id = id;
	}



	public String getName() {
		return name;
	}



	public void setName(String name) {
		this.name = name;
	}



	public String getImage() {
		return image;
	}



	public void setImage(String image) {
		this.image = image;
	}



	public int getPrice() {
		return price;
	}



	public void setPrice(int price) {
		this.price = price;
	}



	public String getMadeIn() {
		return madeIn;
	}



	public void setMadeIn(String madeIn) {
		this.madeIn = madeIn;
	}



	public String getVendor() {
		return vendor;
	}



	public void setVendor(String vendor) {
		this.vendor = vendor;
	}



	public String getQuantity() {
		return quantity;
	}



	public void setQuantity(String quantity) {
		this.quantity = quantity;
	}



	public String getDesc() {
		return desc;
	}



	public void setDesc(String desc) {
		this.desc = desc;
	}



	@Override
	public String toString() {
		return "GiaDungEntity [id=" + id + ", name=" + name + ", image="
				+ image + ", price=" + price + ", madeIn=" + madeIn
				+ ", vendor=" + vendor + ", quantity=" + quantity + ", desc="
				+ desc + "]";
	}
	
	

}
