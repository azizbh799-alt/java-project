/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package presentation;

import persistence.CustomerDAO;
import persistence.TechnicianDAO;
import controller.TicketController;
import controller.CustomerController;
import entity.Ticket;
import entity.CompanyCustomer;
import entity.Technician;
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
public class TicketRegistrationScreen extends JFrame {

    private Container container;
    private TicketController ticketController;
    private EventManager eventManager;
    private CustomerController customerController;

    private JLabel lbTecnico;
    private JComboBox cbTecnico;

    private JLabel lbClienteCompany;
    private JComboBox cbCliente;

    private JLabel lbProblem;
    private JComboBox cbProblem;

    private JLabel lbProblemPerformance;
    private JTextField tfProblemaPerformance;
    private JLabel lbDuration;
    private JTextField tfDuration;

    private JLabel lbTitle;
    private JTextField tfTitle;
    private JLabel lbDescription;
    private JTextField tfDescription;
    private JLabel lbPriority;
    private JComboBox<String> cbPriority;

    private JLabel lbOS;
    private JComboBox cbOS;
    private JLabel lbOSVersion;
    private JTextField tfOSVersion;

    private JLabel lbDatabase;
    private JComboBox cbDatabase;

    private JLabel lbNetwork;
    private JComboBox cbNetwork;
    private JLabel lbEnderecoNetwork;
    private JTextField tfEnderecoNetwork;

    private JButton btSave;
    private JButton btCancel;

    private TechnicianDAO tecnicoDAO;
    private CustomerDAO customerDAO;

    public TicketRegistrationScreen(TicketController ctr) {

        super("Ticket Registration!");

        customerController = new CustomerController();

        this.tecnicoDAO = new TechnicianDAO();
        this.customerDAO = new CustomerDAO();

        setSize(800, 400);
        setLocationRelativeTo(null);
        this.container = getContentPane();
        this.ticketController = ctr;
        this.eventManager = new EventManager();

        initialize();
    }

