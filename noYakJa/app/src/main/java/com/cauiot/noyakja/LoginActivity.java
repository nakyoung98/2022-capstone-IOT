package com.cauiot.noyakja;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

import androidx.annotation.NonNull;

import com.cauiot.noyakja.DB.DBUser;
import com.cauiot.noyakja.DB.UserInfo;
import com.cauiot.noyakja.databinding.ActivityLoginBinding;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.FirebaseException;
import com.google.firebase.FirebaseTooManyRequestsException;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseAuthInvalidCredentialsException;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.auth.PhoneAuthCredential;
import com.google.firebase.auth.PhoneAuthOptions;
import com.google.firebase.auth.PhoneAuthProvider;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.firestore.auth.User;

import java.util.HashMap;
import java.util.concurrent.TimeUnit;

public class LoginActivity extends Activity {

    private static final String TAG = "LoginActivity";

    public FirebaseUser user = null;

    private ActivityLoginBinding activityLoginBinding;

    // [START declare_auth]
    private FirebaseAuth mAuth;
    // [END declare_auth]

    private String mVerificationId;
    private PhoneAuthProvider.ForceResendingToken mResendToken;
    private PhoneAuthProvider.OnVerificationStateChangedCallbacks mCallbacks;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        activityLoginBinding = ActivityLoginBinding.inflate(getLayoutInflater());
        View view = activityLoginBinding.getRoot();
        setContentView(view);

        // [START initialize_auth]
        // Initialize Firebase Auth
        mAuth = FirebaseAuth.getInstance();
        // [END initialize_auth]

