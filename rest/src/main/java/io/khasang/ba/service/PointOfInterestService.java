package io.khasang.ba.service;

import io.khasang.ba.entity.PointOfInterest;

import java.util.List;

public interface PointOfInterestService {

    /**
     * method for add POI
     *
     * @param pointOfInterest = POI for adding
     * @return created POI
     */
    PointOfInterest addPointOfInterest(PointOfInterest pointOfInterest);

    /**
     * method for getting POI by specific id
     *
     * @param id - POI's id
     * @return POI by id
     */
    PointOfInterest getPointOfInterestById(long id);

    /**
     * method gor getting all POIs
     *
     * @return all POIs
     */
    List<PointOfInterest> getAllPointOfInterest();

    /**
     * method for update POI
     *
     * @param pointOfInterest - POI's with updated params
     * @return updated cat
     */
    PointOfInterest updatePointOfInterest(PointOfInterest pointOfInterest);

    /**
     * method for delete POI by id
     *
     * @param id - POI's id for delete
     * @return deleted POI
     */
    PointOfInterest deletePointOfInterest(long id);
}
