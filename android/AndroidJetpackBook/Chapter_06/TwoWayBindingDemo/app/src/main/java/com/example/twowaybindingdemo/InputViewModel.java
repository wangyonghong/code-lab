package com.example.twowaybindingdemo;

import androidx.databinding.BaseObservable;
import androidx.databinding.Bindable;

public class InputViewModel extends BaseObservable {

    private String inputText;

    @Bindable
    public String getInput() {
        return inputText;
    }

    public void setInput(String inputStr) {
        if (!inputStr.equals(inputText)) {
            inputText = inputStr;
            notifyPropertyChanged(BR.input);
        }
    }

}
