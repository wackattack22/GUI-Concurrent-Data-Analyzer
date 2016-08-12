/**
 * HTTP Data Retriever
 *
 * CMSC 335
 * Project 4
 * @author Leo Wack
 * Fall 2015
 * IDE: Netbeans 8.0.2             
 */

package project4;

import java.util.*;
import java.io.*;
import java.net.URL;

public class HttpDataRetriever implements DataRetriever{
    
    
    @Override
    public File retrieveDataBlock() throws IOException{
        
        File file = null;
        FileOutputStream outstream = null;
        InputStream instream = null;
        try{
            //Picks a random file to download
            Random rand = new Random();
            int	fileIndex = rand.nextInt(10)+1;   
            URL dataUrl = new URL("http://cmsc335.s3-website-us-east-1.amazonaws.com/" + fileIndex + ".dat");
            instream = dataUrl.openStream();
            
           
            //Create temp file
            file = File.createTempFile("random", ".txt");
            outstream = new FileOutputStream(file);
            
            //write instream to outstream using a buffer
            byte[] buffer = new byte[1024];
            int i = instream.read(buffer);
            while (i != -1) {
                outstream.write(buffer, 0, i);
                i = instream.read(buffer);
            }
            outstream.close();
            instream.close();
        }
        catch (IOException e){
            e.printStackTrace();
        }
        return file;
    }
    
}
