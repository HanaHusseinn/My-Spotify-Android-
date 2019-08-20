package com.example.myapplicationproj;

import android.content.Context;
import android.content.Intent;
import android.media.MediaPlayer;
import android.net.Uri;
import android.os.Bundle;
import android.os.Parcelable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;
import com.squareup.picasso.Transformation;

import java.io.IOException;
import java.io.Serializable;
import java.util.ArrayList;

import jp.wasabeef.picasso.transformations.MaskTransformation;
import jp.wasabeef.picasso.transformations.RoundedCornersTransformation;

public class CustomAdapter extends ArrayAdapter
{



    Context con;//list bec i want to keep updating it so its size will keep changing so dynamic better
    ArrayList<model> data= new ArrayList<model>();

    private static class ViewHolder {
        TextView text;
        TextView playURL;
        ImageButton button;
    }

    public  CustomAdapter (Context context, ArrayList<model> data)
    {
        super(context,android.R.layout.simple_list_item_1,android.R.id.text1);
        this.con = context;
        this.data = data;
    }

    public CustomAdapter(Context context) {
        super(context, android.R.layout.simple_list_item_1, android.R.id.text1);
        this.con= context;
    }

    @Override
    public int getCount() {

        //int size = models == null ? 0 : models.size();
        //return size;
        return data.size();
    }

    @Override
    public Object getItem(int position) { return data.get(position); }

    @Override
    public long getItemId(int position) {
        //return 0;
        return position;
    }
    //this method will be called for every item of your listview
    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {

        LayoutInflater inflater = (LayoutInflater) con.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        convertView= inflater.inflate(R.layout.listlayout, parent, false);

        TextView text = (TextView) convertView.findViewById(R.id.text_view_song_details); //recognize your view like this
        TextView singer=(TextView) convertView.findViewById(R.id.singer_name_playlist_text_view);
        //TextView singer_name_text_view=(TextView) convertView.findViewById(R.id.singer_name_text_view);
        ImageButton button= (ImageButton) convertView.findViewById(R.id.play_button);
        final TextView playURL= (TextView) convertView.findViewById(R.id.play_url_button);
        ImageView imageView=(ImageView) convertView.findViewById(R.id.song_image_view);





        Album album=data.get(position).getAlbum();
        ArrayList<Image> images=album.getImages();
        Image image=images.get(0);


        final String song_image= image.getUrl();
        final String song_name=data.get(position).getName();
        final String singer_name=data.get(position).getArtists().get(0).getName();
        final String previewURL=data.get(position).getPreview_url();

        //setting the picture of song in playlist
        Picasso.with(con).load(song_image).into(imageView);

        //final Transformation transformation = new MaskTransformation(getContext(), R.drawable.layout_bg);
        //Picasso.with(con).load(song_image).transform(transformation).resize(175,300).into(imageView);




        //setting the name of song
        //text.setSelected(true);//for horizontal scrolling

        text.setText(song_name); //send  the preview of song too url for the button wehn clicked to be directed to it, so it may be the content of the button ot=r somthing not the text to be displayed on it
        singer.setText(singer_name);

        //TODO: do relative view singer name textview below song name textview
        //singer_name_text_view.setText(singer_name);

        //setting the preview_url of the song to the play_button
        playURL.setText(data.get(position).getPreview_url());
        //Action on clicking on play button
        button.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view) {

                Intent intent = new Intent(con, SongActivity.class);

                intent.putExtra(Activity2.EXTRA_song_image,song_image);///best practise is to create a constant for that string
                intent.putExtra(Activity2.EXTRA_song_name,song_name);
                intent.putExtra(Activity2.EXTRA_singer_name,singer_name);
                intent.putExtra(Activity2.EXTRA_preview_url,previewURL);
                intent.putExtra(Activity2.EXTRA_position,position);

                //Bundle args = new Bundle();
                //args.putSerializable(Activity2.EXTRA_tracks,(Serializable) a);
                //intent.putExtras(args);


                intent.putParcelableArrayListExtra(Activity2.EXTRA_tracks,data);
                //intent.putExtra(Activity2.EXTRA_tracks, data.get(1));

                //intent.putExtra(Activity2.EXTRA_position,position);
                //intent.putExtra(EXTRA_songsList,(Serializable) songsList);
                //intent.putExtra(EXTRA_list,list);
                intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK); //for mobile app to open new activity from another function in the activity
                con.startActivity(intent);


            }
        });

        //akhaliiha fl screen3 ,send the preview url and when i go there , just start song to be a ble to pause, also i w ant when i go back that the song keeps playing in background



        //String url=data.get(position).getAlbum().getImages().get(2).getUrl();




        //Uri uri = Uri.parse(preview_link.get(position)); // missing 'http://' will cause crash
        //Intent intent = new Intent(Intent.ACTION_VIEW, uri);
        //con.startActivity(intent); //must get this context first in constructor
        return convertView;
    }

    public void setData(ArrayList<model> newData) {
        if (data != null) {
            data.clear();
            data.addAll(newData);
            notifyDataSetChanged();
        }
    }
}
