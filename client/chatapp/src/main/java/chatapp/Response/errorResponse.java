package chatapp.Response;

public class errorResponse extends Response 
{
    public String description;
    public errorResponse(int status , String description)
    {
        this.type = "errorResponse";
        this.status = status;
        this.description = description;
    }
}
