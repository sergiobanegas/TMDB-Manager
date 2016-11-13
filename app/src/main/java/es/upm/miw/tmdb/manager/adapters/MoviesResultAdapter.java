package es.upm.miw.tmdb.manager.adapters;

import android.content.Context;
import android.support.annotation.NonNull;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import java.text.DecimalFormat;
import java.util.List;

import es.upm.miw.tmdb.manager.R;
import es.upm.miw.tmdb.manager.models.configuration.Configuration;
import es.upm.miw.tmdb.manager.models.responses.movies.MoviesResult;

public class MoviesResultAdapter extends ArrayAdapter<MoviesResult> {

    private Context context;
    private List<MoviesResult> data;
    private int resourceLayoutId;

    public MoviesResultAdapter(Context context, int resource, List<MoviesResult> items) {
        super(context, resource, items);
        this.context = context;
        resourceLayoutId = resource;
        data = items;
    }

    @NonNull
    @Override
    public View getView(int position, View convertView, @NonNull ViewGroup parent) {
        View view;
        if (null != convertView) {
            view = convertView;
        } else {
            LayoutInflater linf = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            view = linf.inflate(resourceLayoutId, parent, false);
        }
        MoviesResult moviesResult = data.get(position);
        ImageView imageView = (ImageView) view.findViewById(R.id.image);
        Picasso.with(context).load(Configuration.getInstance().getMoviePosterImageUrl() + moviesResult.getPosterPath()).into(imageView);
        ((TextView) view.findViewById(R.id.title)).setText(moviesResult.getTitle());
        String popularity = new DecimalFormat("#.##").format(moviesResult.getPopularity());
        ((TextView) view.findViewById(R.id.popularity)).setText(popularity);
        ((TextView) view.findViewById(R.id.releaseDate)).setText(moviesResult.getReleaseDate());
        return view;
    }
}
