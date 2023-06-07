import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.*;
import java.util.List;

public class Order {

    //조회R - 주문 후 이루어져야함
    //수정U - 주문 후

    List<Menu> orderList = new ArrayList<>(); //장바구니
    List<Product> totalOrderList = new LinkedList<>();//대기 목록
    List<Product> totalOrderList2 = new ArrayList<>();//완료 목록
    Integer orderPrice = 0; //주문 금액
    Integer totalPrice = 0; //대기 총 금액
    Integer totalPrice2 = 0; //완료 총 금액
    Integer count = 0;//대기번호
    String request = ""; //요청사항


    public void cartAdd(Menu menu) { //장바구니에 담고, 장바구니 금액 합산
        orderList.add(menu);
        orderPrice += menu.price;
    }

    public void orderListPrint() { //장바구니 출력
        int i = 1;
        for (Menu menu : orderList)
            System.out.printf("%d. %s | %d원 | %s\n", i++, menu.getName(), menu.getPrice(), menu.getDesc());
    }



    public void count() { //주문 완료했을 때
        requestMsg();
        count++;
        addTotalOrder(orderList); // (1)주문이 완료되면, 장바구니에 든 목록을 대기 목록List에 저장하고
        System.out.println("주문이 완료되었습니다!");
        System.out.println("대기번호는 [" + count + "]번 입니다.");
        System.out.println("(3초후 메뉴판으로 돌아갑니다.)");
        clearCart(); //(2)장바구니는 비운다
    }

    public void addTotalOrder(List<Menu> list) { //장바구니List를 대기목록List에 저장하는 메서드
        LocalDateTime lt = LocalDateTime.now();
        String time = lt.format(DateTimeFormatter.ofPattern("yyyy년 MM월 dd일 HH:mm:ss")); //현재 시간
        for (int i = 0; i < list.size(); i++) {
            totalOrderList.add(new Product(count, list.get(i).getName(), list.get(i).getPrice(), request, time, "대기"));
        }                               //주문번호, 이름, 가격, 요청사항, 현재시간,상태필드 초기화. 객체 생성 후 List에 add

    }


    public void requestMsg() { //주문 시 요청사항
        Scanner sc = new Scanner(System.in);
        System.out.print("요청사항을 작성해주세요 >>");
        request = sc.nextLine();
    }

    public void clearCart() { //장바구니 비우기
        orderList.clear();
        orderPrice = 0;
    }


    public void totalOrderPrint() { //대기
        System.out.println("[ 대기 주문 목록 ]");
        totalPrice = 0; //여기서, 0으로 선언 안 하면, 대기목록메서드 실행될 때마다 기존값이 계속 같이 플러스됨
        for (Product p : totalOrderList) {
            int i = 1;

            totalPrice += p.getPrice();
            System.out.printf("주문번호 : %d | %s | %d원 | 요청사항 : %s | 주문일시 : %s | %s\n",p.getBno(), p.getName(), p.getPrice(), p.getRequest(), p.getOrderDate(), p.getState());
        }
        System.out.printf("[ 대기금액 현황 : %d원 ]\n", totalPrice);
        ChoiceComplet();
    }

    private void ChoiceComplet() { //완료시킬 상품 선택하는 메서드
        Scanner sc = new Scanner(System.in);
        System.out.print("완료하실 상품을 선택해주세요 >>");
        int choice = sc.nextInt(); // (1) (ex)내가 1번을 선택했다면,

        totalOrderList2.add(totalOrderList.get(choice - 1)); // (2) List에선 0이 1번임. 선택한 choice - 1 해주고, 선택한 객체를 완료List에 add하기

        for (int i = 0; i < totalOrderList.size(); i++) {
            if (totalOrderList.get(i).getBno() == choice) {
                totalOrderList.remove(i); // 내가 선택한 번호와, 객체의 주문번호가 같으면 지워지도록 했는데, 주문번호 1번이 여러개인데 하나의 목록만 지워짐 ㅠ해결못함
            }
            return;
        }



    }


    public void totalOrder2Print() { //완료목록
        LocalDateTime lt = LocalDateTime.now();
        String time = lt.format(DateTimeFormatter.ofPattern("yyyy년 MM월 dd일 HH:mm:ss")); //완료시점의 시간
        System.out.println("[ 완료 주문 목록 ]");
        totalPrice2 = 0;
        for (Product p : totalOrderList2) { //완료목록의 타입이 Product라, Product에 담아서 출력 가능

            totalPrice2 += p.getPrice();
            System.out.printf("주문번호 : %d | %s | %d원 | 요청사항 : %s | 주문일시 : %s | 완료일시 : %s\n",
                    p.getBno(), p.getName(), p.getPrice(), p.getRequest(), p.getOrderDate(),time );

        }
        System.out.printf("[ 완료금액 현황 : %d원 ]\n", totalPrice2);

    }






    public Integer getOrderPrice() {
        return orderPrice;
    }

    public Integer getTotalPrice() {
        return totalPrice;
    }

}
