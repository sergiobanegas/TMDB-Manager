package es.upm.miw.tmdb.manager.activities;

import android.app.SearchManager;
import android.content.Intent;
import android.support.v4.content.ContextCompat;
import android.support.v4.view.MenuItemCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.SearchView;
import android.text.Html;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Collections;
import java.util.List;

import es.upm.miw.tmdb.manager.R;
import es.upm.miw.tmdb.manager.adapters.PersonsResultAdapter;
import es.upm.miw.tmdb.manager.models.configuration.Configuration;
import es.upm.miw.tmdb.manager.models.responses.persons.Persons;
import es.upm.miw.tmdb.manager.models.responses.persons.PersonsResult;
import es.upm.miw.tmdb.manager.services.TMDBRESTAPIService;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class PersonsActivity extends AppCompatActivity {

    private String nameSearched;
    private TMDBRESTAPIService apiService;
    private LinearLayout columns;
    private RelativeLayout tvPageInfo;
    private ListView lvPersons;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_persons);
        Retrofit retrofit = new Retrofit.Builder().baseUrl(Configuration.getInstance().getBaseUrl()).addConverterFactory(GsonConverterFactory.create()).build();
        apiService = retrofit.create(TMDBRESTAPIService.class);
        columns = (LinearLayout) findViewById(R.id.columns);
        columns.setVisibility(View.INVISIBLE);
        tvPageInfo = (RelativeLayout) findViewById(R.id.pageInfo);
        TextView tvPageInfoMainText = (TextView) findViewById(R.id.pageInfoMainText);
        String sourceString = getString(R.string.txtSearchPersonsInfo);
        tvPageInfoMainText.setText(Html.fromHtml(sourceString));
        lvPersons = (ListView) findViewById(R.id.itemList);
    }

    @Override
    protected void onResume() {
        super.onResume();
        if (nameSearched != null) {
            searchPersons(nameSearched);
        }
    }

    @Override
    public void onSaveInstanceState(Bundle outState) {
        outState.putString(getString(R.string.FILTER_KEY), nameSearched);
        super.onSaveInstanceState(outState);
    }

    @Override
    public void onRestoreInstanceState(Bundle savedInstanceState) {
        super.onRestoreInstanceState(savedInstanceState);
        nameSearched = savedInstanceState.getString(getString(R.string.FILTER_KEY));
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.search_menu, menu);
        final MenuItem searchItem = menu.findItem(R.id.action_search);

        if (searchItem != null) {
            SearchView searchView = (SearchView) MenuItemCompat.getActionView(searchItem);
            EditText searchPlate = (EditText) searchView.findViewById(android.support.v7.appcompat.R.id.search_src_text);
            searchPlate.setHint(R.string.searchPersonHint);
            View searchPlateView = searchView.findViewById(android.support.v7.appcompat.R.id.search_plate);
            searchPlateView.setBackgroundColor(ContextCompat.getColor(this, android.R.color.transparent));
            searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
                @Override
                public boolean onQueryTextSubmit(String query) {
                    nameSearched = query;
                    searchPersons(query);
                    return false;
                }

                @Override
                public boolean onQueryTextChange(String newText) {
                    return false;
                }
            });
            SearchManager searchManager = (SearchManager) getSystemService(SEARCH_SERVICE);
            searchView.setSearchableInfo(searchManager.getSearchableInfo(getComponentName()));

        }
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.action_refresh:
                nameSearched = null;
                tvPageInfo.setVisibility(View.VISIBLE);
                columns.setVisibility(View.INVISIBLE);
                lvPersons.setVisibility(View.INVISIBLE);
                break;
        }
        return true;
    }

    private void searchPersons(String name) {
        Call<Persons> callGetPersonByName = apiService.getPersonByName(name);
        callGetPersonByName.enqueue(new Callback<Persons>() {
            @Override
            public void onResponse(Call<Persons> call, Response<Persons> response) {
                Persons persons = response.body();
                lvPersons.setVisibility(View.VISIBLE);
                if (persons != null) {
                    tvPageInfo.setVisibility(View.GONE);
                    final List<PersonsResult> personsList = persons.getResults();
                    Collections.sort(personsList);
                    if (personsList.size() > 0) {
                        columns.setVisibility(View.VISIBLE);
                        PersonsResultAdapter adapter = new PersonsResultAdapter(getApplicationContext(), R.layout.persons_result, personsList);
                        lvPersons.setAdapter(adapter);
                        lvPersons.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                            @Override
                            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                                PersonsResult result = personsList.get(position);
                                Intent intent = new Intent(getApplicationContext(), InfoPersonActivity.class);
                                Bundle bundle = new Bundle();
                                bundle.putParcelable(getString(R.string.PERSONRESULT_KEY), result);
                                intent.putExtras(bundle);
                                startActivity(intent);
                            }
                        });

                    }
                } else {
                    Log.i("MIW16", getString(R.string.strErrorPersons) + "with name " + nameSearched);
                }
            }

            @Override
            public void onFailure(Call<Persons> call, Throwable t) {
                Toast.makeText(
                        getApplicationContext(),
                        "ERROR: " + t.getMessage(),
                        Toast.LENGTH_LONG
                ).show();
                Log.e("MIW16", t.getMessage());
            }
        });
    }
}
