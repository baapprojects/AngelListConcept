package com.binarybricks.pragya.angellistconcept.network;

import com.binarybricks.pragya.angellistconcept.datamodel.JobDetailsDataModel;
import com.binarybricks.pragya.angellistconcept.datamodel.JobsDataModel;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.http.GET;
import retrofit2.http.Query;

/**
 * Created by PRAGYA on 8/14/2016.
 */
public class RetroClient {

    // Trailing slash is needed
    public static final String BASE_URL = "https://apps.dexterapps.in/";
    private Retrofit retrofit;

    public Retrofit getRetrofit() {
        if (retrofit == null) {
            retrofit = new Retrofit.Builder()
                    .baseUrl(BASE_URL)
                    .addConverterFactory(GsonConverterFactory.create())
                    .build();
        }
        return retrofit;
    }

    public void searchJobs(String jobTitle, String jobLocation, Callback<List<JobsDataModel>> callback) {
        AngelListInterface angelListInterface = getRetrofit().create(AngelListInterface.class);
        Call<List<JobsDataModel>> call = angelListInterface.searchJob(jobTitle, jobLocation);
        call.enqueue(callback);
    }

    public void getJobDetail(String jobId, Callback<List<JobDetailsDataModel>> callback) {
        AngelListInterface angelListInterface = getRetrofit().create(AngelListInterface.class);
        Call<List<JobDetailsDataModel>> call = angelListInterface.getJobDetail(jobId);
        call.enqueue(callback);
    }

    interface AngelListInterface {
        // Request method and URL specified in the annotation
        // Callback for the parsed response is the last parameter

        @GET("angellist/angellist.php")
        Call<List<JobsDataModel>> searchJob(@Query("jobtitle") String jobTitle, @Query("joblocation") String jobLocation);

        @GET("angellist/angellist.php")
        Call<List<JobDetailsDataModel>> getJobDetail(@Query("id") String jobId);
    }
}
