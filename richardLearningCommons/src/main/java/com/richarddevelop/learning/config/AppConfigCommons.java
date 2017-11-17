package com.richarddevelop.learning.config;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;

@Configuration
@ComponentScan({ "com.richarddevelop.learning.bean.*" })
@Import({AppConfigDao.class})
public class AppConfigCommons {


}
