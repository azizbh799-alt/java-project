/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import persistence.CompanyDAO;
import entity.Company;

import java.util.ArrayList;
import java.util.Collection;

import presentation.CompanyRegistrationScreen;
import javax.swing.JOptionPane;

public class CompanyController implements IController {

    private CompanyDAO mapeadorCompany;
    private CompanyRegistrationScreen telaCompany;
    private Collection<Company> companies;

    public CompanyController() {
        this.mapeadorCompany = new CompanyDAO();
        //fiz isso soh pra nao quebrar os metodos dessa classe por enquanto, sei q
        //nao deveria ficar aqui
        this.companies = mapeadorCompany.getCompanys();
        this.telaCompany = new CompanyRegistrationScreen(this);
    }

    public Company find(long nmr, String name) {
        Company result = null;
        for (Company company : companies) {
            if (company.getNomeCompany().equals(name) && company.getNumeroContrato() == nmr) {
                result = company;
            }
        }
        return result;
    }

        public int validate(long contrato, String name) {
    	
    	this.companies = mapeadorCompany.getCompanys();
    	
        int result = 0;
        
        for (Company company : companies) {
        	//1 o contrato e o name da company ja estao em uso
            if (company.getNumeroContrato() == contrato && company.getNomeCompany().equals(name)) {
                result = 1;
            } 
            //2 o name da company ja esta em uso
            else if (company.getNumeroContrato() != contrato && company.getNomeCompany().equals(name)) {
                result = 2;
            } 
            //3 o numero de contrato da company ja esta em uso
            else if(company.getNumeroContrato() == contrato && !company.getNomeCompany().equals(name)){
                result = 3;
            }
            //4 nenhum atributo da company esta em uso
            else if(company.getNumeroContrato() != contrato && !company.getNomeCompany().equals(name)){
            	result = 4;
            }
        }
        return result;
    }

    @Override
    public Company insert(long n, String name) {
    	
    	Long num = new Long(n);
    	boolean opcao = check(num, name);    	
    	this.companies = mapeadorCompany.getCompanys();
    	
    	if(!opcao){
	    	Company company = new Company(n, name);
	    	mapeadorCompany.put(company);
	    	return company;
    	}
    	
    	JOptionPane.showMessageDialog(null, "Company already registered");
    	return null;
    }

//    public void printar() {
//        mapeadorCompany.printar();
//   }
    
      public boolean check(Long num, String name){
    	companies = mapeadorCompany.getCompanys();
    	
    	for(Company e : companies){
    		if(e.getNomeCompany().equals(name) || e.getNumeroContrato() == num){
    			return true;
    		}    		
    	}
    	
    	return false;
    }

    public void registerCompany() {
        this.telaCompany.setVisible(true);
    }

    public void closeScreen() {
        this.telaCompany.setVisible(false);
    }
}



