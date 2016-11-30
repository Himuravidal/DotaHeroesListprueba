package cl.adachersoft.dotaheroeslist.login;

import com.google.firebase.auth.FirebaseAuth;

/**
 * Created by cristian on 30-11-2016.
 */

public class LoginValidation {

private LoginCallback callback;

    public LoginValidation (LoginCallback callback){
        this.callback = callback;
    }

    public void init(){
        FirebaseAuth auth = FirebaseAuth.getInstance();
        if (auth.getCurrentUser()!=null){
            callback.loged();
        }else {
            callback.singUp();
        }

    }


}
