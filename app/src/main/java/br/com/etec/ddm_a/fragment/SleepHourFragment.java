package br.com.etec.ddm_a.fragment;

import android.app.TimePickerDialog;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.widget.AppCompatButton;
import androidx.fragment.app.Fragment;

import br.com.etec.ddm_a.R;
import br.com.etec.ddm_a.model.CustomTime;

public class SleepHourFragment extends Fragment {

    static final String IS_DEFAULT = "isDefault";

    CustomTime displayTime = new CustomTime();
    CustomTime sleepTime = new CustomTime();

    public static SleepHourFragment newInstance(boolean isDefault){
        SleepHourFragment fragment = new SleepHourFragment();
        Bundle params = new Bundle();
        params.putBoolean(IS_DEFAULT, isDefault);
        fragment.setArguments(params);

        return fragment;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.sleephour_fragment, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        setupTitle(getArguments().getBoolean(IS_DEFAULT));
        setupTimer();
        setupListeners();
    }

    private void setupTitle(boolean isDefault){
        TextView title = getView().findViewById(R.id.am_title_text);

        if(isDefault){
            title.setText(getString(R.string.home_title));
        } else {
            title.setText(getString(R.string.home_second_title));
        }
    }

    private void setupListeners()
    {
        AppCompatButton editButton = getView().findViewById(R.id.am_edit_btn);
        editButton.setOnClickListener(view -> {
            TimePickerDialog timeDialog = new TimePickerDialog(getContext(),timeListener,sleepTime.getHour(),sleepTime.getMinutes(),true);
            timeDialog.show();
        });
    }


    private void setupTimer()
    {
        String timeString = paddingTime(displayTime.getHour())+":"+paddingTime(displayTime.getMinutes());

        TextView timer = getView().findViewById(R.id.am_timer_text);
        timer.setText(timeString);
    }

    private String paddingTime(int time){
        String timeString = "";

        if(time < 10){
            timeString = "0"+time;
        } else {
            timeString = String.valueOf(time);
        }

        return timeString;
    }

    TimePickerDialog.OnTimeSetListener timeListener = (view, hour, minute) -> {
        sleepTime.setHour(hour);
        sleepTime.setMinutes(minute);
        displayTime = sleepTime.setFromCurrentDiff();
        setupTimer();
    };
}
