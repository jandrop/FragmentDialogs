package es.atrapandocucarachas.fragmentdialogs;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Toast;

import java.util.Locale;

import es.atrapandocucarachas.fragmentdialogs.dialogs.AlertDialogFragment;
import es.atrapandocucarachas.fragmentdialogs.dialogs.DatePickerFragment;
import es.atrapandocucarachas.fragmentdialogs.dialogs.ListDialogFragment;

public class MainActivity extends AppCompatActivity {

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

    public void alertDialog(View view) {
        AlertDialogFragment dialog = AlertDialogFragment.newInstance(R.string.alert_dialog_title, R.string.alert_dialog_message);
        dialog.show(getSupportFragmentManager(), "dialogAlert");
        dialog.addListener(new AlertDialogFragment.OnClickListener() {
            @Override
            public void onPositiveClick() {
                Toast.makeText(MainActivity.this, "Positive Click", Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onNegativeClick() {
                Toast.makeText(MainActivity.this, "Negative Click", Toast.LENGTH_SHORT).show();
            }
        });
    }

    public void listDialog(View view) {
        String[] mItems = {
                "Single line text goes here",
                "Two line wrapped text goes here\nmaking it wrap to next line",
                "Single line text goes here",
                "Three line wrapped text goes here\nmaking it wrap to nex line and\ncontinues longer to be here"
        };

        ListDialogFragment dialog = ListDialogFragment.newInstance(mItems,R.string.list_dialog_title);
        dialog.show(getSupportFragmentManager(),"listDialog");
        dialog.setOnItemClickListener(new ListDialogFragment.OnItemClickListener() {
            @Override
            public void onItemClick(int which) {
                Toast.makeText(MainActivity.this, "Position: " + which, Toast.LENGTH_SHORT).show();
            }
        });
    }
}
