package com.nickeecoco.myyelp;

import android.content.ContentValues;
import android.content.Context;
import android.content.DialogInterface;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RatingBar;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;

import java.util.ArrayList;

public class SearchResultsAdapter extends RecyclerView.Adapter<SearchResultsAdapter.ViewHolder> {
    private final String DB_NAME = "Restaurants Database";
    private final String TABLE_NAME = "FAVORITES";

    ArrayList<YelpResponse.Business> mData;
    LayoutInflater mInflater;
    FavoritesDatabaseHelper db_helper;

    SearchResultsAdapter(Context context, ArrayList<YelpResponse.Business> data) {
        this.mInflater = LayoutInflater.from(context);
        this.mData = data;
    }


    @NonNull
    @Override
    public SearchResultsAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = mInflater.inflate(R.layout.restaurant_card, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        YelpResponse.Business currentRestaurant = mData.get(position);

        String name = currentRestaurant.name;
        StringBuilder displayName = new StringBuilder();
        displayName.append(position + 1)
            .append(" ")
            .append(name);

        double rating = currentRestaurant.rating;

        String price = currentRestaurant.price;

        ArrayList<YelpResponse.Category> categories = currentRestaurant.categories;
        StringBuilder categoriesString = new StringBuilder();

        for (YelpResponse.Category category : categories) {
            categoriesString.append(category.category).append(", ");
        }

        // remove last coma and space
        categoriesString = new StringBuilder(categoriesString.substring(0, (categoriesString.length() - 2)));

        String telephone = currentRestaurant.phone;

        String address = currentRestaurant.location.address;
        String city = currentRestaurant.location.city;
        String state = currentRestaurant.location.state;

        StringBuilder fullAddress = new StringBuilder();
        fullAddress.append(address)
                .append(", ")
                .append(city)
                .append(", ")
                .append(state);

        String imgURL = currentRestaurant.imageURL;

        holder.tv_name.setText(displayName);
        holder.rb_rating.setRating((float) rating);
        holder.tv_price.setText(price);
        holder.tv_categories.setText(categoriesString);
        holder.tv_telephone.setText(telephone);
        holder.tv_address.setText(fullAddress);
        Glide.with(holder.itemView.getContext())
                .load(imgURL)
                .into(holder.iv_image);

        // click
        String finalCategoriesString = String.valueOf(categoriesString);
        holder.restaurantCard.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                AlertDialog.Builder builder = new AlertDialog.Builder(view.getContext())
                        .setTitle("Add to favorite?")
                        .setMessage("Do you want to add this item to your favorites?")
                        .setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int i) {
                                db_helper = new FavoritesDatabaseHelper(mInflater.getContext(), DB_NAME, null, 1);
                                ContentValues favorite = new ContentValues();
                                favorite.put("NAME", name);
                                favorite.put("RATING", rating);
                                favorite.put("PRICE", price);
                                favorite.put("CATEGORIES", finalCategoriesString);
                                favorite.put("TELEPHONE", telephone);
                                favorite.put("ADDRESS", String.valueOf(fullAddress));
                                favorite.put("IMAGE", imgURL);
                                db_helper.getWritableDatabase().insert(TABLE_NAME, null, favorite);

                            }
                        })
                        .setNegativeButton("No", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int i) {

                            }
                        });

                builder.create().show();
            }
        });
    }

    @Override
    public int getItemCount() {
        return mData.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView tv_name;
        RatingBar rb_rating;
        TextView tv_price;
        TextView tv_categories;
        TextView tv_telephone;
        TextView tv_address;
        ImageView iv_image;
        LinearLayout restaurantCard;

        ViewHolder(View itemView) {
            super(itemView);
            tv_name = itemView.findViewById(R.id.tv_name);
            rb_rating = itemView.findViewById(R.id.rb_rating);
            tv_price = itemView.findViewById(R.id.tv_price);
            tv_categories = itemView.findViewById(R.id.tv_categories);
            tv_telephone = itemView.findViewById(R.id.tv_telephone);
            tv_address = itemView.findViewById(R.id.tv_address);
            iv_image = itemView.findViewById(R.id.iv_image);
            restaurantCard = itemView.findViewById(R.id.restaurantCard);
        }
    }

}
