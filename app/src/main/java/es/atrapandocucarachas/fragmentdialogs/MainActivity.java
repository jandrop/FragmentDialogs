package es.atrapandocucarachas.fragmentdialogs;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Toast;

import java.util.Locale;

import es.atrapandocucarachas.fragmentdialogs.dialogs.DatePickerFragment;

public class MainActivity extends AppCompatActivity{

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void selectDate(View view) {
        DatePickerFragment dialog = new DatePickerFragment();
        dialog.show(getSupportFragmentManager(), "datePicker");
        dialog.setOnDateListener(new DatePickerFragment.DateSelectedListener() {
            @Override
            public void onDateSelected(int year, int month, int day) {
                Toast.makeText(MainActivity.this,
                        String.format(Locale.getDefault(), "%02d/%02d/%d", day, month, year),
                        Toast.LENGTH_SHORT)
                        .show();
            }
        });

    }
}
