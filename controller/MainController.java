/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import presentation.MainScreen;

/**
 *
 * @author Richard
 */
public class MainController {

    private TicketController ticketController;
    private CustomerController customerController;
    private TechnicianController technicianController;
    private CompanyController companyController;
    private MainScreen mainScreen;

    public MainController() {
        this.mainScreen = new MainScreen(this);
        this.technicianController = new TechnicianController();
        this.ticketController = new TicketController();
        this.customerController = new CustomerController();
        this.companyController = new CompanyController();
    }
    
    public void start(){
        this.mainScreen.setVisible(true);
    }

    public CompanyController getCtrCompany() {
        return companyController;
    }

    public void setCtrCompany(CompanyController companyController) {
        this.companyController = companyController;
    }

    public TicketController getCtrChamados() {
        return ticketController;
    }

    public void setCtrChamados(TicketController ticketController) {
        this.ticketController = ticketController;
    }

    public CustomerController getCtrClientes() {
        return customerController;
    }

    public void setCtrClientes(CustomerController customerController) {
        this.customerController = customerController;
    }

    public TechnicianController getCtrTecnicos() {
        return technicianController;
    }

    public void setCtrTecnicos(TechnicianController technicianController) {
        this.technicianController = technicianController;
    }

}



