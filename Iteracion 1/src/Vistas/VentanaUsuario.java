package Vistas;

import java.awt.EventQueue;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;

import Interfaces.IVentanaUsuario;

public class VentanaUsuario extends JFrame implements IVentanaUsuario{

	private JPanel contentPane;
	private JTextField ipTexto;
	private JTextField puertoIP;
    private JButton comenzarChatBoton,atenderBoton,rechazarBoton;
    private JLabel notificacionLlamada;
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
		setTitle("Comenzar Sesion Chat");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(new GridLayout(0, 1, 0, 0));
		
		JPanel panel = new JPanel();
		contentPane.add(panel);
		panel.setLayout(new GridLayout(0, 2, 0, 0));
		
		JLabel ipLabel = new JLabel("IP: 1.333.666.1");
		ipLabel.setHorizontalAlignment(SwingConstants.CENTER);
		ipLabel.setFont(new Font("Tahoma", Font.PLAIN, 15));
		panel.add(ipLabel);
		
		JLabel puertoLabel = new JLabel("Puerto: 2366");
		puertoLabel.setHorizontalAlignment(SwingConstants.CENTER);
		puertoLabel.setFont(new Font("Tahoma", Font.PLAIN, 16));
		panel.add(puertoLabel);
		
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
		
		notificacionLlamada = new JLabel("Tienes una llamada de IP:1.121.212.5, Puerto:1233");
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
		String ip=this.ipTexto.getText();
		return ip;
	}

	@Override
	public String getPuerto() {
		String puerto=this.puertoIP.getText();
		return puerto;
	}

}
