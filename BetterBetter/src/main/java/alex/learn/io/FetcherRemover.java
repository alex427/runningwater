package alex.learn.io;

import java.io.*;

public class FetcherRemover {
    public static void main(String [] args) throws Exception {
        BufferedReader bufferedReader = new BufferedReader(new FileReader(new File("f://log.text")));
        BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(new File("f://logg.text")));

        String line = null;
        while ((line = bufferedReader.readLine()) != null) {
            if(line.contains("[fetcher#5]")){
                bufferedWriter.write(line);
                bufferedWriter.newLine();
                bufferedWriter.flush();
            }
        }
        bufferedReader.close();
        bufferedWriter.close();
    }
}
