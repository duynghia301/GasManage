package com.example.gasmanage.view;

import androidx.lifecycle.ViewModelProvider;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.example.gasmanage.R;
import com.example.gasmanage.viewmodel.CustomerViewModel;

public class CustomerDetailFragment extends Fragment {

    private CustomerViewModel mViewModel;
    private TextView textViewCustomerName;
    private TextView textViewCustomerAddress;
    private TextView textViewUsedNumGas;
    private TextView textViewGasLevelType;
    private TextView textViewPrice;
    private Button buttonEdit;
    private Button buttonDelete;

    public static CustomerDetailFragment newInstance(int customerId) {
        CustomerDetailFragment fragment = new CustomerDetailFragment();
        Bundle args = new Bundle();
        args.putInt("CUSTOMER_ID", customerId);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_customer_detail, container, false);
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        mViewModel = new ViewModelProvider(this).get(CustomerViewModel.class);

        // Khởi tạo các TextView và Button
        textViewCustomerName = getView().findViewById(R.id.textViewCustomerName);
        textViewCustomerAddress = getView().findViewById(R.id.textViewCustomerAddress);
        textViewUsedNumGas = getView().findViewById(R.id.textViewUsedNumGas);
        textViewGasLevelType = getView().findViewById(R.id.textViewGasLevelType);
        textViewPrice = getView().findViewById(R.id.textViewPrice);
        buttonEdit = getView().findViewById(R.id.buttonEdit);
        buttonDelete = getView().findViewById(R.id.buttonDelete);

        // Lấy CUSTOMER_ID từ arguments
//        if (getArguments() != null) {
//            int customerId = getArguments().getInt("CUSTOMER_ID");
//            loadCustomerData(customerId);
//        }

        // Xử lý nút Sửa
//        buttonEdit.setOnClickListener(v -> editCustomer());

        // Xử lý nút Xóa
//        buttonDelete.setOnClickListener(v -> deleteCustomer());
    }

//    private void loadCustomerData(int customerId) {
//        mViewModel.getCustomerById(customerId).observe(getViewLifecycleOwner(), customer -> {
//            if (customer != null) {
//                textViewCustomerName.setText(customer.getNA()); // Sử dụng đúng phương thức
//                textViewCustomerAddress.setText(customer.getAddress()); // Sử dụng đúng phương thức
//                textViewUsedNumGas.setText(String.valueOf(customer.getUsedNumGas())); // Sử dụng đúng phương thức
//                textViewGasLevelType.setText(customer.getGasLevelTypeId()); // Sử dụng đúng phương thức
//                textViewPrice.setText(String.format("%.2f", customer.getPrice())); // Sử dụng đúng phương thức
//            } else {
//                Toast.makeText(getActivity(), "Khách hàng không tồn tại", Toast.LENGTH_SHORT).show();
//            }
//        });
//    }

//    private void editCustomer() {
//        int customerId = getArguments().getInt("CUSTOMER_ID");
//        CustomerEditFragment editCustomerFragment = CustomerEditFragment.newInstance(customerId);
//        requireActivity().getSupportFragmentManager().beginTransaction()
//                .replace(R.id.fragment_container, editCustomerFragment)
//                .addToBackStack(null)
//                .commit();
//    }

//    private void deleteCustomer() {
//        int customerId = getArguments().getInt("CUSTOMER_ID");
//        mViewModel.deleteCustomer(customerId);
//        Toast.makeText(getActivity(), "Khách hàng đã được xóa", Toast.LENGTH_SHORT).show();
//        // Quay lại fragment danh sách khách hàng
//        requireActivity().getSupportFragmentManager().popBackStack();
//    }
}
