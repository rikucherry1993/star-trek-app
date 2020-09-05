package com.rikucherry.startrek.ui.adapter;

import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.databinding.DataBindingUtil;
import androidx.recyclerview.widget.RecyclerView;

import com.rikucherry.startrek.R;
import com.rikucherry.startrek.databinding.ListCelestialObjectsBinding;
import com.rikucherry.startrek.model.ObjectsItem;

import java.util.List;

/**
 * Adapter for ObjectsItem
 *
 * @author rikucherry
 * @version 1.0
 */
public class ObjectsItemAdapter extends RecyclerView.Adapter<ObjectsItemAdapter.ViewHolder> {

    private List<ObjectsItem> mList;

    public ObjectsItemAdapter(List<ObjectsItem> items) {
        this.mList = items;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        // view binding (class name = layout's name + suffix(=Binding))
        ListCelestialObjectsBinding listBinding = DataBindingUtil.inflate(LayoutInflater
                .from(parent.getContext()),R.layout.list_celestial_objects,parent,false);

        return new ViewHolder(listBinding);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        ObjectsItem item = mList.get(position);

        holder.listBinding.imageObject.setImageResource(item.getChildImageId());
        holder.listBinding.textObjectName.setText(item.getObjectName());
        holder.listBinding.textObjectCategory.setText(item.getObjectCategory());
        holder.listBinding.textObjectIntroduction.setText(item.getObjectIntroduction());
    }

    @Override
    public int getItemCount() {
        if (mList == null) {
            return 0;
        }
        return mList.size();
    }


    public class ViewHolder extends RecyclerView.ViewHolder {

        final ListCelestialObjectsBinding listBinding;

        public ViewHolder(ListCelestialObjectsBinding listBinding) {
            super(listBinding.getRoot());

            this.listBinding = listBinding;

        }
    }
}