package ly.generalassemb.drewmahrt.shoppinglistver2;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

/**
 * Created by Scott Lindley on 10/25/2016.
 */

public class RecyclerViewHolder extends RecyclerView.ViewHolder{
    public TextView mItemName, mItemDescription, mPrice, mType;

    public RecyclerViewHolder(View itemView) {
        super(itemView);
            mItemName = (TextView)itemView.findViewById(R.id.item_name);
            mItemDescription = (TextView)itemView.findViewById(R.id.item_description);
            mPrice = (TextView)itemView.findViewById(R.id.item_price);
            mType = (TextView)itemView.findViewById(R.id.item_type);
    }
}
