package com.byteshaft.shiurim;

import android.support.annotation.IdRes;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Toast;

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
                    Toast.makeText(getApplicationContext(), "cal", Toast.LENGTH_SHORT).show();
                    // The tab with id R.id.tab_calender was reselected,
                    // change your content accordingly.
                } else if (tabId == R.id.tab_document) {
                    Toast.makeText(getApplicationContext(), "doc one", Toast.LENGTH_SHORT).show();
                } else if (tabId == R.id.tab_tweets_one) {
                    Toast.makeText(getApplicationContext(), "tweet one", Toast.LENGTH_SHORT).show();

                } else if (tabId == R.id.tab_document_two) {
                    Toast.makeText(getApplicationContext(), "doc two", Toast.LENGTH_SHORT).show();
                } else if (tabId == R.id.tab_tweets_two) {
                    Toast.makeText(getApplicationContext(), "tweet two", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }
}
