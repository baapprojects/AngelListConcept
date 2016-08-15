package com.binarybricks.pragya.angellistconcept;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.EditText;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class JobSearchActivity extends AppCompatActivity {

    @BindView(R.id.toolbar)
    Toolbar toolbar;

    @BindView(R.id.etSearchJobTitle)
    EditText etSearchJobTitle;

    @BindView(R.id.etSetLocation)
    EditText etSetLocation;

    private String JOB_TITLE = "job_title";
    private String JOB_LOCATION = "job_location";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_job_search);
        ButterKnife.bind(this);
        setSupportActionBar(toolbar);

        getSupportActionBar().setTitle("Angellist");

    }

    @OnClick(R.id.btnSearch)
    public void onSearch(View view){
        Intent intent  = new Intent(JobSearchActivity.this, JobSearchResult.class);
        intent.putExtra(JOB_TITLE,etSearchJobTitle.getText().toString());
        intent.putExtra(JOB_LOCATION,etSetLocation.getText().toString());
        startActivity(intent);
    }
}
