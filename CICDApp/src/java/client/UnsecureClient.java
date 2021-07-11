/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package client;

import javax.ws.rs.ClientErrorException;
import javax.ws.rs.client.Client;
import javax.ws.rs.client.WebTarget;

/**
 * Jersey REST client generated for REST resource:UnsecureResource
 * [notsecure]<br>
 * USAGE:
 * <pre>
 *        UnsecureClient client = new UnsecureClient();
 *        Object response = client.XXX(...);
 *        // do whatever with response
 *        client.close();
 * </pre>
 *
 * @author smart
 */
public class UnsecureClient {

    private WebTarget webTarget;
    private Client client;
    private static final String BASE_URI = "https://localhost:8181/CICDApp/webresources";
    
    static {
        //for localhost testing only
        javax.net.ssl.HttpsURLConnection.setDefaultHostnameVerifier(
                new javax.net.ssl.HostnameVerifier() {

            public boolean verify(String hostname,
                    javax.net.ssl.SSLSession sslSession) {
                if (hostname.equals("localhost")) {
                    return true;
                }
                return false;
            }
        });
    }
    
    public UnsecureClient() {
        client = javax.ws.rs.client.ClientBuilder.newClient();
        webTarget = client.target(BASE_URI).path("notsecure");
    }

    public <T> T loginUser(Class<T> responseType, String email, String password) throws ClientErrorException {
        return webTarget.path(java.text.MessageFormat.format("loginUser/{0}/{1}", new Object[]{email, password})).request().post(null, responseType);
    }

    public void registerUser(String name, String email, String password, String receivesEmail) throws ClientErrorException {
        webTarget.path(java.text.MessageFormat.format("registerUser/{0}/{1}/{2}/{3}", new Object[]{name, email, password, receivesEmail})).request().post(null);
    }

    public void registerUserWithRole(String name, String email, String password, String receivesEmail, String golbalRoleId) throws ClientErrorException {
        webTarget.path(java.text.MessageFormat.format("registerUserWithRole/{0}/{1}/{2}/{3}/{4}", new Object[]{name, email, password, receivesEmail, golbalRoleId})).request().post(null);
    }

    public String changePassword(String email, String old_password, String new_password) throws ClientErrorException {
        return webTarget.path(java.text.MessageFormat.format("changePassword/{0}/{1}/{2}", new Object[]{email, old_password, new_password})).request().post(null, String.class);
    }

    public void close() {
        client.close();
    }
    
}
