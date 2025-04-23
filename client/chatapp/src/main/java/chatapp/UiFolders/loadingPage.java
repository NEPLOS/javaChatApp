package chatapp.UiFolders;

import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;

import chatapp.App;
import chatapp.Constants;

public class loadingPage extends Form {

    JPanel loadinPanel = new JPanel();
    ImageIcon loadingIcon = new ImageIcon(Constants.CURRENT_PATH_STRING + "/resources/loading_128.gif");
    JLabel loadingLabel = new JLabel(loadingIcon);

    public loadingPage(App app) 
    {
        super(app , "loading");
        addGuiElements();
    }
    
    public void addGuiElements()
    {
        loadinPanel.setLayout(null);

        loadingLabel.setBounds(0,0,720,500);

        //loadingLabel.setForeground();
        //loadingLabel.setForeground(Constants.PRIMARY_COLOR);
        loadingLabel.setHorizontalAlignment(SwingConstants.CENTER);
        loadingLabel.setVerticalAlignment(SwingConstants.CENTER);

        loadinPanel.add(loadingLabel);
        loadinPanel.setBackground(Constants.PRIMARY_COLOR);
        loadinPanel.setBounds(0,0,720,500);
 
        add(loadinPanel);
    }
}
