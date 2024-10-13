package TICKETAUTOMAT;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class MainWindow extends JFrame {
    private JPanel header;
    private JPanel menubar;
    private JPanel contentArea;
    CardLayout cardLayout = new CardLayout();

    private ButtonListener guiHandler = new ButtonListener(this);

    private JButton[] menubarButtons = new JButton[4];

    private Color primaryColor = new Color(43, 42, 40);
    private Color secondaryColor = new Color(0, 0, 0);
    private Color tertiaryColor = Color.darkGray;

    private String[] ticketTypes = {"Single Ticket", "Day Pass", "Weekly Pass", "Monthly Pass"};
    private double[] ticketPrices = {2.50, 10.00, 25.00, 80.00};

    public MainWindow() {
        setUndecorated(true); // Remove window decorations
        setSize(new Dimension(1000, 700));
        setLocationRelativeTo(null);

        add(createHeader(), BorderLayout.PAGE_START);
        add(createMenubar(), BorderLayout.LINE_START);
        add(createContentArea(), BorderLayout.CENTER);

        setVisible(true);
    }

    public static void main(String[] args) {
        new MainWindow();
    }

    private JPanel createHeader() {
        header = new JPanel(new BorderLayout());
        header.setBackground(tertiaryColor);
        header.setPreferredSize(new Dimension(0, 35));

        JPanel headerLeftSide = new JPanel(new FlowLayout(FlowLayout.LEFT, 0, 0));
        headerLeftSide.setPreferredSize(new Dimension(200, 35));

        JLabel titleLabel = new JLabel("Fahrkartenautomat");
        titleLabel.setHorizontalAlignment(SwingConstants.CENTER);
        titleLabel.setPreferredSize(new Dimension(200, 35));
        titleLabel.setOpaque(true);
        titleLabel.setBackground(Color.darkGray);
        titleLabel.setForeground(Color.WHITE);

        headerLeftSide.add(titleLabel);
        header.add(headerLeftSide, BorderLayout.LINE_START);

        JPanel headerRightSide = new JPanel(new FlowLayout(FlowLayout.RIGHT, 0, 0));
        headerRightSide.setPreferredSize(new Dimension(this.getWidth() - 200, 35));
        headerRightSide.setBackground(Color.darkGray);

        JButton minimizeButton = new JButton("-");
        minimizeButton.setFont(new Font("Arial", Font.BOLD, 20));
        minimizeButton.setMargin(new Insets(2, 2, 2, 2));
        minimizeButton.setFocusable(false);
        minimizeButton.setPreferredSize(new Dimension(40, 35));
        minimizeButton.setBackground(Color.darkGray);
        minimizeButton.setForeground(Color.white);
        minimizeButton.addActionListener(guiHandler);
        minimizeButton.setName("minimizeButton");
        headerRightSide.add(minimizeButton);

        JButton fullscreenButton = new JButton("\u25A1");
        fullscreenButton.setMargin(new Insets(2, 2, 2, 2));
        fullscreenButton.setFocusable(false);
        fullscreenButton.setPreferredSize(new Dimension(40, 35));
        fullscreenButton.setBackground(Color.darkGray);
        fullscreenButton.setForeground(Color.white);
        fullscreenButton.addActionListener(guiHandler);
        fullscreenButton.setName("fullscreenButton");
        headerRightSide.add(fullscreenButton);

        JButton exitButton = new JButton("X");
        exitButton.setMargin(new Insets(2, 2, 2, 2));
        exitButton.setFocusable(false);
        exitButton.setPreferredSize(new Dimension(40, 35));
        exitButton.setBackground(Color.darkGray);
        exitButton.setForeground(Color.white);
        exitButton.addActionListener(guiHandler);
        exitButton.setName("exitButton");
        exitButton.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseEntered(MouseEvent e) {
                exitButton.setBackground(Color.RED);
            }

            @Override
            public void mouseExited(MouseEvent e) {
                exitButton.setBackground(Color.darkGray);
            }
        });

        headerRightSide.add(exitButton);
        header.add(headerRightSide, BorderLayout.LINE_END);
        return header;
    }

    private JPanel createMenubar() {
        menubar = new JPanel(new FlowLayout(FlowLayout.CENTER, 0, 20));
        menubar.setPreferredSize(new Dimension(200, 0));
        menubar.setBackground(secondaryColor);

        for (int i = 1; i < 5; i++) {
            JButton button = new JButton("Ticket " + i);
            button.setPreferredSize(new Dimension(120, 50));
            button.setForeground(Color.white);
            button.setBackground(primaryColor);
            button.setFocusPainted(false);
            button.addActionListener(guiHandler);
            button.setName("menuButton " + i);
            menubarButtons[i - 1] = button;
            JPanel div = new JPanel();
            div.add(button);
            div.setPreferredSize(new Dimension(200, 100));
            div.setBackground(secondaryColor);
            menubar.add(div);
        }
        return menubar;
    }

    private JPanel createContentArea() {
        contentArea = new JPanel();
        contentArea.setBackground(primaryColor);
        contentArea.setLayout(cardLayout);

        return contentArea;
    }

    // Inner class for handling button clicks
    class ButtonListener implements ActionListener {
        private MainWindow frame;

        public ButtonListener(MainWindow _frame) {
            this.frame = _frame;
        }

        @Override
        public void actionPerformed(ActionEvent e) {
            JButton button = (JButton) e.getSource();
            String name = button.getName();
            if (name != null && name.startsWith("menuButton")) {
                // Extract the ticket number from the button name
                String[] parts = name.split(" ");
                int ticketNumber = Integer.parseInt(parts[1]);
                // Perform ticket selection logic based on ticket number
                buyTicket(ticketNumber);
            } else if (name != null && name.equals("exitButton")) {
                // Close the application
                System.exit(0);
            } else if (name != null && name.equals("minimizeButton")) {
                // Minimize the window
                frame.setState(JFrame.ICONIFIED);
            }
        }

        private void buyTicket(int ticketNumber) {
            // Placeholder method to show ticket selection
            String ticketType = ticketTypes[ticketNumber - 1];
            double ticketPrice = ticketPrices[ticketNumber - 1];
            JOptionPane.showMessageDialog(frame, "You bought a " + ticketType + " for $" + ticketPrice);
        }
    }
}
