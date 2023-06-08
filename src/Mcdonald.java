import java.util.*;

public class Mcdonald {

    InitProduct ip = new InitProduct();
    Order order = new Order();
    List<Menu> mainList = new ArrayList<>(); //메인화면에서 보여질 List
    List<Menu> mainOrderList = new ArrayList<>();
    List<Menu> mainManagerList = new ArrayList<>();

    public Mcdonald() {
        init();
    }

    public void init() {
        mainList.add(new Menu("햄버거", "주문 즉시 바로 조리해 더욱 맛있는, 맥도날드의 다양한 버거를 소개합니다"));
        mainList.add(new Menu("사이드", "가볍게 즐겨도, 버거와 함께 푸짐하게 즐겨도, 언제나 맛있는 사이드"));
        mainList.add(new Menu("음료수", "다양한 음료를 부담없이 즐기세요"));

        mainOrderList.add(new Menu("장바구니", "장바구니를 확인 후 주문합니다"));
        mainOrderList.add(new Menu("주문취소", "진행중인 주문을 취소하고 장바구니를 비웁니다"));
        mainOrderList.add(new Menu("주문 현황", "주문 현황을 보여줍니다"));

        mainManagerList.add(new Menu("대기 목록", "대기 중인 주문을 보여줍니다."));
        mainManagerList.add(new Menu("완료 목록", "완료된 주문을 보여줍니다."));
        mainManagerList.add(new Menu("상품 생성", "새 상품을 등록합니다."));
        mainManagerList.add(new Menu("상품 삭제", "상품을 삭제합니다."));
    }


    public void kiosk() { //1번
        java.lang.System.out.println("-------------------------------------");
        java.lang.System.out.println("★ 맥도날드에 오신걸 환영합니다 ★");
        java.lang.System.out.println("↓ 아래 메뉴판을 골라 입력해주세요.\n");

        java.lang.System.out.println("[MAIN MENU]");
        int nextnum = mainPrint(1, mainList);
        java.lang.System.out.println();

        java.lang.System.out.println("[ORDER MENU]");
        nextnum = mainPrint(nextnum, mainOrderList);
        java.lang.System.out.println();
        System.out.println("0. MANAGER MENU로 이동");
        java.lang.System.out.println("-------------------------------------");

        ChoiceMenu();
    }

    public int mainPrint(int num, List<Menu> screen) { //main프린트 해주는 공통 메서드
        for (int i = 0; i < screen.size(); i++) {
            java.lang.System.out.printf("%d. %s | %s\n", num++, screen.get(i).getMenu(), screen.get(i).getDesc());
        }
        return num;
    }


    public void ChoiceMenu() { //2번
        Scanner sc = new Scanner(java.lang.System.in);
        int choice = sc.nextInt();

        switch (choice) {
            case 1:
                burgerPrint();
                break;
            case 2:
                sidePrint();
                break;
            case 3:
                drinkPrint();
                break;
            case 4:
                cartList();
                break;
            case 5:
                cancelPrint();
                break;
            case 6:
                order.recentOrder();
                break;
            case 0:
                managerPrint();
                break;

            default:
                java.lang.System.out.println("해당하는 메뉴가 없습니다.");
                returnMain();
        }
    }

    public void managerPrint() {
        java.lang.System.out.println("[MANAGER MENU]");
        mainPrint(1, mainManagerList);
        System.out.println();
        System.out.println("0. MAIN MENU로 이동");

        choiceManagerMenu();
    }

    public void choiceManagerMenu() {
        Scanner sc = new Scanner(System.in);
        int choice = sc.nextInt();
        switch (choice) {
            case 1:
                waitPrint();
                break;
            case 2:
                completionPrint();
                break;
            case 3:
                writeComplet();
                break;
            case 4:
                delComplet();
                break;
            case 0:
                kiosk();
                break;

            default:
                java.lang.System.out.println("해당하는 메뉴가 없습니다.");
                returnManagerMenu();
        }
    }


    public void burgerPrint() {
        java.lang.System.out.println("★ 맥도날드에 오신걸 환영합니다 ★");
        java.lang.System.out.println("↓ 아래 메뉴판을 골라 입력해주세요.\n");
        java.lang.System.out.println("[BURGER MENU]");
        List<Menu> burgerList = ip.returnList("햄버거");
        if (burgerList != null) {
            foodPrint(burgerList);
        } else {
            java.lang.System.out.println("상품이 없습니다.");
            returnMain();
        }
    }

    public void sidePrint() {
        java.lang.System.out.println("★ 맥도날드에 오신걸 환영합니다 ★");
        java.lang.System.out.println("↓ 아래 메뉴판을 골라 입력해주세요.\n");
        java.lang.System.out.println("[SIDE MENU]");
        List<Menu> sideList = ip.returnList("사이드");
        if (sideList != null) {
            foodPrint(sideList);
        } else {
            java.lang.System.out.println("상품이 없습니다.");
            returnMain();
        }
    }

