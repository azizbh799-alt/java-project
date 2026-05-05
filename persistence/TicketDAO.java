/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package persistence;

import entity.Ticket;
import entity.TicketLog;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.Collection;
import java.util.HashMap;

/**
 *
 * @author Richard
 */
public class TicketDAO {

    private HashMap<Integer, Ticket> ticketCache;
    private HashMap<Integer, TicketLog> ticketLogCache;
    private static final String fileName = "tickets.dat";
    private static final String logFileName = "registroChamados.dat";

    public TicketDAO() {
        ticketCache = new HashMap<>();
        ticketLogCache = new HashMap<>();
        loadData();
    }

    public int generateCode() {
        return ticketCache.keySet().size() + 1;
    }

    public int generateTicketLogCode() {
        return ticketLogCache.keySet().size() + 1;
    }

    public void put(Ticket ticket) {
        ticket.setCodigo(generateCode());
        ticketCache.put(ticket.getCodigo(), ticket);
        persist();
    }

    public void putRegistro(TicketLog registroChamado) {
        
        ticketLogCache.put(registroChamado.getCodigo(), registroChamado);
        persist();
    }

    public Ticket get(Integer ticketCode) {
        return ticketCache.get(ticketCode);
    }

    private void loadData() {

        try {
            //ticket
            FileInputStream fis = new FileInputStream(fileName);
            ObjectInputStream ois = new ObjectInputStream(fis);
            ticketCache = (HashMap<Integer, Ticket>) ois.readObject();
            ois.close();
            fis.close();

            //registro  ticket
            FileInputStream fisR = new FileInputStream(logFileName);
            ObjectInputStream oisR = new ObjectInputStream(fisR);
            ticketLogCache = (HashMap<Integer, TicketLog>) oisR.readObject();
            oisR.close();
            fisR.close();

        } catch (FileNotFoundException ex) {
            System.err.println("Error opening file " + fileName);
            System.err.println("Error opening file " + logFileName);
            System.err.println(ex.getMessage());
        } catch (IOException ex) {
            System.err.println("Input/output date error " + fileName);
            System.err.println("Input/output date error " + logFileName);
            System.err.println(ex.getMessage());
        } catch (ClassNotFoundException ex) {
            System.err.println("Error processing file records " + fileName);
            System.err.println("Error processing file records " + logFileName);
            System.err.println(ex.getMessage());
        }
    }

    public void persist() {

        try {
            FileOutputStream fos = new FileOutputStream(fileName);
            ObjectOutputStream oos = new ObjectOutputStream(fos);
            oos.writeObject(ticketCache);
            oos.flush();
            fos.flush();
            oos.close();
            fos.close();

            FileOutputStream fosR = new FileOutputStream(logFileName);
            ObjectOutputStream oosR = new ObjectOutputStream(fosR);
            oosR.writeObject(ticketLogCache);
            oosR.flush();
            fosR.flush();
            oosR.close();
            fosR.close();

        } catch (FileNotFoundException ex) {
            System.err.println("File not found " + fileName);
            System.err.println("File not found " + logFileName);
            System.err.println(ex.getMessage());
        } catch (IOException ex) {
            System.err.println("Input/output error " + fileName);
            System.err.println("Input/output error " + logFileName);
            System.err.println(ex.getMessage());
        }

    }

    public Collection<Ticket> getChamados() {
        return ticketCache.values();
    }
    
    public Collection<TicketLog> getRegistros(){
    	return ticketLogCache.values();
    }
}



