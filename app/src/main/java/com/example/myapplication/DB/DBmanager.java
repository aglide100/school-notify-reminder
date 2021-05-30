package com.example.myapplication.DB;

import android.util.Log;

import com.example.myapplication.Model.Plan;
import com.example.myapplication.Model.Post;

import java.util.ArrayList;

import io.realm.Realm;

public class DBmanager {
    Realm realm = Realm.getDefaultInstance();

    public void addPlan(Plan newPlan) {
        PlanRealmObject plan = new PlanRealmObject();
    }

    public void addPost(com.example.myapplication.Model.Post newPost){
        PostRealmObject post = new PostRealmObject();
        post.PostToRealmObject(newPost);

        realm.executeTransaction(new Realm.Transaction() {
            @Override
            public void execute(Realm realm) {
                realm.copyToRealm(post);
            }
        });
    }

    public void addPost(ArrayList<Post> postList) {
        Log.e("Realm", "" +postList.size());

        for (int i = 0; i < postList.size(); i++) {
            addPost(postList.get(i));
        }
    }

    public Post getPost(){
        final PostRealmObject postRealmObject = realm.where(PostRealmObject.class).findFirst();
        Post post = new Post();
        post.RealmObjectToPost(postRealmObject);

        return post;
    }
}
