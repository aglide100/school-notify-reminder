package com.example.myapplication.DB;

import android.util.Log;

import com.example.myapplication.Model.Plan;
import com.example.myapplication.Model.Post;

import java.util.ArrayList;

import io.realm.Realm;
import io.realm.RealmModel;
import io.realm.RealmResults;

public class DBmanager {
    Realm realm = Realm.getDefaultInstance();

    public void addNewPlan(Plan newPlan) {
        PlanRealmObject plan = new PlanRealmObject();
        plan.PlanToRealmObject(newPlan);

        realm.executeTransaction(new Realm.Transaction() {
            @Override
            public void execute(Realm realm) {
                realm.copyToRealm(plan);
            }
        });

        Log.e("Realm","플랜 생성완료!" + plan.getPlanName());
    }

    public void addPost(com.example.myapplication.Model.Post newPost){
        PostRealmObject post = new PostRealmObject();
        post.PostToRealmObject(newPost);

        final RealmResults<PostRealmObject> findSamePost = realm.where(PostRealmObject.class).equalTo("parent", newPost.getParent()).equalTo("code",newPost.getCode()).equalTo("num", newPost.getNum()).findAll();

        if (findSamePost.size() > 0) {
            Log.e("Realm", "이미 같은 포스트가 존재합니다." + newPost.getCode()+ "항목 " + newPost.getNum() + "번째 " + newPost.getTitle());
        } else {
            Log.e("Realm", "새로운 포스트를 추가했습니다." + newPost.getCode()+ "항목 " + newPost.getNum() + "번째 " + newPost.getTitle());
            realm.executeTransaction(new Realm.Transaction() {
                @Override
                public void execute(Realm realm) {
                    realm.copyToRealm(post);
                }
            });
        }
    }

    public void addPost(ArrayList<Post> postList) {
        Log.e("Realm", "" +postList.size());

        for (int i = 0; i < postList.size(); i++) {
            addPost(postList.get(i));
        }
    }

    public ArrayList<Post> getPost(Plan plan) {
        String parent = plan.getPlanID();
        ArrayList<Post> postList = new ArrayList<Post>();

        final RealmResults<PostRealmObject> post = realm.where(PostRealmObject.class).equalTo("parent",parent).findAll();

        for(int i = 0; i < post.size(); i++){
            Post newPost = new Post();
            newPost.RealmObjectToPost(post.get(i));
            postList.add(newPost);
        }

        return postList;
    }

    public int getPostCount(String planID, String code) {
        final RealmResults<PlanRealmObject> planRealmObject = realm.where(PlanRealmObject.class).equalTo("ID", planID).findAll();
        int num = planRealmObject.where().equalTo("code", code).findAll().size();

        return num;
    }


    public Plan getPlan(String planID){
        Plan plan = new Plan();

        final PlanRealmObject planRealmObject = realm.where(com.example.myapplication.DB.PlanRealmObject.class).equalTo("ID", planID).findFirst();

//      이러면 플랜이름을 유니크 속성을 줘야함
        plan.RealmObjectToPlan(planRealmObject);

        return plan;
    }


    public ArrayList<Plan> getPlanList(){
        ArrayList<Plan> planList = new ArrayList<Plan>();

        final RealmResults<PlanRealmObject> getPlans = realm.where(PlanRealmObject.class).findAll();
        if (getPlans == null){
            Log.e("Realm","플랜이 없습니다!!!");
            return null;
        } else {
            for(int i = 0; i < getPlans.size(); i++) {
                Plan newPlan = new Plan();
                newPlan.RealmObjectToPlan(getPlans.get(i));
                planList.add(newPlan);
            }
        }

        return planList;
    }
}
