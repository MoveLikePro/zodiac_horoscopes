package com.enigmaticdevs.zodiachoroscopes;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.viewpager.widget.ViewPager;

import android.app.AlarmManager;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.res.Resources;
import android.os.Build;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.util.Log;
import android.util.TypedValue;
import android.view.View;
import android.widget.ImageView;
import com.enigmaticdevs.zodiachoroscopes.Fragments.FragmentDaily;
import com.enigmaticdevs.zodiachoroscopes.Fragments.FragmentMonthly;
import com.enigmaticdevs.zodiachoroscopes.Fragments.FragmentWeekly;
import com.enigmaticdevs.zodiachoroscopes.ItemAdapters.NavItemAdapter;
import com.enigmaticdevs.zodiachoroscopes.Recievers.NotificationReciever;
import com.google.android.gms.ads.AdListener;
import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdView;
import com.google.android.material.tabs.TabLayout;
import com.yarolegovich.slidingrootnav.SlidingRootNavBuilder;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    TabLayout tabLayout;
    List<String> menu;
    List<Integer> icons;
    ImageView theme_toggle;
    SharedPreferences sharedprefrences;
    AdView mAdView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        sharedprefrences = this.getSharedPreferences("com.enigmaticdevs.zodiachoroscopes",MODE_PRIVATE);
        if(sharedprefrences.getString("Theme","light").equals("light"))
        {
            setTheme(R.style.LightTheme);
        }
        else
        {
            setTheme(R.style.DarkTheme);
        }
        setContentView(R.layout.activity_main);
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                getWindow().getDecorView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR);
            }
        mAdView = findViewById(R.id.adView);
        AdRequest adRequest = new AdRequest.Builder().build();
        mAdView.loadAd(adRequest);
        mAdView.setAdListener(new AdListener() {
            @Override
            public void onAdFailedToLoad(int i) {
                super.onAdFailedToLoad(i);
                Log.d("adfailedtoload", "This is why: "+i);
            }

            @Override
            public void onAdLoaded() {
                super.onAdLoaded();
              //  mAdView.setVisibility(View.VISIBLE);
            }
        });
        tabLayout = findViewById(R.id.tab_layout_id);
        ViewPager viewPager = findViewById(R.id.viewpager_id);
        ViewPagerAdapter adapter = new ViewPagerAdapter(getSupportFragmentManager());
        Toolbar toolbar = findViewById(R.id.toolbar);
        theme_toggle = findViewById(R.id.theme_toggle);
        tabLayout.setTabRippleColor(null);
        adapter.AddFragment(new FragmentDaily());
        adapter.AddFragment(new FragmentWeekly());
        adapter.AddFragment(new FragmentMonthly());
        viewPager.setAdapter(adapter);
        tabLayout.setupWithViewPager(viewPager);
        new SlidingRootNavBuilder(this)
                .withMenuLayout(R.layout.menu_left_drawer)
                .withToolbarMenuToggle(toolbar)
                .inject();
        initializeRecycler();
        sharedprefrences = this.getSharedPreferences("com.enigmaticdevs.zodiachoroscopes",MODE_PRIVATE);
        if(sharedprefrences.getString("Theme","light").equals("light"))
            theme_toggle.setBackgroundResource(R.drawable.dark_theme);
        else
            theme_toggle.setBackgroundResource(R.drawable.light_theme);
        theme_toggle.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(sharedprefrences.getString("Theme","light").equals("light"))
                  sharedprefrences.edit().putString("Theme","dark").apply();
                else
                   sharedprefrences.edit().putString("Theme","light").apply();

                Intent intent = new Intent(MainActivity.this,MainActivity.class);
                startActivity(intent);
                finish();
            }
        });
        createAlarmManager();
    }
    protected  void initializeRecycler(){
        addItems();
        RecyclerView recyclerView = findViewById(R.id.list);
        NavItemAdapter itemAdapter = new NavItemAdapter(menu,icons,this);
        GridLayoutManager gridLayoutManager = new GridLayoutManager(this,1);
        recyclerView.setLayoutManager(gridLayoutManager);
        recyclerView.setAdapter(itemAdapter);
    }

    private void addItems() {
        menu = new ArrayList<>();
        icons = new ArrayList<>();
        menu.add("Rate App");
        menu.add("Share");
        menu.add("Checkout My Other Apps");
        icons.add(R.drawable.rate_app);
        icons.add(R.drawable.share);
        icons.add(R.drawable.other_apps);

    }
    public void createAlarmManager() {
        createNotificationChannel();
        Intent intent = new Intent(this, NotificationReciever.class);
        PendingIntent pendingIntent = PendingIntent.getBroadcast(getApplicationContext(), 100, intent, PendingIntent.FLAG_UPDATE_CURRENT);
        AlarmManager alarmManager = (AlarmManager) getSystemService(ALARM_SERVICE);
        Calendar calendar = Calendar.getInstance();
        Log.d("before", String.valueOf(calendar.get(Calendar.DAY_OF_MONTH)));
        int curHr = calendar.get(Calendar.HOUR_OF_DAY);
        if(curHr>6)
            calendar.add(Calendar.DAY_OF_MONTH,1);
        calendar.set(Calendar.HOUR_OF_DAY,6);
        calendar.set(Calendar.MINUTE, 0);
        Log.d("after", String.valueOf(calendar.get(Calendar.DAY_OF_MONTH)));
        assert alarmManager != null;
        alarmManager.cancel(pendingIntent);
        alarmManager.setRepeating(AlarmManager.RTC_WAKEUP,calendar.getTimeInMillis(), AlarmManager.INTERVAL_DAY, pendingIntent);
    }

    private void createNotificationChannel() {
        // Create the NotificationChannel, but only on API 26+ because
        // the NotificationChannel class is new and not in the support library
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            CharSequence name = "Daily Notification";
            String description = "Message";
            int importance = NotificationManager.IMPORTANCE_DEFAULT;
            NotificationChannel channel = new NotificationChannel("channelId", name, importance);
            channel.setDescription(description);
            // Register the channel with the system; you can't change the importance
            // or other notification behaviors after this
            NotificationManager notificationManager = getSystemService(NotificationManager.class);
            notificationManager.createNotificationChannel(channel);
        }
    }
}