package com.byteshaft.shiurim;

import android.support.annotation.IdRes;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.byteshaft.shiurim.fragments.CalendarFragment;
import com.byteshaft.shiurim.fragments.DocumentOneFragment;
import com.byteshaft.shiurim.fragments.DocumentTwoFragment;
import com.byteshaft.shiurim.fragments.TweetOneFragment;
import com.byteshaft.shiurim.fragments.TweetTwoFragment;
import com.roughike.bottombar.BottomBar;
import com.roughike.bottombar.OnTabSelectListener;

public class MainActivity extends AppCompatActivity {

    private BottomBar mBottomBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mBottomBar = (BottomBar) findViewById(R.id.bottomBar);
        mBottomBar.setOnTabSelectListener(new OnTabSelectListener() {
            @Override
            public void onTabSelected(@IdRes int tabId) {

                if (tabId == R.id.tab_calender) {
                    loadFragment(new CalendarFragment());

                } else if (tabId == R.id.tab_document) {
                    loadFragment(new DocumentOneFragment());

                } else if (tabId == R.id.tab_tweets_one) {
                    loadFragment(new TweetOneFragment());

                } else if (tabId == R.id.tab_document_two) {
                    loadFragment(new DocumentTwoFragment());

                } else if (tabId == R.id.tab_tweets_two) {
                    loadFragment(new TweetTwoFragment());
                }
            }
        });
    }

    public void loadFragment(Fragment fragment) {
        FragmentTransaction tx = getSupportFragmentManager().beginTransaction();
        tx.replace(R.id.container, fragment);
        tx.commit();
    }
}
