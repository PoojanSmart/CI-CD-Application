/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import javax.inject.Named;
import javax.enterprise.context.RequestScoped;

/**
 *
 * @author pankti
 */
@Named(value = "formatBean")
@RequestScoped
public class FormatBean {

    /**
     * Creates a new instance of FormatBean
     */
    public FormatBean() {
    }
    
    public String TitileCase(String val)
    {
           return Character.toUpperCase(val.charAt(0)) + val.substring(1);
    }
    
}
