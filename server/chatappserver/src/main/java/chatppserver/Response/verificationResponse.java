package chatppserver.Response;

import chatppserver.serverStatus;

public class verificationResponse extends Response 
{
    public verificationResponse(int status)
    {
        this.type = "verificationResponse";
        this.status = serverStatus.SUCCESS_VERIFY;
    }    
}
