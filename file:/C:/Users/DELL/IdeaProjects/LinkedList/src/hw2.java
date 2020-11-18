
import java.io.*;

public class hw2 {


    public static int checkChild(String parent, String child) {
        for(int i = 0 ; i < parent.length() ; i++) {
            if(parent.charAt(i) == '(') {
                i++;
                int count = 1;
                while(count > 0) {
                    if(parent.charAt(i) == '(')
                        count++;
                    if(parent.charAt(i) == ')')
                        count--;
                    i++;
                }
                if(i >= parent.length())
                    break;
            }
            if(child.length() == 1) {
                if(parent.charAt(i) == child.charAt(0))
                    return i;
            }
            else if (i < parent.length()-1){
                if(parent.charAt(i) == child.charAt(0) && parent.charAt(i+1) == child.charAt(1))
                    return i;
            }
        }
        return -1;
    }

    public static String newHeap(String heap, int ind) {
        int i = ind;
        while(heap.charAt(i) != '(')
            i++;
        int j = i+1;
        int count = 1;
        while(count != 0) {
            if(heap.charAt(j) == '(')
                count++;
            if(heap.charAt(j) == ')')
                count--;
            j++;
        }
        return heap.substring(i+1,j-1);
    }

    public static boolean checkPath(String heap, String path) {
        int aramaSayisi = (path.length())/2+1;
        for(int i = 0 ; i < aramaSayisi ; i++) {
            if(i == 0) {
                if(checkChild(heap, path.substring(0,1)) == -1)
                    return false;
                else {
                    int ind = checkChild(heap, path.substring(0,1));
                    heap = newHeap(heap, ind);
                }

            }
            else if(i < aramaSayisi-1) {
                if(checkChild(heap, path.substring(i,i+2)) == -1)
                    return false;
                else {
                    int ind = checkChild(heap, path.substring(i,i+2));
                    heap = newHeap(heap, ind);
                }
            }
            else {
                if(aramaSayisi == 2) {
                    if(checkChild(heap, path.substring(i)) == -1)
                        return false;
                }
                else {
                    if(checkChild(heap, path.substring(i+1)) == -1)
                        return false;
                }

            }
        }
        return true;
    }


    public static void main(String[] args) {
        String treeFile = args[0];
        String pathFile = args[1];

        try{
            FileInputStream fstream = new FileInputStream(pathFile);

            DataInputStream in = new DataInputStream(fstream);
            BufferedReader br = new BufferedReader(new InputStreamReader(in));
            String strLine;
            while ((strLine = br.readLine()) != null)   {
                int count = 0;
                int count2 = 0;
                try{
                    FileInputStream fstream2 = new FileInputStream(treeFile);
                    DataInputStream in2 = new DataInputStream(fstream2);
                    BufferedReader br2 = new BufferedReader(new InputStreamReader(in2));
                    String strLine2;
                    while ((strLine2 = br2.readLine()) != null)   {
                        count++;
                        if(checkPath(strLine2, strLine))
                            count2++;
                    }
                    in2.close();
                }catch (Exception e){
                    System.err.println("Error: " + e.getMessage());
                }
                System.out.println((double)count2/count);

            }
            in.close();
        }catch (Exception e){
            System.err.println("Error: " + e.getMessage());
        }

    }
    }


