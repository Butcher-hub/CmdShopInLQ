public class User {
    private String username;
    private String password;
    private String address;
    private String phone;
    private double money;
    ShoppingCar shoppingCar = new ShoppingCar(10);
    /**
     * 用户购买行为
     * @param product
     */
    public void buy(Product product,int count){
        if(money>= product.getPprice()){
            System.out.println("你钱不够，无法购买"+product.getPname());
            return;
        }
        shoppingCar.add(product,count);
    }

    /**
     * 用户查看购物车
     */
    public void checkSC(){
        if (shoppingCar.getBlank()==null){
            System.out.println("您的购物车空空如也！");
            return;
        }
        shoppingCar.showList();
    }

    public void pay(){
        ControlExcel controlExcel = new ControlExcel();
        controlExcel.changeMoney(username,shoppingCar.getAmount());
        System.out.println("付款成功");
        for (Product p:shoppingCar.getBlank()) {
            controlExcel.changeProduct(p.getPname(),p.getPcount());
        }
    }



    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public double getMoney() {
        return money;
    }

    public void setMoney(double money) {
        this.money = money;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }
}
