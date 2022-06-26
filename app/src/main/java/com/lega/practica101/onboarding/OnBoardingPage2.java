package com.lega.practica101.onboarding;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.lega.practica101.databinding.FragmentOnBoardingPage2Binding;

public class  OnBoardingPage2 extends Fragment {

    private FragmentOnBoardingPage2Binding binding;
    private String next = "THREE";
    private String skip = "ONE";


    public OnBoardingPage2() {
        // Required empty public constructor
    }

    public static OnBoardingPage2 newInstance() {
        return new OnBoardingPage2();
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        binding = FragmentOnBoardingPage2Binding.inflate(getLayoutInflater());
        return binding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
    }

    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);
    }

    @Override
    public void onDetach() {
        super.onDetach();
    }

}