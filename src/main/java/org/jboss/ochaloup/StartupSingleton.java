package org.jboss.ochaloup;

import javax.annotation.PostConstruct;
import javax.ejb.Singleton;
import javax.ejb.Startup;

@Singleton
@Startup
public class StartupSingleton {
    @PostConstruct
    public void postConstruct() {
        System.out.println("Singleton postconsturct");
    }
}
