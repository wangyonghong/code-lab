package com.example.databindingdemo;

import androidx.databinding.BaseObservable;
import androidx.databinding.Bindable;

public class CustomObservableObject extends BaseObservable {

    private String dataValue_1;
    private String dataValue_2;

    @Bindable
    public String getDataValue_1() {
        return dataValue_1;
    }

    public String getDataValue_2() {
        return dataValue_2;
    }

    public void setDataValue_1(String dataValue_1) {
        this.dataValue_1 = dataValue_1;
        notifyPropertyChanged(BR.dataValue_1);
    }

    public void setDataValue_2(String dataValue_2) {
        this.dataValue_2 = dataValue_2;
    }
}
