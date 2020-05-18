package sg.edu.rp.c346.id18014854.reservation;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.TimePicker;
import android.widget.Toast;

import java.util.Calendar;

public class MainActivity extends AppCompatActivity {

    Button btnBook;
    Button btnReset;
    EditText etName;
    EditText etMN;
    EditText etPax;
    CheckBox cbArea;
    DatePicker dp;
    TimePicker tp;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        btnBook = findViewById(R.id.buttonReservation);
        btnReset = findViewById(R.id.buttonReset);
        etName = findViewById(R.id.editTextName);
        etMN = findViewById(R.id.editTextPhone);
        etPax = findViewById(R.id.editTextPax);
        cbArea = findViewById(R.id.checkBoxArea);
        dp = findViewById(R.id.datePicker);
        tp = findViewById(R.id.timePicker);

        tp.setOnTimeChangedListener(new TimePicker.OnTimeChangedListener() {
            @Override
            public void onTimeChanged(TimePicker view, int hourOfDay, int minute) {
                if (tp.getCurrentHour() > 20) {
                    tp.setCurrentHour(20);
                }
                if (tp.getCurrentHour() < 8) {
                    tp.setCurrentHour(8);
                }
            }
        });

        btnBook.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String message = "";
                Calendar now = Calendar.getInstance();
                Calendar res = Calendar.getInstance();
                res.set(dp.getYear(), dp.getMonth(), dp.getDayOfMonth(), tp.getCurrentHour(), tp.getCurrentMinute());
                if (etName.getText().toString().isEmpty() || etMN.getText().toString().isEmpty() || etPax.getText().toString().isEmpty()) {
                    message += "Booking Failed: Fill in all inputs";
                }
                else{
                    if (now.after(res)){
                    message += "Please select date and time after today";
                    }
                    else {
                    message += "Reservation \nName : " + etName.getText() + "\n" +
                            "Mobile Number : " + etMN.getText() + "\n" +
                            "Pax : " + etPax.getText() + "\n";
                    if (cbArea.isChecked()) {
                        message += "Smoking Area \n";
                    } else {
                        message += "Non-smoking Area \n";
                    }
                    int month = dp.getMonth() + 1;
                    message += "Date is " + dp.getDayOfMonth() + "/" + month + "/" + dp.getYear() + "\n";
                    message += "Time is " + tp.getCurrentHour() + ":" + tp.getCurrentMinute();
                }
                }
                Toast.makeText(MainActivity.this, message, Toast.LENGTH_LONG).show();

            }
        });

        btnReset.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                etName.setText("");
                etMN.setText("");
                etPax.setText("");
                dp.updateDate(2020, 5, 1);
                tp.setCurrentHour(19);
                tp.setCurrentMinute(30);
                cbArea.setChecked(false);

                Toast.makeText(MainActivity.this, "Input Cleared", Toast.LENGTH_LONG).show();
            }
        });
    }
}
