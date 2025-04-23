package chatapp.Response;

import chatapp.Constants;

public class verificationResponse extends Response 
{
    public verificationResponse(int status)
    {
        this.type = "verificationResponse";
        this.status = Constants.SUCCESS_VERIFY;
    }    
}
