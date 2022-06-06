package com.example.appcompatdialogdemo;

import android.app.Dialog;
import android.content.DialogInterface;
import android.os.Bundle;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatDialogFragment;

public class SingleSelectListDialogFragment extends AppCompatDialogFragment {

    private OnItemSelected onItemSelected;
    private CharSequence[] selectItems;

    public SingleSelectListDialogFragment(OnItemSelected onItemSelected, CharSequence[] selectItems) {
        this.onItemSelected = onItemSelected;
        this.selectItems = selectItems;
    }

    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        builder.setTitle("请选择").setItems(selectItems, (dialog, which) -> onItemSelected.onItemSelected(which));
        return builder.create();
    }

    public interface OnItemSelected {
        void onItemSelected(int index);
    }
}
