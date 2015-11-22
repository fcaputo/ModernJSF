package de.frankcaputo;

import javax.enterprise.context.RequestScoped;
import javax.faces.context.FacesContext;
import javax.inject.Named;

@RequestScoped
@Named
public class Navigation {

    public String getCurrentUrl() {
        FacesContext context = FacesContext.getCurrentInstance();

        String viewId = context.getViewRoot().getViewId();
        String url = context.getApplication().getViewHandler().getActionURL(context, viewId);

        return context.getExternalContext().encodeActionURL(url);
    }

}
