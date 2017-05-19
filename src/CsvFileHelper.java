    /*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package src;

import java.io.File;

/**
 *
 * @author Florian
 */
public class CsvFileHelper {
    
    public static String getResourcePath(String fileName) {
       final File f = new File("");
       final String dossierPath = f.getAbsolutePath() + File.separator + fileName;
       return dossierPath;
   }

   public static File getResource(String fileName) {
       final String completeFileName = getResourcePath(fileName);
       File file = new File(completeFileName);
       return file;
   }


private final static String FILE_NAME = "CommunesFrance.csv";

    
    public void testGetResource() {
        // Param
        final String fileName = FILE_NAME;

        // Result
        // ...

        // Appel
        final File file = CsvFileHelper.getResource(fileName);

        // Test
       
    }

}