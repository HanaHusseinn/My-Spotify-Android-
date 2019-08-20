package com.example.myapplicationproj;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.res.ResourcesCompat;

import org.json.JSONObject;

import android.content.Context;
import android.content.Intent;
import android.content.res.Resources;
import android.graphics.PorterDuff;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.os.Bundle;
import android.util.Base64;
import android.util.Log;
import android.view.View;
import android.webkit.WebView;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.VideoView;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import pl.droidsonroids.gif.GifDrawable;


public class MainActivity extends AppCompatActivity implements View.OnClickListener {
    public static final String EXTRA_catName = "com.example.myapplicationproj.EXTRA_catName"; //start with pckg name
    public static final String EXTRA_list= "com.example.myapplicationproj.EXTRA_list";
    //public static final String EXTRA_songsList= "com.example.myapplicationproj.EXTRA_songsList";

    //public ArrayList<model> songsList=new ArrayList<model>();


    private Button jazz;
    private Button pop;
    private Button popp;
    private Button hipHop;
    private Button classic;
    private TextView categories;
    private ImageView jazz_img;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        //References

        jazz= findViewById(R.id.button_jazz);
        pop= findViewById(R.id.button_pop);
        popp=findViewById(R.id.popp);
        //pop.setCompoundDrawables(,null,null,null);
        //pop.setCompoundDrawablesWithIntrinsicBounds(R.drawable.pop, 0, 0, 0);
        //WebView view = (WebView) findViewById(R.id.myWebView);
        //view.loadUrl("file:///android_asset/clef.gif");
        //view.setInitialScale(100);
        GifDrawable gifFromAssets = null;

        try {
            gifFromAssets = new GifDrawable( getAssets(), "notes2.gif" );
        } catch (IOException e) {
            e.printStackTrace();
        }



        popp.setBackground(gifFromAssets);

        //popp.getBackground().setColorFilter(this.getResources().getColor(R.color.ThemeBckgndColor), PorterDuff.Mode.SRC_ATOP);

        hipHop=findViewById(R.id.button_hipHop);
        classic=findViewById(R.id.button_classic);
        //jazz_img=findViewById(R.id.image_button_jazz);
        //jazz_img.setImageResource(R.id.image_button_jazz);
        //categories=findViewById(R.id.text_view_category);

        jazz.setOnClickListener(this);
        pop.setOnClickListener(this);
        hipHop.setOnClickListener(this);
        classic.setOnClickListener(this);
        //String URL= "https://api.spotify.com/v1/search?q=Justin&type=artist";
        //URL = URL.replaceAll(" ", "%20");

        //HTTPRequest gg=new HTTPRequest();



    }

    @Override
    public void onClick(View view) {
        int viewID=view.getId();
        String catName="";
        switch (viewID){
            case R.id.button_jazz:
                catName="Jazz";
                break;
            case R.id.button_pop:
                catName="Pop";
                break;
            case R.id.button_hipHop:
                catName="Hip Hop";
                break;
            case R.id.button_classic:
                catName="Classic";
                break;
                default:
                    break;
        }





        openActivity2(catName);
       /* switch(view.getId()) {
            case R.id.button_jazz:
                categories.setText("Categories > Jazz"); //3wza akhaliha byetdas 3aleha 3shan arga3
                break;
            case R.id.button_pop:
                categories.setText("Categories > Pop");
                break;
            case R.id.button_hipHop:
                categories.setText("Categories > Hip Hop");
                break;
            case R.id.button_classic:
                categories.setText("Categories > Classic");
                break;
                default:
                    break;
        }*/
    }


    public void openActivity2(String catName){
        Intent intent = new Intent(this, Activity2.class);

        intent.putExtra(EXTRA_catName,catName);///best practise is to create a constant for that string
        //intent.putExtra(EXTRA_songsList,(Serializable) songsList);
        //intent.putExtra(EXTRA_list,list);
        startActivity(intent);
    }

}
