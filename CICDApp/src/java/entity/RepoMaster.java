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
@Table(name = "Repo_Master")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "RepoMaster.findAll", query = "SELECT r FROM RepoMaster r"),
    @NamedQuery(name = "RepoMaster.findByRepoId", query = "SELECT r FROM RepoMaster r WHERE r.repoId = :repoId")})
public class RepoMaster implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "RepoId")
    private Integer repoId;
    @Basic(optional = false)
    @NotNull
    @Lob
    @Size(min = 1, max = 65535)
    @Column(name = "PysicalPath")
    private String pysicalPath;
    @JoinColumn(name = "GitId", referencedColumnName = "GitId")
    @ManyToOne
    private GitMaster gitId;
    @OneToMany(mappedBy = "repoId")
    private Collection<ProjectMaster> projectMasterCollection;

    public RepoMaster() {
    }

    public RepoMaster(Integer repoId) {
        this.repoId = repoId;
    }

    public RepoMaster(Integer repoId, String pysicalPath) {
        this.repoId = repoId;
        this.pysicalPath = pysicalPath;
    }

    public RepoMaster(String pysicalPath,GitMaster gitId) {
        this.pysicalPath = pysicalPath;
        this.gitId = gitId;
    }

    public RepoMaster(String physicalPath) {
        this.pysicalPath = pysicalPath;
    }

    public Integer getRepoId() {
        return repoId;
    }

    public void setRepoId(Integer repoId) {
        this.repoId = repoId;
    }

    public String getPysicalPath() {
        return pysicalPath;
    }

    public void setPysicalPath(String pysicalPath) {
        this.pysicalPath = pysicalPath;
    }

    public GitMaster getGitId() {
        return gitId;
    }

    public void setGitId(GitMaster gitId) {
        this.gitId = gitId;
    }

    @XmlTransient
    public Collection<ProjectMaster> getProjectMasterCollection() {
        return projectMasterCollection;
    }

    public void setProjectMasterCollection(Collection<ProjectMaster> projectMasterCollection) {
        this.projectMasterCollection = projectMasterCollection;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (repoId != null ? repoId.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof RepoMaster)) {
            return false;
        }
        RepoMaster other = (RepoMaster) object;
        if ((this.repoId == null && other.repoId != null) || (this.repoId != null && !this.repoId.equals(other.repoId))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "entity.RepoMaster[ repoId=" + repoId + " ]";
    }
    
}
