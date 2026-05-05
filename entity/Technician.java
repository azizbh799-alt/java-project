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
public class Technician extends Person implements Serializable {

    private static final long serialVersionUID = 1L;

    public Technician(String name, long phone) {
        super(name, phone);
    }

    @Override
    public String toString() {
        return this.getNome();
    }

}


