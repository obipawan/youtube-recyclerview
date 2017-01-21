package com.greycellofp.youtubelist;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;

public interface IFragmentManager {
    FragmentManager getSupportFragmentManager();
    Fragment getSupportFragment();
}
