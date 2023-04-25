package Vistas;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import java.awt.GridLayout;
import javax.swing.JLabel;
import java.awt.Font;
import javax.swing.SwingConstants;
import javax.swing.JTextField;
import javax.swing.JTextArea;
import javax.swing.JScrollPane;
import javax.swing.JButton;

public class VentanaChat extends JFrame {

	private JPanel contentPane;

	private JButton finalizarChatBoton,enviarBoton;
	private JTextArea mensajeTA,conversacionTA;
	private JLabel modoLabel,ip1Label ,puerto1Label,ip2Label ,puerto2Label;
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					VentanaChat frame = new VentanaChat();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public VentanaChat() {
		setTitle("Chat");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 466);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setLayout(new BorderLayout(0, 0));
		setContentPane(contentPane);
		
		JPanel panel = new JPanel();
		contentPane.add(panel, BorderLayout.NORTH);
		panel.setLayout(new GridLayout(2, 2, 0, 0));
		
		JPanel panel_4 = new JPanel();
		panel.add(panel_4);
		
		modoLabel = new JLabel("Modo: ");
		modoLabel.setFont(new Font("Tahoma", Font.PLAIN, 16));
		panel_4.add(modoLabel);
		
		JPanel panel_5 = new JPanel();
		panel.add(panel_5);
		panel_5.setLayout(new GridLayout(0, 2, 0, 0));
		
		ip1Label = new JLabel("IP1: 1.411.333.6");
		ip1Label.setHorizontalAlignment(SwingConstants.CENTER);
		ip1Label.setFont(new Font("Tahoma", Font.PLAIN, 16));
		panel_5.add(ip1Label);
		
		puerto1Label = new JLabel("Puerto1: 1222");
		puerto1Label.setHorizontalAlignment(SwingConstants.CENTER);
		puerto1Label.setFont(new Font("Tahoma", Font.PLAIN, 16));
		panel_5.add(puerto1Label);
		
		ip2Label = new JLabel("IP2: 1.411.333.6");
		ip2Label.setHorizontalAlignment(SwingConstants.CENTER);
		ip2Label.setFont(new Font("Tahoma", Font.PLAIN, 16));
		panel_5.add(ip2Label);
		
		puerto2Label = new JLabel("Puerto2:1223");
		puerto2Label.setHorizontalAlignment(SwingConstants.CENTER);
		puerto2Label.setFont(new Font("Tahoma", Font.PLAIN, 16));
		panel_5.add(puerto2Label);
		
		JPanel panel_1 = new JPanel();
		contentPane.add(panel_1, BorderLayout.CENTER);
		
		JScrollPane scrollPane = new JScrollPane();
		panel_1.add(scrollPane);
		
		 conversacionTA = new JTextArea();
		scrollPane.setViewportView(conversacionTA);
		conversacionTA.setColumns(45);
		conversacionTA.setRows(10);
		
		JPanel panel_2 = new JPanel();
		contentPane.add(panel_2, BorderLayout.SOUTH);
		panel_2.setLayout(new GridLayout(2, 1, 0, 0));
		
		JScrollPane scrollPane_1 = new JScrollPane();
		panel_2.add(scrollPane_1);
		
		mensajeTA = new JTextArea();
		mensajeTA.setToolTipText("");
		scrollPane_1.setViewportView(mensajeTA);
		mensajeTA.setRows(3);
		mensajeTA.setColumns(45);
		
		JPanel panel_3 = new JPanel();
		panel_2.add(panel_3);
		
		enviarBoton = new JButton("Enviar Mensaje");
		enviarBoton.setFont(new Font("Tahoma", Font.PLAIN, 16));
		panel_3.add(enviarBoton);
		
		finalizarChatBoton = new JButton("Finalizar Chat");
		finalizarChatBoton.setFont(new Font("Tahoma", Font.PLAIN, 16));
		panel_3.add(finalizarChatBoton);
		
		this.enviarBoton.setActionCommand("Enviar Mensaje");
		this.finalizarChatBoton.setActionCommand("Finalizar Chat");
	}

}
