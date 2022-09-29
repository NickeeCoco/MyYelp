package com.nickeecoco.myyelp;

import android.content.Context;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.SearchView;
import android.widget.Spinner;

import java.util.ArrayList;
import java.util.Collections;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class SearchFragment extends Fragment {

    RecyclerView recyclerView;
    SearchResultsAdapter adapter;
    SearchView searchView;
    Context context;
    Spinner sortBy;
    ArrayList<YelpResponse.Business> restaurants = new ArrayList<>();
    String sorting = "rating";

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        context = inflater.getContext();

        View view = inflater.inflate(R.layout.fragment_search, container, false);
        recyclerView = view.findViewById(R.id.searchResults);
        recyclerView.setLayoutManager(new LinearLayoutManager(context));
        recyclerView.addItemDecoration(new DividerItemDecoration(recyclerView.getContext(), DividerItemDecoration.VERTICAL));
        adapter = new SearchResultsAdapter(context, restaurants);
        recyclerView.setAdapter(adapter);

        getDataFromAPI("", sorting);

        // manage search
        searchView = view.findViewById(R.id.search);
        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String s) {
                getDataFromAPI(s, sorting);
                return false;
            }

            @Override
            public boolean onQueryTextChange(String s) {
                return false;
            }
        });

        // manage sort
        sortBy = view.findViewById(R.id.sortBy);
        sortBy.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                if(i == 0) {
                    sorting = "rating";
                    sortByRating();
                } else if(i == 1) {
                    sorting = "price";
                    sortByPrice();
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });

        return view;
    }

    public void getDataFromAPI(String query, String sorting) {
        YelpAPI yelpAPI = new YelpClient().create();
        Call<YelpResponse> call = yelpAPI.getSearchResults("Montreal", query);

        Callback<YelpResponse> listener = new Callback<YelpResponse>() {
            @Override
            public void onResponse(Call<YelpResponse> call, Response<YelpResponse> response) {
                YelpResponse res = response.body();
                restaurants.clear();
                restaurants.addAll(res.businesses);

                if(sorting.equals("rating")) {
                    sortByRating();
                }

                if(sorting.equals("price")) {
                    sortByPrice();
                }
            }

            @Override
            public void onFailure(Call<YelpResponse> call, Throwable t) {

            }
        };

        call.enqueue(listener);
    }

    public void sortByRating() {
        Collections.sort(restaurants, (restaurant1, restaurant2) -> {
            if(restaurant1.rating < restaurant2.rating) {
                return 1;
            } else if (restaurant1.rating > restaurant2.rating) {
                return -1;
            } else {
                return 0;
            }
        });
        adapter = new SearchResultsAdapter(context, restaurants);
        recyclerView.setAdapter(adapter);

    }

    public void sortByPrice() {
        Collections.sort(restaurants, (restaurant1, restaurant2) -> {
            if(restaurant1.price == null) {
                if(restaurant2.price == null) {
                    return 0;
                }  else {
                    return -1;
                }
            } else if(restaurant2.price == null) {
                return 1;
            } else if(restaurant1.price.length() > (restaurant2).price.length()) {
                return 1;
            } else if (restaurant1.price.length() < restaurant2.price.length()) {
                return -1;
            } else {
                return 0;
            }
        });

        adapter = new SearchResultsAdapter(context, restaurants);
        recyclerView.setAdapter(adapter);
    }

}