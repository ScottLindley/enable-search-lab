package ly.generalassemb.drewmahrt.shoppinglistver2;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.List;

/**
 * Created by Scott Lindley on 10/25/2016.
 */

public class RecyclerViewAdapter extends RecyclerView.Adapter<RecyclerViewHolder>{
    private List<GroceryItem> mGrocryItemList;

    public RecyclerViewAdapter(List<GroceryItem> grocryItemList) {
        mGrocryItemList = grocryItemList;
    }

    @Override
    public RecyclerViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.grocery_list_item, null);
        return new RecyclerViewHolder(view);
    }

    @Override
    public void onBindViewHolder(RecyclerViewHolder holder, int position) {
        holder.mItemName.setText(mGrocryItemList.get(position).getName());
        holder.mItemDescription.setText(mGrocryItemList.get(position).getDescription());
        holder.mPrice.setText(mGrocryItemList.get(position).getPrice());
        holder.mType.setText(mGrocryItemList.get(position).getType());
    }

    @Override
    public int getItemCount() {
        return mGrocryItemList.size();
    }

    public void replaceData(List<GroceryItem> newList){
        mGrocryItemList = newList;
        notifyDataSetChanged();
    }
}
