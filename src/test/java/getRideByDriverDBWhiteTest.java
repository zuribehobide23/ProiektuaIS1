import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.junit.Test;

import dataAccess.DataAccess;
import domain.Driver;
import domain.Ride;
import testOperations.TestDataAccess;

public class getRideByDriverDBWhiteTest {

	// sut:system under test
		static DataAccess sut = new DataAccess();

		// additional operations needed to execute the test
		static TestDataAccess testDA = new TestDataAccess();
		
		@Test
		// Gidaria existitzen da eta bidai aktiboak ditu
		public void test1() {
			// Parametroen definizia
		    String driverUsername = "Driver Test";
		    String driverPassword = "123";
		    String rideFrom = "Donostia";
		    String rideTo = "Zarautz";

		    Driver driver = null;
		    Ride ride1 = null;

		    SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
		    Date rideDate = null;

		    try {
		        rideDate = sdf.parse("05/10/2026");
		    } catch (ParseException e) {
		        e.printStackTrace();
		    }

		    // Datu basean definizioak egin
		    try {
		        testDA.open();
		        driver = testDA.createDriver(driverUsername, driverPassword);
		        assertNotNull(driver);
		        
		        ride1 = testDA.createRide(rideFrom, rideTo, rideDate, 5, 10.0, driver);
		        ride1.setActive(true);
		        assertNotNull(ride1);
		        testDA.close();


		        assertNotNull(ride1);

		        //  invoke System Under Test(sut)
		        sut.open();
		        List<Ride> result = sut.getRidesByDriver(driverUsername);
		        sut.close();

		        // Berifikazioa
		        assertTrue(ride1.isActive());
		        assertEquals(1, result.size());
		        assertTrue(result.contains(ride1));
		        assertNotNull(result);
		        
		    } catch (Exception e) {
		        e.printStackTrace();
		        fail("Unexpected exception: " + e.getMessage());
		    } finally {
		    	// Remove the created objects in the database (cascade removing)
		        testDA.open();
		        testDA.removeRide(driverUsername, rideFrom, rideTo, rideDate);
		        testDA.removeDriver(driverUsername);
		        testDA.close();
		    }
		}
		
		@Test
		//Gidaria ez da existitzen
		public void test2() {
			//  invoke System Under Test(sut)
		    try {
		        sut.open();
		        List<Ride> result = sut.getRidesByDriver("NonExistentDriver");
		        sut.close();

		        // Berifikazioa
		        assertNull(result);
		    } catch (Exception e) {
		        e.printStackTrace();
		        fail("Unexpected exception: " + e.getMessage());
		    }
		}
		
		@Test
		//Gidaria exiten da baina ez ditu bidairik
		public void test3() {
			// Parametroen definizioa
		    String driverUsername = "Driver Test";
		    String driverPassword = "123";

		    Driver driver = null;

		    // Datu basean definitu
		    try {
		        testDA.open();
		        driver = testDA.createDriver(driverUsername, driverPassword);
		        testDA.close();

		        assertNotNull(driver);

		        // invoke System Under Test (sut)
		        sut.open();
		        List<Ride> result = sut.getRidesByDriver(driverUsername);
		        sut.close();

		        // Berifikazioa
		        assertNotNull(result);
		        assertTrue(result.isEmpty());
		    } catch (Exception e) {
		        e.printStackTrace();
		        fail("Unexpected exception: " + e.getMessage());
		    } finally {
		        // Remove the created objects in the database (cascade removing)
		        testDA.open();
		        testDA.removeDriver(driverUsername);
		        testDA.close();
		    }
		}
		
		
		@Test
		
		public void test4() {
			// Parametroen definizioa
		    String driverUsername = "Driver Test";
		    String driverPassword = "123";
		    String rideFrom = "Donostia";
		    String rideTo = "Zarautz";

		    Driver driver = null;
		    Ride inactiveRide1 = null;
		    Ride inactiveRide2 = null;

		    SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
		    Date rideDate = null;

		    try {
		        rideDate = sdf.parse("05/10/2026");
		    } catch (ParseException e) {
		        e.printStackTrace();
		    }

		    // Datu basean definizioa
		    try {
		        testDA.open();
		        driver = testDA.createDriver(driverUsername, driverPassword);
		        testDA.close();

		        assertNotNull(driver);

		        testDA.open();
		        inactiveRide1 = testDA.createRide(rideFrom, rideTo, rideDate, 5, 10.0, driver);
		        inactiveRide1.setActive(false); 
		        testDA.close();

		        testDA.open();
		        inactiveRide2 = testDA.createRide("Bilbao", "Vitoria", rideDate, 5, 10.0, driver);
		        inactiveRide2.setActive(false);
		        testDA.close();

		        assertNotNull(inactiveRide1);
		        assertNotNull(inactiveRide2);

		        // invoke System Under Test (sut)
		        sut.open();
		        List<Ride> result = sut.getRidesByDriver(driverUsername);
		        sut.close();

		        // Berifikazioa
		        assertNotNull(result);
		        assertTrue(result.isEmpty());
		    } catch (Exception e) {
		        e.printStackTrace();
		        fail("Unexpected exception: " + e.getMessage());
		    } finally {
		        // Remove the created objects in the database (cascade removing)
		        testDA.open();
		        testDA.removeRide(driverUsername, rideFrom, rideTo, rideDate);
		        testDA.removeRide(driverUsername, "Bilbao", "Vitoria", rideDate);
		        testDA.removeDriver(driverUsername);
		        testDA.close();
		    }
		}		
}