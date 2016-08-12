/**
 * Executor Data Processor
 *
 * CMSC 335
 * Project 4
 * @author Leo Wack
 * Fall 2015
 * IDE: Netbeans 8.0.2             
 */
package project4;


import java.io.*;
import java.util.concurrent.*;
import java.util.*;


public class ExecutorDataProcessor implements DataProcessor{
    private AnalysisAlgorithm algorithm = null;
    private File outputDir = null;
    private int fileCount = 0;
    ExecutorService executorService;
     
    //Construct with output source location, algorithm choice, and thread count
    public ExecutorDataProcessor(File outputDir, AnalysisAlgorithm algorithm, int threadCount){
        this.outputDir = outputDir;
        this.algorithm = algorithm;
        //Instantiate executor service with user defined thread count
        this.executorService = Executors.newFixedThreadPool(threadCount);
    }
    
    @Override
    public Future<File> processDataBlock(File dataFile){
        //Submit worker to executor service
        Future task = executorService.submit(new Worker(dataFile));
        return task;
    }
    //Shuts down threads
    @Override
    public void shutdown(){
        executorService.shutdown();
    }
    
    
    //Inner Callable Worker class
    private class Worker implements Callable<File> {
        
        private File inputFile = null;
        private File newFile = new File(outputDir.toString()+"\\Output"+(++fileCount)+".txt");
        //Constructs with input data source
        public Worker(File inputFile) {
            this.inputFile = inputFile;
        }
        
        @Override
        public File call() throws Exception {
            //Data processing
            try{
                PrintWriter writer = new PrintWriter(newFile);    //output
                Scanner s = new Scanner(inputFile);                 //input
                //reads each line of the data file
                //passes int to algorithm
                //writes results to output
                while(s.hasNextInt()){
                    int num = s.nextInt();
                    writer.println(algorithm.analyzeValue(num));
                }
                writer.close();
                s.close();
            }
            catch(IOException e){
                e.printStackTrace();
            }
            return newFile;
        }
    }//end Worker
}