    private void initialize() {

        container.setLayout(new GridBagLayout());
        GridBagConstraints cts = new GridBagConstraints();

        lbTecnico = new JLabel("Technician: ");
        cts.fill = GridBagConstraints.HORIZONTAL;
        cts.gridx = 0;
        cts.gridy = 0;
        container.add(lbTecnico, cts);

        cbTecnico = new JComboBox();
        cbTecnico.setPreferredSize(new Dimension(150, 20));
        Iterator iterator = tecnicoDAO.getTechnicianCache().keySet().iterator();
        while (iterator.hasNext()) {
            this.cbTecnico.addItem(tecnicoDAO.getTechnicianCache().get(iterator.next()));
        }
        cts.fill = GridBagConstraints.HORIZONTAL;
        cts.gridx = 1;
        cts.gridy = 0;
        container.add(cbTecnico, cts);

        lbClienteCompany = new JLabel("Customer: ");
        cts.fill = GridBagConstraints.HORIZONTAL;
        cts.gridx = 0;
        cts.gridy = 1;
        container.add(lbClienteCompany, cts);

        cbCliente = new JComboBox();
        cbCliente.setPreferredSize(new Dimension(400, 20));
        Iterator iterator1 = customerDAO.getCustomerCache().keySet().iterator();
        while (iterator1.hasNext()) {
            this.cbCliente.addItem(customerDAO.getCustomerCache().get(iterator1.next()));
        }
        cts.gridx = 1;
        cts.gridy = 1;
        container.add(cbCliente, cts);

        lbProblem = new JLabel("Choose the problem: ");
        cts.fill = GridBagConstraints.HORIZONTAL;
        cts.gridx = 0;
        cts.gridy = 2;
        container.add(lbProblem, cts);

        cbProblem = new JComboBox();
        cbProblem.addItem("Network");
        cbProblem.addItem("Database");
        cbProblem.addItem("Performance");
        cbProblem.addActionListener(eventManager);
        cts.fill = GridBagConstraints.HORIZONTAL;
        cts.gridx = 1;
        cts.gridy = 2;
        container.add(cbProblem, cts);

        lbNetwork = new JLabel("Connection type: ");
        cts.gridx = 0;
        cts.gridy = 3;
        container.add(lbNetwork, cts);

        cbNetwork = new JComboBox();
        cbNetwork.addItem("ADSL");
        cbNetwork.addItem("Radio");
        cbNetwork.addItem("Cable Modem");
        cbNetwork.addItem("Other");
        cts.gridx = 1;
        cts.gridy = 3;
        container.add(cbNetwork, cts);

        lbEnderecoNetwork = new JLabel("Network Address: ");
        cts.gridx = 0;
        cts.gridy = 4;
        container.add(lbEnderecoNetwork, cts);

        tfEnderecoNetwork = new JTextField();
        tfEnderecoNetwork.setPreferredSize(new Dimension(150, 20));
        cts.gridx = 1;
        cts.gridy = 4;
        container.add(tfEnderecoNetwork, cts);

        lbDatabase = new JLabel("Database type");
        lbDatabase.setVisible(false);
        cts.fill = GridBagConstraints.HORIZONTAL;
        cts.gridx = 0;
        cts.gridy = 3;
        container.add(lbDatabase, cts);

        cbDatabase = new JComboBox();
        cbDatabase.addItem("SQLServer");
        cbDatabase.addItem("Oracle");
        cbDatabase.addItem("MySql");
        cbDatabase.setVisible(false);
        cts.fill = GridBagConstraints.HORIZONTAL;
        cts.gridx = 1;
        cts.gridy = 3;
        container.add(cbDatabase, cts);

        lbProblemPerformance = new JLabel("Describe the operation: ");
        lbProblemPerformance.setVisible(false);
        cts.gridx = 0;
        cts.gridy = 3;
        container.add(lbProblemPerformance, cts);

        tfProblemaPerformance = new JTextField();
        tfProblemaPerformance.setVisible(false);
        cts.gridx = 1;
        cts.gridy = 3;
        container.add(tfProblemaPerformance, cts);

        lbDuration = new JLabel("Duration: ");
        lbDuration.setVisible(false);
        cts.gridx = 0;
        cts.gridy = 4;
        container.add(lbDuration, cts);

        tfDuration = new JTextField();
        tfDuration.setPreferredSize(
                new Dimension(150, 20));
        tfDuration.setVisible(false);
        cts.fill = GridBagConstraints.HORIZONTAL;
        cts.gridx = 1;
        cts.gridy = 4;
        container.add(tfDuration, cts);

        lbTitle = new JLabel("Title: ");
        cts.fill = GridBagConstraints.HORIZONTAL;
        cts.gridx = 0;
        cts.gridy = 5;
        container.add(lbTitle, cts);

        tfTitle = new JTextField();
        tfTitle.setPreferredSize(
                new Dimension(300, 20));
        cts.fill = GridBagConstraints.HORIZONTAL;
        cts.gridx = 1;
        cts.gridy = 5;
        container.add(tfTitle, cts);

        lbDescription = new JLabel("Description: ");
        cts.fill = GridBagConstraints.HORIZONTAL;
        cts.gridx = 0;
        cts.gridy = 6;
        container.add(lbDescription, cts);

        tfDescription = new JTextField();
        tfDescription.setPreferredSize(
                new Dimension(300, 20));
        cts.fill = GridBagConstraints.HORIZONTAL;
        cts.gridx = 1;
        cts.gridy = 6;
        container.add(tfDescription, cts);

        lbPriority = new JLabel("Priority: ");
        cts.fill = GridBagConstraints.HORIZONTAL;
        cts.gridx = 0;
        cts.gridy = 7;
        container.add(lbPriority, cts);

        cbPriority = new JComboBox<String>();
        cbPriority.setPreferredSize(new Dimension(150, 20));
        cbPriority.addItem("Normal");
        cbPriority.addItem("Important");
        cbPriority.addItem("Urgent");
        cbPriority.addItem("CrÃƒÂ­tica");        
        cbPriority.setPreferredSize(new Dimension(150, 20));
        cts.fill = GridBagConstraints.HORIZONTAL;
        cts.gridx = 1;
        cts.gridy = 7;
        container.add(cbPriority, cts);

        lbOS = new JLabel("Operating System: ");
        cts.gridx = 0;
        cts.gridy = 8;
        container.add(lbOS, cts);

        cbOS = new JComboBox();
        cbOS.addItem(
                "Windows");
        cbOS.addItem(
                "Linux");
        cts.gridx = 1;
        cts.gridy = 8;
        container.add(cbOS, cts);
        lbOSVersion = new JLabel("OS Version: ");
        cts.gridx = 0;
        cts.gridy = 9;

        container.add(lbOSVersion, cts);
        tfOSVersion = new JTextField();
        tfOSVersion.setPreferredSize(
                new Dimension(150, 20));
        cts.gridx = 1;
        cts.gridy = 9;
        container.add(tfOSVersion, cts);

        btSave = new JButton("Save");
        cts.fill = GridBagConstraints.HORIZONTAL;
        btSave.setActionCommand(btSave.getText());
        btSave.addActionListener(eventManager);
        cts.gridx = 0;
        cts.gridy = 10;

        container.add(btSave, cts);
        btCancel = new JButton("Cancel");
        cts.fill = GridBagConstraints.HORIZONTAL;
        btCancel.setActionCommand(btCancel.getText());
        btCancel.addActionListener(eventManager);
        cts.gridx = 1;
        cts.gridy = 10;
        container.add(btCancel, cts);

    }

