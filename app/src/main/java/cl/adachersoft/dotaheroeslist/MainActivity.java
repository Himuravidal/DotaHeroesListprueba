package cl.adachersoft.dotaheroeslist;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);


        //TODO change this activity for a Tabbed Activity
        //TODO move this to a fragment

        RecyclerView recycler = (RecyclerView) findViewById(R.id.mainRv);
        recycler.setHasFixedSize(true);
        recycler.setLayoutManager(new LinearLayoutManager(this));

        DatabaseReference reference = FirebaseDatabase.getInstance().getReference().child("heroes");

        //TODO for favorites replace model heroe with String
        FirebaseRecyclerAdapter adapter = new FirebaseRecyclerAdapter<Heroe, HeroeHolder>(Heroe.class, android.R.layout.simple_list_item_1, HeroeHolder.class, reference) {
            @Override
            protected void populateViewHolder(HeroeHolder viewHolder, Heroe model, int position) {
                viewHolder.setName(model.localized_name);
            }
        };

        recycler.setAdapter(adapter);

    }

    public static class HeroeHolder extends RecyclerView.ViewHolder {
        View mView;

        public HeroeHolder(View itemView) {
            super(itemView);
            mView = itemView;
        }

        public void setName(final String name) {
            TextView field = (TextView) mView.findViewById(android.R.id.text1);
            field.setText(name);

            field.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    String uid = FirebaseAuth.getInstance().getCurrentUser().getUid();
                    DatabaseReference favorites = FirebaseDatabase.getInstance().getReference().child("favorites").child(uid);
                    favorites.child(name).setValue(name);
                    //TODO for favorites removeValue() https://firebase.google.com/docs/database/android/read-and-write
                    //TODO refactor this to better scope variables
                }
            });

        }
    }
}
