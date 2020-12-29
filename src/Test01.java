/**
 * 这是测试创建Excel
 */
public class Test01 {
    public static void main(String[] args) {
       double d = 995.0;
       int i = 10;
        System.out.println(d-i);
        ControlExcel controlExcel = new ControlExcel();
        controlExcel.createExcel("users.xls");
    }
}
