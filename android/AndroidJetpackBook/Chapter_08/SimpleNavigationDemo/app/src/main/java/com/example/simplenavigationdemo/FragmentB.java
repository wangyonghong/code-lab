package com.example.simplenavigationdemo;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.fragment.app.Fragment;
import androidx.navigation.Navigation;

public class FragmentB extends Fragment {

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_b, container, false);
        view.findViewById(R.id.fragment_jump_to_next_btn).setOnClickListener(v ->
                Navigation.findNavController(view).navigate(R.id.fragmentC)
        );

        return view;
    }
}