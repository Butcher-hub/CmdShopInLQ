import java.text.SimpleDateFormat;
import java.util.Date;

public class User {
    private String username;
    private String password;
    private String address;
    private String phone;
    private double money;
    private String  id;

    public String  getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    ShopCart shopCart = new ShopCart(10);
    /**
     * 用户购买行为
     * @param product
     */
    public void buy(Product product,int count){
        if(money< product.getPprice()){
            System.out.println("你钱不够，无法购买"+product.getPname());
            return;
        }
        shopCart.add(product,count);
    }

    /**
     * 用户查看购物车
     */
    public void checkSC(){
        if (shopCart.getBlank()[0]==null){
            System.out.println("您的购物车空空如也！");
            return;
        }
        shopCart.showList();
    }

    public void pay(){
        if (shopCart.getAmount()==0){
            System.out.println("你购物车没有东西哦，先去买点吧！");
            return;
        }
        ControlExcel controlExcel = new ControlExcel();
        controlExcel.changeMoney(username, shopCart.getAmount());
        System.out.println("付款成功");

        for (Product p: shopCart.getBlank()) {
            if(p==null){
                Date date = new Date();
                SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyyMMddhhmmss");
                controlExcel.addOrder(simpleDateFormat.format(date),shopCart.getBlank(), shopCart.getCount(), shopCart.getAmount());
                controlExcel.addOrderWithUser(simpleDateFormat.format(date),id);
                System.out.println("商品信息已更新！订单生成完毕！");
                //清空购物车
                shopCart.freeIt();
                break;
            }
            controlExcel.changeProduct(p.getPid(),p.getUcount());
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
