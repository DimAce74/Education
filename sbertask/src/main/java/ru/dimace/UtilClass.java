package ru.dimace;


import java.math.BigDecimal;
import java.math.BigInteger;
import java.text.SimpleDateFormat;
import java.util.*;

public class UtilClass {
    private Set<List<Byte>> listElements = new HashSet<>();
    private SimpleDateFormat format = new SimpleDateFormat("dd-MM-yy");

    public Set<List<Byte>> getListElements() {
        return this.listElements;
    }

    public void setListElements(Set<List<Byte>> newListElements) {
        this.listElements = newListElements;
    }

    public void addElement(List<Byte> element) {
        if (!listElements.contains(element))
            listElements.add(element);
    }

    public <Number> boolean compareNumberWithZero(Number obj) {


        if (obj instanceof Byte && (Byte)obj==0) {
            return true;
        } else if (obj instanceof Short && ((Short)obj) == 0) {
            return true;
        } else if (obj instanceof Integer && ((Integer)obj) == 0) {
            return true;
        } else if (obj instanceof Long && ((Long)obj) == 0) {
            return true;
        } else if (obj instanceof Double && ((Double)obj).equals(0.00)) {
            return true;
        } else if (obj instanceof Float && new Float("0.0").equals(obj)) {
            return true;
        } else if (obj instanceof BigInteger && BigInteger.ZERO.equals(obj)) {
            return true;
        } else if (obj instanceof BigDecimal && BigDecimal.ZERO.equals(obj)) {
            return true;
        }
        return false;
    }

        /**
        if (obj instanceof Integer && ((Integer)obj) == 0) {
            return true;
        } else if (obj instanceof Double && obj.equals(0.00)) {
            return true;
        } else if (obj instanceof Float && new Float("0.0").equals(obj)) {
            return true;
        } else if (obj instanceof BigDecimal && BigDecimal.ZERO.equals(obj)) {
            return true;
        }
        return false;
    }
*/
    /**
     * Метод логирует данные (предполагается использование из внешних модулей).
     * Формат: [текущая дата в виде dd-MM-yy]: stroka; dataElements как строка (через сепаратор)
     * @param stroka строка из инпута
     * @param dataElements коллеция с данными для логирования
     * @param separator символ, который используется для разделения элементов из коллеции dataElements для создания строкового представления.
     */
    void LogData(String stroka, Collection dataElements, char separator) {
        stroka.trim();
        String result = "[" + format.format(new Date()) + "]: " + stroka + "; ";

        for (Object obj : dataElements)
            result += obj + " ";

        System.out.println(result);
    }

    /**
     * [startDate или текущая дата, если startDate = null или меньше текущей даты] + 5 рабочих дней + 1 календарный день + 1 год.
     * classifier - справочник содержащий даты выходных и праздничных дней
     */
    public static Date getDatePlus5WorkDaysPlus1DayPlus1Year(Date startDate, List<InsuranceHoliday> classifier) {
        Calendar calendar = Calendar.getInstance();
        if (startDate != null & startDate.compareTo(calendar.getTime()) < 0) {
            calendar.setTime(startDate);
        }
        int workingDays = 0;
        while (workingDays < 5) {
            calendar.add(Calendar.DAY_OF_YEAR, 1);
            boolean weekend = false;
            for (InsuranceHoliday holiday : classifier) {
                if (holiday.getTheDate().getYear() == calendar.getTime().getYear()
                        && holiday.getTheDate().getMonth() == calendar.getTime().getMonth()
                        && holiday.getTheDate().getDay() == calendar.getTime().getDay()) {
                    weekend = true;
                    break;
                }
            }
            if (!weekend)
                workingDays++;
        }
        calendar.add(Calendar.DAY_OF_YEAR, 1);
        calendar.add(Calendar.YEAR, 1);
        return calendar.getTime();
    }

}

