package com.mygdx.game;

//SINGLETON GLOBALS CLASS
public class Globals {
    //GLOBAL FIELDS
    private Topping holding;
    private Boolean showText;
    private Boolean orderScreen;
    private Pizza currentPizza;
    private Customer currentCustomer;
    private float money;
    
    //Private static instance of the class
    private static Globals inst = null;
    
    //Constructor
    private Globals(){
        this.holding = new Topping("empty", 0, 0);
        this.showText = false;
        this.orderScreen = false;
        this.currentPizza = null;
        this.currentCustomer = null;
        this.money = 0;
    }
    
    //Get instance method
    public static Globals getInstance(){
        if(inst == null)
            inst = new Globals();
        return inst;
    }
    
    
    //Get and Set methods
    public Topping getHolding(){
        return holding;
    }
    public void setHolding(Topping holding){
        this.holding = holding;
    }
    
    public Boolean getShowText(){
        return showText;
    }
    public void setShowText(Boolean bool){
        this.showText = bool;
        currentCustomer.setTalking(bool);
    }
    
    public Boolean getOrderScreen(){
        return orderScreen;
    }
    public void setOrderScreen(Boolean bool){
        this.orderScreen = bool;
    }
    
    public Pizza getPizza(){
        return currentPizza;
    }
    public void setPizza(Pizza p){
        this.currentPizza = p;
    }
    
    public Customer getCustomer(){
        return currentCustomer;
    }
    public void setCustomer(Customer c){
        this.currentCustomer = c;
    }
    
    public float getMoney(){
        return this.money;
    }
    public void setMoney(double m){
        this.money += m;
    }
}
