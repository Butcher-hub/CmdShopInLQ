import java.text.SimpleDateFormat;
import java.util.Date;

public class User {
    private String username;
    private String password;
    private String address;
    private String phone;
    private double money;
    private String id;

    public void setId(String id) {
        this.id = id;
    }

    ShopCart shopCart = new ShopCart(10);

    /**
     * 用户购买行为
     *
     * @param product 商品
     * @param count   数量
     */
    public void buy(Product product, int count) {
        if (money < product.getPprice()) {
            System.out.println("你钱不够，无法购买" + product.getPname());
            return;
        }
        shopCart.add(product, count);
    }

    /**
     * 用户查看购物车
     */
    public void checkSC() {
        if (shopCart.getBlank()[0] == null) {
            System.out.println("您的购物车空空如也！");
            return;
        }
        shopCart.showList();
    }

    /**
     * 用户支付方法
     */
    public void pay(Order order) {
        if (shopCart.getAmount() == 0) {
            System.out.println("你购物车没有东西哦，先去买点吧！");
            return;
        }
        order.setPayTime(new SimpleDateFormat("yyyyMMddhhmmss").format(new Date()));
    }

    public String getId() {
        return id;
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

    public void setAddress(String address) {
        this.address = address;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }
}
