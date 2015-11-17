package appwars.appwise.be.dj_database.fragments;

import android.os.Bundle;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.parse.ParseException;
import com.parse.ParseUser;
import com.parse.SignUpCallback;

import appwars.appwise.be.dj_database.R;

/**
 * Created by Lakkedelakke on 15/11/2015.
 */
public class RegistrationFragment extends BaseFragment {
    private View view;
    private EditText editTextUsername;
    private EditText editTextPassWord;
    private EditText editTextPassWordRepeat;
    private EditText editTextFirstName;
    private EditText editTextLastName;
    private EditText editTextEmailAddress;
    private Button btn_create_account;
    private String userName;
    private String passWord;
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
        findViewsById();
        editTextEmailAddress.setError("e-mailaddress can't be empty");
        editTextFirstName.setError("first name can't be empty");
        editTextLastName.setError("last name can't be empty");
        editTextPassWord.setError("password must be at least 6 characters");
        editTextPassWordRepeat.setError("repeat your password");
        editTextUsername.setError("username can't be empty");

        emailAddress = editTextEmailAddress.getText().toString();
        firstName = editTextFirstName.getText().toString();
        lastName = editTextLastName.getText().toString();
        passWord = editTextPassWord.getText().toString();
        userName = editTextUsername.getText().toString();

        btn_create_account.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (TextUtils.isEmpty(emailAddress) && TextUtils.isEmpty(firstName) && TextUtils.isEmpty(lastName) && TextUtils.isEmpty(userName) && TextUtils.isEmpty(passWord)) {
                    Toast.makeText(getActivity().getApplicationContext(), "Please fill in the fields", Toast.LENGTH_SHORT).show();
                } else if (passWord != editTextPassWordRepeat.getText().toString() && passWord.length() < 6) {
                    Toast.makeText(getActivity().getApplicationContext(), "passwords should be identical and at least 6 characters", Toast.LENGTH_SHORT).show();
                } else if (!TextUtils.isEmpty(emailAddress) && !TextUtils.isEmpty(firstName) && !TextUtils.isEmpty(lastName) && !TextUtils.isEmpty(userName) && !TextUtils.isEmpty(passWord)) {
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
                            } else {
                                Toast.makeText(getActivity().getApplicationContext(), "Something went wrong. Please try again", Toast.LENGTH_SHORT).show();
                            }
                        }
                    });
                }
            }
        });

        return view;
    }

    public void findViewsById() {
        editTextEmailAddress = (EditText) view.findViewById(R.id.edit_text_email);
        editTextFirstName = (EditText) view.findViewById(R.id.edit_text_first_name);
        editTextLastName = (EditText) view.findViewById(R.id.edit_text_last_name);
        btn_create_account = (Button) view.findViewById(R.id.btn_create_account);
        editTextUsername = (EditText) view.findViewById(R.id.edit_text_username);
        editTextPassWord = (EditText) view.findViewById(R.id.edit_text_password);
        editTextPassWordRepeat = (EditText) view.findViewById(R.id.edit_text_password_repeat);
    }
}
