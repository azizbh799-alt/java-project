package presentation;

import persistence.TechnicianDAO;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import controller.TicketController;
import entity.Ticket;
import entity.TicketLog;
import entity.Status;
import entity.Technician;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.util.InputMismatchException;
import java.util.Iterator;
import javax.swing.JComboBox;

public class TicketTrackingScreen extends JFrame {

    private static final long serialVersionUID = 1L;
    private TicketController ticketController;

    private JLabel lCodCham;
    private JTextField tfCodCham;
    private JTextArea displayCham;
    private JButton btSave;
    private JButton btCancel;
    private Container container;
    private EventManager eventManager;

    private JButton btSearch;
    private JLabel lbTitle;
    private JTextField tfTitle;

    private JLabel lbStatus;
    private JComboBox cbStatus;

    private JLabel lbCause;
    private JTextField tfCause;
    private JLabel lbSolution;
    private JTextField tfSolution;
    private JLabel lbSubject;
    private JTextField tfSubject;

    private JLabel lbTecnico;
    private JComboBox cbTecnico;

    private TechnicianDAO tecnicoDAO;

    public TicketTrackingScreen(TicketController ctr) {

        super("Update or close Ticket");
        this.ticketController = ctr;
        container = getContentPane();
        container.setLayout(new GridBagLayout());
        setSize(500, 400);
        setLocationRelativeTo(null);
        eventManager = new EventManager();
        this.tecnicoDAO = new TechnicianDAO();

        initialize();

    }

    private void initialize() {

        GridBagConstraints cts = new GridBagConstraints();

        lCodCham = new JLabel("Ticket code: ");
        cts.fill = GridBagConstraints.HORIZONTAL;
        cts.gridx = 0;
        cts.gridy = 0;
        container.add(lCodCham, cts);

        tfCodCham = new JTextField();
        tfCodCham.setPreferredSize(new Dimension(150, 20));
        cts.fill = GridBagConstraints.HORIZONTAL;
        cts.gridx = 1;
        cts.gridy = 0;
        container.add(tfCodCham, cts);

        lbTitle = new JLabel("Title: ");
        lbTitle.setVisible(false);
        cts.gridx = 0;
        cts.gridy = 1;
        container.add(lbTitle, cts);

        tfTitle = new JTextField();
        cts.fill = GridBagConstraints.HORIZONTAL;
        tfTitle.setVisible(false);
        cts.gridx = 1;
        cts.gridy = 1;
        container.add(tfTitle, cts);

        lbStatus = new JLabel("Status: ");
        lbStatus.setVisible(false);
        cts.gridx = 0;
        cts.gridy = 2;
        container.add(lbStatus, cts);

        cbStatus = new JComboBox();
        cbStatus.addItem("In service");
        cbStatus.addItem("Waiting for user response");
        cbStatus.addItem("Closed");
        cbStatus.addItem("No solution");
        cbStatus.addActionListener(eventManager);
        cbStatus.setVisible(false);
        cts.gridx = 1;
        cts.gridy = 2;
        container.add(cbStatus, cts);

        lbTecnico = new JLabel("Technician: ");
        lbTecnico.setVisible(false);
        cts.gridx = 0;
        cts.gridy = 3;
        container.add(lbTecnico, cts);

        cbTecnico = new JComboBox();
        cbTecnico.setPreferredSize(new Dimension(150, 20));
        Iterator iterator = tecnicoDAO.getTechnicianCache().keySet().iterator();
        while (iterator.hasNext()) {
            this.cbTecnico.addItem(tecnicoDAO.getTechnicianCache().get(iterator.next()));
        }
        cbTecnico.setVisible(false);
        cts.fill = GridBagConstraints.HORIZONTAL;
        cts.gridx = 1;
        cts.gridy = 3;
        container.add(cbTecnico, cts);

        lbSubject = new JLabel("Handled subject:");
        lbSubject.setVisible(false);
        cts.gridx = 0;
        cts.gridy = 4;
        container.add(lbSubject, cts);

        tfSubject = new JTextField();
        tfSubject.setVisible(false);
        cts.gridx = 1;
        cts.gridy = 4;
        container.add(tfSubject, cts);

        lbCause = new JLabel("Cause: ");
        lbCause.setVisible(false);
        cts.gridx = 0;
        cts.gridy = 5;
        container.add(lbCause, cts);

        tfCause = new JTextField();
        tfCause.setVisible(false);
        cts.gridx = 1;
        cts.gridy = 5;
        container.add(tfCause, cts);

        lbSolution = new JLabel("Solution: ");
        lbSolution.setVisible(false);
        cts.gridx = 0;
        cts.gridy = 6;
        container.add(lbSolution, cts);

        tfSolution = new JTextField();
        tfSolution.setVisible(false);
        cts.gridx = 1;
        cts.gridy = 6;
        container.add(tfSolution, cts);

        btSearch = new JButton("Search");
        btSearch.setActionCommand(btSearch.getText());
        btSearch.addActionListener(eventManager);
        cts.fill = GridBagConstraints.HORIZONTAL;
        cts.gridx = 0;
        cts.gridy = 7;
        container.add(btSearch, cts);

        //save button, instantiate and add
        btSave = new JButton("Save");
        btSave.setActionCommand(btSave.getText());
        btSave.addActionListener(eventManager);
        btSave.setVisible(false);
        cts.fill = GridBagConstraints.HORIZONTAL;
        cts.gridx = 0;
        cts.gridy = 7;
        container.add(btSave, cts);

        //cancel button, instantiate and add
        btCancel = new JButton("Cancel");
        btCancel.setActionCommand(btCancel.getText());
        btCancel.addActionListener(eventManager);
        cts.fill = GridBagConstraints.HORIZONTAL;
        cts.gridx = 1;
        cts.gridy = 7;
        container.add(btCancel, cts);
    }

