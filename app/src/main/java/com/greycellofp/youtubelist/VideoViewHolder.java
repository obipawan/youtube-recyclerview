package com.greycellofp.youtubelist;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.TextView;

import com.facebook.drawee.view.SimpleDraweeView;

public class VideoViewHolder extends RecyclerView.ViewHolder {
    public SimpleDraweeView image;
    public TextView title;
    public TextView description;
    public FrameLayout videoContainer;

    public VideoViewHolder(View itemView) {
        super(itemView);
        image = (SimpleDraweeView) itemView.findViewById(R.id.image);
        title = (TextView) itemView.findViewById(R.id.title);
        description = (TextView) itemView.findViewById(R.id.description);
        videoContainer = (FrameLayout) itemView.findViewById(R.id.video_container);
    }
}
