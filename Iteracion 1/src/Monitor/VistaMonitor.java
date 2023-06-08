package Monitor;

import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.awt.event.ActionListener;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;
import javax.swing.JTable;
import javax.swing.JList;
import javax.swing.JButton;

public class VistaMonitor extends JFrame implements IVistaMonitor {

	private JPanel contentPane;
	private JTable table;
	private DefaultTableModel dtm;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					VistaMonitor frame = new VistaMonitor();
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
	public VistaMonitor() {
		setResizable(false);
		setTitle("Monitor");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 291, 94);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setLayout(new BorderLayout(0, 0));
		setContentPane(contentPane);
		Object[][] data= {
				{"ServidorA" , "No disponible"},
				{"ServidorB" , "No disponible"}
		};
		
		String [] columnas= {"Servidor" , "Disponibilidad"};
		this.dtm= new DefaultTableModel(data,columnas);
		table = new JTable(this.dtm);
		contentPane.add(table, BorderLayout.CENTER);
		this.setVisible(true);
	}

	@Override
	public void actualiarVista(int nroServidor, String estado) {
		this.dtm.setValueAt(estado, nroServidor-1, 1);
		this.setVisible(true);
		
	}


}
