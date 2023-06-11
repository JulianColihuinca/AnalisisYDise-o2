package Vistas;

import java.awt.EventQueue;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Observable;
import java.util.Observer;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;

import Red.Llamada;
import Red.RespuestaLlamada;
import Servidor.UsuarioRegistro;

import javax.swing.JTable;
import javax.swing.JScrollPane;

public class VentanaUsuario extends JFrame implements IVentanaUsuario {

	private JPanel contentPane;
	private JTextField ipTexto;
	private JTextField puertoIP;
	private JButton comenzarChatBoton, atenderBoton, rechazarBoton;
	private JLabel notificacionLlamada;
	private JLabel ipLabel;
	private JLabel puertoLabel;
	private JPanel panel_5;
	private JPanel panel_6;
	private JLabel lblNewLabel;
	private JLabel estadoServidor;
	private JPanel panel_7;
	private JPanel panel_8;
	private JLabel lblNewLabel_1;
	private JLabel nicknameLabel;
	private JPanel panel_9;
	private JLabel lblNewLabel_4;
	private JTable usuariosTabla;
	private DefaultTableModel dtm;
	private JScrollPane scrollPane;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					VentanaUsuario frame = new VentanaUsuario();
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
	public VentanaUsuario() {
		setResizable(false);
		setTitle("Comenzar Sesion Chat");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 600, 418);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(new GridLayout(0, 1, 0, 0));

		JPanel panel = new JPanel();
		contentPane.add(panel);
		panel.setLayout(new GridLayout(3, 2, 0, 0));

		ipLabel = new JLabel("IP: ");
		ipLabel.setHorizontalAlignment(SwingConstants.CENTER);
		ipLabel.setFont(new Font("Tahoma", Font.PLAIN, 15));
		panel.add(ipLabel);

		puertoLabel = new JLabel("Puerto: ");
		puertoLabel.setHorizontalAlignment(SwingConstants.CENTER);
		puertoLabel.setFont(new Font("Tahoma", Font.PLAIN, 16));
		panel.add(puertoLabel);
		
		panel_5 = new JPanel();
		panel.add(panel_5);
		
		lblNewLabel = new JLabel("Servidor: ");
		lblNewLabel.setFont(new Font("Tahoma", Font.PLAIN, 15));
		panel_5.add(lblNewLabel);
		
		panel_6 = new JPanel();
		panel.add(panel_6);
		
		estadoServidor = new JLabel("Desconectado");
		estadoServidor.setFont(new Font("Tahoma", Font.PLAIN, 15));
		panel_6.add(estadoServidor);
		
		panel_7 = new JPanel();
		panel.add(panel_7);
		
		lblNewLabel_1 = new JLabel("Nickname:");
		lblNewLabel_1.setFont(new Font("Tahoma", Font.PLAIN, 15));
		panel_7.add(lblNewLabel_1);
		
		panel_8 = new JPanel();
		panel.add(panel_8);
		
		nicknameLabel = new JLabel("New label");
		nicknameLabel.setFont(new Font("Tahoma", Font.PLAIN, 15));
		panel_8.add(nicknameLabel);

		JPanel panel_1 = new JPanel();
		contentPane.add(panel_1);
		panel_1.setLayout(new GridLayout(2, 2, 0, 0));

		JLabel lblNewLabel_2 = new JLabel("Ingrese IP:");
		lblNewLabel_2.setHorizontalAlignment(SwingConstants.CENTER);
		panel_1.add(lblNewLabel_2);

		ipTexto = new JTextField();
		panel_1.add(ipTexto);
		ipTexto.setColumns(10);

		JLabel lblNewLabel_3 = new JLabel("Ingrese Puerto:");
		lblNewLabel_3.setHorizontalAlignment(SwingConstants.CENTER);
		panel_1.add(lblNewLabel_3);

		puertoIP = new JTextField();
		panel_1.add(puertoIP);
		puertoIP.setColumns(10);

		JPanel panel_2 = new JPanel();
		contentPane.add(panel_2);

		comenzarChatBoton = new JButton("Comenzar Chat");
		panel_2.add(comenzarChatBoton);

		JPanel panel_3 = new JPanel();
		contentPane.add(panel_3);
		panel_3.setLayout(new GridLayout(2, 1, 0, 0));

