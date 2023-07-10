package com.example.project.Utilities;



import android.content.Context;
import android.widget.Toast;



public class SignalGenerator {

    private static SignalGenerator instance = null;
    private Context context;


    public static final String FUNC_SUCCEEDED = "DONE";
    public static final String FUNC_NOT_SUCCEEDED = "Task was not successful";

    public static final String FILL_TUB = "fill the tab";
    public static final String EXIST = "Already exist";

    public static final String ID_NOT_EXIST = "ID don't exist";

    public static final String TEN_DIGITS = "10 digits";
    public static final String NINE_DIGIT = "9 digits";
    public static final String INVALID_VERIFICATION = "The verification code entered was invalid";



    public SignalGenerator(Context context) {
        this.context = context;
    }



    public static void init(Context context) {
        if (instance == null) {
            instance = new SignalGenerator(context);
        }
    }

    public static SignalGenerator getInstance() {
        return instance;
    }

    public void toast(String text, int lengthShort) {

        Toast
                .makeText(context, text, text.length())
                .show();
    }

}
