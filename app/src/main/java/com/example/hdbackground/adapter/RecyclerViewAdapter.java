package com.example.hdbackground.adapter;

import static com.bumptech.glide.load.DecodeFormat.PREFER_ARGB_8888;
import static com.bumptech.glide.load.resource.bitmap.BitmapTransitionOptions.withCrossFade;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.hdbackground.R;
import com.example.hdbackground.curated_model.Photos;

import java.util.ArrayList;

public class RecyclerViewAdapter extends RecyclerView.Adapter<RecyclerViewAdapter.ViewHolder> {

    private final Context context;
    private final ArrayList<Photos> photosArrayList;
    private final OnClickItemListener onClickItemListener;

    public RecyclerViewAdapter(Context context, ArrayList<Photos> photosArrayList, OnClickItemListener onClickItemListener) {
        this.context = context;
        this.photosArrayList = photosArrayList;
        this.onClickItemListener = onClickItemListener;
    }

    @NonNull
    @Override
    public RecyclerViewAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.image_list_item, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerViewAdapter.ViewHolder holder, int position) {
     //   holder.textView.setText(photosArrayList.get(position).getPhotographer());
     //   RequestBuilder<Bitmap> requestBuilder = Glide.with(context).asBitmap().load(photosArrayList.get(position).getSrc().getMedium());
        Glide.with(context)
                .load(photosArrayList.get(position).getSrc().getMedium())
                .format(PREFER_ARGB_8888)
                .placeholder(R.drawable.image_placeholder)
                .error(R.drawable.broken_image)
                .into(holder.imageView);

        holder.cardView.setOnClickListener(v -> {
            Photos photos = photosArrayList.get(position);
            onClickItemListener.onClickImage(photos);
        });
    }

    @Override
    public int getItemCount() {
        return photosArrayList.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        TextView textView;
        ImageView imageView;
        CardView cardView;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
         //   textView = itemView.findViewById(R.id.textView_imageOwner);
            imageView = itemView.findViewById(R.id.imageView_imageListItem);
            cardView = itemView.findViewById(R.id.cardView_photosList);
        }
    }
}
