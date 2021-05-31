package br.com.etec.ddm_a.fragment;

import android.app.TimePickerDialog;
import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.widget.AppCompatButton;
import androidx.fragment.app.Fragment;

import br.com.etec.ddm_a.R;
import br.com.etec.ddm_a.model.CustomTime;
import br.com.etec.ddm_a.view.TimeTextView;

public class SleepHourFragment extends Fragment {

    static final String IS_DEFAULT = "isDefault";

    TimeListener activityTimeListener;
    boolean isDefault;
    CustomTime displayTime = new CustomTime();
    CustomTime sleepTime = new CustomTime();
    TimeTextView timeText;

    public static SleepHourFragment newInstance(boolean isDefault){
        SleepHourFragment fragment = new SleepHourFragment();
        Bundle params = new Bundle();
        params.putBoolean(IS_DEFAULT, isDefault);
        fragment.setArguments(params);

        return fragment;
    }

    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);
        try{
            activityTimeListener = (TimeListener) context;
        } catch (ClassCastException error){
            throw new ClassCastException(context.toString() + " do not implement inner interface TimeListener");
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.sleephour_fragment, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        timeText = view.findViewById(R.id.am_timertext_view);
        isDefault = getArguments()!=null && getArguments().getBoolean(IS_DEFAULT);
        setupTitle();
        setupTimer();
        setupListeners();
    }

    private void setupTitle(){
        if(isDefault){
            timeText.setTitle(getString(R.string.home_title));
        } else {
            timeText.setTitle(getString(R.string.home_second_title));
        }
    }

    private void setupTimer()
    {
        timeText.setTime(displayTime.toString());
    }

    private void setupListeners()
    {
        AppCompatButton editButton = requireView().findViewById(R.id.am_edit_btn);
        editButton.setOnClickListener(view -> {
            TimePickerDialog timeDialog = new TimePickerDialog(getContext(),timeListener,sleepTime.getHour(),sleepTime.getMinutes(),true);
            timeDialog.show();
        });
    }

    TimePickerDialog.OnTimeSetListener timeListener = (view, hour, minute) -> {
        sleepTime.setHour(hour);
        sleepTime.setMinutes(minute);
        displayTime = sleepTime.setFromCurrentDiff();
        activityTimeListener.onTimeResult(sleepTime, isDefault);
        setupTimer();
    };

    public interface TimeListener{
        void onTimeResult(CustomTime time, boolean isFirst);
    }
}
