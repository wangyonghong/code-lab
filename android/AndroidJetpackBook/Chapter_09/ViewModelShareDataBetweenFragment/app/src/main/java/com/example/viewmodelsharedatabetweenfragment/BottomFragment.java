package com.example.viewmodelsharedatabetweenfragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

public class BottomFragment extends Fragment {

    private TextView textContentTv;
    private Button refreshUIBtn;
    private TextViewModel textViewModel;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        super.onCreateView(inflater, container, savedInstanceState);
        View view = inflater.inflate(R.layout.fragment_bottom, container);
        textContentTv = view.findViewById(R.id.fragment_bottom_tv);
        refreshUIBtn = view.findViewById(R.id.fragment_bottom_btn);
        textViewModel = new ViewModelProvider(requireActivity(), new ViewModelProvider.NewInstanceFactory()).get(TextViewModel.class);
        refreshUIBtn.setOnClickListener(v -> {
            textContentTv.setText(textViewModel.getTextContent());
        });
        return view;
    }
}
