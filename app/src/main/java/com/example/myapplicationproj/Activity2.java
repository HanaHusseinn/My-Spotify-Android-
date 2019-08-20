package com.example.myapplicationproj;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.view.MenuItemCompat;

import android.content.Intent;
import android.graphics.PorterDuff;
import android.graphics.drawable.ColorDrawable;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.view.Gravity;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.SearchView;
import android.widget.TextView;
import android.widget.Toast;
import java.util.ArrayList;

public class Activity2 extends AppCompatActivity {

    public static final String EXTRA_song_image = "com.example.myapplicationproj.EXTRA_song_image"; //start with pckg name
    public static final String EXTRA_song_name = "com.example.myapplicationproj.EXTRA_song_name"; //start with pckg name
    public static final String EXTRA_singer_name = "com.example.myapplicationproj.EXTRA_singer_name"; //start with pckg name
    public static final String EXTRA_preview_url = "com.example.myapplicationproj.EXTRA_preview_url"; //start with pckg name
    public static final String EXTRA_tracks = "com.example.myapplicationproj.EXTRA_tracks"; //start with pckg name
    public static final String EXTRA_position = "com.example.myapplicationproj.EXTRA_position"; //start with pckg name


    private TextView categories;
    private EditText singerName;
    private Button Search;
    ListView playList;
    private ListView newPL;
    private TextView songText;
    CustomAdapter ad;
    ActionBar actionBar;
    String genreName;

