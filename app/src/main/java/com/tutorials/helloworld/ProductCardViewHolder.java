package com.tutorials.helloworld;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

public class ProductCardViewHolder extends RecyclerView.ViewHolder {
    public ImageView matchesImage;
    public TextView matchesName;
    public TextView matchesDescription;
    public ImageButton likeButton;

    public ProductCardViewHolder(@NonNull View itemView) {
        super(itemView);
        matchesImage = itemView.findViewById(R.id.matches_image);
        matchesName = itemView.findViewById(R.id.matches_name);
        matchesDescription = itemView.findViewById(R.id.matches_description);
        likeButton = itemView.findViewById(R.id.like_button);
    }
}
