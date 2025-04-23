package chatapp.Response;

import chatapp.Constants;

public class signupResponse extends Response
{   
    public String email;

    public signupResponse(String email) 
    {
        this.email = email;
        this.status = Constants.SUCCESS_SIGNUP;
        this.type = "signupResponse";
    }    
}
