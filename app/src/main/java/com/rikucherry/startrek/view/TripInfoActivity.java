package com.rikucherry.startrek.view;

import android.content.Intent;
import android.os.Bundle;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.material.dialog.MaterialAlertDialogBuilder;
import com.google.android.material.slider.LabelFormatter;
import com.google.android.material.slider.Slider;
import com.rikucherry.startrek.R;
import com.rikucherry.startrek.Util.AppConstants;

/**
 * Fill in all the information needed for launch.
 */
public class TripInfoActivity extends AppCompatActivity {

    private final String TAG = this.getClass().getSimpleName();

    // views
    Slider sliderSpeed;
    Slider sliderTime;
    TextView textSpeed;
    TextView textTime;
    Button launchButton;
    ImageView spacecraftImage;
    TextView textSpacecraftImage;
    ImageButton imageButtonHelp;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_trip_info);

        // view binding
        sliderSpeed = findViewById(R.id.discrete_slider_speed);
        textSpeed = findViewById(R.id.text_travel_speed);
        sliderTime = findViewById(R.id.discrete_slider_time);
        textTime = findViewById(R.id.text_travel_time);
        launchButton = findViewById(R.id.button_launch);
        spacecraftImage = findViewById(R.id.image_spacecraft_image);
        textSpacecraftImage = findViewById(R.id.text_spacecraft_image);
        imageButtonHelp = findViewById(R.id.image_button_help);

        initializeUI();

        // TODO: Validation

    }


    /**
     * Initialize dynamic UI elements.
     */
    private void initializeUI(){
        // customize label values of slider
        // TODO: How to change font?
        sliderSpeed.setLabelFormatter(new LabelFormatter() {
            @NonNull
            @Override
            public String getFormattedValue(float value) {
                if (value == 0) {
                    textSpeed.setText("");
                    spacecraftImage.setImageDrawable(null);
                    textSpacecraftImage.setVisibility(View.VISIBLE);
                    return AppConstants.NO_VELOCITY;
                } else if (value == 1) {
                    textSpacecraftImage.setVisibility(View.INVISIBLE);
                    textSpeed.setText(AppConstants.ESCAPE_VELOCITY + getString(R.string.unit_travel_speed));
                    spacecraftImage.setImageResource(R.drawable.normal_spacecraft);
                    return AppConstants.ESCAPE_VELOCITY + getString(R.string.unit_travel_speed);
                } else if (value == 2) {
                    textSpacecraftImage.setVisibility(View.INVISIBLE);
                    textSpeed.setText(AppConstants.SOLAR_SYSTEM_VELOCITY + getString(R.string.unit_travel_speed));
                    spacecraftImage.setImageResource(R.drawable.escape_from_solar_system);
                    return AppConstants.SOLAR_SYSTEM_VELOCITY + getString(R.string.unit_travel_speed);
                } else {
                    textSpacecraftImage.setVisibility(View.INVISIBLE);
                    textSpeed.setText(AppConstants.SPEED_OF_LIGHT + getString(R.string.unit_travel_speed));
                    spacecraftImage.setImageResource(R.drawable.light);
                    return AppConstants.SPEED_OF_LIGHT + getString(R.string.unit_travel_speed);
                }
            }
        });


        sliderTime.setLabelFormatter(new LabelFormatter() {
            @NonNull
            @Override
            public String getFormattedValue(float value) {
                String labelTravelTime;
                if (value == 0) {
                    labelTravelTime = AppConstants.NO_TIME;
                    textTime.setText("");
                    return labelTravelTime;
                } else if (value == 1) {
                    labelTravelTime = "1" + " " + getString(R.string.unit_travel_time_day);
                } else if (value == 2) {
                    labelTravelTime = "1" + " " + getString(R.string.unit_travel_time_year);
                } else {
                    labelTravelTime = (int)value + 1 + " " + getString(R.string.unit_travel_time_years);
                }
                textTime.setText(labelTravelTime);
                return labelTravelTime;

            }
        });


        setListeners();

    }


    /**
     * Set listeners to buttons.
     */
    private void setListeners(){


        //TODO: Don't know why selector doesn't work.
        launchButton.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View view, MotionEvent motionEvent) {
                switch (motionEvent.getAction()) {
                    case MotionEvent.ACTION_DOWN:
                        view.setPressed(true);
                        break;
                    case MotionEvent.ACTION_UP:
                        view.setPressed(false);
                        // TODO: start destination activity here.
                        // TODO: Set debug log.

                        Intent i = new Intent(TripInfoActivity.this, DestinationActivity.class);
                        i.putExtra(AppConstants.EXTRA_TRAVEL_SPEED,textSpeed.getText().toString());
                        i.putExtra(AppConstants.EXTRA_TRAVEL_TIME,textTime.getText().toString());
                        startActivityForResult(i,AppConstants.DESTINATION_ACTIVITY);

                        // TODO: Set unit to an individual text view.
                        // TODO: Set debug log.

                        break;
                }
                return true;
            }
        });


        imageButtonHelp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //TODO: Set debug log.
                new MaterialAlertDialogBuilder(TripInfoActivity.this, R.style.myMaterialAlertDialog)
                        .setTitle(R.string.title_how_to_use)
                        .setMessage(getString(R.string.text_introduction))
                        .setPositiveButton(getString(R.string.button_positive),null).show();
            }
        });

    }

}
