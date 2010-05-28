/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.genrep.phase.listener;


import com.genrep.system.service.AppSystem;
import javax.faces.event.PhaseEvent;
import javax.faces.event.PhaseId;
import javax.faces.event.PhaseListener;

/**
 *
 * @author user1
 */
public class PhaseListenerClass implements  PhaseListener{
    private String cphase;

    public void afterPhase(PhaseEvent arg0) {
        AppSystem.getLogger().info("AFTER" +arg0.getPhaseId());
    }

    public void beforePhase(PhaseEvent arg0) {
        AppSystem.getLogger().info("BEFORE" +arg0.getPhaseId());
    }

    public PhaseId getPhaseId() {
        return PhaseId.ANY_PHASE;
    }

}
