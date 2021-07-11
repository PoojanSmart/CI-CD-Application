/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package config;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Named;
import javax.security.enterprise.identitystore.DatabaseIdentityStoreDefinition;
import javax.security.enterprise.identitystore.Pbkdf2PasswordHash;

/**
 *
 * @author smart
 */
@DatabaseIdentityStoreDefinition(
        dataSourceLookup = "jdbc/projectjndipool",
        callerQuery = "select password from User_Master where Email = ?",
        groupsQuery = "select Email from User_Master where Email = ?",
        hashAlgorithm = UserPasswordHash.class,
        priority = 30
)

@Named
@ApplicationScoped
public class ProjectConfig {

}
