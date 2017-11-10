package FireBase;

import android.app.Activity;
import android.support.annotation.NonNull;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

/**
 * Created by gshestakov on 11/9/2017.
 */

public class FireBaseProvider {
    private static  FireBaseProvider instance;
    private Activity activity;

    public FireBaseProvider(Activity activity) {
        this.activity = activity;
        mAuth = FirebaseAuth.getInstance();
        mAuthListener = new FirebaseAuth.AuthStateListener() {
            @Override
            public void onAuthStateChanged(@NonNull FirebaseAuth firebaseAuth) {
                FirebaseUser user = firebaseAuth.getCurrentUser();
                if (user != null) {
                    // User is signed in

                } else {
                    // User is signed out

                }
            }
        };
        signin("shestakov.g@gmail.com", "HereMobile");
    }

    public static FireBaseProvider getInstance() throws Exception {
        if (instance==null) throw new Exception("Instance doesn't exist");
        return instance;
    }

    public static FireBaseProvider getInstance(Activity activity) {
        if (instance==null) instance= new FireBaseProvider(activity);
        return instance;
    }

    private FirebaseAuth mAuth;
    private FirebaseAuth.AuthStateListener mAuthListener;

    public void signin(String email , String password)
    {
        mAuth.signInWithEmailAndPassword(email,password).addOnCompleteListener(activity, new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                if(task.isSuccessful()) {
                    Toast.makeText(activity, "Authorisation successful", Toast.LENGTH_SHORT).show();
                }else
                    Toast.makeText(activity, "Authorisation failed", Toast.LENGTH_SHORT).show();

            }
        });
    }
    public void registration (String email , String password){
        mAuth.createUserWithEmailAndPassword(email, password).addOnCompleteListener(activity, new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                if(task.isSuccessful())
                {
                    Toast.makeText(activity, "Authorisation successful", Toast.LENGTH_SHORT).show();
                }
                else
                    Toast.makeText(activity, "Authorisation failed", Toast.LENGTH_SHORT).show();
            }
        });
    }
}
