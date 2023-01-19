package me.yonghong.android.cube.tlab.pag;

import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import me.yonghong.android.cube.R;

public class SimpleListAdapter extends RecyclerView.Adapter<SimpleListAdapter.ItemHolder> {

  private ItemClickListener itemClickListener;
  private Object[] items;

  public SimpleListAdapter(Object[] items, ItemClickListener itemClickListener) {
    this.items = items;
    this.itemClickListener = itemClickListener;
  }

  @NonNull
  @Override
  public SimpleListAdapter.ItemHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
    return new ItemHolder(View.inflate(viewGroup.getContext(), R.layout.layout_item, null));
  }

  @Override
  public void onBindViewHolder(@NonNull final SimpleListAdapter.ItemHolder viewHolder,
                               int position) {
    viewHolder.title.setText(items[position].toString());
    viewHolder.itemView.setOnClickListener(new View.OnClickListener() {
      @Override
      public void onClick(View v) {
        if (itemClickListener != null) {
          itemClickListener.onItemClick(viewHolder.getAdapterPosition());
        }
      }
    });
  }

  @Override
  public int getItemCount() {
    return items.length;
  }

  public class ItemHolder extends RecyclerView.ViewHolder {

    TextView title;

    public ItemHolder(@NonNull View itemView) {
      super(itemView);
      title = itemView.findViewById(R.id.tv_title);
    }
  }

  public interface ItemClickListener {
    void onItemClick(int position);
  }
}
