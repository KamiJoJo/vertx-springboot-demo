package io.kami.demo.mapper;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

@Mapper
public interface ExampleMapper {

  @Select("SELECT NOW()")
  String getCurrentDateTime();
}
