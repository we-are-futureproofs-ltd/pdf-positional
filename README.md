# pdf-positional
The pdf-positional Java application hooks into the [PDFBox](https://pdfbox.apache.org/) API in order to parse and extract word positional data from a given PDF.  The application runs as part of the consumer **extraction** process.

### Requirements
 - JDK 1.8
 - PDFBox 2.0.0 (included in repo)
 - JSONSimple (included in repo)

### Compiling & Deployment
The application was written using Netbeans and contains an ant script that configures the built jar file in such a way that it can be run in a standalone manner from the extraction consumer box.
To compile from Netbeans:
- Right-click PDFPositional root node from the Projects tree
- Select "Clean and build" from menu
- Once the build has completed the built jar file can be found in the Dist folder as PdfPositional.jar
- To complete the deployment process this file should be copied to the /data/libs/pdf-positional of the extraction repository

### Running Compliled 
 - java -jar PdfPositional.jar [options] [filename]

where filename is the file location of the PDF
where options include:

| Option      | values         |
| ----------- |:--------------:|
| --page      | numerical page |
| --mode      | scratch        |
  

