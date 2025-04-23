package chatapp.UiFolders;

import java.awt.CardLayout;

import javax.swing.JFrame;
import javax.swing.JPanel;

import chatapp.App;

public class UiManager 
{
    public JFrame frame;
    public JPanel mainPanel;   
    public CardLayout cardLayout;
    public App app;

    public UiManager(App app)
    {
        if (app.client == null) 
        {
            System.out.println("null");
        }
        else
        {
            System.out.println("not null");
        }
    }

    public void UiSetUp()
    {
        frame = new JFrame("Chips");
        cardLayout = new CardLayout();
        mainPanel = new JPanel(cardLayout);

        mainPanel.add(new loadingPage(app), "loading");
        mainPanel.add(new LoginGui(app) , "login");
        mainPanel.add(new SignupGui(app), "signup");

        // frame.setLayout(null);
        frame.add(mainPanel);
        frame.setLocationRelativeTo(null);
        frame.setSize(720,500);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setVisible(true);

        showPage("loading");
    }

    public void showPage(String name) 
    {
        System.out.println("setting GUI to : " + name);
        cardLayout.show(mainPanel, name);
    }

}
