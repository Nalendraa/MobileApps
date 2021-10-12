package com.example.navigationbar_e41202285;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import android.os.Bundle;
import android.widget.Toast;

import com.etebarian.meowbottomnavigation.MeowBottomNavigation;

public class MainActivity extends AppCompatActivity {

    // initialize variable
    MeowBottomNavigation bottomNavigation;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // penugasan variable
        bottomNavigation = findViewById(R.id.bottom_navigation);

        // menambahkan item menu
        bottomNavigation.add(new MeowBottomNavigation.Model(1, R.drawable.ic_notification));
        bottomNavigation.add(new MeowBottomNavigation.Model(2, R.drawable.ic_home));
        bottomNavigation.add(new MeowBottomNavigation.Model(3,R.drawable.ic_setting));

        bottomNavigation.setOnShowListener(new MeowBottomNavigation.ShowListener() {
            @Override
            public void onShowItem(MeowBottomNavigation.Model item) {
                 // initilaize fragment
                Fragment fragment = null;

                // check kondisi
                switch (item.getId()){
                    case 1:
                        // ketika id 1
                        // initialize notif fragment
                        fragment = new NotificationFragment();
                        break;
                    case 2:
                        // ketika id 2
                        // initialize home fragment
                        fragment = new HomeFragment();
                        break;
                    case 3:
                        // ketika id 3
                        // initialize setting fragment
                        fragment = new SettingFragment();
                        break;
                }

                //memuat fragment
                loadFragment(fragment);
            }
        });

        // menetapkan notofikasi
        bottomNavigation.setCount(1, "10");

        // menetapkan home
        bottomNavigation.show(2, true);

        bottomNavigation.setOnClickMenuListener(new MeowBottomNavigation.ClickListener() {
            @Override
            public void onClickItem(MeowBottomNavigation.Model item) {

                //menampilkan toast
                Toast.makeText(getApplicationContext()
                        , "You Clicked " + item.getId()
                        , Toast.LENGTH_SHORT).show();
            }
        });

        bottomNavigation.setOnReselectListener(new MeowBottomNavigation.ReselectListener() {
            @Override
            public void onReselectItem(MeowBottomNavigation.Model item) {

                //menampilkan toast
                Toast.makeText(getApplicationContext()
                        , "You Reselected " + item.getId()
                        , Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void loadFragment(Fragment fragment) {

        //mengganti fragment
        getSupportFragmentManager()
                .beginTransaction()
                .replace(R.id.frame_layout,fragment)
                .commit();
    }
}