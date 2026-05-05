/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package presentation;

import controller.TechnicianController;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.InputMismatchException;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;

/**
 *
 * @author Richard
 */
public class TechnicianRegistrationScreen extends JFrame {

    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private TechnicianController technicianController;
    private Container container;
    private EventManager eventManager;

    private JButton btSave;
    private JButton btCancel;

    private JLabel lbName;
    private JLabel lbPhone;

    private JTextField tfName;
    private JTextField tfPhone;

    public TechnicianRegistrationScreen(TechnicianController ctr) {
        super("Technician Registration");

        this.eventManager = new EventManager();
        this.technicianController = ctr;
        setLocationRelativeTo(null);
        setSize(400, 300);
        this.container = getContentPane();
        initialize();
    }

    private void initialize() {

        container.setLayout(new GridBagLayout());

        GridBagConstraints cts = new GridBagConstraints();

        lbName = new JLabel("Technician name: ");
        cts.fill = GridBagConstraints.HORIZONTAL;
        cts.gridx = 0;
        cts.gridy = 0;
        container.add(lbName, cts);

        tfName = new JTextField();
        cts.fill = GridBagConstraints.HORIZONTAL;
        cts.gridx = 1;
        cts.gridy = 0;
        container.add(tfName, cts);

        lbPhone = new JLabel("Phone: ");
        cts.fill = GridBagConstraints.HORIZONTAL;
        cts.gridx = 0;
        cts.gridy = 1;
        container.add(lbPhone, cts);
        tfPhone = new JTextField();
        tfPhone.setPreferredSize(new Dimension(150, 20));
        cts.fill = GridBagConstraints.HORIZONTAL;
        cts.gridx = 1;
        cts.gridy = 1;
        container.add(tfPhone, cts);

        btSave = new JButton("Save");
        btSave.setPreferredSize(new Dimension(150, 20));
        btSave.setActionCommand(btSave.getText());
        btSave.addActionListener(eventManager);
        cts.fill = GridBagConstraints.HORIZONTAL;
        cts.gridx = 0;
        cts.gridy = 2;
        container.add(btSave, cts);

        btCancel = new JButton("Cancel");
        btCancel.setPreferredSize(new Dimension(150, 20));
        btCancel.setActionCommand(btCancel.getText());
        btCancel.addActionListener(eventManager);
        cts.fill = GridBagConstraints.HORIZONTAL;
        cts.gridx = 1;
        cts.gridy = 2;
        container.add(btCancel, cts);

    }

    private void clearScreen() {

        tfName.setText("");
        tfPhone.setText("");
    }

    private class EventManager implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {
            if (e.getActionCommand().equals(btSave.getActionCommand())) {

                boolean varControle = true;

                Long phone = new Long(0); //como nao existe phone 0, nenhum input vai sobrescrever esse valor
                String name = "";

                try {
                    phone = Long.parseLong(tfPhone.getText());
                    name = tfName.getText();
                } catch (NumberFormatException | InputMismatchException ex) {
                    JOptionPane.showMessageDialog(null, "Input error; make sure values are in the correct format", "Input error", JOptionPane.ERROR_MESSAGE);
                    varControle = false;
                } finally {
                    if (varControle) {
                        technicianController.insert(phone, name);
                        clearScreen();
                        technicianController.closeTechnicianScreen();
                    }
                }

            } else if (e.getActionCommand().equals(btCancel.getActionCommand())) {
                new TelaCancel().setVisible(true);
            }
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
                    technicianController.closeTechnicianScreen();
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



