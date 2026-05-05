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
public class Ticket implements Serializable {

    private static final long serialVersionUID = 1L;

    private Integer code;

    private String date;
    private String time;

    private String title;
    private String description;
    private int priority;
    private String status;
    private String problemType;
    private Technician technician;
    private CompanyCustomer customer;

    //sistema operacional do customer e versao
    private String sistemaOperacional;
    private String versaoSO;
    //database problem
    private String bancoDeDados;
    //quando o ticket for fechado, solution
    private String causaProblema;
    private String solucaoProblema;
    //problem de rede
    private String tipoConexao;
    private String enderecoNetwork;
    //problem desempenho
    private String operacao;
    private double duracaoOperacao;

    //construtor para problemNetwork
    public Ticket(Integer code, String title, String description, int priority, Technician technician,
            CompanyCustomer customer, String sistemaOperacional, String versaoSO, String tipoConexao, String enderecoNetwork) {

        Calendar c = Calendar.getInstance();
        this.time = DateFormat.getTimeInstance().format(c.getTime());
        this.date = DateFormat.getDateInstance().format(c.getTime());
        this.title = title;
        this.description = description;
        this.priority = priority;
        this.status = "Started";
        this.problemType = "Network Problem";
        this.technician = technician;
        this.customer = customer;
        this.sistemaOperacional = sistemaOperacional;
        this.versaoSO = versaoSO;
        this.tipoConexao = tipoConexao;
        this.enderecoNetwork = enderecoNetwork;

    }

    //construtor para problemBancoDeDados
    public Ticket(String title, String description, int priority, Technician technician,
            CompanyCustomer customer, String sistemaOperacional, String versaoSO, String bancoDeDados) {

        Calendar c = Calendar.getInstance();
        this.time = DateFormat.getTimeInstance().format(c.getTime());
        this.date = DateFormat.getDateInstance().format(c.getTime());
        this.title = title;
        this.description = description;
        this.priority = priority;
        this.status = "started";
        this.problemType = "Database";
        this.technician = technician;
        this.customer = customer;
        this.sistemaOperacional = sistemaOperacional;
        this.versaoSO = versaoSO;
        this.bancoDeDados = bancoDeDados;
    }

    //construtor para problem de desempenho
    public Ticket(String title, String description, int priority, Technician technician,
            CompanyCustomer customer, String sistemaOperacional, String versaoSO, String operacao, double tempoOperacao) {

        Calendar c = Calendar.getInstance();
        this.time = DateFormat.getTimeInstance().format(c.getTime());
        this.date = DateFormat.getDateInstance().format(c.getTime());
        this.title = title;
        this.description = description;
        this.priority = priority;
        this.status = "Started";
        this.problemType = "Performance Problem";
        this.technician = technician;
        this.customer = customer;
        this.sistemaOperacional = sistemaOperacional;
        this.versaoSO = versaoSO;
        this.operacao = operacao;
        this.duracaoOperacao = tempoOperacao;
    }

    public Technician getTecnico() {
        return technician;
    }

    public int getCodigo() {
        return this.code;
    }

    public void setCodigo(int code) {
        this.code = code;
    }

    public void setTecnico(Technician technician) {
        this.technician = technician;
    }

    public CompanyCustomer getCliente() {
        return customer;
    }

    public void setCliente(CompanyCustomer customer) {
        this.customer = customer;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getTipoProblema() {
        return problemType;
    }

    public void setTipoProblema(String problemType) {
        this.problemType = problemType;
    }

    public String getData() {
        return date;
    }

    public void setData(String date) {
        this.date = date;
    }

    public String getHora() {
        return time;
    }

    public void setHora(String time) {
        this.time = time;
    }

    public String getTitulo() {
        return title;
    }

    public void setTitulo(String title) {
        this.title = title;
    }

    public String getDescricao() {
        return description;
    }

    public void setDescricao(String description) {
        this.description = description;
    }

    public int getPrioridade() {
        return priority;
    }

    public void setPrioridade(int priority) {
        this.priority = priority;
    }

    public String getSistemaOperacional() {
        return sistemaOperacional;
    }

    public void setSistemaOperacional(String sistemaOperacional) {
        this.sistemaOperacional = sistemaOperacional;
    }

    public String getVersaoSO() {
        return versaoSO;
    }

    public void setVersaoSO(String versaoSO) {
        this.versaoSO = versaoSO;
    }

    public String getBancoDeDados() {
        return bancoDeDados;
    }

    public void setBancoDeDados(String bancoDeDados) {
        this.bancoDeDados = bancoDeDados;
    }

    public String getCausaProblema() {
        return causaProblema;
    }

    public void setCausaProblema(String causaProblema) {
        this.causaProblema = causaProblema;
    }

    public String getSolucaoProblema() {
        return solucaoProblema;
    }

    public void setSolucaoProblema(String solucaoProblema) {
        this.solucaoProblema = solucaoProblema;
    }

    public String getTipoConexao() {
        return tipoConexao;
    }

    public void setTipoConexao(String tipoConexao) {
        this.tipoConexao = tipoConexao;
    }

    public String getEnderecoNetwork() {
        return enderecoNetwork;
    }

    public void setEnderecoNetwork(String enderecoNetwork) {
        this.enderecoNetwork = enderecoNetwork;
    }

    public String getOperacao() {
        return operacao;
    }

    public void setOperacao(String operacao) {
        this.operacao = operacao;
    }

    public double getDuracaoOperacao() {
        return duracaoOperacao;
    }

    public void setDuracaoOperacao(double duracaoOperacao) {
        this.duracaoOperacao = duracaoOperacao;
    }
    
    @Override
    public boolean equals(final Object c){
    	Ticket comp = (Ticket) c;   	
    	
    	if(this.getCodigo() != comp.getCodigo()) return false;
    	if(!this.getData().equals(comp.getData())) return false;
    	if(!this.getTitulo().equals(comp.getTitulo())) return false;
    	
    	return true;
    }

}




