import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableRowSorter;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;



public class UI extends JFrame implements ActionListener {

    private final JButton BUTTONNAVHOME, BUTTONNAVDONATION, BUTTONNAVSTORE, BUTTONNAVBOOKING, BUTTONNAVLOGIN;
    private final JPanel PANELCONTENT;
    public static JTextField emailField, createFirstNameField,createSecondNameField,createEmailField,createPhoneNumberField;
    public static JTextField createDOBField;
    public static JPasswordField passwordField,createPasswordField,confirmPasswordField;
    private JButton loginButton,createAccountButton;

    private TableRowSorter sorter;






    final Color REDPRIMARY = Color.decode("#c94235"); //global variable for the red colour of the app
    final Color BLUEPRIMARY = Color.decode("#0061a3"); //global variable for the blue colour of the app

    public UI() { //creation of UI and its components such as the menu buttons

        setTitle("Room Booking");
        setSize(450, 932);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setResizable(false);


        JPanel panelButtons = new JPanel();
        panelButtons.setLayout(null);
        panelButtons.setSize(450, 75);
        panelButtons.setBackground(Color.darkGray);

        PANELCONTENT = new JPanel();
        PANELCONTENT.setLayout(null);
        PANELCONTENT.setBounds(0, 75, 450, 857);
        PANELCONTENT.setBackground(REDPRIMARY);

        Icon buttonHomeIcon = new ImageIcon("src/HomeIcon2.png");
        BUTTONNAVHOME = new JButton(buttonHomeIcon);
        BUTTONNAVHOME.setFocusPainted(false);
        BUTTONNAVHOME.addActionListener(this);
        panelButtons.add(BUTTONNAVHOME);
        int buttonWidth = 87;
        int buttonHeight = 75;
        BUTTONNAVHOME.setBounds(0, 0, buttonWidth, buttonHeight);
        BUTTONNAVHOME.setBackground(BLUEPRIMARY);

        Icon buttonDonationIcon = new ImageIcon("src/DonationImage.png");
        BUTTONNAVDONATION = new JButton(buttonDonationIcon);
        BUTTONNAVDONATION.setFocusPainted(false);
        BUTTONNAVDONATION.addActionListener(this);
        panelButtons.add(BUTTONNAVDONATION);
        BUTTONNAVDONATION.setBounds(87, 0, buttonWidth, buttonHeight);
        BUTTONNAVDONATION.setBackground(BLUEPRIMARY);


        Icon buttonStoreIcon = new ImageIcon("src/StoreIcon.png");
        BUTTONNAVSTORE = new JButton(buttonStoreIcon);
        BUTTONNAVSTORE.setFocusPainted(false);
        BUTTONNAVSTORE.addActionListener(this);
        panelButtons.add(BUTTONNAVSTORE);
        BUTTONNAVSTORE.setBounds(174, 0, buttonWidth, buttonHeight);
        BUTTONNAVSTORE.setBackground(BLUEPRIMARY);

        Icon buttonBookingIcon = new ImageIcon("src/BookingIcon.png");
        BUTTONNAVBOOKING = new JButton(buttonBookingIcon);
        BUTTONNAVBOOKING.setFocusPainted(false);
        BUTTONNAVBOOKING.addActionListener(this);
        panelButtons.add(BUTTONNAVBOOKING);
        BUTTONNAVBOOKING.setBounds(261, 0, buttonWidth, buttonHeight);
        BUTTONNAVBOOKING.setBackground(BLUEPRIMARY);

        Icon buttonMenuIcon = new ImageIcon("src/LoginIcon.png");
        BUTTONNAVLOGIN = new JButton(buttonMenuIcon);
        BUTTONNAVLOGIN.setFocusPainted(false);
        BUTTONNAVLOGIN.addActionListener(this);
        panelButtons.add(BUTTONNAVLOGIN);
        BUTTONNAVLOGIN.setBounds(348, 0, buttonWidth, buttonHeight);
        BUTTONNAVLOGIN.setBackground(BLUEPRIMARY);



        getContentPane().add(panelButtons);
        getContentPane().add(PANELCONTENT);
        setVisible(true);




    }


    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == BUTTONNAVHOME) {
            resetPanel();
            panelHomePage();

        }
        else if (e.getSource() == BUTTONNAVDONATION){
            resetPanel();
            panelDonationPage();
        }
        else if (e.getSource() == BUTTONNAVSTORE){
            resetPanel();
            panelStorePage();
        }
        else if (e.getSource() == BUTTONNAVBOOKING){
            resetPanel();
            panelTableBookingPage();
        }
        else if (e.getSource() == BUTTONNAVLOGIN){
            resetPanel();
            panelLogIn();
        }
    }

    public void resetPanel() {
        PANELCONTENT.removeAll();
        PANELCONTENT.repaint();
        PANELCONTENT.setBounds(0, 75, 450, 857);
    }
    public void panelHomePage() {
        //code unimplemented
    }

    public void panelDonationPage(){
        //code unimplemented
    }

    public void panelStorePage(){
        //code unimplemented

    }

    public void panelTableBookingPage() {
        System.out.println(DatabaseConnectionLogIn.loggedInUser); // determines what user logged in
        DefaultTableModel model = new DefaultTableModel();
        model.setColumnIdentifiers(new String[]{"Table Number","Number of Seats", "Location"});

        sorter = new TableRowSorter<>(model);
        JTable tableRooms = new JTable(model);

        //Booking.displayBookings(model);
        JScrollPane jscroll = new JScrollPane(tableRooms);
        PANELCONTENT.add(jscroll);
        jscroll.setBounds(25, 45, 375, 350);
    }


    public void panelLogIn() {

        //all code for creation of login fields
        JLabel loginLabel = new JLabel("Log in");
        loginLabel.setFont(new Font("Nexa", Font.PLAIN, 30));
        JLabel emailLabel = new JLabel("Email:");
        JLabel passwordLabel = new JLabel("Password:");
        emailField = new JTextField(10);
        passwordField = new JPasswordField(10);

        //log in button
        loginButton = new JButton("Log in");
        loginButton.setBackground(BLUEPRIMARY);
        loginButton.setForeground(Color.white);
        loginButton.setFont(new Font("Nexa",Font.PLAIN,20));

        //sets positions of labels and fields
        loginLabel.setBounds(150,60,100,40);
        emailLabel.setBounds(60,100,100,40);
        passwordLabel.setBounds(60,150,100,40);
        emailField.setBounds(150,100,200,40);
        passwordField.setBounds(150,150,200,40);
        loginButton.setBounds(150,200,200,30);
        // adds fields and label to frame
        PANELCONTENT.add(loginLabel);
        PANELCONTENT.add(emailLabel);
        PANELCONTENT.add(passwordLabel);
        PANELCONTENT.add(emailField);
        PANELCONTENT.add(passwordField);
        PANELCONTENT.add(loginButton);

        loginButton.addActionListener(e -> { //Add the action listener for the login button
            DatabaseConnectionLogIn db = new DatabaseConnectionLogIn();
            db.connectToDB();
                });


        //all code for creation of sign up fields
        JLabel createAccountLabel = new JLabel("<html> OR <br/>Create account</html>");
        createAccountLabel.setFont(new Font("Nexa", Font.PLAIN,30));
        JLabel createFirstNameLabel = new JLabel("First Name:");
        JLabel createSecondNameLabel = new JLabel("Second Name:");
        JLabel createPasswordLabel = new JLabel("Password:");
        JLabel confirmPasswordLabel = new JLabel("<html>Confirm<br/>password:</html>");
        JLabel createEmailLabel = new JLabel("Email:");
        JLabel createPhoneNumber = new JLabel("Phone Number:");
        JLabel createDOBLabel = new JLabel("Date of Birth");

        //create account button
        createAccountButton = new JButton("Create account");
        createAccountButton.setBackground(BLUEPRIMARY);
        createAccountButton.setForeground(Color.white);
        createAccountButton.setFont(new Font("Nexa",Font.PLAIN,20));
        createAccountButton.addActionListener(this); //action listener for create account button
        createFirstNameField = new JTextField(10);
        createSecondNameField = new JTextField(10);
        createPasswordField = new JPasswordField(10);
        confirmPasswordField = new JPasswordField(10);
        createEmailField = new JTextField(10);
        createPhoneNumberField = new JTextField(10);
        createDOBField = new JTextField(10);

        createAccountLabel.setBounds(150,300,200,70);
        createFirstNameLabel.setBounds(60,420,100,40);
        createSecondNameLabel.setBounds(60,470,100,40);
        createPasswordLabel.setBounds(60,520,100,40);
        confirmPasswordLabel.setBounds(60,570,100,40);
        createEmailLabel.setBounds(60,620,100,40);
        createPhoneNumber.setBounds(60,670,100,40);
        createDOBLabel.setBounds(60,720,100,40);

        createFirstNameField.setBounds(150,420,200,40);
        createSecondNameField.setBounds(150,470,200,40);
        createPasswordField.setBounds(150,520,200,40);
        confirmPasswordField.setBounds(150,570,200,40);
        createEmailField.setBounds(150,620,200,40);
        createPhoneNumberField.setBounds(150,670,200,40);
        createDOBField.setBounds(150,720,200,40);
        createAccountButton.setBounds(150,770,200,30);

        //add labels to panel
        PANELCONTENT.add(createAccountLabel);
        PANELCONTENT.add(createFirstNameLabel);
        PANELCONTENT.add(createSecondNameLabel);
        PANELCONTENT.add(createPasswordLabel);
        PANELCONTENT.add(confirmPasswordLabel);
        PANELCONTENT.add(createEmailLabel);
        PANELCONTENT.add(createPhoneNumber);
        PANELCONTENT.add(createDOBLabel);
        // add Jfields to panel
        PANELCONTENT.add(createFirstNameField);
        PANELCONTENT.add(createSecondNameField);
        PANELCONTENT.add(createPasswordField);
        PANELCONTENT.add(confirmPasswordField);
        PANELCONTENT.add(createPhoneNumberField);
        PANELCONTENT.add(createEmailField);
        PANELCONTENT.add(createDOBField);
        // add button to panel
        PANELCONTENT.add(createAccountButton);

        //action listener that runs the validation when the "Create account" button is pressed by the user
        createAccountButton.addActionListener( e -> {
            CreateAccount.isValidDetails(createFirstNameField,createSecondNameField,createPasswordField,confirmPasswordField,createPhoneNumberField,createEmailField,createDOBField);
        });




        // Add the main panel to the frame
        add(PANELCONTENT);
    }



}