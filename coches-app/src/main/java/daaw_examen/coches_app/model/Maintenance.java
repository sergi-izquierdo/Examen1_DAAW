package daaw_examen.coches_app.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import java.time.LocalDate;

@Document(collection = "maintenances")
public class Maintenance {
    
    @Id
    private String id;

    @NotNull(message = "La fecha es obligatoria")
    private LocalDate date;

    @NotBlank(message = "La descripción es obligatoria")
    private String description;

    @NotNull(message = "El coste es obligatorio")
    private Double cost;

    // Relacion con coche
    private String carId;

    // Constructores, Getters y Setters

    public Maintenance() {}

    public String getId() { return id; }
    public void setId(String id) { this.id = id; }

    public LocalDate getDate() { return date; }
    public void setDate(LocalDate date) { this.date = date; }
    
    public String getDescription() { return description; }
    public void setDescription(String description) { this.description = description; }
    
    public Double getCost() { return cost; }
    public void setCost(Double cost) { this.cost = cost; }
    
    public String getCarId() { return carId; }
    public void setCarId(String carId) { this.carId = carId; }
}
