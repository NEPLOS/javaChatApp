package chatppserver.Response;

import chatppserver.serverStatus;

public class signupResponse extends Response
{   
    public String email;

    public signupResponse(String email) 
    {
        this.email = email;
        this.status = serverStatus.SUCCESS_SIGNUP;
        this.type = "signupResponse";
    }    
}
