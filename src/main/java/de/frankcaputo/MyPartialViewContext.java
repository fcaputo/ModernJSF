package de.frankcaputo;

import javax.faces.context.FacesContext;
import javax.faces.context.PartialViewContext;
import javax.faces.context.PartialViewContextFactory;
import javax.faces.context.PartialViewContextWrapper;

public class MyPartialViewContext extends PartialViewContextWrapper {
    private PartialViewContext wrapped;

    public MyPartialViewContext(PartialViewContext wrapped) {
        this.wrapped = wrapped;
    }

    @Override
    public PartialViewContext getWrapped() {
        return wrapped;
    }

    @Override
    public void setRenderAll(boolean renderAll) {
        if(renderAll) {
            getRenderIds().clear();
            getRenderIds().add("main");
        }
    }

    public static class Factory extends PartialViewContextFactory {

        private PartialViewContextFactory wrapped;

        public Factory(PartialViewContextFactory wrapped) {
            this.wrapped = wrapped;
        }

        @Override
        public PartialViewContext getPartialViewContext(FacesContext context) {
            return new MyPartialViewContext(wrapped.getPartialViewContext(context));
        }
    }
}
