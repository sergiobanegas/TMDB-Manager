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
import es.upm.miw.tmdb.manager.models.responses.persons.PersonsResult;

public class PersonsResultAdapter extends ArrayAdapter<PersonsResult> {

    private Context context;
    private List<PersonsResult> data;
    private int resourceLayoutId;

    public PersonsResultAdapter(Context context, int resource, List<PersonsResult> items) {
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
        PersonsResult person = data.get(position);
        ImageView imageView = (ImageView) view.findViewById(R.id.image);
        Picasso.with(context).load(Configuration.getInstance().getPersonProfileImageSmallUrl() + person.getProfilePath()).into(imageView);
        String popularity = new DecimalFormat("#.##").format(person.getPopularity());
        ((TextView) view.findViewById(R.id.name)).setText(person.getName());
        ((TextView) view.findViewById(R.id.popularity)).setText(popularity);
        return view;
    }
}
