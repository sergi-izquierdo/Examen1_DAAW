package daaw_examen.coches_app.controller;



import daaw_examen.coches_app.model.Car;
import daaw_examen.coches_app.model.Maintenance;
import daaw_examen.coches_app.repository.CarRepository;
import daaw_examen.coches_app.repository.MaintenanceRepository;
import daaw_examen.coches_app.exception.ResourceNotFoundException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import jakarta.validation.Valid;
import java.util.List;

@RestController
public class CarController {

    @Autowired
    private CarRepository carRepository;

    @Autowired
    private MaintenanceRepository maintenanceRepository;

    // 1. POST /cars -> Crear un nuevo coche
    @PostMapping("/cars")
    public ResponseEntity<Car> createCar(@Valid @RequestBody Car car) {
        Car newCar = carRepository.save(car);
        return new ResponseEntity<>(newCar, HttpStatus.CREATED);
    }

    // 2. GET /cars/{id} -> Obtener un coche por identificador
    @GetMapping("/cars/{id}")
    public ResponseEntity<Car> getCar(@PathVariable String id) {
        Car car = carRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Coche con id: " + id + " no encontrado"));
        return new ResponseEntity<>(car, HttpStatus.OK);
    }

    // 3. POST /cars/{id}/maintenances -> Crear y asociar un mantenimiento a un coche
    @PostMapping("/cars/{id}/maintenances")
    public ResponseEntity<Maintenance> addMaintenance(@PathVariable String id, @Valid @RequestBody Maintenance maintenance) {
        // Primero verificamos que el coche exista
        if (!carRepository.existsById(id)) {
            throw new ResourceNotFoundException("No se puede añadir mantenimiento. Coche con id: " + id + " no encontrado");
        }

        // Asociamos el mantenimiento al coche
        maintenance.setCarId(id);
        Maintenance newMaintenance = maintenanceRepository.save(maintenance);
        
        return new ResponseEntity<>(newMaintenance, HttpStatus.CREATED);
    }

    // 4. GET /maintenances -> Listar todos los mantenimientos existentes
    @GetMapping("/maintenances")
    public ResponseEntity<List<Maintenance>> listMaintenances() {
        List<Maintenance> maintenances = maintenanceRepository.findAll();
        return new ResponseEntity<>(maintenances, HttpStatus.OK);
    }
}