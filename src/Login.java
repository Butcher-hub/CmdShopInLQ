import java.util.Scanner;

public class Login {

    private User user;

    public User getUser() {
        return user;
    }

    /**
     * 登录系统的方法
     */
    public void login()  {
        boolean flag = true;
        ControlExcel controlExcel  =new ControlExcel();
        User users[] = controlExcel.readUserExcel("Users.xlsx");
        Scanner sc = new Scanner(System.in);
        System.out.println("欢迎光临小店！先登录吧！");
        while (flag) {
            System.out.print("请输入用户名：");
            String username = sc.next();
            System.out.print("请输入密码：");
            String password = sc.next();
            for (User u : users) {
                if (username.equals(u.getUsername())) {
                    if (password.equals(u.getPassword())){
                        flag = false;
                        System.out.println("登录成功");
                        System.out.println("您的账户余额为："+u.getMoney());
                        user=u;//用户初始化
                        break;
                    }else{
                        System.out.println(u.getUsername()+"的密码错误,请重新输入");
                        break;
                    }
                }else {
                    System.out.println("对不起，无此用户！请重新输入");
                    break;
                }
            }
            if (!flag){
                break;
            }
        }
    }


}
