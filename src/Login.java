import java.util.Scanner;

public class Login {
    public static void main(String[] args) {
        boolean flag = true;
        User users[] = load();
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
            }else {
                System.out.println("您的用户名密码不正确，请重新输入");
            }
        }
    }

    public static User[] load(){
        ControlExcel controlExcel  =new ControlExcel();
        return controlExcel.readExcel("Users.xlsx");
    }


}
