import java.awt.Color;
import java.awt.Component;
import java.awt.Font;
import java.awt.Image;
import java.awt.event.*;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import javax.swing.*;
import javax.swing.BoxLayout;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;

import java.awt.BorderLayout;

public class Server implements ActionListener {
    JTextField text;
    static JPanel textarea; //The textarea variable is declared as static because it is accessed and modified by the inner classes and methods within the Server class, including the main method. Since these inner classes and methods are called within the main method (which is also static), the textarea variable needs to be static to be accessible from within the main method.
    static Box vertical = Box.createVerticalBox();
    static JFrame f = new JFrame();
    static DataOutputStream output;

    Server() {
        // This is for the background image of the frame
        ImageIcon backgroundImage = new ImageIcon("whatsapp.jpg"); // getting the image
                                                                                                  // using the ImageIcon
        JLabel backgroundLabel = new JLabel(backgroundImage);// creating label for the background
        f.setContentPane(backgroundLabel);// setting this to the frame

        JPanel contentPane = new JPanel(); // A new JPanel named contentPane is created. This panel will serve as a
                                           // container for other UI components.
        contentPane.setOpaque(false); // The setOpaque(false) method is called on contentPane. This sets the panel to
                                      // be transparent, allowing the background image to be visible through it.
        contentPane.setLayout(null); // The layout manager of contentPane is set to null. This means that the panel's
                                     // components will be manually positioned using absolute coordinates rather than
                                     // relying on a specific layout manager.
        contentPane.setBounds(0, 0, 450, 720); // The setBounds method is used to set the size and position of
                                               // contentPane. In this case, it sets the panel's position to (0, 0) and
                                               // its size to 450 pixels in width and 720 pixels in height.
        backgroundLabel.add(contentPane); // Finally, the contentPane panel is added to the backgroundLabel label. This
                                          // ensures that the contentPane is displayed as part of the background image
                                          // label and allows other components to be added to it.

        JPanel jpanel = new JPanel(); // A new JPanel named jpanel is created. This panel will serve as a container
                                      // for other UI components within the contentPane.
        jpanel.setBackground(new Color(7, 94, 84)); // The setBackground method is called on jpanel to set its
                                                    // background color. In this case, the color is set to an RGB value
                                                    // of (7, 94, 84), which represents a dark green color.
        jpanel.setBounds(0, 0, 450, 70); // The setBounds method is used to set the size and position of jpanel. In this
                                         // case, it sets the panel's position to (0, 0) and its size to 450 pixels in
                                         // width and 70 pixels in height. This determines the placement of jpanel
                                         // within the contentPane.
        jpanel.setLayout(null); // The layout manager of jpanel is set to null. This means that the panel's
                                // components will be manually positioned using absolute coordinates rather than
                                // relying on a specific layout manager.
        contentPane.add(jpanel); // Finally, the jpanel panel is added to the contentPane panel. This places
                                 // jpanel as a child component within contentPane, allowing other components to
                                 // be added to jpanel.

        ImageIcon main_arrow = new ImageIcon("arrow.png");
        Image sized_arrow = main_arrow.getImage().getScaledInstance(25, 25, Image.SCALE_DEFAULT);
        ImageIcon copy_arrow = new ImageIcon(sized_arrow);
        JLabel arrow = new JLabel(copy_arrow);
        arrow.setBounds(5, 20, 25, 25);
        jpanel.add(arrow);

        arrow.addMouseListener(new MouseAdapter() {
            public void mouseClicked(MouseEvent e) {
                f.setVisible(false);
            }
        });

        ImageIcon main_pic = new ImageIcon("hacker.png");
        Image sized_pic = main_pic.getImage().getScaledInstance(40, 40, Image.SCALE_DEFAULT);
        ImageIcon copy_pic = new ImageIcon(sized_pic);
        JLabel pic = new JLabel(copy_pic);
        pic.setBounds(40, 10, 40, 40);
        jpanel.add(pic);

        ImageIcon main_vedio = new ImageIcon("video.png");
        Image sized_vedio = main_vedio.getImage().getScaledInstance(25, 25, Image.SCALE_DEFAULT);
        ImageIcon copy_vedio = new ImageIcon(sized_vedio);
        JLabel vedio = new JLabel(copy_vedio);
        vedio.setBounds(310, 25, 25, 25);
        jpanel.add(vedio);

        ImageIcon main_call = new ImageIcon("call.png");
        Image sized_call = main_call.getImage().getScaledInstance(25, 25, Image.SCALE_DEFAULT);
        ImageIcon copy_call = new ImageIcon(sized_call);
        JLabel call = new JLabel(copy_call);
        call.setBounds(360, 25, 25, 25);
        jpanel.add(call);

        ImageIcon main_dots = new ImageIcon("dots.png");
        Image sized_dots = main_dots.getImage().getScaledInstance(10, 22, Image.SCALE_DEFAULT);
        ImageIcon copy_dots = new ImageIcon(sized_dots);
        JLabel dots = new JLabel(copy_dots);
        dots.setBounds(410, 23, 10, 25);
        jpanel.add(dots);

        JLabel name_label = new JLabel("Durga Sainath");
        name_label.setBounds(100, 10, 150, 30);
        name_label.setForeground(Color.white);
        name_label.setFont(new Font("sans-serif", Font.BOLD, 18));
        jpanel.add(name_label);

        JLabel status_label = new JLabel("Online");
        status_label.setBounds(100, 30, 150, 30);
        status_label.setForeground(Color.white);
        status_label.setFont(new Font("sans-serif", Font.BOLD, 14));
        jpanel.add(status_label);

        textarea = new JPanel();
        textarea.setBounds(10, 75, 430, 570);
        textarea.setOpaque(false);
        textarea.setLayout(new BorderLayout());
        JScrollPane scrollPane = new JScrollPane(textarea);
        scrollPane.setBounds(10, 75, 430, 570);
        scrollPane.getViewport().setOpaque(false);
        scrollPane.setOpaque(false);
        scrollPane.setBackground(new Color(0, 0, 0, 0));
        scrollPane.setBorder(null);
        scrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_NEVER);
        contentPane.add(scrollPane);

