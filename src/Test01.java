import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * 这是测试创建Excel
 */
public class Test01 {
    public static void main(String[] args) {
       double d = 995.0;
       int i = 10;
        System.out.println(d-i);

        Date date = new Date();
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyyMMddhhmmss");

        System.out.println(simpleDateFormat.format(date));

        ControlExcel controlExcel = new ControlExcel();
        //controlExcel.createExcel("users.xls");
        Product [] products = new Product[2];
        for (int j = 0; j <products.length ; j++) {
            products[j] = new Product();
        }
        products[0].setPname("飞科剃须刀");
        products[0].setUcount(2);
        products[0].setPprice(20);
        products[1].setPname("松下剃须刀");
        products[1].setUcount(10);
        products[1].setPprice(100);
        //controlExcel.addOrder("zhangsan","138999999999","桂电",products);
    }
}
