import java.util.Scanner;

/**
 * @Author: butcher
 * @Date: 2020/12/27/16:00
 */
public class Shop {
    public static void main(String[] args) {
        Login login = new Login();
        login.login();
        ControlExcel controlExcel  =new ControlExcel();
        Product [] products  = controlExcel.readProductExcel("product.xlsx");
        Scanner sc = new Scanner(System.in);
        showProduct(products);
        while(true) {
            System.out.print("请输入您要购买的商品id或输入0退出购买:");
            int id = sc.nextInt();
            if (id==0){
                System.out.println("期待您的下次光临！");
                break;
            }
            System.out.print("请输入您要购买的商品的数量:");
            int num = sc.nextInt();
            controlExcel.buy(new Integer(id).toString(),num,login.getName());
            showProduct(controlExcel.readProductExcel("product.xlsx"));
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
