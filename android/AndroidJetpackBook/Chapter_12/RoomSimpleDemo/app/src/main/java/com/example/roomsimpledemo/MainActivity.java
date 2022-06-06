package com.example.roomsimpledemo;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ListView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.room.Room;

import com.example.roomsimpledemo.db.PersonDatabase;
import com.example.roomsimpledemo.entity.PersonEntity;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private ListView personEntitiesLv;
    private PersonListAdapter personListAdapter;
    private Button addNewBtn, refreshBtn;

    private List<PersonEntity> personEntityList;
    private PersonDatabase personDatabase;

    private final String[] personNames = {"Adam", "Bob", "Cindy", "David", "Eric", "Frost", "Grace"};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        findView();
        initData();
        setListener();
    }

    private void setListener() {
        addNewBtn.setOnClickListener(v -> {
            new Thread(() -> {
                personDatabase.personDao().insertAll(new PersonEntity(genRandomName(), genRandomAge(), genRandomGender()));
                refreshData();
            }).start();
        });
        refreshBtn.setOnClickListener(v -> new Thread(this::refreshData).start());
    }

    private void initData() {
        personListAdapter = new PersonListAdapter();
        personEntitiesLv.setAdapter(personListAdapter);
        personDatabase = Room.databaseBuilder(getApplicationContext(), PersonDatabase.class, "room_simple_demo").build();
        new Thread(this::refreshData).start();
    }

    private void findView() {
        personEntitiesLv = findViewById(R.id.activity_main_data_ll);
        addNewBtn = findViewById(R.id.activity_main_add_btn);
        refreshBtn = findViewById(R.id.activity_main_refresh_btn);
    }

    private void refreshData() {
        if (personEntityList == null) {
            personEntityList = new ArrayList<>();
        } else {
            personEntityList.clear();
        }
        personEntityList.addAll(personDatabase.personDao().getAll());
        runOnUiThread(() -> personListAdapter.notifyDataSetChanged());
    }

    private void deleteItem(int position) {
        personDatabase.personDao().delete(personEntityList.get(position));
        refreshData();
    }

    private String genRandomName() {
        return personNames[(int) (Math.random() * 7)];

    }

    private int genRandomAge() {
        return (int) (Math.random() * 80) + 1;
    }

    private int genRandomGender() {
        int randomInt = (int) (Math.random() * 100);
        return randomInt % 2;
    }

    class PersonListAdapter extends BaseAdapter {

        @Override
        public int getCount() {
            if (personEntityList != null) {
                return personEntityList.size();
            } else {
                return 0;
            }
        }

        @Override
        public Object getItem(int position) {
            if (personEntityList != null) {
                return personEntityList.get(position);
            } else {
                return null;
            }
        }

        @Override
        public long getItemId(int position) {
            return position;
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            ViewHolder viewHolder;
            if (convertView == null) {
                viewHolder = new ViewHolder();
                convertView = LayoutInflater.from(MainActivity.this).inflate(R.layout.person_item, null);
                viewHolder.nameTv = convertView.findViewById(R.id.person_item_name_tv);
                viewHolder.ageTv = convertView.findViewById(R.id.person_item_age_tv);
                viewHolder.genderTv = convertView.findViewById(R.id.person_item_gender_tv);
                viewHolder.deleteIb = convertView.findViewById(R.id.person_item_delete_ib);
                convertView.setTag(viewHolder);
            } else {
                viewHolder = (ViewHolder) convertView.getTag();
            }

            viewHolder.nameTv.setText(personEntityList.get(position).name);
            viewHolder.ageTv.setText(String.valueOf(personEntityList.get(position).age));
            if (personEntityList.get(position).gender == PersonEntity.GENDER_MALE) {
                viewHolder.genderTv.setText(R.string.common_male);
            } else {
                viewHolder.genderTv.setText(R.string.common_female);
            }
            viewHolder.deleteIb.setOnClickListener(v -> new Thread(() -> deleteItem(position)).start());
            return convertView;
        }

        class ViewHolder {
            private TextView nameTv;
            private TextView ageTv;
            private TextView genderTv;
            private ImageButton deleteIb;
        }
    }
}