package com.greycellofp.youtubelist.network.models;

import com.google.gson.annotations.SerializedName;

/**
 * Created by a Jedi Master.
 */

public class Thumbnails {
    @SerializedName("default")
    public Image regular;
    public Image medium;
    public Image high;
}
