package com.example.report.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.report.R;
import com.example.report.pojo.ChildTransactionDetailList;

import java.util.List;

public class JSONChildAdapter extends RecyclerView.Adapter<JSONChildAdapter.JSONChildHolder> {
    private List<ChildTransactionDetailList> ChildItemList;
    private JSONParentAdapter context;

    JSONChildAdapter(List<ChildTransactionDetailList> childItemList, JSONParentAdapter context) {
        this.ChildItemList = childItemList;
        this.context = context;
    }

    @NonNull
    @Override
    public JSONChildHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.child_list_item, viewGroup, false);
        return new JSONChildHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull JSONChildHolder childViewHolder, int position) {

        ChildTransactionDetailList childItem = ChildItemList.get(position);
        childViewHolder.TransactionID.setText(childItem.getTransactionID());
        childViewHolder.TransactionKyat.setText("+ "+childItem.getAmount()+" .00 MMK");
        childViewHolder.Phone.setText("From - "+childItem.getPhone());
        childViewHolder.Time.setText(childItem.getTime());
    }

    @Override
    public int getItemCount() {
        return ChildItemList.size();
    }

    public class JSONChildHolder extends RecyclerView.ViewHolder {
        TextView TransactionID;
        TextView TransactionKyat;
        TextView Phone;
        TextView Time;

        public JSONChildHolder(@NonNull View itemView) {
            super(itemView);
            TransactionID = itemView.findViewById(R.id.tran_ID);
            TransactionKyat = itemView.findViewById(R.id.tran_kyat);
            Phone=itemView.findViewById(R.id.tran_phone);
            Time=itemView.findViewById(R.id.tran_time);

        }
    }
}