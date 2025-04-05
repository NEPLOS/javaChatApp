package chatppserver.Response;
import chatppserver.serverStatus;

public class handShakeResponse extends Response 
{
    public String cookie;
    public handShakeResponse()
    {
        this.type = "handshakeResponse";
        this.status = serverStatus.SUCCESS_CONNECTION;    
    }
}
