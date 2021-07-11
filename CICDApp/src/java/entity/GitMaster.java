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
@Table(name = "Git_Master")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "GitMaster.findAll", query = "SELECT g FROM GitMaster g"),
    @NamedQuery(name = "GitMaster.findByGitId", query = "SELECT g FROM GitMaster g WHERE g.gitId = :gitId"),
    @NamedQuery(name = "GitMaster.findByRepoUrl", query = "SELECT g FROM GitMaster g WHERE g.repoUrl = :repoUrl")})
public class GitMaster implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "GitId")
    private Integer gitId;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 1000)
    @Column(name = "RepoUrl")
    private String repoUrl;
    @OneToMany(mappedBy = "gitId")
    private Collection<RepoMaster> repoMasterCollection;
    @JoinColumn(name = "CredentialId", referencedColumnName = "CredentialId")
    @ManyToOne
    private RepoCredential credentialId;

    public GitMaster() {
    }

    public GitMaster(Integer gitId) {
        this.gitId = gitId;
    }

    public GitMaster(Integer gitId, String repoUrl) {
        this.gitId = gitId;
        this.repoUrl = repoUrl;
    }


    public GitMaster(String repoUrl) {
        this.gitId = gitId;
        this.repoUrl = repoUrl;
    }
    
    public GitMaster(String repoUrl,RepoCredential credentialId) {
        this.repoUrl = repoUrl;
        this.credentialId = credentialId;
                
    }
    
    public Integer getGitId() {
        return gitId;
    }

    public void setGitId(Integer gitId) {
        this.gitId = gitId;
    }

    public String getRepoUrl() {
        return repoUrl;
    }

    public void setRepoUrl(String repoUrl) {
        this.repoUrl = repoUrl;
    }

    @XmlTransient
    public Collection<RepoMaster> getRepoMasterCollection() {
        return repoMasterCollection;
    }

    public void setRepoMasterCollection(Collection<RepoMaster> repoMasterCollection) {
        this.repoMasterCollection = repoMasterCollection;
    }

    public RepoCredential getCredentialId() {
        return credentialId;
    }

    public void setCredentialId(RepoCredential credentialId) {
        this.credentialId = credentialId;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (gitId != null ? gitId.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof GitMaster)) {
            return false;
        }
        GitMaster other = (GitMaster) object;
        if ((this.gitId == null && other.gitId != null) || (this.gitId != null && !this.gitId.equals(other.gitId))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "entity.GitMaster[ gitId=" + gitId + " ]";
    }
    
}
