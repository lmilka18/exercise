import com.sun.source.util.SourcePositions;

public class Date {
    private String strYear, strMonth, strDay;
    private static final int[][] DAYSINMOMTH = {{31, 29, 31, 30, 31, 30, 31, 31, 30, 31, 30, 31}, {31, 28, 31, 30, 31, 30, 31, 31, 30, 31, 30, 31}};
    private int day, month, year;

    public Date(String InputDate) {
        strYear = InputDate.substring(6, 10);
        strMonth = InputDate.substring(3, 5);
        strDay = InputDate.substring(0, 2);
        year = Integer.parseInt(strYear);
        month = Integer.parseInt(strMonth);
        day = Integer.parseInt(strDay);
    }

    // определение високосного года
    public boolean isLeapYear() {
        int controlNumber = year % 100 == 0 ? year % 400 : year % 4;
        return (controlNumber == 0);
    }

    //определение дня недели
    public String dayOfWeek() {
        final String[] WEEKDAYS = {"воскресенье", "понедельник", "вторник", "среда", "четверг", "пятница", "суббота"};
        final int[][] TABB = {{0, 3, 4, 0, 2, 5, 0, 3, 6, 1, 4, 6}, {0, 3, 3, 6, 1, 4, 6, 2, 5, 0, 3, 5}};
        int P = year / 100;
        int Q = year % 100 - 1;
        int b = (isLeapYear() ? TABB[0][month - 1] : TABB[1][month - 1]);
        int D = ((5 * (P % 4 + Q / 4) + Q % 4 + b + day) % 7);
        String weekDay = WEEKDAYS[D];
        return weekDay;
    }

    // количество дней начиная с заданной даты до конца месяца month
    public int daysToEndOfMonth(int month) {
        int leapVariant = (isLeapYear() ? 0 : 1);
        if (month < this.month) {
            return 0;
        }
        int countDays = lastDayInMonth() - this.day;
        if (this.month < 12) {
            for (int i = this.month; i < month; i++) {
                countDays = countDays + DAYSINMOMTH[leapVariant][i - 1];
            }
        }
        return countDays;
    }

    // форматирование даты
    public String formatDay(String format) {
        final String[][] monthName = {{"1", "2", "3", "4", "5", "6", "7", "8", "9", "10", "11", "12"},
                {"01", "02", "03", "04", "05", "06", "70", "08", "09", "10", "11", "12"},
                {"янв", "фев", "мар", "апр", "май", "июн", "июл", "авг", "сен", "окт", "ноя", "дек"},
                {"января", "февраля", "марта", "апреля", "мая", "июня", "июля", "августа", "сентября", "октября", "ноября", "декабря"}};
        final int[] shiftForMonth = {0, 2, 0, 0};
        String result = format;
        for (String i = "MMMM"; i.length() > 0; i = i.substring(1)) {
            result = result.replace(i, monthName[i.length() - 1][month - 1]);
        }
        for (String i = "YYYY"; i.length() > 0; i = i.substring(1)) {
            result = result.replace(i, strYear.substring(shiftForMonth[i.length() - 1]));
        }
        for (String i = "yyyy"; i.length() > 0; i = i.substring(1)) {
            result = result.replace(i, strYear.substring(shiftForMonth[i.length() - 1]));
        }
        result = result.replace("dd", strDay);
        result = result.replace("DD", strDay);
        result = result.replace("d", (strDay.charAt(0) == '0') ? strDay.substring(1) : strDay);
        result = result.replace("D", (strDay.charAt(0) == '0') ? strDay.substring(1) : strDay);
        return result;
    }

    // печать дней, начиная со для dd из аданной даты до последнего дня inMonth месяца
    public void printDays(String inMonth, String format) {
        int month = Integer.parseInt(inMonth);
        int daysInMonth = lastDayInMonth();
        if ((day + 1) < daysInMonth)
            for (int i = day; i < daysInMonth + 1; i++) {
                String curDay = ("00" + Integer.toString(i)).substring(Integer.toString(i).length()) + '.' + inMonth + '.' + strYear;
                System.out.println(new Date(curDay).formatDay(format));
            }
    }

    public String StrMonth() {
        final String[] monthName =
                {"январь", "февраль", "март", "апрель", "май", "июнь", "июль", "август", "сентябрь", "октябрь", "ноябрь", "декабрь"};
        return monthName[month - 1];
    }

    public boolean checkIsNotDate() {
        int leapVariant = (isLeapYear() ? 0 : 1);
        try {
            if (month > 12) {
                throw new Exception("Месяц слишком большой");
            }
            if (day > lastDayInMonth()) {
                throw new Exception("День слишком слишком большой");
            }
        } catch (Exception ex) {
            System.out.print(ex.getMessage());
            return true;
        }
        return false;
    }

    public int lastDayInMonth() {
        return DAYSINMOMTH[(isLeapYear() ? 0 : 1)][month - 1];
    }

}

