import org.apache.poi.ss.usermodel.HorizontalAlignment;
import org.apache.poi.ss.usermodel.CellType;
import org.apache.poi.xssf.usermodel.*;

import java.io.*;
import java.text.DecimalFormat;

public class ControlExcel {

    User users[] = null;
    Product products[] = null;

    /**
     * 读取用户Excel的方法
     *
     * @param url 文件路径
     * @return 用户数组
     */
    public User[] readUserExcel(String url) {
        FileInputStream fis;

        try {
            fis = new FileInputStream(new File(url));
            XSSFWorkbook xw = new XSSFWorkbook(fis);
            //获取工作表
            XSSFSheet xs = xw.getSheetAt(0);
            //通过获取用户表中的用户行数，创建用户数组
            users = new User[xs.getLastRowNum()];

            for (int j = 1; j <= xs.getLastRowNum(); j++) {
                XSSFRow row = xs.getRow(j);
                User user = new User();
                for (int k = 0; k <= row.getLastCellNum(); k++) {
                    XSSFCell cell = row.getCell(k);

                    if (cell == null)
                        continue;

                    if (k == 0) {
                        user.setId(this.getValue(cell));
                    } else if (k == 1) {
                        user.setUsername(this.getValue(cell));
                    } else if (k == 2) {
                        user.setPassword(this.getValue(cell));
                    } else if (k == 3) {
                        user.setAddress(this.getValue(cell));
                    } else if (k == 4) {
                        user.setPhone(this.getValue(cell));
                    } else if (k == 5) {
                        user.setMoney(Double.parseDouble(this.getValue(cell)));
                    }
                    users[j - 1] = user;
                }
            }
            fis.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return users;
    }

    /**
     * 读取商品excel的方法
     *
     * @param url 商品文件的地址
     * @return 商品数组
     */
    public Product[] readProductExcel(String url) {
        FileInputStream fis;

        try {
            fis = new FileInputStream(new File(url));
            XSSFWorkbook xw = new XSSFWorkbook(fis);
            XSSFSheet xs = xw.getSheetAt(0);
            //创建商品数组
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
                    products[j - 1] = product;
                }
            }
            fis.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return products;
    }

    /**
     * 修改商品表中商品的信息
     *
     * @param id  商品编号
     * @param num 减少的数量
     */
    public void changeProduct(String id, int num) {
        try {
            FileInputStream fis = new FileInputStream(new File("product.xlsx"));
            XSSFWorkbook xw = new XSSFWorkbook(fis);
            //获取工作表
            XSSFSheet xs = xw.getSheetAt(0);
            //获取有数据的行数
            for (int j = 1; j <= xs.getLastRowNum(); j++) {
                XSSFRow row = xs.getRow(j);
                if (this.getValue(row.getCell(0)).equals(id)) {
                    XSSFCell cell = row.getCell(3);
                    int count = Integer.parseInt(this.getValue(cell));
                    count -= num;
                    if (count < 0) {
                        System.out.println("对不起！您购买的" + this.getValue(row.getCell(1)) + "库存不足！请减少购买量");
                        return;
                    }
                    cell.setCellValue(count);
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

    /**
     * 修改用户的余额的方法
     *
     * @param name 用户姓名
     * @param cost 减少的钱数
     */
    public void changeMoney(String name, int cost) {
        try {
            FileInputStream fis = new FileInputStream(new File("Users.xlsx"));
            XSSFWorkbook xw = new XSSFWorkbook(fis);
            XSSFSheet xs = xw.getSheetAt(0);
            for (int j = 1; j <= xs.getLastRowNum(); j++) {
                XSSFRow row = xs.getRow(j);
                if (getValue(row.getCell(1)).equals(name)) {
                    XSSFCell cell = row.getCell(5);
                    int money = Integer.parseInt(getValue(cell));
                    money -= cost;
                    if (money <= 0) {
                        System.out.println("对不起！您余额不足。。。");
                        return;
                    }
                    cell.setCellValue(money);
                    System.out.println("交易成功");
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

    /**
     * 绑定订单与用户的方法
     *
     * @param oid 订单编号
     * @param uid 用户编号
     */
    public void addOrderWithProduct(String oid, String uid) {
        try {
            FileInputStream fis = new FileInputStream(new File("productAndOder.xlsx"));
            XSSFWorkbook xw = new XSSFWorkbook(fis);
            XSSFSheet xs = xw.getSheetAt(0);
            int index = xs.getLastRowNum() + 1;
            XSSFRow row = xs.createRow(index);
            for (int i = 0; i < 2; i++) {
                if (i == 0) {
                    row.createCell(i).setCellValue(oid);
                } else if (i == 1) {
                    row.createCell(i).setCellValue(uid);
                }
            }
            fis.close();
            FileOutputStream fos = new FileOutputStream(new File("productAndOder.xlsx"));
            xw.write(fos);
            fos.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * 添加订单的方法
     */
    public void addOrder(Order order) {
        try {
            FileInputStream fis = new FileInputStream(new File("order.xlsx"));
            XSSFWorkbook xw = new XSSFWorkbook(fis);

            XSSFCellStyle alignStyle = xw.createCellStyle();
            alignStyle.setAlignment(HorizontalAlignment.CENTER);//水平居中
            XSSFSheet xs = xw.getSheetAt(0);
            int rowindex = 0;
            rowindex = xs.getLastRowNum() + 1;

            XSSFRow row = xs.createRow(rowindex);
            row.createCell(0).setCellValue(order.getId());//订单编号
            row.createCell(1).setCellValue(order.getUser().getId());//用户编号
            row.createCell(2).setCellValue(order.getUser().shopCart.getCount());//商品数量
            row.createCell(3).setCellValue(order.getUser().shopCart.getAmount());//商品总价
            row.createCell(4).setCellValue(order.getCreateTime());//创建时间

            fis.close();
            FileOutputStream fos = new FileOutputStream(new File("order.xlsx"));
            xw.write(fos);
            fos.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * 获取excel表中值的方法
     *
     * @param cell
     * @return
     */
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
