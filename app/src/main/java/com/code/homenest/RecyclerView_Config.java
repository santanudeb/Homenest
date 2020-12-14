package com.code.homenest;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.List;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

public class RecyclerView_Config {
    private Context mContext;
    private ComplaintsAdapter mComplaintsAdapter;
    public void setConfig(RecyclerView recyclerView, Context context, List<Complaints> complaints, List<String> keys){
        mContext=context;
        mComplaintsAdapter=new ComplaintsAdapter(complaints, keys);
        recyclerView.setLayoutManager(new LinearLayoutManager(context));
        recyclerView.setAdapter(mComplaintsAdapter);
    }

    class ComplaintsView extends RecyclerView.ViewHolder {
        private TextView mPhone;
        private TextView mStatus;
        private TextView mFlatno;
        private TextView mNeeds;

        private String key;

        public ComplaintsView (ViewGroup parent){
            super(LayoutInflater.from(mContext).inflate(R.layout.complaints_item, parent, false));

            mPhone=(TextView)itemView.findViewById(R.id.tv_phone);
            mStatus=(TextView)itemView.findViewById(R.id.tv_status);
            mFlatno=(TextView)itemView.findViewById(R.id.tv_flatno);
            mNeeds=(TextView)itemView.findViewById(R.id.tv_needs);
        }

        public void bind(Complaints complaints, String key){
            mPhone.setText(complaints.getPhone());
            mStatus.setText(complaints.getStatus());
            mFlatno.setText(complaints.getFlatNo());
            mNeeds.setText(complaints.getNeeds());

            this.key=key;
        }
    }

    class ComplaintsAdapter extends RecyclerView.Adapter<ComplaintsView> {
        private List<Complaints> mComplaintsList;
        private List<String> mKeys;

        public ComplaintsAdapter(List<Complaints> mComplaintsList, List<String> mKeys) {
            this.mComplaintsList = mComplaintsList;
            this.mKeys = mKeys;
        }

        @NonNull
        @Override
        public ComplaintsView onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
            return new ComplaintsView(parent);
        }

        @Override
        public void onBindViewHolder(@NonNull ComplaintsView holder, int position) {
            holder.bind(mComplaintsList.get(position), mKeys.get(position));
        }

        @Override
        public int getItemCount() {
            return mComplaintsList.size();
        }
    }
}

//This is for admiv view Complaints