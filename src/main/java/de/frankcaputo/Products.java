package de.frankcaputo;

import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.inject.Named;
import javax.validation.constraints.Min;

@Named
@RequestScoped
public class Products {

    @Inject
    private ShoppingCart shoppingCart;

    @Min(value = 1, message = "please add at least one item to the shopping cart")
    private int count = 1;

    public void setCount(int count) {
        this.count = count;
    }

    public int getCount() {
        return count;
    }

    public String buy() {
        shoppingCart.add(count);
        return "/pages/shoppingCart.xhtml";
    }
}
