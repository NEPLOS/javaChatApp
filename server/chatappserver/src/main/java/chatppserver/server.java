package chatppserver;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;


public class server 
{
    ServerSocket serverSocket;

    public server(ServerSocket serverSocket)
    {
        this.serverSocket = serverSocket;
    }
    public void startServer()
    {
        ClientHandler.connect.query("CREATE table IF NOT EXISTS user(email TEXT,password TEXT,username TEXT, cookie TEXT);");
        ClientHandler.connect.query("CREATE table IF NOT EXISTS message(username TEXT , message TEXT);");
        ClientHandler.connect.query("CREATE table IF NOT EXISTS tempUser(email TEXT , password TEXT , verifyCode TEXT);");
        try 
        {
            while(!serverSocket.isClosed())
            {
                Socket socket = serverSocket.accept();
                System.out.println("a new client has been connected");
                ClientHandler clientHandler = new ClientHandler(socket);
                Thread thread = new Thread(clientHandler);
                thread.start(); 
            }
        } 
        catch (IOException e) 
        {
            
        }
    }
    public void closeServerSocket()
    {
        try 
        {
            if(serverSocket != null)
                serverSocket.close();    
        } 
        catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) throws IOException 
    {
        ServerSocket socket = new ServerSocket(1234);
        server s = new server(socket);
        s.startServer(); 
    }

}
