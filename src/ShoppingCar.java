import java.security.PrivateKey;

/**
 * @Author: butcher
 * @Date: 2020/12/27/18:05
 */
public class ShoppingCar {
    private int count;
    private int amount;

    public Product[] getBlank() {
        return blank;
    }

    private Product [] blank;

    public ShoppingCar( int num) {
        this.blank = new Product[num];
    }

    public void add(Product product,int count){
        for (Product p:blank) {
            if (p==null){
                p=product;
                p.setUcount(count);
            }
        }
    }

    /**
     * 显示购物车中的商品
     */
    public void showList(){
        for (Product p:blank) {
            System.out.printf("|%-6s|%-6s|%-6s|%-6s|%-6s|\n",p.getPid(),p.getPname(),p.getPprice(),p.getPcount(),p.getUcount());
            count+=p.getPcount();
            amount+=p.getPprice();
        }
        System.out.println("------------------------------");
        System.out.println("数量总计："+count+"       "+"金额总计："+amount);
    }

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }

    public int getAmount() {
        return amount;
    }

    public void setAmount(int amount) {
        this.amount = amount;
    }
}
