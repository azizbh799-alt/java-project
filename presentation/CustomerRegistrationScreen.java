/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package presentation;

import persistence.CompanyDAO;
import controller.CustomerController;
import entity.CompanyCustomer;
import entity.Company;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.InputMismatchException;
import java.util.Iterator;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;

/**
 *
 * @author Richard
 */
public class CustomerRegistrationScreen extends JFrame {

    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private CustomerController ctrCliente;
    private CompanyDAO empresaDAO;
    private Container container;
    private EventManager eventManager;

    private JLabel lbCompany;
    private JComboBox cbCompanys;

    private JLabel lbCpf;
    private JTextField tfCpf;
    private JLabel lbName;
    private JTextField tfName;
    private JLabel lbPhone;
    private JTextField tfPhone;

    private JButton btSave;
    private JButton btCancel;

    public CustomerRegistrationScreen(CustomerController ctr) {
        super("Customer Registration");
        this.eventManager = new EventManager();
        this.empresaDAO = new CompanyDAO();
        this.ctrCliente = ctr;
        this.container = getContentPane();

        setLocationRelativeTo(null);
        setSize(400, 300);

        initialize();
    }

    private void initialize() {

        container.setLayout(new GridBagLayout());

        GridBagConstraints cts = new GridBagConstraints();

        lbCompany = new JLabel("Companies: ");
        cts.fill = GridBagConstraints.HORIZONTAL;
        cts.gridx = 0;
        cts.gridy = 0;
        container.add(lbCompany, cts);

        cbCompanys = new JComboBox();
        cbCompanys.setPreferredSize(new Dimension(150, 20));
        Iterator iterator = empresaDAO.getCompanyCache().keySet().iterator();
        while (iterator.hasNext()) {
            this.cbCompanys.addItem(empresaDAO.getCompanyCache().get(iterator.next()));
        }
        cts.fill = GridBagConstraints.HORIZONTAL;
        cts.gridx = 1;
        cts.gridy = 0;
        container.add(cbCompanys, cts);

        lbCpf = new JLabel("CPF: ");
        cts.fill = GridBagConstraints.HORIZONTAL;
        cts.gridx = 0;
        cts.gridy = 1;
        container.add(lbCpf, cts);
        tfCpf = new JTextField();
        tfCpf.setPreferredSize(new Dimension(150, 20));
        cts.fill = GridBagConstraints.HORIZONTAL;
        cts.gridx = 1;
        cts.gridy = 1;
        container.add(tfCpf, cts);

        lbName = new JLabel("Name: ");
        cts.fill = GridBagConstraints.HORIZONTAL;
        cts.gridx = 0;
        cts.gridy = 2;
        container.add(lbName, cts);

        tfName = new JTextField();
        tfName.setPreferredSize(new Dimension(150, 20));
        cts.fill = GridBagConstraints.HORIZONTAL;
        cts.gridx = 1;
        cts.gridy = 2;
        container.add(tfName, cts);

        lbPhone = new JLabel("Phone: ");
        cts.fill = GridBagConstraints.HORIZONTAL;
        cts.gridx = 0;
        cts.gridy = 3;
        container.add(lbPhone, cts);

        tfPhone = new JTextField();
        tfPhone.setPreferredSize(new Dimension(150, 20));
        cts.fill = GridBagConstraints.HORIZONTAL;
        cts.gridx = 1;
        cts.gridy = 3;
        container.add(tfPhone, cts);

        btSave = new JButton("Save");
        btSave.setPreferredSize(new Dimension(150, 20));
        btSave.setActionCommand(btSave.getText());
        btSave.addActionListener(eventManager);
        cts.fill = GridBagConstraints.HORIZONTAL;
        cts.gridx = 0;
        cts.gridy = 4;
        container.add(btSave, cts);

        btCancel = new JButton("Cancel");
        btCancel.setPreferredSize(new Dimension(150, 20));
        btCancel.setActionCommand(btCancel.getText());
        btCancel.addActionListener(eventManager);
        cts.fill = GridBagConstraints.HORIZONTAL;
        cts.gridx = 1;
        cts.gridy = 4;
        container.add(btCancel, cts);

    }

    private void clearScreen() {
        tfCpf.setText("");
        tfName.setText("");
        tfPhone.setText("");
    }

    private class EventManager implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {
            if (e.getActionCommand().equals(btSave.getActionCommand())) {

                boolean varControle = true;

                Long CPFvalido = new Long(-1);
                try {
                    CPFvalido = Long.parseLong(tfCpf.getText());
                } catch (NumberFormatException | InputMismatchException ex) {
                    JOptionPane.showMessageDialog(null, "Input error in CPF field; use numbers only", "Input error", JOptionPane.ERROR_MESSAGE);
                }

                boolean validaCPF = true;

            	//tem q fazer dentro de um if, se fosse fora esse metodo iria executar
                //mesmo depois do catch, com um valor erroneo, e sempre daria errado
                if (CPFvalido != -1) {
                    validaCPF = ctrCliente.getClienteDAO().validarCPF(CPFvalido);
                }

                if (validaCPF) {

                    Long cpf = new Long(0);
                    String name = "";
                    Long phone = new Long(0);
                    Company emp = null;

                    try {
                        emp = (Company) cbCompanys.getSelectedItem();
                        cpf = Long.parseLong(tfCpf.getText());
                        name = tfName.getText();
                        phone = Long.parseLong(tfPhone.getText());
                    } catch (NumberFormatException | InputMismatchException ex) {
                        JOptionPane.showMessageDialog(null, "Input error; make sure values are in the correct format", "Input error", JOptionPane.ERROR_MESSAGE);
                        varControle = false;
                    } finally {
                        if (varControle) {
                            CompanyCustomer ct = ctrCliente.addNewCustomer(emp, cpf, name, phone);
                            JOptionPane.showMessageDialog(null, "Customer successfully added, name: "
                                    + ct.getNome() + " - CPF: " + ct.getCpf() + " - Company: " + ct.getCompany());
                            clearScreen();
                            ctrCliente.closeCustomerScreen();
                        }
                    }

                } else {
                    JOptionPane.showMessageDialog(null, "CPF is already registered, CPF: " + tfCpf.getText());
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
                    ctrCliente.closeCustomerScreen();
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



