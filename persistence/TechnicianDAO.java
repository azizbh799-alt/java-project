/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package persistence;

import entity.Technician;
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
public class TechnicianDAO {

    private HashMap<Integer, Technician> technicianCache;
    private static final String fileName = "tecnicos.dat";

    public TechnicianDAO() {
        this.technicianCache = new HashMap<>();
        loadData();
    }

    public int generateCode() {
        return technicianCache.keySet().size() + 1;
    }

    public HashMap<Integer, Technician> getTechnicianCache() {
        return technicianCache;

    }

    public void put(Technician technician) {

        technicianCache.put(generateCode(), technician);
        persist();
    }

    public Technician get(Integer code) {
        return technicianCache.get(code);
    }

    private void loadData() {

        try {
            FileInputStream fis = new FileInputStream(fileName);
            ObjectInputStream ois = new ObjectInputStream(fis);

            technicianCache = (HashMap<Integer, Technician>) ois.readObject();

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

            oos.writeObject(technicianCache);

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


