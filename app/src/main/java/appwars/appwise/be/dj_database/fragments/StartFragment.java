package appwars.appwise.be.dj_database.fragments;

import android.app.Fragment;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.parse.ParseObject;

import appwars.appwise.be.dj_database.R;
import appwars.appwise.be.dj_database.activities.LoginActivity;

/**
 * Created by Lakkedelakke on 15/11/2015.
 */
public class StartFragment extends BaseFragment {
    private Button btn_login;
    private Button btn_register;
    private TextView text_forgot_password;
    public static final String TAG = StartFragment.class.getSimpleName();

    public static StartFragment newInstance(Bundle bundle) {
        StartFragment fragment = new StartFragment();
        if (bundle != null) {
            fragment.setArguments(bundle);
        }
        return fragment;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_start, container, false);
        btn_login = (Button) view.findViewById(R.id.btn_login);
        btn_register = (Button) view.findViewById(R.id.btn_register);
        text_forgot_password = (TextView) view.findViewById(R.id.text_forgot_password);

        btn_register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(getContext(), "You clicked registration, mothafucka", Toast.LENGTH_LONG).show();
                ((LoginActivity)getActivity()).startRegistrationFragment();

            }
        });
        text_forgot_password.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ((LoginActivity)getActivity()).startForgotPasswordFragment();
            }
        });

        return view;
    }
}
