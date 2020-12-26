/**
 * 测试读取excel
 */
public class Test02 {
    public static void main(String[] args) {
        ControlExcel controlExcel = new ControlExcel();
        User [] users = controlExcel.readExcel("src/Users.xlsx");
        for (User u:users) {
            System.out.println(u.getUsername());
            System.out.println(u.getPassword());
        }
    }
}
