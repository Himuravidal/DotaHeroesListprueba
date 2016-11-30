package cl.adachersoft.dotaheroeslist.login;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Toast;

import com.firebase.ui.auth.AuthUI;

import cl.adachersoft.dotaheroeslist.main.MainActivity;
import cl.adachersoft.dotaheroeslist.R;

import static com.firebase.ui.auth.ui.AcquireEmailHelper.RC_SIGN_IN;

public class FullscreenActivity extends AppCompatActivity implements LoginCallback{


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fullscreen);
        new LoginValidation(this).init();
    }

    @Override
    public void loged() {

        Toast.makeText(this, "LOGED", Toast.LENGTH_SHORT).show();
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);

    }

    public void singUp() {
        startActivityForResult(
                AuthUI.getInstance()
                        .createSignInIntentBuilder()
                        .setProviders(
                                AuthUI.EMAIL_PROVIDER)
                        .build(),
                RC_SIGN_IN);

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == RESULT_OK) {
            loged();
        }
    }

}
