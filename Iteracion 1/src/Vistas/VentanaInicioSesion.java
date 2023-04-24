package Vistas;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import java.awt.GridLayout;
import javax.swing.JLabel;
import javax.swing.SwingConstants;
import java.awt.Font;
import javax.swing.JTextField;
import javax.swing.JButton;

public class VentanaInicioSesion extends JFrame {

	private JPanel contentPane;
	private JTextField puertoText;
	private JButton iniciarSesionBoton;
	private JLabel ipLabel ;

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
		setTitle("Iniciar Sesion");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(new GridLayout(3, 1, 0, 0));
		
		JPanel panel = new JPanel();
		contentPane.add(panel);
		
		ipLabel = new JLabel("IP: 1.222.333.1");
		ipLabel.setFont(new Font("Tahoma", Font.PLAIN, 16));
		ipLabel.setHorizontalAlignment(SwingConstants.CENTER);
		panel.add(ipLabel);
		
		JPanel panel_1 = new JPanel();
		contentPane.add(panel_1);
		
		JLabel lblNewLabel_1 = new JLabel("Ingrese Puerto: ");
		lblNewLabel_1.setFont(new Font("Tahoma", Font.PLAIN, 13));
		panel_1.add(lblNewLabel_1);
		
		puertoText = new JTextField();
		panel_1.add(puertoText);
		puertoText.setColumns(15);
		
		JPanel panel_2 = new JPanel();
		contentPane.add(panel_2);
		
		iniciarSesionBoton = new JButton("Iniciar Sesion");
		panel_2.add(iniciarSesionBoton);
		this.iniciarSesionBoton.setActionCommand("Iniciar Sesion");
	}

}
