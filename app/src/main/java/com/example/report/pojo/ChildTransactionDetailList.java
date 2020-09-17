package com.example.report.pojo;

import android.util.Log;

public class ChildTransactionDetailList {
    private String TransactionID;
    private String Amount;
    private String Phone;
    private String Time;

    public String getTransactionID() {
        return TransactionID;
    }

    public void setTransactionID(String transactionID) {
        TransactionID = transactionID;
    }

    public String getAmount() {
        return Amount;
    }

    public void setAmount(String amount) {
        Amount = amount;
    }

    public String getPhone() {
        return Phone;
    }

    public void setPhone(String phone) {
        Phone = phone;
    }

    public String getTime() {
        return Time;
    }

    public void setTime(String time) {
        Time = time;
    }

    public ChildTransactionDetailList(String transactionID, String Amount, String Phone, String Time) {
        this.TransactionID = transactionID;
        this.Amount=Amount;
        this.Phone=Phone;
        this.Time=Time;
    }
}
