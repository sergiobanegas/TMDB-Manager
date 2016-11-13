package es.upm.miw.tmdb.manager.fragments;

import android.app.AlertDialog;
import android.app.Dialog;
import android.app.DialogFragment;
import android.content.DialogInterface;
import android.os.Bundle;

import es.upm.miw.tmdb.manager.R;
import es.upm.miw.tmdb.manager.activities.InfoPersonActivity;

public class DeletePersonDialogFragment extends DialogFragment {

    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        final InfoPersonActivity infoPersonActivity = (InfoPersonActivity) getActivity();
        AlertDialog.Builder builder = new AlertDialog.Builder(infoPersonActivity);
        builder
                .setTitle(R.string.deletePerson)
                .setMessage(R.string.txtDeletePersonQuestion)
                .setPositiveButton(
                        getString(R.string.yes),
                        new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                infoPersonActivity.delete();
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