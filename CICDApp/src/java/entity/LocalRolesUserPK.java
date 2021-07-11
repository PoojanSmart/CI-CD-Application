/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entity;

import java.io.Serializable;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.validation.constraints.NotNull;

/**
 *
 * @author smart
 */
@Embeddable
public class LocalRolesUserPK implements Serializable {

    @Basic(optional = false)
    @Column(name = "Id")
    private int id;
    @Basic(optional = false)
    @NotNull
    @Column(name = "LocalRoleId")
    private int localRoleId;
    @Basic(optional = false)
    @NotNull
    @Column(name = "UserId")
    private int userId;

    public LocalRolesUserPK() {
    }

    public LocalRolesUserPK( int localRoleId, int userId) {
        this.localRoleId = localRoleId;
        this.userId = userId;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getLocalRoleId() {
        return localRoleId;
    }

    public void setLocalRoleId(int localRoleId) {
        this.localRoleId = localRoleId;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (int) id;
        hash += (int) localRoleId;
        hash += (int) userId;
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof LocalRolesUserPK)) {
            return false;
        }
        LocalRolesUserPK other = (LocalRolesUserPK) object;
        if (this.id != other.id) {
            return false;
        }
        if (this.localRoleId != other.localRoleId) {
            return false;
        }
        if (this.userId != other.userId) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "entity.LocalRolesUserPK[ id=" + id + ", localRoleId=" + localRoleId + ", userId=" + userId + " ]";
    }
    
}
