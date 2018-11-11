package io.khasang.ba.service;

import io.khasang.ba.entity.PointOfInterest;

import java.util.List;

public interface PointOfInterestService {

    /**
     * method for add PointOfInterest
     *
     * @param pointOfInterest = PointOfInterest for adding
     * @return created PointOfInterest
     */
    PointOfInterest addPointOfInterest(PointOfInterest pointOfInterest);

    /**
     * method for getting PointOfInterest by specific id
     *
     * @param id - PointOfInterest's id
     * @return PointOfInterest by id
     */
    PointOfInterest getPointOfInterestById(long id);

    /**
     * method gor getting all PointOfInterests
     *
     * @return all PointOfInterests
     */
    List<PointOfInterest> getAllPointOfInterest();

    /**
     * method for update PointOfInterest
     *
     * @param pointOfInterest - PointOfInterest's with updated params
     * @return updated pointOfInterest
     */
    PointOfInterest updatePointOfInterest(PointOfInterest pointOfInterest);

    /**
     * method for delete PointOfInterest by id
     *
     * @param id - PointOfInterest's id for delete
     * @return deleted PointOfInterest
     */
    PointOfInterest deletePointOfInterest(long id);
}
