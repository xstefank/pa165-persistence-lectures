package io.xstefank;

import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class App {

    public static void main(String[] args) {
        ApplicationContext applicationContext = new AnnotationConfigApplicationContext(PA165PersistenceConfig.class);

        applicationContext.getBean(PA165.class).start();
    }
}
