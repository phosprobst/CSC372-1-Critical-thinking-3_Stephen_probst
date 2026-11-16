import java.awt.*;
import java.awt.event.*;
import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Random;
import javax.swing.*;

public class UserInterfaceApp extends JFrame implements ActionListener {

    private JTextArea textArea;

    private JMenuItem dateTimeItem;
    private JMenuItem saveItem;
    private JMenuItem colorItem;
    private JMenuItem exitItem;

    private JPanel mainPanel;

    private Random random;

    public UserInterfaceApp() {

        // Create window
        setTitle("User Interface Application");
        setSize(600, 400);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        random = new Random();

        // Create main panel
        mainPanel = new JPanel(new BorderLayout());

        // Create text area
        textArea = new JTextArea();
        textArea.setFont(new Font("Arial", Font.PLAIN, 16));
        textArea.setLineWrap(true);
        textArea.setWrapStyleWord(true);

        mainPanel.add(new JScrollPane(textArea), BorderLayout.CENTER);

        // Create menu bar
        JMenuBar menuBar = new JMenuBar();

        // Create menu
        JMenu menu = new JMenu("Menu");

        // Create menu items
        dateTimeItem = new JMenuItem("Display Date and Time");
        saveItem = new JMenuItem("Save to log.txt");
        colorItem = new JMenuItem("Change Background Color");
        exitItem = new JMenuItem("Exit");

        // Add ActionListeners
        dateTimeItem.addActionListener(this);
        saveItem.addActionListener(this);
        colorItem.addActionListener(this);
        exitItem.addActionListener(this);

        // Add menu items to menu
        menu.add(dateTimeItem);
        menu.add(saveItem);
        menu.add(colorItem);
        menu.addSeparator();
        menu.add(exitItem);

        // Add menu to menu bar
        menuBar.add(menu);

        // Add menu bar to frame
        setJMenuBar(menuBar);

        // Add panel to frame
        add(mainPanel);

        setLocationRelativeTo(null);
        setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent e) {

        // Menu Item 1: Display date and time
        if (e.getSource() == dateTimeItem) {

            LocalDateTime currentDateTime = LocalDateTime.now();

            DateTimeFormatter formatter =
                    DateTimeFormatter.ofPattern("MM/dd/yyyy hh:mm:ss a");

            textArea.setText(
                    "Current Date and Time:\n" +
                    currentDateTime.format(formatter)
            );
        }

        // Menu Item 2: Save text to log.txt
        else if (e.getSource() == saveItem) {

            try {

                FileWriter writer = new FileWriter("log.txt");

                writer.write(textArea.getText());

                writer.close();

                JOptionPane.showMessageDialog(
                        this,
                        "Text successfully written to log.txt"
                );

            } catch (IOException error) {

                JOptionPane.showMessageDialog(
                        this,
                        "Error writing to file: " + error.getMessage()
                );
            }
        }

        // Menu Item 3: Change background to random green
        else if (e.getSource() == colorItem) {

            int red = random.nextInt(101);
            int green = 128 + random.nextInt(128);
            int blue = random.nextInt(101);

            Color greenColor = new Color(red, green, blue);

            mainPanel.setBackground(greenColor);
            textArea.setBackground(greenColor);

            // Convert color to hexadecimal
            String hexColor = String.format(
                    "#%02X%02X%02X",
                    red,
                    green,
                    blue
            );

            // Show the selected hue in the menu item
            colorItem.setText(
                    "Change Background Color - " + hexColor
            );
        }

        // Menu Item 4: Exit
        else if (e.getSource() == exitItem) {

            System.exit(0);
        }
    }

    public static void main(String[] args) {

        SwingUtilities.invokeLater(() -> {
            new UserInterfaceApp();
        });
    }
}