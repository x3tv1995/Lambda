package org.example;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;
import java.util.function.Consumer;
import java.util.function.Predicate;
import java.util.function.Supplier;
import java.util.stream.Collectors;

/**
 * Hello world!
 */
public class App {
    public static void main(String[] args) {

        List<Product> products = Arrays.asList(
                new Product("Laptop", 999.5, LocalDate.now().plusDays(1), "electronic", LocalDate.now().plusDays(2)),
                new Product("Smartphone", 699.99, LocalDate.now().minusDays(2), "electronic", LocalDate.now().plusDays(365)),
                new Product("Tablet", 399.99, LocalDate.now().minusDays(3), "electronic", LocalDate.now().plusDays(365)),
                new Product("Smartwatch", 199.99, LocalDate.now().plusDays(1), "electronic", LocalDate.now().plusDays(1)),
                new Product("Headphones", 149.99, LocalDate.now().minusDays(5), "electronic", LocalDate.now().plusDays(365)),
                new Product("Bluetooth Speaker", 89.99, LocalDate.now().minusDays(1), "electronic", LocalDate.now().plusDays(2)),
                new Product("Gaming Console", 499.99, LocalDate.now().plusDays(7), "electronic", LocalDate.now().plusDays(365)),
                new Product("4K TV", 1299.99, LocalDate.now().minusDays(8), "electronic", LocalDate.now().plusDays(365)),
                new Product("Wireless Charger", 29.99, LocalDate.now().minusDays(9), "electronic", LocalDate.now().plusDays(365)),
                new Product("E-Reader", 119.99, LocalDate.now().minusDays(10), "electronic", LocalDate.now().plusDays(365)),
                new Product("Iphone", 1190.99, LocalDate.of(2024, 5, 20), "electronic", LocalDate.now().plusDays(365))
        );
        double average2 = products.stream().mapToDouble(Product::getPrice).average().getAsDouble();

        printItem(products, p -> p.getValidUntil().isBefore(LocalDate.now().plusDays(3))); // взял дату окончания срока годности и сравнил её с сегодняшней датой+3 дня,
        // т.е. если  getValidUntil()= 13.11 ,а LocalDate.now().plusDays(3)=14.11, то будет true, но если будет 14.11 ==14.11, то ничего не выведется это false

        printItem(products, p -> average(products) < p.getPrice());// с созданием метода average  нашёл среднее значение и вывел продукты выше среднего
        printItem(products, p -> average2 < p.getPrice());// в переменной average2  создал стрим и нашёл среднее значение


        System.out.println("Майские");
        printItem(products, p -> p.getCreationDate().getMonthValue() == 5 && p.getCreationDate().getYear() == LocalDate.now().getYear()); //продукты которые были произведены в мае текущего года
        Supplier<Product> productSupplier = () -> new Product("", 0.0, LocalDate.of(0, 1, 1), "", LocalDate.of(0, 1, 1));
        printSupplier(productSupplier);

        Supplier<Product> productSupplier2 = () -> new Product("", 0.0, LocalDate.now()
                , "", LocalDate.of(0, 1, 1));


        printSupplier(productSupplier2);
        for (Product product : printSupplier(productSupplier)) {
            System.out.println(product);
        }
        System.out.println();

        for (Product product : printSupplier(productSupplier2)) {
            System.out.println(product);
        }

    }


    public static void printItem(List<Product> list, Predicate<Product> check) {
        System.out.println();
        list.stream().filter(check).forEach(System.out::println);
        System.out.println("------------------------------------");
    }

    public static Double average(List<Product> products) {
        double maxSum = 0;
        for (int i = 0; i < products.size(); i++) {
            maxSum += products.get(i).getPrice();
        }
        return maxSum / products.size();
    }


    public static List<Product> printSupplier(Supplier<Product> supplier) {
        List<Product> products = new ArrayList<>();
        for (int i = 0; i < 10; i++) {
            products.add(supplier.get());
        }
        return products;
    }


}
