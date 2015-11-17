package appwars.appwise.be.dj_database;

import android.app.Application;
import android.content.Context;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import com.parse.Parse;
import com.parse.ParseInstallation;

/**
 * Created by Lakkedelakke on 15/11/2015.
 */
public class DjDatabaseApplication extends Application {

    public void onCreate() {
        super.onCreate();
        Parse.enableLocalDatastore(this);
        Parse.initialize(this, "eEn9XUZ2kM3vtgSF9xsCOHiaTSfxVVtt7WvQlhRz", "aP0UwWsX34N2lrwSYKPZtPzVfazHu010DJ9DO1cp");
        ParseInstallation.getCurrentInstallation().saveInBackground();
    }
}