    //AdvancedAdapter add;
    private ArrayList<String> FinalIDS=new ArrayList<String>();
    private ArrayList<String> FinalLINKS=new ArrayList<String>();
    ArrayList<model> A=new ArrayList<model>();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_2);


        Intent intent=getIntent();
        genreName= intent.getStringExtra(CategoriesActivity.EXTRA_genreName);//we can get the ct from class since its public const
        int genreColour= (int) intent.getSerializableExtra(CategoriesActivity.EXTRA_genreColour);
        int genrePhotoColour = (int) intent.getSerializableExtra(CategoriesActivity.EXTRA_genrePhotoColour);
        int genrePhoto = (int) intent.getSerializableExtra(CategoriesActivity.EXTRA_genrePhoto);


        //ArrayList<model> songsList= (ArrayList<model>) intent.getSerializableExtra(MainActivity.EXTRA_songsList);
        if(genreName.matches("study")) genreName="focus";
        if (genreName.matches("piano")) genreName="dinner";
        Toast.makeText(this, genreName.toUpperCase(), Toast.LENGTH_SHORT).show();
        //Changing ActionBar data
        //changeActionBar(genreName,genreColour,genrePhoto,genrePhotoColour);
        //Making the background of actionBar the colour of the genreColour and the title of genreName
        actionBar=getSupportActionBar();
        actionBar.setTitle(genreName.toUpperCase());
        actionBar.setBackgroundDrawable(new ColorDrawable(genreColour));
        actionBar.setDisplayShowTitleEnabled(false);
        actionBar.setDisplayShowTitleEnabled(true);

        //Setting the image of genre on the right top side with genrePhoto
        actionBar.setDisplayOptions(actionBar.getDisplayOptions()
                | ActionBar.DISPLAY_SHOW_CUSTOM);
        ImageView imageView = new ImageView(actionBar.getThemedContext());
        //imageView.setScaleType(ImageView.ScaleType.CENTER);
        imageView.setScaleType(ImageView.ScaleType.FIT_CENTER);
        //setting image
        imageView.setImageResource(genrePhoto);

        imageView.setColorFilter(getResources().getColor(genrePhotoColour), PorterDuff.Mode.SRC_ATOP);
        ActionBar.LayoutParams layoutParams = new ActionBar.LayoutParams(
                ActionBar.LayoutParams.WRAP_CONTENT,
                ActionBar.LayoutParams.WRAP_CONTENT, Gravity.RIGHT
                | Gravity.CENTER_VERTICAL);
        layoutParams.rightMargin = 40;
        imageView.setLayoutParams(layoutParams);
        actionBar.setCustomView(imageView);



        //setTitleColor(R.color.saxophone_card);
        //changeTitle(catName);


        playList = (ListView) findViewById(R.id.list_view);


       //model a=new model();
       //a.setiD("ekdjfd");
       //a.setLink("http://www.google.com");
       //A.add(a);
        //String[] data = {"Billie - Bad Guy ft. Justin Bieber", "Señorita", "I Don't Care","Lucid Dreams","Billie - Bad Guy ft. Justin Bieber", "Señorita","Lucid Dreams", "I Don't Care","Billie - Bad Guy ft. Justin Bieber", "Señorita","Lucid Dreams", "I Don't Care"};


        model b= new model();
        b.setiD("3");
        Album album=new Album();
        ArrayList<Image> images=new ArrayList<Image>();
        Image image1=new Image();
        Image image2= new Image();
        Image image3=new Image();
        ArrayList<Artist> artists=new ArrayList<Artist>();
        Artist artist=new Artist();

        b.setPreview_url("http://www.google.com");
        image1.setUrl("https://i.scdn.co/image/6d1ff0bb0e6e7d0267588985bedd18b6131903c8");
        image2.setUrl("https://i.scdn.co/image/6d1ff0bb0e6e7d0267588985bedd18b6131903c8");
        image3.setUrl("https://i.scdn.co/image/6d1ff0bb0e6e7d0267588985bedd18b6131903c8");
        images.add(image1);
        images.add(image2);
        images.add(image3);
        album.setImages(images);
        b.setAlbum(album);

        artist.setName("Maher");
        artists.add(artist);
        b.setArtists(artists);

        /*
        Album album=b.getAlbum();
        album.setImages(new ArrayList<Image> ());
        Image img= album.getImages().get(2);

        img.setUrl("https://i.scdn.co/image/16631c30dffc465602037845e345a953386499da");
        */
        A.add(b);


        ad=new CustomAdapter(getApplicationContext(),A);
        playList.setVisibility(View.INVISIBLE);
        playList.setAdapter(ad);

        //public boolean onCreateOptionsMenu(Menu menu)



        //ad.notifyDataSetChanged();


        //String [] Links={"https://api.spotify.com/v1/recommendations?seed_genres=pop","https://api.spotify.com/v1/tracks?ids="};
        if(genreName.matches("focus")) genreName="study";
        if (genreName.matches("dinner")) genreName="piano";
        GetRequest hh= new GetRequest(Activity2.this);
        hh.execute("https://api.spotify.com/v1/recommendations?limit=75&seed_genres="+ genreName);




    }


    /*@Override
    public boolean onCreateOptionsMenu(Menu menu) {


        MenuInflater inflater=getMenuInflater();
        inflater.inflate(R.menu.menu_item,menu);

        MenuItem searchItem=menu.findItem(R.id.item_search);
        //SearchView searchView=(SearchView) MenuItemCompat.getActionView(searchItem);
        SearchView searchView= (SearchView) searchItem.getActionView();

        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {


            @Override
            public boolean onQueryTextChange(String newText) {
                ArrayList<model> templist=new ArrayList<model>();

                String temp;
                for(int i=0;i<A.size();i++){
                    temp=A.get(i).getName();
                    if(temp.toLowerCase().contains(newText.toLowerCase())){
                        templist.add(A.get(i));
                    }
                }
                updateData(templist);
                return true;
            }

            @Override
            public boolean onQueryTextSubmit(String s) {
                return false;
            }
        });

        return super.onCreateOptionsMenu(menu);

    }
*/

    public void changeTitle(String catName){
        categories=findViewById(R.id.text_view_category); //how do i read the text view of act 1
        switch(catName) {
            case "Jazz":
                categories.setText("Categories > Jazz"); //3wza akhaliha byetdas 3aleha 3shan arga3
                break;
            case "Pop":
                categories.setText("Categories > Pop");
                break;
            case "Hip Hop":
                categories.setText("Categories > Hip Hop");
                break;
            case "Classic":
                categories.setText("Categories > Classic");
                break;
            default:
                break;
        }
        //categories=findViewById(R.id.text_view_category)
    }

    public void updateData(ArrayList<model> Data) {
        CustomAdapter la= (CustomAdapter) playList.getAdapter();
        if( la != null){
            playList.setVisibility(View.INVISIBLE);
            ((CustomAdapter)la).setData(Data);
            ((ArrayAdapter)la).notifyDataSetChanged();
            playList.setVisibility(View.VISIBLE);
            A.clear();
            A.addAll(Data);
        }

    }

    public void changeActionBar(String genreName, int genreColour, Drawable genrePhoto, int genrePhotoColour){

    }
}

//songText=findViewById(R.id.text_view_song);
//Drawable img = this.getResources().getDrawable( R.drawable.play );
//songText.setCompoundDrawablesRelativeWithIntrinsicBounds(null,null,img,null);

        /*GifDrawable gifFromAsset = null;

        try {
            gifFromAsset = new GifDrawable( getAssets(), "play.jpg" );
        } catch (IOException e) {
            e.printStackTrace();
        }
        songText.setCompoundDrawablesRelativeWithIntrinsicBounds(null,null,gifFromAsset,null);
*/
        /*GifDrawable giff = null;

        try {
            giff = new GifDrawable( getAssets(), "glitter.gif" );
        } catch (IOException e) {
            e.printStackTrace();
        }
        LinearLayout lin = findViewById(R.id.linear_1);
        lin.setBackground(giff);
*/
//Getting parameters passed to the playlist activity