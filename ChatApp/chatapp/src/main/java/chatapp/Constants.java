package chatapp;

import java.awt.Color;

public class Constants 
{
    // #22223B;
    // #202035;
    public static final String CURRENT_PATH_STRING = System.getProperty("user.dir") + "/src/main";
    public static final Color PRIMARY_COLOR = Color.decode("#19192b");
    public static final Color PRIMARY2_COLOR = Color.decode("#202035");
    public static final Color SECOND_COLOR = Color.decode("#4A4E69");
    public static final Color TEXT_COLOR = Color.decode("#C9ADA7");
    public static final Color RANDOM_COLOR = Color.decode("#F2E9E4");
    public static final Color ERROR_COLOR = Color.decode("#EE4E4E");

    public static final int SUCCESS_CONNECTION = 0;
    public static final int SUCCESS_LOGIN = 1;
    public static final int SUCCESS_SIGNUP = 2;
    public static final int SUCCESS_VERIFY = 3;


    public static final int ERROR_NETWORK = 400;

    public static final int ERROR_SIGNUP_EMAIL_ALREADY_EXISTS = 401;
    public static final int ERROR_SIGNUP_NOT_GMAIL = 405;
    public static final int ERROR_SIGNUP_PASSWORD_SIZE = 406;
    
    public static final int ERROR_LOGIN_EMAIL_PASSWORD = 402;
    public static final int ERROR_LOGIN_WRONG_FORMAT = 407;
    
    
    
    public static final int ERROR_VERIFY_CODE = 403;
    
    public static final int ERROR_WRONG_FORMAT = 404;
    public static final int ERROR_SOMETHING_WENT_WRONG = 499;

}
