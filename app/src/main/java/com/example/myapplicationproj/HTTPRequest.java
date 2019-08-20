package com.example.myapplicationproj;

import android.util.Log;

import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.prefs.PreferenceChangeEvent;


import javax.net.ssl.HttpsURLConnection;

public class HTTPRequest {
    public static void main (String[] args){
        HTTPRequest myObj = new HTTPRequest();
        String result="";
        try{
            result=HTTPRequest.callme();
        } catch (Exception e){
            e.printStackTrace();
        }
        System.out.println(result);
        //return result;
    }
    public static String callme() throws Exception{

/*
        String base_url = "https://api.genius.com";
        String access_token="BQDlrx7GP8uBfqrd6LyjEBUs8RtlQ5O4Ap1Seb-NaKf_VFP7aVPVjWqAsxUWxCYhWBjqbbvKVFJuGCC-CbCzHt1jQ0cpE6I-1kWZ1Dpq8wBvm2WFkaNIjuLVyEL22yko6mR60eUmCtyPc5_2J2Q0Vbezls9pKPJwPRKzvPCq5xs7kZXJE_una805DostR7Ft_p47z7_6PwqoRD-4S55_bi2M7U3dQ-o_QywX-iu7khdDBjNCtDNFQ1EIZ-6GrUG0jvRgRyavIiD9zHDtSsFIWZ2KVCxNQIYe";
        URL obj=new URL(base_url);
        HttpsURLConnection con=(HttpsURLConnection) obj.openConnection();
        con.setRequestMethod("GET");
        con.setRequestProperty("Authorization", "Bearer " + access_token);
        String search_url= base_url + "/search";
*/




        int limit=10; //max limit
        String genre="pop";
        String access_token="BQDlrx7GP8uBfqrd6LyjEBUs8RtlQ5O4Ap1Seb-NaKf_VFP7aVPVjWqAsxUWxCYhWBjqbbvKVFJuGCC-CbCzHt1jQ0cpE6I-1kWZ1Dpq8wBvm2WFkaNIjuLVyEL22yko6mR60eUmCtyPc5_2J2Q0Vbezls9pKPJwPRKzvPCq5xs7kZXJE_una805DostR7Ft_p47z7_6PwqoRD-4S55_bi2M7U3dQ-o_QywX-iu7khdDBjNCtDNFQ1EIZ-6GrUG0jvRgRyavIiD9zHDtSsFIWZ2KVCxNQIYe";
        //String url = "https://api.spotify.com/v1/recommendations?seed_genres=pop";
        String url= "https://api.spotify.com/v1/recommendations?limit=" +limit+"&seed_genres="+ genre;
        URL obj=new URL(url);
        HttpsURLConnection con=(HttpsURLConnection) obj.openConnection();
        con.setRequestMethod("GET");
        con.setRequestProperty("Authorization", "Bearer " + access_token);
        //con.setRequestProperty("User-Agent","Mozilla/5.0 (Macintosh; Intel Mac OS X 10_14_6) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/75.0.3770.142 Safari/537.36");
        con.setRequestProperty("Content-Type","application/json");
        int responseCode= con.getResponseCode();
        System.out.println("\nSending'GET' request to url :  "+ url);
        System.out.println("Response Code : "+  responseCode);
        BufferedReader in = new BufferedReader(
                new InputStreamReader(con.getInputStream()));
        String inputLine;
        StringBuffer response =new StringBuffer();
        while ((inputLine=in.readLine()) !=null){
            response.append(inputLine);
        }
        in.close();
        System.out.println(response.toString());
      //using  org.json llibrary
        /*  try {
            JSONObject myResponse= new JSONObject(response.toString());
        }catch (JSONException err){
            Log.d("Error", err.toString());
        }*/
//using google-gson :  google-gson
        JsonObject myResponse = new JsonParser().parse(response.toString()).getAsJsonObject();
        System.out.println("result  after reading JSON Response");


        ArrayList<JsonObject> Responses=new ArrayList<JsonObject>();
        JsonArray myArray= myResponse.getAsJsonArray("tracks");
        for (int i=0;i<limit;i++) {
            JsonObject NewResponse = myArray.get(i).getAsJsonObject();
            Responses.add(NewResponse);
        }

        String result="";
        for (int i=0;i<limit;i++) {
            String res = Responses.get(i).get("id").toString();
            res=res.substring(1,res.length()-1);
            if (i==0){
                result = result + res;
            }
            else
                result = result + "," + res;

        }





        System.out.println(result);
        String TracksID [] = result.split("\\,");
        for (int i=0;i<TracksID.length;i++)
            System.out.println(TracksID[i]);

        //result=result.substring(1,result.length()-1);
        //System.out.println(result); //resuult hena byerga3 ben quotations
        //myResponse=myArray.get(0).getAsJsonObject();
        //myArray= myResponse.getAsJsonArray("tracks");


        //****le7ad hena kda gebt el id bta3 el song lessa b2a 7ettet el preview



        //NEW PART TO GET THE TRACK

        //url = "https://api.spotify.com/v1/tracks/"+result.substring(1,result.length()-2)+"?market=US"; //market should be one of the returned ones but i asumed this will have lnk to most of preview links of songs
        //url= "https://api.spotify.com/v1/tracks/"+result+"?market=US";
        url= "https://api.spotify.com/v1/tracks?ids=";
        url= url+TracksID[0]; //for the first one only because no , before it
        if (TracksID.length>1) { //in case i only have one song , not so common case
            for (int i = 1; i < TracksID.length; i++) {
                url = url + "%2C" + TracksID[i];
            }
        }
        //ana kda moftareda en el market dyman us , ASALLA7HA
        url=url+ "&market=US";
        System.out.println(url);
        //akhhod el market bta3 el song nafsaha
        //a3ml check lw previewlink b null makhodsh el song di
        //a3ml check 3l return obejcts lw array or object 3shan msh dyman add ba3d
        obj=new URL(url);
        con=(HttpsURLConnection) obj.openConnection();
        con.setRequestMethod("GET");
        con.setRequestProperty("Authorization", "Bearer " + access_token);
        //con.setRequestProperty("User-Agent","Mozilla/5.0 (Macintosh; Intel Mac OS X 10_14_6) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/75.0.3770.142 Safari/537.36");
        con.setRequestProperty("Content-Type","application/json");
        responseCode= con.getResponseCode();
        System.out.println("\nSending'GET' request to url :  "+ url);
        System.out.println("Response Code : "+  responseCode);
        in = new BufferedReader(
                new InputStreamReader(con.getInputStream()));
        response =new StringBuffer();
        while ((inputLine=in.readLine()) !=null){
            response.append(inputLine);
        }
        in.close();
        System.out.println(response.toString());

        myResponse = new JsonParser().parse(response.toString()).getAsJsonObject();
        System.out.println("result  after reading JSON Response");


        //hena hakhod h3ml extract lel preview link of the song
        myArray= myResponse.getAsJsonArray("tracks");

        //String[] SongsPreview;
        //SongsPreview = new String[TracksID.length];
        ArrayList <String> SongsPreview = new ArrayList<String>();
        //String SSSS [] ={"","","","","","","","","",""};
        int count=0;
        ArrayList<String> FinalSongsArray= new ArrayList<String>();
        for (int i=0;i<TracksID.length;i++)
        {
            JsonObject NewResponse = myArray.get(i).getAsJsonObject();
            String SongURL=NewResponse.get("preview_url").toString();//hena abl .tostring a3ml check lw null
            SongURL = SongURL.substring(1,SongURL.length()-1);
            System.out.println("Song URL "+ SongURL);
            //SSSS[i]=NewResponse.get("preview_url").toString();
            if (SongURL.length()>5){
                SongsPreview.add(SongURL);
                FinalSongsArray.add(TracksID[i]);
                count++;
                System.out.println("done");
            }

        }
        String r = "";
        for (int i=0;i<FinalSongsArray.size();i++) {
            String res = FinalSongsArray.get(i);
            //res=res.substring(1,res.length()-1);
            if (i==0){
                r = r+ res;
            }
            else
                r = r + "," + res;

        }


        String FinalSongsIDS = r;


        String previewResult="";
        for (int i=0;i<SongsPreview.size();i++) {
            String res=SongsPreview.get(i);
            res.substring(1,res.length()-1);
            if (i==0){
                previewResult = previewResult + res;
            }
            else
                previewResult = previewResult + "," + res;
        }

        String finalPreviewLinks=previewResult;

        System.out.println(FinalSongsIDS);
        System.out.println(finalPreviewLinks);


        System.out.println("size = " +SongsPreview.size());
        System.out.println("count = " + Integer.toString(count));
        System.out.println("COrrect List");
        for (int i=0;i<SongsPreview.size();i++)
            System.out.println(SongsPreview.get(i));




        //now i want to return finalSongsIDs and SongPreview Lists




        //System.out.println(result);
        //return result;

        return FinalSongsIDS + "|" + finalPreviewLinks;
    }
    //*******lw el song malhash preview link ma7ottahash

    /*public String parse(String jsonLine) {
        jobject = jobject.getAsJsonObject("data");
        JsonArray jarray = jobject.getAsJsonArray("translations");
        jobject = jarray.get(0).getAsJsonObject();
        String result = jobject.get("translatedText").toString();
        return result;
    }*/
}
