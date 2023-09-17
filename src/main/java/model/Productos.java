package model;

import javax.persistence.*;

import lombok.Data;

@Entity
@Table(name = "tb_productos")
@Data																
public class Productos {
    @Id
    private String id_prod;
    private String des_prod;
    private int stk_prod;
    private Double pre_prod;
    private int idcategoria;
    private int est_prod;
    private int idproveedor;
    
    @ManyToOne
    @JoinColumn(name ="idcategoria", 
            insertable = false,
            updatable = false)
    private Categoria objCateg;

    @ManyToOne
    @JoinColumn(name ="idproveedor", 
            insertable = false,
            updatable = false)
    private Proveedor objProvee;
    

}
