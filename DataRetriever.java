package project4;

import java.io.File;
import java.io.IOException;



/**
 * Retrieves data for processing. Data may reside in multiple locations such as
 * in a file, a DB, or remotely on an HTTP site. How the data is actually
 * retrieved will very by implementation.
 *
 * @author mpilone
 */
public interface DataRetriever {

  /**
   * Retrieves a block of data for processing.
   *
   * @return the file to be processed
   * @throws IOException if there is an unexpected problem retrieving the data
   * block
   */
  File retrieveDataBlock() throws IOException;
}
