package io.khasang.ba.service.impl;

import io.khasang.ba.dao.HistoryDao;
import io.khasang.ba.entity.History;
import io.khasang.ba.service.HistoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

/**
 * Implementation of HistoryService based on DAO-layer utilization
 */
@Service
public class HistoryServiceImpl implements HistoryService {

    @Autowired
    private HistoryDao historyDao;

    /**
     * Add new history
     *
     * @param newHistory New instance of history
     * @return Added {@link History} instance
     */
    @Override
    public History addHistory(History newHistory) {
        newHistory.setTimestamp(LocalDateTime.now());
        return historyDao.add(newHistory);
    }

    /**
     * Get history by id
     *
     * @param id Identifier of the desired history
     * @return Found {@link History} instance
     */
    @Override
    public History getHistoryById(long id) {
        return historyDao.getById(id);
    }

    /**
     * Update existing history with new instance
     *
     * @param updatedHistory Updated history instance
     * @return Updated {@link History} instance
     */
    @Override
    public History updateHistory(History updatedHistory) {
        updatedHistory.setTimestamp(LocalDateTime.now());
        return historyDao.update(updatedHistory);
    }

    /**
     * Get all histories
     *
     * @return {@link List} instance of all historys
     */
    @Override
    public List<History> getAllHistories() {
        return historyDao.getAll();
    }

    /**
     * Delete history by id
     *
     * @param id Identifier of the history which should be deleted
     * @return Deleted {@link History} instance
     */
    @Override
    public History deleteHistory(long id) {
        return historyDao.delete(getHistoryById(id));
    }
}
