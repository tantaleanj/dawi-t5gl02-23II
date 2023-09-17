package model;

import javax.persistence.*;

import lombok.Data;

@Entity
@Table(name = "tb_categorias")
@Data	
public class Categoria {
	
	@Id
	private int idcategoria;
	private String descripcion;
}
