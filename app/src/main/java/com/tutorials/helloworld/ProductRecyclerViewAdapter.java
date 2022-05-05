package com.tutorials.helloworld;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.squareup.picasso.Picasso;

import java.util.List;

public class  ProductRecyclerViewAdapter extends RecyclerView.Adapter<ProductCardViewHolder> {
    private List<Matches> matchesList;

    ProductRecyclerViewAdapter(List<Matches> matchesList) {
        this.matchesList = matchesList;
    }

    @NonNull
    @Override
    public ProductCardViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View layoutView = LayoutInflater.from(parent.getContext()).inflate(R.layout.matches_card, parent, false);
        return new ProductCardViewHolder(layoutView);
    }

    @Override
    public void onBindViewHolder(@NonNull ProductCardViewHolder holder, int position) {
        if (matchesList != null && position < matchesList.size()) {
            Matches match = matchesList.get(position);
            holder.matchesName.setText(match.getName());
            holder.matchesDescription.setText(match.getDescription());
            Picasso.get().load(match.getImageUrl()).into(holder.matchesImage);
            holder.likeButton.setOnClickListener((v) -> {
                Toast.makeText(v.getContext(),
                        String.format(v.getContext().getString(R.string.liked_message),
                                match.getName()), Toast.LENGTH_LONG).show();
            });
        }
    }

    @Override
    public int getItemCount() {
        return matchesList.size();
    }
}