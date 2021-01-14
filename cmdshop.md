## 1、需求理解
### （1）要实现一个商店，可以在里面进行购物
### （2）进入商店需要登录，验证用户名
### （3）登录成功后可以进行商品的购买
- 购买商品，购买后加入购物车
- 结算
- 退出购买
- 切换账户
### （4）将数据写入对应的表中
## 2、实现思路
### （1）登录
需要创建一个用户表，里面存有用户的信息。

需要创建创建一个类用来读取用户表中的信息，并返回。

返回的信息是一个用户，根据面向对象的原则，需要建立一个用户类User，其中包含属性：


向控制台输出登录界面，使用Scanner接受用户输入。
```java
    Scanner sc = new Scanner(System.in);
```
接受用户名和密码后,根据读取用户表信息到的进行比较，比较用户名与密码是否相等

如果登录成功，则进行下一步操作，否则重新登录

### （2）购买商品
购买商品是在商店里面购买的，故创建一个商店类shop:

此时我们需要商品

创建一个商品表，创建一个对应的商品类Product，包号以下属性：

商店中应有购买商品的方法

### （3）加购物车

用户可以将购买的商品加入购物车，这样我们需要一个购车类ShopCart

### （4）结算

用户可以进行结算，结算后，需要更新用户的余额，需要更新商品库存，需要生成商品订单

这样又有了一个新的需求，我们需要建立一个订单表和订单类Order，其中存储有客户的订单

## 3、如何实现

### （1）用户类的设计

用户应具有的属性：
```java
    private String username; //用户名
    private String password; //密码
    private String address; //地址
    private String phone; //电话
    private double money; //余额
    private String id; //用户id
```
用户应具有的方法：
```java
//通过id创建一个用户
public void setId(String id) {
        this.id = id;
    }

//用户可以进行购买，传入要购买的商品与数量
public void buy(Product product, int count) {}

//用户可以进行结算
public void pay(Order order) {}

//用户可以查看自己的购物车
public void checkSC() {}
```

### （2）商品类的设计
商品应具有的属性
```java
    private String Pid; //商品id
    private String Pname; //商品名 
    private double Pprice; //商品价格
    private int Pcount; //库存数量
    private int Ucount; //用户购买的数量
```

### （3）订单类的设计
订单应具有的属性：
```java
    private String id; //订单编号
    private User user; //用户
    private String createTime; //创建时间
    private String payTime; //支付时间
    private int count; //商品数量
    private int amount; //商品总额
```
### （4）购物车类的设计
属性：
```java
    private int count; //数量
    private int amount; //总金额
    private Product [] blank; //商品数组
```
方法：
```java
//可以把商品添加到购物车
public void add(Product product,int count){}

//可以显示当前购物车中商品的情况
public void showList(){}

//可以清空购物车
public void freeIt(){}
```

### （5）商店类的设计
属性：
```java
   Product[] products;//商品
```
方法：
```java
//程序的入口
public static void main(String[] args) {}

//展示商品的方法
 public static void showProduct(Product[] products) {}
```

### (5)操控excel的工具类
方法：
```java

//读取用户表
 public User[] readUserExcel(String url) {}

//修改用户表
public void changeMoney(String name, int cost) {}

//读取商品表
 public Product[] readProductExcel(String url) {}

//修改商品表
public void changeProduct(String id, int num) {}

//修改订单表
public void addOrder(Order order) {}

//修改订单关系表
public void addOrderWithProduct(String oid, String uid) {}
``` 

## 4、总结
### 收获
通过这个简单的小项目，对Java面向对像的编程思想理解更深了

通过对现实世界的抽象，设计对应的类，对于现实世界中实体的行为设计相应的方法

这样可以较好的理清程序实现的思路以及步骤，不至于让程序写得太过混乱
 ### 不足
 这次项目对实体的抽象还有很多需要改进的地方，比如：
 - 购物车没有添加购物车的行为，这个行为是用户的
 - 订单没有添加订单的方法，这个行为是用户的
 
 
 其次，还有数据冗余的问题，表的设计应符合至少第三范式，否则会造成在数据量大的时候，数据量过于庞大的问题
 
 ## 5、实验结果
 
