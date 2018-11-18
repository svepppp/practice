package io.khasang.ba.controller;

import io.khasang.ba.entity.News;
import io.khasang.ba.service.NewsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/news")
public class NewsController {

    @Autowired
    private NewsService newsService;

    @ResponseBody
    @RequestMapping(value = "/add", method = RequestMethod.POST, produces = "application/json;charset=utf-8")
    public News addNews(@RequestBody News news) {
        newsService.addNews(news);
        return news;
    }

    @ResponseBody
    @RequestMapping(value = "/get/{id}", method = RequestMethod.GET, produces = "application/json;charset=utf-8")
    public News getNewsById(@PathVariable(value = "id") long id) {
        return newsService.getNewsById(id);
    }

    @ResponseBody
    @RequestMapping(value = "/get/all", method = RequestMethod.GET, produces = "application/json;charset=utf-8")
    public List<News> getAllNews() {
        return newsService.getAllNews();
    }

    @ResponseBody
    @RequestMapping(value = "/update", method = RequestMethod.PUT, produces = "application/json;charset=utf-8")
    public News updateNews(@RequestBody News news) {
        return newsService.updateNews(news);
    }

    @ResponseBody
    @RequestMapping(value = "/delete/{id}", method = RequestMethod.DELETE, produces = "application/json;charset=utf-8")
    public News deleteNews(@PathVariable(value = "id") long id) {
        return newsService.deleteNews(id);
    }
}





