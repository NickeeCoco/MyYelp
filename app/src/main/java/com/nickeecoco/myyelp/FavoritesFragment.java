package com.nickeecoco.myyelp;

import android.database.Cursor;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;

public class FavoritesFragment extends Fragment {
    private final String DB_NAME = "Restaurants Database";
    private final String TABLE_NAME = "FAVORITES";

    RecyclerView recyclerView;
    FavoritesAdapter adapter;
    ArrayList<Favorite> favorites = new ArrayList<>();
    FavoritesDatabaseHelper db_helper;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        db_helper = new FavoritesDatabaseHelper(getContext(), DB_NAME, null, 1);

        favorites.clear();
        Cursor cursor = db_helper.getWritableDatabase().rawQuery("SELECT * FROM FAVORITES", null);
        if(cursor.moveToFirst()) {
            favorites.add(new Favorite(
                    cursor.getString(cursor.getColumnIndexOrThrow("NAME")),
                    cursor.getString(cursor.getColumnIndexOrThrow("IMAGE")),
                    cursor.getString(cursor.getColumnIndexOrThrow("CATEGORIES")),
                    cursor.getDouble(cursor.getColumnIndexOrThrow("RATING")),
                    cursor.getString(cursor.getColumnIndexOrThrow("PRICE")),
                    cursor.getString(cursor.getColumnIndexOrThrow("TELEPHONE")),
                    cursor.getString(cursor.getColumnIndexOrThrow("ADDRESS"))));
            while(cursor.moveToNext()) {
                favorites.add(new Favorite(
                        cursor.getString(cursor.getColumnIndexOrThrow("NAME")),
                        cursor.getString(cursor.getColumnIndexOrThrow("IMAGE")),
                        cursor.getString(cursor.getColumnIndexOrThrow("CATEGORIES")),
                        cursor.getDouble(cursor.getColumnIndexOrThrow("RATING")),
                        cursor.getString(cursor.getColumnIndexOrThrow("PRICE")),
                        cursor.getString(cursor.getColumnIndexOrThrow("TELEPHONE")),
                        cursor.getString(cursor.getColumnIndexOrThrow("ADDRESS"))));
            }
        }

        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_favorites, container, false);
        recyclerView = view.findViewById(R.id.favorites);
        recyclerView.setLayoutManager(new LinearLayoutManager(inflater.getContext()));
        recyclerView.addItemDecoration(new DividerItemDecoration(recyclerView.getContext(), DividerItemDecoration.VERTICAL));
        adapter = new FavoritesAdapter(inflater.getContext(), favorites);
        recyclerView.setAdapter(adapter);

        return view;
    }
}