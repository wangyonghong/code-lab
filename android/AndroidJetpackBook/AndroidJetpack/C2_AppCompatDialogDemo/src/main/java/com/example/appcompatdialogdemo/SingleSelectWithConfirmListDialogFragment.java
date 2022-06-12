package com.example.appcompatdialogdemo;

import android.app.Dialog;
import android.content.DialogInterface;
import android.os.Bundle;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatDialogFragment;

public class SingleSelectWithConfirmListDialogFragment extends AppCompatDialogFragment {

    private OnItemSelected onItemSelected;
    private CharSequence[] selectItems;
    private int selectItemIndex = 0;

    public SingleSelectWithConfirmListDialogFragment(OnItemSelected onItemSelected, CharSequence[] selectItems) {
        this.onItemSelected = onItemSelected;
        this.selectItems = selectItems;
    }

    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        builder.setTitle("请选择").setSingleChoiceItems(selectItems, 0, (dialog, which) -> {
            onItemSelected.onItemSelected(which);
            selectItemIndex = which;
        }).setPositiveButton("确定", (dialog, id) -> onItemSelected.onPositiveButtonClicked(selectItemIndex))
                .setNegativeButton("取消", (dialog, id) -> onItemSelected.onNegativeButtonClicked());
        return builder.create();
    }

    public interface OnItemSelected {
        void onItemSelected(int index);

        void onPositiveButtonClicked(int index);

        void onNegativeButtonClicked();
    }
}
