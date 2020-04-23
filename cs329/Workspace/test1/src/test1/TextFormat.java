package test1;

import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Scanner;
import java.io.FileNotFoundException; // go over

public class TextFormat {

    // These "static final" identifiers are called "symbolic constants". They're
    // a good way of localizing assumptions about concrete values

    private static final int MARGIN_LEFT = 5, MARGIN_RIGHT = 75, TEXT_HEIGHT = 60;
    private static final String INDENT_LEFT = "     ";

    private static final int TITLE = 0, H1 = 1, LEFT = 2, RIGHT = 3, JUSTIFY = 4, BLOCKQUOTE = 5;
    
    private static Scanner inp;
    private static String result; 


    public static void main(String[] args) throws IOException {

        if (args.length < 0) {
            System.err.println("USAGE: java " + TextFormat.class.getSimpleName() + "<input file>");
            System.exit(1); // This causes your application to exit immediately
                            // but cleanly.
        }

        inp = new Scanner(System.in);
        // inp = makeReader(args[0]);
        PrintWriter outp = makeWriter("mkp.out");

        // calculate formatted text, ignoring page break/numbering problems
        String formatted = "";

        while (inp.hasNext()) { // primary translation loop
            String tag = inp.next().trim();

            if (tag.equals("<title>")) {
            	/*TODO: call title method 
            	 * 
            	 */
            	
                System.err.println("title");

            } else if (tag.equals("<h1>")) {

                System.err.println("h1");

            } else if (tag.equals("<left>")) {

                System.err.println("left");
                formatted += fLeft(inp);
                /*
                 * When fLeft() returns, the stream that inp points to has
                 * advanced past the closing </left> tag. If you support aht
                 * same convention for the other five tags, then within this
                 * main method, the only possible values for tag= inp.next() are
                 * the six opening tags.
                 */

            } else if (tag.equals("right")) {

                System.err.println("right");

            } else if (tag.equals("justify")) {

                System.err.println("justify");

            } else if (tag.equals("blockquote")) {

                System.err.println("blockquote");
            } else {
                // Thjs should never be reached, if your input file has a valid
                // tag markup
                // System.err.println("Unrecognized command \"" + line +
                // "\" at line " + linect);
            }
        } // end primary translation loop

        // Now use this formatted text to copy lines onto pages, with
        // appropriate page nbumbering and form feed at the end of each page
        
        inp.close();
        outp.close();
    } // main

    private static String fLeft(Scanner inp) {
        /*
         * As discussed in my comments for the call to fLeft (in main()), the
         * idea here is to read every word of the content that occurs between
         * the opening <left> tag (which was just consumed) and the closing
         * </left> tag (which will be the next non-content item, in a valid
         * input file.
         * 
         */
        String content = "";
        String word = inp.next();

        while (!word.equals("</left>")) {
            content += word + " ";
            word = inp.next();
        }
        /*
         * At this point, inp has consumed the closing </left>, and the next
         * thing it reads will be the next opening tag. That means we've read
         * everything that needs to be formatted within this method call.
         */

        // prototype: eventually, you'll build a String containing the formatted
        // version of the content.
        return content;
    }

    public static Scanner makeReader(String infName) {
        File file = new File(infName);
            try{
                Scanner inp = new Scanner(file);
            }
            catch(FileNotFoundException e){
                System.out.println("Unable to find file");
            }
        
        // prototype: eventually you'll replace this with a Scanner that is
        // constructed on a file handle that corresponds to infName

        return inp;

    } // makeReader

    public static PrintWriter makeWriter(String outfName) {
        File writeFile = new File(outfName);
        try {
        PrintWriter writer = new PrintWriter(writeFile);
        
        return writer;

        }catch(Exception e) {}
        
        // prototype: eventually, you'll replace this with a PrintWriter that is
        // constructed from a handle for the output file
        return null;
    }
    
    public static String pad (String s, int min){
        String padding = "";
        int diff = min - s.length();
        int sum = diff/2;
        int i = 0;
            while (i<sum){
                padding = padding + "";
                i = i+1;
            }
            return padding + s;
        
    } //pad 
    
    public static void title(){
        String titlecontent = " ";
        String word = inp.next();
            while (!word.equals("</title>")){
                titlecontent += word + " "; 
                word = inp.next();
            }
            titlecontent = titlecontent.toUpperCase();
            titlecontent = pad(titlecontent,70);  
            
            //result = result + titlecontent;
            //writer.write(titleContent);
    } //title
    
    public static void h1(){
        String h1content = " ";
        String word = inp.next();
            while (!word.equals("</h1>")){
                h1content = "     " + h1content;
                h1content += word + " "; 
                word = inp.next();
            }

            String name = "";
            int length = name.length();
            int i = 0;
            String dash = "-";
                while (i<length){
                    dash = dash + 1;
                    i = i + 1;     
                }
            } //h1
            
     public static String left(){
        String left = " ";
        left = inp.next();
        String total = "";
            while (!left.equals("</left>")){
                left = "    " + left;
                left += left + " "; 
                left = inp.next();
            while (left.length()<70){
                left = left + inp.next();
            } 
              total = total + "/n" + left;     
            }
                return total; 
         } //left

    public static String right(){
        String right = " ";
        right = inp.next();
        String total = "";
            while (!right.equals("</right>")){
                right = "    " + right;
                right += right + " "; 
                right = inp.next();
            while (right.length()<70){
                right = right + inp.next();
            } 
              total = total + "/n" + right;     
            }
                return total; 
         } //right

    public static String blockquote(){
        String blockquote = " ";
        blockquote = inp.next();
        String total = "";
            while (!blockquote.equals("</blockquote>")){
                blockquote = "          " + blockquote;
                blockquote += blockquote + " "; 
                blockquote = inp.next();
            while (blockquote.length()<70){
                blockquote = blockquote + inp.next();
            } 
              total = total + "/n" + blockquote;     
            }
                return total; 
    } //blockquote

public static void justify(){
    String line = " ";
    String word = inp.next();
    
        while (word.equals("</justify>")){
            word = " " + word;
            word += word + " ";
            word = inp.next();
            
        }

} //justify




} //class​

