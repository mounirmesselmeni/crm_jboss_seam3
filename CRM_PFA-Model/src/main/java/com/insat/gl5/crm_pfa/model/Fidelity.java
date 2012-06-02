/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.insat.gl5.crm_pfa.model;

import javax.persistence.Entity;

/**
 *
 * @author Mu7ammed 3li -- mohamed.ali.affes@gmail.com --
 */
@Entity
public class Fidelity extends BaseEntity{
    private int score;

    public Fidelity() {
    }

    
    public Fidelity(int score) {
        this.score = score;
    }

    public void incrementScore(int value){
        score+=value;
    }
    
    public void decrementScore(int value){
        score-=value;
    }
    
    /**
     * @return the score
     */
    public int getScore() {
        return score;
    }
}
