import static org.junit.Assert.*;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;

import java.sql.Date;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.List;

import org.junit.Test;

import dataAccess.DataAccess;
import domain.Driver;
import domain.Ride;
import domain.Traveler;
import testOperations.TestDataAccess;
import org.junit.Test;

public class getRideByDriverDBBlackTest {

	// sut: system under test.
    static DataAccess sut = new DataAccess();
    // additional operations needed to execute the test
    static TestDataAccess testDA = new TestDataAccess();
    
    @Test
    public void testDriverExistsWithActiveRides() {
        // Definir los parámetros
        String driverUsername = "Driver Test";
        String driverPassword = "123";
        
        Driver driver = null;
        Ride ride1 = null, ride2 = null;

        // Inicializar la base de datos de prueba
        testDA.open();
        driver = testDA.createDriver(driverUsername, driverPassword);
        testDA.close();
        
        LocalDate localDate = LocalDate.of(2026, 10, 5); // 5 de octubre de 2026
        Date rideDate = java.sql.Date.valueOf(localDate);

        // Crear rides activas
        testDA.open();
        ride1 = testDA.createRide("Donostia", "Zarautz", rideDate, 4, 10, driver);
        ride2 = testDA.createRide("Bilbao", "Vitoria", rideDate, 3, 12, driver);
        ride1.setActive(true);  // Ride activa
        ride2.setActive(true);  // Ride activa
        testDA.close();

        try {
        	
        	
            // Llamar al sistema bajo prueba (sut)
            List<Ride> rides = sut.getRidesByDriver(driverUsername);
            
            // Verificar los resultados
            assertNotNull(rides);
            assertEquals(2, rides.size());
            assertTrue(rides.contains(ride1));
            assertTrue(rides.contains(ride2));
        } catch (Exception e) {
            fail("Unexpected exception: " + e.getMessage());
        } finally {
            // Limpiar los datos de prueba
            testDA.open();
            testDA.removeRide(driverUsername, "Donostia", "Zarautz", rideDate);
            testDA.removeRide(driverUsername, "Bilbao", "Vitoria", rideDate);
            testDA.removeDriver(driverUsername);
            testDA.close();
        }
    } 
}