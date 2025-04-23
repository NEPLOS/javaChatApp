package chatapp;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.Socket;
import java.util.Scanner;

import org.json.JSONObject;

import com.google.gson.Gson;

import chatapp.Response.errorResponse;
import chatapp.Response.loginResponse;
import chatapp.Response.signupResponse;
import chatapp.UiFolders.LoginGui;
import chatapp.UiFolders.MainUi;
import chatapp.UiFolders.SignupGui;
import chatapp.UiFolders.UiManager;
import chatapp.UiFolders.verificationGui;

public class Client 
{
    public boolean running = true;
    public static Socket socket;
    public static BufferedReader bufferReader;
    public static BufferedWriter bufferedWriter;
    public static Gson gson = new Gson();
    public String userDataJson;
    public UiManager ui;
    static App app;
    //ChatUI chatUi;

    public Client(Socket socket , String userDataJson , UiManager ui)
    {

        try 
        {
            this.userDataJson = userDataJson;
            this.socket = socket;
            this.bufferedWriter = new BufferedWriter(new OutputStreamWriter(socket.getOutputStream()));
            this.bufferReader = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            bufferedWriter.write(userDataJson);
            bufferedWriter.newLine();
            bufferedWriter.flush();
            this.ui = ui;
        }
        catch (IOException e) 
        {
            closeEverything(this.socket, bufferReader, bufferedWriter);
            System.out.println("ops something went wrong :((( ");
           // e.printStackTrace();
        }
        //this.clientUserName = bufferReader.readLine();
    }

    // public void setChatUi(ChatUI ui)
    // {
    //     //this.chatUi = ui;
    // }

    public void closeEverything(Socket socket , BufferedReader br , BufferedWriter bw)
    {
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
            System.out.println("333");
        }
    }

    public void disconnect() 
    {
        running = false;
        closeEverything(socket, bufferReader, bufferedWriter);
        //Thread.currentThread().interrupt();
    }

    public void lisenFormessage()
    {
        new Thread(new Runnable() 
        {
            @Override public void run()
            {
                String newMessage;

                try 
                {
                    
                    while (socket.isConnected()) 
                    {
                        newMessage = bufferReader.readLine();
                        if (newMessage != null) 
                        {
                            System.out.println(newMessage);
                            //Response response = gson.fromJson(newMessage,  Response.class);
                            
                            JSONObject jsonResponse = new JSONObject(newMessage);
                            System.out.println(jsonResponse);
                            
                            String type = jsonResponse.getString("type");
                            System.out.println(type);

                            if (type.equals("handshakeResponse")) 
                            {
                                
                                ui.showPage("login");
                            }
                            else if(type.equals("loginResponse"))
                            {
                                loginResponse ls = gson.fromJson(newMessage, loginResponse.class);
                                FileWriter fw = new FileWriter("config.json");
                                fw.write(gson.toJson(ls));
                                fw.close();
                            
                                System.out.println("you are basically login :D ");
                                System.out.println(newMessage);
                                ui.mainPanel.add(new MainUi(app),"mainUI");
                                ui.showPage("mainUI");
                            }
                            else if (type.equals("signupResponse")) 
                            {
                                signupResponse ls = gson.fromJson(newMessage, signupResponse.class);
                                // FileWriter fw = new FileWriter("config.json");
                                // fw.write(gson.toJson(ls));
                                // fw.close();
                            
                                System.out.println("go verify your ass boi ");
                                System.out.println(newMessage);

                                ui.mainPanel.add(new verificationGui(app,ls.email), "verify");
                                ui.showPage("verify");
                            }
                            else if(type.equals("errorResponse"))
                            {
                                errorResponse errorRes = gson.fromJson(newMessage, errorResponse.class); 
                                // handle changes
                                if(errorRes.status == Constants.ERROR_SIGNUP_EMAIL_ALREADY_EXISTS)
                                {
                                    SignupGui.ShowError("email already exists");
                                    System.out.println("ERROR : (signup) email is already in use");
                                    SignupGui.SignUpButton.setEnabled(true);
                                }
                                else if(errorRes.status == Constants.ERROR_SIGNUP_NOT_GMAIL)
                                {
                                    SignupGui.ShowError("only gmail is acceptable");
                                    System.out.println("ERROR : (signup) not gmail");
                                    SignupGui.SignUpButton.setEnabled(true);
                                }
                                else if(errorRes.status == Constants.ERROR_SIGNUP_PASSWORD_SIZE)
                                {
                                    SignupGui.ShowError("password size is less than 8");
                                    System.out.println("ERROR : (signup) password size is less than 8");
                                    SignupGui.SignUpButton.setEnabled(true);
                                }
                                else if(errorRes.status == Constants.ERROR_LOGIN_EMAIL_PASSWORD)
                                {
                                    System.out.println("ERROR : (login) email or password is wrong");
                                    LoginGui.ShowError("email or password is wrong");
                                    LoginGui.loginButton.setEnabled(true);
                                    ui.showPage("login");
                                }
                                else if(errorRes.status == Constants.ERROR_LOGIN_WRONG_FORMAT)
                                {
                                    System.out.println("ERROR : (login) wrong email format");
                                    LoginGui.ShowError("email format is wrong");
                                    LoginGui.loginButton.setEnabled(true);
                                    ui.showPage("login");
                                }
                                else if(errorRes.status == Constants.ERROR_VERIFY_CODE)
                                {
                                    System.out.println("ERROR : (verify) wrong verification code");
                                }
                            }
                            else
                                System.out.println("nah i'm out KneeGrow");
                            // if(response.obj instanceof successfulConnection)
                            // {
                            //     successfulConnection test = (successfulConnection)response.obj;
                            //     System.out.println(test.status);
                            // }
                            // else
                            // {
                            //     System.out.println("nah");
                            // }

                          //  System.out.println(response);
                            //successfulConnection successfulConnectionObj = (successfulConnection) response.getObj();
                            //chatUi.addMessage(newMessage, false);
                            //System.out.println(successfulConnectionObj);
    
                        }
                    }
                } 
                catch (IOException e) 
                {
                    System.out.println("222");
                    e.printStackTrace();
                    closeEverything(socket, bufferReader, bufferedWriter);
                }
            }    
        }).start();
    }

    public void sendMessage(String message)
    {
        try 
        {
            //Scanner scanne = new Scanner(System.in);
            if(socket.isConnected()) 
            {
                bufferedWriter.write(message);
                bufferedWriter.newLine();
                bufferedWriter.flush();
            }
        } 
        catch (IOException e) 
        {
            closeEverything(socket, bufferReader, bufferedWriter);
            //e.printStackTrace();
        }
    }

    public static void main(String[] args) throws IOException 
    {
        Scanner s = new Scanner(System.in);
        System.out.print("Enter your name: ");
        String username = s.nextLine();

        Socket socket = new Socket("localhost", 1234);

        //Client clnt = new Client(socket, username);
        
        //ChatUI chatUI = new ChatUI(username, clnt);
        //clnt.setChatUi(chatUI);
        //
        //clnt.lisenFormessage();
    }


}
