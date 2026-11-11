package pd12.store;

import lombok.AllArgsConstructor;

@AllArgsConstructor
public class Store {
    private int availableQuantity;

    public synchronized boolean purchase(int quantity) {
        if (quantity < 1) {
            throw new IllegalArgumentException("The value of items ordered can't be less than 1!");
        }

        if (quantity <= getAvailableQuantity()) {

            this.availableQuantity = this.availableQuantity - quantity;
            return true;
        }

        return false;
    }

    public synchronized int getAvailableQuantity() {
        return this.availableQuantity;
    }
}
