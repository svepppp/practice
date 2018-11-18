package io.khasang.ba.service;

import io.khasang.ba.entity.History;

import java.util.List;

/**
 * Service layer interface for history management
 */
public interface HistoryService {

    /**
     * Add new history
     *
     * @param newHistory New instance of history
     * @return Added {@link History} instance
     */
    History addHistory(History newHistory);

    /**
     * Get history by id
     *
     * @param id Identifier of the desired history
     * @return Found {@link History} instance
     */
    History getHistoryById(long id);

    /**
     * Update existing history with new instance
     *
     * @param updatedHistory Updated history instance
     * @return Updated {@link History} instance
     */
    History updateHistory(History updatedHistory);

    /**
     * Get all histories
     *
     * @return {@link List} instance of all historys
     */
    List<History> getAllHistories();

    /**
     * Delete history by id
     *
     * @param id Identifier of the history which should be deleted
     * @return Deleted {@link History} instance
     */
    History deleteHistory(long id);
}
