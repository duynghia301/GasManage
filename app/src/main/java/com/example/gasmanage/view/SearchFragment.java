package com.example.gasmanage.view;

import android.database.Cursor;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.SimpleCursorAdapter;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;


import com.example.gasmanage.R;
import com.example.gasmanage.database.DatabaseHelper;

public class SearchFragment extends Fragment {

    private EditText editTextSearch;
    private ListView listViewResults;
    private DatabaseHelper databaseHelper;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_search, container, false);

        editTextSearch = view.findViewById(R.id.edittext_search);
        listViewResults = view.findViewById(R.id.listview_results);

        databaseHelper = new DatabaseHelper(getActivity());

        // Hiển thị tất cả khách hàng khi fragment được khởi tạo
        displayAllCustomers();

        // Thêm TextWatcher để tìm kiếm theo từng ký tự
        editTextSearch.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
                // Không cần làm gì trước khi văn bản thay đổi
            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                // Gọi phương thức tìm kiếm khi văn bản thay đổi
                performSearch(s.toString().trim());
            }

            @Override
            public void afterTextChanged(Editable s) {
                // Không cần làm gì sau khi văn bản thay đổi
            }
        });
        listViewResults.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

            }
            
            public void onItemClick(AdapterView<?> parent, View view, int position, int id) {
                openDetailFragment(id); // Gọi phương thức mở fragment chi tiết
            }
        });



        return view;
    }



    private void performSearch(String query) {
        try {
            Cursor cursor;
            if (!query.isEmpty()) {
                cursor = databaseHelper.searchCustomers(query);
            } else {
                // Nếu trường tìm kiếm rỗng, hiển thị tất cả khách hàng
                cursor = databaseHelper.getAllCustomers();
            }

            if (cursor != null && cursor.getCount() > 0) {
                SimpleCursorAdapter adapter = new SimpleCursorAdapter(
                        getActivity(),
                        R.layout.list_item,
                        cursor,
                        new String[]{ "NAME", "ADDRESS"},
                        new int[]{R.id.textview_name, R.id.textview_address},
                        0);

                listViewResults.setAdapter(adapter);
            } else {
                listViewResults.setAdapter(null);
            }
        } catch (Exception e) {
            Toast.makeText(getActivity(), "Error occurred: " + e.getMessage(), Toast.LENGTH_LONG).show();
        }
    }

    private void displayAllCustomers() {
        try {
            Cursor cursor = databaseHelper.getAllCustomers();
            if (cursor != null && cursor.getCount() > 0) {
                SimpleCursorAdapter adapter = new SimpleCursorAdapter(
                        getActivity(),
                        R.layout.list_item,
                        cursor,
                        new String[]{"NAME", "ADDRESS"},
                        new int[]{R.id.textview_name, R.id.textview_address},
                        0);

                listViewResults.setAdapter(adapter);
            } else {
                Toast.makeText(getActivity(), "No customers found", Toast.LENGTH_SHORT).show();
            }
        } catch (Exception e) {
            Toast.makeText(getActivity(), "Error occurred: " + e.getMessage(), Toast.LENGTH_LONG).show();
        }
    }
    private void openDetailFragment(int ID) {
        CustomerDetailFragment detailFragment = CustomerDetailFragment.newInstance((int) ID); // Truyền ID vào fragment
        FragmentTransaction transaction = requireActivity().getSupportFragmentManager().beginTransaction();
        transaction.replace(R.id.fragment_container, detailFragment); // Đảm bảo sử dụng ID container phù hợp
        transaction.addToBackStack(null); // Thêm vào back stack để có thể quay lại
        transaction.commit();
    }


}