        text = new JTextField();
        text.setBounds(60, 655, 330, 40);
        text.setFont(new Font("sans-serif", Font.BOLD, 16));
        text.setBorder(null);
        contentPane.add(text);

        ImageIcon smiley_icon = new ImageIcon("smiley.jpeg");
        Image sized_smiley = smiley_icon.getImage().getScaledInstance(40, 40, Image.SCALE_DEFAULT);
        ImageIcon copy_smiley = new ImageIcon(sized_smiley);
        // JButton smiley=new JButton(copy_smiley);
        RoundedButton smiley = new RoundedButton(copy_smiley);
        smiley.setBounds(10, 655, 40, 40);
        // send.setBackground(Color.BLACK);
        contentPane.add(smiley);

        ImageIcon sent_icon = new ImageIcon("sent.png");
        Image sized_sent = sent_icon.getImage().getScaledInstance(40, 40, Image.SCALE_DEFAULT);
        ImageIcon copy_sent = new ImageIcon(sized_sent);
        RoundedButton send = new RoundedButton(copy_sent);
        send.addActionListener(this);
        send.setBounds(400, 655, 40, 40);
        contentPane.add(send);

        f.setSize(450, 720);
        f.setLocation(300, 150);
        f.setTitle("Whatsapp");
        f.setUndecorated(true);
        f.setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent ae) {
        try {
            String name = text.getText();
            JPanel name_panel = formatLabel(name);
            JPanel right = new JPanel(new BorderLayout());
            right.setOpaque(false);
            right.add(name_panel, BorderLayout.LINE_END);
            vertical.add(right);
            vertical.add(Box.createVerticalStrut(15));
            textarea.add(vertical, BorderLayout.PAGE_START);
            output.writeUTF(name);
            text.setText("");
            f.repaint();
            f.validate();
        } catch (IOException e) {}
    }

    public static JPanel formatLabel(String name) {
        JLabel name_label = new JLabel("<html><p style=\"width: 150px\">" + name + "</p></html>");
        name_label.setFont(new Font("sans-serif", Font.BOLD, 16));
        name_label.setOpaque(false);
        name_label.setForeground(Color.white);
        name_label.setBackground(new Color(0, 255, 0, 128)); // set green transparent background
        JPanel name_panel = new JPanel();
        name_panel.setBackground(new Color(7, 94, 84)); // set transparent background
        name_panel.setOpaque(true);
        name_panel.setLayout(new BoxLayout(name_panel, BoxLayout.Y_AXIS));
        name_panel.setBorder(new EmptyBorder(10, 10, 10, 10));
        // name_panel.setBorder(new RoundedBorder(20));
        name_panel.add(name_label);

        Calendar calendar = Calendar.getInstance();
        SimpleDateFormat date = new SimpleDateFormat("HH:mm");
        JLabel time = new JLabel();
        time.setText(date.format(calendar.getTime()));
        time.setFont(new Font("sans-serif", Font.PLAIN, 8));
        time.setForeground(Color.white);
        time.setAlignmentX(Component.LEFT_ALIGNMENT);
        name_panel.add(Box.createVerticalStrut(5)); // add some spacing between the name and the time label
        name_panel.add(time);

        return name_panel;
    }

    public static void main(String[] args) {
        new Server(); // Creates an instance of the Server class, which initializes the GUI and sets
                      // up the server.
        try {
            try (ServerSocket socket = new ServerSocket(1234)) { // Creates a new ServerSocket object that listens on
                                                                 // port 1234 for incoming connections. The try
                                                                 // statement ensures that the socket is properly closed
                                                                 // after use.
                while (true) { // Enters an infinite loop to continuously listen for incoming connections.
                    Socket s = socket.accept(); // Accepts an incoming connection and creates a new Socket object (s) to
                                                // handle communication with the client.
                    DataInputStream input = new DataInputStream(s.getInputStream()); // Creates a new DataInputStream to
                                                                                     // read data from the client's
                                                                                     // input stream.
                    output = new DataOutputStream(s.getOutputStream()); // Creates a new DataOutputStream to send data
                                                                        // to the client's output stream.
                    while (true) {// Enters an inner loop to continuously read messages from the client.
                        String message = input.readUTF(); // Reads a UTF-8 encoded string from the client's input
                                                          // stream.
                        JPanel panel = formatLabel(message); // Creates a new JPanel by calling the formatLabel method,
                                                             // passing the received message as an argument. This panel
                                                             // represents the formatted message to be displayed.
                        panel.setOpaque(false); // set panel background color as transparent
                        JPanel messagePanel = new JPanel(new BorderLayout()); // Creates a new JPanel named left with a
                                                                              // BorderLayout manager. This panel will
                                                                              // contain the panel representing the
                                                                              // message.
                        messagePanel.add(panel, BorderLayout.LINE_START); // Adds the panel to the left panel,
                                                                         // positioning it on the left side.
                        messagePanel.setOpaque(false); // set message panel background color as transparent
                        vertical.add(messagePanel);
                        vertical.add(Box.createVerticalStrut(15));
                        vertical.setOpaque(false); // set vertical box background color as transparent
                        textarea.add(vertical, BorderLayout.PAGE_START);
                        textarea.setOpaque(false); // set textarea background color as transparent
                        f.validate();
                    }
                }
            }
        } catch (Exception e) {}
    }
}