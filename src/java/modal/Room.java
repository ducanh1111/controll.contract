/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package modal;

import java.io.Serializable;

/**
 *
 * @author ADMIN
 */
public class Room implements Serializable {

    private int rid;
    private String rname;
    private Boolean condition;
    private TypeOfRoom typeofroom;

    public int getRid() {
        return rid;
    }

    public void setRid(int rid) {
        this.rid = rid;
    }

    public String getRname() {
        return rname;
    }

    public void setRname(String rname) {
        this.rname = rname;
    }

    public Boolean getCondition() {
        return condition;
    }

    public void setCondition(Boolean condition) {
        this.condition = condition;
    }

    public TypeOfRoom getTypeofroom() {
        return typeofroom;
    }

    public void setTypeofroom(TypeOfRoom typeofroom) {
        this.typeofroom = typeofroom;
    }

}
