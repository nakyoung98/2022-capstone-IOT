package com.cauiot.noyakja;

import android.app.Dialog;
import android.app.TimePickerDialog;
import android.os.Bundle;
import android.text.format.DateFormat;
import android.util.Log;
import android.widget.TextView;
import android.widget.TimePicker;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.DialogFragment;
import androidx.lifecycle.MutableLiveData;

import com.cauiot.noyakja.DB.DBSettingMedicine;
import com.cauiot.noyakja.DB.MyTime;

import java.util.Calendar;

public class TimePickerFragment extends DialogFragment implements TimePickerDialog.OnTimeSetListener {

    private final String TAG = "TimePickerFragment";
    MyTime myTime = new MyTime();
    TextView currentTextView;

    @NonNull
    @Override
    public Dialog onCreateDialog(@Nullable Bundle savedInstanceState) {
        final Calendar c = Calendar.getInstance();
        int hour = c.get(Calendar.HOUR_OF_DAY);
        int minute = c.get(Calendar.MINUTE);

        return new TimePickerDialog(getActivity(), this, hour, minute,
                DateFormat.is24HourFormat(getActivity()));
    }

    @Override
    public void onTimeSet(TimePicker timePicker, int hourOfDay, int minute) {
        myTime.setHour(hourOfDay);
        myTime.setMin(minute);
        Log.i(TAG, myTime.toString());
        currentTextView.setText(myTime.toString());
    }

    public void setCurrentTextView(TextView currentTextView) {
        this.currentTextView = currentTextView;
    }

    public MyTime getMyTime() {
        Log.i(TAG,"getMyTime: "+ myTime.toString());
        return myTime;
    }
}