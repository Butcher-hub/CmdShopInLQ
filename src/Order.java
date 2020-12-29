public class Order extends User{
    private String pname;
    private String pcount;
    private String pamount;

    public String getPname() {
        return pname;
    }

    public void setPname(String pname) {
        this.pname = pname;
    }

    public String getPcount() {
        return pcount;
    }

    public void setPcount(String pcount) {
        this.pcount = pcount;
    }

    public String getPamount() {
        return pamount;
    }

    public void setPamount(String pamount) {
        this.pamount = pamount;
    }
}
