package com.example.hdbackground;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.SearchView;
import androidx.appcompat.widget.Toolbar;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.example.hdbackground.adapter.OnClickItemListener;
import com.example.hdbackground.adapter.RecyclerViewAdapter;
import com.example.hdbackground.curated_model.Photos;
import com.example.hdbackground.curated_model.PixelApiResponse;
import com.example.hdbackground.curatedphotos.CuratedPhotos;
import com.example.hdbackground.curatedphotos.OnFetchCuratedPhotos;
import com.example.hdbackground.searchphotos.OnFetchSearchedPhotos;
import com.example.hdbackground.searchphotos.SearchPhotosModel;
import com.example.hdbackground.searchphotos.SearchPhotos;
import com.example.hdbackground.viewmodel.ShareViewModel;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;

public class HomeFragment extends Fragment implements OnClickItemListener {

    private static SearchView searchView;
    private Toolbar toolbar;
    private static RecyclerView recyclerView;
    private RecyclerViewAdapter recyclerViewAdapter;
    private ShareViewModel shareViewModel;
    private static FloatingActionButton fab_nextPage, fab_previousPage;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_home, container, false);

        toolbar = view.findViewById(R.id.home_fragment_toolbar);
        ((AppCompatActivity) requireActivity()).setSupportActionBar(toolbar);

        searchView = view.findViewById(R.id.home_fragment_searchView);
        recyclerView = view.findViewById(R.id.home_fragment_recyclerView);

        fab_previousPage = view.findViewById(R.id.fab_previousPage);
        fab_nextPage = view.findViewById(R.id.fab_nextPage);

        shareViewModel = new ViewModelProvider(requireActivity()).get(ShareViewModel.class);

        //Creating curated object
        CuratedPhotos curatedPhotos = new CuratedPhotos();
        curatedPhotos.getPixelImages(onFetchCuratedPhotosListener);

        //Navigate to next pae
        curatedPhotos.nextPageCuratedImages(onFetchCuratedPhotosListener);

        //Navigate to previous page
        curatedPhotos.prevPageCuratedPhotos(onFetchCuratedPhotosListener);

        //Search photos object
        SearchPhotos searchPhotos = new SearchPhotos();
        searchPhotos.setSearchView(listener);

        return view;
    }

    //Interface to fetch the list of curated photos
    private final OnFetchCuratedPhotos onFetchCuratedPhotosListener = new OnFetchCuratedPhotos() {
        @Override
        public void fetchCuratedPhotos(PixelApiResponse pixelApiResponse) {
            setRecyclerView(pixelApiResponse.getPhotos());

        }

        @Override
        public void onFailure(String message) {
            Toast.makeText(getContext(), "An error occurred!!", Toast.LENGTH_SHORT).show();
        }
    };

    //Interface to fetch searched photos
    private final OnFetchSearchedPhotos listener = searchApiResponse -> setRecyclerView(searchApiResponse.getPhotos());

    private void setRecyclerView(ArrayList<Photos> photosArrayList) {
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new GridLayoutManager(getContext(), 3));
        recyclerViewAdapter = new RecyclerViewAdapter(getContext(), photosArrayList, this);
        recyclerView.setAdapter(recyclerViewAdapter);
    }

    @Override
    public void onClickImage(Photos photos) {
        getParentFragmentManager().beginTransaction()
                .setReorderingAllowed(true)
                .replace(R.id.fragment_container, new DetailsFragment(getContext()), null)
                .addToBackStack(null)
                .commit();

        shareViewModel.selectedPhoto(photos);
    }

    //Create an instance of search view to be accessed by other classes
    public static SearchView getSearchView() {
        return searchView;
    }

    public static FloatingActionButton getNextFab() {
        return fab_nextPage;
    }

    public static FloatingActionButton getPrevFab() {
        return fab_previousPage;
    }

    public static RecyclerView getRecyclerView(){
        return recyclerView;
    }

}