package com.example.nestednavigationgraphdemo;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.fragment.app.Fragment;
import androidx.navigation.Navigation;

public class GoodListFragment extends Fragment {

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_good_list, container, false);
        view.findViewById(R.id.good_list_fragment_text_tv).setOnClickListener(v -> Navigation.findNavController(view).navigate(R.id.messageCenterFragment));
        return view;
    }
}