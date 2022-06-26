package com.lega.practica101;

import static android.Manifest.permission.ACCESS_COARSE_LOCATION;
import static android.Manifest.permission.ACCESS_FINE_LOCATION;
import static com.lega.practica101.repository.constans.KEY_NAME;
import static com.lega.practica101.repository.constans.KEY_SURNAME;

import android.Manifest;
import android.annotation.SuppressLint;
import android.content.pm.PackageManager;
import android.graphics.Color;
import android.graphics.PorterDuff;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import com.google.android.material.snackbar.Snackbar;
import com.google.android.material.tabs.TabLayout;
import com.google.android.material.tabs.TabLayoutMediator;
import com.lega.practica101.databinding.ActivityHomeBinding;
import com.lega.practica101.view.PageAdapterHome;

public class HomeActivity extends AppCompatActivity {
    private ActivityHomeBinding binding;
    private String welcome = "";
    private String name = "";
    private String surname = "";
    private PageAdapterHome pageAdapter;
    private static final int REQUESTCODE = 200;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityHomeBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        setSupportActionBar(binding.HAToolbar);

        binding.HAToolbar.setNavigationIcon(R.drawable.home);

        if(getIntent().getExtras() != null){
            name = getIntent().getExtras().getString(KEY_NAME, "No hay Nombre");
            surname = getIntent().getExtras().getString(KEY_SURNAME, "No hay Apellido");
            welcome = "Bienvenido " + name + " " + surname;
            Snackbar.make(binding.HAToolbar, welcome, Snackbar.LENGTH_SHORT).show();
        }

        pageAdapter = new PageAdapterHome(this);
        binding.VPA1ContainerPages.setAdapter(pageAdapter);
        binding.VPA1ContainerPages.setCurrentItem(0, false);

        new TabLayoutMediator(binding.VPA1Tabs, binding.VPA1ContainerPages,((tab, position) -> {
            if(position == 0){
                tab.view.setBackgroundColor(Color.rgb(159,252,253));
                tab.setIcon(R.drawable.camara);
            }else  if(position == 1){
                tab.view.setBackgroundColor(Color.rgb(226,211,242));
                tab.setIcon(R.drawable.baseline_directions_car_black_24dp);
            }else  if(position == 2) {
                tab.view.setBackgroundColor(Color.rgb(242,186,237));
                tab.setIcon(R.drawable.baseline_terrain_black_24dp);
            }
            else  if(position == 3) {
                tab.view.setBackgroundColor(Color.rgb(255,241,130));
                tab.setIcon(R.drawable.baseline_face_black_24dp);
            }
        })).attach();

        binding.VPA1Tabs.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                tab.getIcon().setColorFilter(Color.WHITE, PorterDuff.Mode.SRC_IN);
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {
                tab.getIcon().setColorFilter(Color.BLACK, PorterDuff.Mode.SRC_IN);
            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {

            }

        });

        checkRequestPermission();
    }

    private void checkRequestPermission() {
        if(ContextCompat.checkSelfPermission(this, Manifest.permission.ACCESS_COARSE_LOCATION) == PackageManager.PERMISSION_GRANTED && ContextCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) == PackageManager.PERMISSION_GRANTED){
            Toast.makeText(this, "Permisos de Localizacion Consedidos",Toast.LENGTH_LONG).show();
        }else{
            ActivityCompat.requestPermissions(this, new String[]{ACCESS_COARSE_LOCATION, ACCESS_FINE_LOCATION}, REQUESTCODE);
            Toast.makeText(this, "Permisos de Localizacion NO HAN SIDO Consedidos",Toast.LENGTH_LONG).show();
        }
    }

    @SuppressLint("MissingSuperCall")
    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        if(requestCode == REQUESTCODE && grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED){
            Snackbar.make(binding.HAToolbar, "Vamos Acceder a su Ubicaciòn",Snackbar.LENGTH_LONG).show();
        }else{
            Toast.makeText(this, "Pues sin permiso me voy",Toast.LENGTH_LONG).show();
            try{
                Thread.sleep(2000);
                finishAndRemoveTask();
                //finish();
            }catch (InterruptedException e){
                e.printStackTrace();
            }
        }
    }
}