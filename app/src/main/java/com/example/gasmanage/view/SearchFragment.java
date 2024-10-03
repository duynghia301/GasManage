package com.example.gasmanage.view;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.example.gasmanage.R;

public class SearchFragment extends Fragment {

    private EditText editTextSearch;
    private ListView listViewResults;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_search, container, false);

        editTextSearch = view.findViewById(R.id.edittext_search);
        listViewResults = view.findViewById(R.id.listview_results);
        Button buttonSearch = view.findViewById(R.id.button_search);

        buttonSearch.setOnClickListener(v -> {
            String query = editTextSearch.getText().toString();
            if (!query.isEmpty()) {
                // TODO: Thực hiện tìm kiếm khách hàng theo tên hoặc địa chỉ
                Toast.makeText(getActivity(), "Searching for: " + query, Toast.LENGTH_SHORT).show();
            } else {
                Toast.makeText(getActivity(), "Please enter a name or address", Toast.LENGTH_SHORT).show();
            }
        });

        return view;
    }
}
