package andrewhossam.thed.tools;

import android.app.ActivityOptions;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import java.util.ArrayList;

import andrewhossam.thed.DetailsActivity;
import andrewhossam.thed.MainActivity;
import andrewhossam.thed.R;
import andrewhossam.thed.data.Data;

/**
 * Created by Andrew on 5/29/2017.
 */

public class MyAdapter extends RecyclerView.Adapter<MyAdapter.ViewHolder> {
    Context context;
    MainActivity mainActivity;
    private ArrayList<Data> DataList;

    // Provide a suitable constructor (depends on the kind of dataset)
    public MyAdapter(MainActivity mainActivity, ArrayList<Data> DataList, Context context) {
        this.DataList = DataList;
        this.context = context;
        this.mainActivity = mainActivity;
    }

    // Create new views (invoked by the layout manager)
    @Override
    public MyAdapter.ViewHolder onCreateViewHolder(ViewGroup parent,
                                                   int viewType) {
        // create a new view
        View v = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.product_item, parent, false);
        // set the view's size, margins, paddings and layout parameters
        ViewHolder vh = new ViewHolder(v);
        return vh;
    }

    // Replace the contents of a view (invoked by the layout manager)
    @Override
    public void onBindViewHolder(final ViewHolder holder, final int position) {
        // - get element from your dataset at this position
        // - replace the contents of the view with that element
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Bundle bundle;
                Intent intent = new Intent(context, DetailsActivity.class);
                intent.putExtra("data", DataList.get(position));
                if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.LOLLIPOP) {
                    bundle = ActivityOptions.makeSceneTransitionAnimation(mainActivity, holder.image, holder.image.getTransitionName()).toBundle();
                    context.startActivity(intent, bundle);
                } else {
                    context.startActivity(intent);
                }
            }
        });
        holder.price.setText("$" + DataList.get(position).getPrice());
        holder.desc.setText(DataList.get(position).getProductDescription());
        Data.Image imageObject = DataList.get(position).getImage();
        int densityDpi = (int) context.getResources().getDisplayMetrics().density;
        Picasso.with(context).load(Uri.parse(httptohttps.convert(imageObject.getLink()))).resize(Integer.parseInt(imageObject.getWidth()) * densityDpi, Integer.parseInt(imageObject.getHeight()) * densityDpi).centerInside().into(holder.image);


    }

    // Return the size of your dataset (invoked by the layout manager)
    @Override
    public int getItemCount() {
        return DataList.size();
    }

    // Provide a reference to the views for each data item
    // Complex data items may need more than one view per item, and
    // you provide access to all the views for a data item in a view holder
    public static class ViewHolder extends RecyclerView.ViewHolder {
        // each data item is just a string in this case
        public TextView price, desc;
        ImageView image;

        public ViewHolder(View v) {
            super(v);
            price = (TextView) v.findViewById(R.id.item_price);
            desc = (TextView) v.findViewById(R.id.item_desc);
            image = (ImageView) v.findViewById(R.id.image_item);
        }
    }
}