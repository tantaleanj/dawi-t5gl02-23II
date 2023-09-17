package app;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import model.Categoria;
import model.Productos;
import model.Proveedor;
import model.Usuario;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.util.List;
import java.awt.event.ActionEvent;
import javax.swing.JTextArea;
import javax.swing.JScrollPane;
import javax.swing.JTextField;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JComboBox;
import javax.swing.JPasswordField;
import java.awt.Font;

public class Login extends JFrame {

	private JPanel contentPane;
	private JTextField txtUsuario;
	private JPasswordField txtPassword;
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Login frame = new Login();
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
	public Login() {
		setTitle("Login Usuarios");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 522, 147);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);

		JButton btnNewButton = new JButton("Ingresar");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				ingresar();
			}
		});
		btnNewButton.setBounds(407, 41, 89, 23);
		contentPane.add(btnNewButton);
		
		txtUsuario = new JTextField();
		txtUsuario.setBounds(122, 11, 242, 20);
		contentPane.add(txtUsuario);
		txtUsuario.setColumns(10);
		
		JLabel lblUsuario = new JLabel("Usuario");
		lblUsuario.setFont(new Font("Tahoma", Font.PLAIN, 15));
		lblUsuario.setBounds(10, 14, 102, 14);
		contentPane.add(lblUsuario);
		
		JLabel lblContrasena = new JLabel("Contraseña");
		lblContrasena.setFont(new Font("Tahoma", Font.PLAIN, 15));
		lblContrasena.setBounds(10, 67, 102, 14);
		contentPane.add(lblContrasena);
		
		txtPassword = new JPasswordField();
		txtPassword.setBounds(122, 64, 128, 20);
		contentPane.add(txtPassword);
		
	}
	
	void imprimir(String msg) {
		
    }
	
	void ingresar() {
		// Llamar a la conexión
		EntityManagerFactory fabrica = Persistence.createEntityManagerFactory("jpa_sesion01");
		EntityManager manager = fabrica.createEntityManager();

		String usuario = txtUsuario.getText();
		char[] contraseña = txtPassword.getPassword();
		String contraseñaString = new String(contraseña);

		try {
		    // Realizar la consulta usando JPQL
		    String jpql = "SELECT u FROM Usuario u WHERE u.usr_usua = :usuario AND u.cla_usua = :contraseña";
		    List<Usuario> lstUsuarios = manager.createQuery(jpql, Usuario.class)
		        .setParameter("usuario", usuario)
		        .setParameter("contraseña", contraseñaString)
		        .getResultList();

		    if (!lstUsuarios.isEmpty()) {
		        // Usuario y contraseña válidos
		        JOptionPane.showMessageDialog(this, "Usuario y contraseña válidos");
		        // Cierra el formulario actual
		        this.dispose();

		        // Abre el formulario MantenmientoProductos
		        FrmManteProd frmManteProd = new FrmManteProd();
		        frmManteProd.setVisible(true);
		    } else {
		        // Usuario o contraseña incorrectos
		        JOptionPane.showMessageDialog(this, "Usuario o contraseña incorrectos");
		    }
		} finally {
		    manager.close();
		    fabrica.close();
		}
	}
	
	void aviso (String msg) {
		JOptionPane.showMessageDialog(this, msg, "Aviso", JOptionPane.INFORMATION_MESSAGE);
	}
}
