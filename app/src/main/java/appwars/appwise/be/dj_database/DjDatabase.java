package appwars.appwise.be.dj_database;

import android.app.Application;
import android.content.Context;

/**
 * Created by Lakkedelakke on 9/11/2015.
 */
public class DjDatabase extends Application {
    public Context context;

    public Context getContext() {
        return context;
    }

    public void setContext(Context context) {
        this.context = context;
    }

    @Override
    public void onCreate() {
        super.onCreate();
        this.context = context;

    }
}
