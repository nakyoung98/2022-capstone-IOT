package com.cauiot.noyakja;

import android.content.Context;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.cauiot.noyakja.DB.DBGuardians;
import com.cauiot.noyakja.DB.DBStoreQuery;
import com.cauiot.noyakja.DB.UserInfo;
import com.cauiot.noyakja.placeholder.GuardianContent;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.DocumentSnapshot;

/**
 * A fragment representing a list of Items.
 */
public class GuardianFragment extends Fragment {

    private final String TAG = "GuardianFragment";

    // TODO: Customize parameter argument names
    private static final String ARG_COLUMN_COUNT = "column-count";
    // TODO: Customize parameters
    private int mColumnCount = 1;

    /**
     * Mandatory empty constructor for the fragment manager to instantiate the
     * fragment (e.g. upon screen orientation changes).
     */
    public GuardianFragment() {
    }

    // TODO: Customize parameter initialization
    @SuppressWarnings("unused")
    public static GuardianFragment newInstance(int columnCount) {
        GuardianFragment fragment = new GuardianFragment();
        Bundle args = new Bundle();
        args.putInt(ARG_COLUMN_COUNT, columnCount);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        checkGuardianList();

        if (getArguments() != null) {
            mColumnCount = getArguments().getInt(ARG_COLUMN_COUNT);
        }


    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_guardian_list, container, false);

        // Set the adapter
        if (view instanceof RecyclerView) {
            Context context = view.getContext();
            RecyclerView recyclerView = (RecyclerView) view;
            recyclerView.addItemDecoration(new RecyclerViewAdapterDecoration(this.getContext()));
            if (mColumnCount <= 1) {
                recyclerView.setLayoutManager(new LinearLayoutManager(context));
            } else {
                recyclerView.setLayoutManager(new GridLayoutManager(context, mColumnCount));
            }
            recyclerView.setAdapter(new MyGuardianRecyclerViewAdapter(GuardianContent.ITEMS));
        }
        return view;
    }

    private void checkGuardianList() {
        DBStoreQuery dbStoreQuery = new DBStoreQuery(new DBGuardians().getDBName(), com.cauiot.noyakja.DB.UserInfo.getUid());
        dbStoreQuery.getReference().document(UserInfo.getUid()).get().addOnCompleteListener(new OnCompleteListener<DocumentSnapshot>() {
            @Override
            public void onComplete(@NonNull Task<DocumentSnapshot> task) {
                if(task.isSuccessful()){
                    DocumentSnapshot document = task.getResult();
                    if(document.exists()){
                        Log.d(TAG, "DocumentSnapshotData: " + document.getData());
                        DBGuardians.guardians = document.toObject(DBGuardians.guardians.getClass());
                        if(DBGuardians.guardians != null) Log.i(TAG,"guardians List: "+ DBGuardians.guardians.toString());
                        else Log.i(TAG,"no List!");
                    }else{
                        Log.d(TAG, "No such document");
                    }
                }else{
                    Log.d(TAG, "get failed with", task.getException());
                }
            }
        });
    }
}