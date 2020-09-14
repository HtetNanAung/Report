package com.example.report.pojo;

import java.util.List;

public class ParentTransactionDetailList {
    private String date;
    private List<ChildTransactionDetailList> Child;

    public ParentTransactionDetailList(String date, List<ChildTransactionDetailList> Child) {
        this.Child = Child;
        this.date = date;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public List<ChildTransactionDetailList> getChild() {
        return Child;
    }

    public void setChild(List<ChildTransactionDetailList> child) {
        Child = child;
    }
}
