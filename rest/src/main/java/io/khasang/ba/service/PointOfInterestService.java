package io.khasang.ba.service;

import io.khasang.ba.entity.Poi;

import java.util.List;

public interface PoiService {

    /**
     * method for add POI
     *
     * @param poi = POI for adding
     * @return created POI
     */
    Poi addPoi(Poi poi);

    /**
     * method for getting POI by specific id
     *
     * @param id - POI's id
     * @return POI by id
     */
    Poi getPoiById(long id);

    /**
     * method gor getting all POIs
     *
     * @return all POIs
     */
    List<Poi> getAllPoi();

    /**
     * method for update POI
     *
     * @param poi - POI's with updated params
     * @return updated cat
     */
    Poi updatePoi(Poi poi);

    /**
     * method for delete POI by id
     *
     * @param id - POI's id for delete
     * @return deleted POI
     */
    Poi deletePoi(long id);
}
