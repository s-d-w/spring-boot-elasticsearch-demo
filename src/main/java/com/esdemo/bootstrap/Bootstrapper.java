package com.esdemo.bootstrap;

import java.util.List;
import java.util.logging.Logger;
import javax.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.context.annotation.Configuration;

@Configuration
@ConditionalOnProperty(name = "esdemo.bootstrap.enable")
public class Bootstrapper {
    
    private static final Logger LOG = Logger.getLogger(Bootstrapper.class.getName());   
    
    @Autowired
    private List<Bootstrap> items;
    
    @PostConstruct
    public void bootstrap() {
        LOG.info("Running bootstrap routine..");
        items.stream().forEachOrdered(Bootstrap::bootstrap);
    }
}


