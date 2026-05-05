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
public class Person implements Serializable{
    
    private static final long serialVersionUID = 1L;
    private String name;
    private long phone;

    public Person(String name, long phone) {
        this.name = name;
        this.phone = phone;
    }

    public String getNome() {
        return name;
    }

    public void setNome(String name) {
        this.name = name;
    }

    public long getTelefone() {
        return phone;
    }

    public void setTelefone(long phone) {
        this.phone = phone;
    }
    
    
    
}


