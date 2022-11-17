
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        String testDay = "00000000";
        String testMonth = "00";
        Scanner in = new Scanner(System.in);
        boolean inputIsNotCorrect = true;
        while (inputIsNotCorrect) {
            System.out.print("Введите дату в формате ДД.ММ.ГГГГ или ДД-ММ-ГГГГ: ");
            testDay = in.nextLine();
            inputIsNotCorrect = (!(testDay.matches("\\d{2}+[.,-]\\d{2}+[.,-]\\d{4}")));
            if (inputIsNotCorrect) {
                System.out.println("Ошибка. Неверный формат");
            }
        }
        Date day = new Date(testDay);
        if (day.checkIsNotDate()) {
            return;
        }
        System.out.print("Введите формат, используя символы d(D),M,y(Y): ");
        String format = in.nextLine();
        System.out.println(day.formatDay(format));
        System.out.printf("Год: %s - %s год\n", day.formatDay("YYYY"), (day.isLeapYear() ? "високосный " : "не високосный"));
        System.out.printf("Месяц: %s, содержит %s дней\n",day.StrMonth(),Integer.toString(day.lastDayInMonth()));
        System.out.printf("Число: %s - %s\n", day.formatDay("dd"), day.dayOfWeek());
        inputIsNotCorrect = true;
        while (inputIsNotCorrect) {
            System.out.print("Введите месяц в формате ММ: ");
            testMonth = in.next();
            inputIsNotCorrect = (!(testMonth.matches("\\d{2}")) || Integer.parseInt(testMonth) > 12);
            if (inputIsNotCorrect) {
                System.out.println("Месяц введен неверно");
            }
        }
        System.out.printf("До конца %s осталось %d дней\n", new Date("01." + testMonth + ".1000").formatDay("MMMM"), day.daysToEndOfMonth(Integer.parseInt(testMonth)));
        System.out.print("Печатать даты?(Да) ");
        String needPrint = in.next();
        if (!needPrint.equals("Да")) {
            return;
        }
        System.out.printf("Печать дней %s, начиная с %s-го дня:\n ", new Date("01." + testMonth + ".1000").formatDay("MMMM"), day.formatDay("dd"));
        day.printDays(testMonth,format);
    }
}