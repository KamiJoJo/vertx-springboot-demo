package io.kami.demo.service.example;

import io.kami.demo.mapper.ExampleMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ExampleServiceImpl implements ExampleService {

  @Autowired
  private ExampleMapper exampleMapper;

  @Override
  public String getCurrentDataTime() {
    return exampleMapper.getCurrentDateTime();
  }
}
