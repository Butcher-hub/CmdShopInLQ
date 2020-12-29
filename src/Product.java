/**
 * @Author: butcher
 * @Date: 2020/12/27/13:03
 */
public class Product {
    private String Pid;
    private String Pname;
    private double Pprice;
    private int Pcount;
    private int Ucount;

    public int getUcount() {
        return Ucount;
    }

    public void setUcount(int ucount) {
        Ucount = ucount;
    }

    public String getPid() {
        return Pid;
    }

    public void setPid(String pid) {
        Pid = pid;
    }

    public String getPname() {
        return Pname;
    }

    public void setPname(String pname) {
        Pname = pname;
    }

    public double getPprice() {
        return Pprice;
    }

    public void setPprice(double pprice) {
        Pprice = pprice;
    }

    public int getPcount() {
        return Pcount;
    }

    public void setPcount(int pcount) {
        Pcount = pcount;
    }
}
