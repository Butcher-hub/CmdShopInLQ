import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.CellType;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.*;
import java.net.URL;

public class ControlExcel {

    /**
     * 创建Excel的方法
     * @param url 创建的路径与文件名
     */
    public void createExcel(String url){
        try {
            //创建users工作表
            Workbook usersWork = new HSSFWorkbook();  // or new XSSFWorkbook();
            Sheet sheet1 = usersWork.createSheet("users");
            FileOutputStream fileOut = new FileOutputStream(url);
            usersWork.write(fileOut);
            fileOut.close();
            System.out.println(url+"创建完成");
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    /**
     * 读取Excel的方法
     * @param url 文件路径
     * @param
     * @return 用户数组
     */
    public User[] readUserExcel(String url) {
        FileInputStream fis;
//        声明一个用户数值
        User users[] = null;
        try {
            fis = new FileInputStream(new File(url));
            XSSFWorkbook xw = new XSSFWorkbook(fis);
            //获取工作表
            XSSFSheet xs = xw.getSheetAt(0);
            //获取有数据的行数
            users = new User[xs.getLastRowNum()];

            for (int j = 1; j <= xs.getLastRowNum(); j++) {
                XSSFRow row = xs.getRow(j);
                User user = new User();
                for (int k = 0; k <= row.getLastCellNum(); k++) {
                    XSSFCell cell = row.getCell(k);

                    if (cell == null)
                        continue;
                    if (k == 0) {
                        user.setUsername(this.getValue(cell));
                    } else if (k == 1) {
                        user.setPasswrod(this.getValue(cell));
                    } else if (k == 2) {
                        user.setAddress(this.getValue(cell));
                    } else if (k == 3) {
                        user.setPhone(this.getValue(cell));
                    }
                    users[j-1]=user;
                }
            }
            fis.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return users;
    }

    public Product [] readProductExcel(String url) {
//        声明一个用户数值
       FileInputStream fis;
        Product products[] = null;
        try {
            fis = new FileInputStream(new File(url));
            XSSFWorkbook xw = new XSSFWorkbook(fis);
            //获取工作表
            XSSFSheet xs = xw.getSheetAt(0);
            //获取有数据的行数
            products = new Product[xs.getLastRowNum()];

            for (int j = 1; j <= xs.getLastRowNum(); j++) {
                XSSFRow row = xs.getRow(j);
                Product product = new Product();
                for (int k = 0; k <= row.getLastCellNum(); k++) {
                    XSSFCell cell = row.getCell(k);
                    if (cell == null)
                        continue;
                    if (k == 0) {
                        product.setPid(this.getValue(cell));
                    } else if (k == 1) {
                        product.setPname(this.getValue(cell));
                    } else if (k == 2) {
                        product.setPprice(this.getValue(cell));
                    } else if (k == 3) {
                        product.setPcount(this.getValue(cell));
                    }
                    products[j-1]=product;
                }
            }
            fis.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return products;
    }

    public void buy(String id,int num){
        try {
            FileInputStream fis = new FileInputStream(new File("product.xlsx"));

            XSSFWorkbook xw = new XSSFWorkbook(fis);
            //获取工作表
            XSSFSheet xs = xw.getSheetAt(0);
            //获取有数据的行数
            for (int j = 1; j <= xs.getLastRowNum(); j++) {
                XSSFRow row = xs.getRow(j);
                if (this.getValue(row.getCell(0)).equals(id)){
                    XSSFCell cell = row.getCell(3);
                    int count = Integer.parseInt(this.getValue(cell));
                    count-=num;
                    if(count<=0){
                        System.out.println("对不起！您购买的商品库存不足！请减少购买量");
                    }
                    cell.setCellValue(count);
                    System.out.println(row.getCell(1).getStringCellValue()+"购买成功！");
                }

            }
            fis.close();
            FileOutputStream fos = new FileOutputStream(new File("product.xlsx"));
            xw.write(fos);
            fos.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private String getValue(XSSFCell cell) {
        String value;
        CellType type = cell.getCellTypeEnum();

        switch (type) {
            case STRING:
                value = cell.getStringCellValue();
                break;
            case BLANK:
                value = "";
                break;
            case BOOLEAN:
                value = cell.getBooleanCellValue() + "";
                break;
            case NUMERIC:
                value = cell.getNumericCellValue()+"";
                value=value.substring(0,value.indexOf("."));
                break;
            case FORMULA:
                value = cell.getCellFormula();
                break;
            case ERROR:
                value = "非法字符";
                break;
            default:
                value = "";
                break;
        }
        return value;
    }
}