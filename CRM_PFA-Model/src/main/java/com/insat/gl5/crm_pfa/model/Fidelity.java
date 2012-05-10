/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.insat.gl5.crm_pfa.model;

import javax.persistence.Entity;
import javax.persistence.OneToOne;

/**
 *
 * @author Mu7ammed 3li -- mohamed.ali.affes@gmail.com --
 */
@Entity
public class Fidelity extends BaseEntity{
    private int score;
    
    private Account account;


    /**
     * @return the score
     */
    public int getScore() {
        return score;
    }

    /**
     * @param score the score to set
     */
    public void setScore(int score) {
        this.score = score;
    }

    @OneToOne
    public Account getAccount() {
        return account;
    }

    public void setAccount(Account account) {
        this.account = account;
    }
    
}
