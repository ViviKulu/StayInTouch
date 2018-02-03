package com.example.babimaji.stayintouch.view;

import android.support.v7.widget.RecyclerView;
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

    public FellowsViewHolder(View itemView) {
        super(itemView);

        image = itemView.findViewById(R.id.image_view);
        name = itemView.findViewById(R.id.name_textview);
        linkedin = itemView.findViewById(R.id.linkedin_textview);
    }

    public void onBind(Fellow fellow) {
        String url = fellow.getPicture();

        name.setText(fellow.getName());
        linkedin.setText(fellow.getLinkedin());

        Picasso.with(itemView.getContext())
                .load(url)
                .into(image);
    }
}
