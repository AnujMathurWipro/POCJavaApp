package com.example.pocjavaapp.adapter;

import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.databinding.DataBindingUtil;
import androidx.recyclerview.widget.RecyclerView;

import com.example.pocjavaapp.R;
import com.example.pocjavaapp.databinding.ItemListBinding;
import com.example.pocjavaapp.models.RowsItem;
import com.squareup.picasso.Picasso;

import org.jetbrains.annotations.NotNull;

import java.util.List;

public class MainScreenListAdapter extends RecyclerView.Adapter<MainScreenListAdapter.ViewHolder> {

    private LayoutInflater inflater;
    private List<RowsItem> itemList;

    public MainScreenListAdapter(LayoutInflater inf, List<RowsItem> list) {
        inflater = inf;
        itemList = list;
    }

    @Override
    public ViewHolder onCreateViewHolder(@NotNull ViewGroup parent, int viewType) {
        ItemListBinding binding = DataBindingUtil.inflate(inflater, R.layout.item_list, parent, false);
        return new ViewHolder(binding.getRoot());
    }

    @Override
    public int getItemCount() {
        return itemList == null ? 0 : itemList.size();
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        ItemListBinding binding = DataBindingUtil.getBinding(holder.itemView);
        RowsItem item = itemList.get(position);
        binding.setItem(item);
        binding.executePendingBindings();
    }

    public void setListItems(List<RowsItem> items) {
        itemList = items;
        notifyDataSetChanged();
    }

    class ViewHolder extends RecyclerView.ViewHolder {
        ViewHolder(View view) {
            super(view);
        }
    }
}