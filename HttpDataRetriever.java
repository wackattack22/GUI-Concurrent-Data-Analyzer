/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
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
            Random rand = new Random();
            int	fileIndex = rand.nextInt(10)+1;   
            URL dataUrl = new URL("http://cmsc335.s3-website-us-east-1.amazonaws.com/" + fileIndex + ".dat");
            instream = dataUrl.openStream();
            
           
            //Create temp file
            file = File.createTempFile("random", ".txt");
            outstream = new FileOutputStream(file);
            
            //Write data
            byte[] buffer = new byte[1024];
            int len = instream.read(buffer);
            while (len != -1) {
                outstream.write(buffer, 0, len);
                len = instream.read(buffer);
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
