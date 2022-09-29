package com.nickeecoco.myyelp;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RatingBar;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;

import java.util.ArrayList;

public class FavoritesAdapter extends RecyclerView.Adapter<FavoritesAdapter.ViewHolder> {
    ArrayList<Favorite> mData;
    LayoutInflater mInflater;

    FavoritesAdapter(Context context, ArrayList<Favorite> data) {
        this.mInflater = LayoutInflater.from(context);
        this.mData = data;
    }

    @NonNull
    @Override
    public FavoritesAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = mInflater.inflate(R.layout.restaurant_card, parent, false);
        return new FavoritesAdapter.ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull FavoritesAdapter.ViewHolder holder, int position) {
        Favorite currentFavorite = mData.get(position);

        String name = currentFavorite.name;
        StringBuilder displayName = new StringBuilder();
        displayName.append(position + 1);
        displayName.append(" ");
        displayName.append(name);

        String imgURL = currentFavorite.imageURL;
        String categories = currentFavorite.categories;
        double rating = currentFavorite.rating;
        String price = currentFavorite.price;
        String phone = currentFavorite.phone;
        String address = currentFavorite.address;

        holder.tv_name.setText(displayName);
        Glide.with(holder.itemView.getContext())
                .load(imgURL)
                .into(holder.iv_image);
        holder.tv_categories.setText(categories);
        holder.rb_rating.setRating((float) rating);
        holder.tv_price.setText(price);
        holder.tv_telephone.setText(phone);
        holder.tv_address.setText(address);
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
