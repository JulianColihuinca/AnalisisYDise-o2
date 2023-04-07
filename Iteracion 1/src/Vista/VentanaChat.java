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
import javax.swing.JTextField;
import javax.swing.JTextArea;
import javax.swing.JScrollPane;
import javax.swing.JButton;

public class VentanaChat extends JFrame {

	private JPanel contentPane;

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
		
		JLabel lblNewLabel_4 = new JLabel("Modo: ");
		lblNewLabel_4.setFont(new Font("Tahoma", Font.PLAIN, 16));
		panel_4.add(lblNewLabel_4);
		
		JPanel panel_5 = new JPanel();
		panel.add(panel_5);
		panel_5.setLayout(new GridLayout(0, 2, 0, 0));
		
		JLabel lblNewLabel = new JLabel("IP1: 1.411.333.6");
		lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel.setFont(new Font("Tahoma", Font.PLAIN, 16));
		panel_5.add(lblNewLabel);
		
		JLabel lblNewLabel_1 = new JLabel("Puerto1: 1222");
		lblNewLabel_1.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel_1.setFont(new Font("Tahoma", Font.PLAIN, 16));
		panel_5.add(lblNewLabel_1);
		
		JLabel lblNewLabel_2 = new JLabel("IP2: 1.411.333.6");
		lblNewLabel_2.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel_2.setFont(new Font("Tahoma", Font.PLAIN, 16));
		panel_5.add(lblNewLabel_2);
		
		JLabel lblNewLabel_3 = new JLabel("Puerto2:1223");
		lblNewLabel_3.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel_3.setFont(new Font("Tahoma", Font.PLAIN, 16));
		panel_5.add(lblNewLabel_3);
		
		JPanel panel_1 = new JPanel();
		contentPane.add(panel_1, BorderLayout.CENTER);
		
		JScrollPane scrollPane = new JScrollPane();
		panel_1.add(scrollPane);
		
		JTextArea textArea = new JTextArea();
		scrollPane.setViewportView(textArea);
		textArea.setColumns(45);
		textArea.setRows(10);
		
		JPanel panel_2 = new JPanel();
		contentPane.add(panel_2, BorderLayout.SOUTH);
		panel_2.setLayout(new GridLayout(2, 1, 0, 0));
		
		JScrollPane scrollPane_1 = new JScrollPane();
		panel_2.add(scrollPane_1);
		
		JTextArea textArea_1 = new JTextArea();
		textArea_1.setToolTipText("");
		scrollPane_1.setViewportView(textArea_1);
		textArea_1.setRows(3);
		textArea_1.setColumns(45);
		
		JPanel panel_3 = new JPanel();
		panel_2.add(panel_3);
		
		JButton btnNewButton = new JButton("Enviar Mensaje");
		btnNewButton.setFont(new Font("Tahoma", Font.PLAIN, 16));
		panel_3.add(btnNewButton);
		
		JButton btnNewButton_1 = new JButton("Finalizar Chat");
		btnNewButton_1.setFont(new Font("Tahoma", Font.PLAIN, 16));
		panel_3.add(btnNewButton_1);
	}

}
