package com.example.babimaji.stayintouch.view;

import android.support.v7.widget.RecyclerView;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.view.View;
import android.widget.TextView;
import com.example.babimaji.stayintouch.FavoriteListener;
import com.example.babimaji.stayintouch.R;
import com.example.babimaji.stayintouch.model.Fellow;
import com.squareup.picasso.Picasso;

public class FellowsViewHolder extends RecyclerView.ViewHolder {

    private ImageView image;
    private TextView name;
    private TextView linkedin;
    private ImageButton imageButton;
    private int imageB;

    public FellowsViewHolder(View itemView) {
        super(itemView);

        image = itemView.findViewById(R.id.image_view);
        name = itemView.findViewById(R.id.name_textview);
        linkedin = itemView.findViewById(R.id.linkedin_textview);
        imageButton = itemView.findViewById(R.id.image_button);
    }

    public void onBind(final Fellow fellow, final FavoriteListener favoriteListener) {

        name.setText(fellow.getName());
        linkedin.setText(fellow.getLinkedin());

        String url = fellow.getPicture();

        Picasso.with(itemView.getContext())
                .load(url)
                .into(image);

        setImage(fellow.isFavorite());

        imageButton.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {

                boolean newStatus;

                if(fellow.isFavorite()) {
                    newStatus = false;
                } else{
                    newStatus = true;
                }

                fellow.setFavorite(newStatus);
                favoriteListener.updateFavorite(fellow);

                setImage(newStatus);
            }
        });
    }

    public void setImage(boolean isFavorite){
        if(isFavorite) {
            imageB = R.drawable.ic_favorite_black_24dp;
        } else{
            imageB = R.drawable.ic_favorite_border_black_24dp;
        }

        imageButton.setImageResource(imageB);

    }

}
