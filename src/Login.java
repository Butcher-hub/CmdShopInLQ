import java.util.Scanner;

public class Login {
    public static void main(String[] args) throws ClassNotFoundException {
        boolean flag = true;

        ControlExcel controlExcel  =new ControlExcel();
        User users[] = controlExcel.readUserExcel("Users.xlsx");
        Product [] products  = controlExcel.readProductExcel("product.xlsx");
        Scanner sc = new Scanner(System.in);
        while (flag) {
            System.out.print("请输入用户名：");
            String username = sc.next();
            System.out.print("请输入密码：");
            String password = sc.next();

            for (User u : users) {
                if (username.equals(u.getUsername()) && password.equals(u.getPassword())) {

                    flag = false;
                    break;
                }
            }
            if (!flag){
                System.out.println("登录成功");
                showProduct(products);

                while(true) {
                    System.out.print("请输入您要购买的商品id或输入0退出购买:");
                    int id = sc.nextInt();
                    System.out.print("请输入您要购买的商品的数量:");
                    int num = sc.nextInt();
                    if (id==0){
                        System.out.println("感谢您的惠顾，欢迎下次光临");
                        break;
                    }
                    controlExcel.buy(new Integer(id).toString(),num);
                    showProduct(controlExcel.readProductExcel("product.xlsx"));
                }
            }else {
                System.out.println("您的用户名密码不正确，请重新输入");
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

    public static void showUserMes(){

    }




}
