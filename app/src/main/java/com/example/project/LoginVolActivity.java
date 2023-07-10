package com.example.project;

import static android.content.ContentValues.TAG;
import androidx.activity.result.ActivityResultLauncher;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;


import com.example.project.Utilities.SignalGenerator;
import com.firebase.ui.auth.AuthUI;
import com.firebase.ui.auth.FirebaseAuthUIActivityResultContract;
import com.firebase.ui.auth.IdpResponse;
import com.firebase.ui.auth.data.model.FirebaseAuthUIAuthenticationResult;
import com.google.firebase.FirebaseException;
import com.google.firebase.FirebaseTooManyRequestsException;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseAuthInvalidCredentialsException;
import com.google.firebase.auth.FirebaseAuthMissingActivityForRecaptchaException;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.auth.PhoneAuthCredential;
import com.google.firebase.auth.PhoneAuthOptions;
import com.google.firebase.auth.PhoneAuthProvider;

import java.util.Arrays;
import java.util.List;
import java.util.concurrent.TimeUnit;

public class LoginVolActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login_vol);

          FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
    //    if (user == null)
            login();
       // else
         //   moveToActivity();
    }

    private void login() {
        List<AuthUI.IdpConfig> providers = Arrays.asList(
                new AuthUI.IdpConfig.EmailBuilder().build(),
                new AuthUI.IdpConfig.PhoneBuilder().build());


        Intent signInIntent = AuthUI.getInstance()
                .createSignInIntentBuilder()
                .setAvailableProviders(providers)
                .build();
        signInLauncher.launch(signInIntent);

    }


    private final ActivityResultLauncher<Intent> signInLauncher = registerForActivityResult(
            new FirebaseAuthUIActivityResultContract(),
            this::onSignInResult
    );


    private void onSignInResult(FirebaseAuthUIAuthenticationResult result) {
        IdpResponse response = result.getIdpResponse();
        if (result.getResultCode() == RESULT_OK) {
            if (response != null && response.getPhoneNumber() != null)
                verifyPhoneNumber(response.getPhoneNumber());

            else
                moveToActivity();

        } else
            SignalGenerator.getInstance().toast(result.toString(), Toast.LENGTH_LONG);

    }

    private void moveToActivity() {
        Intent intent = new Intent(this, VolActivity.class);
        startActivity(intent);
        finish();
    }



    private void verifyPhoneNumber(String phoneNumber) {
        PhoneAuthOptions options =
                PhoneAuthOptions.newBuilder(FirebaseAuth.getInstance())
                        .setPhoneNumber(phoneNumber)
                        .setTimeout(60L, TimeUnit.SECONDS)
                        .setActivity(this)
                        .setCallbacks(mCallbacks)
                        .build();
        PhoneAuthProvider.verifyPhoneNumber(options);
    }

    private PhoneAuthProvider.OnVerificationStateChangedCallbacks mCallbacks =
            new PhoneAuthProvider.OnVerificationStateChangedCallbacks() {

                @Override
                public void onVerificationCompleted(@NonNull PhoneAuthCredential credential) {
                    signInWithPhoneAuthCredential(credential);
                }

                @Override
                public void onVerificationFailed(@NonNull FirebaseException e) {
                    Log.d(TAG, "onVerificationFailed", e);
                    if (e instanceof FirebaseAuthInvalidCredentialsException)
                        SignalGenerator.getInstance().toast(e.getMessage(), Toast.LENGTH_LONG);
                     else if (e instanceof FirebaseTooManyRequestsException)
                        SignalGenerator.getInstance().toast(e.getMessage(), Toast.LENGTH_LONG);
                     else if (e instanceof FirebaseAuthMissingActivityForRecaptchaException)
                        SignalGenerator.getInstance().toast(e.getMessage(), Toast.LENGTH_LONG);

                }

                @Override
                public void onCodeSent(@NonNull String verificationId,
                                       @NonNull PhoneAuthProvider.ForceResendingToken token) {
                    moveToActivity();
                }
            };

    private void signInWithPhoneAuthCredential(PhoneAuthCredential credential) {
        FirebaseAuth.getInstance().signInWithCredential(credential)
                .addOnCompleteListener(this, task -> {
                    if (task.isSuccessful())
                        moveToActivity();
                     else
                        if (task.getException() instanceof FirebaseAuthInvalidCredentialsException)
                            SignalGenerator.getInstance().toast(SignalGenerator.INVALID_VERIFICATION , Toast.LENGTH_LONG);

                });
    }
}