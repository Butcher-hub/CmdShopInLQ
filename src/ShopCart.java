/**
 * @Author: butcher
 * @Date: 2020/12/27/18:05
 */
public class ShopCart {
    private int count;
    private int amount;
    private Product [] blank;

    public Product[] getBlank() {
        return blank;
    }

    public ShopCart(int num) {
        this.blank = new Product[num];
    }

    /**
     * 购物车添加商品的方法
     * @param product 商品
     * @param count 数量
     */
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

    /**
     * 显示购物车中商品与数量的方法
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

    /**
     * 清空购物车的方法
     */
    public void freeIt(){

        for (int i = 0; i <blank.length ; i++) {
            if (blank[i]!=null){
                blank[i]=null;
            }
        }
        this.amount=0;
        this.count=0;
        System.out.println("购物车已经清空了");
        //不使用增强for循环的原因是：增强for循环底层使用迭代器（iterator）实现，只能对数组遍历，不能赋值
        //优点是遍历前不需要声明数组长度，使用方便
    }

    public int getCount() {
        return count;
    }

    public int getAmount() {
        return amount;
    }

}
