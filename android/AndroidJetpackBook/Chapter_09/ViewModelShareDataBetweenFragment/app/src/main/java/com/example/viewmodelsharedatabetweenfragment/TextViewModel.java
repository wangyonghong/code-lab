package com.example.viewmodelsharedatabetweenfragment;

import androidx.lifecycle.ViewModel;

public class TextViewModel extends ViewModel {

    private String textContent;

    public String getTextContent() {
        return textContent;
    }

    public void setTextContent(String textContent) {
        this.textContent = textContent;
    }
}
