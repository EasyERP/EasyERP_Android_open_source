package com.thinkmobiles.easyerp.presentation.screens.hr.employees;

import android.widget.Toast;

import com.thinkmobiles.easyerp.data.model.crm.common.images.ImageItem;
import com.thinkmobiles.easyerp.domain.hr.EmployeesRepository;
import com.thinkmobiles.easyerp.presentation.adapters.hr.EmployeesAdapter;
import com.thinkmobiles.easyerp.presentation.base.rules.master.alphabetical.AlphabeticalPresenter;
import com.thinkmobiles.easyerp.presentation.base.rules.master.alphabetical.MasterAlphabeticalFragment;
import com.thinkmobiles.easyerp.presentation.base.rules.master.selectable.SelectableAdapter;
import com.thinkmobiles.easyerp.presentation.managers.GoogleAnalyticHelper;
import com.thinkmobiles.easyerp.presentation.screens.gallery.GalleryActivity_;

import org.androidannotations.annotations.AfterInject;
import org.androidannotations.annotations.AfterViews;
import org.androidannotations.annotations.Bean;
import org.androidannotations.annotations.EFragment;

import java.util.ArrayList;

/**
 * Created by Lynx on 3/13/2017.
 */

@EFragment
public class EmployeesFragment extends MasterAlphabeticalFragment implements EmployeesContract.EmployeeView {

    private EmployeesContract.EmployeePresenter presenter;

    @Bean
    protected EmployeesAdapter employeesAdapter;
    @Bean
    protected EmployeesRepository employeesRepository;

    @Override
    protected SelectableAdapter getAdapter() {
        return employeesAdapter;
    }

    @Override
    protected AlphabeticalPresenter getPresenter() {
        return presenter;
    }

    @AfterInject
    @Override
    public void initPresenter() {
        new EmployeesPresenter(this, employeesRepository);
    }

    @AfterViews
    protected void initUI() {
        GoogleAnalyticHelper.trackScreenView(this, getResources().getConfiguration());

        alphabetView.setListener(letter -> {
            GoogleAnalyticHelper.trackClick(this, GoogleAnalyticHelper.EventType.SET_FILTER, "Letter " + letter);
            presenter.setLetter(letter);
        });
    }

    @Override
    public void setPresenter(EmployeesContract.EmployeePresenter presenter) {
        this.presenter = presenter;
    }

    @Override
    public String getScreenName() {
        return "Employees list screen";
    }

    @Override
    public void openDetailsScreen(String employeeID) {
        ArrayList<ImageItem> images = new ArrayList<>();
        ImageItem item1 = new ImageItem();
        ImageItem item2 = new ImageItem();
        ImageItem item3 = new ImageItem();
        ImageItem item4 = new ImageItem();
        ImageItem item5 = new ImageItem();

        item1.imageSrc = "/customImages/58be77c49531b78473550902/58be77c49531b78473550903.jpeg";
        item2.imageSrc = "/customImages/58be77c49531b78473550902/58be77d89220a4c47382640a.jpeg";
        item3.imageSrc = "/customImages/58be77c49531b78473550902/58be77e58d58517473184fc3.jpeg";
        item4.imageSrc = "/customImages/58be77c49531b78473550902/58be77ed9531b78473550908.jpeg";
        item5.imageSrc = "/customImages/58be77c49531b78473550902/58be77f68629ece673bba398.jpeg";

        images.add(item1);
        images.add(item2);
        images.add(item3);
        images.add(item4);
        images.add(item5);

        GalleryActivity_.intent(getActivity())
                .imageItems(images)
                .position(3)
                .title("ZBS book")
                .start();
    }
}
