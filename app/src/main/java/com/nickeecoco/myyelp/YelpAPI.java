package com.nickeecoco.myyelp;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface YelpAPI {
    @GET("businesses/search")
    Call<YelpResponse> getSearchResults(@Query("location") String location,
                                        @Query("term") String query);
}
