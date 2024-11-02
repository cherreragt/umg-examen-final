package org.example;

import org.example.domain.Zoo;
import org.example.repository.ZooRepository;

import java.util.Date;

public class Main {
    public static void main(String[] args) {
        try {
            var zooRepository = new ZooRepository();
            var zoo = new Zoo();
            zoo.setNombre("Leon");
            zoo.setEspecie("Mamifero");
            zoo.setFechaNacimiento(new Date());
            zoo.setId(1);

            // INSERT
            zooRepository.save(zoo);

            // READ
            var getZoo = zooRepository.read(zoo);
            getZoo.setNombre("Tigre");

            // UPDATE
            zooRepository.update(getZoo);

            // DELETE
            zooRepository.delete(getZoo);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}