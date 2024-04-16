package br.edu.utfpr.todoapi.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import br.edu.utfpr.todoapi.model.Sensor;

public interface SensorRepository extends JpaRepository<Sensor, Long>{

}
