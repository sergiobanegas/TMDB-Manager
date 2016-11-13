package es.upm.miw.tmdb.manager.activities;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.squareup.picasso.Picasso;


import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import es.upm.miw.tmdb.manager.R;
import es.upm.miw.tmdb.manager.utils.NonScrollListView;
import es.upm.miw.tmdb.manager.adapters.KnownForAdapter;
import es.upm.miw.tmdb.manager.fragments.DeletePersonDialogFragment;
import es.upm.miw.tmdb.manager.models.configuration.Configuration;
import es.upm.miw.tmdb.manager.models.responses.movies.Credits;
import es.upm.miw.tmdb.manager.models.responses.persons.KnownFor;
import es.upm.miw.tmdb.manager.models.responses.movies.MovieData;
import es.upm.miw.tmdb.manager.models.responses.movies.MoviesResult;
import es.upm.miw.tmdb.manager.models.responses.persons.PersonData;
import es.upm.miw.tmdb.manager.models.responses.persons.PersonsResult;
import es.upm.miw.tmdb.manager.models.database.Person;
import es.upm.miw.tmdb.manager.services.persons.PersonsResource;
import es.upm.miw.tmdb.manager.services.TMDBRESTAPIService;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class InfoPersonActivity extends AppCompatActivity {

    private TMDBRESTAPIService apiService;
    private PersonsResult personResult;
    private PersonData personData;
    private PersonsResource personResource;
    FloatingActionButton saveButton, deleteButton;
    private List<MovieData> knownForMovieData = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_info_person);
        personResult = getIntent().getExtras().getParcelable(getString(R.string.PERSONRESULT_KEY));
        personResource = PersonsResource.getInstance(this);
        TextView tvName = (TextView) findViewById(R.id.name);
        tvName.setText(personResult.getName());
        String popularity = new DecimalFormat("#.##").format(personResult.getPopularity());
        TextView tvTitle = (TextView) findViewById(R.id.popularity);
        tvTitle.setText(String.valueOf(popularity));
        final TextView tvBiography = (TextView) findViewById(R.id.biography);
        final TextView tvBirthday = (TextView) findViewById(R.id.birthday);
        final TextView tvDeathday = (TextView) findViewById(R.id.deathday);
        final TextView tvPlaceOfBirth = (TextView) findViewById(R.id.placeOfBirth);
        ImageView imageView = (ImageView) findViewById(R.id.image);
        Picasso.with(this).load(Configuration.getInstance().getPersonProfileImageBigUrl() + personResult.getProfilePath()).into(imageView);
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
                new DeletePersonDialogFragment().show(getFragmentManager(), "DELETE PERSON DIALOG");
            }
        });
        Collections.sort(personResult.getKnownFor());
        KnownForAdapter adapter = new KnownForAdapter(this, R.layout.knowfor_result, personResult.getKnownFor());
        NonScrollListView lvKnowForList = (NonScrollListView) findViewById(R.id.knownForList);
        lvKnowForList.setAdapter(adapter);
        lvKnowForList.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                KnownFor result = personResult.getKnownFor().get(position);
                Intent intent = new Intent(getApplicationContext(), InfoMovieActivity.class);
                Bundle bundle = new Bundle();
                bundle.putParcelable(getString(R.string.MOVIERESULT_KEY), new MoviesResult(result));
                intent.putExtras(bundle);
                startActivity(intent);
            }
        });
        Retrofit retrofit = new Retrofit.Builder().baseUrl(Configuration.getInstance().getBaseUrl()).addConverterFactory(GsonConverterFactory.create()).build();
        apiService = retrofit.create(TMDBRESTAPIService.class);
        Call<PersonData> callGetPersonData = apiService.getPersonData(personResult.getId());
        callGetPersonData.enqueue(new Callback<PersonData>() {
            @Override
            public void onResponse(Call<PersonData> call, Response<PersonData> response) {
                personData = response.body();
                if (personData != null) {
                    tvBiography.setText(personData.getBiography());
                    tvBirthday.setText(personData.getBirthday());
                    if (personData.getDeathday() == null || personData.getDeathday().equals("")) {
                        findViewById(R.id.deathdayText).setVisibility(View.INVISIBLE);
                    } else {
                        tvDeathday.setText(personData.getDeathday());
                    }
                    tvPlaceOfBirth.setText(personData.getPlaceOfBirth());
                    Person person = personResource.findOne(personData.getId());
                    if (person != null) {
                        saveButton.setVisibility(View.INVISIBLE);
                    } else {
                        deleteButton.setVisibility(View.INVISIBLE);
                    }
                } else {
                    Log.i("MIW16", getString(R.string.strErrorPersons));
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

    public void save() {
        for (KnownFor knownFor : personResult.getKnownFor()) {
            getMovieFromKnowForTitle(knownFor.getId());
        }
    }

    public void getMovieFromKnowForTitle(final int id) {
        Call<MovieData> callGetMovieData = apiService.getMovieData(id);
        callGetMovieData.enqueue(new Callback<MovieData>() {
                                     @Override
                                     public void onResponse(Call<MovieData> call, Response<MovieData> response) {
                                         final MovieData movie = response.body();
                                         knownForMovieData.add(movie);
                                         if (knownForMovieData.size() == personResult.getKnownFor().size()) {
                                             Call<Credits> callGetMovieCredits = apiService.getMovieCredits(movie.getId());
                                             callGetMovieCredits.enqueue(new Callback<Credits>() {
                                                 @Override
                                                 public void onResponse(Call<Credits> call, Response<Credits> response) {
                                                     Credits credits = response.body();
                                                     String directing = null;
                                                     String writing = null;
                                                     if (credits != null) {
                                                         if (credits.getCrew().size() == 1) {
                                                             directing = credits.getCrew().get(0).getName();
                                                         } else if (credits.getCrew().size() > 1) {
                                                             directing = credits.getCrew().get(0).getName();
                                                             writing = credits.getCrew().get(1).getName();
                                                         }
                                                     }
                                                     personResource.insert(personResult.getId(), personResult.getName(), personData.getBiography(), personData.getBirthday(), personData.getDeathday(), personData.getPlaceOfBirth(), Double.valueOf(new DecimalFormat("#.##").format(personData.getPopularity())), Configuration.getInstance().getPersonProfileImageSmallUrl() + personData.getProfilePath(), Configuration.getInstance().getPersonProfileImageBigUrl() + personData.getProfilePath(), knownForMovieData, directing, writing);
                                                     Toast.makeText(getApplicationContext(), R.string.personSaved, Toast.LENGTH_SHORT).show();
                                                     saveButton.setVisibility(View.INVISIBLE);
                                                     deleteButton.setVisibility(View.VISIBLE);
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
                                     }

                                     @Override
                                     public void onFailure(Call<MovieData> call, Throwable t) {
                                         Toast.makeText(
                                                 getApplicationContext(),
                                                 "ERROR: " + t.getMessage(),
                                                 Toast.LENGTH_LONG
                                         ).show();
                                         Log.e("MIW16", t.getMessage());
                                     }
                                 }

        );
    }

    public void delete() {
        personResource.delete(personResult.getId());
        Toast.makeText(this, R.string.personDeleted, Toast.LENGTH_SHORT).show();
        saveButton.setVisibility(View.VISIBLE);
        deleteButton.setVisibility(View.INVISIBLE);
    }

}
