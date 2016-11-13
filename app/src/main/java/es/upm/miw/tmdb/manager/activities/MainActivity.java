package es.upm.miw.tmdb.manager.activities;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.squareup.picasso.Picasso;

import java.util.Calendar;
import java.util.List;

import es.upm.miw.tmdb.manager.R;
import es.upm.miw.tmdb.manager.models.responses.sessions.APIConfiguration;
import es.upm.miw.tmdb.manager.models.configuration.Configuration;
import es.upm.miw.tmdb.manager.models.responses.movies.Movies;
import es.upm.miw.tmdb.manager.models.responses.movies.MoviesResult;
import es.upm.miw.tmdb.manager.services.TMDBRESTAPIService;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class MainActivity extends AppCompatActivity {

    private static final String API_BASE_URL = "https://api.themoviedb.org/3/";
    private List<MoviesResult> moviesList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Retrofit retrofit = new Retrofit.Builder().baseUrl(API_BASE_URL).addConverterFactory(GsonConverterFactory.create()).build();
        final TMDBRESTAPIService apiService = retrofit.create(TMDBRESTAPIService.class);
        Call<APIConfiguration> configurationCall = apiService.getConfiguration();
        configurationCall.enqueue(new Callback<APIConfiguration>() {
            @Override
            public void onResponse(Call<APIConfiguration> call, Response<APIConfiguration> response) {
                APIConfiguration apiConfiguration = response.body();
                if (apiConfiguration != null) {
                    Configuration.getInstance().init(apiConfiguration, API_BASE_URL);
                    Call<Movies> mostPopularMoviesCall = apiService.getMostPopularMovies();
                    mostPopularMoviesCall.enqueue(new Callback<Movies>() {
                        @Override
                        public void onResponse(Call<Movies> call, Response<Movies> response) {
                            Movies movies = response.body();
                            if (movies != null) {
                                moviesList = movies.getResults();
                                if (moviesList.size() > 0) {
                                    final MoviesResult movie = moviesList.get(0);
                                    TextView tvTitle = (TextView) findViewById(R.id.mostPopularMovieTitle);
                                    tvTitle.setText(movie.getTitle());
                                    TextView tvOverview = (TextView) findViewById(R.id.mostPopularMovieOverview);
                                    if (movie.getOverview().length() > 99) {
                                        String shortOverview = movie.getOverview().substring(0, 100) + "...";
                                        tvOverview.setText(shortOverview);
                                    } else {
                                        tvOverview.setText(movie.getOverview());
                                    }
                                    ImageView ivImage = (ImageView) findViewById(R.id.mostPopularMovieImage);
                                    Picasso.with(getApplicationContext()).load(Configuration.getInstance().getMovieBackdropImageSmallUrl() + movie.getBackdropPath()).into(ivImage);
                                    LinearLayout tvInfo = (LinearLayout) findViewById(R.id.mostPopularMovieInfo);
                                    tvInfo.setOnClickListener(new View.OnClickListener() {
                                        @Override
                                        public void onClick(View v) {
                                            Intent intent = new Intent(getApplicationContext(), InfoMovieActivity.class);
                                            Bundle bundle = new Bundle();
                                            bundle.putParcelable(getString(R.string.MOVIERESULT_KEY), movie);
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
                    Call<Movies> lastYearMostPopularMoviesCall = apiService.getLastYearMostPopularMovies(Calendar.getInstance().get(Calendar.YEAR) - 1);
                    lastYearMostPopularMoviesCall.enqueue(new Callback<Movies>() {
                        @Override
                        public void onResponse(Call<Movies> call, Response<Movies> response) {
                            Movies movies = response.body();
                            if (movies != null) {
                                moviesList = movies.getResults();
                                if (moviesList.size() > 0) {
                                    final MoviesResult movie = moviesList.get(0);
                                    TextView tvTitle = (TextView) findViewById(R.id.lastYearMostPopularMovieTitle);
                                    tvTitle.setText(movie.getTitle());
                                    TextView tvOverview = (TextView) findViewById(R.id.lastYearMostPopularMovieOverview);
                                    if (movie.getOverview().length() > 99) {
                                        String shortOverview = movie.getOverview().substring(0, 100) + "...";
                                        tvOverview.setText(shortOverview);
                                    } else {
                                        tvOverview.setText(movie.getOverview());
                                    }
                                    ImageView ivImage = (ImageView) findViewById(R.id.lastYearMostPopularMovieImage);
                                    Picasso.with(getApplicationContext()).load(Configuration.getInstance().getMovieBackdropImageSmallUrl() + movie.getBackdropPath()).into(ivImage);
                                    LinearLayout tvInfo = (LinearLayout) findViewById(R.id.lastYearMostPopularMovieInfo);
                                    tvInfo.setOnClickListener(new View.OnClickListener() {
                                        @Override
                                        public void onClick(View v) {
                                            Intent intent = new Intent(getApplicationContext(), InfoMovieActivity.class);
                                            Bundle bundle = new Bundle();
                                            bundle.putParcelable(getString(R.string.MOVIERESULT_KEY), movie);
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
                    Call<Movies> mostRatedMoviesCall = apiService.getMostRatedMovies();
                    mostRatedMoviesCall.enqueue(new Callback<Movies>() {
                        @Override
                        public void onResponse(Call<Movies> call, Response<Movies> response) {
                            Movies movies = response.body();
                            if (movies != null) {
                                moviesList = movies.getResults();
                                if (moviesList.size() > 0) {
                                    final MoviesResult movie = moviesList.get(0);
                                    TextView tvTitle = (TextView) findViewById(R.id.mostRatedMovieTitle);
                                    tvTitle.setText(movie.getTitle());
                                    TextView tvOverview = (TextView) findViewById(R.id.mostRatedMovieOverview);
                                    if (movie.getOverview().length() > 99) {
                                        String shortOverview = movie.getOverview().substring(0, 100) + "...";
                                        tvOverview.setText(shortOverview);
                                    } else {
                                        tvOverview.setText(movie.getOverview());
                                    }
                                    ImageView ivImage = (ImageView) findViewById(R.id.mostRatedMovieImage);
                                    Picasso.with(getApplicationContext()).load(Configuration.getInstance().getMovieBackdropImageSmallUrl() + movie.getBackdropPath()).into(ivImage);
                                    LinearLayout tvInfo = (LinearLayout) findViewById(R.id.mostRatedMovieInfo);
                                    tvInfo.setOnClickListener(new View.OnClickListener() {
                                        @Override
                                        public void onClick(View v) {
                                            Intent intent = new Intent(getApplicationContext(), InfoMovieActivity.class);
                                            Bundle bundle = new Bundle();
                                            bundle.putParcelable(getString(R.string.MOVIERESULT_KEY), movie);
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
                    Call<Movies> thisYearMostRatedMoviesCall = apiService.getThisYearMostRatedMovies(Calendar.getInstance().get(Calendar.YEAR));
                    thisYearMostRatedMoviesCall.enqueue(new Callback<Movies>() {
                        @Override
                        public void onResponse(Call<Movies> call, Response<Movies> response) {
                            Movies movies = response.body();
                            if (movies != null) {
                                moviesList = movies.getResults();
                                if (moviesList.size() > 0) {
                                    final MoviesResult movie = moviesList.get(0);
                                    TextView tvTitle = (TextView) findViewById(R.id.thisYearsmostRatedMovieTitle);
                                    tvTitle.setText(movie.getTitle());
                                    TextView tvOverview = (TextView) findViewById(R.id.thisYearsmostRatedMovieOverview);
                                    if (movie.getOverview().length() > 99) {
                                        String shortOverview = movie.getOverview().substring(0, 100) + "...";
                                        tvOverview.setText(shortOverview);
                                    } else {
                                        tvOverview.setText(movie.getOverview());
                                    }
                                    ImageView ivImage = (ImageView) findViewById(R.id.thisYearsmostRatedMovieImage);
                                    Picasso.with(getApplicationContext()).load(Configuration.getInstance().getMovieBackdropImageSmallUrl() + movie.getBackdropPath()).into(ivImage);
                                    LinearLayout tvInfo = (LinearLayout) findViewById(R.id.thisYearsmostRatedMovieInfo);
                                    tvInfo.setOnClickListener(new View.OnClickListener() {
                                        @Override
                                        public void onClick(View v) {
                                            Intent intent = new Intent(getApplicationContext(), InfoMovieActivity.class);
                                            Bundle bundle = new Bundle();
                                            bundle.putParcelable(getString(R.string.MOVIERESULT_KEY), movie);
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
                } else {
                    Log.i("MIW16", getString(R.string.strErrorConfig));
                }
            }

            @Override
            public void onFailure(Call<APIConfiguration> call, Throwable t) {
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
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.main_menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.searchMoviesAction:
                startActivity(new Intent(getApplicationContext(), MoviesActivity.class));
                break;
            case R.id.searchPersonsAction:
                startActivity(new Intent(getApplicationContext(), PersonsActivity.class));
                break;
        }
        return true;
    }

}
