package gr.unipi.msc.android.movieexercise.ui.adapter;

import android.content.Context;
import android.os.Handler;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

import gr.unipi.msc.android.movieexercise.R;
import gr.unipi.msc.android.movieexercise.datamanager.model.MovieListObject;
import gr.unipi.msc.android.movieexercise.ui.activity.MovieDetailActivity;
import gr.unipi.msc.android.movieexercise.util.C;


public class MoviesAdapter extends RecyclerView.Adapter<MoviesAdapter.ViewHolder> {

    private Context context;
    private List<MovieListObject> data = new ArrayList<>();

    public MoviesAdapter(Context context) {
        this.context = context;
    }

    public void setData(List<MovieListObject> movies) {
        data = movies;
        notifyDataSetChanged();
    }

    public void addData(List<MovieListObject> movies) {
        data.addAll(movies);
        notifyDataSetChanged();
    }

    public void clearData() {
        data.clear();
        notifyDataSetChanged();
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.item_movie, viewGroup, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ViewHolder viewHolder, int i) {
        MovieListObject movieModel = data.get(i);

        viewHolder.title.setText(movieModel.getTitle());

        if (TextUtils.isEmpty(movieModel.getRelease_date())) {
            viewHolder.subtitle.setVisibility(View.GONE);
        } else {
            viewHolder.subtitle.setText(movieModel.getRelease_date());
        }


        Picasso.get().load(C.TMDB_IMAGE_URI+movieModel.getPoster_path()).into(viewHolder.cover);

        if (i == this.getItemCount() - 1){
            // do your load more task here
        }
    }

    @Override
    public int getItemCount() {
        return data.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder implements RecyclerView.OnClickListener{

        private ImageView cover;
        private TextView title, subtitle;

        public ViewHolder(View itemView) {
            super(itemView);
            title = (TextView) itemView.findViewById(R.id.title);
            subtitle = (TextView) itemView.findViewById(R.id.subtitle);
            cover = (ImageView) itemView.findViewById(R.id.image);
            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View v) {
            new Handler().postDelayed(new Runnable() {
                @Override
                public void run() {
                    context.startActivity(MovieDetailActivity.getCallingIntent(context, data.get(getPosition())));
                }
            }, 200); // <--- Giving time to the ripple effect finish before opening a new activity
        }
    }
}
