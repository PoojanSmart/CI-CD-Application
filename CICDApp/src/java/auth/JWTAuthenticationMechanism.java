package auth;

import static auth.Constants.AUTHORIZATION_HEADER;
import static auth.Constants.BEARER;
import io.jsonwebtoken.ExpiredJwtException;
import java.io.Serializable;
import java.util.Enumeration;
import java.util.logging.Logger;
import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.inject.Named;
import javax.security.enterprise.AuthenticationStatus;
import javax.security.enterprise.authentication.mechanism.http.HttpAuthenticationMechanism;
import javax.security.enterprise.authentication.mechanism.http.HttpMessageContext;
import javax.security.enterprise.credential.Credential;
import javax.security.enterprise.credential.Password;
import javax.security.enterprise.credential.UsernamePasswordCredential;
import javax.security.enterprise.identitystore.CredentialValidationResult;
import javax.security.enterprise.identitystore.IdentityStoreHandler;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import model.LoginBean;

@RequestScoped
@Named
public class JWTAuthenticationMechanism implements HttpAuthenticationMechanism, Serializable {

    private static final Logger LOGGER = Logger.getLogger(JWTAuthenticationMechanism.class.getName());
 
    @Inject  private IdentityStoreHandler identityStoreHandler;
    @Inject  private TokenProvider tokenProvider;
    @Inject private LoginBean loginBean;

    String token;
    String name,password;
    @Override
    public AuthenticationStatus validateRequest(HttpServletRequest request, HttpServletResponse response, HttpMessageContext context) {

        if(request.getRequestURI().contains("/webhook"))
            return context.doNothing();
        if(request.getRequestURI().replace(context.getRequest().getContextPath(), "").contains("/webresources/cicd/"))
        {            
            Enumeration<String> s = context.getRequest().getHeaders(AUTHORIZATION_HEADER);
            token = s.nextElement().split(" ")[1];
        }
        else
            token = (String) context.getRequest().getSession().getAttribute("token");
        //String token = extractToken(context);

        if (token==null && loginBean.getUsername()!=null) {
         //    System.out.println("JWTAuthenticationMechanism - in login if");
              
            Credential credential = new UsernamePasswordCredential(loginBean.getUsername(),new Password(loginBean.getPassword()));       
            CredentialValidationResult result = identityStoreHandler.validate(credential);
      
            if (result.getStatus() == CredentialValidationResult.Status.VALID) {
                
               AuthenticationStatus status= createToken(result, context);
               loginBean.setStatus(status);
               return status;
            }
            else
            {
                loginBean.setMessage("Login Error : Either Login or Password is Wrong. Try Again");
                loginBean.setStatus(AuthenticationStatus.SEND_FAILURE);
            }
            
            // if the authentication fastatusiled, we return the unauthorized status in the http response
      //      return context.responseUnauthorized();
        }
        
         else if (token != null) 
         {
             if(validateToken(token, context) == AuthenticationStatus.NOT_DONE || validateToken(token, context) == AuthenticationStatus.NOT_DONE)
             {
                 try
                 {request.getServletContext().getRequestDispatcher("/index.jsf").forward(request, response);}
                 catch(Exception e){}
             }
             else if(request.getRequestURL().toString().contains("logout.jsf"))
             {
                 context.getRequest().getSession().invalidate();
                 context.getRequest().getSession().removeAttribute("token");
                 Cookie x = new Cookie("JSESSIONID","");
                 x.setValue("");
                 context.getResponse().addCookie(x);
            try
                 {                 context.getResponse().sendRedirect("CICDApp/common/home.jsf");
    /*request.getServletContext().getRequestDispatcher("/index.jsf").forward(request, response);*/}
                 
                 catch(Exception e){}
                 
             }
            return validateToken(token, context);
        } else if (context.isProtected()) {
            // A protected resource is a resource for which a constraint has been defined.
            // if there are no credentials and the resource is protected, we response with unauthorized status
            return context.responseUnauthorized();
        }
        // there are no credentials AND the resource is not protected, 
        // SO Instructs the container to "do nothing"
        if(token == null && loginBean.getUsername()==null && !request.getRequestURI().contains("register.jsf") 
                && !request.getRequestURI().contains("forgotpassword.jsf")
                && !request.getRequestURI().contains("resetpassword.jsf")
                && !request.getRequestURI().contains("verifyotp.jsf")
                && !request.getRequestURI().contains("/webresources/cicd1/")
                && !request.getRequestURI().contains("/webresources/notsecure/")
                &&!request.getRequestURI().contains("index.jsf") && !request.getRequestURI().contains("javax.faces.resource"))
        {
                String requestUrl = request.getRequestURI().replace(context.getRequest().getContextPath(), "");
                try
                {
                    loginBean.setRedirectionURL(requestUrl);
                    request.getServletContext().getRequestDispatcher("/index.jsf").forward(request, response);
                }

                catch(Exception e){}
        }

        return context.doNothing();
    }

     private AuthenticationStatus validateToken(String token, HttpMessageContext context) {
        try {
            if (tokenProvider.validateToken(token)) {
                JWTCredential credential = tokenProvider.getCredential(token);
                  context.getRequest().getSession().setAttribute("token", token);
          
            //System.out.println("JWTAuthenticationMechanism-Token Validated");
                return context.notifyContainerAboutLogin(credential.getPrincipal(), credential.getAuthorities());
            
            }
            // if token invalid, response with unauthorized status
            return context.responseUnauthorized();
        } catch (ExpiredJwtException eje) {
//            LOGGER.log(Level.INFO, "Security exception for user {0} - {1}", new String[]{eje.getClaims().getSubject(), eje.getMessage()});
            return context.responseUnauthorized();
        }
    }

     
    private AuthenticationStatus createToken(CredentialValidationResult result, HttpMessageContext context) {
        if (!isRememberMe(context)) {
            // if (true) {
            String jwt = tokenProvider.createToken(result.getCallerPrincipal().getName(), result.getCallerGroups(), false);
          
            context.getResponse().setHeader(Constants.AUTHORIZATION_HEADER, Constants.BEARER + jwt);
           
            
            
            
            //System.out.println("iToken="+jwt);
            context.getRequest().getSession().setAttribute("token", jwt);
        }
        //System.out.println("JWTAuthenticationMechanism - Token Created");
        return context.notifyContainerAboutLogin(result.getCallerPrincipal(), result.getCallerGroups());
    }

    
    private String extractToken(HttpMessageContext context) {
        String authorizationHeader = context.getRequest().getHeader(AUTHORIZATION_HEADER);
        if (authorizationHeader != null && authorizationHeader.startsWith(BEARER)) {
            String token = authorizationHeader.substring(BEARER.length(), authorizationHeader.length());
     //System.out.println("JWTAuthenticationMechanism - Extract Tokens");
      context.getRequest().getSession().setAttribute("token", token);
          
            return token;
        }
        return null;
    }


    public Boolean isRememberMe(HttpMessageContext context) {
        return Boolean.valueOf(context.getRequest().getParameter("rememberme"));
    }

}
