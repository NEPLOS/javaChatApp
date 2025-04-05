package chatapp;

import java.io.File;
import java.io.FileWriter;
import java.net.Socket;
import java.util.Scanner;

import org.json.JSONObject;

import chatapp.Request.handShakeRequest;
import chatapp.UiFolders.UiManager;

public class App 
{
    public static UiManager ui;
    public static Client client;

    public App()
    {
        try
        {
            Socket socket = new Socket("localhost", 1234);
            this.client = new Client(socket, UserDataFromFile() , null);
            this.client.app = this;

            ui = new UiManager(this);
            ui.UiSetUp();

            //handleFile();
            this.client.ui = ui;
            this.client.lisenFormessage();
        }
        catch(Exception e)
        {
            e.printStackTrace();
            System.out.println("server is offline");
        }
    }

    public String UserDataFromFile()
    {
        try
        {

            File jsonFile = new File("config.json");
            System.out.println("i'm here 1 ");
            if(jsonFile.createNewFile())
            {                 
                handShakeRequest user = new handShakeRequest(null);
                String userJson = client.gson.toJson(user);
                FileWriter writer = new FileWriter("config.json");
                System.out.println("handchake request : " + userJson);
                writer.write(userJson);
                writer.close();
                return userJson;
            }
            System.out.println("i'm here 2 ");

            String data = "";
            Scanner myReader = new Scanner(jsonFile);
            while (myReader.hasNextLine()) 
            {
                data = myReader.nextLine();
                System.out.println(data);
            }
            System.out.println("i'm here 3 ");

            JSONObject jsonResponse = new JSONObject(data);
            System.out.println(jsonResponse);
            String cookie = null;
            if(jsonResponse.has("cookie"))
            {
                cookie = jsonResponse.getString("cookie");
            }
            System.out.println("cookie : " + cookie);
            System.out.println("i'm here 4 ");
            if(cookie == null)
            {
                handShakeRequest user = new handShakeRequest(null);
                String userJson = client.gson.toJson(user);
                return userJson;
            }
            else
            {
                handShakeRequest user = new handShakeRequest(cookie);
                String userJson = client.gson.toJson(user);
                return userJson;
            }

            
            
        }
        catch( Exception e)
        {
            e.printStackTrace();
            return "";
        }
    }
}
