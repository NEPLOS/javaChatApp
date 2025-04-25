package chatppserver.Response;

public class errorResponse extends Response 
{
    String description;
    public errorResponse(int status , String description)
    {
        this.type = "errorResponse";
        this.status = status;
        this.description = description;
    }
}
