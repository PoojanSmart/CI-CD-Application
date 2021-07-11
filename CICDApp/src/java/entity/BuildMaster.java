/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entity;

import java.io.Serializable;
import java.util.Date;
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
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;
import org.ocpsoft.prettytime.PrettyTime;

/**
 *
 * @author smart
 */
@Entity
@Table(name = "Build_Master")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "BuildMaster.findAll", query = "SELECT b FROM BuildMaster b"),
    @NamedQuery(name = "BuildMaster.findByBulidId", query = "SELECT b FROM BuildMaster b WHERE b.bulidId = :buildId"),
    @NamedQuery(name = "BuildMaster.findByProjectId", query = "SELECT b FROM BuildMaster b WHERE b.projectId.projectId = :ProjectId"),
    @NamedQuery(name = "BuildMaster.findByBuildTime", query = "SELECT b FROM BuildMaster b WHERE b.buildTime = :buildTime"),
    @NamedQuery(name = "BuildMaster.findByStage", query = "SELECT b FROM BuildMaster b WHERE b.stage = :stage"),
    @NamedQuery(name = "BuildMaster.findByPass", query = "SELECT b FROM BuildMaster b WHERE b.pass = :pass")})
public class BuildMaster implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "BulidId")
    private Integer bulidId;
    @Basic(optional = false)
    @NotNull
    @Column(name = "BuildTime")
    @Temporal(TemporalType.TIMESTAMP)
    private Date buildTime;
    @Basic(optional = false)
    @NotNull
    @Lob
    @Size(min = 1, max = 65535)
    @Column(name = "Build")
    private String build;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 20)
    @Column(name = "Stage")
    private String stage;
    @Basic(optional = false)
    @NotNull
    @Column(name = "Pass")
    private boolean pass;
    @JoinColumn(name = "ProjectId", referencedColumnName = "ProjectId")
    @ManyToOne(optional = false)
    private ProjectMaster projectId;

    public BuildMaster() {
    }

    public BuildMaster(Integer bulidId) {
        this.bulidId = bulidId;
    }

    public BuildMaster(Integer bulidId, Date buildTime, String build, String stage, boolean pass) {
        this.bulidId = bulidId;
        this.buildTime = buildTime;
        this.build = build;
        this.stage = stage;
        this.pass = pass;
    }
public BuildMaster(String build, String stage, boolean pass) {
        this.build = build;
        this.stage = stage;
        this.pass = pass;
        this.buildTime = new Date();
    }
    public Integer getBulidId() {
        return bulidId;
    }

    public void setBulidId(Integer bulidId) {
        this.bulidId = bulidId;
    }

    public Date getBuildTime() {
        return buildTime;
    }

    public void setBuildTime(Date buildTime) {
        this.buildTime = buildTime;
    }

    public String getBuild() {
        return build;
    }

    public void setBuild(String build) {
        this.build = build;
    }

    public String getStage() {
        return stage;
    }

    public void setStage(String stage) {
        this.stage = stage;
    }

    public boolean getPass() {
        return pass;
    }

    public void setPass(boolean pass) {
        this.pass = pass;
    }

    public ProjectMaster getProjectId() {
        return projectId;
    }

    public void setProjectId(ProjectMaster projectId) {
        this.projectId = projectId;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (bulidId != null ? bulidId.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof BuildMaster)) {
            return false;
        }
        BuildMaster other = (BuildMaster) object;
        if ((this.bulidId == null && other.bulidId != null) || (this.bulidId != null && !this.bulidId.equals(other.bulidId))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "entity.BuildMaster[ bulidId=" + bulidId + " ]";
    }
    public String getPrettyBuildTime()
    {
        PrettyTime p = new PrettyTime();
        return p.format(this.getBuildTime());
    }
    
    
}