        //auth 언어 변경
        mAuth.setLanguageCode("kr");
        // Initialize phone auth callbacks
        // [START phone_auth_callbacks]
        mCallbacks = new PhoneAuthProvider.OnVerificationStateChangedCallbacks() {

            @Override
            public void onVerificationCompleted(PhoneAuthCredential credential) {
                // This callback will be invoked in two situations:
                // 1 - Instant verification. In some cases the phone number can be instantly
                //     verified without needing to send or enter a verification code.
                // 2 - Auto-retrieval. On some devices Google Play services can automatically
                //     detect the incoming verification SMS and perform verification without
                //     user action.
                Log.d(TAG, "onVerificationCompleted:" + credential);

                signInWithPhoneAuthCredential(credential);
            }

            @Override
            public void onVerificationFailed(FirebaseException e) {
                // This callback is invoked in an invalid request for verification is made,
                // for instance if the the phone number format is not valid.
                Log.w(TAG, "onVerificationFailed", e);

                if (e instanceof FirebaseAuthInvalidCredentialsException) {
                    // Invalid request
                } else if (e instanceof FirebaseTooManyRequestsException) {
                    // The SMS quota for the project has been exceeded
                }

                // Show a message and update the UI
            }

            @Override
            public void onCodeSent(@NonNull String verificationId,
                                   @NonNull PhoneAuthProvider.ForceResendingToken token) {
                // The SMS verification code has been sent to the provided phone number, we
                // now need to ask the user to enter the code and then construct a credential
                // by combining the code with a verification ID.
                Log.d(TAG, "onCodeSent:" + verificationId);

                // Save verification ID and resending token so we can use them later
                mVerificationId = verificationId;
                Log.i(TAG, mVerificationId);
                mResendToken = token;
            }
        };
        // [END phone_auth_callbacks]
    }

    // [START on_start_check_user]
    @Override
    public void onStart() {
        super.onStart();
        // Check if user is signed in (non-null) and update UI accordingly.
        user = mAuth.getCurrentUser();
        //Log.i(TAG, currentUser.getUid());
        if(user != null){updateUI(user);}
    }
    // [END on_start_check_user]


    private void startPhoneNumberVerification(String phoneNumber) {
        // [START start_phone_auth]
        PhoneAuthOptions options =
                PhoneAuthOptions.newBuilder(mAuth)
                        .setPhoneNumber(phoneNumber)       // Phone number to verify
                        .setTimeout(120L, TimeUnit.SECONDS) // Timeout and unit
                        .setActivity(this)                 // Activity (for callback binding)
                        .setCallbacks(mCallbacks)          // OnVerificationStateChangedCallbacks
                        .build();
        PhoneAuthProvider.verifyPhoneNumber(options);
        // [END start_phone_auth]
    }

    private PhoneAuthCredential verifyPhoneNumberWithCode(String verificationId, String code) {
        // [START verify_with_code]
        PhoneAuthCredential credential = PhoneAuthProvider.getCredential(verificationId, code);
        // [END verify_with_code]
        return credential;
    }

    // [START resend_verification]
    private void resendVerificationCode(String phoneNumber,
                                        PhoneAuthProvider.ForceResendingToken token) {
        PhoneAuthOptions options =
                PhoneAuthOptions.newBuilder(mAuth)
                        .setPhoneNumber(phoneNumber)       // Phone number to verify
                        .setTimeout(120L, TimeUnit.SECONDS) // Timeout and unit
                        .setActivity(this)                 // Activity (for callback binding)
                        .setCallbacks(mCallbacks)          // OnVerificationStateChangedCallbacks
                        .setForceResendingToken(token)     // ForceResendingToken from callbacks
                        .build();
        PhoneAuthProvider.verifyPhoneNumber(options);
    }
    // [END resend_verification]

    // [START sign_in_with_phone]
    private void signInWithPhoneAuthCredential(PhoneAuthCredential credential) {
        mAuth.signInWithCredential(credential)
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {
                            // Sign in success, update UI with the signed-in user's information
                            Log.d(TAG, "signInWithCredential:success");

                            FirebaseUser user = task.getResult().getUser();

                            // Update UI
                            updateUI(user);
                        } else { //가입실패
                            // Sign in failed, display a message and update the UI
                            Log.w(TAG, "signInWithCredential:failure", task.getException());
                            if (task.getException() instanceof FirebaseAuthInvalidCredentialsException) {
                                // The verification code entered was invalid
                            }
                        }
                    }
                });
    }
    // [END sign_in_with_phone]

    private void updateUI(FirebaseUser user) {

        findUser(user);

        //todo 전화번호 인증만하고 앱을 나간 사람에게는, 다음 화면을 회원정보를 입력하게해야함.
    }

    private boolean checkPhoneNumber(String phoneNumber) {
        if(phoneNumber.isEmpty()) return false;
        if(phoneNumber.length() < 10) return false;

        return true;
    }

    public void auth(View view){

        String phoneNumber = activityLoginBinding.phoneNumberInsert.getText().toString();
        phoneNumber = splitPhone(phoneNumber);

        if(checkPhoneNumber(phoneNumber)){
            phoneNumber = "+82" + phoneNumber.substring(1);
            Log.i(TAG,phoneNumber);

            startPhoneNumberVerification(phoneNumber);

            goneText();
            activityLoginBinding.authMessageText.setVisibility(View.VISIBLE);
        }else{
            goneText();
            activityLoginBinding.invalidAuthMessageText.setVisibility(View.VISIBLE);
        }
    }

    private void goneText(){
        activityLoginBinding.authMessageText.setVisibility(View.GONE);
        activityLoginBinding.invalidAuthMessageText.setVisibility(View.GONE);
    }


    private String splitPhone(String phoneNumber){
        String[] splitPhoneNumbers = phoneNumber.split("-");
        String tempPhoneNumber = "";

        for (String splitPhoneNumber:splitPhoneNumbers) {
            tempPhoneNumber += splitPhoneNumber;
        }

        return tempPhoneNumber;
    }

    public void checkAuth(View view){
        String code = activityLoginBinding.authNumberInsert.getText().toString();
        Log.i(TAG, code);

        signInWithPhoneAuthCredential(verifyPhoneNumberWithCode(mVerificationId, code));
    }


    private void findUser(FirebaseUser user){    //getData


        DBUser DBUser = new DBUser();

        Log.i(TAG,"uid: "+user.getUid());


        DBUser.getReference().child(user.getUid()).get().addOnCompleteListener(new OnCompleteListener<DataSnapshot>() {
            @Override
            public void onComplete(@NonNull Task<DataSnapshot> task) {
                if(!task.isSuccessful()){
                    Log.e(TAG, "DB 수집 오류", task.getException());
                }else{
                    Log.i(TAG, "DB 수집 성공");
                    HashMap<String,String> hashMap = (HashMap) task.getResult().getValue();

                    if(hashMap != null) {
                        String name = hashMap.get("name");
                        String phone = hashMap.get("phone");
                        String uid = hashMap.get("uid");

                        UserInfo.setName(name);
                        UserInfo.setPhone(phone);
                        UserInfo.setUid(uid);
                        Log.i(TAG, UserInfo.toString_());

                        Intent mainIntent = new Intent(LoginActivity.this, MainActivity.class);

                        Log.i(TAG,"LoginActivity -> MainActivity");

                        startActivity(mainIntent);
                    }else{ //첫로그인 (첫가입)
                        Intent userInfoIntent = new Intent(LoginActivity.this, UserInfoActivity.class);

                        //userInfo 담아 보냄
                        UserInfo.setUid(user.getUid());
                        UserInfo.setPhone(user.getPhoneNumber());


                        Log.i(TAG,"LoginActivity -> UserInfoActivity");
                        startActivity(userInfoIntent);
                        finish();
                    }
                }


            }
        });

    }

}