import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;
import static org.mockito.Mockito.*;

import java.sql.Date;
import java.util.Collections;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.TypedQuery;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import dataAccess.DataAccess;
import domain.Driver;
import domain.Ride;

public class GetRidesByDriverMockWhiteTest {

    @Mock
    private DataAccess sut; // Aquí puedes simular el objeto sut (si es necesario)

    @Mock
    private EntityManagerFactory entityManagerFactory;

    @Mock
    private EntityManager db;

    @Mock
    private EntityTransaction et;

    @Mock
    private TypedQuery<Driver> mockedDriverQuery;

    @Mock
    private TypedQuery<Ride> mockedRideQuery;

    @Mock
    private Driver driverMock; // Mock del driver

    @Before
    public void init() {
        MockitoAnnotations.openMocks(this);
        when(entityManagerFactory.createEntityManager()).thenReturn(db);
        when(db.getTransaction()).thenReturn(et);
    }

    @After
    public void tearDown() {
        // Aquí puedes cerrar cualquier recurso si es necesario
    }
    
    @Test
    public void test1() {
        // Define parámetros
        String driverUsername = "Driver Test";
        Ride ride = new Ride("Donostia", "Zarautz", new Date(0), 5, 10.0, driverMock);

        // Configurar el mock para devolver el driver
        when(db.createQuery(anyString(), eq(Driver.class))).thenReturn(mockedDriverQuery);
        when(mockedDriverQuery.getResultList()).thenReturn(Collections.singletonList(driverMock)); // Simulando que el conductor existe

        // Simular que el conductor tiene viajes
        when(driverMock.getCreatedRides()).thenReturn(Collections.singletonList(ride)); // Aquí usamos el mock de Driver

        try {
            // Invocar el sistema bajo prueba (sut)
            sut.open();
            List<Ride> result = sut.getRidesByDriver(driverUsername);
            sut.close();

            // Verificación
            assertNotNull(result);
            assertEquals(1, result.size());
            assertTrue(result.contains(ride));
        } catch (Exception e) {
            e.printStackTrace();
            fail("Unexpected exception: " + e.getMessage());
        }
    }
    
}