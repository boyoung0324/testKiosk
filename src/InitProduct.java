import java.util.*;

public class InitProduct {

    //상품 생성
    //상품 삭제
    String menu = "";
    String name = "";
    Integer price = 0;
    String desc = "";
    String id = ""; //사용 못함 ㅠ

    Map<String, List<Menu>> menuMap = new HashMap<>(); //메뉴List가 들어갈 Map. 메뉴(카테고리)를 키로 설정
    List<Menu> burgerList = new ArrayList<>(); //햄버거메뉴가 들어갈 List
    List<Menu> sideList = new ArrayList<>();//사이드메뉴가 들어갈 List
    List<Menu> drinkList = new ArrayList<>();//음료수메뉴가 들어갈 List


    public void write() {

        Scanner sc = new Scanner(System.in);
        System.out.print("메뉴를 입력하세요 >>");
        menu = sc.nextLine();

        System.out.print("상품명을 입력하세요 >>");
        name = sc.nextLine();

        System.out.print("가격을 입력하세요 >>");
        price = sc.nextInt();
        sc.nextLine();

        System.out.print("상품 설명을 입력하세요 >>");
        desc = sc.nextLine();

        addMap();
    }

    public void addMap() {

        if (menu.equals("햄버거")) { //메뉴가 햄버거라면,
            burgerList.add(new Menu(name, price, desc)); //버거리스트에 담기고
            menuMap.put("햄버거", burgerList); //그 리스트가 맵에 value로 담김

        } else if (menu.equals("사이드")) {
            sideList.add(new Menu(name, price, desc));
            menuMap.put("사이드", sideList);

        } else if (menu.equals("음료수")) {
            drinkList.add(new Menu(name, price, desc));
            menuMap.put("음료수", drinkList);

        }

    }


    public void delete() {
        Scanner sc = new Scanner(System.in);
        System.out.print("삭제할 메뉴를 입력하세요 >>");
        String food = sc.nextLine(); // (1)카테고리 메뉴를 입력하면,
        List<Menu> list = returnList(food); //Map에 value(List)반환

        if(list != null){
            print(list); // (2)해당 카테고리의 메뉴가 출력된다
            System.out.print("삭제할 상품명 번호를 입력하세요 >>");
            int choice = sc.nextInt();

            for (int i = 0; i < list.size(); i++) {
                list.remove(choice - 1);
            }
            System.out.println("상품이 삭제되었습니다.");
        }else{
            System.out.println("해당 메뉴가 없습니다.");
            return;
        }

    }

    public void print(List<Menu> list) {
        int i = 1;
        for (Menu menu : list) { //같은 타입(Menu)이면, for문으로 넘겨받아서 출력 가능
            System.out.printf("%d. %s | %d원 | %s\n", i++, menu.getName(), menu.getPrice(), menu.getDesc());
        }
    }

    public List<Menu> returnList(String key) {
        return menuMap.get(key);
    } //map에 value(List객체)를 반환


}
