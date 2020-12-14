package com.code.homenest;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;

import java.util.List;

public class AdminViewEmployees extends AppCompatActivity {

    private RecyclerView mRecyclerView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.admin_view_employees_layout);

        mRecyclerView=(RecyclerView)findViewById(R.id.rvemployee);
        new FirebaseDatabaseHelper2().readEmployee(new FirebaseDatabaseHelper2.DataStatus() {
            @Override
            public void DataIsLoaded(List<Employee> employees, List<String> keys) {
                new RecyclerView_Config2().setConfig(mRecyclerView, AdminViewEmployees.this, employees, keys);
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
