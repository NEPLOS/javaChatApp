package chatppserver;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.Socket;
import java.security.SecureRandom;
import java.util.ArrayList;
import java.util.Base64;
import java.util.Random;

import org.json.JSONObject;

import com.google.gson.Gson;

import chatppserver.Request.handShakeRequest;
import chatppserver.Request.loginRequest;
import chatppserver.Request.signupRequest;
import chatppserver.Request.verificationRequest;
import chatppserver.Response.errorResponse;
import chatppserver.Response.handShakeResponse;
import chatppserver.Response.loginResponse;
import chatppserver.Response.signupResponse;

public class ClientHandler implements Runnable 
{

    static ArrayList<ClientHandler> handler = new ArrayList<>();
    static MyJDBC connect = new MyJDBC("127.0.0.1", "3306", "mydb");
    static Gson gson = new Gson();
    //private static ArrayList<ClientHandler> ArrayList;
    Socket socket;
    BufferedReader bufferreader;
    BufferedWriter bufferwriter;
    String userEmail;

    public ClientHandler(Socket socket)
    {
        try
        {
            this.socket = socket;
            this.bufferwriter = new BufferedWriter(new OutputStreamWriter(socket.getOutputStream()));
            this.bufferreader = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            String UserData = bufferreader.readLine();
            System.out.println(UserData);

            //handShakeRequest g = new handShakeRequest();
            
            //user = gson.fromJson(UserData, UserData.class);
            //if(user.cookie == null) {System.out.println("user does not have any account");}
            //System.out.println(user);
            // connect.query("INSERT INTO user values('"+clientUserName+"')");
            handler.add(this);
            HandShakeCookie(UserData);
            //sendMessageToUser(gson.toJson(new handShakeResponse()));
        }
        catch(IOException e)
        {
            e.printStackTrace();

        }
    }

    public void HandShakeCookie(String data)
    {
        String respond = "";
        handShakeRequest lr = gson.fromJson(data, handShakeRequest.class);
        
        try 
        {

            if(lr.cookie == null)
            {
                handShakeResponse logIn = new handShakeResponse();
                respond = gson.toJson(logIn);
            }
            else
            {
                connect.querySelect("SELECT * FROM user WHERE cookie='"+lr.cookie+"';");
            
                if(connect.resultset.next())
                {
                    String LoginDataEmail = connect.resultset.getString("email");
                    String LoginDataCookie = connect.resultset.getString("cookie");
                    String LoginDataUsername = connect.resultset.getString("username");
                
                    loginResponse logIn = new loginResponse(LoginDataEmail, LoginDataCookie, LoginDataUsername);
                    respond = gson.toJson(logIn);
                }
                else
                {
                    handShakeResponse logIn = new handShakeResponse();
                    respond = gson.toJson(logIn);
                }
            }
            System.out.println("json respond : " + respond);
            sendMessageToUser(respond);

        
        } 
        catch (Exception e) 
        {
        
        }
    }
    
    @Override public void run()
    {
        String message;

        while(socket.isConnected())
        {
            try 
            {
                message = bufferreader.readLine();

                if(message != null && handler.contains(this))
                {
                    //connect.query("INSERT INTO message values('"+clientUserName+"','"+message+"')");
                    
                    JSONObject jsonResponse = new JSONObject(message);
                    System.out.println(jsonResponse);
                    
                    String type = jsonResponse.getString("type");

                    System.out.println("type");

                    if(type.equals("loginRequest"))
                    {
                        loginRequestFunc(message);
                    }
                    else if (type.equals("signupRequest"))
                    {
                        signupRequestFunc(message);
                    }
                    else if(type.equals("verificationRequest"))
                    {
                        verificationRequestFunc(message);
                    }

                    // TODO: handle messages from user
                    
                    //System.out.println(message);

                   // sendMessageToUser(message);
                }
                else
                {
                    closeEveryThing(socket, bufferreader, bufferwriter);
                    break;
                }
            } 
            catch (Exception e) 
            {
                closeEveryThing(socket, bufferreader, bufferwriter);
                break;
            }
        }
    }

    public void sendMessageToUser(String message)
    {
        try
        {
           // String successConnection = gson.toJson(new successfulConnection());
            //System.out.println(message);
            bufferwriter.write(message);
            bufferwriter.newLine();
            bufferwriter.flush();
            
        }
        catch(IOException e)
        {
            e.printStackTrace();
        }
        
    }

    public void removeClient()
    {
        System.out.println("SERVER: someone has left the chat");
        if(userEmail != null)
            connect.query("DELETE FROM tempUser WHERE email='"+userEmail+"';");
        handler.remove(this);
    }

    public void closeEveryThing(Socket socket , BufferedReader br , BufferedWriter bw)
    {
        removeClient();
        try 
        {
            if(br != null)
                br.close();
            if(bw != null)
                bw.close();
            if(socket != null)
                socket.close();
        } 
        catch (IOException e) 
        {
            e.printStackTrace();
        }
    }

