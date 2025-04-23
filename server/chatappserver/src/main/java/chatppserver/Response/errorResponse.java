package chatppserver.Response;

public class errorResponse extends Response 
{
    public errorResponse(int status)
    {
        this.type = "errorResponse";
        this.status = status;
    }
}
