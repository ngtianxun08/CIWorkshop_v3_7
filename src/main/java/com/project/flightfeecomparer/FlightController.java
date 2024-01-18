/**
 * FlightController.java
 * 
 * This class is a part of the flightfeecomparer project.
 * It is responsible for handling HTTP requests related to flights.
 * 
 */
package com.project.flightfeecomparer;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.amadeus.exceptions.ResponseException;
import com.amadeus.resources.Location;
import com.amadeus.resources.FlightOfferSearch;

@RestController
@RequestMapping("/api/v1")
public class FlightController {
    private final AmadeusConnect amadeusConnect;

    @Autowired
    public FlightController(AmadeusConnect amadeusConnect) {
        this.amadeusConnect = amadeusConnect;
    }

    @GetMapping("/locations")
	public Location[] locations(@RequestParam(required=true) String keyword) throws ResponseException {
		return this.amadeusConnect.location(keyword);
	}

    @GetMapping("/flights")
    public FlightOfferSearch[] flights(@RequestParam(required=true) String origin,
                          @RequestParam(required=true) String destination,
                          @RequestParam(required=true) String departDate,
                          @RequestParam(required=true) String adults,
                          @RequestParam(required = false) String returnDate)
                          throws ResponseException {
        return this.amadeusConnect.flights(origin, destination, departDate, adults, returnDate);
    }
}