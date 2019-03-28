package com.mobiquityinc.packer.conf;

import com.mobiquityinc.packer.FileUtil;
import com.mobiquityinc.packer.Parser;
import com.mobiquityinc.packer.packer.Packer;
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
