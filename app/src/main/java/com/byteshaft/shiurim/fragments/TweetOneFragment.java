package com.byteshaft.shiurim.fragments;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import twitter4j.Paging;
import twitter4j.Status;
import twitter4j.Twitter;
import twitter4j.TwitterException;
import twitter4j.TwitterFactory;
import twitter4j.User;
import twitter4j.conf.ConfigurationBuilder;

import com.byteshaft.shiurim.Helpers;
import com.byteshaft.shiurim.R;

import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.List;

public class TweetOneFragment extends Fragment {
    private View mBaseView;
    ImageView imageViewTweets;
    TextView userTextView;
    Bitmap twitterProfileIcon;
    List<Status> statuses;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        mBaseView = inflater.inflate(R.layout.twitter_one_fragment, container, false);
        imageViewTweets = (ImageView) mBaseView.findViewById(R.id.iv_tweets_icon);
        userTextView = (TextView) mBaseView.findViewById(R.id.tv_twitter_account_name);
        return mBaseView;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        Helpers.showProgressDialog(getActivity(), "Loading twitter feeds");

        new Thread(new Runnable() {
            @Override
            public void run() {
                ConfigurationBuilder cb = new ConfigurationBuilder();
                cb.setDebugEnabled(true)
                        .setOAuthConsumerKey("16eabEYM1yyerYbmqnm9OSbJK")
                        .setOAuthConsumerSecret("8oCNlQD221N9gPUGFpADF4WUD6eUBqSNegxMzOKOblG0kDjghn")
                        .setOAuthAccessToken("3184467546-WY7Z0yUUc2RbCkKS6QWAqamVtlIOr1iV8bW6lcS")
                        .setOAuthAccessTokenSecret("tuz8b3Tt2dlYe2eK7pSzBumD6FklaynchGwEmQIJhRbE6");
                TwitterFactory tf = new TwitterFactory(cb.build());
                Twitter twitter = tf.getInstance();
                Paging paging = new Paging(1, 10);

                try {
                    final User user = twitter.showUser("VCKMishkanTorah");
                    URL url = new URL(user.getBiggerProfileImageURL());
                    twitterProfileIcon = BitmapFactory.decodeStream(url.openConnection().getInputStream());
                    statuses = twitter.getUserTimeline("VCKMishkanTorah", paging);
                    final TweetsOneArrayAdapter tweetsAdapter = new TweetsOneArrayAdapter(
                            getActivity().getApplicationContext(), R.layout.tweets_row, statuses);

                    final ListView listview = (ListView) mBaseView.findViewById(R.id.lv_tweets);
                    getActivity().runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            listview.setAdapter(tweetsAdapter);
                            imageViewTweets.setImageBitmap(twitterProfileIcon);
                            userTextView.setText("@" + user.getScreenName());
                        }
                    });
                } catch (TwitterException e) {
                    e.printStackTrace();
                } catch (MalformedURLException e) {
                    e.printStackTrace();
                } catch (IOException e) {
                    e.printStackTrace();
                }
                Helpers.dismissProgressDialog();
            }
        }).start();
    }

    static class ViewHolder {
        public TextView title;
    }

    private class TweetsOneArrayAdapter extends ArrayAdapter<List> {

        public TweetsOneArrayAdapter(Context context, int resource, List objects) {
            super(context, resource, objects);

        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            ViewHolder holder;
            if (convertView == null) {
                Context context = getActivity().getApplicationContext();
                LayoutInflater inflater = (LayoutInflater) context.getSystemService(
                        Context.LAYOUT_INFLATER_SERVICE);
                convertView = inflater.inflate(R.layout.tweets_row, parent, false);
                holder = new ViewHolder();
                holder.title = (TextView) convertView.findViewById(R.id.tv_tweet);
                convertView.setTag(holder);
            } else {
                holder = (ViewHolder) convertView.getTag();
            }
            Status status = (Status) getItem(position);
            holder.title.setText(status.getText());
            return convertView;
        }
    }
}