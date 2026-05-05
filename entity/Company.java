/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entity;

import java.io.Serializable;

/**
 *
 * @author Richard
 */
public class Company implements Serializable {

    private static final long serialVersionUID = 1L;
    private long contractNumber;
    private String companyName;

    public Company(long contractNumber, String companyName) {
        this.contractNumber = contractNumber;
        this.companyName = companyName;
    }

    public long getNumeroContrato() {
        return contractNumber;
    }

    public void setNumeroContrato(long contractNumber) {
        this.contractNumber = contractNumber;
    }

    public String getNomeCompany() {
        return companyName;
    }

    public void setNomeCompany(String companyName) {
        this.companyName = companyName;
    }

    @Override
    public String toString() {
        return contractNumber + " - " + companyName;
    }

}



