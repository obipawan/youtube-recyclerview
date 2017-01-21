package com.greycellofp.youtubelist;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.greycellofp.youtubelist.models.Video;

import java.util.ArrayList;
import java.util.List;

class YouTubeAdapter extends RecyclerView.Adapter<VideoViewHolder> {
    private List<Video> videos;
    private IFragmentManager fragmentManager;

    YouTubeAdapter(IFragmentManager fragmentManager) {
        this.fragmentManager = fragmentManager;
    }

    void setVideos(List<Video> videos) {
        if (this.videos == null) {
            this.videos = new ArrayList<>(videos.size());
            this.videos.addAll(videos);
            return;
        }
        this.videos.clear();
        this.videos.addAll(videos);
    }

    @Override
    public VideoViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View view = inflater.inflate(R.layout.video_item, parent, false);
        return new VideoViewHolder(view);
    }

    @Override
    public void onBindViewHolder(VideoViewHolder holder, int position) {
    }

    @Override
    public void onViewAttachedToWindow(VideoViewHolder holder) {
        super.onViewAttachedToWindow(holder);
        int position = holder.getAdapterPosition();
        Video video = videos.get(position);
        video.binder.prepare();
        video.binder.bind(holder, fragmentManager);
    }

    @Override
    public void onViewDetachedFromWindow(VideoViewHolder holder) {
        super.onViewDetachedFromWindow(holder);
        int position = holder.getAdapterPosition();
        Video video = videos.get(position);
        video.binder.unBind(holder, fragmentManager);
    }

    @Override
    public int getItemCount() {
        return this.videos.size();
    }
}
