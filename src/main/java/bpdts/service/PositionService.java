package bpdts.service;

// Implement the service layer for the Person model

// Geodesic libraries are used to deal with coordinates and distances

import bpdts.model.Position;
import lombok.extern.slf4j.Slf4j;
import net.sf.geographiclib.Geodesic;
import org.springframework.stereotype.Service;

@Slf4j
@Service
public class PositionService {

        Geodesic pos;    // Long. 51.5098, -0.1180 Lat.
        // Conversion from Miles to Kilometres
        public static final double MILES_TO_KM = 1.60934;

        // Info obtained from https://www.latlong.net/place/london-the-uk-14153.html#:~:text=The%20latitude%20of%20London%2C%20the,%C2%B0%207'%205.1312''%20W.
        public static final double LONDON_LATITUDE = 51.5098;
        public static final double LONDON_LONGITUDE = -0.1180;

        public static final Position LONDON = new Position(LONDON_LATITUDE, LONDON_LONGITUDE);

        public PositionService() {
            pos = new Geodesic(Geodesic.WGS84.EquatorialRadius(), Geodesic.WGS84.Flattening());
        }

        // Calculate the miles between two positions
        public Double milesBetween(Position from, Position to) {

            double distanceInMetres = pos.Inverse(from.latitude, from.longitude, to.latitude, to.longitude).s12;
            double distanceInKm = distanceInMetres / 1000.0;
            double distanceInMiles = kmToMiles(distanceInKm);
            log.debug("{} to {} is {} miles", from, to, distanceInMiles);
            return distanceInMiles;
        }

        protected static double milesToKm(double miles) {
            return miles * MILES_TO_KM;
        }
        protected static double kmToMiles(double km) {
            return km / MILES_TO_KM;
        }
}
