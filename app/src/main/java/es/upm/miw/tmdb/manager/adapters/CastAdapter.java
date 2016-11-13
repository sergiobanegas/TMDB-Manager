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

import java.util.List;

import es.upm.miw.tmdb.manager.R;
import es.upm.miw.tmdb.manager.models.configuration.Configuration;
import es.upm.miw.tmdb.manager.models.responses.movies.Cast;

public class CastAdapter extends ArrayAdapter<Cast> {

    private Context context;
    private List<Cast> data;
    private int resourceLayoutId;

    public CastAdapter(Context context, int resource, List<Cast> items) {
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
        Cast cast = data.get(position);
        ((TextView) view.findViewById(R.id.name)).setText(cast.getName());
        ((TextView) view.findViewById(R.id.character)).setText(cast.getCharacter());
        ImageView imageView = (ImageView) view.findViewById(R.id.image);
        String imageLink = cast.getProfilePath();
        Picasso.with(getContext()).load(Configuration.getInstance().getPersonProfileImageSmallUrl() + imageLink).into(imageView);
        return view;
    }
}
