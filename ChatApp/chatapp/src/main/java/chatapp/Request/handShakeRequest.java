package chatapp.Request;

public class handShakeRequest extends Request
{
    public String cookie;
    public handShakeRequest(String cookie)
    {
        this.cookie = cookie;
        this.type = "handshakeRequest";
    }
}
