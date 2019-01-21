package hoodStuff;

/**
 * Created $(DATE)
 */
public class SortingFile
{
    public static String[] getSortedTab(String[] tabToSort, int startLine, int sizeOfBlock, int lineToSorted) throws Exception
    {
        int sizeTab = tabToSort.length;

        //Sprawdzenie warunków sortowania
        if(tabToSort == null) throw new Exception("Null exception");
        if(sizeTab <= (startLine + sizeOfBlock)) throw new Exception("Nothing to sort");

        for(int i = startLine + lineToSorted; i <= sizeTab -1 ; i += sizeOfBlock)
        {
            String[] date = new String[3];

            date = tabToSort[3].split("-");

            System.out.println(date[2]);
        }

        return tabToSort;
    }
}
