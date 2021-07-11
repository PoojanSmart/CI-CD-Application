/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entity;

import java.io.Serializable;
import java.util.Collection;
import javax.persistence.Basic;
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
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author smart
 */
@Entity
@Table(name = "Repo_Credential")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "RepoCredential.findAll", query = "SELECT r FROM RepoCredential r"),
    @NamedQuery(name = "RepoCredential.findByCredential", query = "SELECT r FROM RepoCredential r WHERE r.username = :username and r.password = :password"),
    @NamedQuery(name = "RepoCredential.findByCredentialId", query = "SELECT r FROM RepoCredential r WHERE r.credentialId = :credentialId"),
    @NamedQuery(name = "RepoCredential.findByUsername", query = "SELECT r FROM RepoCredential r WHERE r.username = :username")})
public class RepoCredential implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "CredentialId")
    private Integer credentialId;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 100)
    @Column(name = "username")
    private String username;
    @Basic(optional = false)
    @NotNull
    @Lob
    @Size(min = 1, max = 65535)
    @Column(name = "Password")
    private String password;
    @JoinColumn(name = "UserId", referencedColumnName = "UserId")
    @ManyToOne(optional = false)
    private UserMaster userId;
    @OneToMany(mappedBy = "credentialId")
    private Collection<GitMaster> gitMasterCollection;

    public RepoCredential() {
    }

    public RepoCredential(Integer credentialId) {
        this.credentialId = credentialId;
    }
    public RepoCredential(String username, String password) {
        this.username = username;
        this.password = password;
    }

    public RepoCredential(Integer credentialId, String username, String password) {
        this.credentialId = credentialId;
        this.username = username;
        this.password = password;
    }

    public Integer getCredentialId() {
        return credentialId;
    }

    public void setCredentialId(Integer credentialId) {
        this.credentialId = credentialId;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public UserMaster getUserId() {
        return userId;
    }

    public void setUserId(UserMaster userId) {
        this.userId = userId;
    }

    @XmlTransient
    public Collection<GitMaster> getGitMasterCollection() {
        return gitMasterCollection;
    }

    public void setGitMasterCollection(Collection<GitMaster> gitMasterCollection) {
        this.gitMasterCollection = gitMasterCollection;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (credentialId != null ? credentialId.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof RepoCredential)) {
            return false;
        }
        RepoCredential other = (RepoCredential) object;
        if ((this.credentialId == null && other.credentialId != null) || (this.credentialId != null && !this.credentialId.equals(other.credentialId))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "entity.RepoCredential[ credentialId=" + credentialId + " ]";
    }
    
}
