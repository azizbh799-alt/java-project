/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import persistence.TechnicianDAO;
import presentation.TechnicianRegistrationScreen;
import entity.Technician;

/**
 *
 * @author Richard
 */
public class TechnicianController implements IController {

    private TechnicianRegistrationScreen telaCadastroTecnico;
    private TechnicianDAO tecnicoDAO;

    public TechnicianController() {

        this.tecnicoDAO = new TechnicianDAO();

    }

    @Override
    public Technician insert(long n, String name) {
        Technician tec = new Technician(name, n);
        tecnicoDAO.put(tec);
        return tec;
    }

    public void registerTechnician() {
        this.telaCadastroTecnico = new TechnicianRegistrationScreen(this);
        this.telaCadastroTecnico.setVisible(true);
    }

    public void closeTechnicianScreen() {
        this.telaCadastroTecnico.setVisible(false);
    }

}


