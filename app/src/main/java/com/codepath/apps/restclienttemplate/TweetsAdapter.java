package com.codepath.apps.restclienttemplate;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.codepath.apps.restclienttemplate.models.Tweet;

import java.util.List;

public class TweetsAdapter extends RecyclerView.Adapter<TweetsAdapter.ViewHolder> {

    Context context;
    List<Tweet> tweets;

    // pass context and list of tweets
    public TweetsAdapter(Context context, List<Tweet> tweets) {
        this.context = context;
        this.tweets = tweets;
    }

    // for each row, inflate layout for tweet
    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_tweet, parent, false);
        return new ViewHolder(view);
    }

    // bind values based on position of elements
    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        // get data at position
        Tweet tweet = tweets.get(position);

        // bind tweet with viewholder
        holder.bind(tweet);

    }

    @Override
    public int getItemCount() {
        return tweets.size();
    }

    // define viewholder
    public class ViewHolder extends RecyclerView.ViewHolder {

        ImageView ivProfileImage;
        TextView tvBody;
        TextView tvScreenName;
        TextView tvTimestamp;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            this.ivProfileImage = itemView.findViewById(R.id.ivProfileImage);
            this.tvBody = itemView.findViewById(R.id.tvBody);
            this.tvScreenName = itemView.findViewById(R.id.tvScreenName);
            this.tvTimestamp = itemView.findViewById(R.id.tvTimestamp);
        }

        public void bind(Tweet tweet) {
            tvBody.setText(tweet.body);
            tvScreenName.setText(tweet.user.screenName);
            tvTimestamp.setText(TimeFormatter.getTimeDifference(tweet.createdAt));
            Glide.with(context).load(tweet.user.publicImageUrl).into(ivProfileImage);
        }
    }
}
