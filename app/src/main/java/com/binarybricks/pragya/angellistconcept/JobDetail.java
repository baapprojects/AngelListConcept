package com.binarybricks.pragya.angellistconcept;

import android.app.Dialog;
import android.app.ProgressDialog;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.binarybricks.pragya.angellistconcept.datamodel.JobDetailsDataModel;
import com.binarybricks.pragya.angellistconcept.network.RetroClient;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class JobDetail extends AppCompatActivity {

    @BindView(R.id.toolbar)
    Toolbar toolbar;
    @BindView(R.id.ivCompanyLogo)
    ImageView ivCompanyLogo;
    @BindView(R.id.tvCompanyName)
    TextView tvCompanyName;
    @BindView(R.id.tvTagLine)
    TextView tvTagLine;
    @BindView(R.id.tvJobLocation1)
    TextView tvJobLocation1;
    @BindView(R.id.tvNoOfEmployees)
    TextView tvNoOfEmployees;
    @BindView(R.id.tvPeopleApplied1)
    TextView tvPeopleApplied1;
    @BindView(R.id.tvJobTitle)
    TextView tvJobTitle;
    @BindView(R.id.tvJobSalary)
    TextView tvJobSalary;
    @BindView(R.id.tvJobEquity)
    TextView tvJobEquity;
    @BindView(R.id.tvJobType)
    TextView tvJobType;
    @BindView(R.id.tvJobLocation)
    TextView tvJobLocation;
    @BindView(R.id.tvPeopleApplied)
    TextView tvPeopleApplied;
    @BindView(R.id.tvProduct)
    TextView tvProduct;
    @BindView(R.id.tvTech)
    TextView tvTech;

    private Dialog myDialog;
//    @BindView(R.id.etEmail) EditText etEmail;
//    @BindView(R.id.etPhone) EditText etPhone;

    private List<JobDetailsDataModel> jobDetailList;
    private ProgressDialog progress;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_job_detail);

        ButterKnife.bind(this);
        String jobId = getIntent().getStringExtra("job_id");

        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        getSupportActionBar().setTitle("");

        progress = ProgressDialog.show(JobDetail.this, "", getString(R.string.progress_getting_job_detail), true);

        jobDetailList = new ArrayList<>();

        RetroClient retroClient = new RetroClient();
        retroClient.getJobDetail(jobId, new Callback<List<JobDetailsDataModel>>() {
            @Override
            public void onResponse(Call<List<JobDetailsDataModel>> call, Response<List<JobDetailsDataModel>> response) {
                progress.dismiss();
                if (response.isSuccessful()) {
                    jobDetailList = response.body();
                    Picasso.with(JobDetail.this).load(jobDetailList.get(0).getLogo()).into(ivCompanyLogo);
                    tvCompanyName.setText(jobDetailList.get(0).getCompany());
                    tvTagLine.setText(jobDetailList.get(0).getTagline());
                    tvJobLocation1.setText(jobDetailList.get(0).getJoblocation());
                    tvNoOfEmployees.setText(jobDetailList.get(0).getNoofemployee());
                    tvPeopleApplied1.setText(jobDetailList.get(0).getPeopleapplied());
                    tvJobTitle.setText(jobDetailList.get(0).getJob());
                    tvJobSalary.setText(jobDetailList.get(0).getJobsalary());
                    tvJobEquity.setText(jobDetailList.get(0).getJobequity());
                    tvJobType.setText(jobDetailList.get(0).getJobtype());
                    tvJobLocation.setText(jobDetailList.get(0).getJoblocation().trim());
                    tvPeopleApplied.setText(jobDetailList.get(0).getPeopleapplied());
                    tvProduct.setText(jobDetailList.get(0).getProduct().trim());
                    tvTech.setText(jobDetailList.get(0).getTech().trim());
                }
            }

            @Override
            public void onFailure(Call<List<JobDetailsDataModel>> call, Throwable t) {
                progress.dismiss();
            }
        });
    }

    @OnClick(R.id.btnApply)
    public void onApply(View v){
        myDialog = new Dialog(this);
        myDialog.setContentView(R.layout.contact_information_popup);
        Button submit = (Button) myDialog.findViewById(R.id.btnSubmit);
        EditText etEmail = (EditText) myDialog.findViewById(R.id.etEmail);
        EditText etPhone = (EditText) myDialog.findViewById(R.id.etPhone);

        myDialog.show();

        submit.setOnClickListener(new View.OnClickListener()
        {

            @Override
            public void onClick(View v)
            {
                new AlertDialog.Builder(JobDetail.this).setMessage(getString(R.string.submit_thanku_msg)).show();
                myDialog.dismiss();
            }
        });


    }

}
