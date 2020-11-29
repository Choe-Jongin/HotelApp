package com.example.hotel.Object;

import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;

import com.example.hotel.ConnectServer;

import java.io.Serializable;

public class RoomInfo implements Serializable {
    private int id;
    private String type;
    private String bed_type;
    private int base_person_num;
    private int base_price;
    private int add_person_num;
    private int add_price_per_person;
    private String thumbnailurl;


    public RoomInfo(){
        this(0,"nontype", "1", 1, 100,
                2, 10, "noimage");
    }
    public RoomInfo(int id, String type, String bed_type, int base_person_num, int base_price,
                    int add_person_num, int add_price_per_person, String thumbnailurl) {
        this.id = id;
        this.type = type;
        this.bed_type = bed_type;
        this.base_person_num = base_person_num;
        this.base_price = base_price;
        this.add_person_num = add_person_num;
        this.add_price_per_person = add_price_per_person;
        this.thumbnailurl = thumbnailurl;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getBed_type() {
        return bed_type;
    }

    public void setBed_type(String bed_type) {
        this.bed_type = bed_type;
    }

    public int getBase_person_num() {
        return base_person_num;
    }

    public void setBase_person_num(int base_person_num) {
        this.base_person_num = base_person_num;
    }

    public int getBase_price() {
        return base_price;
    }

    public void setBase_price(int base_price) {
        this.base_price = base_price;
    }

    public int getAdd_person_num() {
        return add_person_num;
    }

    public void setAdd_person_num(int add_person_num) {
        this.add_person_num = add_person_num;
    }

    public int getAdd_price_per_person() {
        return add_price_per_person;
    }

    public void setAdd_price_per_person(int add_price_per_person) {
        this.add_price_per_person = add_price_per_person;
    }

    public String getThumbnailUrl() {
        return thumbnailurl;
    }

    public void setThumbnailUrl(String thumbnail) {
        this.thumbnailurl = thumbnail;
    }

    public Drawable getDrawableThumbnail(){
        return new BitmapDrawable(ConnectServer.loadBitmap(thumbnailurl));
    }

}
