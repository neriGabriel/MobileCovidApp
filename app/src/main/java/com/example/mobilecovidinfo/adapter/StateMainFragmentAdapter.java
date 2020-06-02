package com.example.mobilecovidinfo.adapter;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.navigation.NavDirections;
import androidx.navigation.Navigation;
import androidx.recyclerview.widget.RecyclerView;

import com.example.mobilecovidinfo.R;
import com.example.mobilecovidinfo.model.State;
import com.example.mobilecovidinfo.util.DateUtil;
import com.example.mobilecovidinfo.view.MainFragment;
import com.example.mobilecovidinfo.view.MainFragmentDirections;

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
        holder.txtDataAtualizacao.setText("Ultima atualização " + this.stateList.get(position).getDatetime());
        holder.cardItem.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                NavDirections action = MainFragmentDirections.actionMainFragmentToSettingsFragment();
                Navigation.findNavController(view).navigate(action);
            }
        });
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
        CardView cardItem;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            this.txtState = itemView.findViewById(R.id.txtState);
            this.txtTotalCases = itemView.findViewById(R.id.txtTotalCases);
            this.txtTotalSuspects = itemView.findViewById(R.id.txtTotalSuspects);
            this.txtDataAtualizacao = itemView.findViewById(R.id.txtDtAtutalizacao);
            this.cardItem = itemView.findViewById(R.id.cardItem);
        }
    }
}
