package appwars.appwise.be.dj_database.activities;

import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.view.View;
import android.view.WindowManager;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;

import com.parse.ParseUser;

import appwars.appwise.be.dj_database.R;
import appwars.appwise.be.dj_database.fragments.ForgotPasswordFragment;
import appwars.appwise.be.dj_database.fragments.RegistrationFragment;
import appwars.appwise.be.dj_database.fragments.StartFragment;

public class LoginActivity extends BaseActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        View view = findViewById(android.R.id.content);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
            getWindow().getDecorView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN | View.SYSTEM_UI_FLAG_LAYOUT_HIDE_NAVIGATION);
        }

        getWindow().addFlags(WindowManager.LayoutParams.FLAG_HARDWARE_ACCELERATED);

        if (ParseUser.getCurrentUser() == null) {
            startStartFragment();
        } else {
            goToMainActivity();
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

    @Override
    public void onBackPressed() {
        Fragment f = getSupportFragmentManager().findFragmentById(R.id.fragment_container);
        if (f instanceof StartFragment) {
            this.finish();
        } else {
            super.onBackPressed();
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

    public void goToMainActivity() {
        Intent goToMainActivity = new Intent(getApplicationContext(), MainActivity.class);
        startActivity(goToMainActivity);
    }
}
