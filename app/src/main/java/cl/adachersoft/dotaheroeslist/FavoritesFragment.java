package cl.adachersoft.dotaheroeslist;


import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import static android.R.attr.key;
import static android.os.Build.VERSION_CODES.M;
import static com.google.android.gms.internal.zzoe.ke;


public class FavoritesFragment extends Fragment {


    public static FavoritesFragment newInstance() {
        return new FavoritesFragment();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_favorites, container, false);
    }


    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        RecyclerView recycler = (RecyclerView) view.findViewById(R.id.FavRv);
        recycler.setHasFixedSize(true);
        recycler.setLayoutManager(new LinearLayoutManager(getContext()));

        String uid = FirebaseAuth.getInstance().getCurrentUser().getUid();
        DatabaseReference reference = FirebaseDatabase.getInstance().getReference().child("favorites").child(uid);

     FirebaseRecyclerAdapter<String,FavHolder>adapter= new FirebaseRecyclerAdapter<String, FavHolder>(String.class,android.R.layout.simple_list_item_1, FavoritesFragment.FavHolder.class, reference) {
         @Override
         protected void populateViewHolder(FavHolder viewHolder, String model, int position) {
             viewHolder.setView(model);
         }
     };


        recycler.setAdapter(adapter);
    }


    public static class FavHolder extends RecyclerView.ViewHolder {
        View mView;

        private FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
        private String uid = user.getUid();
        private DatabaseReference reference =FirebaseDatabase.getInstance().getReference().child("favorites");

        public FavHolder(View itemView) {
            super(itemView);
            mView = itemView;
        }

        public void setView(final String name) {
            TextView field = (TextView) mView.findViewById(android.R.id.text1);
            field.setText(name);
            field.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    reference.child(uid).child(name).removeValue();

                }
            });

        }
    }

}