    public void drinkPrint() {
        java.lang.System.out.println("★ 맥도날드에 오신걸 환영합니다 ★");
        java.lang.System.out.println("↓ 아래 메뉴판을 골라 입력해주세요.\n");
        java.lang.System.out.println("[DRINK MENU]");
        List<Menu> drinkList = ip.returnList("음료수");
        if (drinkList != null) {
            foodPrint(drinkList);
        } else {
            java.lang.System.out.println("상품이 없습니다.");
            returnMain();
        }

    }


    void foodPrint(List<Menu> foodList) { //음식 프린트 공동 사용
        for (int i = 0; i < foodList.size(); i++) {
            java.lang.System.out.printf("%d. %s | %d원 | %s\n", i + 1, foodList.get(i).name, foodList.get(i).price, foodList.get(i).desc);
        }
        foodChoice(foodList);
    }

    void foodChoice(List<Menu> foodList) { //음식 선택 메서드 공동 사용
        Scanner sc = new Scanner(java.lang.System.in);
        int choice = sc.nextInt();
        if (choice >= 1 && choice <= foodList.size()) { //메뉴번호 1,2,3,4
            Menu menu = foodList.get(choice - 1); // 메뉴번호에서 -1하면 인덱스랑 맞춰짐
            orderCart(menu);
        } else {
            java.lang.System.out.println("해당하는 상품이 없습니다.");
            returnMain();
        }

    }

    void orderCart(Menu menu) { //공통 메서드2
        java.lang.System.out.printf("[%s | %d원 | %s]\n", menu.getName(), menu.getPrice(), menu.getDesc());
        java.lang.System.out.println("위 메뉴를 장바구니에 추가하시겠습니까?");
        java.lang.System.out.println("1. 확인\t 2. 취소");
        cartInput(menu);
    }


    void cartInput(Menu menu) { //공통 메서드 3
        Scanner sc = new Scanner(java.lang.System.in);
        int choice = sc.nextInt();
        if (choice == 1) {
            order.cartAdd(menu);
            java.lang.System.out.println("장바구니에 추가되었습니다.");
            kiosk();
        } else if (choice == 2) {
            kiosk();
        } else {
            java.lang.System.out.println("해당하는 메뉴가 없습니다.");
            returnMain();
        }
    }

    void writeComplet() { //상품등록 완료
        ip.write();
        java.lang.System.out.println();
        java.lang.System.out.println("상품이 등록되었습니다. ");
        returnManagerMenu();
    }

    void delComplet() { //지우기 완료
        ip.delete();

        returnManagerMenu();
    }


    public void cartList() {
        if (order.orderList.size() != 0) {

            java.lang.System.out.println("[Order]");
            order.orderListPrint();
            java.lang.System.out.println();

            java.lang.System.out.println("[Total]");
            java.lang.System.out.println("총 가격 : " + order.getOrderPrice() + "원");

            java.lang.System.out.println("위와 같이 주문 하시겠습니까?");
            java.lang.System.out.println("1. 주문\t 2. 메뉴판");
            orderInput();
        } else {
            java.lang.System.out.println("[  장바구니가 비었습니다. ]");
            returnMain();

        }

    }

    void orderInput() {
        Scanner sc = new Scanner(java.lang.System.in);
        int choice = sc.nextInt();
        if (choice == 1) {
            orderSuccess();
        } else if (choice == 2) {
            kiosk();
        } else {
            java.lang.System.out.println("해당하는 메뉴가 없습니다.");
            returnMain();
        }
    }

    public void orderSuccess() { //주문 완료
        order.count();
        try {
            Thread.sleep(3000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        kiosk();
    }

    public void returnMain() {
        Scanner sc = new Scanner(java.lang.System.in);
        java.lang.System.out.println();
        java.lang.System.out.println("0. 메인으로 돌아가기");
        int choice = sc.nextInt();
        if (choice == 0) {
            kiosk();
        } else {
            returnMain();
        }
    }

    public void returnManagerMenu() {
        Scanner sc = new Scanner(java.lang.System.in);
        java.lang.System.out.println();
        java.lang.System.out.println("0. 관리메뉴로 돌아가기");
        int choice = sc.nextInt();
        if (choice == 0) {
            managerPrint();
        } else {
            returnManagerMenu();
        }
    }

    public void waitPrint() { //대기 상품 목록
        if (order.waitList.size() != 0) {
            order.waitListPrint();
            java.lang.System.out.println();
            java.lang.System.out.println("완료되었습니다.");
            returnManagerMenu();
        } else {
            java.lang.System.out.println("[ 대기중인 상품이 없습니다. ]");
            returnManagerMenu();
        }
    }

    public void completionPrint() { //완료 상품 목록
        if (order.compList.size() != 0) {
            order.compListPrint();
            returnManagerMenu();
        } else {
            java.lang.System.out.println("[ 완료된 상품이 없습니다. ]");
            returnManagerMenu();
        }
    }

    public void cancelPrint() {
        Scanner sc = new Scanner(System.in);
        System.out.println("진행하던 주문을 취소하시겠습니까?");
        System.out.println("1. 확인\t 2. 취소");
        int choice = sc.nextInt();

        if (choice == 1) {
            System.out.println("진행하던 주문이 취소되었습니다.");
            order.clearCart();
            kiosk();
        } else if (choice == 2) {
            kiosk();
        }
    }
}