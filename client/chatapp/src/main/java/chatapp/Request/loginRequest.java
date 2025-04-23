package chatapp.Request;

public class loginRequest extends Request 
{
    public String email;
    public String password;

    public loginRequest(String email , String password)
    {
        this.email = email;
        this.password = password;
        this.type = "loginRequest";
    }
}
