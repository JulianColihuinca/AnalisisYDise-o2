package Vista;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import java.awt.GridLayout;
import javax.swing.JLabel;
import java.awt.Font;
import javax.swing.SwingConstants;
import javax.swing.JButton;
import javax.swing.JTextField;

public class VentanaUsuario extends JFrame {

	private JPanel contentPane;
	private JTextField textField;
	private JTextField textField_1;

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
		
		JLabel lblNewLabel = new JLabel("IP: 1.333.666.1");
		lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel.setFont(new Font("Tahoma", Font.PLAIN, 15));
		panel.add(lblNewLabel);
		
		JLabel lblNewLabel_1 = new JLabel("Puerto: 2366");
		lblNewLabel_1.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel_1.setFont(new Font("Tahoma", Font.PLAIN, 16));
		panel.add(lblNewLabel_1);
		
		JPanel panel_1 = new JPanel();
		contentPane.add(panel_1);
		panel_1.setLayout(new GridLayout(2, 2, 0, 0));
		
		JLabel lblNewLabel_2 = new JLabel("Ingrese IP:");
		lblNewLabel_2.setHorizontalAlignment(SwingConstants.CENTER);
		panel_1.add(lblNewLabel_2);
		
		textField = new JTextField();
		panel_1.add(textField);
		textField.setColumns(10);
		
		JLabel lblNewLabel_3 = new JLabel("Ingrese Puerto:");
		lblNewLabel_3.setHorizontalAlignment(SwingConstants.CENTER);
		panel_1.add(lblNewLabel_3);
		
		textField_1 = new JTextField();
		panel_1.add(textField_1);
		textField_1.setColumns(10);
		
		JPanel panel_2 = new JPanel();
		contentPane.add(panel_2);
		
		JButton btnNewButton = new JButton("Comenzar Chat");
		panel_2.add(btnNewButton);
		
		JPanel panel_3 = new JPanel();
		contentPane.add(panel_3);
		panel_3.setLayout(new GridLayout(2, 1, 0, 0));
		
		JLabel lblNewLabel_4 = new JLabel("Tienes una llamada de IP:1.121.212.5, Puerto:1233");
		lblNewLabel_4.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel_4.setFont(new Font("Tahoma", Font.PLAIN, 16));
		panel_3.add(lblNewLabel_4);
		
		JPanel panel_4 = new JPanel();
		panel_3.add(panel_4);
		
		JButton btnNewButton_1 = new JButton("Atender Llamada");
		panel_4.add(btnNewButton_1);
		
		JButton btnNewButton_2 = new JButton("Rechazar Llamada");
		panel_4.add(btnNewButton_2);
	}

}
