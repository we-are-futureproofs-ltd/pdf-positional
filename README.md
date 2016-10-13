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

Or you can use the ant script from the command line:
- ant -f [location]/PdfPositional -Dnb.internal.action.name=rebuild clean jar


### Testing
From NetBeans (without code coverage):
- Expand the Test Packages folder
- Right click on the pdfpositional folder and select "Test Package"
- The tests will be run in Test results window

From NetBeans (with code coverage):
- Right click on root PdfPositional node 
- select "Test with JaCoCoverage"
- The tests will be run in Test results window and the coverage report popped.

From command line (without code coverage)
- ant -f [location]/PdfPositional -Dnb.internal.action.name=test -Dincludes=pdfpositional/**/*Test.java -Dignore.failing.tests=true test

From command line (with code coverage)
- ant -f [location]/PdfPositional "-Drun.jvmargs=  -javaagent:\"[jacocoagentLocation]jacocoagent.jar\"=includes=pdfpositional.*:pdfpositional.exceptions.*,destfile=\"[location]/PdfPositional/jacoco.exec-312441300272722\"" test


### Running Compliled 
 - java -jar PdfPositional.jar [options] [filename]

where filename is the file location of the PDF
where options include:

| Option      | values         |
| ----------- |:--------------:|
| --page      | numerical page |
| --mode      | scratch        |


### Notes on process flow
- Parameters are checked
- Input file validated
- Output stream created (file/stdout)
- PDFBox document initialized
- PDFBox writeText method started (to kick off document processing)
- Overridden processTextPosition is called as each character is read
- Character is checked for type and either added to current word or pushed to a new word
- Last word is passed to current page data collection
- When current page is completed then page data is streamed to output as JSON
- On final page completion page data is written and streams closed

  

