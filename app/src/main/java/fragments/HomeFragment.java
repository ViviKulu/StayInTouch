package fragments;


import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.content.LocalBroadcastManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.babimaji.stayintouch.FavoriteListener;
import com.example.babimaji.stayintouch.MyIntentService;
import com.example.babimaji.stayintouch.R;
import com.example.babimaji.stayintouch.backend.AppDatabase;
import com.example.babimaji.stayintouch.controller.FellowsAdapter;
import com.example.babimaji.stayintouch.model.Fellow;
import java.util.ArrayList;
import constants.Constants;


public class HomeFragment extends Fragment implements FavoriteListener {

    private RecyclerView recyclerView;
    private FellowsAdapter fellowsAdapter;
    private AppDatabase db;
    private ResponseReceiver receiver;

    public static HomeFragment newInstance() {
        HomeFragment fragment = new HomeFragment();
        return fragment;
    }

    public HomeFragment() {
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        View v = inflater.inflate(R.layout.fragment_home, container, false);

        recyclerView = v.findViewById(R.id.fellows_RV);

        db = AppDatabase.getAppDatabase(getActivity());

        fellowsAdapter = new FellowsAdapter(this);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this.getActivity());
        recyclerView.setAdapter(fellowsAdapter);
        recyclerView.setLayoutManager(linearLayoutManager);

        return v;

    }

    @Override
    public void onResume() {
        super.onResume();
        IntentFilter broadcastFilter = new IntentFilter(
                ResponseReceiver.LOCAL_ACTION);
        receiver = new ResponseReceiver();
        LocalBroadcastManager localBroadcastManager = LocalBroadcastManager.getInstance(getActivity());
        localBroadcastManager.registerReceiver(receiver, broadcastFilter);

        Intent inputIntent = new Intent(getActivity(), MyIntentService.class);
        getActivity().startService(inputIntent);
    }

    @Override
    public void onPause() {
        super.onPause();
        LocalBroadcastManager localBroadcastManager = LocalBroadcastManager.getInstance(getActivity());
        localBroadcastManager.unregisterReceiver(receiver);
    }

    public void saveDataTodb(final ArrayList<Fellow> fellows) {
        new AsyncTask<Void, Void, Void>() {

            @Override
            protected Void doInBackground(Void... voids) {
                db.fellowDao().addAll(fellows);
                return null;
            }

        }.execute();
    }

    @Override
    public void updateFavorite(final Fellow fellow) {
        new AsyncTask<Void, Void, Void>() {

            @Override
            protected Void doInBackground(Void... voids) {
                //@Update does not work for some reason, so I first delete the fellow, then I add
                // the same fellow to db with value "isFavorite" set to true
                db.fellowDao().deleteFellow(fellow);
                db.fellowDao().isFavorite(fellow);
                return null;
            }

        }.execute();

    }

    public class ResponseReceiver extends BroadcastReceiver {

        public static final String LOCAL_ACTION = "workDone";

        @Override
        public void onReceive(Context context, Intent intent) {

            ArrayList<Fellow> receivedList = intent.getParcelableArrayListExtra(Constants.LIST_OF_FELLOWS);
            fellowsAdapter.setData(receivedList);
            fellowsAdapter.notifyDataSetChanged();

            saveDataTodb(receivedList);

        }
    }

}


