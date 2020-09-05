package com.rikucherry.startrek.ui.view;

import android.content.Intent;
import android.os.Bundle;
import android.view.MotionEvent;
import android.view.View;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.material.dialog.MaterialAlertDialogBuilder;
import com.google.android.material.slider.LabelFormatter;
import com.rikucherry.startrek.R;
import com.rikucherry.startrek.constant.AppConstants;
import com.rikucherry.startrek.databinding.ActivityTripInfoBinding;

/**
 * Fill in all the information needed for launch.
 */
public class TripInfoActivity extends AppCompatActivity {

    private final String TAG = this.getClass().getSimpleName();

    // binding object
    private ActivityTripInfoBinding binding;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityTripInfoBinding.inflate(getLayoutInflater());
        View view = binding.getRoot();
        setContentView(view);

        initializeUI();

        // TODO: Validation

    }


    /**
     * Initialize dynamic UI elements.
     */
    private void initializeUI(){
        // customize label values of slider
        // TODO: How to change font?
        binding.discreteSliderSpeed.setLabelFormatter(new LabelFormatter() {
            @NonNull
            @Override
            public String getFormattedValue(float value) {
                if (value == 0) {
                    binding.textTravelSpeed.setText("");
                    binding.imageSpacecraftImage.setImageDrawable(null);
                    binding.textSpacecraftImage.setVisibility(View.VISIBLE);
                    return AppConstants.NO_VELOCITY;
                } else if (value == 1) {
                    binding.textSpacecraftImage.setVisibility(View.INVISIBLE);
                    binding.textTravelSpeed.setText(AppConstants.ESCAPE_VELOCITY + getString(R.string.unit_travel_speed));
                    binding.imageSpacecraftImage.setImageResource(R.drawable.normal_spacecraft);
                    return AppConstants.ESCAPE_VELOCITY + getString(R.string.unit_travel_speed);
                } else if (value == 2) {
                    binding.textSpacecraftImage.setVisibility(View.INVISIBLE);
                    binding.textTravelSpeed.setText(AppConstants.SOLAR_SYSTEM_VELOCITY + getString(R.string.unit_travel_speed));
                    binding.imageSpacecraftImage.setImageResource(R.drawable.escape_from_solar_system);
                    return AppConstants.SOLAR_SYSTEM_VELOCITY + getString(R.string.unit_travel_speed);
                } else {
                    binding.textSpacecraftImage.setVisibility(View.INVISIBLE);
                    binding.textTravelSpeed.setText(AppConstants.SPEED_OF_LIGHT + getString(R.string.unit_travel_speed));
                    binding.imageSpacecraftImage.setImageResource(R.drawable.light);
                    return AppConstants.SPEED_OF_LIGHT + getString(R.string.unit_travel_speed);
                }
            }
        });


        binding.discreteSliderTime.setLabelFormatter(new LabelFormatter() {
            @NonNull
            @Override
            public String getFormattedValue(float value) {
                String labelTravelTime;
                if (value == 0) {
                    labelTravelTime = AppConstants.NO_TIME;
                    binding.textTravelTime.setText("");
                    return labelTravelTime;
                } else if (value == 1) {
                    labelTravelTime = "1" + " " + getString(R.string.unit_travel_time_day);
                } else if (value == 2) {
                    labelTravelTime = "1" + " " + getString(R.string.unit_travel_time_year);
                } else {
                    labelTravelTime = (int)value + 1 + " " + getString(R.string.unit_travel_time_years);
                }
                binding.textTravelTime.setText(labelTravelTime);
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
        binding.buttonLaunch.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View view, MotionEvent motionEvent) {
                switch (motionEvent.getAction()) {
                    case MotionEvent.ACTION_DOWN:
                        view.setPressed(true);
                        break;
                    case MotionEvent.ACTION_UP:
                        view.setPressed(false);

                        Intent i = new Intent(TripInfoActivity.this, DestinationActivity.class);
                        i.putExtra(AppConstants.EXTRA_TRAVEL_SPEED,binding.textTravelSpeed.getText().toString());
                        i.putExtra(AppConstants.EXTRA_TRAVEL_TIME,binding.textTravelTime.getText().toString());
                        startActivityForResult(i,AppConstants.DESTINATION_ACTIVITY);

                        // TODO: Set unit to an individual text view.

                        break;
                }
                return true;
            }
        });


        binding.imageButtonHelp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                new MaterialAlertDialogBuilder(TripInfoActivity.this, R.style.myMaterialAlertDialog)
                        .setTitle(R.string.title_how_to_use)
                        .setMessage(getString(R.string.text_introduction))
                        .setPositiveButton(getString(R.string.button_positive),null).show();
            }
        });

    }

}
