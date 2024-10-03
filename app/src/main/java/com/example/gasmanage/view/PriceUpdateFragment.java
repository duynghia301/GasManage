package com.example.gasmanage.view;

import androidx.lifecycle.ViewModelProvider;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.gasmanage.R;
import com.example.gasmanage.viewmodel.GasLevelViewModel;

public class PriceUpdateFragment extends Fragment {

    private GasLevelViewModel mViewModel;

    public static PriceUpdateFragment newInstance() {
        return new PriceUpdateFragment();
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_price_update, container, false);
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        mViewModel = new ViewModelProvider(this).get(GasLevelViewModel.class);
        // TODO: Use the ViewModel
    }

}