package com.example.myapplication.Model;

import com.example.myapplication.Model.ErrorModel;
import com.example.myapplication.Model.Post;

import java.util.ArrayList;

public class AsyncResult {
    private ArrayList<Post> successItem;
    private ArrayList<ErrorModel> failedItem;

    public AsyncResult() {
        successItem = new ArrayList<>();
        failedItem = new ArrayList<>();
    }

    public AsyncResult(ArrayList<Post> successItem, ArrayList<ErrorModel> failedItem) {
        this.successItem = successItem;
        this.failedItem = failedItem;
    }

    public ArrayList<Post> getSuccessItem() {
        return successItem;
    }

    public void addSuccessItem(Post item) {
        if (successItem == null) {
            successItem = new ArrayList<>();
        }
        successItem.add(item);
    }
    public void addSuccessItem(ArrayList<Post> item) {
        if (successItem == null) {
            successItem = new ArrayList<>();
        }
        successItem.addAll(item);
    }

    public void setSuccessItem(ArrayList<Post> successItem) {
        this.successItem = successItem;
    }

    public void setFailedItem(ArrayList<ErrorModel> failedItem) {
        this.failedItem = failedItem;
    }

    public ArrayList<ErrorModel> getFailedItem() {
        return failedItem;
    }

    public void addFailedItem(ErrorModel item) {
        if (failedItem == null) {
            failedItem = new ArrayList<>();
        }
        failedItem.add(item);
    }
    public void addFailedItem(ArrayList<ErrorModel> item) {
        if (failedItem == null) {
            failedItem = new ArrayList<>();
        }
        failedItem.addAll(item);
    }
}
