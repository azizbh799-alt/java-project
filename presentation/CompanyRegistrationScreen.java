package presentation;

import java.awt.Container;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.InputMismatchException;

import controller.CompanyController;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;

public class CompanyRegistrationScreen extends JFrame {

    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private CompanyController companyController;

    //label and input for Company Contract Number
    private JLabel lbNumContrato;
    private JTextField tfNumContrato;

    //label and input for Company Name
    private JLabel lbNameEmp;
    private JTextField tfNameEmp;

    //Save and Cancel buttons
    private JButton btSave;
    private JButton btCancel;

    private Container container;
    private EventManager eventManager;

    public CompanyRegistrationScreen(CompanyController companyController) {
        super("Company Registration");
        this.companyController = companyController;
        this.eventManager = new EventManager();
        initialize();

        setSize(500, 300);
        setLocationRelativeTo(null);
    }

    private void initialize() {

        container = getContentPane();
        container.setLayout(new GridBagLayout());

        GridBagConstraints cts = new GridBagConstraints();

        //initialize and add company name elements
        lbNameEmp = new JLabel();
        lbNameEmp.setText("Company name:");
        cts.gridx = 0;
        cts.gridy = 0;
        container.add(lbNameEmp, cts);

        tfNameEmp = new JTextField();
        tfNameEmp.setPreferredSize(new Dimension(150, 20));
        cts.fill = GridBagConstraints.HORIZONTAL;
        cts.gridx = 1;
        cts.gridy = 0;
        container.add(tfNameEmp, cts);

        //initialize and add contract number elements
        lbNumContrato = new JLabel();
        lbNumContrato.setText("Company contract number:");
        cts.gridx = 0;
        cts.gridy = 1;
        container.add(lbNumContrato, cts);

        tfNumContrato = new JTextField();
        tfNumContrato.setPreferredSize(new Dimension(200, 20));
        cts.fill = GridBagConstraints.HORIZONTAL;
        cts.gridx = 1;
        cts.gridy = 1;
        container.add(tfNumContrato, cts);

        //initialize and add buttons
        btSave = new JButton("Save");
        btSave.setActionCommand(btSave.getText());
        btSave.addActionListener(eventManager);
        btSave.setSize(new Dimension(20, 20));
        cts.gridx = 0;
        cts.gridy = 2;
        container.add(btSave, cts);

        btCancel = new JButton("Cancel");
        btCancel.setActionCommand(btCancel.getText());
        btCancel.addActionListener(eventManager);
        btCancel.setSize(new Dimension(20, 20));
        cts.gridx = 1;
        cts.gridy = 2;
        container.add(btCancel, cts);
    }

    private class EventManager implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {

            //this variable is not necessary, but kept for now
            boolean varControle = true;

            //set an initial value because Java requires it
            Long numContrato = Long.MIN_VALUE;
            String name = null;

            if (e.getActionCommand().equals(btSave.getActionCommand())) {
                //read text fields and create a new company object
                try {
                    numContrato = Long.parseLong(tfNumContrato.getText());
                    name = tfNameEmp.getText();

                } catch (InputMismatchException ex) {
                    JOptionPane.showMessageDialog(null, "Input error; make sure values are in the correct format", "Input error", JOptionPane.ERROR_MESSAGE);
                    varControle = false;

                } catch (NumberFormatException ex) {
                    JOptionPane.showMessageDialog(null, "Input error; certifique-se que os inputs estÃƒÂ¯Ã‚Â¿Ã‚Â½o nos formatos corretos", "Input error", JOptionPane.ERROR_MESSAGE);
                    varControle = false;

                } finally {
                    if (varControle) {
                        companyController.insert(numContrato, name);
                        companyController.closeScreen();
                    } else {
                        System.out.println("Error (debug)");
                    }
                }

                //clear field values after saving
                tfNameEmp.setText("");
                tfNumContrato.setText("");

//                //print only for debugging
//                companyController.printar();
            } else if (e.getActionCommand().equals(btCancel.getActionCommand())) {
                new TelaCancel().setVisible(true);
            }

        }

        private class TelaCancel extends JFrame {

            /**
             *
             */
            private static final long serialVersionUID = 1L;
            private JLabel mensagem;
            private JButton Yes;
            private JButton No;

            public TelaCancel() {

                Container container = getContentPane();
                container.setLayout(new FlowLayout());
                setSize(300, 125);

                setLocationRelativeTo(null);

                mensagem = new JLabel("Are you sure you want to cancel the registration?");

                Yes = new JButton("Yes");
                No = new JButton("No");

                Yes.addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        companyController.closeScreen();
                        setVisible(false);
                    }
                });

                No.addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        setVisible(false);
                    }
                });

                container.add(mensagem);
                container.add(Yes);
                container.add(No);

            }

        }
    }
}




