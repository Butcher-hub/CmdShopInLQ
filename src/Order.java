import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * @Author: butcher
 * @Date: 2021/01/05/23:25
 */
public class Order {
    private String id;
    private User user;
    private String createTime;
    private String payTime;
    private int count;
    private int amount;

    public Order(User user) {
        this.user = user;
        this.id = new SimpleDateFormat("yyyyMMddhhmmss").format(new Date());
        this.createTime = new SimpleDateFormat("yyyy年MM月dd日hh时mm分ss秒").format(new Date());
    }

    public void setPayTime(String payTime) {
        this.payTime = payTime;
    }

    public void change(Order order) {
        ControlExcel controlExcel = new ControlExcel();
        controlExcel.addOrder(order);//写入order表
        controlExcel.changeMoney(user.getUsername(), user.shopCart.getAmount());//修改客户金额
        for (Product p : user.shopCart.getBlank()) {
            if (p == null) {
                System.out.println("商品信息已更新！订单生成完毕！");
                //清空购物车
                user.shopCart.freeIt();
                break;
            }
            controlExcel.addOrderWithProduct(id, p.getPid());
            controlExcel.changeProduct(p.getPid(), p.getUcount());
        }
    }

    public String getCreateTime() {
        return createTime;
    }

    public String getId() {
        return id;
    }

    public User getUser() {
        return user;
    }

}
