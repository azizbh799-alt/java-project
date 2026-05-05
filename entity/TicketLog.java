/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entity;

import java.io.Serializable;
import java.text.DateFormat;
import java.util.Calendar;

/**
 *
 * @author Richard
 */
public class TicketLog implements Serializable {

    private static final long serialVersionUID = 1L;
    private Integer code;
    private String time;
    private String date;
    private String subject;
    private Technician technician;
    private Ticket ticket;

    public TicketLog(String subject, Ticket ticket, Technician technician) {
        Calendar c = Calendar.getInstance();
        this.time = DateFormat.getTimeInstance().format(c.getTime());
        this.date = DateFormat.getDateInstance().format(c.getTime());
        this.subject = subject;
        this.technician = technician;
        this.ticket = ticket;
    }

    public Integer getCodigo() {
        return code;
    }

    public void setCodigo(Integer code) {
        this.code = code;
    }

    public void setChamado(Ticket ticket) {
        this.ticket = ticket;
    }

    public Ticket getChamado() {
        return ticket;
    }

    public String getHora() {
        return time;
    }

    public void setHora(String time) {
        this.time = time;
    }

    public String getData() {
        return date;
    }

    public void setData(String date) {
        this.date = date;
    }

    public String getAssunto() {
        return subject;
    }

    public void setAssunto(String subject) {
        this.subject = subject;
    }

    public Technician getTecnico() {
        return technician;
    }

    public void setTecnico(Technician technician) {
        this.technician = technician;
    }

}



