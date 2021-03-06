package com.example.cse_110_team14;

import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import static org.junit.Assert.*;

import android.text.SpannableStringBuilder;
import android.widget.EditText;

import androidx.lifecycle.Lifecycle;
import androidx.recyclerview.widget.RecyclerView;
import androidx.test.core.app.ActivityScenario;
import androidx.test.ext.junit.rules.ActivityScenarioRule;
import androidx.test.ext.junit.runners.AndroidJUnit4;

import java.util.List;

@RunWith(AndroidJUnit4.class)
public class SearchbarTest {
    @Rule
    public ActivityScenarioRule<SearchActivity> scenarioRule =
            new ActivityScenarioRule<>(SearchActivity.class);

    private final static int NUM_ANIMALS = 14;
    @Test
    public void allAnimalsInEmptySearchBar() {
        ActivityScenario<SearchActivity> scenario = scenarioRule.getScenario();

        scenario.moveToState(Lifecycle.State.CREATED);

        scenario.onActivity(activity -> {
//            // Makes sure all animals are originally present
            List<ZooData.VertexInfo> filteredList =
                    activity.filter(new SpannableStringBuilder(""));
            assertEquals(filteredList.size(), NUM_ANIMALS);

           filteredList =
                    activity.filter(new SpannableStringBuilder(""));
          // Makes sure all animals are present when there is nothing in the search bar
            assertEquals(filteredList.size(), NUM_ANIMALS);
        });
    }


    @Test
    public void filterAnimalsById() {
        ActivityScenario<SearchActivity> scenario = scenarioRule.getScenario();

        scenario.moveToState(Lifecycle.State.CREATED);

        scenario.onActivity(activity -> {
            List<ZooData.VertexInfo> filteredList =
                    activity.filter(new SpannableStringBuilder("Bird"));
            assertEquals(filteredList.size(), 6);
            assertEquals(filteredList.get(0).name, "Bali Mynah");


        });

    }

    @Test
    public void testSearchBehavior() {
        ActivityScenario<SearchActivity> scenario = ActivityScenario.launch(SearchActivity.class);
        scenario.moveToState(Lifecycle.State.CREATED);

        scenario.onActivity(activity -> {
            EditText searchBar = activity.searchBar;
            RecyclerView recyclerView = activity.searchRecyclerView;

            searchBar.requestFocus();
            searchBar.setText("Gorilla");
            searchBar.clearFocus();

            assertEquals("Gorilla", (searchBar.getText()).toString());
        });
    }

}