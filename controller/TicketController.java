/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import persistence.TicketDAO;
import presentation.TicketRegistrationScreen;
import presentation.TicketTrackingScreen;
import presentation.TicketReportScreen;
import entity.Ticket;
import entity.CompanyCustomer;
import entity.TicketLog;
import entity.Technician;
import java.util.Collection;

/**
 *
 * @author Richard
 */
public class TicketController {

    private TicketRegistrationScreen ticketScreen;
    private TicketTrackingScreen ticketUpdateScreen;
    private TicketDAO ticketDAO;
    private Ticket updatedTicket;

    public TicketController() {

        this.ticketDAO = new TicketDAO();

    }

    public Ticket updateTicket(Ticket ticket, String status, String cause, String solution) {

        ticket.setStatus(status);
        ticket.setCausaProblema(cause);
        ticket.setSolucaoProblema(solution);

        return ticket;
    }

    public Ticket addNetworkTicket(String title, String description, int priority, Technician technician, CompanyCustomer customer,
            String so, String versaoSO, String conexao, String enderecoNetwork) {

        Ticket ticket = new Ticket(priority, title, description, priority,
                technician, customer, enderecoNetwork, versaoSO, conexao, enderecoNetwork);
        ticketDAO.put(ticket);
        return ticket;
    }

    public Ticket addDatabaseTicket(String title, String description, int priority, Technician technician, CompanyCustomer customer,
            String so, String versaoSO, String bancoDeDados) {

        Ticket ticket = new Ticket(title, description, priority, technician, customer, so, versaoSO, bancoDeDados);
        ticketDAO.put(ticket);
        return ticket;
    }

    public Ticket addPerformanceTicket(String title, String description, int priority, Technician technician, CompanyCustomer customer,
            String so, String versaoSO, String operacao, double tempo) {

        Ticket ticket = new Ticket(title, description, priority, technician, customer, so, versaoSO, operacao, tempo);
        ticketDAO.put(ticket);
        return ticket;
    }

    public TicketLog addTicketLog(String subject, Ticket ticket, Technician tec) {
        TicketLog registro = new TicketLog(subject, ticket, tec);
        ticketDAO.putRegistro(registro);
        return registro;
    }

    public void registerTicket() {
        this.ticketScreen = new TicketRegistrationScreen(this);
        this.ticketScreen.setVisible(true);

    }

    public void closeScreen() {
        this.ticketScreen.setVisible(false);
    }

    public Ticket getTicketByCode(Integer code) {
        return ticketDAO.get(code);

    }

    public void setChamadoAlterado(Ticket c) {
        this.updatedTicket = c;
    }

    public Ticket getChamadoAlterado() {
        return this.updatedTicket;
    }

    public String generateReports(int problemTypeIndex) {
    	
    	Ticket ref = null;
    	
    	String problem = ""; 
    	
    	switch(problemTypeIndex){
    		case 0 :
    			problem = "Network Problem";
    			break;
    		
    		case 1 :
    			problem = "Database";
    			break;
    		
    		case 2 :
    			problem = "Performance Problem";
    			break;
    	}

        Collection<Ticket> tickets = ticketDAO.getChamados();
        Collection<TicketLog> logs = ticketDAO.getRegistros();

        String report = "";

        for (Ticket c : tickets) {
            if (c.getTipoProblema().equals(problem)) {
            	
            	ref = c;
            	int p = ref.getPrioridade();
            	String priority = "";
            	switch(p){
            		case 1 :
            			priority = "Normal";
            			break;
            		
            		case 2 :
            			priority = "Important";
            			break;
            			
            		case 3 :
            			priority = "Urgent";
            			break;
            			
            		case 4 :
            			priority = "CrÃƒÂ­tica";
            			break;
            	}
                report += "\n" + "--------" + "\nData de abertura do ticket: " +
                    	ref.getData() + 
                    	"\nHorÃƒÂ¡rio de abertura do ticket: " + ref.getHora() + 
                    	"\nTÃƒÂ­tulo do ticket: " + ref.getTitulo() + 
                    	"\nCÃƒÂ³digo do ticket: " + ref.getCodigo() +
                    	"\nDescriÃƒÂ§ÃƒÂ£o do ticket: " + ref.getDescricao() + 
                    	"\nPrioridade do ticket: " + priority + 
                    	"\nStatus do ticket: " + ref.getStatus() + 
                    	"\nTipo de problem do ticket: " + ref.getTipoProblema() + 
                    	"\nTÃƒÂ©cnico responsÃƒÂ¡vel pelo ticket: " + ref.getTecnico() + 
                    	"\nCliente requisitor do ticket: " + ref.getCliente() + "\n";
                
                for(TicketLog rc : logs){
                	if(rc.getChamado().equals(ref)){
                		report += "\nRelatÃƒÂ³rio de logs de acompanhamento:" + 
                	"\nData: " + rc.getData() + 
                	"\nHora: " + rc.getHora() + 
                	"\nSubject: " + rc.getAssunto() +
                	"\nTÃƒÂ©cnico responsÃƒÂ¡vel: " + rc.getTecnico().getNome() +
                	"\nCausa do problem: " + ref.getCausaProblema() + 
                	"\nSoluÃƒÂ§ÃƒÂ£o do problem: " + ref.getSolucaoProblema();
                	}
                }
            }
        }

        return report;
    }

    public Ticket findByCode(int ticketCode) {

        Ticket storedTicket = null;

        Collection<Ticket> tickets = ticketDAO.getChamados();

        for (Ticket ticket : tickets) {
            if (ticketCode == ticket.getCodigo()) {
                storedTicket = ticket;
            }
        }
        return storedTicket;
    }

    public String getTicketDetails(Ticket c) {

        String details = "\n" + "--------" + "\n" + "Data de abertura do ticket: "
                + c.getData() + "\n" + "Time de abertura do ticket: " + c.getHora() + "\n"
                + "Titulo do ticket: " + c.getTitulo() + "\n" + "DescriÃƒÂ¯Ã‚Â¿Ã‚Â½ÃƒÂ¯Ã‚Â¿Ã‚Â½o do ticket: " + c.getDescricao() + "\n"
                + "Prioridade do ticket" + c.getPrioridade() + "\n" + "Status do ticket: " + c.getStatus() + "\n"
                + "Tipo de problem do ticket: " + c.getTipoProblema() + "\n" + "Technician responsible pelo ticket: "
                + c.getTecnico() + "\n" + "Customer requisitor do ticket: " + c.getCliente() + "\n";

        return details;
    }

    public void updateTicket() {
        this.ticketUpdateScreen = new TicketTrackingScreen(this);
        ticketUpdateScreen.setVisible(true);
    }

    public void openReport() {
        new TicketReportScreen(this).setVisible(true);
    }

    public void closeUpdateScreen() {
        this.ticketUpdateScreen.setVisible(false);
    }
    
    public int validateTicketQuantity(CompanyCustomer customer){
        
        int result = 0;
        Collection<Ticket> tickets = ticketDAO.getChamados();
        
        for (Ticket ticket : tickets) {
            if (customer == ticket.getCliente()) {
                result ++;
            }
        }
        return result;
    }

}



