package com.example.proyecto.ui.gallery;

import android.widget.EditText;
import android.widget.TextView;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.proyecto.R;

public class GalleryViewModel extends ViewModel {

    private MutableLiveData<String> mText;
    public GalleryViewModel() {
        mText = new MutableLiveData<>();
        mText.setValue("");
    }

    public LiveData<String> getText() {
        return mText;
    }
}