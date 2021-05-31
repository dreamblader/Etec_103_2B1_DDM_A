
package br.com.etec.ddm_a.activity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatButton;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import java.io.File;
import java.io.FileOutputStream;
import java.io.ObjectOutputStream;

import br.com.etec.ddm_a.R;
import br.com.etec.ddm_a.fragment.SleepHourFragment;
import br.com.etec.ddm_a.model.CustomTime;
import br.com.etec.ddm_a.view.TimeTextView;

public class MainActivity extends AppCompatActivity implements SleepHourFragment.TimeListener {

    public static final String FILE_NAME = "TimeHistory";

    CustomTime initTime = null;
    CustomTime finalTime = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main_activity);
        setupListeners();
        setupFragments();
    }

    private void setupFragments(){
        if(getSupportFragmentManager().getFragments().size() == 0){
            FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
            Fragment sleepHour = SleepHourFragment.newInstance(true);
            Fragment awakeHour = SleepHourFragment.newInstance(false);
            transaction.add(R.id.ma_sleep_frame, sleepHour);
            transaction.add(R.id.ma_awake_frame, awakeHour);
            transaction.commit();
        }
    }

    private void setupListeners(){
        AppCompatButton save = findViewById(R.id.ma_save_btn);
        AppCompatButton history = findViewById(R.id.ma_history_btn);

        save.setOnClickListener( view -> {
            if(initTime != null && finalTime != null){
                CustomTime saveTime = finalTime.setFromDiff(initTime);
                saveFile(saveTime);
            } else{
                Toast.makeText(this, "Selecione a Hora Final e Inicial Primeiro", Toast.LENGTH_LONG).show();
            }
        });

        history.setOnClickListener( view -> {
            Intent intent = new Intent(this, TimeHistoryActivity.class);
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

    private void saveFile(CustomTime saveTime){
        File file = new File(this.getFilesDir(),FILE_NAME);

        try{
            boolean exist = !file.createNewFile();
            try(FileOutputStream fos = new FileOutputStream(file, true)) {
                try (ObjectOutputStream oos = new ObjectOutputStream(fos)) {
                    if(exist && file.length() > 0){
                        long pos = fos.getChannel().position()-4;
                        fos.getChannel().truncate(pos);
                    }
                    oos.writeObject(saveTime);
                }
            }
            Toast.makeText(this, "Arquivo Salvo com Sucesso",Toast.LENGTH_LONG).show();
        }catch(Exception error){
            error.printStackTrace();
            Toast.makeText(this, "Arquivo Cancelado\nCausa: "+error.getMessage(),Toast.LENGTH_LONG).show();
        }
    }

}

