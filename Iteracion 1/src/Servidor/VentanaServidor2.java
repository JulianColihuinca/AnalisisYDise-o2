package Servidor;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.border.EmptyBorder;

public class VentanaServidor2 extends JFrame implements IVentanaServidor{

	private JPanel contentPane;
	private JTextArea textAreaSolicitudes;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					VentanaServidor2 frame = new VentanaServidor2();
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
	public VentanaServidor2() {
		setTitle("SERVIDOR");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(new BorderLayout(0, 0));
		
		textAreaSolicitudes = new JTextArea();
		textAreaSolicitudes.setEditable(false);
		contentPane.add(textAreaSolicitudes, BorderLayout.CENTER);
		
		JLabel lblSolisitudesServidor = new JLabel("Solicitudes Servidor:");
		contentPane.add(lblSolisitudesServidor, BorderLayout.NORTH);
	}

	@Override
	public void actualizarLista(String item) {
		this.textAreaSolicitudes.append(item+"\n");
		
	}

}
