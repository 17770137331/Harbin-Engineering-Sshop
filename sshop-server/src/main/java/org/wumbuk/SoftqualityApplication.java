package org.wumbuk;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.ServletComponentScan;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@ServletComponentScan("org.chatcat.filter")
@SpringBootApplication
//开启事务管理
@EnableTransactionManagement
//与dao层的@Mapper二选一写上即可，主要的作用是扫描包
//@MapperScan("org.chatcat.dao")
public class SoftqualityApplication {

    public static void main(String[] args) {
        SpringApplication.run(SoftqualityApplication.class, args);
    }

}
