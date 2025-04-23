package chatppserver.Response;

import chatppserver.serverStatus;

public class loginResponse extends Response 
{
    public String email;
    public String cookie;
    public String username;

    public loginResponse(String email, String cookie, String username) 
    {
        this.status = serverStatus.SUCCESS_LOGIN;
        this.type = "loginResponse";
        this.email = email;
        this.cookie = cookie;
        this.username = username;
    }    
}
