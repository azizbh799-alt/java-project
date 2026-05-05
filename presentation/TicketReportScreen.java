package presentation;

import java.awt.Container;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JTextArea;

import controller.TicketController;

public class TicketReportScreen extends JFrame{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private TicketController CC;
	private JLabel lEmitir;	
	private JButton btGenerate;
	private JButton btClear;
	private JTextArea areareports;
	private JComboBox<String> lstProb;
	private Container container;
	private GE eventManager;
	
	public TicketReportScreen(TicketController c){	
		super("Closed Tickets Report");
		this.CC = c;
		container = getContentPane();
		container.setLayout(new GridBagLayout());		
		setSize(700, 700);
		eventManager = new GE();
		initialize();
	}
	
	private void initialize(){
		
		GridBagConstraints cts = new GridBagConstraints();
				
		cts.fill = GridBagConstraints.HORIZONTAL;
        cts.gridx = 0;
        cts.gridy = 1;
		container.add(new JLabel("Select problem type:"), cts);
		
		//instancia, adiciona valores e adiciona combo
		lstProb = new JComboBox<String>();
		lstProb.addItem("Network/Connection Problem");
		lstProb.addItem("Database Problem");
		lstProb.addItem("Performance Problem");
		cts.fill = GridBagConstraints.HORIZONTAL;
        cts.gridx = 0;
        cts.gridy = 2;
		container.add(lstProb, cts);
		
		//instancia e adiciona area de texto
		areareports = new JTextArea();
		areareports.setPreferredSize(new Dimension(500, 500));		
		cts.fill = GridBagConstraints.HORIZONTAL;
        cts.gridx = 0;
        cts.gridy = 3;
		container.add(areareports, cts);
		
		//instancia e adiciona botoes
		btClear = new JButton("Clear");
		btClear.setActionCommand(btClear.getText());
		btClear.addActionListener(eventManager);
		cts.fill = GridBagConstraints.HORIZONTAL;
        cts.gridx = 0;
        cts.gridy = 5;
		container.add(btClear, cts);
		
		btGenerate = new JButton("Generate report");
		btGenerate.setActionCommand(btGenerate.getText());
		btGenerate.addActionListener(eventManager);
		cts.fill = GridBagConstraints.HORIZONTAL;
        cts.gridx = 0;
        cts.gridy = 4;
		container.add(btGenerate, cts);
	}
	
	public void incluirTexto(String txt){
		areareports.setText(txt);
	}
	
	private class GE implements ActionListener{

		@Override
		public void actionPerformed(ActionEvent e) {
			if(e.getActionCommand().equals(btGenerate.getText())){
				String report = CC.generateReports(lstProb.getSelectedIndex());
				if(report.equals("")){ incluirTexto("There are no tickets of this problem type to display");
				}else{
					incluirTexto(CC.generateReports(lstProb.getSelectedIndex()));
				}
			}
			else if(e.getActionCommand().equals(btClear.getActionCommand())){
				incluirTexto("");
			}
		}
		
	}
}



