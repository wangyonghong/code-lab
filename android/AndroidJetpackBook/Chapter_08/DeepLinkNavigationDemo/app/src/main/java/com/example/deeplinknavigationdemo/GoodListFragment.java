package com.example.deeplinknavigationdemo;

import android.app.PendingIntent;
import android.content.Intent;
import android.net.Uri;
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
        view.findViewById(R.id.good_list_fragment_text_tv).setOnClickListener(v -> {
            // 启动显式深层链接
            try {
                Navigation.findNavController(view).createDeepLink()
                        .setDestination(R.id.paymentPayFragment).createPendingIntent().send();
            } catch (PendingIntent.CanceledException e) {
                e.printStackTrace();
            }
            // 启动隐式深层链接（请复制下面的代码到另外的App中或另一个Fragment中执行）
//            Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse("dlnd://www.deeplinknavigationdemo.com/gooddetail"));
//            intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
//            startActivity(intent);
        });
        return view;
    }
}