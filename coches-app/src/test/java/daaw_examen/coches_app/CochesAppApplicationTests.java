package daaw_examen.coches_app;

import daaw_examen.coches_app.model.Car;
import daaw_examen.coches_app.repository.CarRepository;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.test.web.server.LocalServerPort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class CochesAppApplicationTests {

	@LocalServerPort
	private int port;

	@Autowired
	private TestRestTemplate restTemplate;

	@Autowired
	private CarRepository carRepository;

	@AfterEach
	void tearDown() {
		// Limpiar la base de datos después de cada test
		carRepository.deleteAll();
	}

	@Test
	void testCrearYRecuperarCoche() {
		// 1. Crear un coche mediante POST /cars
		String urlCrear = "http://localhost:" + port + "/cars";
		Car newCar = new Car("1234-TEST", 2024);

		ResponseEntity<Car> respuestaPost = restTemplate.postForEntity(urlCrear, newCar, Car.class);

		// 2. Verificar respuesta del POST
		// a. Código 201 Created
		assertThat(respuestaPost.getStatusCode()).isEqualTo(HttpStatus.CREATED);
		
		// b. Contiene el identificador
		Car createdCar = respuestaPost.getBody();
		assertThat(createdCar).isNotNull();
		assertThat(createdCar.getId()).isNotNull();
		String idCoche = createdCar.getId();

		// 3. Recuperar el coche mediante GET /cars/{id}
		String urlGet = "http://localhost:" + port + "/cars/" + idCoche;
		ResponseEntity<Car> respuestaGet = restTemplate.getForEntity(urlGet, Car.class);

		// 4. Verificar respuesta del GET
		// a. Código 200 OK
		assertThat(respuestaGet.getStatusCode()).isEqualTo(HttpStatus.OK);
		
		// b. Datos coinciden
		Car recoveredCar = respuestaGet.getBody();
		assertThat(recoveredCar).isNotNull();
		assertThat(recoveredCar.getPlate()).isEqualTo("1234-TEST");
		assertThat(recoveredCar.getYear()).isEqualTo(2024);
	}
}
