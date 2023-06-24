package Vistas;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import java.awt.GridLayout;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import javax.swing.JLabel;
import javax.swing.SwingConstants;
import java.awt.Font;
import javax.swing.JTextField;
import javax.swing.JButton;

public class VentanaInicioSesion extends JFrame implements UIInicioSesion,KeyListener{

	private JPanel contentPane;
	private JTextField puertoText;
	private JButton iniciarSesionBoton;
	private JLabel ipLabel ;
	private JTextField nicknameText;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					VentanaInicioSesion frame = new VentanaInicioSesion();
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
	public VentanaInicioSesion() {
		setResizable(false);
		setTitle("Iniciar Sesion");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(new GridLayout(3, 1, 0, 0));
		
		JPanel panel = new JPanel();
		contentPane.add(panel);
		
		ipLabel = new JLabel("");
		ipLabel.setFont(new Font("Tahoma", Font.PLAIN, 16));
		ipLabel.setHorizontalAlignment(SwingConstants.CENTER);
		panel.add(ipLabel);
		
		
		JPanel panel_1 = new JPanel();
		panel_1.setLayout(new GridLayout(2,0));
		contentPane.add(panel_1);
		
		JPanel panelPuerto=new JPanel();
		panel_1.add(panelPuerto);
		
		
		JLabel lblNewLabel_1 = new JLabel("Ingrese Puerto: ");
		lblNewLabel_1.setFont(new Font("Tahoma", Font.PLAIN, 13));
		panelPuerto.add(lblNewLabel_1);
		
		puertoText = new JTextField();
		panelPuerto.add(puertoText);
		puertoText.setColumns(15);
		
		JPanel panelNickname=new JPanel();
		panel_1.add(panelNickname);
		
		JLabel lblNickname=new JLabel("Ingrese Nickname: ");
		lblNickname.setFont(new Font("Tahoma",Font.PLAIN,13));
		panelNickname.add(lblNickname);
		
		nicknameText=new JTextField();
		panelNickname.add(nicknameText);
		nicknameText.setColumns(15);
		
		
		JPanel panel_2 = new JPanel();
		contentPane.add(panel_2);
		
		iniciarSesionBoton = new JButton("Iniciar Sesion");
		panel_2.add(iniciarSesionBoton);
		this.iniciarSesionBoton.setActionCommand("Iniciar Sesion");
		this.iniciarSesionBoton.setEnabled(false);
		this.puertoText.addKeyListener(this);
		this.nicknameText.addKeyListener(this);
		
		this.setVisible(true); //AL CREARSE LA VENTANA SE HACE VISIBLE
	}

	@Override
	public String getPuerto() {
		return this.puertoText.getText();
	}

	@Override
	public void modificarIP(String ip) {
		this.ipLabel.setText("IP: " + ip  );
	}

	@Override
	public void addActionListener(ActionListener a) {
		this.iniciarSesionBoton.addActionListener(a);
		
	}

	@Override
	public void keyTyped(KeyEvent e) {
	    if (this.puertoText.getText().length()>0 && this.getNickname().length()>0) {
	    	this.iniciarSesionBoton.setEnabled(true);
	    }
	    else {
	    	this.iniciarSesionBoton.setEnabled(false);
	    }
	    
	}

	@Override
	public void keyPressed(KeyEvent e) {
	
	}

	@Override
	public void keyReleased(KeyEvent e) {
		
	}

	
	@Override
	public String getNickname() {
		return this.nicknameText.getText();
	}

}
