package com.example.appcompatdialogdemo;

import android.os.Bundle;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    private String selectItems[] = {"菜单项1", "菜单项2", "菜单项3", "菜单项4", "菜单项5"};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        findViewById(R.id.activity_main_simple_dialog_btn).setOnClickListener(v -> new SimpleDialogFragment(new SimpleDialogFragment.OnButtonClicked() {
            @Override
            public void onPositiveButtonClicked() {
                Toast.makeText(MainActivity.this, "确定", Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onNegativeButtonClicked() {

            }
        }).show(getSupportFragmentManager(), "SimpleDialog"));
        findViewById(R.id.activity_main_single_select_list_dialog_btn).setOnClickListener(v ->
                new SingleSelectListDialogFragment(index ->
                        Toast.makeText(MainActivity.this,
                                "选择了" + selectItems[index], Toast.LENGTH_SHORT).show(),
                        selectItems).show(getSupportFragmentManager(), "SingleSelectDialog"));
        findViewById(R.id.activity_main_single_select_with_confirm_list_dialog_btn).setOnClickListener(v ->
                new SingleSelectWithConfirmListDialogFragment(new SingleSelectWithConfirmListDialogFragment.OnItemSelected() {
                    @Override
                    public void onItemSelected(int index) {

                    }

                    @Override
                    public void onPositiveButtonClicked(int index) {
                        Toast.makeText(MainActivity.this, "选择了" + selectItems[index], Toast.LENGTH_SHORT).show();
                    }

                    @Override
                    public void onNegativeButtonClicked() {

                    }
                }, selectItems).show(getSupportFragmentManager(), "SingleSelectWithConfirmDialog"));
        findViewById(R.id.activity_main_multi_select_with_confirm_list_dialog_btn).setOnClickListener(v -> {
            boolean isSelected[] = {false, false, false, false, false};
            new MultiSelectWithConfirmListDialogFragment(new MultiSelectWithConfirmListDialogFragment.OnItemSelected() {
                @Override
                public void onItemSelected(int index) {

                }

                @Override
                public void onPositiveButtonClicked(boolean[] isSelected) {
                    String selectedStr = "";
                    for (int i = 0; i < isSelected.length; i++) {
                        if (isSelected[i]) {
                            selectedStr += (selectItems[i] + " ");
                        }
                    }
                    Toast.makeText(MainActivity.this, "选择了" + selectedStr, Toast.LENGTH_SHORT).show();
                }

                @Override
                public void onNegativeButtonClicked() {

                }
            }, selectItems, isSelected).show(getSupportFragmentManager(), "MultiSelectWithConfirmDialog");
        });
        findViewById(R.id.activity_main_single_custom_select_with_confirm_list_dialog_btn).setOnClickListener(v -> {
            new SingleCustomSelectListDialogFragment(index -> {
                Toast.makeText(MainActivity.this,
                        "选择了" + selectItems[index], Toast.LENGTH_SHORT).show();
            }, selectItems).show(getSupportFragmentManager(), "SingleCustomSelectListDialog");
        });
    }
}