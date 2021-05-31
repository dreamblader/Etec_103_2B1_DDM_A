package br.com.etec.ddm_a.activity;

import android.os.Bundle;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.io.EOFException;
import java.io.File;
import java.io.FileInputStream;
import java.io.ObjectInputStream;
import java.util.ArrayList;

import br.com.etec.ddm_a.R;
import br.com.etec.ddm_a.adapter.TimeHistoryAdapter;
import br.com.etec.ddm_a.model.CustomTime;

import static br.com.etec.ddm_a.activity.MainActivity.FILE_NAME;

public class TimeHistoryActivity extends AppCompatActivity {

    ArrayList<CustomTime> savedHistory = new ArrayList<>();
    TimeHistoryAdapter adapter;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        savedHistory = loadFile();
        setContentView(R.layout.time_history_activity);

        adapter =  new TimeHistoryAdapter(savedHistory);
        RecyclerView historyView = findViewById(R.id.tha_list);
        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        historyView.setLayoutManager(layoutManager);
        historyView.setAdapter(adapter);
    }

    private ArrayList<CustomTime> loadFile(){

        File file = new File(this.getFilesDir(),FILE_NAME);
        ArrayList<CustomTime> result = new ArrayList<>();
        CustomTime temp;

        try(FileInputStream fis = new FileInputStream(file)) {
            try (ObjectInputStream ois = new ObjectInputStream(fis)) {
                while((temp = (CustomTime) ois.readObject()) != null){
                    result.add(temp);
                }
            }
        } catch(Exception error){
            if(!(error instanceof EOFException)){
                error.printStackTrace();
                Toast.makeText(this, "Arquivo Cancelado\nCausa: "+error.getMessage(),Toast.LENGTH_LONG).show();
            }
        }

        return result;
    }
}
