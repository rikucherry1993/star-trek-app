package com.rikucherry.startrek.view;

import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.denzcoskun.imageslider.ImageSlider;
import com.denzcoskun.imageslider.constants.ScaleTypes;
import com.denzcoskun.imageslider.models.SlideModel;
import com.google.android.material.dialog.MaterialAlertDialogBuilder;
import com.rikucherry.startrek.R;
import com.rikucherry.startrek.model.ObjectsItem;
import com.rikucherry.startrek.util.AppConstants;

import java.util.ArrayList;
import java.util.List;

import static java.lang.Thread.sleep;

public class DestinationActivity extends AppCompatActivity {

    private final String TAG = this.getClass().getSimpleName();

    // views
    ImageButton imageButtonBack;
    ImageButton imageButtonHelp;
    TextView labelArrivingAt;
    TextView textArrivingAt;
    ImageSlider imageSlider;
    List<SlideModel> mSlideModel;
    RecyclerView objectList;

    // data set
    private List<ObjectsItem> mList = new ArrayList<>();
    private ObjectsItemAdapter mAdapter;
    private RecyclerView.LayoutManager mLayoutManager;

    // other components
    private LoadingDialog loadingDialog;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_destination);

        // view binding
        imageButtonBack = findViewById(R.id.image_button_back);
        imageButtonHelp = findViewById(R.id.image_button_help);
        labelArrivingAt = findViewById(R.id.label_arriving_at);
        textArrivingAt = findViewById(R.id.text_arriving_at);
        imageSlider = findViewById(R.id.image_galaxy_slider);
        objectList = findViewById(R.id.list_celestial_objects);

        loadingDialog = new LoadingDialog(this);

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

        loadingDialog.startLoadingDialog();

        //TODO: Replace with an actual api, show loading modal.
        new AsyncTask<Void,Void,Void>(){
            @Override
            protected Void doInBackground(Void... voids) {
                try {
                    sleep(3000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }

                return null;
            }

            @Override
            protected void onPostExecute(Void v){

                loadingDialog.dismissDialog();

                initializeUI();

            }

        }.execute();



    }


    /**
     * Initialize dynamic UI elements.
     */
    private void initializeUI(){

        // TODO: mock
        labelArrivingAt.setVisibility(View.VISIBLE);
        textArrivingAt.setText("Centaurus");

        // TODO: mock: bind images to image slider.
        mSlideModel.add(new SlideModel(R.drawable.centaurus_mock_01,
                ScaleTypes.CENTER_CROP));
        mSlideModel.add(new SlideModel(R.drawable.centaurus_mock_02,
                ScaleTypes.CENTER_CROP));
        mSlideModel.add(new SlideModel(R.drawable.centaurus_mock_03,
                ScaleTypes.CENTER_CROP));

        imageSlider.setImageList(mSlideModel,ScaleTypes.CENTER_CROP);


        initializeRecyclerView();
        setListeners();
    }


    /**
     * Initialize recyclerView.
     */
    private void initializeRecyclerView(){

        // TODO mock data
        ObjectsItem object1 = new ObjectsItem();
        object1.setImageResourceId(R.drawable.alpha_centauri_mock);
        object1.setObjectName("Alpha Centauri");
        object1.setObjectCategory("Star");
        object1.setObjectIntroduction("4th brightest star, a component of the triple Alpha Centauri star system. ");

        ObjectsItem object2 = new ObjectsItem();
        object2.setImageResourceId(R.drawable.beta_centauri_mock);
        object2.setObjectName("Beta Centauri");
        object2.setObjectCategory("Star");
        object2.setObjectIntroduction("Officially called Hadar,is a triple star system in the southern constellation of Centaurus.");

        ObjectsItem object3 = new ObjectsItem();
        object3.setImageResourceId(R.drawable.menkent_mock);
        object3.setObjectName("Theta Centauri");
        object3.setObjectCategory("Star");
        object3.setObjectIntroduction("Officially named Menkent,is a star in the southern constellation of Centaurus.");

        ObjectsItem object4 = new ObjectsItem();
        object4.setImageResourceId(R.drawable.gamma_centauri_mock);
        object4.setObjectName("Gamma Centauri");
        object4.setObjectCategory("Star");
        object4.setObjectIntroduction("A star in the southern constellation Centaurus. It has the proper name Muhlifain");

        ObjectsItem object5 = new ObjectsItem();
        object5.setImageResourceId(R.drawable.epsilon_centauri_mock);
        object5.setObjectName("Epsilon Centauri");
        object5.setObjectCategory("Star");
        object5.setObjectIntroduction("A star in the southern constellation of Centaurus. It is one of the brightest stars in the constellation with a slightly variable apparent visual magnitude of +2.30.");

        mList.add(object1);
        mList.add(object2);
        mList.add(object3);
        mList.add(object4);
        mList.add(object5);


        mAdapter = new ObjectsItemAdapter(this,mList);
        mLayoutManager = new LinearLayoutManager(this);
        objectList.setLayoutManager(mLayoutManager);
        objectList.setItemAnimator(new DefaultItemAnimator());
        objectList.setAdapter(mAdapter);

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
                //TODO: Set debug log.
                new MaterialAlertDialogBuilder(DestinationActivity.this, R.style.myMaterialAlertDialog)
                        .setTitle(getString(R.string.title_next_step))
                        .setMessage(getString(R.string.text_next_step))
                        .setPositiveButton(getString(R.string.button_positive),null).show();
            }
        });

    }


}
