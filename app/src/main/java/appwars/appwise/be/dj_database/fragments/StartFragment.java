package appwars.appwise.be.dj_database.fragments;

import android.app.Fragment;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.os.Bundle;
import android.support.design.widget.Snackbar;
import android.support.design.widget.TextInputLayout;
import android.text.TextUtils;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.TextureView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.nispok.snackbar.SnackbarManager;
import com.parse.LogInCallback;
import com.parse.ParseObject;
import com.parse.ParseUser;

import org.w3c.dom.Text;

import java.text.ParseException;

import appwars.appwise.be.dj_database.R;
import appwars.appwise.be.dj_database.activities.LoginActivity;
import appwars.appwise.be.dj_database.activities.MainActivity;
import butterknife.ButterKnife;
import butterknife.InjectView;

/**
 * Created by Lakkedelakke on 15/11/2015.
 */
public class StartFragment extends BaseFragment {
    @InjectView(R.id.btn_login)
    TextView btn_login;

    @InjectView(R.id.text_forgot_password)
    TextView text_forgot_password;

    @InjectView(R.id.btn_register)
    TextView btn_register;

    @InjectView(R.id.til_login_username)
    TextInputLayout til_login_username;

    @InjectView(R.id.til_login_password)
    TextInputLayout til_login_password;

    @InjectView(R.id.edit_text_login_username)
    EditText edit_text_login_username;

    private String username;
    private String password;

    public static final String TAG = StartFragment.class.getSimpleName();

    public static StartFragment newInstance(Bundle bundle) {
        StartFragment fragment = new StartFragment();
        if (bundle != null) {
            fragment.setArguments(bundle);
        }
        return fragment;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, final ViewGroup container, Bundle savedInstanceState) {
        final View view = inflater.inflate(R.layout.fragment_start, container, false);
        ButterKnife.inject(this, view);

        SharedPreferences sharedpreferences = getActivity().getSharedPreferences("", Context.MODE_PRIVATE);
        final SharedPreferences.Editor editor = sharedpreferences.edit();
        username = sharedpreferences.getString("username", "");

        if (username != "") {
            edit_text_login_username.setText(username, TextView.BufferType.EDITABLE);
        }

        btn_register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ((LoginActivity) getActivity()).startRegistrationFragment();
            }
        });

        btn_login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(final View v) {
                username = til_login_username.getEditText().getText().toString();
                password = til_login_password.getEditText().getText().toString();

                ParseUser.logInInBackground(username, password, new LogInCallback() {
                    @Override
                    public void done(ParseUser parseUser, com.parse.ParseException e) {
                        if (parseUser != null) {
                            editor.putString("username", username).apply();
                            editor.putString("password", password).apply();
                            SnackbarManager.show(com.nispok.snackbar.Snackbar.with(v.getContext()).text("successfully logged in").color(Color.argb(70, 50, 70, 90))
                                    .position(com.nispok.snackbar.Snackbar.SnackbarPosition.TOP).showAnimation(true));

                        } else {
                            e.printStackTrace();
                            Log.e("error logging in", e.toString());
                            Toast.makeText(getContext(), "There seems to be an error while logging in", Toast.LENGTH_LONG).show();

                        }
                    }
                });

                SnackbarManager.show(com.nispok.snackbar.Snackbar.with(v.getContext()).text("No log in yet :)").color(Color.argb(70, 50, 70, 90))
                        .position(com.nispok.snackbar.Snackbar.SnackbarPosition.TOP).showAnimation(true));
            }
        });

        text_forgot_password.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ((LoginActivity) getActivity()).startForgotPasswordFragment();
            }
        });

        return view;
    }

    public String getSimpleFragmentName() {
        return this.getClass().getName();
    }
}
