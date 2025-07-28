package vehicle;


import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class VehicleFactoryTest {

    @Test
    void shouldCreateVehicles() {

        Vehicle car = VehicleFactory.create("ABC123", VehicleType.CAR);
        Vehicle bike = VehicleFactory.create("DEF456", VehicleType.BIKE);
        Vehicle mobileHome = VehicleFactory.create("GHI789", VehicleType.MOBILE_HOME);

        assertEquals("ABC123", car.getLicensePlate());
        assertEquals("DEF456", bike.getLicensePlate());
        assertEquals("GHI789", mobileHome.getLicensePlate());
        assertEquals(VehicleType.CAR, car.getVehicleType());
        assertEquals(VehicleType.BIKE, bike.getVehicleType());
        assertEquals(VehicleType.MOBILE_HOME, mobileHome.getVehicleType());
    }

}