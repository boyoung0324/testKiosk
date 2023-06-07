public class Product extends Menu {

    Integer bno;
    String request; //요청사항
    String orderDate; //주문일시
    String state;

    public Product(Integer bno, String name, Integer price, String request, String orderDate, String state) {
        super(name, price);
        this.bno = bno;
        this.request = request;
        this.orderDate = orderDate;
        this.state = state;
    }

    public Integer getBno() {
        return bno;
    }

    public String getRequest() {
        return request;
    }

    public String getOrderDate() {
        return orderDate;
    }


    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }
}
