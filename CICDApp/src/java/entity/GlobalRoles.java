/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entity;

import java.io.Serializable;
import java.util.Collection;
import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
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
@Table(name = "Global_Roles")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "GlobalRoles.findAll", query = "SELECT g FROM GlobalRoles g"),
    @NamedQuery(name = "GlobalRoles.findById", query = "SELECT g FROM GlobalRoles g WHERE g.id = :id"),
    @NamedQuery(name = "GlobalRoles.findByRole", query = "SELECT g FROM GlobalRoles g WHERE g.role = :role"),
    @NamedQuery(name = "GlobalRoles.findByProjectCreate", query = "SELECT g FROM GlobalRoles g WHERE g.projectCreate = :projectCreate"),
    @NamedQuery(name = "GlobalRoles.findByProjectRemove", query = "SELECT g FROM GlobalRoles g WHERE g.projectRemove = :projectRemove"),
    @NamedQuery(name = "GlobalRoles.findByProjectExecute", query = "SELECT g FROM GlobalRoles g WHERE g.projectExecute = :projectExecute"),
    @NamedQuery(name = "GlobalRoles.findByBulid", query = "SELECT g FROM GlobalRoles g WHERE g.bulid = :bulid"),
    @NamedQuery(name = "GlobalRoles.findByBuildHistory", query = "SELECT g FROM GlobalRoles g WHERE g.buildHistory = :buildHistory"),
    @NamedQuery(name = "GlobalRoles.findByCredentialView", query = "SELECT g FROM GlobalRoles g WHERE g.credentialView = :credentialView"),
    @NamedQuery(name = "GlobalRoles.findByCredentialCreate", query = "SELECT g FROM GlobalRoles g WHERE g.credentialCreate = :credentialCreate"),
    @NamedQuery(name = "GlobalRoles.findByCredentialDelete", query = "SELECT g FROM GlobalRoles g WHERE g.credentialDelete = :credentialDelete")})
public class GlobalRoles implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "Id")
    private Integer id;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 30)
    @Column(name = "Role")
    private String role;
    @Basic(optional = false)
    @NotNull
    @Column(name = "ProjectCreate")
    private boolean projectCreate;
    @Basic(optional = false)
    @NotNull
    @Column(name = "ProjectRemove")
    private boolean projectRemove;
    @Basic(optional = false)
    @NotNull
    @Column(name = "ProjectExecute")
    private boolean projectExecute;
    @Basic(optional = false)
    @NotNull
    @Column(name = "Bulid")
    private boolean bulid;
    @Basic(optional = false)
    @NotNull
    @Column(name = "BuildHistory")
    private boolean buildHistory;
    @Basic(optional = false)
    @NotNull
    @Column(name = "CredentialView")
    private boolean credentialView;
    @Basic(optional = false)
    @NotNull
    @Column(name = "CredentialCreate")
    private boolean credentialCreate;
    @Basic(optional = false)
    @NotNull
    @Column(name = "CredentialDelete")
    private boolean credentialDelete;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "globalRoleId")
    private Collection<UserMaster> userMasterCollection;

    public GlobalRoles() {
    }

    public GlobalRoles(Integer id) {
        this.id = id;
    }

    public GlobalRoles(Integer id, String role, boolean projectCreate, boolean projectRemove, boolean projectExecute, boolean bulid, boolean buildHistory, boolean credentialView, boolean credentialCreate, boolean credentialDelete) {
        this.id = id;
        this.role = role;
        this.projectCreate = projectCreate;
        this.projectRemove = projectRemove;
        this.projectExecute = projectExecute;
        this.bulid = bulid;
        this.buildHistory = buildHistory;
        this.credentialView = credentialView;
        this.credentialCreate = credentialCreate;
        this.credentialDelete = credentialDelete;
    }

    public GlobalRoles(String role, Boolean projectCreate, Boolean projectRemove, Boolean projectExecute, Boolean bulid, Boolean buildHistory, Boolean credentialView, Boolean credentialCreate, Boolean credentialDelete) {
        this.role = role;
        this.projectCreate = projectCreate;
        this.projectRemove = projectRemove;
        this.projectExecute = projectExecute;
        this.bulid = bulid;
        this.buildHistory = buildHistory;
        this.credentialView = credentialView;
        this.credentialCreate = credentialCreate;
        this.credentialDelete = credentialDelete;    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public boolean getProjectCreate() {
        return projectCreate;
    }

    public void setProjectCreate(boolean projectCreate) {
        this.projectCreate = projectCreate;
    }

    public boolean getProjectRemove() {
        return projectRemove;
    }

    public void setProjectRemove(boolean projectRemove) {
        this.projectRemove = projectRemove;
    }

    public boolean getProjectExecute() {
        return projectExecute;
    }

    public void setProjectExecute(boolean projectExecute) {
        this.projectExecute = projectExecute;
    }

    public boolean getBulid() {
        return bulid;
    }

    public void setBulid(boolean bulid) {
        this.bulid = bulid;
    }

    public boolean getBuildHistory() {
        return buildHistory;
    }

    public void setBuildHistory(boolean buildHistory) {
        this.buildHistory = buildHistory;
    }

    public boolean getCredentialView() {
        return credentialView;
    }

    public void setCredentialView(boolean credentialView) {
        this.credentialView = credentialView;
    }

    public boolean getCredentialCreate() {
        return credentialCreate;
    }

    public void setCredentialCreate(boolean credentialCreate) {
        this.credentialCreate = credentialCreate;
    }

    public boolean getCredentialDelete() {
        return credentialDelete;
    }

    public void setCredentialDelete(boolean credentialDelete) {
        this.credentialDelete = credentialDelete;
    }

    @XmlTransient
    public Collection<UserMaster> getUserMasterCollection() {
        return userMasterCollection;
    }

    public void setUserMasterCollection(Collection<UserMaster> userMasterCollection) {
        this.userMasterCollection = userMasterCollection;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (id != null ? id.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof GlobalRoles)) {
            return false;
        }
        GlobalRoles other = (GlobalRoles) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "entity.GlobalRoles[ id=" + id + " ]";
    }
    
}
