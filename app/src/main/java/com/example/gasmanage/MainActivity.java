package com.example.gasmanage;

import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.ServiceConnection;
import android.os.Bundle;
import android.util.Log;
import android.widget.Button;
import android.widget.ImageButton;

import androidx.appcompat.app.AppCompatActivity;

import com.example.gasmanage.database.DatabaseHelper;
import com.example.gasmanage.utils.MusicPlayer;
import com.example.gasmanage.view.CustomerDetailFragment;
import com.example.gasmanage.view.HomeFragment;
import com.example.gasmanage.view.ScheduleFragment;
import com.example.gasmanage.view.SearchFragment;
import com.example.gasmanage.view.SettingsFragment;

public class MainActivity extends AppCompatActivity {
    private DatabaseHelper myDB;
    private MusicPlayer musicPlayerService;
    private boolean isBound = false;

    private static final String PREFS_NAME = "MusicSettings";
    private static final String PREF_MUSIC_ON = "music_on";
    private DatabaseHelper databaseHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        DatabaseHelper databaseHelper = new DatabaseHelper(this);

// Thêm dữ liệu vào bảng customer
          // Khởi động dịch vụ âm nhạc
        Intent intent = new Intent(this, MusicPlayer.class);
        startService(intent);

        // Load HomeFragment mặc định
        if (savedInstanceState == null) {
            getSupportFragmentManager().beginTransaction()
                    .replace(R.id.fragment_container, new HomeFragment())
                    .commit();
        }

        // Khai báo các nút
        ImageButton buttonHome = findViewById(R.id.button_home);
        ImageButton buttonSearch = findViewById(R.id.button_search);
        ImageButton buttonSettings = findViewById(R.id.button_settings);
        ImageButton buttonSchedule = findViewById(R.id.button_schedule);
        ImageButton buttonCustomer = findViewById(R.id.button_customer);

        // Thiết lập sự kiện cho các nút
        buttonHome.setOnClickListener(v -> {
            getSupportFragmentManager().beginTransaction()
                    .replace(R.id.fragment_container, new HomeFragment())
                    .commit();
        });

        buttonSearch.setOnClickListener(v -> {
            getSupportFragmentManager().beginTransaction()
                    .replace(R.id.fragment_container, new SearchFragment())
                    .commit();
        });

        buttonSettings.setOnClickListener(v -> {
            getSupportFragmentManager().beginTransaction()
                    .replace(R.id.fragment_container, new SettingsFragment())
                    .commit();
        });

        buttonSchedule.setOnClickListener(v->{
            getSupportFragmentManager().beginTransaction()
                    .replace(R.id.fragment_container, new ScheduleFragment())
                    .commit();
        });
        buttonCustomer.setOnClickListener(v->{
            getSupportFragmentManager().beginTransaction()
                    .replace(R.id.fragment_container, new CustomerDetailFragment())
                    .commit();
        });
    }

    @Override
    protected void onStart() {
        super.onStart();
        Intent serviceIntent = new Intent(this, MusicPlayer.class);
        bindService(serviceIntent, connection, Context.BIND_AUTO_CREATE);
    }

    @Override
    protected void onStop() {
        super.onStop();
        if (isBound) {
            unbindService(connection);
            isBound = false;
        }
    }

    @Override
    protected void onResume() {
        super.onResume();
        if (musicPlayerService != null && getMusicSetting()) {
            musicPlayerService.playMusic(); // Tiếp tục phát nhạc nếu cài đặt đang bật
        }
    }

    @Override
    protected void onPause() {
        super.onPause();
        if (musicPlayerService != null) {
            musicPlayerService.stopMusic(); // Tạm dừng nhạc khi thoát ứng dụng
        }
    }

    private ServiceConnection connection = new ServiceConnection() {
        @Override
        public void onServiceConnected(ComponentName name, android.os.IBinder service) {
            MusicPlayer.LocalBinder binder = (MusicPlayer.LocalBinder) service;
            musicPlayerService = binder.getService();
            isBound = true;

            // Tiếp tục phát nhạc nếu cài đặt đang bật
            if (getMusicSetting()) {
                musicPlayerService.playMusic();
            }
        }

        @Override
        public void onServiceDisconnected(ComponentName name) {
            musicPlayerService = null;
            isBound = false;
        }
    };

    private boolean getMusicSetting() {
        SharedPreferences preferences = getSharedPreferences(PREFS_NAME, MODE_PRIVATE);
        return preferences.getBoolean(PREF_MUSIC_ON, true); // Mặc định bật nhạc
    }
}
