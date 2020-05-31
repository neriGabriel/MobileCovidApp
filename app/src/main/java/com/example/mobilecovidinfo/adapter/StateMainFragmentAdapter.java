package com.example.mobilecovidinfo.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.mobilecovidinfo.R;
import com.example.mobilecovidinfo.model.State;

import java.util.ArrayList;
import java.util.List;

public class StateMainFragmentAdapter extends RecyclerView.Adapter<StateMainFragmentAdapter.ViewHolder> {

    private List<State> stateList = new ArrayList<>();

    public StateMainFragmentAdapter(List<State> stateList) {
        this.stateList = stateList;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                                      .inflate(R.layout.state_info, parent, false);
        return new ViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        holder.txtState.setText(this.stateList.get(position).getState());
        holder.txtTotalCases.setText(String.valueOf(this.stateList.get(position).getCases()));
        holder.txtTotalSuspects.setText(String.valueOf(this.stateList.get(position).getSuspects()));
        holder.txtDataAtualizacao.setText("Ultima atualização 31/05/2020 17:34:00");
    }

    @Override
    public int getItemCount() {
        return this.stateList.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {

        TextView txtState;
        TextView txtTotalCases;
        TextView txtTotalSuspects;
        TextView txtDataAtualizacao;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            this.txtState = itemView.findViewById(R.id.txtState);
            this.txtTotalCases = itemView.findViewById(R.id.txtTotalCases);
            this.txtTotalSuspects = itemView.findViewById(R.id.txtTotalSuspects);
            this.txtDataAtualizacao = itemView.findViewById(R.id.txtDtAtutalizacao);
        }
    }
}
