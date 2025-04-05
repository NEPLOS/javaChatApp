package chatapp.UiFolders;

import java.awt.Color;
import java.awt.Cursor;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.nio.file.Path;
import java.nio.file.Paths;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import javax.swing.SwingConstants;

import chatapp.App;
import chatapp.Client;
import chatapp.Constants;
import chatapp.Request.loginRequest;

public class LoginGui extends Form {

    JPanel loginPage = new JPanel();
    JPanel animationPanel = new JPanel();

    JLabel loginLable = new JLabel("Login"); // login lable
    JLabel emailLable = new JLabel("Email: ");
    JTextField emailFeild = new JTextField();
    JLabel passwordlLable = new JLabel("Password: ");
    JPasswordField passwordFeild = new JPasswordField();
    JButton loginButton = new JButton("Login");
    ImageIcon loginFailedIcon = new ImageIcon(Constants.CURRENT_PATH_STRING + "/resources/error24.png");
    JLabel loginFailed = new JLabel("Login failed, email or password is incorrect");
    boolean showError = false;
    
    JLabel signUpLink = new JLabel("don't havea an account ? click here");

    loginRequest loginRes;

    public LoginGui(App app) 
    {
        super(app, "login");

        app.ui.frame.setSize(720,500);
        
        addGuiElements();
        Path currentRelativePath = Paths.get("");
        String s = currentRelativePath.toAbsolutePath().toString();
        System.out.println(s);
    
        
    }

    // @Override public void paint(Graphics g) 
    // {
    //     super.paint(g);
    
    //     g.setColor(Color.black);
    //     g.drawLine(360, 0, 360, 500);
    // }

    public int countChar(String text , char c)
    {
        int result = 0;
        for(int i =0; i < text.length(); i++)
        {
            if(text.charAt(i) == c)
                result++;
        }
        return result;
    }

    // public boolean passwordFilter(String text)
    // {
    //     int result = 0;
    //     for(int i =0; i < text.length(); i++)
    //     {
    //         if(text.charAt(i) == c)
    //             result++;
    //     }
    //     return result;
    // }

    public boolean loginFilters()
    {
        String email = emailFeild.getText();
        String password = new String(passwordFeild.getPassword());

        if(!(email.endsWith("@gmail.com") && countChar(email,'@') == 1))
        {
            return false;
        }

        if(password.length() < 8 )
            return false;
        

        return true;
    }
    
    
    public void addGuiElements()
    {
        loginPage.setLayout(null);
        
        FlowLayout flowLayout = new FlowLayout();
        flowLayout.setVgap(40);
        animationPanel.setLayout(flowLayout);
    

        loginLable.setBounds(0 ,30,360,30); // bound it
        loginLable.setHorizontalAlignment(SwingConstants.CENTER);
        loginLable.setFont(new Font( "Dialog" , Font.BOLD , 22 ));
        loginLable.setForeground(Constants.TEXT_COLOR);
        loginPage.add(loginLable);

        emailLable.setBounds(20 , 110 , 360 , 20);
        emailLable.setForeground(Constants.TEXT_COLOR);
        emailLable.setFont(new Font( "Dialog" , Font.PLAIN , 14 ));
        loginPage.add(emailLable);

        emailFeild.setBounds(20 , 140 , 320 , 35);
        emailFeild.setBackground(Constants.SECOND_COLOR);
        emailFeild.setForeground(Constants.RANDOM_COLOR);
        emailFeild.setToolTipText("enter your email address");
        loginPage.add(emailFeild);

        passwordlLable.setBounds(20 , 210 , 360 , 20);
        passwordlLable.setForeground(Constants.TEXT_COLOR);
        passwordlLable.setFont(new Font( "Dialog" , Font.PLAIN , 14 ));
        loginPage.add(passwordlLable);

        passwordFeild.setBounds(20 , 240 , 320 , 35);
        passwordFeild.setBackground(Constants.SECOND_COLOR);
        passwordFeild.setForeground(Constants.RANDOM_COLOR);
        passwordFeild.setToolTipText("enter your password");
        loginPage.add(passwordFeild);

        loginButton.setFont(new Font( "Dialog" , Font.PLAIN , 14 ));
        loginButton.setBackground(Constants.TEXT_COLOR);
        loginButton.setBounds(60 , 380 , 250 , 30);
        loginButton.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        loginButton.setToolTipText("login");
        loginPage.add(loginButton);

        loginButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) 
            {
                System.out.println("Send button clicked!");


                if(loginFilters())
                {
                                        
                    loginRes = new loginRequest(emailFeild.getText(), new String(passwordFeild.getPassword()));
                    
                    String messageToServer = Client.gson.toJson(loginRes);
                    
                    app.client.sendMessage(messageToServer);
                    
                    loginButton.setEnabled(false);
                }
                else
                {   
                    if(showError == false)
                    {
                        loginFailed.setText("pls fill the email and password correctly");
                        loginFailed.setVisible(true);
                        repaint();
                    }
                }
                
            }
        });

        loginFailed.setBounds(20 , 280 , 320 , 35);
        loginFailed.setOpaque(true);
        //rgb(226, 153, 153)
        loginFailed.setBackground(new Color(226,153,153,80));
        loginFailed.setForeground(Constants.ERROR_COLOR);
        loginFailed.setIcon(loginFailedIcon);
        loginFailed.setIconTextGap(12);
        //Border border = new javax.swing.border.LineBorder(Color.RED, 2, true);
        loginFailed.setBorder(null);
        loginFailed.setVisible(false);
        loginPage.add(loginFailed);

        signUpLink.setBounds(60 , 420 , 240 , 20);
        signUpLink.setHorizontalAlignment(SwingConstants.CENTER);
        signUpLink.setBackground(Color.blue);
        signUpLink.setFont(new Font("Dialog" , Font.PLAIN , 10));
        signUpLink.setForeground(Constants.TEXT_COLOR);
        signUpLink.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));

        signUpLink.addMouseListener(new MouseAdapter() {
        
            @Override public void mouseClicked(MouseEvent e)
            {
                //LoginGui.this.dispose();
                System.out.println("moving to signup page...");
                app.ui.showPage("signup");

                //new SignupGui("").setVisible(true);
            }
        
        });

        loginPage.add(signUpLink);

        loginPage.setBounds(360, 0, 360, 500);
        loginPage.setBackground(Constants.PRIMARY_COLOR);
        add(loginPage);

        ImageIcon gifIcon = new ImageIcon(Constants.CURRENT_PATH_STRING + "/resources/chips.gif");
        System.out.println(Constants.CURRENT_PATH_STRING + "/res/error.png");

        JLabel gifLabel = new JLabel(gifIcon);

        animationPanel.add(gifLabel);


        animationPanel.setBounds(0,0,360,500);
        animationPanel.setBackground(Constants.PRIMARY2_COLOR);
        add(animationPanel);
    
    }
}
