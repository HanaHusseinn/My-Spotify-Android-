package com.example.myapplicationproj;


import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageButton;
import android.widget.TextView;

import java.util.ArrayList;

public class AdvancedAdapter extends ArrayAdapter {

    Context con;
    ArrayList<String> data=new ArrayList<String>();//list bec i want to keep updating it so its size will keep changing so dynamic better
    ArrayList<String> preview_link= new ArrayList<String>();

    // View lookup cache
    private static class ViewHolder {
        TextView text;
        TextView playURL;
        ImageButton button;
    }

    @Override
    public int getCount() {
        return data.size();
    }

    @Override
    public Object getItem(int position) { return data.get(position); }

    @Override
    public long getItemId(int position) {
        //return 0;
        return position;
    }

    public AdvancedAdapter(Context context,ArrayList<String> data,ArrayList<String> preview_link) {
        super(context, R.layout.listlayout, data);
        this.data = data;
        this.preview_link=preview_link;
        this.con=context;
    }


    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        LayoutInflater inflater = LayoutInflater.from(getContext());
        convertView = inflater.inflate(R.layout.listlayout, parent, false);

        ViewHolder viewHolder = viewHolder = new ViewHolder();
        viewHolder.text = (TextView) convertView.findViewById(R.id.text_view_song_details);
        viewHolder.playURL= (TextView) convertView.findViewById(R.id.play_url_button);
        viewHolder.button= (ImageButton) convertView.findViewById(R.id.play_button);

        viewHolder.text.setSelected(true);//for horizontal scrolling
        viewHolder.text.setText(data.get(position)); //send  the preview of song too url for the button wehn clicked to be directed to it, so it may be the content of the button ot=r somthing not the text to be displayed on it
        viewHolder.playURL.setText(preview_link.get(position));

        return convertView;
    }

    public void setData(ArrayList<String> fIds,ArrayList<String> fLinks) {
        if (data != null) {
            data.clear();
            data.addAll(fIds);
            //notifyDataSetChanged();
        }
        if (preview_link != null) {
            preview_link.clear();
            preview_link.addAll(fLinks);
            //notifyDataSetChanged();
        }
        notifyDataSetChanged();
    }
}

