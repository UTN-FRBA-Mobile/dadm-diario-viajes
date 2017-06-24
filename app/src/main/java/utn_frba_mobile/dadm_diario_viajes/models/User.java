package utn_frba_mobile.dadm_diario_viajes.models;

import android.net.Uri;

import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.IgnoreExtraProperties;

/**
 * Created by toiacabrera on 14/05/17.
 */
@IgnoreExtraProperties
public class User {

    private String email;
    private String name;
    private Uri photoUrl;

    public User() {
        // Default constructor required for calls to DataSnapshot.getValue(User.class)
    }

    public static User create(FirebaseUser firebaseUser) {
        User user = new User();
        user.email = firebaseUser.getEmail();
        user.name = firebaseUser.getDisplayName();
        user.photoUrl = firebaseUser.getPhotoUrl();
        return user;
    }

    public String getEmail() {
        return this.email;
    }
    public String getName() {
        return name;
    }
    public Uri getPhotoUrl() {
        return photoUrl;
    }
}
