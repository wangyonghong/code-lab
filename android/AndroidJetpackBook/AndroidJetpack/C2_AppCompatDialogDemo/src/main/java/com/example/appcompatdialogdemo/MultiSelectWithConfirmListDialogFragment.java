package com.example.appcompatdialogdemo;

import android.app.Dialog;
import android.content.DialogInterface;
import android.os.Bundle;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatDialogFragment;

public class MultiSelectWithConfirmListDialogFragment extends AppCompatDialogFragment {

    private OnItemSelected onItemSelected;
    private CharSequence[] selectItems;
    private boolean[] isItemSelect;

    public MultiSelectWithConfirmListDialogFragment(OnItemSelected onItemSelected, CharSequence[] selectItems, boolean[] isItemSelect) {
        this.onItemSelected = onItemSelected;
        this.selectItems = selectItems;
        this.isItemSelect = isItemSelect;
    }

    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        builder.setTitle("请选择").setMultiChoiceItems(selectItems, isItemSelect, (dialog, which, isChecked) ->
                isItemSelect[which] = isChecked).setPositiveButton("确定", (dialog, id) ->
                onItemSelected.onPositiveButtonClicked(isItemSelect))
                .setNegativeButton("取消", (dialog, id) -> onItemSelected.onNegativeButtonClicked());
        return builder.create();
    }

    public interface OnItemSelected {
        void onItemSelected(int index);

        void onPositiveButtonClicked(boolean[] isItemSelect);

        void onNegativeButtonClicked();
    }
}
