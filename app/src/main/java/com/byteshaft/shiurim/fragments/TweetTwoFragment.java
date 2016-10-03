package com.byteshaft.shiurim.fragments;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.byteshaft.shiurim.R;

public class TweetTwoFragment extends Fragment{
    private View mBaseView;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        mBaseView = inflater.inflate(R.layout.twitter_two_fragment, container, false);
        return mBaseView;
    }
}
