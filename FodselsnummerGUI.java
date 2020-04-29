/**
* GUI for Fodselsnummer program.
*
* @author Anastasiia Danshina
* @version 1.0
* @since 2020-04-28
*/


import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

class FodselsnummerGUI{
  private JFrame frame;
  private JLabel label;
  private JPanel panelTextField;
  private JPanel panelButtons;
  private JLabel statusLabel;
  private JLabel formatLabel;

    /**
    * The main method.
    * @param args Unused.
    * @return Nothing.
    */
    public static void main(String args[]){
       FodselsnummerGUI gui = new FodselsnummerGUI();
       gui.prepareFrame();
       gui.addComponents();
    }


    /**
    * This method creates JFrame and JPanel.
    * @param Nothing.
    * @return Nothing.
    */
    private void prepareFrame(){
      // cteate frame
      frame = new JFrame("National Identity Number Validator");
      frame.setSize(350, 180);
      frame.setResizable(false);
      frame.setLayout(new GridLayout(3, 1));

      frame.addWindowListener(new WindowAdapter() {
         public void windowClosing(WindowEvent windowEvent){
            System.exit(0);
         }
      });

      // create statusLabel for displaying status of fodselsnummer (valid/invalid)
      statusLabel = new JLabel("",JLabel.CENTER);
      statusLabel.setSize(200,50);
      statusLabel.setOpaque(true);
      statusLabel.setBackground(Color.WHITE);
      // create panel for text field and its labels
      panelTextField = new JPanel();
      panelTextField.setLayout(new FlowLayout());
      panelTextField.setSize(new Dimension(300, 80));
      // create panel for buttons
      panelButtons = new JPanel();
      panelButtons.setLayout(new FlowLayout());
      panelButtons.setSize(new Dimension(300, 50));
      // add panels and statusLabel to frame
      frame.add(panelTextField);
      frame.add(panelButtons);
      frame.add(statusLabel);

      frame.setVisible(true);
    }


    /**
    * This method adds buttons and text field with labels to JPanels.
    * @param Nothing.
    * @return Nothing.
    */
    private void addComponents(){
      // create text field with a label
      JLabel  fsnLabel = new JLabel("Enter National Identity Number: ", JLabel.CENTER);
      final JTextField textField = new JTextField(11);

      //create label which is shown if non-digit characted is in text field
      formatLabel = new JLabel("");
      formatLabel.setForeground(Color.red);
      textField.addKeyListener(new KeyAdapter(){
        public void keyPressed(KeyEvent e){
          int key = e.getKeyCode();
          if (key == KeyEvent.VK_ENTER){
            String data = textField.getText();
            validate(data);
          }
        }
      });

      // add an event for ENTER key to the text field
      textField.addKeyListener(new KeyAdapter(){
        public void keyReleased(KeyEvent e){
          try{
            long i = Long.parseLong(textField.getText());
            formatLabel.setText("");
          }catch(NumberFormatException exc){
            formatLabel.setText("*digits only");
          }
        }
      });
      // add button to validate fodselsnummer
      JButton validateButton = new JButton("Validate");
      validateButton.addActionListener(new ActionListener() {
         public void actionPerformed(ActionEvent e) {
            String data = textField.getText();
            validate(data);
         }
      });
      // add button to clear text field
      JButton clearButton = new JButton("Clear");
      clearButton.addActionListener(new ActionListener() {
         public void actionPerformed(ActionEvent e) {
            textField.setText("");
            statusLabel.setText("");
            formatLabel.setText("");
            statusLabel.setBackground(Color.WHITE);
         }
      });
      // add text field, labels and buttons to the JPanel
      panelTextField.add(fsnLabel);
      panelTextField.add(textField);
      panelTextField.add(formatLabel);
      panelButtons.add(validateButton);
      panelButtons.add(clearButton);

    }


    /**
    * This method validates fodselsnummer, it uses method from Fodselsnummer program.
    * @param data String cwhich contanies a number to check.
    * @return Nothing.
    */
    private void validate(String data){
      // make statusLabel blink ones before it is updated
      Timer timer = new Timer(500, new ActionListener() {
          public void actionPerformed(ActionEvent e) {
              statusLabel.setVisible(true);
          }
       });
      timer.start();
      statusLabel.setVisible(false);

      if(!(data.equals(""))){
        // if text field is not empty
        Fodselsnummer validateFSN = new Fodselsnummer();
        Boolean val = validateFSN.validateFodselsnummer(data);
        if(val){
          statusLabel.setBackground(Color.GREEN);
          statusLabel.setText("Valid National Identity Number");
        }else {
          statusLabel.setBackground(Color.RED);
          statusLabel.setText("Invalid National Identity Number");
        }
      }else{
        statusLabel.setBackground(Color.WHITE);
        statusLabel.setText("Enter National Identity Number");
      }
    }
}