		notificacionLlamada = new JLabel("");
		notificacionLlamada.setHorizontalAlignment(SwingConstants.CENTER);
		notificacionLlamada.setFont(new Font("Tahoma", Font.PLAIN, 16));
		panel_3.add(notificacionLlamada);

		JPanel panel_4 = new JPanel();
		panel_3.add(panel_4);

		atenderBoton = new JButton("Atender Llamada");
		panel_4.add(atenderBoton);

		rechazarBoton = new JButton("Rechazar Llamada");
		panel_4.add(rechazarBoton);

		this.atenderBoton.setActionCommand("Atender Llamada");
		this.comenzarChatBoton.setActionCommand("Comenzar Chat");
		this.rechazarBoton.setActionCommand("Rechazar Llamada");

		this.atenderBoton.setEnabled(false);
		this.rechazarBoton.setEnabled(false);
		
		panel_9 = new JPanel();
		contentPane.add(panel_9);
		panel_9.setLayout(new GridLayout(2, 1, 0, 0));
		
		lblNewLabel_4 = new JLabel("Usuarios registrados en el sistema: ");
		lblNewLabel_4.setFont(new Font("Tahoma", Font.PLAIN, 16));
		panel_9.add(lblNewLabel_4);
		
		scrollPane = new JScrollPane();
		panel_9.add(scrollPane);
		
		Object[][] data= {};
		
		String [] columnas= {"Nickname" , "IP","Puerto"};
		this.dtm= new DefaultTableModel(data,columnas);
		usuariosTabla = new JTable(this.dtm);
		scrollPane.setViewportView(usuariosTabla);

		this.setVisible(true);
	}

	@Override
	public void addActionListener(ActionListener a) {
		this.atenderBoton.addActionListener(a);
		this.comenzarChatBoton.addActionListener(a);
		this.rechazarBoton.addActionListener(a);
		

	}

	@Override
	public String getIP() {
		String ip = this.ipTexto.getText();
		return ip;
	}

	@Override
	public String getPuerto() {
		String puerto = this.puertoIP.getText();
		return puerto;
	}

	@Override
	public void actualizarDatos(String ip, int puerto,String nickname) {
		this.ipLabel.setText("IP: " + ip);
		this.puertoLabel.setText("PUERTO: " + puerto);
		this.nicknameLabel.setText(nickname);
	}

	

	@Override
	public JFrame addObserver() {
		// TODO Auto-generated method stub
		return this;
	}

	@Override
	public void llamadaRechazada() {
		this.notificacionLlamada.setText("");
		this.atenderBoton.setEnabled(false);
		this.rechazarBoton.setEnabled(false);
		this.comenzarChatBoton.setEnabled(true);
	}

	@Override
	public void recibirLlamada(String ip, int puerto, String nickname) {
		// ACTUALIZO PARTE DE LA VENTANA PARA CONTESTAR O RECHAZAR LLAMADA
		// AL RECIBIR LA LLAMADA SE BLOQUEA EL BOTON INICIAR CHAT HASTA QUE SE CONTESTE
		// LA LLAMADA
		String etiqueta = "Tienes una llamada de IP: " + ip + ", Puerto: " + puerto + ", Nickname: "+nickname;
		this.notificacionLlamada.setText(etiqueta);

		this.atenderBoton.setEnabled(true);
		this.rechazarBoton.setEnabled(true);
		this.comenzarChatBoton.setEnabled(false);
	}

	@Override
	public void llamadaAceptada() {
		this.setVisible(false);

	}

	@Override
	public void actualizarEstadoServidor(boolean estado) {
		if (estado) {
			this.estadoServidor.setText("Conectado");
		}
		else {
			this.estadoServidor.setText("Desconectado");
		}
		
	}

	@Override
	public void actualizarTablaUsuarios(ArrayList<String> nicknames, ArrayList<String> ips,
			ArrayList<Integer> puertos) {
		
		if (this.dtm.getRowCount()< nicknames.size()) {
			int agregar=nicknames.size()-this.dtm.getRowCount();
			for (int j=0;j<agregar;j++) {
				Object[][] data= {};
				this.dtm.addRow(data);
			}
		}
		
		for(int i=0;i<nicknames.size();i++) {
			this.dtm.setValueAt(nicknames.get(i),i , 0);
			this.dtm.setValueAt(ips.get(i),i , 1);
			this.dtm.setValueAt(puertos.get(i),i , 2);
		}
		
		
	}

	

}
