package com.rikucherry.startrek.view;

import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.denzcoskun.imageslider.ImageSlider;
import com.denzcoskun.imageslider.constants.ScaleTypes;
import com.denzcoskun.imageslider.models.SlideModel;
import com.rikucherry.startrek.R;
import com.rikucherry.startrek.Util.AppConstants;

import java.util.ArrayList;
import java.util.List;

public class DestinationActivity extends AppCompatActivity {

    private final String TAG = this.getClass().getSimpleName();

    // views
    ImageButton imageButtonBack;
    ImageButton imageButtonHelp;
    TextView textArrivingAt;
    ImageSlider imageSlider;
    List<SlideModel> mSlideModel;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_destination);

        // view binding
        imageButtonBack = findViewById(R.id.image_button_back);
        imageButtonHelp = findViewById(R.id.image_button_help);
        textArrivingAt = findViewById(R.id.text_arriving_at);
        imageSlider = findViewById(R.id.image_galaxy_slider);

        mSlideModel = new ArrayList<>();

        // Receive intent
        String inputSpeed = getIntent().getStringExtra(AppConstants.EXTRA_TRAVEL_SPEED);
        String inputTime = getIntent().getStringExtra(AppConstants.EXTRA_TRAVEL_TIME);

        // call api
        callSearchApiFake(inputSpeed,inputTime);


    }


    /**
     * Call search api.
     * @param speed
     * @param time
     */
    private void callSearchApiFake(String speed, String time){
        //TODO: Replace with an actual api, show loading modal.


        initializeUI();
    }


    /**
     * Initialize dynamic UI elements.
     */
    private void initializeUI(){

        // TODO: mock
        textArrivingAt.setText("Centaurus");

        // TODO: mock: bind images to image slider.
        mSlideModel.add(new SlideModel(R.drawable.centaurus_mock_01,
                ScaleTypes.CENTER_CROP));
        mSlideModel.add(new SlideModel(R.drawable.centaurus_mock_02,
                ScaleTypes.CENTER_CROP));
        mSlideModel.add(new SlideModel(R.drawable.centaurus_mock_03,
                ScaleTypes.CENTER_CROP));

        imageSlider.setImageList(mSlideModel,ScaleTypes.CENTER_CROP);


        setListeners();
    }


    /**
     * Set listeners to buttons.
     */
    private void setListeners(){

        imageButtonBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //TODO: Set debug log.
                setResult(RESULT_CANCELED);
                finish();
            }
        });

        imageButtonHelp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //TODO: Show up introduction dialog.
                //TODO: Set debug log.
            }
        });

    }


}
