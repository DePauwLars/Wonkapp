package appwars.appwise.be.dj_database.fragments;

import android.os.Bundle;
import android.support.annotation.BinderThread;
import android.support.design.widget.TextInputLayout;
import android.text.TextUtils;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.parse.FindCallback;
import com.parse.ParseException;
import com.parse.ParseObject;
import com.parse.ParseQuery;
import com.parse.ParseUser;
import com.parse.RequestPasswordResetCallback;
import com.parse.SignUpCallback;

import java.util.List;

import appwars.appwise.be.dj_database.R;
import appwars.appwise.be.dj_database.activities.LoginActivity;
import butterknife.ButterKnife;
import butterknife.InjectView;


public class RegistrationFragment extends BaseFragment {

    @InjectView(R.id.edit_text_email)
    EditText editTextEmailAddress;

    @InjectView(R.id.edit_text_first_name)
    EditText editTextFirstName;

    @InjectView(R.id.edit_text_last_name)
    EditText editTextLastName;

    @InjectView(R.id.btn_create_account)
    Button btn_create_account;

    @InjectView(R.id.edit_text_username)
    EditText editTextUsername;

    @InjectView(R.id.edit_text_password)
    EditText editTextPassWord;

    @InjectView(R.id.edit_text_password_repeat)
    EditText editTextPassWordRepeat;

    @InjectView(R.id.til_email)
    TextInputLayout til_email;

    @InjectView(R.id.til_first_name)
    TextInputLayout til_firstName;


    @InjectView(R.id.til_last_name)
    TextInputLayout til_lastName;

    @InjectView(R.id.til_username)
    TextInputLayout til_userName;

    @InjectView(R.id.til_password)
    TextInputLayout til_passWord;

    @InjectView(R.id.til_password_repeat)
    TextInputLayout til_passWordRepeat;

    private View view;
    private String userName;
    private String passWord;
    private String passWordRepeat;
    private String firstName;
    private String lastName;
    private String emailAddress;

    public static RegistrationFragment newInstance(Bundle bundle) {
        RegistrationFragment fragment = new RegistrationFragment();
        if (bundle != null) {
            fragment.setArguments(bundle);
        }
        return fragment;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_registration, container, false);
        ButterKnife.inject(this, view);

        btn_create_account.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                emailAddress = til_email.getEditText().getText().toString();
                firstName = til_firstName.getEditText().getText().toString();
                lastName = til_lastName.getEditText().getText().toString();
                passWord = til_passWord.getEditText().getText().toString();
                passWordRepeat = til_passWordRepeat.getEditText().getText().toString();
                userName = til_userName.getEditText().getText().toString();

                setErrorsForInputFields();

                if (TextUtils.isEmpty(emailAddress) || TextUtils.isEmpty(firstName) || TextUtils.isEmpty(lastName) || TextUtils.isEmpty(userName) || TextUtils.isEmpty(passWord)) {
                    Toast.makeText(getActivity().getApplicationContext(), "please fill in every field", Toast.LENGTH_SHORT).show();
                } else if (passWord != passWordRepeat && passWord.length() < 6) {
                    Toast.makeText(getActivity().getApplicationContext(), "passwords must be identical and at least 6 characters", Toast.LENGTH_SHORT).show();
                } else {

                    ParseUser user = new ParseUser();
                    user.setEmail(emailAddress);
                    user.setPassword(passWord);
                    user.setUsername(userName);
                    user.put("firstName", firstName);
                    user.put("lastName", lastName);
                    user.signUpInBackground(new SignUpCallback() {
                        public void done(ParseException e) {
                            if (e == null) {
                                Toast.makeText(getActivity().getApplicationContext(), "Account succesfully created!", Toast.LENGTH_SHORT).show();
                                ((LoginActivity) getActivity()).startStartFragment();
                            } else {
                                ParseQuery<ParseObject> query = ParseQuery.getQuery("user");
                                query.whereEqualTo("email", emailAddress);
                                query.findInBackground(new FindCallback<ParseObject>() {
                                    public void done(List<ParseObject> emailAddressList, ParseException e) {
                                        if (e == null) {
                                            if (emailAddressList.contains(emailAddress)) {
                                                Toast.makeText(getActivity().getApplicationContext(), "This e-mailadress is already in use", Toast.LENGTH_LONG).show();
                                            }
                                        } else {
                                            e.printStackTrace();
                                        }
                                    }
                                });
                            }
                        }
                    });
                }
            }
        });
        return view;
    }

    public void setErrorsForInputFields() {
        til_email.setErrorEnabled(true);
        til_email.setError("e-mailaddress can't be empty");

        til_firstName.setErrorEnabled(true);
        til_firstName.setError("first name can't be empty");

        til_lastName.setErrorEnabled(true);
        til_lastName.setError("last name can't be empty");

        til_passWord.setErrorEnabled(true);
        til_passWord.setError("password must be at least 6 characters");

        til_passWordRepeat.setErrorEnabled(true);
        til_passWordRepeat.setError("repeat your password");

        til_userName.setErrorEnabled(true);
        til_userName.setError("username can't be empty");
    }
}
