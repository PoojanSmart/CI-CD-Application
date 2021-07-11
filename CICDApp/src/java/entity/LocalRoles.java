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
import javax.persistence.JoinColumn;
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
@Table(name = "Local_Roles")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "LocalRoles.findAll", query = "SELECT l FROM LocalRoles l"),
    @NamedQuery(name = "LocalRoles.findById", query = "SELECT l FROM LocalRoles l WHERE l.id = :id"),
    @NamedQuery(name = "LocalRoles.findByRole", query = "SELECT l FROM LocalRoles l WHERE l.role = :role"),
    @NamedQuery(name = "LocalRoles.findByProjectCreate", query = "SELECT l FROM LocalRoles l WHERE l.projectCreate = :projectCreate"),
    @NamedQuery(name = "LocalRoles.findByProjectRemove", query = "SELECT l FROM LocalRoles l WHERE l.projectRemove = :projectRemove"),
    @NamedQuery(name = "LocalRoles.findByProjectExecute", query = "SELECT l FROM LocalRoles l WHERE l.projectExecute = :projectExecute"),
    @NamedQuery(name = "LocalRoles.findByBuild", query = "SELECT l FROM LocalRoles l WHERE l.build = :build"),
    @NamedQuery(name = "LocalRoles.findByBuildHistory", query = "SELECT l FROM LocalRoles l WHERE l.buildHistory = :buildHistory"),
     @NamedQuery(name = "LocalRoles.findByProjectId", query = "SELECT l FROM LocalRoles l WHERE l.projectId.projectId = :projectId")
})

public class LocalRoles implements Serializable {

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
    @Column(name = "Build")
    private boolean build;
    @Basic(optional = false)
    @NotNull
    @Column(name = "BuildHistory")
    private boolean buildHistory;
    @JoinColumn(name = "ProjectId", referencedColumnName = "ProjectId")
    @ManyToOne(optional = false)
    private ProjectMaster projectId;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "localRoles")
    private Collection<LocalRolesUser> localRolesUserCollection;

    public LocalRoles() {
    }

    public LocalRoles(Integer id) {
        this.id = id;
    }

    public LocalRoles(Integer id, String role, boolean projectCreate, boolean projectRemove, boolean projectExecute, boolean build, boolean buildHistory) {
        this.id = id;
        this.role = role;
        this.projectCreate = projectCreate;
        this.projectRemove = projectRemove;
        this.projectExecute = projectExecute;
        this.build = build;
        this.buildHistory = buildHistory;
    }

    public LocalRoles(String role, Boolean projectCreate, Boolean projectRemove, Boolean projectExecute, Boolean build, Boolean buildHistory) {
        this.role = role;
        this.projectCreate = projectCreate;
        this.projectRemove = projectRemove;
        this.projectExecute = projectExecute;
        this.build = build;
        this.buildHistory = buildHistory;
    }

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

    public boolean getBuild() {
        return build;
    }

    public void setBuild(boolean build) {
        this.build = build;
    }

    public boolean getBuildHistory() {
        return buildHistory;
    }

    public void setBuildHistory(boolean buildHistory) {
        this.buildHistory = buildHistory;
    }

    public ProjectMaster getProjectId() {
        return projectId;
    }

    public void setProjectId(ProjectMaster projectId) {
        this.projectId = projectId;
    }

    @XmlTransient
    public Collection<LocalRolesUser> getLocalRolesUserCollection() {
        return localRolesUserCollection;
    }

    public void setLocalRolesUserCollection(Collection<LocalRolesUser> localRolesUserCollection) {
        this.localRolesUserCollection = localRolesUserCollection;
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
        if (!(object instanceof LocalRoles)) {
            return false;
        }
        LocalRoles other = (LocalRoles) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "entity.LocalRoles[ id=" + id + " ]";
    }
    
}
