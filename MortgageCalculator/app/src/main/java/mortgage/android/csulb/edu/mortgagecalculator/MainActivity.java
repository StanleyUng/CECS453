package mortgage.android.csulb.edu.mortgagecalculator;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.SeekBar;
import android.widget.TextView;
import android.widget.Toast;

import static mortgage.android.csulb.edu.mortgagecalculator.R.id.seekBar;

public class MainActivity extends AppCompatActivity {
    private EditText text;
    private SeekBar seekbar;
    private TextView mortgage, textView;
    private CheckBox tax;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Find all ID's
        text = (EditText) findViewById(R.id.editText);
        seekbar = (SeekBar) findViewById(R.id.seekBar);
        tax = (CheckBox) findViewById(R.id.checkBox);
        mortgage = (TextView) findViewById(R.id.textView);
        textView = (TextView) findViewById(R.id.textView2);

        // Keeps track of the seekBar
        seekbar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            int rate = 10;

            @Override
            public void onProgressChanged(SeekBar seekBar, int p, boolean b) {
                rate = p;
                textView.setText("Interest Rate: " + rate);
            }
            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {
                textView.setText("Interest Rate: " + rate);
            }
            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {
                textView.setText("Interest Rate: " + rate);
            }
        });
    }

    public void onClick(View view) {
        double t = 0;
        double years;

        switch (view.getId()) {
            case R.id.button:
                if (text.getText().length() == 0) {
                    Toast.makeText(this, "Please enter a valid number", Toast.LENGTH_LONG).show();
                    return;
                }

                // Get the Principal amount that is typed in by the user
                float principal = Float.parseFloat(text.getText().toString());

                // The radio buttons to decide the amount of years
                RadioButton years_15 = (RadioButton) findViewById(R.id.radioButton);
                RadioButton years_20 = (RadioButton) findViewById(R.id.radioButton2);
                RadioButton years_30 = (RadioButton) findViewById(R.id.radioButton3);
                if (years_15.isChecked()) {
                    years = 15.0;
                } else if (years_20.isChecked()) {
                    years = 20.0;
                } else {
                    years = 30.0;
                }
                // The seekbar progress / 1200 = J
                double j = (double) seekbar.getProgress() / 1200.0;

                // Calculates the total amount of months of the term of the loan
                double months = years * 12.0;

                // Check if we need to take Tax and Insurance into account
                if (tax.isChecked()) {
                    t = principal * 0.001;
                }

                // Test if we have an interest rate
                if(j == 0) {
                    mortgage.setText("Mortgage: $" + String.valueOf(
                            String.format("%.2f", (principal / months) + t) ));
                } else {
                    mortgage.setText("Mortgage: $" + String.valueOf(
                            String.format("%.2f", (principal * (j / (1 - Math.pow((1 + j), (-months)))) + t))));
                }
                break;
        }
    }
}
