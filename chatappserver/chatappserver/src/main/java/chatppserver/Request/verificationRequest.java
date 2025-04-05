package chatppserver.Request;

public class verificationRequest extends Request 
{
    public String verificationCode;
    public verificationRequest(String verificationCode)
    {
        this.verificationCode = verificationCode;
        this.type = "verificationRequest";
    }    
}
