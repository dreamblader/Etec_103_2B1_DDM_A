package br.com.etec.ddm_a.activity;

import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

import br.com.etec.ddm_a.R;
import br.com.etec.ddm_a.adapter.TimeHistoryAdapter;
import br.com.etec.ddm_a.model.CustomTime;

public class TimeHistoryActivity extends AppCompatActivity {

    ArrayList<CustomTime> savedHistory = new ArrayList<>();
    TimeHistoryAdapter adapter;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        savedHistory = this.getIntent().getParcelableArrayListExtra("LIST");
        setContentView(R.layout.time_history_activity);

        adapter =  new TimeHistoryAdapter(savedHistory);
        RecyclerView historyView = findViewById(R.id.tha_list);
        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        historyView.setLayoutManager(layoutManager);
        historyView.setAdapter(adapter);
    }
}