    public void loginRequestFunc(String text)
    {
        try
        {

            loginRequest lr = gson.fromJson(text, loginRequest.class);
            String query = "";
            // if(lr.cookie == null)
            // {
            //     query = "SELECT * FROM user WHERE email = '"+lr.email+"' AND password = '"+lr.password+"';";
            // }
            // else
            // {
                //     query = "SELECT * FROM user WHERE cookie = '"+lr.cookie+"';";
                // }
                
            System.out.println("yo");
            connect.querySelect("SELECT * FROM user WHERE email = '"+lr.email+"' AND password = '"+lr.password+"';");
            if(connect.resultset.next())
            {
                //System.out.println("it exists");
                String LoginDataEmail = connect.resultset.getString("email");
                String LoginDataCookie = connect.resultset.getString("cookie");
                String LoginDataUsername = connect.resultset.getString("username");
                userEmail = LoginDataEmail;
                loginResponse logS = new loginResponse(LoginDataEmail, LoginDataCookie, LoginDataUsername);
                String JsonLogSuccess = gson.toJson(logS);
                System.out.println(JsonLogSuccess);
                sendMessageToUser(JsonLogSuccess);
            }
            else
            {
                errorResponse errorMessage = new errorResponse(serverStatus.ERROR_WRONG_EMAIL_PASSWORD);
                String errorMessageString = gson.toJson(errorMessage);
                sendMessageToUser(errorMessageString);
                System.out.println("does not exists");
            }
        }
        catch(Exception e)
        {
            e.printStackTrace();
        }
    }
    public void signupRequestFunc(String text)
    {
        try 
        {
            signupRequest lr = gson.fromJson(text, signupRequest.class);
            connect.querySelect("SELECT * FROM user WHERE email='"+lr.email+"';");

            if(connect.resultset.next())
            {
                //TODO: duplicate email
                errorResponse errorMessage = new errorResponse(serverStatus.ERROR_EMAIL_ALREADY_EXISTS);
                String errorMessageString = gson.toJson(errorMessage);
                sendMessageToUser(errorMessageString);
                System.out.println("email already exist");
            }
            else
            {
                String verificationCode = getSaltString();
                connect.query("INSERT INTO tempUser values('"+lr.email+"', '"+lr.password+"' ,'"+verificationCode+"')");
                signupResponse sr = new signupResponse(lr.email);
                userEmail = lr.email;
                String JsonLogSuccess = gson.toJson(sr);
                System.out.println(JsonLogSuccess);
                System.out.println("verification code : " + verificationCode);
                sendMessageToUser(JsonLogSuccess);
            }
        } 
        catch (Exception e) 
        {
            e.printStackTrace();
            // TODO: handle exception
        }
    }

    public void verificationRequestFunc(String text)
    {
        try 
        {
            verificationRequest requestVerify = gson.fromJson(text, verificationRequest.class);

            connect.querySelect("SELECT * FROM tempUser WHERE email='"+userEmail+"';");

            if(connect.resultset.next())
            {
                String verifyEmail = connect.resultset.getString("email");
                String verifyPassword = connect.resultset.getString("password");
                String verifyCode = connect.resultset.getString("verifyCode");

                if(verifyCode.equals(requestVerify.verificationCode))
                {
                    String cookie = generateCookie();
                    String randomUsername = getSaltString();
                    connect.query("INSERT INTO user values('"+verifyEmail+"' , '"+verifyPassword+"' , '"+randomUsername+"' ,'"+cookie+"');");
                    connect.query("DELETE FROM tempUser WHERE email='"+verifyEmail+"';");
                    userEmail = null;
                    loginResponse lr = new loginResponse(verifyEmail, cookie, randomUsername);
                    String lgJson = gson.toJson(lr);
                    sendMessageToUser(lgJson);
                }
            }
            else
            {
                errorResponse errorMessage = new errorResponse(serverStatus.ERROR_VERIFY_CODE);
                String errorMessageString = gson.toJson(errorMessage);
                sendMessageToUser(errorMessageString);
                System.out.println("something ain't right...");
            }
        } 
        catch (Exception e) 
        {
        
        }
    }


    public String getSaltString() { // got it from stackoverflow , don't u fucking dare 
        String SALTCHARS = "ABCDEFGHIJKLMNOPQRSTUVWXYZ1234567890";
        StringBuilder salt = new StringBuilder();
        Random rnd = new Random();
        while (salt.length() < 8) {
            int index = (int) (rnd.nextFloat() * SALTCHARS.length());
            salt.append(SALTCHARS.charAt(index));
        }
        String saltStr = salt.toString();
        return saltStr;

    }

    public String generateCookie() { // samething here , i don't know java 
        SecureRandom random = new SecureRandom();
        byte[] bytes = new byte[32];
        random.nextBytes(bytes);
        return Base64.getUrlEncoder().withoutPadding().encodeToString(bytes);
    }
}
