package appwars.appwise.be.dj_database.fragments;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.parse.FindCallback;
import com.parse.ParseException;
import com.parse.ParseObject;
import com.parse.ParseQuery;
import com.parse.ParseUser;
import com.parse.RequestPasswordResetCallback;

import java.util.List;

import appwars.appwise.be.dj_database.R;

/**
 * Created by Lakkedelakke on 16/11/2015.
 */
public class ForgotPasswordFragment extends BaseFragment {
    private Button btn_reset_password;
    private EditText edit_text_email;
    private String emailAddress;

    public static ForgotPasswordFragment newInstance(Bundle bundle) {
        ForgotPasswordFragment fragment = new ForgotPasswordFragment();
        if (bundle != null) {
            fragment.setArguments(bundle);
        }
        return fragment;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_forgot_password, container, false);
        edit_text_email = (EditText) view.findViewById(R.id.edit_text_email_reset);
        btn_reset_password = (Button) view.findViewById(R.id.btn_reset_password);

        emailAddress = edit_text_email.getText().toString();
        btn_reset_password.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                resetPassword(emailAddress);
            }
        });
        return view;
    }

    public void resetPassword(final String emailAddress) {

        ParseQuery<ParseObject> query = ParseQuery.getQuery("emailaddress");
        query.findInBackground(new FindCallback<ParseObject>() {
            public void done(List<ParseObject> emailAddressList, ParseException e) {
                if (e == null) {
                    if (emailAddressList.contains(emailAddress)) {
                        ParseUser.requestPasswordResetInBackground(emailAddress, new RequestPasswordResetCallback() {
                            @Override
                            public void done(ParseException e) {
                                Toast.makeText(getActivity().getApplicationContext(), "An e-mail has been sent in which you can change your password.", Toast.LENGTH_LONG).show();
                            }
                        });
                    } else {
                        Toast.makeText(getActivity().getApplicationContext(), "It looks like this emailaddress isn't familiar to us. Are you sure it's correct?", Toast.LENGTH_LONG).show();

                    }
                } else {
                    Log.d("error resetting password for + " + emailAddress, "Error: " + e.getMessage());
                }
            }
        });
    }
}
