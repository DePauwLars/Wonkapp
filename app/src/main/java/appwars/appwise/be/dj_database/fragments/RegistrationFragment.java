package appwars.appwise.be.dj_database.fragments;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;

import appwars.appwise.be.dj_database.R;

/**
 * Created by Lakkedelakke on 15/11/2015.
 */
public class RegistrationFragment extends BaseFragment {
    private EditText editTextUsername;
    private EditText editTextPassWord;
    private EditText editTextFullName;
    private EditText editTextEmailAddress;

    public static RegistrationFragment newInstance(Bundle bundle) {
        RegistrationFragment fragment = new RegistrationFragment();
        if (bundle != null) {
            fragment.setArguments(bundle);
        }
        return fragment;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_registration, container, false);
        return view;
    }
}
