package com.tutorials.helloworld;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.squareup.picasso.Picasso;

import java.util.List;
import java.util.function.Consumer;

public class  ProductRecyclerViewAdapter extends RecyclerView.Adapter<ProductCardViewHolder> {
    private List<Matches> matchesList;
    private Consumer<Matches> onClickCallback;

    ProductRecyclerViewAdapter(List<Matches> matchesList, Consumer<Matches> onClickCallback) {
        this.matchesList = matchesList;
        this.onClickCallback = onClickCallback;
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
            Picasso.get().load(match.getImageUrl()).into(holder.matchesImage);
            if(match.isLiked()){
                holder.likeButton.setImageResource(R.drawable.heart_icon_filled);
            } else {
                holder.likeButton.setImageResource(R.drawable.heart_icon);
            }
            holder.likeButton.setOnClickListener((v) -> {
                Toast.makeText(v.getContext(),
                        String.format(v.getContext().getString(R.string.liked_message),
                                match.getName()), Toast.LENGTH_LONG).show();
                onClickCallback.accept(match);
            });
        }
    }

    @Override
    public int getItemCount() {
        return matchesList.size();
    }
}
