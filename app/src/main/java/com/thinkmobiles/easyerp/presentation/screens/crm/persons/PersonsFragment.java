package com.thinkmobiles.easyerp.presentation.screens.crm.persons;

import android.support.v7.widget.LinearLayoutManager;
import android.widget.Toast;

import com.thinkmobiles.easyerp.R;
import com.thinkmobiles.easyerp.data.model.crm.persons.alphabet.AlphabetItem;
import com.thinkmobiles.easyerp.presentation.base.rules.SimpleListWithRefreshFragment;
import com.thinkmobiles.easyerp.presentation.custom.views.alphabet_view.AlphabetListAdapter;
import com.thinkmobiles.easyerp.presentation.custom.views.alphabet_view.AlphabetView;

import org.androidannotations.annotations.AfterViews;
import org.androidannotations.annotations.Bean;
import org.androidannotations.annotations.EFragment;
import org.androidannotations.annotations.ViewById;

import java.util.ArrayList;

/**
 * Created by Lynx on 1/20/2017.
 */

@EFragment(R.layout.fragment_persons)
public class PersonsFragment extends SimpleListWithRefreshFragment {

    @ViewById
    protected AlphabetView alphabetView_FP;

    @Bean
    protected AlphabetListAdapter alphabetListAdapter;

    @AfterViews
    protected void initUI() {
        alphabetView_FP.setListener(letter -> Toast.makeText(getActivity(), letter, Toast.LENGTH_SHORT).show());
        listRecycler.setAdapter(alphabetListAdapter);
        listRecycler.setLayoutManager(new LinearLayoutManager(getActivity(), LinearLayoutManager.VERTICAL, false));

        ArrayList<AlphabetItem> alphabetItems = new ArrayList<>();
        AlphabetItem itA = new AlphabetItem();
        itA.id = "A";
        AlphabetItem itB = new AlphabetItem();
        itB.id = "b";
        AlphabetItem itE = new AlphabetItem();
        itE.id = "E";
        AlphabetItem itK = new AlphabetItem();
        itK.id = "K";
        alphabetItems.add(itA);
        alphabetItems.add(itB);
        alphabetItems.add(itE);
        alphabetItems.add(itK);
        alphabetView_FP.setEnabledLetters(alphabetItems);
    }

    @Override
    public void onRefresh() {

    }

    @Override
    protected boolean needProgress() {
        return true;
    }
}
