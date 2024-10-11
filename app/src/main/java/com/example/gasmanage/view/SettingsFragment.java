package com.example.gasmanage.view;

import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.IBinder;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Switch;

import androidx.fragment.app.Fragment;

import com.example.gasmanage.R;
import com.example.gasmanage.utils.MusicPlayer;

public class SettingsFragment extends Fragment {
    private Switch switchPlayMusic;



    private MusicPlayer musicPlayerService;
    private boolean isBound = false;

    private static final String PREFS_NAME = "MusicSettings";
    private static final String PREF_MUSIC_ON = "music_on";

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_settings, container, false);
        switchPlayMusic = view.findViewById(R.id.switch_play_music);

        // Lấy cài đặt âm nhạc và cập nhật trạng thái của switch
        switchPlayMusic.setChecked(getMusicSetting());

        switchPlayMusic.setOnCheckedChangeListener((buttonView, isChecked) -> {
            if (isChecked) {
                musicPlayerService.playMusic(); // Bắt đầu phát nhạc
            } else {
                musicPlayerService.stopMusic();

            }
            saveMusicSetting(isChecked); // Lưu cài đặt
        });

        return view;
    }

    private void saveMusicSetting(boolean isOn) {
        SharedPreferences preferences = getActivity().getSharedPreferences(PREFS_NAME, Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = preferences.edit();
        editor.putBoolean(PREF_MUSIC_ON, isOn);
        editor.apply();
    }

    private boolean getMusicSetting() {
        SharedPreferences preferences = getActivity().getSharedPreferences(PREFS_NAME, Context.MODE_PRIVATE);
        return preferences.getBoolean(PREF_MUSIC_ON, true); // Mặc định bật nhạc
    }

    @Override
    public void onStart() {
        super.onStart();
        Intent serviceIntent = new Intent(getActivity(), MusicPlayer.class);
        getActivity().bindService(serviceIntent, connection, Context.BIND_AUTO_CREATE);
    }

    @Override
    public void onStop() {
        super.onStop();
        if (isBound) {
            getActivity().unbindService(connection);
            isBound = false;
        }
    }

    private ServiceConnection connection = new ServiceConnection() {
        @Override
        public void onServiceConnected(ComponentName name, IBinder service) {
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
}
