/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entity;

import java.io.Serializable;
import java.util.Collection;
import java.util.Date;
import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.Lob;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author smart
 */
@Entity
@Table(name = "User_Master")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "UserMaster.findAll", query = "SELECT u FROM UserMaster u"),
    @NamedQuery(name = "UserMaster.findByUserId", query = "SELECT u FROM UserMaster u WHERE u.userId = :userId"),
    @NamedQuery(name = "UserMaster.findByLogin", query = "SELECT u FROM UserMaster u WHERE u.email = :email and u.password = :password"),
    @NamedQuery(name = "UserMaster.findByName", query = "SELECT u FROM UserMaster u WHERE u.name = :name"),
    @NamedQuery(name = "UserMaster.findByEmail", query = "SELECT u FROM UserMaster u WHERE u.email = :email"),
    @NamedQuery(name = "UserMaster.findByReceivesEmail", query = "SELECT u FROM UserMaster u WHERE u.receivesEmail = :receivesEmail"),
    @NamedQuery(name = "UserMaster.findByOtp", query = "SELECT u FROM UserMaster u WHERE u.otp = :otp"),
    @NamedQuery(name = "UserMaster.findByOTPSent", query = "SELECT u FROM UserMaster u WHERE u.oTPSent = :oTPSent")})
public class UserMaster implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "UserId")
    private Integer userId;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 30)
    @Column(name = "Name")
    private String name;
    // @Pattern(regexp="[a-z0-9!#$%&'*+/=?^_`{|}~-]+(?:\\.[a-z0-9!#$%&'*+/=?^_`{|}~-]+)*@(?:[a-z0-9](?:[a-z0-9-]*[a-z0-9])?\\.)+[a-z0-9](?:[a-z0-9-]*[a-z0-9])?", message="Invalid email")//if the field contains email address consider using this annotation to enforce field validation
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 50)
    @Column(name = "Email")
    private String email;
    @Basic(optional = false)
    @NotNull
    @Lob
    @Size(min = 1, max = 65535)
    @Column(name = "Password")
    private String password;
    @Basic(optional = false)
    @NotNull
    @Column(name = "ReceivesEmail")
    private boolean receivesEmail;
    @Size(max = 10)
    @Column(name = "OTP")
    private String otp;
    @Column(name = "OTPSent")
    @Temporal(TemporalType.TIMESTAMP)
    private Date oTPSent;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "userId")
    private Collection<RepoCredential> repoCredentialCollection;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "userMaster")
    private Collection<LocalRolesUser> localRolesUserCollection;
    @JoinColumn(name = "GlobalRoleId", referencedColumnName = "Id")
    @ManyToOne(optional = false)
    private GlobalRoles globalRoleId;

    public UserMaster() {
    }

    public UserMaster(Integer userId) {
        this.userId = userId;
    }

    public UserMaster(Integer userId, String name, String email, String password, boolean receivesEmail) {
        this.userId = userId;
        this.name = name;
        this.email = email;
        this.password = password;
        this.receivesEmail = receivesEmail;
    }

    public UserMaster(String name, String email, String password, Boolean receivesEmail, GlobalRoles role) {

        this.name = name;
        this.email = email;
        this.password = password;
        this.receivesEmail = receivesEmail;
        this.globalRoleId = role;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public boolean getReceivesEmail() {
        return receivesEmail;
    }

    public void setReceivesEmail(boolean receivesEmail) {
        this.receivesEmail = receivesEmail;
    }

    public String getOtp() {
        return otp;
    }

    public void setOtp(String otp) {
        this.otp = otp;
    }

    public Date getOTPSent() {
        return oTPSent;
    }

    public void setOTPSent(Date oTPSent) {
        this.oTPSent = oTPSent;
    }

    @XmlTransient
    public Collection<RepoCredential> getRepoCredentialCollection() {
        return repoCredentialCollection;
    }

    public void setRepoCredentialCollection(Collection<RepoCredential> repoCredentialCollection) {
        this.repoCredentialCollection = repoCredentialCollection;
    }

    @XmlTransient
    public Collection<LocalRolesUser> getLocalRolesUserCollection() {
        return localRolesUserCollection;
    }

    public void setLocalRolesUserCollection(Collection<LocalRolesUser> localRolesUserCollection) {
        this.localRolesUserCollection = localRolesUserCollection;
    }

    public GlobalRoles getGlobalRoleId() {
        return globalRoleId;
    }

    public void setGlobalRoleId(GlobalRoles globalRoleId) {
        this.globalRoleId = globalRoleId;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (userId != null ? userId.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof UserMaster)) {
            return false;
        }
        UserMaster other = (UserMaster) object;
        if ((this.userId == null && other.userId != null) || (this.userId != null && !this.userId.equals(other.userId))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "entity.UserMaster[ userId=" + userId + " ]";
    }
    
}
