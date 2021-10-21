//package Structural;
//
//import java.util.Hashtable;
//// This code also uses other classes not implemented in the example. Anyway, the ideia is here.
//// A proxy acts as a lightweight version of the real thing. It can do some of the tasks but ends up delegating
//// Other more sensitive operations to the real class. Here it acts as a gateway to keep warehouses from receiving
//// orders that they cannot fulfill due to not having enough stock.
//public class ExampleProxy {
//}
//
//// Subject Interface
//interface IOrder {
//    public void fulfillOrder(Order order);
//}
//
//// Real subject class
//class Warehouse implements IOrder {
//    private Hashtable<String, Integer> stock;
//    private String address;
//
//    public Warehouse(String address){
//        this.address = address;
//    }
//
//    @Override
//    // Doesn't check if it has enough stock to fulfill order, since an order should be sent to a warehouse if it can be fulfilled!
//    public void fulfillOrder(Order order) {
//        for (Item item: order.itemList)
//            this.stock.replace(item.sku, stock.get(item)-1);
//    }
//
//    public int currentInventory(Item item){
//        if (stock.containsKey(item.sku))
//            return stock.get(item.sku).intValue();
//        return 0;
//    }
//}
//
////Proxy class - Wraps Warehouses.
//class OrderFulfillment implements IOrder {
//    private List<Warehouse> warehouses;
//
//    @Override
//    public void fulfillOrder(Order order) {
//        /*For each item in a order, check each warehouse to see if it is in stock
//        * If it is, then create a new Order for that warehouse, else check next one
//        * Send all the orders to the warehouse(s)
//        * */
//
//        for (Item item: order.itemList){
//            for (Warehouse warehouse: warehouses){
//                ...
//            }
//        }
//    }
//}