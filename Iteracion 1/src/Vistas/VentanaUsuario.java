package Vistas;

import java.awt.EventQueue;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionListener;
import java.util.Observable;
import java.util.Observer;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;

import Interfaces.IVentanaUsuario;
import Main.Llamada;
import Main.RespuestaLlamada;

public class VentanaUsuario extends JFrame implements IVentanaUsuario, Observer{

	private JPanel contentPane;
	private JTextField ipTexto;
	private JTextField puertoIP;
    private JButton comenzarChatBoton,atenderBoton,rechazarBoton;
    private JLabel notificacionLlamada;
    private JLabel ipLabel;
    private JLabel puertoLabel;
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
		
		ipLabel = new JLabel("IP: ");
		ipLabel.setHorizontalAlignment(SwingConstants.CENTER);
		ipLabel.setFont(new Font("Tahoma", Font.PLAIN, 15));
		panel.add(ipLabel);
		
		puertoLabel = new JLabel("Puerto: ");
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

	@Override
	public void actualizarDatos(String ip,int puerto) {
		this.ipLabel.setText("IP: "+ip);	
		this.puertoLabel.setText("PUERTO: "+puerto);
	}

	
	//-----------------------------ESTE UPDATE ES PARA RECIBIR LA LLAMADA Y LA CONTESTACION NEGATIVA
	@Override
	public void update(Observable o, Object arg) {
		System.out.println("ESTOY EN METODO UPDATE VENTANA USUARIO");
		if(arg instanceof RespuestaLlamada) {
			System.out.println("RECIBI UNA RESPUESTA A LA LLAMADA");
			RespuestaLlamada respuesta=(RespuestaLlamada)arg;
			if(!respuesta.isRespuesta()) { //SI NO ME ATENDIERON
				System.out.println("LA RESPUESTA A LA LLAMADA FUE NEGATIVA");
				
				//REVISAR PORQUE NO FUNCIONAN LAS SIGUIENTES CUATRO LINEAS DE CODIGO
				
				this.notificacionLlamada.setText("");
				this.atenderBoton.setEnabled(false);
				this.rechazarBoton.setEnabled(false);
				this.comenzarChatBoton.setEnabled(true);
				System.out.println("LA RESPUESTA A LA LLAMADA FUE NEGATIVA");
			}// SI ME ATIENDEN PASO A VENTANA DEL CHAT DESDE EL CONSTRUCTOR
				
		}else if(arg instanceof Llamada) { //VERIFICO QUE RECIBA UNA LLAMADA
			System.out.println("RECIBI UNA LLAMADA");
			Llamada llamada=(Llamada)arg;
		
			String ip=llamada.getIPOrigen();
			int puerto=llamada.getPuertoOrigen();
		
			String etiqueta="Tienes una llamada de IP: "+ip+", Puerto: "+puerto;
			this.notificacionLlamada.setText(etiqueta);
		
			this.atenderBoton.setEnabled(true);
			this.rechazarBoton.setEnabled(true);
			this.comenzarChatBoton.setEnabled(false);
		} //TENGO QUE ENVIAR/RECIBIR UNA RESPUESTA -> EL ENVIO DESDE EL CONSTRUCTOR
	}

	
	
	
	@Override
	public JFrame addObserver() {
		// TODO Auto-generated method stub
		return this;
	}

}
