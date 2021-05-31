package br.com.etec.ddm_a.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

import br.com.etec.ddm_a.R;
import br.com.etec.ddm_a.model.CustomTime;

public class TimeHistoryAdapter extends RecyclerView.Adapter<TimeHistoryAdapter.TimeHistoryViewHolder> {

    ArrayList<CustomTime> itemList;

    public TimeHistoryAdapter(ArrayList<CustomTime> list){
        itemList = list;
    }

    @NonNull
    @Override
    public  TimeHistoryViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.time_history_items, parent, false);
        return new TimeHistoryViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull TimeHistoryViewHolder holder, int position) {
        holder.bind(itemList.get(position), position);
    }

    @Override
    public int getItemCount() {
        return itemList.size();
    }

    public static class TimeHistoryViewHolder extends RecyclerView.ViewHolder{

        public TimeHistoryViewHolder(@NonNull View itemView) {
            super(itemView);
        }

        public void bind(CustomTime time, int position){
            String text = "Dia "+(position+1)+" - "+time.toString()+" Horas de Sono";
            TextView textView = itemView.findViewById(R.id.thi_text);
            textView.setText(text);

            if(time.getHour() < 8){
                textView.setTextColor(ContextCompat.getColor(itemView.getContext(), R.color.red_33));
            }

        }
    }
}
