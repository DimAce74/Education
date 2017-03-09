package ru.dimace;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.Calendar;

/**
 * Created by DimAce on 07.03.2017.
 */
public class App {
    public static void main(String[] args) {
        UtilClass utilClass = new UtilClass();
        short s = 0;
        byte b = 0;
        System.out.println(utilClass.compareNumberWithZero(0.0));
        System.out.println(utilClass.compareNumberWithZero(0.1));
        System.out.println(utilClass.compareNumberWithZero(new Integer(0)));
        System.out.println(utilClass.compareNumberWithZero(new Float(0)));
        System.out.println(utilClass.compareNumberWithZero(new Long(0)));
        System.out.println(utilClass.compareNumberWithZero(s));
        System.out.println(utilClass.compareNumberWithZero(b));
        System.out.println(utilClass.compareNumberWithZero(BigInteger.ZERO));
        System.out.println(utilClass.compareNumberWithZero(BigDecimal.ZERO));

    }
}
