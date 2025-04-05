package chatppserver.Request;

public class cookieLoginRequest extends Request 
{
    public String cookie;
    public cookieLoginRequest()
    {
        this.type = "cookieLoginRequest";
    }
}
