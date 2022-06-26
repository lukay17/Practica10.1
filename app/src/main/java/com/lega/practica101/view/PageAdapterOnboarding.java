package com.lega.practica101.view;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.viewpager2.adapter.FragmentStateAdapter;

import com.lega.practica101.fragment.ViewFragment;
import com.lega.practica101.onboarding.OnBoardingPage1;
import com.lega.practica101.onboarding.OnBoardingPage2;
import com.lega.practica101.onboarding.OnBoardingPage3;


public class PageAdapterOnboarding extends FragmentStateAdapter {

    public PageAdapterOnboarding(@NonNull FragmentActivity fragmentActivity) {
        super(fragmentActivity);
    }

    @NonNull
    @Override
    public Fragment createFragment(int position) {
        Fragment fragment;
        switch (position){
            case 0:
                fragment = OnBoardingPage1.newInstance();
                break;
            case 1:
                fragment = OnBoardingPage2.newInstance();
                break;
            case 2:
                fragment = OnBoardingPage3.newInstance();
                break;
            case 3:
                fragment = ViewFragment.newInstance();
                break;
            default:
                throw  new IllegalStateException("Unexpected value " + position);
        }
        return fragment;
    }

    @Override
    public int getItemCount() {
        return 4;
    }
}
