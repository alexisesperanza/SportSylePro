package com.grupo8.sportsylepro.ui.tucuenta;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

public class TuCuentaViewModel extends ViewModel {

    private final MutableLiveData<String> mText;

    public TuCuentaViewModel() {
        mText = new MutableLiveData<>();
        mText.setValue("");
    }

    public LiveData<String> getText() {
        return mText;
    }
}