package MVC;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.lang.reflect.Array;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.ArrayList;
import java.util.ListIterator;
import java.util.Observable;
import java.util.Observer;

public class ExampleMVC {
    // Should implement relationship between classes

    // The view should subscribe to the model
    // The controller has an instance of the model an one of the view so that it can serve as proxy for commands
    // comming from the view to the model, such as CRUD operations.

    // The updated model will then notify its observers (view(s))
    // The view will update itself after the notification
}


// CONTROLLER
class OrderController {
    private StoreOrder storeOrder;
    private OrderView orderView;

    public OrderController (StoreOrder storeOrder, OrderView orderView) {
        this.storeOrder = storeOrder;
        this.orderView = orderView;
    }

    public void deleteItem(int itemNum) {
        storeOrder.deleteItem(itemNum);
    }

    public void changePrice(int itemNum, BigDecimal newPrice) {
        storeOrder.changePrice(itemNum, newPrice);
    }
}

// VIEW
class OrderView extends JPanel implements Observer, ActionListener {

    // Controller
    private OrderController controller;

    //User-interface elements ??
    private JFrame frame;
    private JButton changePriceButton;
    private JButton deleteItemButton;
    private JTextField newPriceField;
    private JLabel totalLabel;
    private JTable groceryList;

    private void createUI() {
        // Init UI elements
        deleteItemButton = new JButton("Delete item");
        add(deleteItemButton);
        // ...

        // Add listeners
        deleteItemButton.addActionListener(this);
        // ...
    }

    @Override
    public void update(Observable s, Object arg) {
        display(((StoreOrder) s).getItemList(), ((StoreOrder) s).getPriceList());
    }

    public OrderView(OrderController controller) {
        this.controller = controller;
        createUI();
    }

    public void display(ListIterator itemList, ListIterator priceList) {
        // code to display order?
    }

    @Override
    public void actionPerformed(ActionEvent actionEvent) {
        if (actionEvent.getSource() == deleteItemButton) {
            controller.deleteItem(groceryList.getSelectedRow());
        }
        else if (actionEvent.getSource() == changePriceButton) {
            BigDecimal newPrice = new BigDecimal(newPriceField.getText());
            controller.changePrice(groceryList.getSelectedRow(), newPrice);
        }
    }
}

// MODEL
class StoreOrder extends Observable {
    private ArrayList<String> itemList;
    private ArrayList<BigDecimal> priceList;

    public StoreOrder() {
        itemList = new ArrayList<String>();
        priceList = new ArrayList<BigDecimal>();
    }

    public String getItem(int itemNum) {
        return itemList.get(itemNum);
    }

    public BigDecimal getPrice(int itemNum) {
        return priceList.get(itemNum);
    }

    public ListIterator<String> getItemList() {
        return itemList.listIterator();
    }

    public ListIterator<BigDecimal> getPriceList() {
        return priceList.listIterator();
    }

    public void deleteItem(int itemNum) {
        itemList.remove(itemNum);
        priceList.remove(itemNum);
        setChanged();
        notifyObservers();
    }

    public void addItem(int barcode) {
        // code to add item
        // prices looked up from database
        setChanged();
        notifyObservers();
    }

    public void changePrice(int itemNum, BigDecimal newPrice) {
        priceList.set(itemNum, newPrice);
        setChanged();
        notifyObservers();
    }
}