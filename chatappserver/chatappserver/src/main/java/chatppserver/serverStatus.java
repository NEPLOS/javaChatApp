package chatppserver;

public class serverStatus 
{

    public static final int SUCCESS_CONNECTION = 0;
    public static final int SUCCESS_LOGIN = 1;
    public static final int SUCCESS_SIGNUP = 2;
    public static final int SUCCESS_VERIFY = 3;


    public static final int ERROR_NETWORK = 400;
    public static final int ERROR_EMAIL_ALREADY_EXISTS = 401;
    public static final int ERROR_WRONG_EMAIL_PASSWORD = 402;
    public static final int ERROR_VERIFY_CODE = 403;

}
