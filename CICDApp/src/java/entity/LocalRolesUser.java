/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entity;

import java.io.Serializable;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author smart
 */
@Entity
@Table(name = "Local_Roles_User")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "LocalRolesUser.findAll", query = "SELECT l FROM LocalRolesUser l"),
    @NamedQuery(name = "LocalRolesUser.findById", query = "SELECT l FROM LocalRolesUser l WHERE l.localRolesUserPK.id = :id"),
    @NamedQuery(name = "LocalRolesUser.findByLocalRoleId", query = "SELECT l FROM LocalRolesUser l WHERE l.localRolesUserPK.localRoleId = :localRoleId"),
    @NamedQuery(name = "LocalRolesUser.findByUserId", query = "SELECT l FROM LocalRolesUser l WHERE l.localRolesUserPK.userId = :userId")

})
public class LocalRolesUser implements Serializable {

    private static final long serialVersionUID = 1L;
    @EmbeddedId
    protected LocalRolesUserPK localRolesUserPK;
    @JoinColumn(name = "LocalRoleId", referencedColumnName = "Id", insertable = false, updatable = false)
    @ManyToOne(optional = false)
    private LocalRoles localRoles;
    @JoinColumn(name = "UserId", referencedColumnName = "UserId", insertable = false, updatable = false)
    @ManyToOne(optional = false)
    private UserMaster userMaster;

    public LocalRolesUser() {
    }

    public LocalRolesUser(LocalRolesUserPK localRolesUserPK) {
        this.localRolesUserPK = localRolesUserPK;
    }

    public LocalRolesUser(int localRoleId, int userId) {
        this.localRolesUserPK = new LocalRolesUserPK(localRoleId, userId);
    }

    public LocalRolesUserPK getLocalRolesUserPK() {
        return localRolesUserPK;
    }

    public void setLocalRolesUserPK(LocalRolesUserPK localRolesUserPK) {
        this.localRolesUserPK = localRolesUserPK;
    }

    public LocalRoles getLocalRoles() {
        return localRoles;
    }

    public void setLocalRoles(LocalRoles localRoles) {
        this.localRoles = localRoles;
    }

    public UserMaster getUserMaster() {
        return userMaster;
    }

    public void setUserMaster(UserMaster userMaster) {
        this.userMaster = userMaster;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (localRolesUserPK != null ? localRolesUserPK.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof LocalRolesUser)) {
            return false;
        }
        LocalRolesUser other = (LocalRolesUser) object;
        if ((this.localRolesUserPK == null && other.localRolesUserPK != null) || (this.localRolesUserPK != null && !this.localRolesUserPK.equals(other.localRolesUserPK))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "entity.LocalRolesUser[ localRolesUserPK=" + localRolesUserPK + " ]";
    }
    
}
