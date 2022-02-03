import java.io.File;
import java.io.IOException;

import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.pdmodel.PDPage;
import org.apache.pdfbox.pdmodel.PDPageContentStream;
import org.apache.pdfbox.pdmodel.graphics.image.PDImageXObject;

public class PDFExcample {

	public static void main(String[] args) throws IOException {
		String imagePath = "S:\\CrawlingTest\\";
        String pdfPath = "S:\\CrawlingTest\\toon.pdf";
         
        if (!pdfPath.endsWith(".pdf")) {
            System.err.println("Last argument must be the destination .pdf file");
            System.exit(1);
        }
        
    	PDDocument doc = new PDDocument();
    	
    	File imgDir = new File(imagePath);
    	File[] imgFiles = imgDir.listFiles();
    	System.out.println(imgFiles.length);
    	for(int i=1; i<=imgFiles.length; i++) {
            PDPage page = new PDPage();
            doc.addPage(page);
             
            // capture한 이미지 이름이 1.jpg, 2.jpg 이런식으로 되어있음.
            PDImageXObject pdImage = PDImageXObject.createFromFile(imagePath+i+".jpg", doc);
             
            PDPageContentStream contents = new PDPageContentStream(doc, page);
             
            contents.drawImage(pdImage, 0, 0, 612, 796);
             
            contents.close();
            doc.save(pdfPath);
            System.out.print(i+" ");
            if(i%50 == 0) System.out.println("");
    	}
	}

}
