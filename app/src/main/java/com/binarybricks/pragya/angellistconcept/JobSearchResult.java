package com.binarybricks.pragya.angellistconcept;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import com.binarybricks.pragya.angellistconcept.adapters.JobSearchAdapter;
import com.binarybricks.pragya.angellistconcept.datamodel.JobsDataModel;
import com.binarybricks.pragya.angellistconcept.network.RetroClient;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class JobSearchResult extends AppCompatActivity {

    @BindView(R.id.toolbar)
    Toolbar toolbar;

    @BindView(R.id.lvJobSearchResult)
    ListView lvJobSearchResult;

    private ProgressDialog progress;
    private List<JobsDataModel> jobsSearchList;
    private JobSearchAdapter jobSearchAdapter;

    private String JOB_ID = "job_id";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_job_search_result);

        ButterKnife.bind(this);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        getSupportActionBar().setTitle(R.string.search_result_activity_title);

        progress = ProgressDialog.show(JobSearchResult.this, "",getString(R.string.progrees_finding_job),true);

        String title = getIntent().getStringExtra("job_title");
        String location = getIntent().getStringExtra("job_location");

        jobsSearchList = new ArrayList<>();
        jobSearchAdapter = new JobSearchAdapter(JobSearchResult.this, jobsSearchList);
        lvJobSearchResult.setAdapter(jobSearchAdapter);

        RetroClient retroClient = new RetroClient();
        retroClient.searchJobs(title, location, new Callback<List<JobsDataModel>>() {
            @Override
            public void onResponse(Call<List<JobsDataModel>> call, Response<List<JobsDataModel>> response) {
                progress.dismiss();
                if (response.isSuccessful()){
                    jobsSearchList = response.body();
                    jobSearchAdapter.setJobSearchList(jobsSearchList);
                    jobSearchAdapter.notifyDataSetChanged();
                }
            }

            @Override
            public void onFailure(Call<List<JobsDataModel>> call, Throwable t) {
                progress.dismiss();
                Snackbar.make(lvJobSearchResult, t.getMessage(), Snackbar.LENGTH_SHORT).show();
            }
        });

        lvJobSearchResult.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent intent  = new Intent(JobSearchResult.this, JobDetail.class);
                intent.putExtra(JOB_ID,jobsSearchList.get(position).getId());
                startActivity(intent);
            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater menuInflater = getMenuInflater();
        menuInflater.inflate(R.menu.job_filter_menu, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == R.id.filter){
            Intent filterIntent = new Intent(JobSearchResult.this, FilterActivity.class);
            startActivity(filterIntent);
        }
        return super.onOptionsItemSelected(item);
    }
}
