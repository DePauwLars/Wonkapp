package appwars.appwise.be.dj_database.fragments;

import android.content.Context;
import android.graphics.Color;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.nispok.snackbar.Snackbar;
import com.nispok.snackbar.SnackbarManager;
import com.parse.FindCallback;
import com.parse.ParseException;
import com.parse.ParseObject;
import com.parse.ParseQuery;
import com.parse.ParseUser;
import com.parse.RequestPasswordResetCallback;

import java.util.List;

import appwars.appwise.be.dj_database.R;
import appwars.appwise.be.dj_database.activities.LoginActivity;
import butterknife.ButterKnife;
import butterknife.InjectView;

/**
 * Created by Lakkedelakke on 16/11/2015.
 */
public class ForgotPasswordFragment extends BaseFragment {
    @InjectView(R.id.edit_text_email_reset) EditText edit_text_email;
    @InjectView(R.id.btn_reset_password)Button btn_reset_password;

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
        ButterKnife.inject(this, view);

        btn_reset_password.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                emailAddress = edit_text_email.getText().toString();

                if (emailAddress.length() == 0) {
                    SnackbarManager.show(com.nispok.snackbar.Snackbar.with(v.getContext()).position(Snackbar.SnackbarPosition.TOP)
                            .text("Please fill in a valid e-mailaddress").color(Color.rgb(198, 28, 28)));
                } else if (!emailAddress.contains("@")) {
                    SnackbarManager.show(com.nispok.snackbar.Snackbar.with(v.getContext()).position(Snackbar.SnackbarPosition.TOP)
                            .text("Please fill in a valid e-mailaddress").color(Color.rgb(198, 28, 28)));
                } else {
                    resetPassword(emailAddress);
                }
            }
        });
        return view;
    }

    public void resetPassword(final String userEmail) {

        ParseQuery<ParseUser> query = ParseUser.getQuery();
        query.whereEqualTo("email", emailAddress);
        query.findInBackground(new FindCallback<ParseUser>() {
            @Override
            public void done(List<ParseUser> list, ParseException e) {
                if (e == null) {
                    ParseUser.requestPasswordResetInBackground(emailAddress, new RequestPasswordResetCallback() {
                        @Override
                        public void done(ParseException e) {
                            if (e == null) {
                                Toast.makeText(getActivity().getApplicationContext(), "An e-mail has been sent to " + emailAddress + " in which you can change your password.", Toast.LENGTH_LONG).show();

                                InputMethodManager imm = (InputMethodManager) getActivity().getSystemService(Context.INPUT_METHOD_SERVICE);
                                imm.toggleSoftInput(InputMethodManager.SHOW_FORCED, 0);

                                ((LoginActivity) getActivity()).startStartFragment();
                            } else {
                                Toast.makeText(getActivity().getApplicationContext(), "Something went wrong, please try again later.", Toast.LENGTH_LONG).show();
                                Log.e("error resetting password for + " + userEmail, "Error: " + e.getMessage());
                            }
                        }
                    });
                }
            }
        });
    }
}
