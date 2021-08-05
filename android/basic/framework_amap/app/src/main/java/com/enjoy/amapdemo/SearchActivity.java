package com.enjoy.amapdemo;

import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.EditText;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.amap.api.maps.model.LatLng;
import com.amap.api.maps.model.Poi;
import com.amap.api.navi.AMapNavi;
import com.amap.api.navi.AmapNaviPage;
import com.amap.api.navi.AmapNaviParams;
import com.amap.api.navi.AmapNaviType;
import com.amap.api.navi.AmapPageType;
import com.amap.api.services.core.LatLonPoint;
import com.amap.api.services.help.Inputtips;
import com.amap.api.services.help.InputtipsQuery;
import com.amap.api.services.help.Tip;

import java.util.ArrayList;
import java.util.List;

public class SearchActivity extends AppCompatActivity implements Inputtips.InputtipsListener, TextWatcher, RvAdapter.OnItemClickListener {

    private EditText editText;
    private Inputtips inputTips;
    private RecyclerView recyclerView;
    private RvAdapter rvAdapter;
    private AMapNavi mAMapNavi;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search);
        editText = findViewById(R.id.edit_query);
        editText.addTextChangedListener(this);

        recyclerView = findViewById(R.id.recyclerView);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        rvAdapter = new RvAdapter(this, recyclerView, new ArrayList<>());
        rvAdapter.setOnItemClickListener(this);
        recyclerView.setAdapter(rvAdapter);

        inputTips = new Inputtips(this, (InputtipsQuery) null);
        inputTips.setInputtipsListener(this);

        mAMapNavi = AMapNavi.getInstance(this);
        //设置内置语音播报
        mAMapNavi.setUseInnerVoice(true, false);
    }

    @Override
    public void onGetInputtips(List<Tip> list, int i) {
        rvAdapter.setData(list);
    }

    @Override
    public void beforeTextChanged(CharSequence s, int start, int count, int after) {

    }

    @Override
    public void onTextChanged(CharSequence s, int start, int before, int count) {
        InputtipsQuery inputquery = new InputtipsQuery(String.valueOf(s), "0731");
        inputquery.setCityLimit(true);//限制在当前城市
        inputTips.setQuery(inputquery);
        inputTips.requestInputtipsAsyn();
    }

    @Override
    public void afterTextChanged(Editable s) {

    }


    @Override
    public void onItemClick(RecyclerView parent, View view, int position, Tip data) {
        LatLonPoint point = data.getPoint();
        Poi poi = new Poi(data.getName(), new LatLng(point.getLatitude(), point.getLongitude()), data.getPoiID());
        AmapNaviParams params = new AmapNaviParams(null, null, poi, AmapNaviType.DRIVER,
                AmapPageType.ROUTE);
        AmapNaviPage.getInstance().showRouteActivity(getApplicationContext(), params, null);
    }
}
