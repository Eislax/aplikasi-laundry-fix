package com.latihanpbo.aplikasilaundryfix.view.main;

import android.Manifest;
import android.app.Activity;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.latihanpbo.aplikasilaundryfix.R;
import com.latihanpbo.aplikasilaundryfix.view.cucibasah.CuciBasahActivity;
import com.latihanpbo.aplikasilaundryfix.view.dryclean.DryCleanActivity;
import com.latihanpbo.aplikasilaundryfix.view.history.HistoryActivity;
import com.latihanpbo.aplikasilaundryfix.view.informasi.cucibasah_informasi;
import com.latihanpbo.aplikasilaundryfix.view.informasi.dryCleaning_informasi;
import com.latihanpbo.aplikasilaundryfix.view.informasi.premiumWash_informasi;
import com.latihanpbo.aplikasilaundryfix.view.informasi.setrika_informasi;
import com.latihanpbo.aplikasilaundryfix.view.ironing.IroningActivity;
import com.latihanpbo.aplikasilaundryfix.view.premiumwash.PremiumWashActivity;
import com.latihanpbo.aplikasilaundryfix.view.profile.ProfileActivity;

import im.delight.android.location.SimpleLocation;

import java.util.ArrayList;
import java.util.List;




public class MainActivity extends AppCompatActivity {

    int REQ_PERMISSION = 100;
    double strCurrentLatitude;
    double strCurrentLongitude;
    String strCurrentLocation;
//    GoogleMap mapsView;
    SimpleLocation simpleLocation;
//    ProgressDialog progressDialog;
//    MainViewModel mainViewModel;
    MenuAdapter menuAdapter;
//    MainAdapter mainAdapter;
    ModelMenu modelMenu;
    RecyclerView rvMenu, rvRekomendasi;
//    LinearLayout layoutHistory;
    List<ModelMenu> modelMenuList = new ArrayList<>();
    BottomNavigationView bottomNavigationView;


    ImageView cuciBsh, dry, premium, setrika;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Pindah ke Halaman cuci basah
        cuciBsh = findViewById(R.id.cuciBsh);
        cuciBsh.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent pindah = new Intent(MainActivity.this, cucibasah_informasi.class);
                startActivity(pindah);
            }
        });


        //pindah ke halaman dry cleaning
        dry = findViewById(R.id.dryCln);
        dry.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent pindah = new Intent(MainActivity.this, dryCleaning_informasi.class);
                startActivity(pindah);
            }
        });


        // Pindah ke halaman premium wash
        premium = findViewById(R.id.PremiunWash);
        premium.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent pindah = new Intent(MainActivity.this, premiumWash_informasi.class);
                startActivity(pindah);
            }
        });


        // Pindah ke halaman setrika
        setrika = findViewById(R.id.setrika);
        setrika.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent pindah = new Intent(MainActivity.this, setrika_informasi.class);
                startActivity(pindah);
            }
        });

        BottomNavigationView bottomNavigationView = findViewById(R.id.bottom_navigation);
        bottomNavigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                // Tambahkan logika untuk menangani item yang dipilih di sini
               if (item.getItemId() == R.id.navigation_history){
                   Intent historyIntent = new Intent(MainActivity.this, HistoryActivity.class);
                   startActivity(historyIntent);
                   return true;
                } else if (item.getItemId() == R.id.navigation_profile){
                   Intent profileIntent = new Intent(MainActivity.this, ProfileActivity.class);
                   startActivity(profileIntent);
                   return true;
               } else if (item.getItemId() == R.id.navigation_home){
                   return true;
               }
                return false;
            }
        });


        setStatusbar();
        setPermission();
        setLocation();
        setInitLayout();
        setMenu();
