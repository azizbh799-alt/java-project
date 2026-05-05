/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package persistence;

import entity.CompanyCustomer;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.HashMap;

/**
 *
 * @author Richard
 */
public class CustomerDAO {

    private HashMap<Long, CompanyCustomer> customerCache;
    private static final String fileName = "customers.dat";

    public CustomerDAO() {
        this.customerCache = new HashMap<>();
        loadData();
    }

    public boolean validarCPF(long cpf) {
        
        boolean result = false;
        
        CompanyCustomer c = customerCache.get(cpf);
        if (c == null) {
            result = true;
        }
        return result;
    }

    public int generateCode() {
        return customerCache.keySet().size() + 1;
    }

    public HashMap<Long, CompanyCustomer> getCustomerCache() {
        return customerCache;

    }

    public void put(CompanyCustomer customer) {
        customer.setCodigo(generateCode());
        customerCache.put(customer.getCpf(), customer);
        persist();
    }

    public CompanyCustomer get(Long codigoCliente) {
        return customerCache.get(codigoCliente);
    }

    private void loadData() {

        try {
            FileInputStream fis = new FileInputStream(fileName);
            ObjectInputStream ois = new ObjectInputStream(fis);

            customerCache = (HashMap<Long, CompanyCustomer>) ois.readObject();

            ois.close();
            fis.close();

        } catch (FileNotFoundException ex) {
            System.err.println("Error opening file " + fileName);
            System.err.println(ex.getMessage());
        } catch (IOException ex) {
            System.err.println("Input/output date error " + fileName);
            System.err.println(ex.getMessage());
        } catch (ClassNotFoundException ex) {
            System.err.println("Error processing file records " + fileName);
            System.err.println(ex.getMessage());
        }
    }

    public void persist() {

        try {
            FileOutputStream fos = new FileOutputStream(fileName);
            ObjectOutputStream oos = new ObjectOutputStream(fos);

            oos.writeObject(customerCache);

            oos.flush();
            fos.flush();

            oos.close();
            fos.close();

        } catch (FileNotFoundException ex) {
            System.err.println("File not found " + fileName);
            System.err.println(ex.getMessage());
        } catch (IOException ex) {
            System.err.println("Input/output error " + fileName);
            System.err.println(ex.getMessage());
        }

    }
}



