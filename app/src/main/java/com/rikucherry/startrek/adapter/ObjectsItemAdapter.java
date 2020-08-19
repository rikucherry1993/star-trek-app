package com.rikucherry.startrek.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.rikucherry.startrek.R;
import com.rikucherry.startrek.model.ObjectsItem;

import java.util.List;

/**
 * Adapter for ObjectsItem
 *
 * @author rikucherry
 * @version 1.0
 */
public class ObjectsItemAdapter extends RecyclerView.Adapter<ObjectsItemAdapter.ViewHolder> {

    private final Context mContext;
    private List<ObjectsItem> mList;


    public ObjectsItemAdapter(Context context, List<ObjectsItem> items) {
        this.mList = items;
        this.mContext = context;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent,
                                         int viewType) {
        View v = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.list_celestial_objects, parent, false);
        return new ViewHolder(v);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        ObjectsItem item = mList.get(position);

        holder.imageObject.setImageResource(item.getImageResourceId());
        holder.textObjectName.setText(item.getObjectName());
        holder.textObjectCategory.setText(item.getObjectCategory());
        holder.textObjectIntroduction.setText(item.getObjectIntroduction());
    }

    @Override
    public int getItemCount() {
        if (mList == null) {
            return 0;
        }
        return mList.size();
    }


    public class ViewHolder extends RecyclerView.ViewHolder {


        public ImageView imageObject;
        public TextView textObjectName;
        public TextView textObjectCategory;
        public TextView textObjectIntroduction;


        public ViewHolder(View itemView) {
            super(itemView);

            imageObject = itemView.findViewById(R.id.image_object);
            textObjectName = itemView.findViewById(R.id.text_object_name);
            textObjectCategory = itemView.findViewById(R.id.text_object_category);
            textObjectIntroduction = itemView.findViewById(R.id.text_object_introduction);

        }
    }
}