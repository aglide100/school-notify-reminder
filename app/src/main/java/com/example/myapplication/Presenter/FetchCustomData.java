package com.example.myapplication.Presenter;

import android.app.AlertDialog;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.content.Context;
import android.content.DialogInterface;
import android.net.ConnectivityManager;
import android.os.AsyncTask;
import android.os.Build;
import android.util.Log;
import android.widget.Toast;

import androidx.annotation.RequiresApi;
import androidx.core.app.NotificationCompat;
import androidx.core.app.NotificationManagerCompat;

import com.example.myapplication.DB.DBmanager;
import com.example.myapplication.Model.AsyncResult;
import com.example.myapplication.Model.ErrorModel;
import com.example.myapplication.Model.MainModel;
import com.example.myapplication.Model.Post;
import com.example.myapplication.MyApplication;
import com.example.myapplication.R;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.IOException;
import java.util.ArrayList;

import static android.content.Context.CONNECTIVITY_SERVICE;

public class FetchCustomData extends AsyncTask<ArrayList<String>, Void, AsyncResult> {
    private ConnectivityManager connectivityManager;

    private NotificationManager mNotificationManager;
    private NotificationCompat.Builder mNotifyBuilder;
    private int mNotifyID = 10;

    private boolean ok = false;

    private DBmanager dbManager;

    private void CheckState() {
        Context ctx = MyApplication.ApplicationContext();
        connectivityManager = (ConnectivityManager) ctx.getSystemService(CONNECTIVITY_SERVICE);

        if (connectivityManager != null) {
            if (!(connectivityManager.getActiveNetworkInfo() != null && connectivityManager.getActiveNetworkInfo().isConnected())) {
                new AlertDialog.Builder(ctx).setMessage("인터넷에 연결되어 있지 않습니다.").setCancelable(false).setPositiveButton("확인", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.dismiss();
                    }
                }).show();
            } else {
                ok = true;
            }
        }
    }

    @RequiresApi(api = Build.VERSION_CODES.O)
    @Override
    protected void onPreExecute() {
        super.onPreExecute();
        CheckState();
        CreateNotification();
        dbManager = new DBmanager();
    }

    @RequiresApi(api = Build.VERSION_CODES.O)
    @Override
    protected void onProgressUpdate(Void... values) {
        super.onProgressUpdate(values);
    }

    @RequiresApi(api = Build.VERSION_CODES.O)
    private void CreateNotification() {
        Context ctx = MyApplication.ApplicationContext();

        this.mNotifyBuilder = new NotificationCompat.Builder(ctx, "default");
        this.mNotifyBuilder.setContentTitle("데이터를 가져오고 있습니다!");
        this.mNotifyBuilder.setContentText("잠시만 기다려주세요.");
        this.mNotifyBuilder.setSmallIcon(R.mipmap.ic_launcher);

        this.mNotificationManager = (NotificationManager) ctx.getSystemService(ctx.NOTIFICATION_SERVICE);

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            this.mNotificationManager.createNotificationChannel(new NotificationChannel("default", "기본 채널", NotificationManager.IMPORTANCE_DEFAULT));
        }

        this.mNotificationManager.notify(mNotifyID, this.mNotifyBuilder.build());
    }

    private void removeNotification() {
        NotificationManagerCompat.from(MyApplication.ApplicationContext()).cancel(this.mNotifyID);
    }

    @SafeVarargs
    @Override
    protected final AsyncResult doInBackground(ArrayList<String>... arrayLists) {
        AsyncResult result = new AsyncResult();
        if (!ok) {
            return result;
        }

        ArrayList<Post> newPostList = new ArrayList<>();
        Document doc = null;
        String parent = arrayLists[0].get(0);
        String url = arrayLists[0].get(1);

        boolean ok = false;
        do {
            try {
                doc = Jsoup.connect(url).get();
            } catch (Exception e) {
                e.printStackTrace();
                Log.e("Get Items", url + "페이지에서 문서 못가져옴");
                ErrorModel model = new ErrorModel(url, 0);
                result.addFailedItem(model);
                continue;
            }
            String page = doc.text();

            Post newPost = new Post();

            newPost.setUrl(url);
            newPost.setID();
            newPost.setContent(page);
            newPost.setParent(parent);

            newPostList.add(newPost);

            ok = true;

        } while (ok);

        result.setSuccessItem(newPostList);

        return result;
    }

    @Override
    protected void onPostExecute(AsyncResult result) {
        super.onPostExecute(result);

        removeNotification();

        dbManager.addPost(result.getSuccessItem());
    }
}