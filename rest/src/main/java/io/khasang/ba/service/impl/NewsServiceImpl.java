package io.khasang.ba.service.impl;

import io.khasang.ba.dao.NewsDao;
import io.khasang.ba.entity.News;
import io.khasang.ba.service.NewsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class NewsServiceImpl implements NewsService {

    @Autowired
    private NewsDao newsDao;

    @Override
    public News addNews(News news) {
        return newsDao.add(news);
    }

    @Override
    public News getNewsById(long id) {
        return newsDao.getById(id);
    }

    @Override
    public List<News> getAllNews() {
        return newsDao.getAll();
    }

    @Override
    public News updateNews(News news) {
        return newsDao.update(news);
    }

    @Override
    public News deleteNews(long id) {
        return newsDao.delete(getNewsById(id));
    }
}
