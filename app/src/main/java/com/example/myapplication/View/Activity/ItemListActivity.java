package com.example.myapplication.View.Activity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.myapplication.Model.MainModel;
import com.example.myapplication.Model.Post;
import com.example.myapplication.MyApplication;
import com.example.myapplication.Presenter.Contract;
import com.example.myapplication.Presenter.MainPresenter;
import com.example.myapplication.R;
import com.example.myapplication.View.Basic.BasicActivity;

import java.util.ArrayList;

public class ItemListActivity extends BasicActivity {
    private Contract.Presenter presenter;
    private MainModel mainModel;
    private ArrayList<Post> postList;

    private RecyclerView mRecyclerView;
    private RecyclerView.Adapter mAdapter;
    private RecyclerView.LayoutManager mLayoutManager;
    private ArrayList<Post> myDataset;

    @SuppressLint("ResourceType")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_itemlist);

        Intent itemListIntent = getIntent();
        String planID = itemListIntent.getStringExtra("PlanID");
        Log.e("ItemList", planID);

        mLayoutManager = new LinearLayoutManager(MyApplication.ApplicationContext(), LinearLayoutManager.VERTICAL, false);

        presenter = new MainPresenter(findViewById(R.layout.activity_itemlist));
        mainModel = new MainModel(presenter);

        postList = new ArrayList<>();
        postList = mainModel.getPost(planID);

        mRecyclerView = (RecyclerView) findViewById(R.id.post_list_view);
        mRecyclerView.setHasFixedSize(true);
        mRecyclerView.setLayoutManager(mLayoutManager);

        myDataset = new ArrayList<>();
        myDataset = postList;
        mAdapter = new PostAdapter(myDataset);
        mRecyclerView.setAdapter(mAdapter);
    }
}

class PostAdapter<a> extends RecyclerView.Adapter<PostAdapter.ViewHolder> {
    private ArrayList<Post> myDataset;

    // Provide a reference to the views for each data item
    // Complex data items may need more than one view per item, and
    // you provide access to all the views for a data item in a view holder
    public static class ViewHolder extends RecyclerView.ViewHolder {
        // each data item is just a string in this case
        public TextView postTitleView, postDateView, postGroupView;


        public ViewHolder(View view) {
            super(view);
            postTitleView = (TextView) view.findViewById(R.id.post_card_view_title);
            postDateView = (TextView) view.findViewById(R.id.post_card_view_date);
            postGroupView = (TextView) view.findViewById(R.id.post_card_view_group);
        }
    }

    // Provide a suitable constructor (depends on the kind of dataset)
    public PostAdapter(ArrayList<Post> myDataset) {
        this.myDataset = myDataset;

    }

    // Create new views (invoked by the layout manager)
    @Override
    public PostAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        // create a new view
        View v = LayoutInflater.from(MyApplication.ApplicationContext())
                .inflate(R.layout.custom_post_view,  parent, false);
        // set the view's size, margins, paddings and layout parameters
        ViewHolder vh = new ViewHolder(v);
        return vh;
    }

    // Replace the contents of a view (invoked by the layout manager)
    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        // - get element from your dataset at this position
        // - replace the contents of the view with that element
        holder.postTitleView.setText(myDataset.get(position).getTitle());
        holder.postDateView.setText(myDataset.get(position).getDate());
        holder.postGroupView.setText(myDataset.get(position).getCode());


    }

    // Return the size of your dataset (invoked by the layout manager)
    @Override
    public int getItemCount() {
        if (myDataset == null) {
            return 0;
        }

        return myDataset.size();
    }


}
