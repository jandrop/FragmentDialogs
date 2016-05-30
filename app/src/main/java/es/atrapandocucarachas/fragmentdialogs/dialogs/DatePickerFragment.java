/**
 * Clase DatePickerFragment para mostrar un cuadro de dialogo con la fecha
 *
 * @author Alejandro Platas Mallo
 * @version 1.0
 * @since 2014-12-19
 */


package es.atrapandocucarachas.fragmentdialogs.dialogs;

import android.app.Activity;
import android.app.DatePickerDialog;
import android.app.DatePickerDialog.OnDateSetListener;
import android.app.Dialog;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.DialogFragment;
import android.widget.DatePicker;

import java.util.Calendar;

public class DatePickerFragment extends DialogFragment implements OnDateSetListener {

    private static final String EXTRA_DAY = "day";
    private static final String EXTRA_MONTH = "month";
    private static final String EXTRA_YEAR = "year";

    private DateSelectedListener listener;
    private int year, month, day;

    public DatePickerFragment() {

    }

    public static DatePickerFragment newInstance(int day, int month, int year) {
        DatePickerFragment f = new DatePickerFragment();
        Bundle args = new Bundle();
        args.putInt(EXTRA_DAY, day);
        args.putInt(EXTRA_MONTH, month);
        args.putInt(EXTRA_YEAR, year);
        f.setArguments(args);
        return f;
    }

    public void setOnDateListener(DateSelectedListener listener) {
        this.listener = listener;
    }

    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
        if (listener == null) {
            try {
                listener = (DateSelectedListener) activity;
            } catch (ClassCastException e) {
                throw new ClassCastException(activity.toString() +
                        " you must implement DateSelectedListener or call the method setOnDateListener");
            }
        }
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            year = getArguments().getInt(EXTRA_YEAR);
            month = getArguments().getInt(EXTRA_MONTH);
            day = getArguments().getInt(EXTRA_DAY);
        }
    }

    @Override
    @NonNull
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        // Use the current date as the default date in the picker

        if (year == 0 && month == 0 && day == 0) {
            final Calendar c = Calendar.getInstance();
            year = c.get(Calendar.YEAR);
            month = c.get(Calendar.MONTH);
            day = c.get(Calendar.DAY_OF_MONTH);
        }

        // Create a new instance of DatePickerDialog and return it
        return new DatePickerDialog(getContext(), this, year, month, day);
    }

    @Override
    public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth) {
        listener.onDateSelected(year, monthOfYear + 1, dayOfMonth);
    }

    public interface DateSelectedListener {
        void onDateSelected(int year, int month, int day);
    }

}
