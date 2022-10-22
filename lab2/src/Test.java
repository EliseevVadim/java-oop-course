public class Test {
    public static void main(String[] args) {
        Rectangle firstRectangle = new Rectangle(50, 50, 316, 14);
        Rectangle secondRectangle = new Rectangle(316, 40);
        Rectangle thirdRectangle = new Rectangle();
        System.out.println("first rect:");
        firstRectangle.rect_print();
        System.out.println("second rect:");
        secondRectangle.rect_print();
        System.out.println("third rect:");
        thirdRectangle.rect_print();
        firstRectangle.move(69, 96);
        secondRectangle.move(331, 6);
        thirdRectangle.move(31, 6);
        System.out.println("first rect after move:");
        firstRectangle.rect_print();
        System.out.println("second rect after move:");
        secondRectangle.rect_print();
        System.out.println("third rect after move:");
        thirdRectangle.rect_print();
        Rectangle unionResult = firstRectangle.Union(secondRectangle);
        System.out.println("union rect (idk wth it should be, but here we are):");
        unionResult.rect_print();
    }
}