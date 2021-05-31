package com.example.myapplication.DB;

import android.util.Log;

import com.example.myapplication.Model.Plan;
import com.example.myapplication.Model.Post;

import java.util.ArrayList;

import io.realm.Realm;
import io.realm.RealmResults;

public class DBmanager {
    Realm realm = Realm.getDefaultInstance();

    public void addPlan(Plan newPlan) {

        PlanRealmObject plan = new PlanRealmObject();
        plan.PlanToRealmObject(newPlan);

        realm.executeTransaction(new Realm.Transaction() {
            @Override
            public void execute(Realm realm) {
                realm.copyToRealm(plan);
            }
        });
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

    public ArrayList<Post> getPostList(Plan plan) {
        String parent = plan.getPlanID();
        ArrayList<Post> postList = new ArrayList<Post>();

        final RealmResults<PostRealmObject> post = realm.where(PostRealmObject.class).findAll();

        for(int i = 0; i < post.size(); i++){
            Post newPost = new Post();
            newPost.RealmObjectToPost(post.get(i));
            postList.add(newPost);
        }

        return postList;
    }

    public Plan getPlan(){
        Plan plan = new Plan();
        return plan;
    }

    public ArrayList<Plan> getPlanList(){
        ArrayList<Plan> planList = new ArrayList<Plan>();

        final RealmResults<PlanRealmObject> getPlans = realm.where(PlanRealmObject.class).findAll();

        for(int i = 0; i < getPlans.size(); i++) {
            Plan newPlan = new Plan();
        }

        return planList;
    }
}
