package com.code.homenest;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;

import java.util.List;

public class AdminComplaintsActivity extends AppCompatActivity {

    private RecyclerView mRecyclerView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.admin_complaints_layout);

        mRecyclerView=(RecyclerView)findViewById(R.id.rvcomplaints);
        new FirebaseDatabaseHelper().readComplaints(new FirebaseDatabaseHelper.DataStatus() {
            @Override
            public void DataIsLoaded(List<Complaints> complaints, List<String> keys) {
                new RecyclerView_Config().setConfig(mRecyclerView, AdminComplaintsActivity.this, complaints, keys);
            }

            @Override
            public void DataIsInserted() {

            }

            @Override
            public void DataIsUpdated() {

            }

            @Override
            public void DataIsDeleted() {

            }
        });
    }
}
