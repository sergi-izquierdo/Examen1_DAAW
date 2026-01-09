package daaw_examen.coches_app.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

@Document(collection = "cars")
public class Car {
    @Id
    private String id;

    @NotBlank(message = "La matrícula es obligatoria")
    private String plate;

    @NotNull(message = "El año es oblogatorio")
    private Integer year;

    // Constructores, Getters y Setters

    public Car() {}

    public Car(String plate, Integer year){
        this.plate = plate;
        this.year = year;
    }

    public String getId() { return id; }
    public void setId(String id) {this.id = id;}

    public String getPlate() {return plate;}
    public void setPlate(String plate) {this.plate = plate;}

    public Integer getYear() {return year;}
    public void setYear(Integer year) {this.year = year;}
}
