package com.example.myapplication.Main;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.navigation.ui.NavigationUI;

import com.example.myapplication.CalendarAPI.CalendarAPI;
import com.example.myapplication.CalendarAPI.Models.CalendarActivityRequestCode;
import com.example.myapplication.CalendarAPI.Exceptions.CalendarCantNotUseException;
import com.example.myapplication.CalendarAPI.Exceptions.CalendarNeedUpdateGoogleServiceException;
import com.example.myapplication.CalendarAPI.Exceptions.CalendarNetworkException;
import com.example.myapplication.CalendarAPI.Exceptions.CalendarNotYetFinishBringDataException;
import com.example.myapplication.CalendarAPI.Interfaces.CalenderResultInterface;
import com.example.myapplication.CalendarAPI.Models.CalendarInputEvent;
import com.example.myapplication.CalendarAPI.Models.CalendarResponseData;
import com.example.myapplication.CalendarAPI.Utils.CalendarDataUtil;
import com.example.myapplication.Model.MainModel;
import com.example.myapplication.Model.Plan;
import com.example.myapplication.MyApplication;
import com.example.myapplication.Presenter.Contract;
import com.example.myapplication.Presenter.MainPresenter;
import com.example.myapplication.Presenter.MyService;
import com.example.myapplication.R;
import com.example.myapplication.View.Activity.NewPlanActivity;
import com.google.android.material.navigation.NavigationView;

import java.util.ArrayList;
import java.util.Date;

public class MainActivity extends AppCompatActivity {

    private AppBarConfiguration mAppBarConfiguration;
    private DrawerLayout drawer;

    private Contract.Presenter presenter;
    private MainModel mainModel;


    @SuppressLint("ResourceType")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        presenter = new MainPresenter(findViewById(R.layout.activity_main));
        mainModel = new MainModel(presenter);

        drawer = findViewById(R.id.drawer_layout);
        final NavigationView navigationView = findViewById(R.id.nav_view);
        final NavController navController = Navigation.findNavController(this, R.id.nav_host_fragment);
        mAppBarConfiguration = new AppBarConfiguration.Builder(navController.getGraph()).setDrawerLayout(drawer).build();
        NavigationUI.setupActionBarWithNavController(this, navController, mAppBarConfiguration);

        navigationView.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                item.setChecked(true);
                drawer.closeDrawers();

                int id = item.getItemId();
                String title = item.getTitle().toString();

                if (id == R.id.nav_FirstFragment) {
                    navController.navigate(R.id.FirstFragment);
                }

                if (id == R.id.nav_newPlanActivity) {
//                  플랜 생성 액티비티 첨부
                    Intent intent = new Intent(MyApplication.ApplicationContext(), NewPlanActivity.class);
                    startActivity(intent);
                }

                if (id == R.id.nav_SettingFragment) {
                    navController.navigate(R.id.SettingFragment);
                }

                if (id == R.id.nav_PlanListFragment) {
                    navController.navigate(R.id.PlanListFragment);
                }
                return false;
            }
        });

//        try {
//            long hour1 = 3600 * 1000;
//            CalendarAPI.getInstance().addEvent(this, new CalenderResultInterface() {
//                @Override
//                public void getResult(CalendarResponseData responseData) {
//                    Toast.makeText(MainActivity.this, responseData.toString(), Toast.LENGTH_SHORT).show();
//                }
//
//                @Override
//                public void failedWithActivityResult(CalendarActivityRequestCode reason) {
//                    Toast.makeText(MainActivity.this, "error : "+reason.getCode(), Toast.LENGTH_SHORT).show();
//                }
//
//                @Override
//                public void permissionRevoked() {
//                    Toast.makeText(MainActivity.this, "권한 없음", Toast.LENGTH_SHORT).show();
//                }
//            }, new CalendarInputEvent("제목", "집", "설명", new Date(), new Date(new Date().getTime() + hour1)), new CalendarInputEvent("제목2", "집", "설명", new Date(new Date().getTime() + (hour1 * 2)), new Date(new Date().getTime() + (hour1 * 3))));
//        } catch (CalendarNeedUpdateGoogleServiceException e) {
//            e.printStackTrace();
//        } catch (CalendarCantNotUseException e) {
//            e.printStackTrace();
//        } catch (CalendarNetworkException e) {
//            e.printStackTrace();
//        } catch (CalendarNotYetFinishBringDataException e) {
//            e.printStackTrace();
//        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();

        if (id == R.id.nav_home) {
            drawer.openDrawer(GravityCompat.START);
        }

        if (id == R.id.start_fetch_data) {
            ArrayList<Plan> plans = new ArrayList<>();
            plans = mainModel.getPlan();
            presenter.startFetchData(plans);
        }

        if (id == R.id.start_service) {
            Intent intent = new Intent(MyApplication.ApplicationContext(), MyService.class);
            startService(intent);
        }

        if (id == R.id.stop_service) {
            Intent intent = new Intent(MyApplication.ApplicationContext(), MyService.class);
            stopService(intent);
        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    public boolean onSupportNavigateUp() {
        NavController navController = Navigation.findNavController(this, R.id.nav_host_fragment);
        return NavigationUI.navigateUp(navController, mAppBarConfiguration)
                || super.onSupportNavigateUp();
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable @org.jetbrains.annotations.Nullable Intent data) {
        if (CalendarAPI.getInstance().isRequestCode(requestCode)) {
            CalendarAPI.getInstance().progressRequest(requestCode, resultCode, data);
        }

        super.onActivityResult(requestCode, resultCode, data);
    }
}