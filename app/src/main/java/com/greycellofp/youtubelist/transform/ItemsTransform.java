package com.greycellofp.youtubelist.transform;

import com.greycellofp.youtubelist.models.Video;
import com.greycellofp.youtubelist.network.models.Item;
import com.greycellofp.youtubelist.network.models.Items;

import java.util.ArrayList;
import java.util.List;

public class ItemsTransform {
    public static List<Video> from(Items items) {
        if (items == null || items.items == null) {
            return null;
        }
        ArrayList<Video> videos = new ArrayList<>(items.items.size());
        for (Item item : items.items) {
            Video video = new Video();
            video.videoId = item.id.videoId;
            video.image.url = item.snippet.thumbnails.medium.url;
            video.image.width = item.snippet.thumbnails.medium.width;
            video.image.height = item.snippet.thumbnails.medium.height;
            video.title = item.snippet.title;
            video.description = item.snippet.description;
            videos.add(video);
        }
        return videos;
    }
}
