import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.CellType;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.*;
import java.text.DecimalFormat;

public class ControlExcel {


    User users[] = null;
    Product products[] = null;


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
                        user.setPassword(this.getValue(cell));
                    } else if (k == 2) {
                        user.setAddress(this.getValue(cell));
                    } else if (k == 3) {
                        user.setPhone(this.getValue(cell));
                    }else if(k == 4){
                        user.setMoney(Double.parseDouble(this.getValue(cell)));
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
                        product.setPprice(Double.parseDouble(this.getValue(cell)));
                    } else if (k == 3) {
                        product.setPcount(Integer.parseInt(this.getValue(cell)));
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

    public void changeProduct(String id,int num){
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

                    if(count<0){
                        System.out.println("对不起！您购买的商品库存不足！请减少购买量");
                        return;
                    }
                    cell.setCellValue(count);

                }
            }
            System.out.println("商品信息已更新！");
            fis.close();
            FileOutputStream fos = new FileOutputStream(new File("product.xlsx"));
            xw.write(fos);
            fos.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public  void changeMoney(String name,int cost){
        try {
            FileInputStream fis = new FileInputStream(new File("Users.xlsx"));
            XSSFWorkbook xw = new XSSFWorkbook(fis);
            //获取工作表
            XSSFSheet xs = xw.getSheetAt(0);
            //获取有数据的行数
            for (int j = 1; j <= xs.getLastRowNum(); j++) {
                XSSFRow row = xs.getRow(j);
                if (getValue(row.getCell(0)).equals(name)){
                    XSSFCell cell = row.getCell(4);
                    int money = Integer.parseInt(getValue(cell));
                    money-=cost;
                    if(money<=0){
                        System.out.println("对不起！您余额不足。。。");
                    }
                    cell.setCellValue(money);
                    System.out.println("交易成功");
                    System.out.println("您的信息:");
                    System.out.println("姓名："+row.getCell(0).getStringCellValue());
                    System.out.println("地址："+row.getCell(2).getStringCellValue());
                    System.out.println("电话："+getValue(row.getCell(3)));
                    System.out.println("余额："+row.getCell(4).getNumericCellValue());
                }

            }
            fis.close();
            FileOutputStream fos = new FileOutputStream(new File("Users.xlsx"));
            xw.write(fos);
            fos.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }



    private static String getValue(XSSFCell cell) {
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
                DecimalFormat df = new DecimalFormat("#");
                value = df.format(cell.getNumericCellValue());
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
