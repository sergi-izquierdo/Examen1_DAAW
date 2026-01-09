package daaw_examen.coches_app.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

@Document(collection = "coches")
public class Coche {
    @Id
    private String id;

    @NotBlank(message = "La matrícula es obligatoria")
    private String matricula;

    @NotNull(message = "El año es oblogatorio")
    private Integer year;

    public Coche() {}

    public Coche(String matricula, Integer year){
        this.matricula = matricula;
        this.year = year;
    }

    public String getId() { return id; }
    
    public void setId(String id) {this.id = id;}

    public String getMatricula() {return matricula;}

    public void setMatricula(String matricula) {this.matricula = matricula;}

    public Integer getYear() {return year;}

    public void setYear(Integer year) {this.year = year;}
}
