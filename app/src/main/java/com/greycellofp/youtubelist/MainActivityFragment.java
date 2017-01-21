package com.greycellofp.youtubelist;

import android.content.Context;
import android.graphics.Rect;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.support.v4.app.FragmentManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.greycellofp.youtubelist.models.Video;
import com.greycellofp.youtubelist.network.YouTubeSearchService;
import com.greycellofp.youtubelist.network.models.Items;
import com.greycellofp.youtubelist.transform.ItemsTransform;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivityFragment extends Fragment implements IFragmentManager {
    private static final String TAG = MainActivityFragment.class.getSimpleName();

    RecyclerView videos;
    ProgressBar progressBar;

    YouTubeAdapter adapter = new YouTubeAdapter(this);
    float scale;

    public MainActivityFragment() {
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        scale = getResources().getDisplayMetrics().density;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_main, container, false);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        videos = (RecyclerView) view.findViewById(R.id.videos);
        progressBar = (ProgressBar) view.findViewById(R.id.progressBar);
        videos.setLayoutManager(new LinearLayoutManager(getActivity()));
        videos.addItemDecoration(new RecyclerView.ItemDecoration() {
            @Override
            public void getItemOffsets(Rect outRect, View view, RecyclerView parent, RecyclerView.State state) {
                int adapterPosition = parent.getChildAdapterPosition(view);
                if (adapterPosition == 0) {
                    outRect.top = (int) (4 * scale);
                }
                outRect.bottom = (int) (10 * scale);
            }
        });
    }

    public void setSearchString(String searchString) {
        progressBar.setVisibility(View.VISIBLE);
        YouTubeSearchService.search(searchString)
            .enqueue(new Callback<Items>() {
                @Override
                public void onResponse(Call<Items> call, Response<Items> response) {
                    progressBar.setVisibility(View.GONE);
                    List<Video> videos = ItemsTransform.from(response.body());
                    adapter.setVideos(videos);
                    MainActivityFragment.this.videos.setAdapter(adapter);
                }

                @Override
                public void onFailure(Call<Items> call, Throwable throwable) {
                    Log.e(TAG, "", throwable);
                    progressBar.setVisibility(View.GONE);
                    Toast.makeText(getContext(), "failed: " + throwable.getMessage(), Toast.LENGTH_LONG).show();
                }
            });
    }

    @Override
    public FragmentManager getSupportFragmentManager() {
        return getFragmentManager();
    }

    @Override
    public Fragment getSupportFragment() {
        return this;
    }
}
