package com.example.passdatathroughfragmentdemo;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.fragment.app.Fragment;
import androidx.navigation.Navigation;

public class FragmentA extends Fragment {
    private TextView textTv;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_a, container, false);
        textTv = view.findViewById(R.id.fragment_a_text_tv);

        // 未使用Safe Args进行跳转传值
        // Bundle bundle = new Bundle();
        // bundle.putString("text", "这是来自FragmentA的文本");
        // view.findViewById(R.id.fragment_jump_to_next_btn).setOnClickListener(v -> Navigation.findNavController(view).navigate(R.id.fragmentB, bundle));

        // 使用Safe Args进行跳转传值
        FragmentADirections.ActionFragmentAToFragmentB actionFragmentAToFragmentB = FragmentADirections.actionFragmentAToFragmentB();
        actionFragmentAToFragmentB.setText("这是来自FragmentA的文本");
        view.findViewById(R.id.fragment_jump_to_next_btn).setOnClickListener(v -> Navigation.findNavController(view).navigate(actionFragmentAToFragmentB));

        // 未使用Safe Args接收值
        // textTv.setText(getArguments().getString("text"));

        // 使用Safe Args接收值
        textTv.setText(FragmentAArgs.fromBundle(getArguments()).getText());

        return view;
    }

}