package chatapp.UiFolders;

import java.awt.Color;
import java.awt.Cursor;
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
import javax.swing.JTextField;
import javax.swing.SwingConstants;

import chatapp.App;
import chatapp.Constants;
import chatapp.Request.verificationRequest;

public class verificationGui extends Form 
{
    JPanel verifyPanel = new JPanel();
    String emailAddress = "";

    ImageIcon verifyIcon = new ImageIcon(Constants.CURRENT_PATH_STRING + "/resources/verify.png");
    JLabel verifyIconLabel = new JLabel("");


    public verificationGui(App app , String email) 
    {
        super(app , "Verification");
        app.ui.frame.setSize(500,640);
        emailAddress = email;
        addGuiElements();
    }

    public void addGuiElements()
    {

        verifyPanel.setLayout(null);

        // ImageIcon gifIcon = new ImageIcon(Constants.CURRENT_PATH_STRING + "/resources/chips.gif");
        // JLabel gifLabel = new JLabel(gifIcon);
        // verifyPanel.add(gifLabel);

        verifyIconLabel.setBounds(0 , 10 , 500 , 128);
        verifyIconLabel.setOpaque(true);
        //rgb(226, 153, 153)
        verifyIconLabel.setBackground(new Color(226,153,153,0));
        //verifyIconLabel.setForeground(Constants.ERROR_COLOR);
        verifyIconLabel.setIcon(verifyIcon);
        verifyIconLabel.setBorder(null);
        verifyIconLabel.setHorizontalAlignment(SwingConstants.CENTER);
        verifyPanel.add(verifyIconLabel);

        JLabel verifyLabel = new JLabel("Verify your Code");
        verifyLabel.setFont(new Font("Dialog" , Font.BOLD , 25));
        verifyLabel.setForeground(Constants.TEXT_COLOR);
        verifyLabel.setBounds(0, 128 + 20, 500, 35);
        verifyLabel.setHorizontalAlignment(SwingConstants.CENTER);
        verifyPanel.add(verifyLabel);

        JLabel emailLabel = new JLabel("<html>We have sent a verification code to your email<br>" + "Please check your inbox and enter the code to continue.</html>");
        emailLabel.setBounds(50 , 128 + 95 , 600 , 80);
        emailLabel.setFont(new Font("Dialog" , Font.PLAIN , 14));
        emailLabel.setForeground(Constants.TEXT_COLOR);
        verifyPanel.add(emailLabel);

        JLabel emailLable = new JLabel("<html> your email Address: <span style=\"color:#F2E9E4;\">" + emailAddress + "</span></html>");
        emailLable.setBounds(50 , 128 + 145 , 600 , 80);
        emailLable.setFont(new Font("Dialog" , Font.PLAIN , 14));
        emailLable.setForeground(Constants.TEXT_COLOR);
        verifyPanel.add(emailLable);


        JLabel enterCodeLabel = new JLabel("verification code: ");
        enterCodeLabel.setBounds(50 ,130 + 165 , 600 , 80);
        enterCodeLabel.setFont(new Font("Dialog" , Font.PLAIN , 14));
        enterCodeLabel.setForeground(Constants.TEXT_COLOR);
        verifyPanel.add(enterCodeLabel);

        JTextField verificationCodeField = new JTextField();
        verificationCodeField.setBounds(45 ,130 + 220 , 500 - 90 , 50);
        verificationCodeField.setForeground(Constants.TEXT_COLOR);
        verificationCodeField.setBackground(Constants.SECOND_COLOR);
        verificationCodeField.setFont(new Font("Dialog" , Font.PLAIN , 32));
        verificationCodeField.setToolTipText("enter the verification code");
        verifyPanel.add(verificationCodeField);

        JButton submitCode = new JButton("Submit");
        submitCode.setBounds( 125 , 450, 250 , 30);
        submitCode.setHorizontalAlignment(SwingConstants.CENTER);
        submitCode.setBackground(Constants.TEXT_COLOR);


        submitCode.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) 
            {
                System.out.println("Send button clicked!");
                String code = verificationCodeField.getText();
                verificationRequest vr = new verificationRequest(code);
                String vrJson = app.client.gson.toJson(vr);
                app.client.sendMessage(vrJson);
            }
        });
        verifyPanel.add(submitCode);

        JLabel resendLabel = new JLabel("<html>Didn't recive code? <span style=\"color:#F2E9E4\";>Resend</span><html>");
        resendLabel.setBounds(150 , 480 , 200 , 40);
        resendLabel.setForeground(Constants.TEXT_COLOR);
        resendLabel.setHorizontalAlignment(SwingConstants.CENTER);
        verifyPanel.add(resendLabel);
        verifyPanel.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        resendLabel.addMouseListener((MouseListener) new MouseAdapter() {
        
            @Override public void mouseClicked(MouseEvent e)
            {
                
                System.out.println("hello");
                //new LoginGui("").setVisible(true);
            }
        
        });


        verifyPanel.setBounds(0,0,500,640);
        verifyPanel.setBackground(Constants.PRIMARY_COLOR);

        add(verifyPanel);
    }
        
}
