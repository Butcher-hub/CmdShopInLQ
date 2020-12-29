import java.util.Scanner;

/**
 * @Author: butcher
 * @Date: 2020/12/27/16:00
 */
public class Shop {
    Product [] products;


    public static void main(String[] args) {
        Shop shop = new Shop();
        Login login = new Login();
        login.login();//用户已产生
        ControlExcel controlExcel  =new ControlExcel();
        shop.products  = controlExcel.readProductExcel("product.xlsx");

        Scanner sc = new Scanner(System.in);
        showProduct(shop.products);
        while(true) {
            System.out.print("请输入1购买商品,输入2查看购物车,输入3结算,输入0退出购买:");
            int control = sc.nextInt();
            if (control==0){
                System.out.println("期待您的下次光临！");
                break;
            }else if (control==1){
                showProduct(shop.products);
                System.out.print("请输入您要购买的商品id:");
                int id = sc.nextInt();
                boolean flag = false;
                for (Product p:shop.products) {
                    if(p==null){
                        continue;
                    }
                    if (p.getPid().equals(new Integer(id).toString())){
                        //找到商品
                        System.out.print("你要买多少个"+p.getPname()+":");
                        int count = sc.nextInt();
                        login.getUser().buy(p,count);
                        p.setPcount(p.getPcount()-count);
                        System.out.println(p.getPname()+"已经加入购物车了哦");
                        flag=true;
                        break;
                    }
                }
                if (!flag){
                    System.out.println("此商品不存在！");
                }
            }else if (control==2){
                login.getUser().checkSC();
            }else if (control==3){
                login.getUser().pay();
            }else{
                System.out.println("您的输入有误");
            }
        }
    }
    public static void showProduct(Product [] products){
        System.out.printf("%15s\n","商品清单");
        System.out.println("|-----------------------------|");
        System.out.printf("|%-6s|%-6s|%-6s|%-6s|\n","id","名称","price","count");
        System.out.println("|-----------------------------|");
        for (Product p:products) {
            System.out.printf("|%-6s|%-6s|%-6s|%-6s|\n",p.getPid(),p.getPname(),p.getPprice(),p.getPcount());
        }
        System.out.println("|-----------------------------|");
    }








}
