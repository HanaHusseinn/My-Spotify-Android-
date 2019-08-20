package com.example.myapplicationproj;

import android.os.AsyncTask;
import com.google.gson.Gson;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.ProtocolException;
import java.net.URL;
import java.util.ArrayList;

import javax.net.ssl.HttpsURLConnection;




public class GetRequest extends AsyncTask<String,Void,String > { //must  write the class itself not a primitive Integer
    Activity2 activity;
    String FinalSongsIDS;
    String finalPreviewLinks;
    ArrayList<model> Data=new ArrayList<model>();


    public GetRequest(Activity2 activity) {
        this.activity = activity;
    }

    @Override
    protected String doInBackground(String... params) {
        // if(android.os.Debug.isDebuggerConnected())
        //android.os.Debug.waitForDebugger(); //I HATE THIS LINEEEEEEEEEEE
        HttpsURLConnection con = null;



        String access_token = "BQBl9avz9O3kcW-Bk_EHtv5z7EDc8gS9Gwglr4c8kQNTi-HftQVgKG0oyOHRIAmdryCNJ-SVW5foa4ziZlJ89WVxLrMNncc0IUZ2EG5DnoK-0tnjEGVTqe8sqlO5cc8B8kgqoIJ4tn1G-j-HHAXXDI1ke9kvHxS-jdvkGD0EcHL6ZEc33XpuDnyegh1V-D6tRJ1MSTDb9TTAe6n1-0BVAGgNKsdxRRjpZ7WywFlaVfzs3h3L8Q-cXGCv0g0uPf3SJ9imEezPDnaIa4TlftgV3xH_gWyiMvSG";
        URL obj = null;
        String result="";
        String u=params[0];
        try {
            obj = new URL(u);
            con = (HttpsURLConnection) obj.openConnection();
            con.setRequestMethod("GET");
            con.setRequestProperty("Authorization", "Bearer " + access_token);
            con.setRequestProperty("Content-Type", "application/json");
            int responseCode = con.getResponseCode();
            System.out.println("\nSending'GET' request to url :  " + params[0]);
            System.out.println("Response Code : " + responseCode);
            BufferedReader in = new BufferedReader(
                    new InputStreamReader(con.getInputStream()));
            String inputLine;
            StringBuffer response = new StringBuffer();
            while ((inputLine = in.readLine()) != null) {
                response.append(inputLine);
            }
            in.close();
            con.disconnect();
            System.out.println(response.toString());

            result = response.toString();

        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (ProtocolException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

        return result;


            /*
            JsonObject myResponse = new JsonParser().parse(response.toString()).getAsJsonObject();
            System.out.println("result  after reading JSON Response");


            ArrayList<JsonObject> Responses=new ArrayList<JsonObject>();
            JsonArray myArray= myResponse.getAsJsonArray("tracks");
            for (int i=0;i<50;i++) {
                JsonObject NewResponse = myArray.get(i).getAsJsonObject();
                Responses.add(NewResponse);
            }

            String result="";
            for (int i=0;i<50;i++) {
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


            //String url= params[1];
            String url= "https://api.spotify.com/v1/tracks?ids=";
            url= url+TracksID[0]; //for the first one only because no , before it
            if (TracksID.length>1) { //in case i only have one song , not so common case
                for (int i = 1; i < TracksID.length; i++) {
                    url = url + "%2C" + TracksID[i];
                }
            }
            //ana kda moftareda en el market dyman us , ASALLA7HA
            url=url+ "&market=US";
            System.out.println(url);

            obj=new URL(url);
            con=(HttpsURLConnection) obj.openConnection();
            con.setRequestMethod("GET");
            con.setRequestProperty("Authorization", "Bearer " + access_token);
            //con.setRequestProperty("User-Agent","Mozilla/5.0 (Macintosh; Intel Mac OS X 10_14_6) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/75.0.3770.142 Safari/537.36");
            con.setRequestProperty("Content-Type","application/json");
            responseCode= con.getResponseCode();
            System.out.println("\nSending'GET' request to url :  "+ url);
            System.out.println("Response Code : "+  responseCode);
            in = new BufferedReader(new InputStreamReader(con.getInputStream()));
            response =new StringBuffer();
            while (true){
                if (!((inputLine=in.readLine()) !=null))
                    break;
                response.append(inputLine);
            }
            in.close();
            con.disconnect();


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
            for (int i=0;i<TracksID.length;i++) {
                JsonObject NewResponse = myArray.get(i).getAsJsonObject();
                String SongURL=NewResponse.get("preview_url").toString();
                SongURL = SongURL.substring(1,SongURL.length()-1);
                System.out.println("Song URL "+ SongURL);
                //SSSS[i]=NewResponse.get("preview_url").toString();
                if (SongURL.length()>5){
                    model info=new model();
                    SongsPreview.add(SongURL);
                    FinalSongsArray.add(TracksID[i]);
                    count++;
                    info.setiD(TracksID[i]);
                    info.setLink(SongURL);
                    Data.add(info);
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


            FinalSongsIDS = r;


            String previewResult="";
            for (int i=0;i<SongsPreview.size();i++) {
                String res=SongsPreview.get(i);
                //res.substring(1,res.length()-1);
                if (i==0){
                    previewResult = previewResult + res;
                }
                else
                    previewResult = previewResult + "," + res;
            }

            finalPreviewLinks=previewResult;

            System.out.println(FinalSongsIDS);
            System.out.println(finalPreviewLinks);


            System.out.println("size = " +SongsPreview.size());
            System.out.println("count = " + Integer.toString(count));
            System.out.println("COrrect List");
            for (int i=0;i<SongsPreview.size();i++)
                System.out.println(SongsPreview.get(i));

        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (ProtocolException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

        //return FinalSongsIDS + "|" + finalPreviewLinks;
*/
    }



    @Override
    protected void onPostExecute(String result) {
        if (result != null) {


            Gson gson = new Gson();
            Reponse response = gson.fromJson(result, Reponse.class);
            //response.getTracks();
            //model one=response.getTracks().get(0);
            //Album album=one.getAlbum();
            //ArrayList<Image> images=album.getImages();
            //Image image=images.get(2);
            //String url= image.getUrl();
            ArrayList<model> tracks=new ArrayList<model>();
            //only taking songs with preview_url
            for (int i=0;i<response.getTracks().size();i++){
                if(response.getTracks().get(i).getPreview_url()!=null)
                    tracks.add(response.getTracks().get(i));
            }
            activity.updateData(tracks);




            /*
            String [] Arr = result.split("\\|");
            String [] FinalIDS = Arr[0].split("\\,");
            String [] FinalLINKS= Arr[1].split("\\,");
            ArrayList<model> data=new ArrayList<model>();
            for (int i=0;i<FinalIDS.length;i++){
                model song=new model();
                song.setiD(FinalIDS[i]);
                song.setLink(FinalLINKS[i]);
                data.add(song);
            }
            activity.updateData(data);
            */

            //String[] data = {"Billie - Bad Guy ft. Justin Bieber", "Señorita", "I Don't Care","Lucid Dreams","Billie - Bad Guy ft. Justin Bieber", "Señorita","Lucid Dreams", "I Don't Care","Billie - Bad Guy ft. Justin Bieber", "Señorita","Lucid Dreams", "I Don't Care"};
        }
    }


}