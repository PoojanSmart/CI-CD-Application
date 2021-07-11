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
@Table(name = "Project_Master")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "ProjectMaster.findAll", query = "SELECT p FROM ProjectMaster p"),
    @NamedQuery(name = "ProjectMaster.findScheduled", query = "SELECT p FROM ProjectMaster p where p.timeInterval is not null"),
    @NamedQuery(name = "ProjectMaster.findByProjectId", query = "SELECT p FROM ProjectMaster p WHERE p.projectId = :projectId"),
    @NamedQuery(name = "ProjectMaster.findByProjectName", query = "SELECT p FROM ProjectMaster p WHERE p.projectName = :projectName"),
    @NamedQuery(name = "ProjectMaster.findByTimeInterval", query = "SELECT p FROM ProjectMaster p WHERE p.timeInterval = :timeInterval")})
public class ProjectMaster implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "ProjectId")
    private Integer projectId;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 30)
    @Column(name = "ProjectName")
    private String projectName;
    @Basic(optional = false)
    @NotNull
    @Lob
    @Size(min = 1, max = 65535)
    @Column(name = "ShellCommand")
    private String shellCommand;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 100)
    @Column(name = "TimeInterval")
    private String timeInterval;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "projectId")
    private Collection<LocalRoles> localRolesCollection;
    @JoinColumn(name = "RepoId", referencedColumnName = "RepoId")
    @ManyToOne
    private RepoMaster repoId;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "projectId")
    private Collection<BuildMaster> buildMasterCollection;

    public ProjectMaster() {
    }

    public ProjectMaster(Integer projectId) {
        this.projectId = projectId;
    }

    public ProjectMaster(Integer projectId, String projectName, String shellCommand, String timeInterval) {
        this.projectId = projectId;
        this.projectName = projectName;
        this.shellCommand = shellCommand;
        this.timeInterval = timeInterval;
    }

    public ProjectMaster(String projectName, String shellCommand, String timeInterval) {
        this.projectName = projectName;
        this.shellCommand = shellCommand;
        this.timeInterval = timeInterval;
        }
    public ProjectMaster(String projectName, String shellCommand, String timeInterval,RepoMaster repoId) {
        this.projectName = projectName;
        this.shellCommand = shellCommand;
        this.timeInterval = timeInterval;
        this.repoId = repoId;
        }

    public Integer getProjectId() {
        return projectId;
    }

    public void setProjectId(Integer projectId) {
        this.projectId = projectId;
    }

    public String getProjectName() {
        return projectName;
    }

    public void setProjectName(String projectName) {
        this.projectName = projectName;
    }

    public String getShellCommand() {
        return shellCommand;
    }

    public void setShellCommand(String shellCommand) {
        this.shellCommand = shellCommand;
    }

    public String getTimeInterval() {
        return timeInterval;
    }

    public void setTimeInterval(String timeInterval) {
        this.timeInterval = timeInterval;
    }

    @XmlTransient
    public Collection<LocalRoles> getLocalRolesCollection() {
        return localRolesCollection;
    }

    public void setLocalRolesCollection(Collection<LocalRoles> localRolesCollection) {
        this.localRolesCollection = localRolesCollection;
    }

    public RepoMaster getRepoId() {
        return repoId;
    }

    public void setRepoId(RepoMaster repoId) {
        this.repoId = repoId;
    }

    @XmlTransient
    public Collection<BuildMaster> getBuildMasterCollection() {
        return buildMasterCollection;
    }

    public void setBuildMasterCollection(Collection<BuildMaster> buildMasterCollection) {
        this.buildMasterCollection = buildMasterCollection;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (projectId != null ? projectId.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof ProjectMaster)) {
            return false;
        }
        ProjectMaster other = (ProjectMaster) object;
        if ((this.projectId == null && other.projectId != null) || (this.projectId != null && !this.projectId.equals(other.projectId))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "entity.ProjectMaster[ projectId=" + projectId + " ]";
    }
    
}
