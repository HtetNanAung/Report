package com.example.report.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.report.R;
import com.example.report.pojo.ParentTransactionDetailList;

import java.util.List;

public class JSONParentAdapter extends RecyclerView.Adapter<JSONParentAdapter.JSONHolder> {
    private String date;
    private List<ParentTransactionDetailList> itemList;
    private List<Object> listRecyclerItem;
    private Context context;

    public JSONParentAdapter(List<ParentTransactionDetailList> itemList, Context context) {
        this.itemList = itemList;
        this.context = context;
    }

    @NonNull
    @Override
    public JSONHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int viewType) {
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.parent_list_item, viewGroup, false);
        return new JSONHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull JSONHolder holder, int position) {
        ParentTransactionDetailList parentItem = itemList.get(position);
//        ParentTransactionDetailList parentItem = (ParentTransactionDetailList) listRecyclerItem.get(position);
        holder.ParentItemDate.setText("Date " + parentItem.getDate());
        LinearLayoutManager layoutManager = new LinearLayoutManager(holder.ChildRecyclerView.getContext(), LinearLayoutManager.VERTICAL,
                false);
        layoutManager.setInitialPrefetchItemCount(parentItem.getChild().size());
        JSONChildAdapter childItemAdapter = new JSONChildAdapter(parentItem.getChild(), this);
        holder.ChildRecyclerView.setLayoutManager(layoutManager);
        holder.ChildRecyclerView.setAdapter(childItemAdapter);
    }

    @Override
    public int getItemCount() {
        return itemList.size() - 1;
    }

    public class JSONHolder extends RecyclerView.ViewHolder {
        TextView ParentItemDate;
        RecyclerView ChildRecyclerView;

        public JSONHolder(final View itemView) {
            super(itemView);
            ParentItemDate = itemView.findViewById(R.id.name);
            ChildRecyclerView = itemView.findViewById(R.id.json_child_recyclerview);
        }
    }
}
