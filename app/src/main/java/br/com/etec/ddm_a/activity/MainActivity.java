
package br.com.etec.ddm_a.activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatButton;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import android.content.Intent;
import android.os.Bundle;
import android.os.Parcelable;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import br.com.etec.ddm_a.R;
import br.com.etec.ddm_a.fragment.SleepHourFragment;
import br.com.etec.ddm_a.model.CustomTime;
import br.com.etec.ddm_a.view.TimeTextView;

public class MainActivity extends AppCompatActivity implements SleepHourFragment.TimeListener {

    CustomTime initTime = null;
    CustomTime finalTime = null;

    ArrayList<CustomTime> savedHistory = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main_activity);
        setupListeners();
        Fragment sleepHour = SleepHourFragment.newInstance(true);
        Fragment awakeHour = SleepHourFragment.newInstance(false);
        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        transaction.add(R.id.ma_sleep_frame, sleepHour);
        transaction.add(R.id.ma_awake_frame, awakeHour);
        transaction.commit();
    }

    private void setupListeners(){
        AppCompatButton save = findViewById(R.id.ma_save_btn);
        AppCompatButton history = findViewById(R.id.ma_history_btn);

        save.setOnClickListener( view -> {
            if(initTime != null && finalTime != null){
                CustomTime saveTime = finalTime.setFromDiff(initTime);
                savedHistory.add(saveTime);
            } else{
                Toast.makeText(this, "Selecione a Hora Final e Inicial Primeiro", Toast.LENGTH_LONG).show();
            }
        });

        history.setOnClickListener( view -> {
            Intent intent = new Intent(this, TimeHistoryActivity.class);
            intent.putParcelableArrayListExtra("LIST", savedHistory);
            startActivity(intent);
        });
    }

    @Override
    public void onTimeResult(CustomTime time, boolean isFirst) {
        if(isFirst){
            initTime = time;
        } else {
            finalTime = time;
        }
        checkTotalResult();
    }

    private void checkTotalResult(){
        if(initTime != null && finalTime != null){
            TimeTextView timeText = findViewById(R.id.ma_sleep_totaltext);
            CustomTime totalTime = finalTime.setFromDiff(initTime);
            timeText.setTime(totalTime.toString());
        }
    }

}