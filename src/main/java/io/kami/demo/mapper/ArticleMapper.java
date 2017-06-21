package io.kami.demo.mapper;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;
import java.util.Map;

@Mapper
public interface ArticleMapper {

  @Select("SELECT * FROM ARTICLES")
  List<Map<String, Object>> getArticles();
}
