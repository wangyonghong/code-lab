package com.example.viewmodelwithdatabindingdemo;

import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.widget.EditText;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;
import androidx.lifecycle.ViewModelProvider;

import com.example.viewmodelwithdatabindingdemo.databinding.ActivityPhoneNumInputBinding;

public class PhoneNumInputActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ActivityPhoneNumInputBinding binding = DataBindingUtil.setContentView(this, R.layout.activity_phone_num_input);
        EditText phoneInputEt = findViewById(R.id.activity_phone_num_input_et);

        PhoneViewModel phoneViewModel = new ViewModelProvider(this, new ViewModelProvider.NewInstanceFactory()).get(PhoneViewModel.class);
        binding.setPhoneViewModel(phoneViewModel);
        phoneInputEt.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
//                phoneViewModel.notifyChange();
                phoneViewModel.notifyPropertyChanged(BR.phoneNum);
            }
        });
    }
}