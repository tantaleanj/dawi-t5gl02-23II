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

public class FrmManteProd extends JFrame {

	private JPanel contentPane;
	
	private JTextArea txtSalida;
	private JTextField txtCodigo;
	JComboBox cboCategorias;
	private JTextField txtDescripcion;
	private JTextField txtStock;
	private JTextField txtPrecio;
	private JComboBox cboProveedor;
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					FrmManteProd frame = new FrmManteProd();
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
	public FrmManteProd() {
		setTitle("Mantenimiento de Productos");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 410);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);

		JButton btnNewButton = new JButton("Registrar");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				registrar();
			}
		});
		btnNewButton.setBounds(324, 29, 89, 23);
		contentPane.add(btnNewButton);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(10, 193, 414, 143);
		contentPane.add(scrollPane);
		
		txtSalida = new JTextArea();
		scrollPane.setViewportView(txtSalida);
		
		JButton btnListado = new JButton("Listado");
		btnListado.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				listado();
			}
		});
		btnListado.setBounds(177, 347, 89, 23);
		contentPane.add(btnListado);
		
		txtCodigo = new JTextField();
		txtCodigo.setBounds(122, 11, 86, 20);
		contentPane.add(txtCodigo);
		txtCodigo.setColumns(10);
		
		JLabel lblCodigo = new JLabel("Id. Producto :");
		lblCodigo.setBounds(10, 14, 102, 14);
		contentPane.add(lblCodigo);
		
		cboCategorias = new JComboBox();
		cboCategorias.setBounds(122, 70, 124, 22);
		contentPane.add(cboCategorias);
		
		JLabel lblCategora = new JLabel("Categor\u00EDa");
		lblCategora.setBounds(10, 74, 102, 14);
		contentPane.add(lblCategora);
		
		JLabel lblNomProducto = new JLabel("Nom. Producto :");
		lblNomProducto.setBounds(10, 45, 102, 14);
		contentPane.add(lblNomProducto);
		
		txtDescripcion = new JTextField();
		txtDescripcion.setColumns(10);
		txtDescripcion.setBounds(122, 42, 144, 20);
		contentPane.add(txtDescripcion);
		
		JLabel lblStock = new JLabel("Stock:");
		lblStock.setBounds(10, 106, 102, 14);
		contentPane.add(lblStock);
		
		txtStock = new JTextField();
		txtStock.setColumns(10);
		txtStock.setBounds(122, 103, 77, 20);
		contentPane.add(txtStock);
		
		JLabel lblPrecio = new JLabel("Precio:");
		lblPrecio.setBounds(10, 134, 102, 14);
		contentPane.add(lblPrecio);
		
		txtPrecio = new JTextField();
		txtPrecio.setColumns(10);
		txtPrecio.setBounds(122, 131, 77, 20);
		contentPane.add(txtPrecio);
		
		JLabel lblProveedor = new JLabel("Proveedor");
		lblProveedor.setBounds(10, 159, 102, 14);
		contentPane.add(lblProveedor);
		
		cboProveedor = new JComboBox();
		cboProveedor.setBounds(122, 155, 124, 22);
		contentPane.add(cboProveedor);
		
		llenaCombo1();
		llenaCombo2();
	}

	void llenaCombo1() {
		EntityManagerFactory fabrica = Persistence.createEntityManagerFactory("jpa_sesion01");
		EntityManager manager = fabrica.createEntityManager();
		
		// Select * from tb_prodcutos --> Lista
		String sql = "select c from Categoria c";
		List<Categoria> lstCategoria = manager.createQuery(sql, Categoria.class).getResultList();
		
		// Mostrar el listado
		System.out.println("Listado de Categoria");
		cboCategorias.addItem("Seleccione....");
		for (Categoria c : lstCategoria) {
			//  + " - " + c.getDescripcion()
			cboCategorias.addItem(c.getIdcategoria() + " - " + c.getDescripcion());
		}
				
	}
	
	void listado() {
		EntityManagerFactory fabrica = Persistence.createEntityManagerFactory("jpa_sesion01");
		EntityManager manager = fabrica.createEntityManager();

		// Select * from tb_prodcutos --> Lista
		String sql = "select p from Productos p";
		List<Productos> lstProdcutos = manager.createQuery(sql, Productos.class).getResultList();
		
		// Mostrar el listado
		System.out.println("Listado de Productos");
		for (Productos p : lstProdcutos) {
			imprimir("ID PRODUCTO.......: " + p.getId_prod());
			imprimir("DESCRIPCION.......: " + p.getDes_prod());
			imprimir("STOCK.............: " + p.getStk_prod());
			imprimir("PRECIO............: " + p.getPre_prod());
			imprimir("CATEGORIA.........: " + p.getIdcategoria() + " - " + p.getObjCateg().getDescripcion());
			imprimir("ESTADO............: " + p.getEst_prod());
			imprimir("PROVEEDOR.........: " + p.getIdproveedor() + 
                                                " - " + p.getObjProvee().getNombre_rs()+ 
                                                " - " + p.getObjProvee().getTelefono());

			imprimir("----------------------------------");
		}
		
		
		manager.close();
	}
	
	void imprimir(String msg) {
		txtSalida.append(msg + "\n");
    }
	
	void registrar() {
		// llamar a la conexión
		EntityManagerFactory fabrica = Persistence.createEntityManagerFactory("jpa_sesion01");
		EntityManager manager = fabrica.createEntityManager();
        
		//ENTRADAS
		String idProducto = txtCodigo.getText().trim();
        String descripcion;
        int stock;
        double precio;
        String categoriaSeleccionada;
        String proveedorSeleccionado;
        
        //VALIDACIONES
        if(!idProducto.matches("[P][0-9][0-9]")) {
        	aviso("Formato de codigo no valido");
        	return;
        }
        	
		
        Productos p = new Productos();
        
        p.setDes_prod(txtDescripcion.getText());
        p.setEst_prod(1); // 1 : true // 2 : false
        p.setIdcategoria(cboCategorias.getSelectedIndex());
        p.setIdproveedor(cboProveedor.getSelectedIndex());
        p.setPre_prod(Double.parseDouble(txtPrecio.getText()));
        p.setStk_prod(Integer.parseInt(txtStock.getText()));
        p.setId_prod(txtCodigo.getText());
        
        
       // System.out.println(txtCodigo.getText());
       // System.out.println(txtDescripcion.getText());
       // System.out.println(Integer.parseInt(txtStock.getText()));
       // System.out.println(Double.parseDouble(txtPrecio.getText()));
       // System.out.println(cboCategorias.getSelectedIndex());
       // System.out.println(cboProveedor.getSelectedIndex());
        
    	// si queremos registrar, actualizar o eliminar -> transa..
        try {
            manager.getTransaction().begin();
            manager.persist(p);
            manager.getTransaction().commit();
            aviso("Registro Ok");
        } catch (Exception e) {
            e.printStackTrace();
            if (manager.getTransaction().isActive()) {
                manager.getTransaction().rollback();
            }
            aviso("Error al registrar el producto: " + e.getMessage());
        } finally {
            manager.close();
        }

	}
	
	void aviso (String msg) {
		JOptionPane.showMessageDialog(this, msg, "Aviso", JOptionPane.INFORMATION_MESSAGE);
	}
	
	void llenaCombo2() {
		EntityManagerFactory fabrica = Persistence.createEntityManagerFactory("jpa_sesion01");
		EntityManager manager = fabrica.createEntityManager();
		
		// Select * from tb_prodcutos --> Lista
		String sql = "select p from Proveedor p";
		List<Proveedor> lstProveedor = manager.createQuery(sql, Proveedor.class).getResultList();
		
		// Mostrar el listado
		System.out.println("Listado de Proveedor");
		cboProveedor.addItem("Seleccione....");
		for (Proveedor p : lstProveedor) {
			//  + "-" + p.getNombre_rs()
			cboProveedor.addItem(p.getIdproveedor() + "-" + p.getNombre_rs());
		}
		
	}
}
