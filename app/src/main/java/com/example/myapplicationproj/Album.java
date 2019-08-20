package com.example.myapplicationproj;

import android.os.Parcel;
import android.os.Parcelable;

import java.util.ArrayList;

public class Album implements Parcelable {
    ArrayList<Image> images;


    public Album(){

    }
    protected Album(Parcel in) {
        images = in.createTypedArrayList(Image.CREATOR);
    }

    public static final Creator<Album> CREATOR = new Creator<Album>() {
        @Override
        public Album createFromParcel(Parcel in) {
            return new Album(in);
        }

        @Override
        public Album[] newArray(int size) {
            return new Album[size];
        }
    };

    public ArrayList<Image> getImages(){
        return images;
    }
    public void setImages(ArrayList<Image> images){
        this.images=images;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeTypedList(images);
    }
}
