package appwars.appwise.be.dj_database.activities;

import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.widget.Toolbar;
import android.transition.Transition;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.view.WindowManager;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.parse.ParseUser;

import org.w3c.dom.Text;

import appwars.appwise.be.dj_database.R;
import appwars.appwise.be.dj_database.fragments.ForgotPasswordFragment;
import appwars.appwise.be.dj_database.fragments.RegistrationFragment;
import appwars.appwise.be.dj_database.fragments.StartFragment;

public class LoginActivity extends BaseActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        Animation fade_in = AnimationUtils.loadAnimation(getApplicationContext(), android.R.anim.fade_in);
        fade_in.setDuration(2000);
        View view = findViewById(android.R.id.content);
        view.startAnimation(fade_in);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
            getWindow().getDecorView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN | View.SYSTEM_UI_FLAG_LAYOUT_HIDE_NAVIGATION);
        }

        getWindow().addFlags(WindowManager.LayoutParams.FLAG_HARDWARE_ACCELERATED);

        if (ParseUser.getCurrentUser() == null) {
            startStartFragment();
        }
    }

    public void startStartFragment() {
        Bundle bundle = new Bundle();

        if (currentApiVersion >= Build.VERSION_CODES.LOLLIPOP) {
            StartFragment fragment = StartFragment.newInstance(bundle);
            FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
            transaction.replace(R.id.fragment_container, fragment);
            transaction.addToBackStack(null);
            transaction.commit();
        } else {
            StartFragment fragment = StartFragment.newInstance(bundle);
            getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container, fragment).addToBackStack(null).commit();
        }
    }

    public void startForgotPasswordFragment() {
        Bundle bundle = new Bundle();

        if (currentApiVersion >= Build.VERSION_CODES.LOLLIPOP) {
            ForgotPasswordFragment fragment = ForgotPasswordFragment.newInstance(bundle);
            FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
            transaction.replace(R.id.fragment_container, fragment);
            transaction.addToBackStack(null);
            transaction.commit();
        } else {
            ForgotPasswordFragment fragment = ForgotPasswordFragment.newInstance(bundle);
            getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container, fragment).addToBackStack(null).commit();
        }
    }

    public void startRegistrationFragment() {
        Bundle bundle = new Bundle();

        if (currentApiVersion >= Build.VERSION_CODES.LOLLIPOP) {
            RegistrationFragment fragment = RegistrationFragment.newInstance(bundle);
            FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
            transaction.replace(R.id.fragment_container, fragment);
            transaction.addToBackStack(null);
            transaction.commit();
        } else {
            RegistrationFragment fragment = RegistrationFragment.newInstance(bundle);
            getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container, fragment).addToBackStack(null).commit();
        }
    }
}
