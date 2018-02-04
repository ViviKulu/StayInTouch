package fragments;


import android.os.AsyncTask;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.babimaji.stayintouch.R;
import com.example.babimaji.stayintouch.backend.AppDatabase;
import com.example.babimaji.stayintouch.controller.FellowsAdapter;
import com.example.babimaji.stayintouch.model.Fellow;

import java.util.ArrayList;
import java.util.List;

public class FavouriteFragment extends Fragment {

    private AppDatabase db;
    private RecyclerView rv;


    public static FavouriteFragment newInstance() {
        FavouriteFragment fragment = new FavouriteFragment();
        return fragment;
    }

    public FavouriteFragment() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_favourite, container, false);

        rv = v.findViewById(R.id.fellows_RV);
        return v;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        db = AppDatabase.getAppDatabase(getActivity());

        getFavorite();
    }

    public void getFavorite() {
        new AsyncTask<Void, Void, List<Fellow>>() {

            @Override
            protected List<Fellow> doInBackground(Void ... voids) {
                List<Fellow> favoriteFellows = db.fellowDao().getFavoriteFellows(true);
                return favoriteFellows;
            }

            @Override
            protected void onPostExecute(List<Fellow> favoriteFellows) {
                super.onPostExecute(favoriteFellows);

                FellowsAdapter fa = new FellowsAdapter(favoriteFellows);

                LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getActivity());
                rv.setAdapter(fa);
                rv.setLayoutManager(linearLayoutManager);


            }
        }.execute();
    }
}
