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
    private MatchesViewModel vm;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_matches, container, false);

        List<Matches> matchesList = new ArrayList<>();
        vm = new MatchesViewModel();

        RecyclerView recyclerView = view.findViewById(R.id.recycler_view);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new GridLayoutManager(getContext(), 2, GridLayoutManager.VERTICAL, false));
        ProductRecyclerViewAdapter adapter = new ProductRecyclerViewAdapter(matchesList, (match) -> {
            match.setLiked(!match.isLiked());
            vm.updateMatch(match);
        });
        recyclerView.setAdapter(adapter);
        int largePadding = getResources().getDimensionPixelSize(R.dimen.grid_spacing);
        int smallPadding = getResources().getDimensionPixelSize(R.dimen.small_grid_spacing);
        recyclerView.addItemDecoration(new ProductItemDecoration(largePadding, smallPadding));

        vm.getMatches(matches -> {
            matchesList.clear();
            matchesList.addAll(matches);
            adapter.notifyDataSetChanged();
        });

        return view;
    }

    @Override
    public void onPause() {
        super.onPause();
        vm.clear();
    }
}