package chatapp;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollBar;
import javax.swing.JScrollPane;
import javax.swing.JTextField;
import javax.swing.SwingUtilities;

public class ChatUI 
{
    String chatName;
    JFrame frame = new JFrame("Chat UI");
    JPanel chatPanel = new JPanel();
    JScrollPane scrollPane = new JScrollPane(chatPanel);
    JPanel chatNamePanel = new JPanel();
    JLabel chatNameLabel = new JLabel(chatName);
    JPanel sendTextPanel = new JPanel();
    JLabel sendText = new JLabel("text : ");
    JTextField message = new JTextField(15);
    JButton sendButton = new JButton("Send");

 //   client clnt;
    
    public ChatUI(String chatName)// , client clnt)
    {
     //   this.clnt = clnt;

        this.chatName = chatName;
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLayout(null);
        frame.setSize(800, 600);

        chatPanel.setLayout(new BoxLayout(chatPanel, BoxLayout.Y_AXIS));
        chatPanel.setBackground(Color.WHITE);

        scrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
        scrollPane.setBounds(200, 30, 600, 600 - 60 - 40);
        scrollPane.getVerticalScrollBar().setUnitIncrement(10);

        chatNamePanel.setBounds(200, 0, 600, 30);
        chatNamePanel.setBackground(Color.LIGHT_GRAY);
        chatNameLabel.setText("test");
        chatNamePanel.add(chatNameLabel);

        sendTextPanel.setBounds(200, 529, 600, 40);
        sendTextPanel.setBackground(Color.LIGHT_GRAY);
        sendTextPanel.add(sendText);

        //sendButton.setFocusable(false);
        sendButton.setEnabled(true);

        sendButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) 
            {
                System.out.println("Send button clicked!");
                String messageToSend = message.getText();
                if(!(messageToSend.trim().isEmpty() || messageToSend.trim().isBlank()))
                {
                   // clnt.sendMessage(messageToSend);
                    addMessage(messageToSend,true);
                    message.setText("");
                }
            }
        });

        sendTextPanel.add(message);
        sendTextPanel.add(sendButton);

        //scrollPane.getVerticalScrollBar().setValue(scrollPane.getVerticalScrollBar().getMaximum());

        frame.add(scrollPane);
        frame.add(chatNamePanel);
        frame.add(sendTextPanel);

        frame.setVisible(true);

        frame.addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                int response = JOptionPane.showConfirmDialog(
                    frame, 
                    "Are you sure you want to exit?", 
                    "Exit Chat", 
                    JOptionPane.YES_NO_OPTION, 
                    JOptionPane.QUESTION_MESSAGE
                );
                
                if (response == JOptionPane.YES_OPTION) 
                {
                    System.exit(0);
                    System.out.println("1-");
                   // clnt.disconnect();
                    System.out.println("2-");

                   // clnt.closeEverything(client.socket, client.bufferReader, client.bufferedWriter);

                    frame.dispose();
                    System.out.println("3-");
                    System.exit(0);
                    System.out.println("4-");
                } 
                else 
                {
                    frame.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
                }
            }
        });

        SwingUtilities.invokeLater(() -> {
            JScrollBar verticalBar = scrollPane.getVerticalScrollBar();
            verticalBar.setValue(verticalBar.getMaximum());
        });

    }

    public void addMessage(String text, boolean isSender) 
    {
        JPanel messagePanel = new JPanel();
        messagePanel.setLayout(new BorderLayout());

        JLabel label = new JLabel("<html><p style='width: 200px; hight: 20px margin:10; padding:10;'>" + text + "</p></html>");
        label.setOpaque(true);
        label.setMinimumSize(new Dimension(200, 20));
        label.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

        label.setBorder(BorderFactory.createCompoundBorder(
            BorderFactory.createLineBorder(Color.BLACK, 1),
            BorderFactory.createEmptyBorder(10, 10, 10, 10)
        ));

        if (isSender) 
        {
            label.setBackground(new Color(173, 216, 230));
            messagePanel.add(label, BorderLayout.EAST);
        } 
        else 
        { 
            if(text.contains("SERVER"))
            {
                label.setBackground(new Color(60, 201, 38)); 
            }
            else
            {
                label.setBackground(new Color(192, 192, 192)); 
            }
            messagePanel.add(label, BorderLayout.WEST);
        }

        chatPanel.add(messagePanel);
        chatPanel.revalidate();
        chatPanel.repaint();
        SwingUtilities.invokeLater(() -> {
            JScrollBar verticalBar = this.scrollPane.getVerticalScrollBar();
            verticalBar.setValue(verticalBar.getMaximum());
        });
    }
    public static void main(String[] args) {

        //ChatUI test = new ChatUI("t");


    }

}
