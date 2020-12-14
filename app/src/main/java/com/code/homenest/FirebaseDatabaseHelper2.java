package com.code.homenest;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

import androidx.annotation.NonNull;

public class FirebaseDatabaseHelper2 {
    private FirebaseDatabase mDatabase;
    private DatabaseReference reff;
    private List<Employee> employees=new ArrayList<>();

    public interface DataStatus{
        void DataIsLoaded(List<Employee> employees, List<String> keys);
        void DataIsInserted();
        void DataIsUpdated();
        void DataIsDeleted();
    }

    public FirebaseDatabaseHelper2() {
        mDatabase=FirebaseDatabase.getInstance();
        reff=mDatabase.getReference("Employee");
    }

    public void readEmployee(final FirebaseDatabaseHelper2.DataStatus dataStatus){
        reff.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                employees.clear();
                List<String> keys=new ArrayList<>();
                for (DataSnapshot keyNode : dataSnapshot.getChildren()){
                    keys.add(keyNode.getKey());
                    Employee employee=keyNode.getValue(Employee.class);
                    employees.add(employee);
                }
                dataStatus.DataIsLoaded(employees, keys);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
    }
}


//This Firebase Helper for Admin_View_Employee