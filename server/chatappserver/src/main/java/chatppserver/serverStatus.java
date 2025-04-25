package chatppserver;

public class serverStatus 
{

    public static final int SUCCESS_CONNECTION = 0;
    public static final int SUCCESS_LOGIN = 1;
    public static final int SUCCESS_SIGNUP = 2;
    public static final int SUCCESS_VERIFY = 3;


    public static final int ERROR_NETWORK = 400;
    
    public static final int ERROR_SIGNUP_EMAIL_ALREADY_EXISTS = 401;
    public static final int ERROR_SIGNUP_NOT_GMAIL = 405;
    public static final int ERROR_SIGNUP_PASSWORD_SIZE = 406;
    
    public static final int ERROR_LOGIN_WRONG_FORMAT = 407;
    public static final int ERROR_LOGIN_EMAIL_PASSWORD = 402;
    
    
    public static final int ERROR_VERIFY_CODE = 403;
    
    public static final int ERROR_WRONG_FORMAT = 404;
    
    public static final int ERROR_SOMETHING_WENT_WRONG = 499;



    public static final String ERROR_SIGNUP_EMAIL_ALREADY_EXISTS_DESC = "email already exists";
    public static final String ERROR_SIGNUP_NOT_GMAIL_DESC = "only gmail is acceptable ";
    public static final String ERROR_SIGNUP_PASSWORD_SIZE_DESC = "password size must be atleast 8 char";
    public static final String ERROR_LOGIN_WRONG_FORMAT_DESC = "wrong email format";
    public static final String ERROR_LOGIN_EMAIL_PASSWORD_DESC = "email or password is wrong";
    public static final String ERROR_VERIFY_CODE_DESC = "verification failed";

}