//        getLocationViewModel();
    }

    private void setLocation() {
        simpleLocation = new SimpleLocation(this);

        if (!simpleLocation.hasLocationEnabled()) {
            SimpleLocation.openSettings(this);
        }

        //get location
        strCurrentLatitude = simpleLocation.getLatitude();
        strCurrentLongitude = simpleLocation.getLongitude();

        //set location lat long
        strCurrentLocation = strCurrentLatitude + "," + strCurrentLongitude;
    }

    private void setInitLayout() {
        rvMenu = findViewById(R.id.rvMenu);
//        rvRekomendasi = findViewById(R.id.rvRekomendasi);
//        layoutHistory = findViewById(R.id.layoutHistory);

//        progressDialog = new ProgressDialog(this);
//        progressDialog.setTitle("Mohon Tunggu…");
//        progressDialog.setCancelable(false);
//        progressDialog.setMessage("sedang menampilkan lokasi");

        rvMenu.setLayoutManager(new GridLayoutManager(this, 2, RecyclerView.VERTICAL, false));
        rvMenu.setHasFixedSize(true);

//        mainAdapter = new MainAdapter(this);
//        rvRekomendasi.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false));
//        rvRekomendasi.setAdapter(mainAdapter);
//        rvRekomendasi.setHasFixedSize(true);

//        layoutHistory.setOnClickListener(v -> {
//            Intent intent = new Intent(MainActivity.this, HistoryActivity.class);
//            startActivity(intent);
//        });
    }

    private void setPermission() {
        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED
                && ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.ACCESS_FINE_LOCATION}, REQ_PERMISSION);
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, String[] permissions, int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        for (int grantResult : grantResults) {
            if (grantResult == PackageManager.PERMISSION_GRANTED) {
                Intent intent = getIntent();
                finish();
                startActivity(intent);
            }
        }
    }

//    @Override
//    public void onMapReady(@NonNull GoogleMap googleMap) {
//        this.mapsView = googleMap;
//
//        //viewmodel
//        getLocationViewModel();
//    }

//    @Override
//    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
//        super.onActivityResult(requestCode, resultCode, data);
//        if (requestCode == REQ_PERMISSION && resultCode == RESULT_OK) {
//
//            //load viewmodel
//            getLocationViewModel();
//        }
//    }

    private void setStatusbar() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            getWindow().getDecorView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_LAYOUT_STABLE | View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN | View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR);
        }

        if (Build.VERSION.SDK_INT >= 21) {
            setWindowFlag(this, WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS, false);
            getWindow().setStatusBarColor(Color.TRANSPARENT);
        }
    }

    private void setMenu() {
        modelMenu = new ModelMenu("Cuci Basah", R.drawable.ic_cuci_basah);
        modelMenuList.add(modelMenu);
        modelMenu = new ModelMenu("Dry Cleaning", R.drawable.ic_dry_cleaning);
        modelMenuList.add(modelMenu);
        modelMenu = new ModelMenu("Premium Wash", R.drawable.ic_premium_wash);
        modelMenuList.add(modelMenu);
        modelMenu = new ModelMenu("Setrika", R.drawable.ic_setrika);
        modelMenuList.add(modelMenu);

        menuAdapter = new MenuAdapter(this, modelMenuList);
        rvMenu.setAdapter(menuAdapter);

        menuAdapter.setOnItemClickListener(modelMenu -> {
            if (modelMenu.getTvTitle().equals("Cuci Basah")) {
                Intent intent = new Intent(new Intent(MainActivity.this, CuciBasahActivity.class));
                intent.putExtra(CuciBasahActivity.DATA_TITLE, modelMenu.getTvTitle());
                startActivity(intent);
            } else if (modelMenu.getTvTitle().equals("Dry Cleaning")) {
                Intent intent = new Intent(new Intent(MainActivity.this, DryCleanActivity.class));
                intent.putExtra(DryCleanActivity.DATA_TITLE, modelMenu.getTvTitle());
                startActivity(intent);
            } else if (modelMenu.getTvTitle().equals("Premium Wash")) {
                Intent intent = new Intent(new Intent(MainActivity.this, PremiumWashActivity.class));
                intent.putExtra(PremiumWashActivity.DATA_TITLE, modelMenu.getTvTitle());
                startActivity(intent);
            } else if (modelMenu.getTvTitle().equals("Setrika")) {
                Intent intent = new Intent(new Intent(MainActivity.this, IroningActivity.class));
                intent.putExtra(IroningActivity.DATA_TITLE, modelMenu.getTvTitle());
                startActivity(intent);
            }
        });
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