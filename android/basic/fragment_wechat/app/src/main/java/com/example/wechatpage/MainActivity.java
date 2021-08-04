package com.example.wechatpage;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.viewpager2.widget.ViewPager2;

import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity implements View.OnClickListener{
    ViewPager2 viewPager;
    private LinearLayout llChat, llContacts,llFind, llProfile;
    private ImageView ivChat, ivContacts, ivFind, ivProfile, ivCurrent;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initPager();
        initTabView();
    }

    private void initTabView() {
        llChat = findViewById(R.id.id_tab_weixin);
        llChat.setOnClickListener(this);
        llContacts = findViewById(R.id.id_tab_contact);
        llContacts.setOnClickListener(this);
        llFind = findViewById(R.id.id_tab_find);
        llFind.setOnClickListener(this);
        llProfile = findViewById(R.id.id_tab_profile);
        llProfile.setOnClickListener(this);
        ivChat = findViewById(R.id.tab_iv_weixin);
        ivContacts = findViewById(R.id.tab_iv_contact);
        ivFind = findViewById(R.id.tab_iv_find);
        ivProfile = findViewById(R.id.tab_iv_profile);

        ivChat.setSelected(true);
        ivCurrent = ivChat;

    }

    private void initPager() {
        viewPager = findViewById(R.id.id_viewpager);
        ArrayList<Fragment> fragments = new ArrayList<>();
        fragments.add(BlankFragment.newInstance("微信聊天"));
        fragments.add(BlankFragment.newInstance("通讯录"));
        fragments.add(BlankFragment.newInstance("发现"));
        fragments.add(BlankFragment.newInstance("我"));
        MyFragmentPagerAdapter pagerAdapter = new MyFragmentPagerAdapter(getSupportFragmentManager(), getLifecycle(),fragments);
        viewPager.setAdapter(pagerAdapter);
        viewPager.registerOnPageChangeCallback(new ViewPager2.OnPageChangeCallback() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
                super.onPageScrolled(position, positionOffset, positionOffsetPixels);
            }

            @Override
            public void onPageSelected(int position) {
                super.onPageSelected(position);
                changeTab(position);
            }

            @Override
            public void onPageScrollStateChanged(int state) {
                super.onPageScrollStateChanged(state);
            }
        });
    }

    private void changeTab(int position) {
        ivCurrent.setSelected(false);
        switch (position){
            case R.id.id_tab_weixin:
                viewPager.setCurrentItem(0);
            case 0:
                ivChat.setSelected(true);
                ivCurrent = ivChat;
                break;
            case R.id.id_tab_contact:
                viewPager.setCurrentItem(1);
            case 1:
                ivContacts.setSelected(true);
                ivCurrent = ivContacts;
                break;
            case R.id.id_tab_find:
                viewPager.setCurrentItem(2);
            case 2:
                ivFind.setSelected(true);
                ivCurrent = ivFind;
                break;
            case R.id.id_tab_profile:
                viewPager.setCurrentItem(3);
            case 3:
                ivProfile.setSelected(true);
                ivCurrent = ivProfile;
                break;

        }
    }

    @Override
    public void onClick(View view) {
        changeTab(view.getId());
    }
}