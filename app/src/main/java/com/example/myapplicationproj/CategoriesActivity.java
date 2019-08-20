package com.example.myapplicationproj;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

import android.content.Intent;
import android.graphics.PorterDuff;
import android.graphics.drawable.ColorDrawable;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.text.Html;
import android.view.View;
import android.widget.ImageView;

import java.io.Serializable;


public class CategoriesActivity extends AppCompatActivity implements View.OnClickListener {

    public static final String EXTRA_genreName = "com.example.myapplicationproj.EXTRA_genreName";
    public static final String EXTRA_genreColour = "com.example.myapplicationproj.EXTRA_genreColour";
    public static final String EXTRA_genrePhotoColour = "com.example.myapplicationproj.EXTRA_genrePhotoColour";
    public static final String EXTRA_genrePhoto = "com.example.myapplicationproj.EXTRA_genrePhoto";
    private ImageView micCard,sunCard,radioCard,discoCard,cassetteCard,bedCard,dumbbellCard,heart_card,saxophoneCard;
    private ImageView lampCard,celloCard,hatCard,eiffelCard,balloonCard,guitarCard,tapeCard,plateCard,percussionCard;
    private CardView popCard,summerCard,hipHopCard,partyCard,rockCard,sleepCard,workoutCard,romanceCard,jazzCard;
    private CardView focusCard,classicalCard,countryCard,frenchCard,kidsAndFamilyCard,metalCard,bluesCard,dinnerCard,latinCard;
    ActionBar actionBar;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_categories);

        //Changing actionBar title and its color and the background
        actionBar=getSupportActionBar();
        actionBar.setTitle("Categories");
        actionBar.setBackgroundDrawable(new ColorDrawable(getResources().getColor(R.color.pop_card)));
        actionBar.setIcon(R.mipmap.musicapp_round);
        actionBar.setTitle(Html.fromHtml("<font color=\"red\">" + "Categories" + "</font>"));
        //getActionBar().setHomeButtonEnabled(true);
        //getActionBar().setDisplayHomeAsUpEnabled(true);

        //Setting the imageviews of categories figures
        micCard=(ImageView)findViewById(R.id.mic_card);
        sunCard=(ImageView)findViewById(R.id.sun_card);
        radioCard=(ImageView) findViewById(R.id.radio_card);
        discoCard=(ImageView)findViewById(R.id.disco_card);
        cassetteCard=(ImageView)findViewById(R.id.cassette_card);
        bedCard=(ImageView)findViewById(R.id.bed_card);
        dumbbellCard=(ImageView)findViewById(R.id.dumbell_card);
        heart_card=(ImageView)findViewById(R.id.heart_card);
        saxophoneCard=(ImageView)findViewById(R.id.saxophone_card);
        lampCard=(ImageView)findViewById(R.id.lamp_card);
        celloCard=(ImageView) findViewById(R.id.cello_card);
        hatCard=(ImageView)findViewById(R.id.hat_card);
        eiffelCard=(ImageView) findViewById(R.id.eiffel_card);
        balloonCard=(ImageView) findViewById(R.id.balloon_card);
        guitarCard=(ImageView)findViewById(R.id.guitar_card);
        tapeCard=(ImageView)findViewById(R.id.tape_card);
        plateCard=(ImageView)findViewById(R.id.plate_card);
        percussionCard=(ImageView)findViewById(R.id.percussion_card);

        //Setting the card buttons of each category
        popCard=(CardView)findViewById(R.id.pop_card);
        summerCard=(CardView)findViewById(R.id.summer_card);
        hipHopCard=(CardView)findViewById(R.id.hip_hop_card);
        partyCard=(CardView)findViewById(R.id.party_card);
        rockCard=(CardView)findViewById(R.id.rock_card);
        sleepCard=(CardView)findViewById(R.id.sleep_card);
        workoutCard=(CardView)findViewById(R.id.workout_card);
        romanceCard=(CardView)findViewById(R.id.romance_card);
        jazzCard=(CardView)findViewById(R.id.jazz_card);
        focusCard=(CardView)findViewById(R.id.focus_card);
        classicalCard=(CardView)findViewById(R.id.classical_card);
        countryCard=(CardView)findViewById(R.id.country_card);
        frenchCard=(CardView)findViewById(R.id.french_card);
        kidsAndFamilyCard=(CardView)findViewById(R.id.kids_and_family_card);
        metalCard=(CardView)findViewById(R.id.metal_card);
        bluesCard=(CardView)findViewById(R.id.blues_card);
        dinnerCard=(CardView)findViewById(R.id.dinner_card);
        latinCard=(CardView)findViewById(R.id.latin_card);

        //Changing the colour of each category figure into its darker colour
        micCard.setColorFilter(this.getResources().getColor(R.color.mic_card), PorterDuff.Mode.SRC_ATOP);
        sunCard.setColorFilter(this.getResources().getColor(R.color.sun_card), PorterDuff.Mode.SRC_ATOP);
        radioCard.setColorFilter(this.getResources().getColor(R.color.radio_card), PorterDuff.Mode.SRC_ATOP);
        discoCard.setColorFilter(this.getResources().getColor(R.color.disco_card), PorterDuff.Mode.SRC_ATOP);
        cassetteCard.setColorFilter(this.getResources().getColor(R.color.cassette_card), PorterDuff.Mode.SRC_ATOP);
        bedCard.setColorFilter(this.getResources().getColor(R.color.bed_card), PorterDuff.Mode.SRC_ATOP);
        dumbbellCard.setColorFilter(this.getResources().getColor(R.color.dumbbell_card), PorterDuff.Mode.SRC_ATOP);
        heart_card.setColorFilter(this.getResources().getColor(R.color.heart_card), PorterDuff.Mode.SRC_ATOP);
        saxophoneCard.setColorFilter(this.getResources().getColor(R.color.saxophone_card), PorterDuff.Mode.SRC_ATOP);
        lampCard.setColorFilter(this.getResources().getColor(R.color.lamp_card), PorterDuff.Mode.SRC_ATOP);
        celloCard.setColorFilter(this.getResources().getColor(R.color.cello_card), PorterDuff.Mode.SRC_ATOP);
        hatCard.setColorFilter(this.getResources().getColor(R.color.hat_card), PorterDuff.Mode.SRC_ATOP);
        eiffelCard.setColorFilter(this.getResources().getColor(R.color.eiffel_card), PorterDuff.Mode.SRC_ATOP);
        balloonCard.setColorFilter(this.getResources().getColor(R.color.balloon_card), PorterDuff.Mode.SRC_ATOP);
        guitarCard.setColorFilter(this.getResources().getColor(R.color.guitar_card), PorterDuff.Mode.SRC_ATOP);
        tapeCard.setColorFilter(this.getResources().getColor(R.color.tape_card), PorterDuff.Mode.SRC_ATOP);
        plateCard.setColorFilter(this.getResources().getColor(R.color.plate_card), PorterDuff.Mode.SRC_ATOP);
        percussionCard.setColorFilter(this.getResources().getColor(R.color.percussion_card), PorterDuff.Mode.SRC_ATOP);

        //Setting onCLickListener to all cards buttons clicks
        popCard.setOnClickListener(this);
        summerCard.setOnClickListener(this);
        hipHopCard.setOnClickListener(this);
        partyCard.setOnClickListener(this);
        rockCard.setOnClickListener(this);
        sleepCard.setOnClickListener(this);
        workoutCard.setOnClickListener(this);
        romanceCard.setOnClickListener(this);
        jazzCard.setOnClickListener(this);
        focusCard.setOnClickListener(this);
        classicalCard.setOnClickListener(this);
        countryCard.setOnClickListener(this);
        frenchCard.setOnClickListener(this);
        kidsAndFamilyCard.setOnClickListener(this);
        metalCard.setOnClickListener(this);
        bluesCard.setOnClickListener(this);
        dinnerCard.setOnClickListener(this);
        latinCard.setOnClickListener(this);



    }

    @Override
    public void onClick(View view) {
        //Taking the name of genre in Spotify to pass it to next activity to open the playlist in the next activity with that genre
        int viewID=view.getId();
        String genreName="";
        int genreColour = 0;
        int genrePhotoColour = 0;
        int genrePhoto = 0;
        switch (viewID){
            case R.id.pop_card:
                genreName="pop"; genreColour=getResources().getColor(R.color.pop_card); genrePhotoColour=R.color.mic_card;
                genrePhoto=R.drawable.mic_card;
                break;
            case R.id.summer_card:
                genreName="summer"; genreColour=getResources().getColor(R.color.summer_card); genrePhotoColour=R.color.sun_card;
                genrePhoto=R.drawable.sun_card;
                break;
            case R.id.hip_hop_card:
                genreName="hip-hop"; genreColour=getResources().getColor(R.color.hip_hop_card); genrePhotoColour=R.color.radio_card;
                genrePhoto=R.drawable.drum_card;
                break;
            case R.id.party_card:
                genreName="party"; genreColour=getResources().getColor(R.color.party_card); genrePhotoColour=R.color.disco_card;
                genrePhoto=R.drawable.disco_card;
                break;
            case R.id.rock_card:
                genreName="rock"; genreColour=getResources().getColor(R.color.rock_card); genrePhotoColour=R.color.cassette_card;
                genrePhoto=R.drawable.cassette_card;
                break;
            case R.id.sleep_card:
                genreName="sleep"; genreColour=getResources().getColor(R.color.sleep_card); genrePhotoColour=R.color.bed_card;
                genrePhoto=R.drawable.bed_card;
                break;
            case R.id.workout_card:
                genreName="work-out"; genreColour=getResources().getColor(R.color.workout_card); genrePhotoColour=R.color.dumbbell_card;
                genrePhoto=R.drawable.dumbbell_card;
                break;
            case R.id.romance_card:
                genreName="romance"; genreColour=getResources().getColor(R.color.romance_card); genrePhotoColour=R.color.heart_card;
                genrePhoto=R.drawable.heart2_card;
                break;
            case R.id.jazz_card:
                genreName="jazz"; genreColour=getResources().getColor(R.color.jazz_card); genrePhotoColour=R.color.saxophone_card;
                genrePhoto=R.drawable.saxophone3_card;
                break;
            case R.id.focus_card:
                genreName="study"; genreColour=getResources().getColor(R.color.focus_card); genrePhotoColour=R.color.lamp_card;
                genrePhoto=R.drawable.lamp3_card;
                break;
            case R.id.classical_card:
                genreName="classical"; genreColour=getResources().getColor(R.color.classical_card); genrePhotoColour=R.color.cello_card;
                genrePhoto=R.drawable.cello4_card;
                break;
            case R.id.country_card:
                genreName="country"; genreColour=getResources().getColor(R.color.country_card); genrePhotoColour=R.color.hat_card;
                genrePhoto=R.drawable.hat5_card;
                break;
            case R.id.french_card:
                genreName="french"; genreColour=getResources().getColor(R.color.french_card); genrePhotoColour=R.color.eiffel_card;
                genrePhoto=R.drawable.eiffel5_card;
                break;
            case R.id.kids_and_family_card:
                genreName="kids"; genreColour=getResources().getColor(R.color.kids_and_family_card); genrePhotoColour=R.color.balloon_card;
                genrePhoto=R.drawable.balloon_card;
                break;
            case R.id.metal_card:
                genreName="metal"; genreColour=getResources().getColor(R.color.metal_card); genrePhotoColour=R.color.guitar_card;
                genrePhoto=R.drawable.guitar_card;
                break;
            case R.id.blues_card:
                genreName="blues"; genreColour=getResources().getColor(R.color.blues_card); genrePhotoColour=R.color.tape_card;
                genrePhoto=R.drawable.tape_card;
                break;
            case R.id.dinner_card:
                genreName="piano"; genreColour=getResources().getColor(R.color.dinner_card); genrePhotoColour=R.color.plate_card;
                genrePhoto=R.drawable.plate_card;
                break;
            case R.id.latin_card:
                genreName="latin"; genreColour=getResources().getColor(R.color.latin_card); genrePhotoColour=R.color.percussion_card;
                //genrePhoto=getResources().getIdentifier(genrePhoto,"drawable", .getPackageName())
                genrePhoto=R.drawable.percussion_card;
                break;
            default:
                break;
        }
        openPlaylistActivity(genreName,genreColour,genrePhotoColour,genrePhoto);

    }

    public void openPlaylistActivity(String genreName, int genreColour, int genrePhotoColour, int genrePhoto){
        Intent intent = new Intent(this, Activity2.class);
        intent.putExtra(EXTRA_genreName,genreName);///best practise is to create a constant for that string
        intent.putExtra(EXTRA_genreColour,genreColour);
        intent.putExtra(EXTRA_genrePhotoColour,genrePhotoColour);
        intent.putExtra(EXTRA_genrePhoto, genrePhoto);
        startActivity(intent);
    }
}
