package com.example.report.pojo;

import android.util.Log;

public class ChildTransactionDetailList {
    private String TransactionID;
    private String Name;

    public String getTransactionID() {
        return TransactionID;
    }

    public String getName() {
        return Name;
    }

    public void setTransactionID(String transactionID) {
        TransactionID = transactionID;
    }

    public void setName(String name) {
        Name = name;
    }

    public ChildTransactionDetailList(String transactionID, String Name) {
        this.TransactionID = transactionID;
        this.Name = Name;
    }
}
