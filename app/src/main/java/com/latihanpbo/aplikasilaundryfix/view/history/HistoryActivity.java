package com.latihanpbo.aplikasilaundryfix.view.history;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.navigation.NavigationBarView;
import com.latihanpbo.aplikasilaundryfix.R;
import com.latihanpbo.aplikasilaundryfix.model.ModelLaundry;
import com.latihanpbo.aplikasilaundryfix.view.main.MainActivity;
import com.latihanpbo.aplikasilaundryfix.view.profile.ProfileActivity;
import com.latihanpbo.aplikasilaundryfix.viewmodel.LaundryViewModel;

import java.util.ArrayList;
import java.util.List;

public class HistoryActivity extends AppCompatActivity implements HistoryAdapter.HistoryAdapterCallback{

    List<ModelLaundry> modelLaundryList = new ArrayList<>();
    HistoryAdapter historyAdapter;
    LaundryViewModel laundryViewModel;
    RecyclerView rvHistory;
    TextView tvNotFound;

    BottomNavigationView bottomNavigationView;

//    androidx.appcompat.widget.Toolbar toolbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_history);

        bottomNavigationView = findViewById(R.id.bottom_navigation);

        setStatusbar();
        setInitLayout();
        setViewModel();

        bottomNavigationView = findViewById(R.id.bottom_navigation);
        bottomNavigationView.setSelectedItemId(R.id.navigation_history);
        bottomNavigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                // Tambahkan logika untuk menangani item yang dipilih di sini
                if (item.getItemId() == R.id.navigation_history){
                    return true;
                } else if (item.getItemId() == R.id.navigation_profile){
                    Intent profileIntent = new Intent(HistoryActivity.this, ProfileActivity.class);
                    finish();
                    startActivity(profileIntent);
                    return true;
                } else if (item.getItemId() == R.id.navigation_home){
                    Intent mainIntent = new Intent(HistoryActivity.this, MainActivity.class);
                    finish();
                    startActivity(mainIntent);
                    return true;
                }
                return false;
            }
        });




//        toolbar = findViewById(R.id.toolbar);
//        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                onBackPressed();
//            }
//        });

        bottomNavigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                // Tambahkan logika untuk menangani item yang dipilih di sini
                if (item.getItemId() == R.id.navigation_home){
                    Intent historyIntent = new Intent(HistoryActivity.this, MainActivity.class);
                    finish();
                    startActivity(historyIntent);
                    return true;
                } else if (item.getItemId() == R.id.navigation_profile){
                    Intent profileIntent = new Intent(HistoryActivity.this, ProfileActivity.class);
                    finish();
                    startActivity(profileIntent);
                    return true;
                } else if (item.getItemId() == R.id.navigation_history){
                    return true;
                }
                return false;
            }
        });

    }

    private void setStatusbar() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            getWindow().getDecorView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_LAYOUT_STABLE | View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN | View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR);
        }

        if (Build.VERSION.SDK_INT >= 21) {
            setWindowFlag(this, WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS, false);
            getWindow().setStatusBarColor(Color.TRANSPARENT);
        }
    }

    private void setInitLayout() {
        rvHistory = findViewById(R.id.rvHistory);
        tvNotFound = findViewById(R.id.tvNotFound);

        tvNotFound.setVisibility(View.GONE);

        historyAdapter = new HistoryAdapter(this, modelLaundryList, this);
        rvHistory.setHasFixedSize(true);
        rvHistory.setLayoutManager(new LinearLayoutManager(this));
        rvHistory.setAdapter(historyAdapter);
    }

    private void setViewModel() {
        laundryViewModel = ViewModelProviders.of(this).get(LaundryViewModel.class);
        laundryViewModel.getDataLaundry().observe(this, modelLaundryList -> {
            if (modelLaundryList.size() != 0) {
                historyAdapter.setDataAdapter(modelLaundryList);
            } else {
                tvNotFound.setVisibility(View.VISIBLE);
                rvHistory.setVisibility(View.GONE);
            }
        });
    }

    @Override
    public void onDelete(ModelLaundry modelLaundry) {
        AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(this);
        alertDialogBuilder.setMessage("Hapus riwayat ini?");
        alertDialogBuilder.setPositiveButton("Ya, Hapus", (dialogInterface, i) -> {
            int uid = modelLaundry.uid;
            laundryViewModel.deleteDataById(uid);
            Toast.makeText(HistoryActivity.this, "Data yang dipilih sudah dihapus", Toast.LENGTH_SHORT).show();
        });

        alertDialogBuilder.setNegativeButton("Batal", (dialogInterface, i) -> dialogInterface.cancel());

        AlertDialog alertDialog = alertDialogBuilder.create();
        alertDialog.show();
    }

    @Override
    protected void onResume(){
        super.onResume();
        setViewModel();
    }

    public static void setWindowFlag(Activity activity, final int bits, boolean on) {
        Window window = activity.getWindow();
        WindowManager.LayoutParams layoutParams = window.getAttributes();
        if (on) {
            layoutParams.flags |= bits;
        } else {
            layoutParams.flags &= ~bits;
        }
        window.setAttributes(layoutParams);
    }

}