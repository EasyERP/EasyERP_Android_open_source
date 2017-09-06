package com.thinkmobiles.easyerp.presentation.base.rules.master.filterable;

import android.app.Activity;
import android.content.Intent;
import android.support.v7.widget.SearchView;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

import com.thinkmobiles.easyerp.R;
import com.thinkmobiles.easyerp.presentation.adapters.crm.SuggestionAdapter;
import com.thinkmobiles.easyerp.presentation.base.rules.master.selectable.MasterSelectableFragment;
import com.thinkmobiles.easyerp.presentation.dialogs.FilterDialogFragment;
import com.thinkmobiles.easyerp.presentation.dialogs.FilterDialogFragment_;
import com.thinkmobiles.easyerp.presentation.holders.data.crm.FilterDH;
import com.thinkmobiles.easyerp.presentation.utils.Constants;
import com.thinkmobiles.easyerp.presentation.utils.filter.FilterHelper;

import org.androidannotations.annotations.AfterInject;
import org.androidannotations.annotations.EFragment;

import java.util.ArrayList;

/**
 * @author Alex Michenko (Created on 23.02.17).
 *         Company: Thinkmobiles
 *         Email: alex.michenko@thinkmobiles.com
 */
@EFragment
public abstract class MasterFilterableFragment extends MasterSelectableFragment implements FilterableView {


    protected abstract FilterablePresenter getPresenter();

    protected SuggestionAdapter suggestionAdapter;
    protected MenuItem menuFilters;
    protected MenuItem menuSearch;
    protected MenuItem menuClear;

    @AfterInject
    protected void initSearchAdapter() {
        suggestionAdapter = new SuggestionAdapter(getActivity());
    }

    @Override
    public int optionsMenuRes() {
        return R.menu.menu_filters;
    }

    @Override
    public void optionsMenuInitialized(Menu menu) {
        menuFilters = menu.findItem(R.id.menuFilters);
        menuSearch = menu.findItem(R.id.menuSearch);
        menuClear = menu.findItem(R.id.menuFilterRemoveAll);
        final SearchView searchView = (SearchView) menuSearch.getActionView();
        searchView.setSuggestionsAdapter(suggestionAdapter);
        searchView.setFocusable(false);
        searchView.setOnSuggestionListener(new SearchView.OnSuggestionListener()
        {
            @Override
            public boolean onSuggestionClick(int position)
            {
                FilterDH item = suggestionAdapter.getSuggestion(position);
                searchView.setQuery(item.name, false);
                getPresenter().filterBySearchItem(item);
                return true;
            }

            @Override
            public boolean onSuggestionSelect(int position)
            {
                return false;
            }
        });
        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                getPresenter().filterBySearchText(query);
                return true;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                return false;
            }
        });

        View clearButton = searchView.findViewById(R.id.search_close_btn);
        clearButton.setOnClickListener(v -> {
            searchView.setQuery("", false);
            searchView.clearFocus();
            getPresenter().removeAll();
        });

        getPresenter().buildOptionMenu();
    }

    @Override
    public void createMenuFilters(FilterHelper helper) {
        //TODO: NPE
        if (helper.isInitialized() && helper.getSearchableFilterList() != null) {
            menuFilters.setVisible(true);
            menuSearch.setVisible(true);
            suggestionAdapter.setItems(helper.getSearchableFilterList());
            helper.buildMenu(menuFilters.getSubMenu());
        }
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.menuFilters:
            case R.id.menuSearch:
                return false;
            case R.id.menuFilterRemoveAll:
                scrollListener.reset();
                getPresenter().removeAll();
                break;
            default:
                getPresenter().changeFilter(item.getItemId(), item.getTitle().toString());
                break;
        }
        return true;
    }

    @Override
    public void selectFilter(int id, boolean isSelected) {
        menuFilters.getSubMenu().getItem(id).setChecked(isSelected);
    }

    @Override
    public void showFilterDialog(ArrayList<FilterDH> filterDHs, int requestCode, String filterName) {
        FilterDialogFragment dialogFragment = FilterDialogFragment_.builder()
                .filterList(filterDHs)
                .filterName(filterName)
                .build();
        dialogFragment.setTargetFragment(this, requestCode);
        dialogFragment.show(getFragmentManager(), getClass().getName());
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        scrollListener.reset();
        if (resultCode == Activity.RESULT_OK) {
            ArrayList<FilterDH> filterDHs = data.getParcelableArrayListExtra(Constants.KEY_FILTER_LIST);
            getPresenter().filterByList(filterDHs, requestCode);
        } else {
            getPresenter().removeFilter(requestCode);
        }
    }
}
