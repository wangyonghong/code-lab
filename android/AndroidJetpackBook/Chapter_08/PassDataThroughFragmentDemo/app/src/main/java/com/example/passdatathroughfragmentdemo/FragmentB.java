package com.example.passdatathroughfragmentdemo;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.fragment.app.Fragment;
import androidx.navigation.Navigation;

public class FragmentB extends Fragment {

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_b, container, false);
        TextView textTv = view.findViewById(R.id.fragment_b_text_tv);
        // textTv.setText(getArguments().getString("text"));
        textTv.setText(FragmentBArgs.fromBundle(getArguments()).getText());
        view.findViewById(R.id.fragment_jump_to_prev_btn).setOnClickListener(v -> {
            Bundle bundle = new Bundle();
            bundle.putString("text", "这是来自FragmentB的文本");
            Navigation.findNavController(view).setGraph(R.navigation.pass_data_through_fragment, bundle);
            Navigation.findNavController(view).popBackStack();
        });
        return view;
    }
}