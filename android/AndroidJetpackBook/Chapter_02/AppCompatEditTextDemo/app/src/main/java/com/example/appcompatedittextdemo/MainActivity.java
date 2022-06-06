package com.example.appcompatedittextdemo;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.content.res.AppCompatResources;
import androidx.appcompat.widget.AppCompatEditText;
import androidx.core.view.ViewCompat;

import android.graphics.PorterDuff;
import android.os.Bundle;
import android.view.ActionMode;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    private AppCompatEditText demoEt;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        demoEt = findViewById(R.id.activity_main_et);
        ViewCompat.setBackgroundTintList(demoEt, AppCompatResources.getColorStateList(MainActivity.this, R.color.act_underline));
        ViewCompat.setBackgroundTintMode(demoEt, PorterDuff.Mode.SRC_IN);
        demoEt.setTextAppearance(MainActivity.this, R.style.TextAppearance_AppCompat_Large);
        demoEt.setCustomSelectionActionModeCallback(new ActionMode.Callback() {
            @Override
            public boolean onCreateActionMode(ActionMode mode, Menu menu) {
                MenuInflater menuInflater = mode.getMenuInflater();
                menuInflater.inflate(R.menu.edittext_selection_menu, menu);
                return true;
            }

            @Override
            public boolean onPrepareActionMode(ActionMode mode, Menu menu) {
                return false;
            }

            @Override
            public boolean onActionItemClicked(ActionMode mode, MenuItem item) {
                switch (item.getItemId()) {
                    case R.id.action_show_text:
                        String selectedText = demoEt.getText().toString().substring(demoEt.getSelectionStart(), demoEt.getSelectionEnd());
                        Toast.makeText(MainActivity.this, selectedText, Toast.LENGTH_SHORT).show();
                        mode.finish();
                        break;
                }
                return false;
            }

            @Override
            public void onDestroyActionMode(ActionMode mode) {

            }
        });
    }

}

