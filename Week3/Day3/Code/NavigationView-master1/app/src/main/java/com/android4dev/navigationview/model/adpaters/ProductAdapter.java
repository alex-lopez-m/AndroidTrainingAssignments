package com.android4dev.navigationview.model.adpaters;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.android4dev.navigationview.R;
import com.android4dev.navigationview.model.Product;
import com.squareup.picasso.Picasso;


import java.util.ArrayList;

/**
 * Created by Android1 on 8/10/2015.
 */
public class ProductAdapter extends RecyclerView.Adapter<ProductAdapter.ProductViewHolder>
{
    private final Context context;
    private ArrayList<Product> productDataSet;

    public ProductAdapter(Context context, ArrayList<Product> productList){
        this.context = context;
        this.productDataSet = productList;
    }

    @Override
    public ProductViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.product_layout, parent, false);
        return new ProductViewHolder(view);
    }

    @Override
    public void onBindViewHolder(final ProductViewHolder holder, final int position) {
        Product p = productDataSet.get(position);

        //ImageView imageView = (ImageView) itemView.findViewById(app.picasso_list.R.id.item_icon);

        Picasso.with(context).setDebugging(true);
        Picasso.with(context)
                .load(p.productImageUrl)
                .resize(300, 500).centerCrop()
                .into(holder.imgvProduct);

        holder.basePrice.setText(p.basePrice);
    }

    @Override
    public int getItemCount(){
        return productDataSet.size();
    }

    public static class ProductViewHolder extends RecyclerView.ViewHolder{
        ImageView imgvProduct;
        TextView basePrice;

        public ProductViewHolder(View itemView){
            super(itemView);

            this.imgvProduct = (ImageView) itemView.findViewById(R.id.img_product_view);
            this.basePrice = (TextView) itemView.findViewById(R.id.tv_current_price);

        }
    }
}
