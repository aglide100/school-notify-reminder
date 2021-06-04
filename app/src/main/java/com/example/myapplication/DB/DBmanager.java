package com.example.myapplication.DB;

import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Intent;
import android.os.Build;
import android.util.Log;

import androidx.core.app.NotificationCompat;

import com.example.myapplication.Main.MainActivity;
import com.example.myapplication.Model.Plan;
import com.example.myapplication.Model.Post;
import com.example.myapplication.MyApplication;
import com.example.myapplication.R;

import java.util.ArrayList;

import io.realm.Realm;
import io.realm.RealmResults;
import io.realm.Sort;

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

        Log.e("Realm", "플랜 생성완료!" + plan.getPlanName());
    }

    public void addPost(com.example.myapplication.Model.Post newPost) {
        PostRealmObject post = new PostRealmObject();
        post.PostToRealmObject(newPost);

        final RealmResults<PostRealmObject> findSamePost = realm.where(PostRealmObject.class).equalTo("parent", newPost.getParent()).equalTo("code", newPost.getCode()).equalTo("num", newPost.getNum()).findAll();

        if (findSamePost.size() != 0) {
            Log.e("Realm", "이미 같은 포스트가 존재합니다." + newPost.getCode() + "항목 " + newPost.getNum() + "번째 " + newPost.getTitle());
        } else {
            NotificationCompat.Builder builder = new NotificationCompat.Builder(MyApplication.ApplicationContext(), "newPost");

            Intent notificationIntent = new Intent(MyApplication.ApplicationContext(), MainActivity.class);
            PendingIntent pIntent = PendingIntent.getActivity(MyApplication.ApplicationContext(), 0, notificationIntent, PendingIntent.FLAG_UPDATE_CURRENT);

            builder.setSmallIcon(R.mipmap.ic_launcher);
            builder.setContentTitle("새로운 알림이 있습니다.");
            builder.setContentText(newPost.getTitle());
            builder.setShowWhen(true);
            builder.setGroup("PostNotifyGroup");
            builder.setContentIntent(pIntent);
            builder.setPriority(NotificationCompat.PRIORITY_DEFAULT);
            builder.setGroupSummary(true);

            builder.setAutoCancel(true);

            NotificationManager notificationManager = (NotificationManager) MyApplication.ApplicationContext().getSystemService(MyApplication.ApplicationContext().NOTIFICATION_SERVICE);
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
                notificationManager.createNotificationChannel(new NotificationChannel("newPost", "새로운 포스트", NotificationManager.IMPORTANCE_DEFAULT));
            }

            notificationManager.notify(20, builder.build());
        }

        Log.e("Realm", "새로운 포스트를 추가했습니다." + newPost.getCode() + "항목 " + newPost.getNum() + "번째 " + newPost.getTitle());
        realm.executeTransaction(new Realm.Transaction() {
            @Override
            public void execute(Realm realm) {
                realm.copyToRealm(post);
            }
        });

    }

    public void addPost(ArrayList<Post> postList) {
        Log.e("Realm", "" + postList.size());

        for (int i = 0; i < postList.size(); i++) {
            addPost(postList.get(i));
        }
    }

    public Post getPost(String ID) {
        Post post = new Post();
        final PostRealmObject realmPost = realm.where(PostRealmObject.class).equalTo("ID", ID).findFirst();
        post.RealmObjectToPost(realmPost);

        return post;
    }

    public ArrayList<Post> getPost(Plan plan) {
        String parent = plan.getPlanID();
        ArrayList<Post> postList = new ArrayList<Post>();

        final RealmResults<PostRealmObject> post = realm.where(PostRealmObject.class).equalTo("parent", parent).sort("date", Sort.DESCENDING).findAll();

        for (int i = 0; i < post.size(); i++) {
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

    public Plan getPlan(String planID) {
        Plan plan = new Plan();

        final PlanRealmObject planRealmObject = realm.where(com.example.myapplication.DB.PlanRealmObject.class).equalTo("ID", planID).findFirst();

//      이러면 플랜이름을 유니크 속성을 줘야함
        plan.RealmObjectToPlan(planRealmObject);

        return plan;
    }


    public ArrayList<Plan> getPlanList() {
        ArrayList<Plan> planList = new ArrayList<Plan>();

        final RealmResults<PlanRealmObject> getPlans = realm.where(PlanRealmObject.class).findAll();
        if (getPlans == null) {
            Log.e("Realm", "플랜이 없습니다!!!");
            return null;
        } else {
            for (int i = 0; i < getPlans.size(); i++) {
                Plan newPlan = new Plan();
                newPlan.RealmObjectToPlan(getPlans.get(i));
                planList.add(newPlan);
            }
        }

        return planList;
    }

    public void deletePlan(Plan plan) {
        realm.executeTransaction(new Realm.Transaction() {
            @Override
            public void execute(Realm realm) {
                final RealmResults<PlanRealmObject> planRealmObjects = realm.where(PlanRealmObject.class).equalTo("ID", plan.getPlanID()).findAll();

                Log.e("Realm", planRealmObjects.toString() + "을 삭제합니다.");
                planRealmObjects.deleteAllFromRealm();
            }
        });
    }

    public void updatePlan(Plan plan) {
        realm.executeTransaction(new Realm.Transaction() {
            @Override
            public void execute(Realm realm) {
                final PlanRealmObject planRealmObject = realm.where(PlanRealmObject.class).equalTo("ID", plan.getPlanID()).findFirst();

                planRealmObject.setPlanName(plan.getPlanName());
                planRealmObject.setSubjects(plan.getSubjects());
                Log.e("Realm", planRealmObject.toString() + "업데이트를 완료했습니다.");

                realm.commitTransaction();
            }
        });
    }

    public void updateContentInPost(Post post) {
        realm.executeTransaction(new Realm.Transaction() {
            @Override
            public void execute(Realm realm) {
                final PostRealmObject postRealmObject = realm.where(PostRealmObject.class).equalTo("ID", post.getID()).findFirst();

                postRealmObject.setContent(post.getContent());

                realm.commitTransaction();
            }
        });
    }
}
