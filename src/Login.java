import java.util.Scanner;

public class Login {

    private User user;

    public User getUser() {
        return user;
    }

    public void login()  {
        boolean flag = true;
        ControlExcel controlExcel  =new ControlExcel();
        User users[] = controlExcel.readUserExcel("Users.xlsx");
        Scanner sc = new Scanner(System.in);
        while (flag) {
            System.out.print("请输入用户名：");
            String username = sc.next();

            System.out.print("请输入密码：");
            String password = sc.next();
            for (User u : users) {
                if (username.equals(u.getUsername()) && password.equals(u.getPassword())) {
                    flag = false;
                    System.out.println("您的账户余额为："+u.getMoney());
                    user=u;//用户初始化
                    break;
                }
            }
            if (!flag){
                System.out.println("登录成功");

                break;
            }else {
                System.out.println("您的用户名密码不正确，请重新输入");
            }
        }
    }


}
