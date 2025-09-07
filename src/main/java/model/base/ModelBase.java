package model.base;

import jakarta.persistence.*;

@MappedSuperclass // usado para o JPA Entity identificar como superclasse
public abstract class ModelBase {
//    Criando atributos
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(name = "nome")
    private String nome;

//    Construtores
    public ModelBase(){}

    public ModelBase(int id, String nome) {
        this.id = id;
        this.nome = nome;
    }

    public ModelBase(String nome) {
        this.nome = nome;
    }

    //    Métodos getters e setters
    public int getId(){
        return this.id;
    }
    public void setId(int id){
        this.id = id;
    }

    public String getNome(){
        return this.nome;
    }
    public void setNome(String nome){
        this.nome = nome;
    }

//    toString
    @Override
    public String toString() {
        return "=== " + this.nome.toUpperCase() + " ===" +
                "\nID: " + this.id;
    }
}
