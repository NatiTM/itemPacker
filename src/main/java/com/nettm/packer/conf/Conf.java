package com.nettm.packer.conf;

import com.nettm.packer.FileUtil;
import com.nettm.packer.Parser;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

@Configuration
@ComponentScan
public class Conf {

    @Bean
    FileUtil fileIO (){
        return new FileUtil();
    }

    @Bean
    Parser parser (){
        return new Parser();
    }

}
