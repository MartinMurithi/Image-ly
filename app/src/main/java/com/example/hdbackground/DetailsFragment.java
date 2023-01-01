package com.example.hdbackground;

import static com.bumptech.glide.load.DecodeFormat.PREFER_ARGB_8888;
import static com.bumptech.glide.load.resource.bitmap.BitmapTransitionOptions.withCrossFade;

import android.app.DownloadManager;
import android.app.WallpaperManager;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.os.Parcelable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.widget.Toolbar;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

import com.bumptech.glide.Glide;
import com.bumptech.glide.RequestBuilder;
import com.bumptech.glide.request.target.CustomTarget;
import com.bumptech.glide.request.transition.Transition;
import com.example.hdbackground.curated_model.Photos;
import com.example.hdbackground.curatedphotos.CuratedPhotos;
import com.example.hdbackground.viewmodel.ShareViewModel;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.io.IOException;
import java.util.ArrayList;

public class DetailsFragment extends Fragment {

    private final Context context;
    private ImageView imageViewWallPaper;
    private Toolbar detailsFragmentToolbar;
    private FloatingActionButton fab_SetWallpaper, fab_downloadImage;
    private Photos photos;
    private ShareViewModel shareViewModel;
    private WallpaperManager wallpaperManager;
    private ProgressBar progressBar;
    private ImageButton imageButton;

    public DetailsFragment(Context context) {
        this.context = context;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_details, container, false);

        imageViewWallPaper = view.findViewById(R.id.imageView_detailImage);
        fab_SetWallpaper = view.findViewById(R.id.imageButton_setWallpaper);
        fab_downloadImage = view.findViewById(R.id.fab_downloadImage);
        progressBar = view.findViewById(R.id.detailsFragment_progressBar);
        imageButton = view.findViewById(R.id.image_btn_homeFragment);

        shareViewModel = new ViewModelProvider(requireActivity()).get(ShareViewModel.class);
        photos = shareViewModel.getSelectedPhoto().getValue();

        progressBar.setVisibility(View.VISIBLE);

        RequestBuilder<Bitmap> requestBuilder = Glide.with(context).asBitmap().load(photos.getSrc().getMedium());

        Glide.with(context)
                .asBitmap()
                .format(PREFER_ARGB_8888)
              //  .placeholder(R.drawable.image_placeholder)
                .thumbnail(requestBuilder)
                .load(photos.getSrc().getOriginal())
                .into(imageViewWallPaper);

        progressBar.setVisibility(View.GONE);

        setWallPaper();

        downLoadImage();

        return view;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        imageButton.setOnClickListener(v -> {
            Parcelable recyclerViewState;

            getParentFragmentManager().beginTransaction()
                    .setReorderingAllowed(true)
                    .replace(R.id.fragment_container, new HomeFragment(), "win")
                    .addToBackStack(null)
                    .commit();

            recyclerViewState = HomeFragment.getRecyclerView().getLayoutManager().onSaveInstanceState();

            //Restore state
            HomeFragment.getRecyclerView().getLayoutManager().onRestoreInstanceState(recyclerViewState);
        });
    }

    /*
                A URI provides a simple, extensible way to identify internet resources. Thanks to the uniformity that URIs provide,
                 different types of resource identifiers can be used in the same context, regardless of the mechanisms used to access
                 those resources. The resource identifiers can also be reused in different contexts
        */
    private void downLoadImage() {
        fab_downloadImage.setOnClickListener(v -> {
            DownloadManager downloadManager = (DownloadManager) context.getSystemService(Context.DOWNLOAD_SERVICE);

            Uri uri = Uri.parse(photos.getSrc().getLarge());

            DownloadManager.Request request = new DownloadManager.Request(uri);
            request.setAllowedNetworkTypes(DownloadManager.Request.NETWORK_WIFI | DownloadManager.Request.NETWORK_MOBILE).setAllowedOverRoaming(false).setTitle("Wallpaper " + photos.getPhotographer()).setMimeType("image/jpeg").setNotificationVisibility(DownloadManager.Request.VISIBILITY_VISIBLE_NOTIFY_COMPLETED).setDestinationInExternalPublicDir(Environment.DIRECTORY_DOWNLOADS, "Wallpaper " + photos.getPhotographer() + " .jpg");

            downloadManager.enqueue(request);
            Toast.makeText(context, "Download completed", Toast.LENGTH_SHORT).show();
        });

    }

    private void setWallPaper() {
        wallpaperManager = WallpaperManager.getInstance(context);
        fab_SetWallpaper.setOnClickListener(v -> Glide.with(context).asBitmap().load(photos.getSrc().getLarge()).format(PREFER_ARGB_8888).centerCrop().into(new CustomTarget<Bitmap>() {
            @Override
            public void onResourceReady(@NonNull Bitmap resource, @Nullable Transition<? super Bitmap> transition) {
                try {
                    wallpaperManager.setBitmap(resource);
                    Toast.makeText(context, "Wallpaper applied", Toast.LENGTH_SHORT).show();
                } catch (IOException e) {
                    Toast.makeText(context, "Couldn't apply Wallpaper", Toast.LENGTH_SHORT).show();
                    e.printStackTrace();
                }
            }

            @Override
            public void onLoadCleared(@Nullable Drawable placeholder) {

            }
        }));
    }
}