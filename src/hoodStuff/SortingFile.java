package hoodStuff;

/**
 * Created $(DATE)
 */
public class SortingFile
{
    public static String[] getSortedTabOfDate(String[] tabToSort, int startLine, int sizeOfBlock, int lineToSorted) throws Exception
    {
        int sizeTab = tabToSort.length + 1;

        //Sprawdzenie warunków sortowania
        if(tabToSort == null) throw new Exception("Null exception");
        if(sizeTab <= (startLine + sizeOfBlock)) throw new Exception("Nothing to sort");

        int wsk, wsk2;
        boolean wasChange = false;

        for(int i = startLine + lineToSorted - 1; i <= sizeTab - sizeOfBlock; i += sizeOfBlock)
        {

            String[] date = new String[3];
            String[] date2 = new String[3];

            date = tabToSort[i].split("-");
            date2 = tabToSort[i + sizeOfBlock].split("-");

            if(date.length != 3) throw new Exception("Integrity error");

            wsk = Integer.parseInt(date[0]) * 12 * 30 + Integer.parseInt(date[1]) * 30 + Integer.parseInt(date[2]);
            wsk2 = Integer.parseInt(date2[0]) * 12 * 30 + Integer.parseInt(date2[1]) * 30 + Integer.parseInt(date2[2]);

            if(wsk > wsk2)
            {
                wasChange = true;

                String[] bufor = new String[sizeOfBlock + 1];
                int iterator = 0;

                for(int z = i - sizeOfBlock + 1; z <= i; z ++)
                {
                    bufor[iterator] = tabToSort[z];
                    tabToSort[z] = tabToSort[z + sizeOfBlock];
                    tabToSort[z + sizeOfBlock] = bufor[iterator];
                    iterator++;
                }

            }

        }

        if(wasChange) tabToSort = getSortedTabOfDate(tabToSort, startLine, sizeOfBlock, lineToSorted);

        return tabToSort;
    }
}
