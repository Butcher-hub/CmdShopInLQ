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
        for (int i = 0; i<this.blank.length;i++) {
            if (blank[i]==null){
                blank[i]=product;
                blank[i].setUcount(count);
                amount += blank[i].getPprice()*count;
                this.count += count;
                return;
            }else if(product.getPname().equals(blank[i].getPname())){
                blank[i].setUcount(blank[i].getUcount()+count);
                amount += blank[i].getPprice()*count;
                this.count += count;
                return;
            }
        }
    }

    /*
     显示购物车中的商品
     */
    public void showList(){
        System.out.println("--------------------------------------");
        for (Product p:blank) {
            if(p==null){
                break;
            }
            System.out.printf("|%-6s|%-6s|%-6s|%-6s|%-6s|\n",p.getPid(),p.getPname(),p.getPprice(),p.getPcount(),p.getUcount());
        }
        System.out.println("--------------------------------------");
        System.out.println("数量总计："+count+"       "+"金额总计："+amount);
    }

    public void freeIt(){
       /* for (Product p : blank) {
            if (p!=null){
                p=null;
            }
        }*/
        for (int i = 0; i <blank.length ; i++) {
            if (blank[i]!=null){
                blank[i]=null;
            }
        }
        this.amount=0;
        this.count=0;
        System.out.println("购物车已经清空了");
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
