/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package resource;

import java.util.Set;
import javax.annotation.PostConstruct;
import javax.inject.Inject;
import javax.ws.rs.core.Application;
import test.Timers;

/**
 *
 * @author pankti
 */
@javax.ws.rs.ApplicationPath("webresources")
public class ApplicationConfig extends Application {
    
    @Inject CICDResource rc;
    
    @Override
    public Set<Class<?>> getClasses() {
        Set<Class<?>> resources = new java.util.HashSet<>();
        addRestResourceClasses(resources);
        return resources;
    }
    
    public ApplicationConfig()
    {
        //new CICDResource().startAllTimers();
    }

    
    @PostConstruct
    public void timersInit(){
        rc.startAllTimers();
    }

    private void addRestResourceClasses(Set<Class<?>> resources) {
        resources.add(filters.AuthFilter.class);
        resources.add(resource.CICDResource.class);
        resources.add(resource.CICDTextResource.class);
        resources.add(resource.UnsecureResource.class);
    }
}
