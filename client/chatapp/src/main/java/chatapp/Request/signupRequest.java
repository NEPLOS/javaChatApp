package chatapp.Request;

public class signupRequest extends Request 
{
    public String email;
    public String password;

    public signupRequest(String email , String password)
    {
        this.email = email;
        this.password = password;
        this.type = "signupRequest";
    }    
}
