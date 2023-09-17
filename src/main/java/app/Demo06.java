package app;

import java.util.Iterator;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import model.Usuario;

public class Demo06 {
	
	// Listado de TODOS los usuarios.
	public static void main(String[] args) {
		
		EntityManagerFactory fabrica = Persistence.createEntityManagerFactory("jpa_sesion01");
		EntityManager manager = fabrica.createEntityManager();

		// Select * from tb_usuarios --> Lista
		String sql = "select u from Usuario u";
		List<Usuario> lstUsuarios = manager.createQuery(sql, Usuario.class).getResultList();
		
		// Mostrar el listado
		System.out.println("Listado de USUARIOS");
		for (Usuario u : lstUsuarios) {
			System.out.println("Codigo.......: " + u.getCod_usua());
			System.out.println("Nombre.......: " + u.getNom_usua() + u.getApe_usua());
			System.out.println("Tipo.........: " + u.getIdtipo() + "-" + 
							u.getOjbTipo().getDescripcion());
			System.out.println("----------------------------------");
		}
		
		
		manager.close();
		
		
	}
}
