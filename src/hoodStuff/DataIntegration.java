package hoodStuff;

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
        if(isItValidCurrency())
        {
            result = Double.parseDouble(valueOf);
            result *= 100;
            result = (int) result;
            result /= 100;
        }

        return String.valueOf(result);
    }

    public DataIntegration(){}
    public DataIntegration(String value)
    {
        this.value = value;
    }
}
