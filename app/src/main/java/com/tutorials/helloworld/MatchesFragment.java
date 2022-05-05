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
        matchesList.add(new Matches("Test 1", "Test person 1", false, "https://i.imgur.com/kobQVOD.jpg"));
        matchesList.add(new Matches("Test 2", "Test person 2", true, "https://i.imgur.com/fF3Iiih.jpg"));
        matchesList.add(new Matches("Test 3", "Test person 3", false, "https://i.imgur.com/z4OKVlA.jpg"));

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