
package br.com.etec.ddm_a.activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import android.os.Bundle;

import br.com.etec.ddm_a.R;
import br.com.etec.ddm_a.fragment.SleepHourFragment;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main_activity);
        Fragment sleepHour = SleepHourFragment.newInstance(true);
        Fragment awakeHour = SleepHourFragment.newInstance(false);
        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        transaction.add(R.id.ma_sleep_frame, sleepHour);
        transaction.add(R.id.ma_awake_frame, awakeHour);
        transaction.commit();
    }



}