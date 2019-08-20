package com.example.myapplicationproj;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.os.Handler;
import android.os.Parcelable;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.SeekBar;
import android.widget.TextView;
import android.widget.Toast;

import com.mikhaellopez.circularimageview.CircularImageView;
import com.squareup.picasso.Picasso;

import java.io.IOException;
import java.util.ArrayList;

import static com.example.myapplicationproj.Activity2.EXTRA_position;

public class SongActivity extends AppCompatActivity {

    TextView songNameTextView,singerNameTextView;
    CircularImageView songImage;
    ImageView backgroundImage;
    SeekBar seekBar;
    MediaPlayer mp = new MediaPlayer();
    Handler handler=new Handler();
    TextView remainingTime,passedTime;
    ImageView nextSong,previousSong,playSong;
    int position; //of the song in  the playlist data
    ArrayList<model> tracks;//all playlist tracks in this screen for next and previous buttons
    RelativeLayout song_relative_layout;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_song);

        getSupportActionBar().hide();
        Intent intent = getIntent();
        String songName = intent.getStringExtra(Activity2.EXTRA_song_name);//we can get the ct from class since its public const
        String singerName = intent.getStringExtra(Activity2.EXTRA_singer_name);
        String imageURL = intent.getStringExtra(Activity2.EXTRA_song_image);
        String previewURL = intent.getStringExtra(Activity2.EXTRA_preview_url);
        position= (int) intent.getSerializableExtra(EXTRA_position);
        //for nextSong and previousSong take the data and the current song's position

        //tracks = intent.getParcelableExtra(Activity2.EXTRA_tracks);
        tracks=intent.getParcelableArrayListExtra(Activity2.EXTRA_tracks);
        //Bundle args = intent.getBundleExtra("BUNDLE");
        //ArrayList<model> tracks = (ArrayList<model>) args.getSerializable(Activity2.EXTRA_tracks);


        //final int position = Integer.parseInt(intent.getStringExtra(EXTRA_position));//why was it an error when i did getIntExtra
        //position=(int) intent.getSerializableExtra(EXTRA_position);



        songNameTextView = (TextView) findViewById(R.id.song_name_text_view);
        singerNameTextView = (TextView) findViewById(R.id.singer_name_text_view_Activity3);
        songImage = (CircularImageView) findViewById(R.id.circular_song_image);
        backgroundImage = (ImageView) findViewById(R.id.background_image_view);
        seekBar = (SeekBar) findViewById(R.id.seekBar);
        //handler.removeCallbacks(moveSeekBarThread);
        //seekBar.setVisibility(View.INVISIBLE);
        passedTime=(TextView)findViewById(R.id.passed_time_text_view);
        remainingTime=(TextView)findViewById(R.id.remaining_time_text_view);
        nextSong=(ImageView)findViewById(R.id.next_song_button);
        previousSong=(ImageView)findViewById(R.id.previous_song_button);
        song_relative_layout=(RelativeLayout)findViewById(R.id.song_relative_layout);
        playSong=(ImageView)findViewById(R.id.song_play_button);
        playSong.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(mp.isPlaying()) {
                    mp.pause();
                    playSong.setImageDrawable(getResources().getDrawable(R.drawable.orange_play_button));
                }
                else{
                    mp.start();
                    playSong.setImageDrawable(getResources().getDrawable(R.drawable.pause_button));
                    handler.removeCallbacks(moveSeekBarThread);
                    handler.postDelayed(moveSeekBarThread, 100); //cal the thread after 100 milliseconds
                }

            }
        });

        songNameTextView.setSelected(true);
        singerNameTextView.setSelected(true);


        //send all song data here to be chnaged if i clicked next or previous
        playSong(previewURL,imageURL,songName,singerName);
        //ChangeSong(position);


        //int mediaPos = mp.getCurrentPosition();
        //int mediaMax = mp.getDuration();

        //seekBar.setMax(mediaMax); // Set the Maximum range of the
        //seekBar.setProgress(mediaPos);// set current progress to song's

        handler.removeCallbacks(moveSeekBarThread);
        handler.postDelayed(moveSeekBarThread, 100); //cal the thread after 100 milliseconds

        previousSong.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view) {
                ChangeSong(position==0 ? tracks.size() - 1 : --position);
            }
        });
        nextSong.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ChangeSong(position==tracks.size()-1? 0 : ++position);
            }
        });
    }

    private void playSong(String songLink,String songPhoto, String songName, String singerName){
        //TODO: also the song image set it and everything needed


        playSong.setImageDrawable(getResources().getDrawable(R.drawable.pause_button));
        Picasso.with(getApplicationContext()).load(songPhoto).into(backgroundImage);
        Picasso.with(getApplicationContext()).load(songPhoto).into(songImage);
        songNameTextView.setText(songName);
        singerNameTextView.setText(singerName);

        try {
            mp.reset(); //solved the problem of getting error due to having alredy nativeDataSource for next and previous songs
            mp.setDataSource(songLink);
            mp.prepare();
            mp.start();
        } catch (IOException e) {
            e.printStackTrace();
        }
        handler.removeCallbacks(moveSeekBarThread);
        handler.postDelayed(moveSeekBarThread, 100); //cal the thread after 100 milliseconds
    }
    /**
     * The Move seek bar. Thread to move seekbar based on the current position
     * of the song
     */

    private Runnable moveSeekBarThread = new Runnable() {
        public void run() {
            //seekBar.setVisibility(View.VISIBLE);
            if(mp.isPlaying()){

                int currentPositionNew = mp.getCurrentPosition();
                int durationNew = mp.getDuration();
                //float progressNew = (currentPositionNew * 100)/durationNew;
                float progressNew = (currentPositionNew * 100);
                seekBar.setMax(durationNew);
                seekBar.setProgress(currentPositionNew);


                int min= currentPositionNew/1000/60;
                int sec= currentPositionNew/1000%60;
                int remainingMin=(durationNew-currentPositionNew)/1000/60;
                int remainingSec=(durationNew-currentPositionNew)/1000%60;
                String timeLabel=String.valueOf(min)+":";
                if(sec<10) timeLabel+="0";
                timeLabel+=sec;

                String remainingTimeLabel=String.valueOf(remainingMin)+":";
                if(remainingSec<10) remainingTimeLabel+="0";
                remainingTimeLabel+=remainingSec;

                passedTime.setText(timeLabel);
                remainingTime.setText(remainingTimeLabel);

                //passedTime.setText(String.valueOf(progressNew) + "%");
                //remainingTime.setText(String.valueOf(durationNew-progressNew)+ "%");

                handler.postDelayed(this, 100); //Looping the thread after 0.1 second
            }
            else
                playSong.setImageDrawable(getResources().getDrawable(R.drawable.orange_play_button));
        }
    };

    private Runnable changeButton = new Runnable() {
        public void run() {
            if(mp.isPlaying()){
                int currentPositionNew = mp.getCurrentPosition();
                int durationNew = mp.getDuration();
                //float progressNew = (currentPositionNew * 100)/durationNew;
                float progressNew = (currentPositionNew * 100);
                seekBar.setMax(durationNew);
                seekBar.setProgress(currentPositionNew);


                int min= currentPositionNew/1000/60;
                int sec= currentPositionNew/1000%60;
                int remainingMin=(durationNew-currentPositionNew)/1000/60;
                int remainingSec=(durationNew-currentPositionNew)/1000%60;
                String timeLabel=String.valueOf(min)+":";
                if(sec<10) timeLabel+="0";
                timeLabel+=sec;

                String remainingTimeLabel=String.valueOf(remainingMin)+":";
                if(remainingSec<10) remainingTimeLabel+="0";
                remainingTimeLabel+=remainingSec;

                passedTime.setText(timeLabel);
                remainingTime.setText(remainingTimeLabel);

                //passedTime.setText(String.valueOf(progressNew) + "%");
                //remainingTime.setText(String.valueOf(durationNew-progressNew)+ "%");

                handler.postDelayed(this, 100); //Looping the thread after 0.1 second
            }
        }
    };

    private void ChangeSong(int position){
        if(mp.isPlaying())
            mp.stop();
        String songLink,songImg,songName,singerName;
        Album album=tracks.get(position).getAlbum();
        ArrayList<Image> images=album.getImages();
        Image image=images.get(0);

        songLink=tracks.get(position).getPreview_url();
        songImg=image.getUrl();
        songName=tracks.get(position).getName();
        singerName=tracks.get(position).getArtists().get(0).getName();
        playSong(songLink,songImg,songName,singerName);
    }

    //Song only plays in the song Activity, not played in background
    @Override
    public void onBackPressed() {
        super.onBackPressed();
        if(mp.isPlaying())
            mp.stop();
        //tracks.clear();
    }

}
