package com.example.lost_and_found_app;

public class LostFoundItem {
    private long LostFoundItemId;
    private String ItemType;
    private String ItemName;
    private String PhoneNumber;
    private String Description;
    private String Date;
    private String Location;

    public LostFoundItem(long itemId, String postType, String name, String phone, String description, String date, String location) {
        this.LostFoundItemId = itemId;
        this.ItemType = postType;
        this.ItemName = name;
        this.PhoneNumber = phone;
        this.Description = description;
        this.Date = date;
        this.Location = location;
    }

    public long getItemId() {
        return LostFoundItemId;
    }

    public String getPostType() {
        return ItemType;
    }


    public String getName() {
        return ItemName;
    }

    public String getPhone() {
        return PhoneNumber;
    }

    public String getDescription() {
        return Description;
    }

    public String getDate() {
        return Date;
    }

    public String getLocation() {
        return Location;
    }
}

