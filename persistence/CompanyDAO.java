package persistence;

import entity.Company;

import java.io.*;
import java.util.Collection;
import java.util.HashMap;
import java.util.Set;

//vou deixar para instanciar os streams dentro dos metodos pq cuida do problem
//do open/close
public class CompanyDAO {

    private HashMap<Long, Company> cacheCompanys;
    private Set<Long> chaves;

    private static final String fileName = "companies.dat";

    public CompanyDAO() {
        cacheCompanys = new HashMap<>();
        loadData();
        //nao vou instanciar os streams no construtor pois os metodos cuidarao disso
    }

    public void put(Company ep) {
        //sei q a chave nao deveria ser essa, por enquanto fica assim
        cacheCompanys.put(ep.getNumeroContrato(), ep);
        persist();
    }

    private void loadData() {

        try {
            FileInputStream fis = new FileInputStream(fileName);
            ObjectInputStream ois = new ObjectInputStream(fis);

            cacheCompanys = (HashMap<Long, Company>) ois.readObject();

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

            oos.writeObject(cacheCompanys);

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

    //para uso da classe CompanyController

    public Collection<Company> getCompanys() {
        return cacheCompanys.values();
    }

//    public void printar() {
//        carregar();
//        Collection<Company> col = cacheCompanys.values();
//
//        for (Company e : col) {
//            System.out.println("Name: " + e.getNomeCompany());
//            System.out.println("Num. Contract: " + e.getNumeroContrato());
//        }
//    }
    public HashMap<Long, Company> getCompanyCache() {
        
        return this.cacheCompanys;

    }
}




