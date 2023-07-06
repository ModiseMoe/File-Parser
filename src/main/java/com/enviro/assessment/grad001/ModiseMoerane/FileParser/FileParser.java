
package com.enviro.assessment.grad001.ModiseMoerane.FileParser;

import java.io.File;
import java.net.URI;


public interface FileParser {
    
    void parseCSV(File csvFile);
    File convertCSVDataToImage(String base64ImageData,  String imgFormat);
    URI createImageLink(File fileImage);
}
