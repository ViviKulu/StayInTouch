package com.example.babimaji.stayintouch.view;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;


import com.example.babimaji.stayintouch.R;
import com.example.babimaji.stayintouch.model.Fellow;
import com.squareup.picasso.Picasso;

/**
 * Created by olgakoleda on 2/2/18.
 */

public class FellowsViewHolder extends RecyclerView.ViewHolder {

    private ImageView image;
    private TextView name;
    private TextView linkedin;
    private Context context;

    public FellowsViewHolder(View itemView) {
        super(itemView);

        image = itemView.findViewById(R.id.image_view);
        name = itemView.findViewById(R.id.name_textview);
        linkedin = itemView.findViewById(R.id.linkedin_textview);
        context = itemView.getContext();
    }

    public void onBind(Fellow fellow) {
        String url = fellow.getPicture();

        Log.d("onBind: ", url);

        name.setText(fellow.getName());
        linkedin.setText(fellow.getLinkedin());

        Picasso.with(context)
                .load(url)
//                .resize(100, 100)
                .into(image);

        Log.d("onBind: ", image.toString());
    }
}
