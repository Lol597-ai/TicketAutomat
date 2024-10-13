package TICKETAUTOMAT;

import java.awt.event.ActionEvent;
 import java.awt.event.ActionListener;
 import java.awt.event.MouseAdapter;
 import java.awt.event.MouseEvent;
 import javax.swing.JButton;
 import javax.swing.JFrame;
 
 public class ButtonListener implements ActionListener {
     private MainWindow frame;
     private String name;
 
     public ButtonListener(MainWindow _frame) {
         this.frame = _frame;
     }
 
     @Override
     public void actionPerformed(ActionEvent e) {
         JButton button = (JButton) e.getSource();
         name = button.getName();
 
         switch (name) {
             case "minimizeButton":
                 frame.setState(JFrame.ICONIFIED);
                 break;
             case "exitButton":
                 frame.dispose();
                 System.exit(0);
                 break;
             case "fullscreenButton":
                 fullscreenButton();
                 break;
             default:
                 // Check if it's a menu button
                 if (name.startsWith("menuButtons")) {
                     // Extract the number from the button name
                     String buttonNumber = name.substring(name.length() - 1);
                     int menuIndex = Integer.parseInt(buttonNumber);
                     // Switch to the corresponding content area
                     frame.cardLayout.show(frame.contentArea, "contentPanel" + menuIndex);
                 } else {
                     // Check if it's a ticket button
                     if (name.startsWith("ticketButtons")) {
                         // Extract the number from the button name
                         String buttonNumber = name.substring(name.length() - 1);
                         int ticketNumber = Integer.parseInt(buttonNumber);
                         // Handle ticket action
                         handleTicketAction(ticketNumber);
                     }
                 }
                 break;
         }
     }
 
     public void fullscreenButton() {
         if (frame.getExtendedState() == JFrame.MAXIMIZED_BOTH) {
             frame.setExtendedState(JFrame.NORMAL);
         } else {
             frame.setExtendedState(JFrame.MAXIMIZED_BOTH);
         }
     }
 
     // Handle ticket action
     private void handleTicketAction(int ticketNumber) {
         // Tickettyp basierend auf der ausgewählten Nummer festlegen
         String ticketType = "";
         switch (ticketNumber) {
             case 1:
                 ticketType = "Einzelfahrschein";
                 break;
             case 2:
                 ticketType = "Tageskarte";
                 break;
             case 3:
                 ticketType = "Wochenkarte";
                 break;
             case 4:
                 ticketType = "Monatskarte";
                 break;
             default:
                 break;
         }
 
         // Ticketkaufdialog anzeigen
         String message = "Möchten Sie ein " + ticketType + " kaufen?";
         int option = javax.swing.JOptionPane.showConfirmDialog(null, message, "Ticket kaufen", javax.swing.JOptionPane.YES_NO_OPTION);
         if (option == javax.swing.JOptionPane.YES_OPTION) {
             // Hier könnten Sie den Kaufprozess implementieren
             System.out.println("Kauf von " + ticketType + " erfolgreich!");
         } else {
             System.out.println("Kauf abgebrochen.");
         }
     }
 }