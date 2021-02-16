package com.rikucherry.startrek.ui.adapter;

import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.databinding.DataBindingUtil;
import androidx.paging.PagedListAdapter;
import androidx.recyclerview.widget.DiffUtil;
import androidx.recyclerview.widget.RecyclerView;

import com.rikucherry.startrek.R;
import com.rikucherry.startrek.databinding.ListCelestialObjectsBinding;
import com.rikucherry.startrek.model.ObjectsItem;

import java.util.List;

/**
 * Adapter for ObjectsItem
 *
 * @author rikucherry
 * @version 1.1
 */
public class ObjectsItemAdapter extends PagedListAdapter<ObjectsItem,ObjectsItemAdapter.ViewHolder> {

    private List<ObjectsItem> mList;

    public ObjectsItemAdapter() {
        super(diffCallback);
//        this.mList = items;
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
        ObjectsItem item = getItem(position);

        if (item != null) {
            holder.listBinding.imageObject.setImageResource(item.getChildImageId());
            holder.listBinding.textObjectName.setText(item.getObjectName());
            holder.listBinding.textObjectCategory.setText(item.getObjectCategory());
            holder.listBinding.textObjectIntroduction.setText(item.getObjectIntroduction());
        }

    }

    private static DiffUtil.ItemCallback<ObjectsItem> diffCallback =
            new DiffUtil.ItemCallback<ObjectsItem>() {
                @Override
                public boolean areItemsTheSame(@NonNull ObjectsItem oldItem, @NonNull ObjectsItem newItem) {
                    return oldItem.getObjectName().equals(newItem.getObjectName());
                }

                @Override
                public boolean areContentsTheSame(@NonNull ObjectsItem oldItem, @NonNull ObjectsItem newItem) {
                    return oldItem.equals(newItem);
                }
            };


    public class ViewHolder extends RecyclerView.ViewHolder {

        final ListCelestialObjectsBinding listBinding;

        public ViewHolder(ListCelestialObjectsBinding listBinding) {
            super(listBinding.getRoot());

            this.listBinding = listBinding;

        }
    }
}