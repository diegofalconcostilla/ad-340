package com.tutorials.helloworld;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;
import java.util.List;

public class MatchesFragment extends Fragment {


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_matches, container, false);

        List<Matches> matchesList = new ArrayList<>();
        matchesList.add(new Matches("Dog Capone", "Beware of the Boss", false, R.drawable.dog_capone));
        matchesList.add(new Matches("Lazy Frankie", "Men in Black Holidays", true, R.drawable.lazy_frankie));
        matchesList.add(new Matches("Space Cat", "Purring in the moon", false, R.drawable.space_cat));

        RecyclerView recyclerView = view.findViewById(R.id.recycler_view);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new GridLayoutManager(getContext(), 2, GridLayoutManager.VERTICAL, false));
        ProductRecyclerViewAdapter adapter = new ProductRecyclerViewAdapter(matchesList);
        recyclerView.setAdapter(adapter);
        int largePadding = getResources().getDimensionPixelSize(R.dimen.grid_spacing);
        int smallPadding = getResources().getDimensionPixelSize(R.dimen.small_grid_spacing);
        recyclerView.addItemDecoration(new ProductItemDecoration(largePadding, smallPadding));

        return view;
    }
}