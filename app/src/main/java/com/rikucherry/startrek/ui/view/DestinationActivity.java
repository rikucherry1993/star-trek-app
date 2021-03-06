package com.rikucherry.startrek.ui.view;

import android.os.Bundle;
import android.view.View;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.paging.PagedList;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.denzcoskun.imageslider.constants.ScaleTypes;
import com.denzcoskun.imageslider.models.SlideModel;
import com.google.android.material.dialog.MaterialAlertDialogBuilder;
import com.rikucherry.startrek.R;
import com.rikucherry.startrek.constant.AppConstants;
import com.rikucherry.startrek.databinding.ActivityDestinationBinding;
import com.rikucherry.startrek.model.ObjectsItem;
import com.rikucherry.startrek.ui.adapter.ObjectsItemAdapter;
import com.rikucherry.startrek.ui.viewmodel.ListViewModel;

import java.util.ArrayList;
import java.util.List;

import static java.lang.Thread.sleep;

public class DestinationActivity extends AppCompatActivity {

    private final String TAG = this.getClass().getSimpleName();

    private List<SlideModel> mSlideModel;

    // binding object
    private ActivityDestinationBinding binding;

    // data set
    private ObjectsItemAdapter mAdapter;
    private RecyclerView.LayoutManager mLayoutManager;

    // other components
    private LoadingDialog loadingDialog;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        // bind view
        binding = ActivityDestinationBinding.inflate(getLayoutInflater());
        View view = binding.getRoot();
        setContentView(view);

        // loading stuffs
        binding.setIsLoading(true);
        loadingDialog = new LoadingDialog(this);
        loadingDialog.startLoadingDialog();

        mSlideModel = new ArrayList<>();

        // Receive intent
        String inputSpeed = getIntent().getStringExtra(AppConstants.EXTRA_TRAVEL_SPEED);
        String inputTime = getIntent().getStringExtra(AppConstants.EXTRA_TRAVEL_TIME);

        // create view model
        final ListViewModel viewModel = new ListViewModel(getApplication(),inputSpeed,inputTime);
        observeViewModel(viewModel);

        initializeRecyclerView();


    }


    /**
     * Start observing viewModel
     * @param viewModel
     */
    private void observeViewModel(ListViewModel viewModel){

        // once receipt of list from viewModel, notify adapter to update changes
        viewModel.objectsItemObservable.observe(this, new Observer<PagedList<ObjectsItem>>() {
            @Override
            public void onChanged(@Nullable PagedList<ObjectsItem> objectsItems) {
                // TODO: fake api time lapse
                try {
                    sleep(3000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }

                binding.setIsLoading(false);

                if (objectsItems == null || objectsItems.size() == 0) {
                    //TODO: alert - You are in nowhere!
                } else {
                    binding.textArrivingAt.setText(objectsItems.get(0).getSystemName());
                    int imageId1 = objectsItems.get(0).getParentImageId1();
                    int imageId2 = objectsItems.get(0).getParentImageId2();
                    int imageId3 = objectsItems.get(0).getParentImageId3();
                    initializeSlider(imageId1,imageId2,imageId3,0);
                    setListeners();

                    mAdapter.submitList(objectsItems);
                }

                // dismiss loading dialog
                loadingDialog.dismissDialog();
            }
        });

    }



    /**
     * Initialize dynamic slider.
     */
    private void initializeSlider(int id1, int id2, int id3, int onError){

        // TODO: mock: bind images to image slider.
        id1 = R.drawable.centaurus_mock_01;
        id2 = R.drawable.centaurus_mock_02;
        id3 = R.drawable.centaurus_mock_03;

        mSlideModel.add(new SlideModel(id1, ScaleTypes.CENTER_CROP));
        mSlideModel.add(new SlideModel(id2, ScaleTypes.CENTER_CROP));
        mSlideModel.add(new SlideModel(id3, ScaleTypes.CENTER_CROP));

        binding.imageGalaxySlider.setImageList(mSlideModel,ScaleTypes.CENTER_CROP);

    }


    /**
     * Initialize recyclerView.
     */
    private void initializeRecyclerView(){


        mAdapter = new ObjectsItemAdapter();
        mLayoutManager = new LinearLayoutManager(this);
        binding.listCelestialObjects.setLayoutManager(mLayoutManager);
        binding.listCelestialObjects.setItemAnimator(new DefaultItemAnimator());
        binding.listCelestialObjects.setAdapter(mAdapter);

    }


    /**
     * Set listeners to buttons.
     */
    private void setListeners(){

        binding.imageButtonBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                setResult(RESULT_CANCELED);
                finish();
            }
        });

        binding.imageButtonHelp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                new MaterialAlertDialogBuilder(DestinationActivity.this, R.style.myMaterialAlertDialog)
                        .setTitle(getString(R.string.title_next_step))
                        .setMessage(getString(R.string.text_next_step))
                        .setPositiveButton(getString(R.string.button_positive),null).show();
            }
        });

    }


}