    private class EventManager implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {
            if (e.getActionCommand().equals(btSave.getActionCommand())) {
                
                if (cbProblem.getSelectedItem().equals("Network")) {
                	
                	String title = "";
                	String description = "";
                	Technician t = null;
                	CompanyCustomer CE = null;
                	String SO = "";
                	String verSO = "";
                	String tipoNetwork = "";
                	String endNetwork = "";
                	
                	int priority = 0; 
                	
                	if(cbPriority.getSelectedItem().equals("Normal")){
                		priority = 1;
                	}else if(cbPriority.getSelectedItem().equals("Important")){
                		priority = 2;
                	}else if(cbPriority.getSelectedItem().equals("Urgent")){
                		priority = 3;
                	}else if(cbPriority.getSelectedItem().equals("CrÃƒÂ­tica")){
                		priority = 4;
                	}
                	
                    try{
                    	title = tfTitle.getText();
                    	description = tfDescription.getText();
                    	t = (Technician) cbTecnico.getSelectedItem();
                    	CE = (CompanyCustomer) cbCliente.getSelectedItem();
                    	SO = (String) cbOS.getSelectedItem();
                    	verSO = tfOSVersion.getText();
                    	tipoNetwork = (String) cbNetwork.getSelectedItem();
                    	endNetwork = tfEnderecoNetwork.getText();
                    	
                    	Ticket c = ticketController.addNetworkTicket(title, description, priority, t, CE, SO, verSO, tipoNetwork, endNetwork);
                    	JOptionPane.showMessageDialog(null, "Created ticket code: " + c.getCodigo());
                    	ticketController.closeScreen();
                    	
                    }catch(NumberFormatException | InputMismatchException ex){
                		JOptionPane.showMessageDialog(null, "Input error; make sure values are in the correct format", "Input error", JOptionPane.ERROR_MESSAGE);
                	}
                } else if (cbProblem.getSelectedItem().equals("Performance")) {	
                	
                	
                	int priority = 0; 
                	
                	if(cbPriority.getSelectedItem().equals("Normal")){
                		priority = 1;
                	}else if(cbPriority.getSelectedItem().equals("Important")){
                		priority = 2;
                	}else if(cbPriority.getSelectedItem().equals("Urgent")){
                		priority = 3;
                	}else if(cbPriority.getSelectedItem().equals("CrÃƒÂ­tica")){
                		priority = 4;
                	}
                	
                    try{
                    Ticket c = ticketController.addPerformanceTicket(tfTitle.getText(), tfDescription.getText(), priority,
                            (Technician) cbTecnico.getSelectedItem(), (CompanyCustomer) cbCliente.getSelectedItem(), (String) cbOS.getSelectedItem(),
                            tfOSVersion.getText(), tfProblemaPerformance.getText(), Integer.parseInt(tfDuration.getText()));
                    JOptionPane.showMessageDialog(null, "Created ticket code: " + c.getCodigo());
                    ticketController.closeScreen();
                    }catch(NumberFormatException | InputMismatchException ex){
                		JOptionPane.showMessageDialog(null, "Input error; make sure values are in the correct format", "Input error", JOptionPane.ERROR_MESSAGE);
                	} 
                } else if (cbProblem.getSelectedItem().equals("Database")) {
                	
                	int priority = 0; 
                	
                	if(cbPriority.getSelectedItem().equals("Normal")){
                		priority = 1;
                	}else if(cbPriority.getSelectedItem().equals("Important")){
                		priority = 2;
                	}else if(cbPriority.getSelectedItem().equals("Urgent")){
                		priority = 3;
                	}else if(cbPriority.getSelectedItem().equals("CrÃƒÂ­tica")){
                		priority = 4;
                	}
                	
                    try{
                    Ticket c = ticketController.addDatabaseTicket(tfTitle.getText(), tfDescription.getText(), priority,
                            (Technician) cbTecnico.getSelectedItem(), (CompanyCustomer) cbCliente.getSelectedItem(), (String) cbOS.getSelectedItem(),
                            tfOSVersion.getText(), (String) cbDatabase.getSelectedItem());
                    		ticketController.closeScreen();
                    		JOptionPane.showMessageDialog(null, "Created ticket code: " + c.getCodigo());
                    		
                    }catch(NumberFormatException | InputMismatchException ex){
                		JOptionPane.showMessageDialog(null, "Input error; make sure values are in the correct format", "Input error", JOptionPane.ERROR_MESSAGE);
                	}
                }

            } else if (e.getActionCommand().equals(btCancel.getActionCommand())) {
                new TelaCancel().setVisible(true);
            } else if (cbProblem.getSelectedItem().equals("Network")) {

                lbNetwork.setVisible(true);
                tfEnderecoNetwork.setVisible(true);
                cbNetwork.setVisible(true);
                lbEnderecoNetwork.setVisible(true);

                lbDatabase.setVisible(false);
                cbDatabase.setVisible(false);
                lbDuration.setVisible(false);
                tfDuration.setVisible(false);
                lbProblemPerformance.setVisible(false);
                tfProblemaPerformance.setVisible(false);

            } else if (cbProblem.getSelectedItem().equals("Performance")) {

                lbDuration.setVisible(true);
                tfDuration.setVisible(true);
                lbProblemPerformance.setVisible(true);
                tfProblemaPerformance.setVisible(true);

                lbDatabase.setVisible(false);
                cbDatabase.setVisible(false);
                lbNetwork.setVisible(false);
                tfEnderecoNetwork.setVisible(false);
                cbNetwork.setVisible(false);
                lbEnderecoNetwork.setVisible(false);
            } else if (cbProblem.getSelectedItem().equals("Database")) {

                lbDatabase.setVisible(true);
                cbDatabase.setVisible(true);

                lbDuration.setVisible(false);
                tfDuration.setVisible(false);
                lbProblemPerformance.setVisible(false);
                tfProblemaPerformance.setVisible(false);
                lbNetwork.setVisible(false);
                tfEnderecoNetwork.setVisible(false);
                cbNetwork.setVisible(false);
                lbEnderecoNetwork.setVisible(false);

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
                    ticketController.closeScreen();
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




