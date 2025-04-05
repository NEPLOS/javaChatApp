package chatapp.Response;
import chatapp.Constants;

public class handShakeResponse extends Response 
{
    public String cookie;
    public handShakeResponse()
    {
        this.type = "handshakeResponse";
        this.status = Constants.SUCCESS_CONNECTION;    
    }
}
