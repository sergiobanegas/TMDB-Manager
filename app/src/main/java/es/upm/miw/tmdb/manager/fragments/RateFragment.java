package es.upm.miw.tmdb.manager.fragments;

import android.app.DialogFragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import es.upm.miw.tmdb.manager.R;
import es.upm.miw.tmdb.manager.activities.InfoMovieActivity;

public class RateFragment extends DialogFragment {

    private View v;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        v = inflater.inflate(R.layout.rate, container, false);
        Button button = (Button) v.findViewById(R.id.rateButton);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                rateMovie();
            }
        });
        return v;
    }

    public void rateMovie() {
        EditText rateForm = (EditText) v.findViewById(R.id.rateForm);
        Double rating = Double.valueOf(rateForm.getText().toString());
        if (rating < 0 || rating > 10) {
            Toast.makeText(getActivity(), R.string.noValidRating, Toast.LENGTH_LONG).show();
        } else if (rating % 0.5 != 0) {
            Toast.makeText(getActivity(), R.string.multipleRating, Toast.LENGTH_LONG).show();
        } else {
            ((InfoMovieActivity) getActivity()).rateMovie(rating);
            rateForm.setText("");

        }

    }
}