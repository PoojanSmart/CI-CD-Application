/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package filters;

import java.io.IOException;
import javax.ws.rs.client.ClientRequestContext;
import javax.ws.rs.client.ClientRequestFilter;
import javax.ws.rs.client.ClientResponseContext;
import javax.ws.rs.client.ClientResponseFilter;
import javax.ws.rs.container.ContainerRequestContext;
import javax.ws.rs.container.ContainerResponseContext;
import javax.ws.rs.container.ContainerResponseFilter;
import javax.ws.rs.container.PreMatching;
import static javax.ws.rs.core.HttpHeaders.AUTHORIZATION;
import javax.ws.rs.ext.Provider;

/**
 *
 * @author smart
 */
@Provider
@PreMatching
public class AuthFilter implements ClientRequestFilter {
    public static String mytoken;
    //@Inject TokenProvider verifier;
    
    public AuthFilter(String token) {      
        mytoken = token;
     }

    

    @Override
    public void filter(ClientRequestContext requestContext) throws IOException {
        requestContext.getHeaders().add(AUTHORIZATION,"Bearer "+mytoken);
    }
    
}
