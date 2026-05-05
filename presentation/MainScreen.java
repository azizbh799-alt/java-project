/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package presentation;

import controller.MainController;
import entity.Ticket;
import entity.CompanyCustomer;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.JFrame;

/**
 *
 * @author Richard
 */
public class MainScreen extends JFrame {

    private MainController controller;

    private Container container;

    private JButton btCompany;
    private JButton btCliente;
    private JButton btTecnico;
    private JButton btChamado;
    private JButton btAlterarChamado;
    private JButton btReport;
    private EventManager eventManager;

    public MainScreen(MainController ctr) {
        super("Ticket Management System - HelpDesk!");

        this.controller = ctr;
        this.eventManager = new EventManager();
        this.container = getContentPane();
        setSize(500, 300);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        initialize();
    }

    private void initialize() {

        container.setLayout(new GridBagLayout());

        GridBagConstraints cts = new GridBagConstraints();

        btCompany = new JButton("Register Company");
        btCompany.setPreferredSize(new Dimension(150, 20));
        btCompany.addActionListener(eventManager);
        btCompany.setActionCommand(btCompany.getText());
        cts.fill = GridBagConstraints.HORIZONTAL;
        cts.gridx = 0;
        cts.gridy = 1;
        container.add(btCompany, cts);

        btCliente = new JButton("Register Customer");
        btCliente.setPreferredSize(new Dimension(150, 20));
        btCliente.addActionListener(eventManager);
        btCliente.setActionCommand(btCliente.getText());
        cts.fill = GridBagConstraints.HORIZONTAL;
        cts.gridx = 0;
        cts.gridy = 2;
        container.add(btCliente, cts);

        btTecnico = new JButton("Register Technician");
        btTecnico.setPreferredSize(new Dimension(150, 20));
        btTecnico.addActionListener(eventManager);
        btTecnico.setActionCommand(btTecnico.getText());
        cts.fill = GridBagConstraints.HORIZONTAL;
        cts.gridx = 0;
        cts.gridy = 3;
        container.add(btTecnico, cts);

        btChamado = new JButton("Register Ticket");
        btChamado.setPreferredSize(new Dimension(150, 20));
        btChamado.addActionListener(eventManager);
        btChamado.setActionCommand(btChamado.getText());
        cts.fill = GridBagConstraints.HORIZONTAL;
        cts.gridx = 0;
        cts.gridy = 4;
        container.add(btChamado, cts);

        btAlterarChamado = new JButton("Update/Close Ticket");
        btAlterarChamado.setPreferredSize(new Dimension(200, 20));
        btAlterarChamado.addActionListener(eventManager);
        btAlterarChamado.setActionCommand(btAlterarChamado.getText());
        cts.fill = GridBagConstraints.HORIZONTAL;
        cts.gridx = 0;
        cts.gridy = 5;
        container.add(btAlterarChamado, cts);

        btReport = new JButton("Report");
        btReport.setPreferredSize(new Dimension(150, 20));
        btReport.addActionListener(eventManager);
        btReport.setActionCommand(btReport.getText());
        cts.fill = GridBagConstraints.HORIZONTAL;
        cts.gridx = 0;
        cts.gridy = 6;
        container.add(btReport, cts);

    }

    private class EventManager implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {
            //register company
            if (e.getActionCommand().equals(btCompany.getActionCommand())) {
                controller.getCtrCompany().registerCompany();
                //register customer
            } else if (e.getActionCommand().equals(btCliente.getActionCommand())) {
                controller.getCtrClientes().registerCustomer();
                //register technician
            } else if (e.getActionCommand().equals(btTecnico.getActionCommand())) {
                controller.getCtrTecnicos().registerTechnician();
            } //register ticket
            else if (e.getActionCommand().equals(btChamado.getActionCommand())) {
                controller.getCtrChamados().registerTicket();
            } //update ticket or close
            else if (e.getActionCommand().equals(btAlterarChamado.getActionCommand())) {
                controller.getCtrChamados().updateTicket();
            } else if (e.getActionCommand().equals(btReport.getActionCommand())) {
                controller.getCtrChamados().openReport();
            }
        }

    }

}




