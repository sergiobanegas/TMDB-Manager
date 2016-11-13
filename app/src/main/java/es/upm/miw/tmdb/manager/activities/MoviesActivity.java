package es.upm.miw.tmdb.manager.activities;

import android.app.SearchManager;
import android.content.Intent;
import android.support.v4.content.ContextCompat;
import android.support.v4.view.MenuItemCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.SearchView;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Collections;
import java.util.List;

import es.upm.miw.tmdb.manager.R;
import es.upm.miw.tmdb.manager.adapters.MoviesResultAdapter;
import es.upm.miw.tmdb.manager.models.configuration.Configuration;
import es.upm.miw.tmdb.manager.models.responses.movies.Movies;
import es.upm.miw.tmdb.manager.models.responses.movies.MoviesResult;
import es.upm.miw.tmdb.manager.services.TMDBRESTAPIService;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class MoviesActivity extends AppCompatActivity {

    private TMDBRESTAPIService apiService;
    private List<MoviesResult> moviesList;
    private String titleSearched;
    private LinearLayout columns;
    private TextView queryTitle, tvEmpty;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_movies);
        Retrofit retrofit = new Retrofit.Builder().baseUrl(Configuration.getInstance().getBaseUrl()).addConverterFactory(GsonConverterFactory.create()).build();
        apiService = retrofit.create(TMDBRESTAPIService.class);
        columns = (LinearLayout) findViewById(R.id.columns);
        queryTitle = (TextView) findViewById(R.id.queryTitle);
        tvEmpty = (TextView) findViewById(R.id.empty);
    }

    @Override
    protected void onResume() {
        super.onResume();
        if (titleSearched == null) {
            getMostPopularMovies();
        } else {
            searchMovie(titleSearched);
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.search_menu, menu);
        final MenuItem searchItem = menu.findItem(R.id.action_search);
        if (searchItem != null) {
            SearchView searchView = (SearchView) MenuItemCompat.getActionView(searchItem);
            EditText searchPlate = (EditText) searchView.findViewById(android.support.v7.appcompat.R.id.search_src_text);
            searchPlate.setHint(R.string.txtSearchMoviesInfo);
            View searchPlateView = searchView.findViewById(android.support.v7.appcompat.R.id.search_plate);
            searchPlateView.setBackgroundColor(ContextCompat.getColor(this, android.R.color.transparent));
            searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
                @Override
                public boolean onQueryTextSubmit(String query) {
                    titleSearched = query;
                    queryTitle.setText(R.string.searchResults);
                    searchMovie(query);
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
                titleSearched = null;
                getMostPopularMovies();
                break;
        }
        return true;
    }


    public void searchMovie(String name) {
        Call<Movies> callGetMovieByName = apiService.getMovieByName(name);
        callGetMovieByName.enqueue(new Callback<Movies>() {
            @Override
            public void onResponse(Call<Movies> call, Response<Movies> response) {
                Movies movies = response.body();
                if (movies != null) {
                    moviesList = movies.getResults();
                    Collections.sort(moviesList);
                    if (moviesList.size() > 0) {
                        columns.setVisibility(View.VISIBLE);
                    } else {
                        columns.setVisibility(View.INVISIBLE);
                    }
                    MoviesResultAdapter adapter = new MoviesResultAdapter(getApplicationContext(), R.layout.movies_result, moviesList);
                    ListView lvMovieList = (ListView) findViewById(R.id.itemList);
                    lvMovieList.setAdapter(adapter);
                    lvMovieList.setEmptyView(tvEmpty);
                    lvMovieList.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                        @Override
                        public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                            MoviesResult result = moviesList.get(position);
                            Intent intent = new Intent(getApplicationContext(), InfoMovieActivity.class);
                            Bundle bundle = new Bundle();
                            bundle.putParcelable(getString(R.string.MOVIERESULT_KEY), result);
                            intent.putExtras(bundle);
                            startActivity(intent);
                        }
                    });
                } else {
                    Log.i("MIW16", getString(R.string.strErrorMovies) + "with name " + titleSearched);
                }
            }

            @Override
            public void onFailure(Call<Movies> call, Throwable t) {
                Toast.makeText(
                        getApplicationContext(),
                        "ERROR: " + t.getMessage(),
                        Toast.LENGTH_LONG
                ).show();
                Log.e("MIW16", t.getMessage());
            }
        });
    }

    @Override
    public void onSaveInstanceState(Bundle outState) {
        outState.putString(getString(R.string.FILTER_KEY), titleSearched);
        super.onSaveInstanceState(outState);
    }

    @Override
    public void onRestoreInstanceState(Bundle savedInstanceState) {
        super.onRestoreInstanceState(savedInstanceState);
        titleSearched = savedInstanceState.getString(getString(R.string.FILTER_KEY));
    }


    public void getMostPopularMovies() {
        Call<Movies> callGetMostPopularMovies = apiService.getMostPopularMovies();
        callGetMostPopularMovies.enqueue(new Callback<Movies>() {
            @Override
            public void onResponse(Call<Movies> call, Response<Movies> response) {
                Movies movies = response.body();
                if (movies != null) {
                    moviesList = movies.getResults();
                    if (moviesList.size() > 0) {
                        MoviesResultAdapter adapter = new MoviesResultAdapter(getApplicationContext(), R.layout.movies_result, moviesList);
                        ListView lvList = (ListView) findViewById(R.id.itemList);
                        lvList.setAdapter(adapter);
                        lvList.setEmptyView(tvEmpty);
                        lvList.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                            @Override
                            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                                MoviesResult result = moviesList.get(position);
                                Intent intent = new Intent(getApplicationContext(), InfoMovieActivity.class);
                                Bundle bundle = new Bundle();
                                bundle.putParcelable(getString(R.string.MOVIERESULT_KEY), result);
                                intent.putExtras(bundle);
                                startActivity(intent);
                            }
                        });
                    }
                } else {
                    Log.i("MIW16", getString(R.string.strErrorMovies));
                }
            }

            @Override
            public void onFailure(Call<Movies> call, Throwable t) {
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
