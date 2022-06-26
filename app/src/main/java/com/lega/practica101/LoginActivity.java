package com.lega.practica101;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager2.widget.ViewPager2;

import com.lega.practica101.databinding.ActivityLoginBinding;
import com.lega.practica101.view.PageAdapterOnboarding;

public class LoginActivity extends AppCompatActivity {

    private ActivityLoginBinding binding;
    private PageAdapterOnboarding pageAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityLoginBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        pageAdapter = new PageAdapterOnboarding(this);
        binding.VPA1ContainerPages.setAdapter(pageAdapter);
        binding.VPA1ContainerPages.setOrientation(ViewPager2.ORIENTATION_HORIZONTAL);

    }

}