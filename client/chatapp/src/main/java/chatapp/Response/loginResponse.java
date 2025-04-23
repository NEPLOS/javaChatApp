package chatapp.Response;

import chatapp.Constants;

public class loginResponse extends Response 
{
    public String email;
    public String cookie;
    public String username;

    public loginResponse(String email, String cookie, String username) 
    {
        this.status = Constants.SUCCESS_LOGIN;
        this.type = "loginResponse";
        this.email = email;
        this.cookie = cookie;
        this.username = username;
    }    
}
