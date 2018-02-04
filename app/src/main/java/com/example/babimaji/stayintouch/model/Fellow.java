package com.example.babimaji.stayintouch.model;

import android.arch.persistence.room.ColumnInfo;
import android.arch.persistence.room.Entity;
import android.arch.persistence.room.PrimaryKey;
import android.os.Parcel;
import android.os.Parcelable;

@Entity
public class Fellow implements Parcelable {

    @PrimaryKey(autoGenerate = true)
    private int id;

    @ColumnInfo(name = "name")
    private String name;

    @ColumnInfo(name = "picture")
    private String picture;

    @ColumnInfo(name = "linkedin")
    private String linkedin;

    @ColumnInfo(name = "isFavorite")
    private boolean isFavorite;

    public boolean isFavorite() {
        return isFavorite;
    }

    public void setFavorite(boolean favorite) {
        isFavorite = favorite;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getLinkedin() {
        return linkedin;
    }

    public void setLinkedin(String linkedin) {
        this.linkedin = linkedin;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPicture() {
        return picture;
    }

    public void setPicture(String picture) {
        this.picture = picture;
    }


    public Fellow() {
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(this.id);
        dest.writeString(this.name);
        dest.writeString(this.picture);
        dest.writeString(this.linkedin);
        dest.writeByte(this.isFavorite ? (byte) 1 : (byte) 0);
    }

    protected Fellow(Parcel in) {
        this.id = in.readInt();
        this.name = in.readString();
        this.picture = in.readString();
        this.linkedin = in.readString();
        this.isFavorite = in.readByte() != 0;
    }

    public static final Creator<Fellow> CREATOR = new Creator<Fellow>() {
        @Override
        public Fellow createFromParcel(Parcel source) {
            return new Fellow(source);
        }

        @Override
        public Fellow[] newArray(int size) {
            return new Fellow[size];
        }
    };
}
