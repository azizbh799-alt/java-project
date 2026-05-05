/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import persistence.CustomerDAO;
import presentation.CustomerRegistrationScreen;
import entity.CompanyCustomer;
import entity.Company;

/**
 *
 * @author Richard
 */
public class CustomerController {

    private CustomerRegistrationScreen customerScreen;
    private CustomerDAO customerDAO;

    public CustomerController() {
        this.customerDAO = new CustomerDAO();
         
    }

    public CustomerDAO getClienteDAO() {
        return customerDAO;
    }

    public void registerCustomer() {
        
        this.customerScreen = new CustomerRegistrationScreen(this);
        customerScreen.setVisible(true);
    }

    public CompanyCustomer addNewCustomer(Company company, long cpf, String name, long phone) {

        CompanyCustomer customer = new CompanyCustomer(1, company, cpf, name, phone);
        customerDAO.put(customer);
        return customer;
    }

//    public CompanyCustomer insert(Company company, long cpf, String name, long phone) {
//        CompanyCustomer customer = new CompanyCustomer(company, cpf, name, phone);
//        customers.add(customer);
//        return customer;
//    }
//    //valida o a company e o customer company informado na time de comeÃƒÆ’Ã‚Â§ar a inserÃƒÆ’Ã‚Â§ÃƒÆ’Ã‚Â£o de um ticket
//    public boolean validarCliente(int nmrContrato, String companyName, long cpf, String nomeUsuario, long phone) {
//
//        boolean result = false;
//        for (CompanyCustomer customerCompany : customers) {
//            if (customerCompany.getCompany().getNumeroContrato() == nmrContrato
//                    && customerCompany.getCompany().getNomeCompany().endsWith(companyName)
//                    && customerCompany.getCpf() == cpf
//                    && customerCompany.getNome().equals(nomeUsuario) && customerCompany.getTelefone() == phone) {
//                result = true;
//            }
//        }
//        return result;
//    }
//
//    public CompanyCustomer retornaCliente(long nmrContrato, String companyName, long cpf, String nomeUsuario, long phone) {
//
//        CompanyCustomer result = null;
//        for (CompanyCustomer customerCompany : customers) {
//            if (customerCompany.getCompany().getNumeroContrato() == nmrContrato
//                    && customerCompany.getCompany().getNomeCompany().equals(companyName)
//                    && customerCompany.getCpf() == cpf
//                    && customerCompany.getNome().equals(nomeUsuario) && customerCompany.getTelefone() == phone) {
//                return customerCompany;
//            }
//
//        }
//        return result;
//    }
    public void closeCustomerScreen() {
        this.customerScreen.setVisible(false);
    }

}



