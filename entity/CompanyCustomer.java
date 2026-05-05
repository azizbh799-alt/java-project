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
public class CompanyCustomer extends Person implements Serializable {

    private static final long serialVersionUID = 1L;
    private Integer code;
    private Company company;
    private long cpf;

    public CompanyCustomer(Integer code, Company company, long cpf, String name, long phone) {
        super(name, phone);
        this.code = code;
        this.company = company;
        this.cpf = cpf;
    }

    public Integer getCodigo() {
        return code;
    }

    public void setCodigo(Integer code) {
        this.code = code;
    }

    public long getCpf() {
        return cpf;
    }

    public void setCpf(long cpf) {
        this.cpf = cpf;
    }

    public Company getCompany() {
        return company;
    }

    @Override
    public String toString() {
        return "Contract: " + company.getNumeroContrato()
                + " - Company Name:" + company.getNomeCompany() + " - Customer: "
                + getCpf() + " - " + getNome();
    }

}




