package app;

import java.util.Iterator;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import model.Productos;
import model.Usuario;

public class Demo07 {
	
	// Listado de TODOS los usuarios.
	public static void main(String[] args) {
		
		EntityManagerFactory fabrica = Persistence.createEntityManagerFactory("jpa_sesion01");
		EntityManager manager = fabrica.createEntityManager();

		// Select * from tb_prodcutos --> Lista
		String sql = "select p from Productos p";
		List<Productos> lstProdcutos = manager.createQuery(sql, Productos.class).getResultList();
		
		// Mostrar el listado
		System.out.println("Listado de Productos");
		for (Productos p : lstProdcutos) {
			System.out.println("ID PRODUCTO.......: " + p.getId_prod());
			System.out.println("DESCRIPCION.......: " + p.getDes_prod());
			System.out.println("STOCK.............: " + p.getStk_prod());
			System.out.println("PRECIO............: " + p.getPre_prod());
			System.out.println("CATEGORIA.........: " + p.getIdcategoria() + " - " + p.getObjCateg().getDescripcion());
            System.out.println("ESTADO............: " + p.getEst_prod());
            System.out.println("PROVEEDOR.........: " + p.getIdproveedor() + 
                                                " - " + p.getObjProvee().getNombre_rs()+ 
                                                " - " + p.getObjProvee().getTelefono());

			System.out.println("----------------------------------");
		}
		
		
		manager.close();
		
		
	}
}
