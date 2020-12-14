package com.code.homenest;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

import androidx.annotation.NonNull;

public class FirebaseDatabaseHelper {
    private FirebaseDatabase mDatabase;
    private DatabaseReference reff;
    private List<Complaints> complaints=new ArrayList<>();

    public interface DataStatus{
        void DataIsLoaded(List<Complaints> complaints, List<String> keys);
        void DataIsInserted();
        void DataIsUpdated();
        void DataIsDeleted();
    }

    public FirebaseDatabaseHelper() {
        mDatabase=FirebaseDatabase.getInstance();
        reff=mDatabase.getReference("Complaints");
    }

    public void readComplaints(final DataStatus dataStatus){
        reff.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                complaints.clear();
                List<String> keys=new ArrayList<>();
                for (DataSnapshot keyNode : dataSnapshot.getChildren()){
                    keys.add(keyNode.getKey());
                    Complaints complaint=keyNode.getValue(Complaints.class);
                    complaints.add(complaint);
                }
                dataStatus.DataIsLoaded(complaints, keys);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
    }
}

////This Firebase Helper for Admin_Complaints_View