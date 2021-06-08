package com.example.myapplication.Main;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.navigation.NavController;
import androidx.navigation.NavDestination;
import androidx.navigation.Navigation;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.navigation.ui.NavigationUI;

import com.example.myapplication.Model.MainModel;
import com.example.myapplication.Model.Plan;
import com.example.myapplication.MyApplication;
import com.example.myapplication.Presenter.Contract;
import com.example.myapplication.Presenter.MainPresenter;
import com.example.myapplication.Presenter.MyService;
import com.example.myapplication.R;
import com.example.myapplication.View.Activity.NewPlanActivity;
import com.google.android.material.navigation.NavigationView;

import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    private AppBarConfiguration mAppBarConfiguration;
    private DrawerLayout drawer;
    private NavigationView navigationView;

    private Contract.Presenter presenter;
    private MainModel mainModel;

    @SuppressLint("ResourceType")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        presenter = new MainPresenter();
        mainModel = new MainModel();

        drawer = findViewById(R.id.drawer_layout);
        navigationView = findViewById(R.id.nav_view);
        final NavController navController = Navigation.findNavController(this, R.id.nav_host_fragment);
        navController.addOnDestinationChangedListener(new NavController.OnDestinationChangedListener() {
            @Override
            public void onDestinationChanged(@NonNull @NotNull NavController controller, @NonNull @NotNull NavDestination destination, @Nullable @org.jetbrains.annotations.Nullable Bundle arguments) {
                if (destination.getId() != R.id.nav_SettingFragment && destination.getId() != R.id.nav_PlanListFragment ) {
                    navigationView.setCheckedItem(R.id.nav_PlanListFragment);
                }
            }
        });
        mAppBarConfiguration = new AppBarConfiguration.Builder(navController.getGraph()).setDrawerLayout(drawer).build();
        NavigationUI.setupActionBarWithNavController(this, navController, mAppBarConfiguration);

        navigationView.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
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
                return true;
            }
        });
    }

    @Override
    protected void onResume() {
        super.onResume();
        navigationView.setCheckedItem(R.id.nav_PlanListFragment);
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
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
                startForegroundService(intent);
            } else {
                startService(intent);
            }
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


}