package com.example.hdbackground.viewmodel;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.hdbackground.curated_model.Photos;

public class ShareViewModel extends ViewModel {

    //Allows data to be edited
    private final MutableLiveData<Photos> selectedPhoto= new MutableLiveData<>();

    //The UI will be observing this live data to update the UI
    public LiveData<Photos> getSelectedPhoto() {
        return selectedPhoto;
    }

    public void selectedPhoto(Photos photos) {
        selectedPhoto.setValue(photos);
    }

}
