package io.khasang.ba.service;

import io.khasang.ba.entity.News;

import java.util.List;

public interface NewsService {
    /**
     * method for add news
     *
     * @param news = news for adding
     * @return created news
     */
    News addNews(News news);

    /**
     * method for getting news by specific id
     *
     * @param id - news's id
     * @return news by id
     */
    News getNewsById(long id);

    /**
     * method for getting all news
     *
     * @return all news
     */
    List<News> getAllNews();

    /**
     * method for update news
     *
     * @param news - news's with updated params
     * @return updated news
     */
    News updateNews(News news);

    /**
     * method for delete news by id
     *
     * @param id - news's id for delete
     * @return deleted news
     */
    News deleteNews(long id);
}
