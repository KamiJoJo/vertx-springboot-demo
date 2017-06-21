package io.kami.demo.service.article;

import io.kami.demo.mapper.ArticleMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service
public class ArticleServiceImpl implements ArticleService {

  @Autowired
  ArticleMapper articleMapper;

  public List<Map<String, Object>> getArticles() {
    return articleMapper.getArticles();
  }
}
