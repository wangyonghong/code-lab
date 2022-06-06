package com.example.appcompatdialogdemo;

import android.app.Dialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.widget.Toast;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatDialogFragment;

public class SimpleDialogFragment extends AppCompatDialogFragment {

    private OnButtonClicked onButtonClicked;

    public SimpleDialogFragment(OnButtonClicked onButtonClicked) {
        this.onButtonClicked = onButtonClicked;
    }

    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        builder.setMessage("您确定这样做吗？")
                .setPositiveButton("确定", (dialog, id) -> onButtonClicked.onPositiveButtonClicked())
                .setNegativeButton("取消", (dialog, id) -> onButtonClicked.onNegativeButtonClicked());
        return builder.create();
    }
    public interface OnButtonClicked{
        void onPositiveButtonClicked();
        void onNegativeButtonClicked();
    }
}
