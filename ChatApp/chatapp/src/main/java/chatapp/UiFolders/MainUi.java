package chatapp.UiFolders;

import java.awt.Font;

import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;

import chatapp.App;
import chatapp.Constants;

public class MainUi extends Form 
{
    JPanel mainPanel = new JPanel();
    JLabel tempLabel = new JLabel("under construction :( ");
    public MainUi(App app) 
    {
        super(app, "Chips");
        app.ui.frame.setSize(720,500);
        addGuiElements();
    }

    public void addGuiElements()
    {
        mainPanel.setLayout(null);


        tempLabel.setLayout(null);
        tempLabel.setBounds(0,0,720,500);
        tempLabel.setHorizontalAlignment(SwingConstants.CENTER);
        tempLabel.setFont(new Font("Dialog" , Font.BOLD , 20));
        tempLabel.setForeground(Constants.TEXT_COLOR);
        mainPanel.add(tempLabel);

        mainPanel.setBounds(0,0,720,500);
        mainPanel.setBackground(Constants.PRIMARY_COLOR);

        add(mainPanel);

    }
    
}
