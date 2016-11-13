package es.upm.miw.tmdb.manager.fragments;

import android.app.AlertDialog;
import android.app.Dialog;
import android.app.DialogFragment;
import android.content.DialogInterface;
import android.os.Bundle;

import es.upm.miw.tmdb.manager.R;
import es.upm.miw.tmdb.manager.activities.InfoMovieActivity;

public class DeleteMovieDialogFragment extends DialogFragment {

    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        final InfoMovieActivity infoMovieActivity = (InfoMovieActivity) getActivity();
        AlertDialog.Builder builder = new AlertDialog.Builder(infoMovieActivity);
        builder
                .setTitle(R.string.deleteMovie)
                .setMessage(R.string.txtDeleteMovieQuestion)
                .setPositiveButton(
                        getString(R.string.yes),
                        new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                infoMovieActivity.delete();
                            }
                        }
                )
                .setNegativeButton(
                        getString(R.string.no),
                        new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {

                            }
                        }
                );
        return builder.create();
    }
}