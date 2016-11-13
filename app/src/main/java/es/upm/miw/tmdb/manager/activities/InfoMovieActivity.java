package es.upm.miw.tmdb.manager.activities;

import android.content.Intent;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.squareup.picasso.Picasso;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;

import es.upm.miw.tmdb.manager.R;
import es.upm.miw.tmdb.manager.utils.NonScrollListView;
import es.upm.miw.tmdb.manager.adapters.CastAdapter;
import es.upm.miw.tmdb.manager.fragments.DeleteMovieDialogFragment;
import es.upm.miw.tmdb.manager.fragments.RateFragment;
import es.upm.miw.tmdb.manager.models.responses.post.APIPOSTResponse;
import es.upm.miw.tmdb.manager.models.responses.movies.Cast;
import es.upm.miw.tmdb.manager.models.configuration.Configuration;
import es.upm.miw.tmdb.manager.models.responses.movies.Credits;
import es.upm.miw.tmdb.manager.models.responses.movies.MovieCrew;
import es.upm.miw.tmdb.manager.models.responses.movies.MoviesResult;
import es.upm.miw.tmdb.manager.models.responses.persons.PersonData;
import es.upm.miw.tmdb.manager.models.responses.persons.Persons;
import es.upm.miw.tmdb.manager.models.responses.persons.PersonsResult;
import es.upm.miw.tmdb.manager.models.responses.sessions.SessionResponse;
import es.upm.miw.tmdb.manager.models.database.Movie;
import es.upm.miw.tmdb.manager.services.TMDBRESTAPIService;
import es.upm.miw.tmdb.manager.services.movies.MoviesResource;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class InfoMovieActivity extends AppCompatActivity {

    private TextView tvDirecting, tvWriting;
    private List<Cast> castList = new ArrayList<>();
    private List<PersonData> castPersonData = new ArrayList<>();
    private TMDBRESTAPIService apiService;
    private MoviesResult movieResult;
    private MoviesResource moviesResource;
    private FloatingActionButton saveButton, deleteButton;
    private SessionResponse session;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_info_movie);
        movieResult = getIntent().getExtras().getParcelable(getString(R.string.MOVIERESULT_KEY));
        moviesResource = MoviesResource.getInstance(this);
        TextView tvOverView = (TextView) findViewById(R.id.overview);
        tvOverView.setText(movieResult.getOverview());
        TextView tvTitle = (TextView) findViewById(R.id.title);
        tvTitle.setText(movieResult.getOriginalTitle());
        TextView tvReleaseDate = (TextView) findViewById(R.id.releaseDate);
        tvReleaseDate.setText(movieResult.getReleaseDate());
        Double popularity = Double.parseDouble(new DecimalFormat("#.##").format(movieResult.getPopularity()));
        TextView tvPopularity = (TextView) findViewById(R.id.popularity);
        tvPopularity.setText(String.valueOf(popularity));
        ImageView imageView = (ImageView) findViewById(R.id.image);
        Picasso.with(this).load(Configuration.getInstance().getMovieBackdropImageBigUrl() + movieResult.getBackdropPath()).into(imageView);
        tvDirecting = (TextView) findViewById(R.id.directing);
        tvWriting = (TextView) findViewById(R.id.writing);
        saveButton = (FloatingActionButton) findViewById(R.id.saveButton);
        saveButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                save();
            }
        });
        deleteButton = (FloatingActionButton) findViewById(R.id.deleteButton);
        deleteButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                new DeleteMovieDialogFragment().show(getFragmentManager(), "DELETE MOVIE DIALOG");
            }
        });
        Movie movie = moviesResource.findOne(movieResult.getId());
        if (movie != null) {
            saveButton.setVisibility(View.INVISIBLE);
        } else {
            deleteButton.setVisibility(View.INVISIBLE);
        }
        Retrofit retrofit = new Retrofit.Builder().baseUrl(Configuration.getInstance().getBaseUrl()).addConverterFactory(GsonConverterFactory.create()).build();
        apiService = retrofit.create(TMDBRESTAPIService.class);
        if (session == null) {
            identify();
        }
        Call<Credits> callGetMovieCredits = apiService.getMovieCredits(movieResult.getId());
        callGetMovieCredits.enqueue(new Callback<Credits>() {
            @Override
            public void onResponse(Call<Credits> call, Response<Credits> response) {
                Credits credits = response.body();
                if (credits != null) {
                    castList = credits.getCast();
                    if (castList.size() > 0) {
                        CastAdapter adapter = new CastAdapter(getApplicationContext(), R.layout.cast, castList);
                        NonScrollListView lvMovieCastList = (NonScrollListView) findViewById(R.id.castList);
                        lvMovieCastList.setAdapter(adapter);
                        lvMovieCastList.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                            @Override
                            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                                if (position<castList.size()){
                                    goToPerson(castList.get(position).getName());
                                }
                            }
                        });
                    } else {
                        Log.i("MIW16", getString(R.string.strErrorSearchCast));
                    }
                    final List<MovieCrew> crewList = credits.getCrew();
                    if (crewList.size() > 0) {
                        tvDirecting.setText(crewList.get(0).getName());
                        tvDirecting.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                goToPerson(crewList.get(0).getName());
                            }
                        });
                    }
                    if (crewList.size() > 1) {
                        tvWriting.setText(crewList.get(1).getName());
                        tvWriting.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                goToPerson(crewList.get(1).getName());
                            }
                        });
                    }
                } else {
                    Log.i("MIW16", getString(R.string.strErrorMovies));
                }
            }

            @Override
            public void onFailure(Call<Credits> call, Throwable t) {
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
    protected void onResume() {
        castPersonData = new ArrayList<>();
        super.onResume();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.movie_menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.rateMovie:
                new RateFragment().show(getFragmentManager(), "RATE DIALOG");
                break;
        }
        return true;
    }


    private void goToPerson(String name) {
        Call<Persons> callGetPersonByName = apiService.getPersonByName(name);
        callGetPersonByName.enqueue(new Callback<Persons>() {
            @Override
            public void onResponse(Call<Persons> call, Response<Persons> response) {
                Persons persons = response.body();
                if (persons != null) {
                    PersonsResult result = persons.getResults().get(0);
                    Intent intent = new Intent(getApplicationContext(), InfoPersonActivity.class);
                    Bundle bundle = new Bundle();
                    bundle.putParcelable(getString(R.string.PERSONRESULT_KEY), result);
                    intent.putExtras(bundle);
                    startActivity(intent);
                } else {
                    Log.i("MIW16", getString(R.string.strErrorPerson));
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

    public void save() {
        if (castList.size() > 10) {
            castList = castList.subList(0, 10);
        }
        for (Cast cast : castList) {
            getPersonFromCastId(cast.getId());
        }
    }

    public void delete() {
        moviesResource.delete(movieResult.getId());
        Toast.makeText(this, R.string.movieDeleted, Toast.LENGTH_SHORT).show();
        saveButton.setVisibility(View.VISIBLE);
        deleteButton.setVisibility(View.INVISIBLE);
    }

    public void getPersonFromCastId(int id) {
        Call<PersonData> callGetPersonData = apiService.getPersonData(id);
        callGetPersonData.enqueue(new Callback<PersonData>() {
            @Override
            public void onResponse(Call<PersonData> call, Response<PersonData> response) {
                PersonData person = response.body();
                if (person != null) {
                    castPersonData.add(person);
                    if (castPersonData.size() == castList.size()) {
                        String directing = tvDirecting.getText().toString();
                        String writing = tvWriting.getText().toString();
                        moviesResource.insert(movieResult.getId(), movieResult.getTitle(), movieResult.getOverview(), directing, writing, movieResult.getReleaseDate(), movieResult.getPopularity(), Configuration.getInstance().getMoviePosterImageUrl() + movieResult.getPosterPath(), Configuration.getInstance().getMovieBackdropImageBigUrl() + movieResult.getBackdropPath(), castList, castPersonData);
                        Toast.makeText(getApplicationContext(), R.string.movieSaved, Toast.LENGTH_SHORT).show();
                        saveButton.setVisibility(View.INVISIBLE);
                        deleteButton.setVisibility(View.VISIBLE);
                    }
                } else {
                    Log.i("MIW16", getString(R.string.strErrorPerson));
                }
            }

            @Override
            public void onFailure(Call<PersonData> call, Throwable t) {
                Toast.makeText(
                        getApplicationContext(),
                        "ERROR: " + t.getMessage(),
                        Toast.LENGTH_LONG
                ).show();
                Log.e("MIW16", t.getMessage());
            }
        });
    }

    private void identify() {
        Call<SessionResponse> callGetSession = apiService.getSession();
        callGetSession.enqueue(new Callback<SessionResponse>() {
            @Override
            public void onResponse(Call<SessionResponse> call, Response<SessionResponse> response) {
                SessionResponse sessionResponse = response.body();
                if (sessionResponse != null && sessionResponse.getSuccess()) {
                    session = sessionResponse;
                } else {
                    Log.i("MIW16", getString(R.string.strErrorSession));
                }
            }

            @Override
            public void onFailure(Call<SessionResponse> call, Throwable t) {
                Toast.makeText(
                        getApplicationContext(),
                        "ERROR: " + t.getMessage(),
                        Toast.LENGTH_LONG
                ).show();
                Log.e("MIW16", t.getMessage());
            }
        });
    }

    public void rateMovie(Double rating) {
        Call<APIPOSTResponse> callRateMovie = apiService.rateMovie(movieResult.getId(), rating, session.getGuestSessionId());
        callRateMovie.enqueue(new Callback<APIPOSTResponse>() {
            @Override
            public void onResponse(Call<APIPOSTResponse> call, Response<APIPOSTResponse> response) {
                APIPOSTResponse apiPostResponse = response.body();
                if (apiPostResponse != null && apiPostResponse.getStatusCode() != 3) {
                    Toast.makeText(getApplicationContext(), "You have rated the movie", Toast.LENGTH_SHORT).show();
                } else {
                    Log.i("MIW16", getString(R.string.strErrorRating));
                }
            }

            @Override
            public void onFailure(Call<APIPOSTResponse> call, Throwable t) {
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
