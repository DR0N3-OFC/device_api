package br.edu.utfpr.todoapi.model;

import java.util.List;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.Data;

@Entity
@Table(name = "tb_gateway") // Define o noma da tabel
@Data
public class Gateway {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id ;
    @Column(nullable = false)
    private String nome;
    @Column(nullable = false)
    private String descricao;
    @Column(nullable = false, unique = true)
    private String endereco;

    @OneToMany(mappedBy = "gateway", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Dispositivo> dispositivos;
}
