package com.binarybricks.pragya.angellistconcept.adapters;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.binarybricks.pragya.angellistconcept.R;
import com.binarybricks.pragya.angellistconcept.datamodel.JobsDataModel;
import com.squareup.picasso.Picasso;

import java.util.List;

/**
 * Created by PRAGYA on 8/14/2016.
 */
public class JobSearchAdapter extends BaseAdapter {

    private List<JobsDataModel> jobSearchList;
    private Context context;

    public JobSearchAdapter(Context context, List<JobsDataModel> jobSearchList) {
        this.jobSearchList = jobSearchList;
        this.context = context;
    }

    @Override
    public int getCount() {
        return jobSearchList.size();
    }

    @Override
    public Object getItem(int position) {
        return jobSearchList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    // our ViewHolder.
    // caches our TextView
    static class ViewHolderItem {
        // Lookup view for data population
        TextView tvJobTitle;
        TextView tvCompanyName;
        TextView tvJobSalary;
        TextView tvJobEquity;
        TextView tvNoOfEmployees;
        TextView tvJobLocation;
        ImageView ivCompanyLogo;
    }

    @Override
    public View getView(int position, View view, ViewGroup parent) {
        ViewHolderItem viewHolder;

        // Check if an existing view is being reused, otherwise inflate the view
        if (view == null) {
            // inflate the layout
            LayoutInflater inflater = ((Activity) context).getLayoutInflater();
            view = inflater.inflate(R.layout.job_search_item, parent, false);

            // well set up the ViewHolder
            viewHolder = new ViewHolderItem();
            viewHolder.tvJobTitle = (TextView) view.findViewById(R.id.tvJobTitle);
            viewHolder.tvCompanyName = (TextView) view.findViewById(R.id.tvCompanyName);
            viewHolder.tvJobLocation = (TextView) view.findViewById(R.id.tvJobLocation);
            viewHolder.ivCompanyLogo = (ImageView) view.findViewById(R.id.ivCompanyLogo);
            viewHolder.tvJobSalary = (TextView) view.findViewById(R.id.tvJobSalary);
            viewHolder.tvJobEquity = (TextView) view.findViewById(R.id.tvJobEquity);
            viewHolder.tvNoOfEmployees = (TextView) view.findViewById(R.id.tvTeam);

            // store the holder with the view.
            view.setTag(viewHolder);
        } else {
            viewHolder = (ViewHolderItem) view.getTag();
        }

        JobsDataModel jobsDataModel = jobSearchList.get(position);

        viewHolder.tvJobTitle.setText(String.format(context.getString(R.string.position),jobsDataModel.getJob(),jobsDataModel.getJobtype()));
        viewHolder.tvCompanyName.setText(jobsDataModel.getCompany());
        viewHolder.tvJobLocation.setText(jobsDataModel.getJoblocation());
        viewHolder.tvJobSalary.setText(jobsDataModel.getJobsalary());
        viewHolder.tvJobEquity.setText(jobsDataModel.getJobequity());
        viewHolder.tvNoOfEmployees.setText(jobsDataModel.getNoofemployee());
        Picasso.with(context).load(jobsDataModel.getLogo()).into(viewHolder.ivCompanyLogo);
        return view;
    }

  public void setJobSearchList(List<JobsDataModel> jobSearchList){
      this.jobSearchList = jobSearchList;
  }
}
