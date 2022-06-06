package com.example.appcompatdialogdemo;

import android.app.Dialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatDialogFragment;

public class SingleCustomSelectListDialogFragment extends AppCompatDialogFragment {

    private OnItemSelected onItemSelected;
    private CharSequence[] selectItems;
    private MenuAdapter menuAdapter;

    public SingleCustomSelectListDialogFragment(OnItemSelected onItemSelected, CharSequence[] selectItems) {
        this.onItemSelected = onItemSelected;
        this.selectItems = selectItems;
    }

    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        menuAdapter = new MenuAdapter(selectItems);
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        builder.setTitle("请选择").setAdapter(menuAdapter, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                onItemSelected.onItemSelected(which);
            }
        });
        return builder.create();
    }

    public interface OnItemSelected {
        void onItemSelected(int index);
    }

    class MenuAdapter extends BaseAdapter {

        private CharSequence[] selectItems;

        public MenuAdapter(CharSequence[] selectItems) {
            this.selectItems = selectItems;
        }

        @Override
        public int getCount() {
            return selectItems.length;
        }

        @Override
        public Object getItem(int position) {
            return selectItems[position];
        }

        @Override
        public long getItemId(int position) {
            return position;
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            LayoutInflater inflater = LayoutInflater.from(getContext());
            ViewHolder holder = null;
            if (convertView == null) {
                convertView = inflater.inflate(R.layout.item_single_menu, null);
                holder = new ViewHolder();
                holder.nameTv = (TextView) convertView.findViewById(R.id.item_single_menu_name_tv);
                convertView.setTag(holder);
            } else {
                holder = (ViewHolder) convertView.getTag();
            }

            holder.nameTv.setText(selectItems[position]);
            return convertView;
        }

        class ViewHolder {
            TextView nameTv;
        }
    }
}
