package hoodStuff;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.Calendar;
import java.util.GregorianCalendar;

/**
 * Created $(DATE)
 */
public class DataIntegration
{
    String value;

    public boolean isItValidCurrency()
    {
        boolean correct = true;
        double test = 0;
        try {
            test = Double.parseDouble(value);
        } catch(Exception e) {
            correct = false;
        }

        return correct;
    }

    public boolean isItValidCurrency(String valueOf)
    {
        boolean correct = true;
        double test = 0;
        try {
            test = Double.parseDouble(valueOf);
        } catch(Exception e) {
            correct = false;
        }

        return correct;
    }

    public String getValidCurrency()
    {
        double result = 0;
        if(isItValidCurrency())
        {
            result = Double.parseDouble(this.value);
            result *= 100;
            result = (int) result;
            result /= 100;
        }

        return String.valueOf(result);
    }

    public String getValidCurrency(String valueOf)
    {
        double result = 0;
        if(isItValidCurrency(valueOf))
        {
            result = Double.parseDouble(valueOf);
            result *= 100;
            result = (int) result;
            result /= 100;
        }

        return String.valueOf(result);
    }

    public boolean isValidDate(String date)
    {
        String[] dateTab = date.split("-");
        GregorianCalendar today = new GregorianCalendar();
        boolean isThisValidDate = false;

        int actualYear = today.get(Calendar.YEAR);
        int month = today.get(Calendar.MONTH) + 1;
        int day = today.get(Calendar.DAY_OF_MONTH);

        if(actualYear == Integer.parseInt(dateTab[0]) && month >= Integer.parseInt(dateTab[1]) && day >= Integer.parseInt(dateTab[2]))
        {
            if(month == Integer.parseInt(dateTab[1]))
            {
                if(day >= Integer.parseInt(dateTab[2])) isThisValidDate = true;
            }
            else if(month > Integer.parseInt(dateTab[1])) isThisValidDate = true;
        }
        else if(actualYear > Integer.parseInt(dateTab[0])){
            if(Integer.parseInt(dateTab[1]) <= 12 && Integer.parseInt(dateTab[2]) <= 32)isThisValidDate = true;
        }

        return isThisValidDate;
    }

    public DataIntegration(){}
    public DataIntegration(String value)
    {
        this.value = value;
    }
}
