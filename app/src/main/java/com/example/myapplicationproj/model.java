package com.example.myapplicationproj;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;
import java.util.ArrayList;

public class model implements Parcelable {


    private String id;
    private String preview_url;
    private ArrayList<Artist> artists;
    private String name;
    //@SerializedName("name")
    private Album album;

    public model(){

    }
    protected model(Parcel in) {
        id = in.readString();
        preview_url = in.readString();
        name = in.readString();
        artists = in.createTypedArrayList(Artist.CREATOR);//for array of created objects
        album=in.readParcelable(Album.class.getClassLoader());//for one created object
        //in.readTypedList(artists,Artist.CREATOR);
        //in.readTypedList(album,Album.CREATOR);

    }

    public static final Creator<model> CREATOR = new Creator<model>() {
        @Override
        public model createFromParcel(Parcel in) {
            return new model(in);
        }

        @Override
        public model[] newArray(int size) {
            return new model[size];
        }
    };

    public String getID(){
        return id;
    }

    public String getPreview_url(){
        return preview_url;
    }

    public void setiD(String iD){this.id=iD;}
    public void setPreview_url(String preview_url){this.preview_url=preview_url;}
    public ArrayList<Artist> getArtists(){
        return artists;
    }
    public void setArtists(ArrayList<Artist> artists){
        this.artists=artists;
    }
    public String getName(){
        return name;
    }
    public void setName(String name){
        this.name=name;
    }

    public Album getAlbum(){
        return album;
    }

    public void setAlbum(Album album){
        this.album=album;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeString(id);
        parcel.writeString(preview_url);
        parcel.writeString(name);
        parcel.writeTypedList(artists);
        parcel.writeParcelable(album,i);//i is the flag
        //parcel.writeTypedList(artists);
    }
}
