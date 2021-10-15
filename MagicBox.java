import java.util.ArrayDeque;
import java.util.Arrays;
import java.util.Scanner;
import java.util.stream.Collectors;

public class MagicBox {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        ArrayDeque<Integer> firstBox = Arrays.stream(scanner.nextLine().split("\\s+"))
                .map(Integer::parseInt)
                .collect(Collectors.toCollection(ArrayDeque::new));

        ArrayDeque<Integer> secondBox = new ArrayDeque<>();

        Arrays.stream(scanner.nextLine().split("\\s+"))
                .map(Integer::parseInt)
                .forEach(secondBox::push);

        int itemsWorth = 0;

        while (!firstBox.isEmpty() && !secondBox.isEmpty()) {
            int firstItem = firstBox.peek();
            int secondItem = secondBox.pop();

            int sum = firstItem + secondItem;

            if (sum % 2 == 0) {
                itemsWorth += sum;
                firstBox.poll();
            } else {
                firstBox.offer(secondItem);
            }
        }

        String emptyBoxOutput = firstBox.isEmpty()
                ? "First magic box is empty."
                : "Second magic box is empty.";

        String itemsValueOutput = itemsWorth >= 90
                ? "Wow, your prey was epic! Value: "
                : "Poor prey... Value: ";

        System.out.println(emptyBoxOutput);
        System.out.println(itemsValueOutput + itemsWorth);
    }
}
