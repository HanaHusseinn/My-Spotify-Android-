package com.example.myapplicationproj;

import android.os.Parcel;
import android.os.Parcelable;

public class Image implements Parcelable {

    private String url;

    public Image(){

    }

    protected Image(Parcel in) {
        url = in.readString();
    }

    public static final Creator<Image> CREATOR = new Creator<Image>() {
        @Override
        public Image createFromParcel(Parcel in) {
            return new Image(in);
        }

        @Override
        public Image[] newArray(int size) {
            return new Image[size];
        }
    };

    public String getUrl(){
            return url;
        }
        public void setUrl(String url) {
            this.url = url;
        }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeString(url);
    }
}
