package daaw_examen.coches_app.repository;

import daaw_examen.coches_app.model.Car;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CarRepository extends MongoRepository<Car, String>{
    
}
