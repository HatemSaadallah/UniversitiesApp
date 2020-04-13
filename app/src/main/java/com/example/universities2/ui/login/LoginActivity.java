package com.example.universities2.ui.login;

import android.app.Activity;

import androidx.annotation.NonNull;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;

import android.content.Intent;
import android.content.res.Resources;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.annotation.StringRes;
import androidx.appcompat.app.AppCompatActivity;

import android.provider.BaseColumns;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;
import android.view.inputmethod.EditorInfo;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.example.universities2.R;
import com.example.universities2.Register;
import com.example.universities2.mainPage;
//import com.example.universities2.FeedReaderContract;
//import com.google.android.gms.tasks.OnFailureListener;
//import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdView;
import com.google.android.gms.ads.MobileAds;
import com.google.android.gms.ads.initialization.InitializationStatus;
import com.google.android.gms.ads.initialization.OnInitializationCompleteListener;
import com.google.firebase.database.DatabaseReference;
//import com.google.firebase.database.FirebaseDatabase;
//import com.google.firebase.database.IgnoreExtraProperties;
//
//import java.util.ArrayList;
//import java.util.HashMap;
//import java.util.List;
//import java.util.Map;





public class LoginActivity extends AppCompatActivity {
//    private DatabaseReference mDatabase;

    private LoginViewModel loginViewModel;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
         AdView mAdView;
        loginViewModel = ViewModelProviders.of(this, new LoginViewModelFactory())
                .get(LoginViewModel.class);


        MobileAds.initialize(this, new OnInitializationCompleteListener() {
            public void onInitializationComplete(InitializationStatus initializationStatus) {
            }
        });
        mAdView = findViewById(R.id.adView);
        AdRequest adRequest = new AdRequest.Builder().build();
        mAdView.loadAd(adRequest);


        final EditText usernameEditText = findViewById(R.id.username);
        final EditText passwordEditText = findViewById(R.id.password);
        final Button loginButton = findViewById(R.id.login);
        final Button registerButton = findViewById(R.id.register);
        registerButton.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Intent i = new Intent(getBaseContext(), Register.class);
                startActivity(i);
                finish();
            }
        });
        final ProgressBar loadingProgressBar = findViewById(R.id.loading);



        loginViewModel.getLoginFormState().observe(this, new Observer<LoginFormState>() {
            @Override
            public void onChanged(@Nullable LoginFormState loginFormState) {
                if (loginFormState == null) {
                    return;
                }
                loginButton.setEnabled(loginFormState.isDataValid());
                if (loginFormState.getUsernameError() != null) {
                    usernameEditText.setError(getString(loginFormState.getUsernameError()));
                }
                if (loginFormState.getPasswordError() != null) {
                    passwordEditText.setError(getString(loginFormState.getPasswordError()));
                }
            }
        });

        loginViewModel.getLoginResult().observe(this, new Observer<LoginResult>() {
            @Override
            public void onChanged(@Nullable LoginResult loginResult) {
                if (loginResult == null) {
                    return;
                }
                loadingProgressBar.setVisibility(View.GONE);
                if (loginResult.getError() != null) {
                    showLoginFailed(loginResult.getError());
                }
                if (loginResult.getSuccess() != null) {
                    updateUiWithUser(loginResult.getSuccess());
                }
                setResult(Activity.RESULT_OK);

                //Complete and destroy login activity once successful
                finish();
            }
        });

        TextWatcher afterTextChangedListener = new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
                // ignore
            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                // ignore
            }

            @Override
            public void afterTextChanged(Editable s) {
                loginViewModel.loginDataChanged(usernameEditText.getText().toString(),
                        passwordEditText.getText().toString());
            }
        };
        usernameEditText.addTextChangedListener(afterTextChangedListener);
        passwordEditText.addTextChangedListener(afterTextChangedListener);
        passwordEditText.setOnEditorActionListener(new TextView.OnEditorActionListener() {

            @Override
            public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {
                if (actionId == EditorInfo.IME_ACTION_DONE) {
                    loginViewModel.login(usernameEditText.getText().toString(),
                            passwordEditText.getText().toString());
                }
                return false;
            }
        });

        loginButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                loadingProgressBar.setVisibility(View.VISIBLE);
                loginViewModel.login(usernameEditText.getText().toString(),
                        passwordEditText.getText().toString());
            }
        });
    }

    private void updateUiWithUser(LoggedInUserView model) {
//        String welcome = getString(R.string.welcome) + model.getDisplayName();

//        FeedReaderContract.FeedReaderDbHelper dbHelper = new FeedReaderContract.FeedReaderDbHelper(this);
//
//        SQLiteDatabase db = dbHelper.getReadableDatabase();

//        String[] projection = {
//                BaseColumns._ID,
//                FeedReaderContract.FeedEntry.COLUMN_NAME_USERNAME,
//                FeedReaderContract.FeedEntry.COLUMN_NAME_PASSWORD
//        };

//        String selection = FeedReaderContract.FeedEntry.COLUMN_NAME_USERNAME + " =? AND " + FeedReaderContract.FeedEntry.COLUMN_NAME_PASSWORD + " = ?";

//        EditText pwd = findViewById(R.id.password);
//        Log.v(usrname.getText().toString(), pwd.getText().toString());
//        if(usrname.getText().toString().equals("admin") && pwd.getText().toString().equals("admin123")){
        Intent i=new Intent(getBaseContext(), mainPage.class);
        startActivity(i);
        Toast.makeText(getApplicationContext(),"تم تسجيل الدخول بنجاح!", Toast.LENGTH_LONG).show();
//        } else {
//            Toast.makeText(getApplicationContext(),"رجاء أدخل اسم المستخدم وكلمة المرور الصحيحين", Toast.LENGTH_LONG).show();
//            Intent i = new Intent(getBaseContext(), LoginActivity.class);
//            startActivity(i);
//        }
//        String[] selectionArgs = { usrname.getText().toString(), pwd.getText().toString()};
//
//        Cursor cursor = db.query(
//                FeedReaderContract.FeedEntry.TABLE_NAME,   // The table to query
//                null,             // The array of columns to return (pass null to get all)
//                selection,              // The columns for the WHERE clause
//                selectionArgs,          // The values for the WHERE clause
//                null,                   // don't group the rows
//                null,                   // don't filter by row groups
//                null               // The sort order
//        );
//        final List userData = new ArrayList<>();
//        while(cursor.moveToNext()) {
//            String itemName = cursor.getString(
//                    cursor.getColumnIndexOrThrow(FeedReaderContract.FeedEntry.COLUMN_NAME_USERNAME));
//            userData.add(itemName);
//        }

    }

    private void showLoginFailed(@StringRes Integer errorString) {
        Toast.makeText(getApplicationContext(), errorString, Toast.LENGTH_SHORT).show();
    }
}
