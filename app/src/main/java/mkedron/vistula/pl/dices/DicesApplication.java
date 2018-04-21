package mkedron.vistula.pl.dices;

import android.app.Application;
import android.content.Context;
import android.content.Intent;

import mkedron.vistula.pl.dices.view.MainMenu;

/**
 * Created by mkked on 07.04.2018.
 */

public class DicesApplication extends Application {

    private static Application app;

    @Override
    public void onCreate() {
        super.onCreate();
        app = this;
        Intent intent = new Intent(this, MainMenu.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        startActivity(intent);
    }

    public static Application getApplication () {
        return app;
    }

    public static Context getContext() {
        return app.getApplicationContext();
    }
}