    private class EventManager implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {
            if (e.getActionCommand().equals(btSave.getActionCommand())) {
            	try{
                Ticket c = ticketController.getTicketByCode(Integer.parseInt(tfCodCham.getText()));
                ticketController.updateTicket(c, (String) cbStatus.getSelectedItem(),
                        tfCause.getText(), tfSolution.getText());
                TicketLog reg = ticketController.addTicketLog(tfSubject.getText(), c, (Technician) cbTecnico.getSelectedItem());
                ticketController.closeUpdateScreen();
                System.err.println("Status: " + c.getStatus() + " Cause: " + c.getCausaProblema()
                        + "Solution: " + c.getSolucaoProblema());
                System.err.println("Subject: " + reg.getAssunto() + "Technician: " + reg.getTecnico().getNome()
                        + " Ticket code: " + reg.getChamado().getCodigo());
            	}catch (NumberFormatException | InputMismatchException ex) {
                    JOptionPane.showMessageDialog(null, "Input error; make sure values are in the correct format", "Input error", JOptionPane.ERROR_MESSAGE);
            	}   

            } else if (e.getActionCommand().equals(btCancel.getActionCommand())) {
                new TelaCancel().setVisible(true);
            } else if (e.getActionCommand().equals(btSearch.getActionCommand())) {
            	Ticket c = null;
            	try{
            		c = ticketController.getTicketByCode(Integer.parseInt(tfCodCham.getText()));
            	}catch (NumberFormatException | InputMismatchException ex) {
                    JOptionPane.showMessageDialog(null, "Input error; make sure values are in the correct format", "Input error", JOptionPane.ERROR_MESSAGE);
            	}
                if (c != null) {
                    btSearch.setVisible(false);
                    btSave.setVisible(true);
                    lbTitle.setVisible(true);
                    tfTitle.setVisible(true);
                    tfTitle.setText("title: " + c.getTitulo());
                    lbTecnico.setVisible(true);
                    cbTecnico.setVisible(true);
                    lbStatus.setVisible(true);
                    cbStatus.setVisible(true);
                    lbSubject.setVisible(true);
                    tfSubject.setVisible(true);

                } else {
                    JOptionPane.showMessageDialog(null, "Ticket with this code does not exist!");
                }

            } else if (cbStatus.getSelectedItem().equals("No solution")
                    || cbStatus.getSelectedItem().equals("Closed")) {
                lbCause.setVisible(true);
                tfCause.setVisible(true);
                lbSolution.setVisible(true);
                tfSolution.setVisible(true);
            } else if (cbStatus.getSelectedItem().equals("In service")
                    || cbStatus.getSelectedItem().equals("Waiting for user response")) {
                lbCause.setVisible(false);
                tfCause.setVisible(false);
                lbSolution.setVisible(false);
                tfSolution.setVisible(false);
            }

//        @Override
//        public void focusGained(FocusEvent arg0) {
//            // TODO Auto-generated method stub			
//        }
//        @Override
//        public void focusLost(FocusEvent e) {
//            JTextField fonte = (JTextField) e.getSource(); //source cast	
//            int code = Integer.MIN_VALUE; //must set a value to satisfy Java
//            try {
//                code = Integer.parseInt(fonte.getText());
//            } catch (NumberFormatException ex) {
//            };
//
//            if (code != Integer.MIN_VALUE) {
//                try {
//                    ticketController.setChamadoAlterado(ticketController.findByCode(code));
//                    displayCham.setText(ticketController.getTicketDetails(ticketController.getChamadoAlterado()));
//                } catch (NullPointerException ex) {
//                    JOptionPane.showMessageDialog(null, "Ticket not found");
//                }
//            }
//        }
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
                    ticketController.closeUpdateScreen();
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




