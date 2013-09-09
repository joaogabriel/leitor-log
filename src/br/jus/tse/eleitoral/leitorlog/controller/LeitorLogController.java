package br.jus.tse.eleitoral.leitorlog.controller;

import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;

import java.awt.Font;

import javax.swing.JProgressBar;

@SuppressWarnings("serial")
public class LeitorLogController extends JFrame {
	
	private JTextField txtUrlArquivoLog;
	
	private JTextArea txtLog = new JTextArea();
	
	private JScrollPane scrollPane = new JScrollPane(txtLog);
	
	private final JProgressBar progressBar = new JProgressBar();

	public LeitorLogController() {
		txtLog.setFont(new Font("Courier New", Font.PLAIN, 14));
		GridBagLayout gridBagLayout = new GridBagLayout();
		gridBagLayout.columnWidths = new int[]{0, 0, 0};
		gridBagLayout.rowHeights = new int[]{0, 0, 0, 0, 0};
		gridBagLayout.columnWeights = new double[]{0.0, 1.0, Double.MIN_VALUE};
		gridBagLayout.rowWeights = new double[]{0.0, 0.0, 0.0, 1.0, Double.MIN_VALUE};
		getContentPane().setLayout(gridBagLayout);
		
		JLabel lblUrlArquivoLog = new JLabel("URL do arquivo de log:");
		GridBagConstraints gbc_lblUrlArquivoLog = new GridBagConstraints();
		gbc_lblUrlArquivoLog.anchor = GridBagConstraints.WEST;
		gbc_lblUrlArquivoLog.insets = new Insets(0, 0, 5, 5);
		gbc_lblUrlArquivoLog.gridx = 0;
		gbc_lblUrlArquivoLog.gridy = 0;
		getContentPane().add(lblUrlArquivoLog, gbc_lblUrlArquivoLog);
		
		txtUrlArquivoLog = new JTextField("http://procbatch.cad.apps.tse.jus.br:8080/arquivo?acao=v&dir=/web-docs/cadastro/jboss/5.1-EAP/procbatch-01/log&arq=server.log");
		GridBagConstraints gbc_txtUrlArquivoLog = new GridBagConstraints();
		gbc_txtUrlArquivoLog.insets = new Insets(0, 0, 5, 5);
		gbc_txtUrlArquivoLog.fill = GridBagConstraints.HORIZONTAL;
		gbc_txtUrlArquivoLog.gridx = 1;
		gbc_txtUrlArquivoLog.gridy = 0;
		getContentPane().add(txtUrlArquivoLog, gbc_txtUrlArquivoLog);
		txtUrlArquivoLog.setColumns(10);
		
		JButton btnAtualizar = new JButton("Atualizar");
		btnAtualizar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				txtLog.setText(null);
				progressBar.setIndeterminate(true);
				
				new Thread() {
					public void run() {
						txtLog.setText(LogService.recuperarArquivoLog(txtUrlArquivoLog.getText()));
						txtLog.setCaretPosition(txtLog.getText().length());
					}
				}.start();
				
				progressBar.setIndeterminate(false);
			}
		});
		
		GridBagConstraints gbc_btnAtualizar = new GridBagConstraints();
		gbc_btnAtualizar.insets = new Insets(0, 0, 5, 0);
		gbc_btnAtualizar.gridwidth = 3;
		gbc_btnAtualizar.gridx = 0;
		gbc_btnAtualizar.gridy = 1;
		getContentPane().add(btnAtualizar, gbc_btnAtualizar);
		
		GridBagConstraints gbc_progressBar = new GridBagConstraints();
		gbc_progressBar.gridwidth = 2;
		gbc_progressBar.insets = new Insets(0, 0, 5, 5);
		gbc_progressBar.gridx = 0;
		gbc_progressBar.gridy = 2;
		getContentPane().add(progressBar, gbc_progressBar);
		
		GridBagConstraints gbc_scrollPane = new GridBagConstraints();
		gbc_scrollPane.insets = new Insets(0, 0, 0, 5);
		gbc_scrollPane.gridwidth = 2;
		gbc_scrollPane.fill = GridBagConstraints.BOTH;
		gbc_scrollPane.gridx = 0;
		gbc_scrollPane.gridy = 3;
		getContentPane().add(scrollPane, gbc_scrollPane);
		
//		setSize(800, 600);
//		setResizable(true);
	}

	public static void main(String[] args) {
		LeitorLogController frame = new LeitorLogController();
		// frame.addWindowListener(this);
		frame.setExtendedState(JFrame.MAXIMIZED_BOTH);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setVisible(true);
	}

}
