package com.greycellofp.youtubelist.network;

import com.greycellofp.youtubelist.Constants;
import com.greycellofp.youtubelist.network.models.Items;

import retrofit2.Call;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.http.GET;
import retrofit2.http.Query;

public class YouTubeSearchService {

    public static Call<Items> search (String query) {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("https://www.googleapis.com/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        IYouTubeSearchService service = retrofit.create(IYouTubeSearchService.class);
        return service.search(query);
    }

    interface IYouTubeSearchService {
        @GET("youtube/v3/search?part=snippet&type=video&key=" + Constants.KEY)
        Call<Items> search(@Query("q") String query);
    }
}
