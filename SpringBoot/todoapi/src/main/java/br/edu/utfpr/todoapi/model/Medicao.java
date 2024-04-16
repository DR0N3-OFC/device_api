package br.edu.utfpr.todoapi.model;

import java.util.Date;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.Data;

@Entity //Entidade gerenciada pelo JPA (pode ser persistida)
@Table(name = "tb_medicao") // Define o noma da tabel
@Data
public class Medicao {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id; 
    
    @Column(nullable = false)
    private double valor;

    @Column(nullable = false, unique = true)
    private Date data;

    @ManyToOne
    @JoinColumn(name = "id")
    private Sensor sensor;
}
