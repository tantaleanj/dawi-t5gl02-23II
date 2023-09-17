package app;

import java.util.Iterator;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import model.Usuario;

public class Demo08 {
	
	// Listado de los usuarios segun un filtro de condicion.
	public static void main(String[] args) {
		
		EntityManagerFactory fabrica = Persistence.createEntityManagerFactory("jpa_sesion01");
		EntityManager manager = fabrica.createEntityManager();

		// Select * from tb_usuarios where id_tipo = ?--> Lista
		int tipo = 2;
		String sql = "select u from Usuario u where u.idtipo = :xtipo";
		List<Usuario> lstUsuarios = manager.createQuery(sql, Usuario.class)
				.setParameter("xtipo", tipo)
				.getResultList();
		
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
