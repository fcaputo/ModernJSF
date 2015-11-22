package de.frankcaputo;

import javax.enterprise.context.SessionScoped;
import javax.faces.context.FacesContext;
import javax.faces.event.PhaseEvent;
import javax.faces.event.PhaseId;
import javax.inject.Named;
import java.io.Serializable;

@Named
@SessionScoped
public class ShoppingCart implements Serializable {

    private boolean sold;
    private int count = 0;

    void add(int count) {
        this.count += count;
    }

    public int getCount() {
        return count;
    }

    public void buy() {
        count = 0;
        sold = true;

        // render all
        FacesContext context = FacesContext.getCurrentInstance();
        context.getPartialViewContext().setRenderAll(true);
    }

    public boolean isSold() {
        return sold;
    }

    public void resetSoldFlag(PhaseEvent phaseEvent) {
        if(PhaseId.RENDER_RESPONSE.equals(phaseEvent.getPhaseId())) {
            sold = false;
        }
    }
}
