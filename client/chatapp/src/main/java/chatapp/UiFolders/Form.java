package chatapp.UiFolders;

import javax.swing.JPanel;

import chatapp.App;

public class Form extends JPanel
{
    public App app;

    public Form(App app , String title)
    {

        this.app = app;

        //super(title);
    
        setSize(720,500);

        //setDefaultCloseOperation(EXIT_ON_CLOSE);

        setLayout(null); // disable layout managment

        //setLocationRelativeTo(null); // center the screen

        //setResizable(false); // i mean... u can see 
    }   
}
