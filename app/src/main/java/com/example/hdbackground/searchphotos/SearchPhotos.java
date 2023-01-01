package com.example.hdbackground.searchphotos;

import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.SearchView;

import com.example.hdbackground.HomeFragment;
import com.example.hdbackground.services.RetrofitInstance;
import com.example.hdbackground.services.SearchPixelApi;

import java.io.Serializable;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class SearchPhotos extends AppCompatActivity implements Serializable {
    private int page;
    private Call<SearchPhotosModel> searchApiResponseCall;
    private SearchPixelApi searchPixelApi;

    public void setSearchView(OnFetchSearchedPhotos searchListener) {
        HomeFragment.getSearchView().setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                HomeFragment.getNextFab().hide();
                HomeFragment.getPrevFab().hide();
                searchPixelApi = RetrofitInstance.getRetrofit().create(SearchPixelApi.class);
                searchApiResponseCall = searchPixelApi.searchPixelPhotos(query, page, 78);
                searchApiResponseCall.enqueue(new Callback<SearchPhotosModel>() {
                    @Override
                    public void onResponse(@NonNull Call<SearchPhotosModel> call, @NonNull Response<SearchPhotosModel> response) {
                        if (response.body() != null) {
                            searchListener.fetchSearchedPhotos(response.body());
                        } else {
                            Toast.makeText(SearchPhotos.this, "An error occurred!!", Toast.LENGTH_SHORT).show();
                        }

                    }

                    @Override
                    public void onFailure(@NonNull Call<SearchPhotosModel> call, @NonNull Throwable t) {
                        t.getStackTrace();
                    }
                });
                return true;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                return false;
            }
        });
    }

  /*  public void nextPageSearchedPhotos(OnFetchSearchedPhotos listener) {
        HomeFragment.getNextFab().setOnClickListener(v -> {
            page++;
            searchPixelApi = RetrofitInstance.getRetrofit().create(SearchPixelApi.class);
            searchApiResponseCall = searchPixelApi.searchPixelPhotos(null, page, 78);
            searchApiResponseCall.enqueue(new Callback<SearchPhotosModel>() {
                @Override
                public void onResponse(@NonNull Call<SearchPhotosModel> call, @NonNull Response<SearchPhotosModel> response) {
                    if (response.body() != null) {
                        listener.fetchSearchedPhotos(response.body());
                    } else {
                        Toast.makeText(SearchPhotos.this, "An error occurred!!", Toast.LENGTH_SHORT).show();
                    }
                }

                @Override
                public void onFailure(Call<SearchPhotosModel> call, Throwable t) {

                }

            });
        });
    }

    public void prevPageSearchedPhotos(OnFetchSearchedPhotos listener) {
        HomeFragment.getPrevFab().setOnClickListener(v -> {
            page--;
            searchPixelApi = RetrofitInstance.getRetrofit().create(SearchPixelApi.class);
            searchApiResponseCall = searchPixelApi.searchPixelPhotos(null, page, 78);
            searchApiResponseCall.enqueue(new Callback<SearchPhotosModel>() {
                @Override
                public void onResponse(@NonNull Call<SearchPhotosModel> call, @NonNull Response<SearchPhotosModel> response) {
                    if (response.body() != null) {
                        listener.fetchSearchedPhotos(response.body());
                    } else {
                        Toast.makeText(SearchPhotos.this, "An error occurred!!", Toast.LENGTH_SHORT).show();
                    }
                }

                @Override
                public void onFailure(Call<SearchPhotosModel> call, Throwable t) {

                }

            });
        });
    }*/
}
