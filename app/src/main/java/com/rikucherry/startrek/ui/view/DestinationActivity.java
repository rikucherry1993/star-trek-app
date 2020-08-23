package com.rikucherry.startrek.ui.view;

import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.denzcoskun.imageslider.ImageSlider;
import com.denzcoskun.imageslider.constants.ScaleTypes;
import com.denzcoskun.imageslider.models.SlideModel;
import com.google.android.material.dialog.MaterialAlertDialogBuilder;
import com.rikucherry.startrek.R;
import com.rikucherry.startrek.constant.AppConstants;
import com.rikucherry.startrek.model.ObjectsItem;
import com.rikucherry.startrek.ui.adapter.ObjectsItemAdapter;
import com.rikucherry.startrek.ui.viewmodel.ListViewModel;

import java.util.ArrayList;
import java.util.List;

public class DestinationActivity extends AppCompatActivity {

    private final String TAG = this.getClass().getSimpleName();

    // views
    ImageButton imageButtonBack;
    ImageButton imageButtonHelp;
    TextView labelArrivingAt;
    TextView textArrivingAt;
    ImageSlider imageSlider;
    List<SlideModel> mSlideModel;
    RecyclerView recyclerView;

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
        recyclerView = findViewById(R.id.list_celestial_objects);

        loadingDialog = new LoadingDialog(this);

        mSlideModel = new ArrayList<>();

        // Receive intent
        String inputSpeed = getIntent().getStringExtra(AppConstants.EXTRA_TRAVEL_SPEED);
        String inputTime = getIntent().getStringExtra(AppConstants.EXTRA_TRAVEL_TIME);

        // create view model
        final ListViewModel viewModel = new ListViewModel(getApplication(),inputSpeed,inputTime);
        observeViewModel(viewModel);

        initializeUI();


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


        mAdapter = new ObjectsItemAdapter(this,mList);
        mLayoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(mLayoutManager);
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        recyclerView.setAdapter(mAdapter);

    }


    /**
     * Set listeners to buttons.
     */
    private void setListeners(){

        imageButtonBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                setResult(RESULT_CANCELED);
                finish();
            }
        });

        imageButtonHelp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                new MaterialAlertDialogBuilder(DestinationActivity.this, R.style.myMaterialAlertDialog)
                        .setTitle(getString(R.string.title_next_step))
                        .setMessage(getString(R.string.text_next_step))
                        .setPositiveButton(getString(R.string.button_positive),null).show();
            }
        });

    }


    /**
     * Start observing viewModel
     * @param viewModel
     */
    private void observeViewModel(ListViewModel viewModel){

        // once receipt of list from viewModel, notify adapter to update changes
        viewModel.getObjectsItemObservable().observe(this, new Observer<List<ObjectsItem>>() {
            @Override
            public void onChanged(@Nullable List<ObjectsItem> objectsItems) {
                mList.clear();
                mList.addAll(objectsItems);
                mAdapter.notifyDataSetChanged();
            }
        });

    }


}
