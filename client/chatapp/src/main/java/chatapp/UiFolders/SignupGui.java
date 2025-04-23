package chatapp.UiFolders;

import java.awt.Color;
import java.awt.Cursor;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import javax.swing.SwingConstants;

import chatapp.App;
import chatapp.Constants;
import chatapp.Request.signupRequest;


public class SignupGui extends Form 
{

    static JPanel signUpPanel = new JPanel();
    JPanel animationPanel = new JPanel();
    JLabel signUpLabel = new JLabel("Sign Up");
    JLabel emailLable = new JLabel("Email: ");
    JTextField emailFeild = new JTextField();
    JLabel passwordlLable = new JLabel("Enter Password: ");
    JPasswordField passwordFeild = new JPasswordField();
    JLabel passwordlLable_2 = new JLabel("Confrim Password: ");
    JPasswordField passwordFeild_2 = new JPasswordField();
    public static JButton SignUpButton = new JButton("SignUp");

    public static ImageIcon errorLabelIcon = new ImageIcon(Constants.CURRENT_PATH_STRING + "/resources/error24.png");
    public static JLabel errorLabel = new JLabel(":D");

    //ImageIcon loginFailedIcon = new ImageIcon(Constants.CURRENT_PATH_STRING + "/resources/error24.png");
    //JLabel loginFailed = new JLabel("Login failed, email or password is incorrect");
    boolean showError = false;

    JLabel loginLink = new JLabel("already have an account ? click here");

    public SignupGui(App app) 
    {
        super(app, "sign up");
        app.ui.frame.setSize(720,500);
        addGuiElements();
    }

    // @Override public void paint(Graphics g) 
    // {
    //     super.paint(g);
    
    //     g.setColor(Color.BLACK);
    //     g.drawLine(359, 0, 359, 500);
    // }

    public static void ShowError(String Text)
    {
        errorLabel.setText(Text);
        errorLabel.setVisible(true);
        signUpPanel.repaint();
    }

    public void addGuiElements()
    {

        repaint();
        signUpPanel.setLayout(null);

        FlowLayout flowLayout = new FlowLayout();
        flowLayout.setVgap(40);
        animationPanel.setLayout(flowLayout);


        signUpLabel.setBounds(0,10,360 ,30);
        signUpLabel.setHorizontalAlignment(SwingConstants.CENTER);
        signUpLabel.setFont(new Font("Dialog" , Font.BOLD , 22));
        signUpLabel.setForeground(Constants.TEXT_COLOR);
        signUpPanel.add(signUpLabel);

        emailLable.setBounds(20 , 60 , 360 , 20);
        emailLable.setForeground(Constants.TEXT_COLOR);
        emailLable.setFont(new Font( "Dialog" , Font.PLAIN , 14 ));
        signUpPanel.add(emailLable);

        emailFeild.setBounds(20 , 90 , 320 , 35);
        emailFeild.setBackground(Constants.SECOND_COLOR);
        emailFeild.setForeground(Constants.RANDOM_COLOR);
        emailFeild.setToolTipText("enter your email address");
        signUpPanel.add(emailFeild);

        passwordlLable.setBounds(20 , 140 , 360 , 20);
        passwordlLable.setForeground(Constants.TEXT_COLOR);
        passwordlLable.setFont(new Font( "Dialog" , Font.PLAIN , 14 ));
        signUpPanel.add(passwordlLable);

        passwordFeild.setBounds(20 , 170 , 320 , 35);
        passwordFeild.setBackground(Constants.SECOND_COLOR);
        passwordFeild.setForeground(Constants.RANDOM_COLOR);
        passwordFeild.setToolTipText("enter your password");
        signUpPanel.add(passwordFeild);


        passwordlLable_2.setBounds(20 , 220 , 360 , 20);
        passwordlLable_2.setForeground(Constants.TEXT_COLOR);
        passwordlLable_2.setFont(new Font( "Dialog" , Font.PLAIN , 14 ));
        signUpPanel.add(passwordlLable_2);

        passwordFeild_2.setBounds(20 , 250 , 320 , 35);
        passwordFeild_2.setBackground(Constants.SECOND_COLOR);
        passwordFeild_2.setForeground(Constants.RANDOM_COLOR);
        passwordFeild_2.setToolTipText("enter your password");
        signUpPanel.add(passwordFeild_2);


        errorLabel.setBounds(20 , 290 , 320 , 35);
        errorLabel.setOpaque(true);
        //rgb(226, 153, 153)
        errorLabel.setBackground(new Color(226,153,153,80));
        errorLabel.setForeground(Constants.ERROR_COLOR);
        errorLabel.setIcon(errorLabelIcon);
        errorLabel.setIconTextGap(12);
        //Border border = new javax.swing.border.LineBorder(Color.RED, 2, true);
        errorLabel.setBorder(null);
        errorLabel.setVisible(false);
        signUpPanel.add(errorLabel);
        

        SignUpButton.setFont(new Font( "Dialog" , Font.PLAIN , 14 ));
        SignUpButton.setBackground(Constants.TEXT_COLOR);
        SignUpButton.setBounds(60 , 380 , 250 , 30);
        SignUpButton.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        SignUpButton.setToolTipText("login");

        SignUpButton.addActionListener((ActionListener) new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) 
            {
                System.out.println("Send button clicked!");

                String email = emailFeild.getText();
                String password1 = new String(passwordFeild.getPassword());
                String password2 = new String(passwordFeild_2.getPassword());

                if(password1.equals(password2))
                {
                    signupRequest sr = new signupRequest(email,password1);
                    String srJson = app.client.gson.toJson(sr);
                    System.out.println(srJson);
                    app.client.sendMessage(srJson);
                    SignUpButton.setEnabled(false);
                }
                else
                {
                    ShowError("wrong password");
                    System.out.println("wrong stuff ig");
                }

                // app.ui.mainPanel.add(new verificationGui(app,""), "verify");
                // app.ui.showPage("verify");
               // SignupGui.this.dispose();
                
               // new verificationGui(emailFeild.getText()).setVisible(true);
                
            }
        });

        signUpPanel.add(SignUpButton);

        loginLink.setBounds(60 , 420 , 240 , 20);
        loginLink.setHorizontalAlignment(SwingConstants.CENTER);
        loginLink.setBackground(Color.blue);
        loginLink.setFont(new Font("Dialog" , Font.PLAIN , 10));
        loginLink.setForeground(Constants.TEXT_COLOR);
        loginLink.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));

        loginLink.addMouseListener((MouseListener) new MouseAdapter() {
        
            @Override public void mouseClicked(MouseEvent e)
            {
               // SignupGui.this.dispose();
                System.out.println("moving to login page...");
                app.ui.showPage("login");
                //new LoginGui("").setVisible(true);
            }
        
        });

        signUpPanel.add(loginLink);


        signUpPanel.setBackground(Constants.PRIMARY_COLOR);
        signUpPanel.setBounds(0,0,360,500);
        add(signUpPanel);


        ImageIcon gifIcon = new ImageIcon(Constants.CURRENT_PATH_STRING + "/resources/chips.gif");
        JLabel gifLabel = new JLabel(gifIcon);
        animationPanel.add(gifLabel);
        animationPanel.setBounds(360,0,360,500);
        animationPanel.setBackground(Constants.PRIMARY2_COLOR);
        add(animationPanel);
    }
    
}
