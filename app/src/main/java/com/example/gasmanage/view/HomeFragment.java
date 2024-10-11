package com.example.gasmanage.view;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.example.gasmanage.R;
import com.example.gasmanage.model.Customer; // Import Activity thêm khách hàng

public class HomeFragment extends Fragment {

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_home, container, false);

        Button buttonAddCustomer = view.findViewById(R.id.button_add_customer);
        Button buttonViewCustomers = view.findViewById(R.id.button_view_customers);

        // Sự kiện thêm khách hàng
        buttonAddCustomer.setOnClickListener(v -> {

        });

        // Sự kiện xem danh sách khách hàng
        buttonViewCustomers.setOnClickListener(v -> {
            // TODO: Mở danh sách khách hàng
        });

        return view;
    }
}
