package lab4;

public class PDATree {
    public static String GetTree(String test,char in,char out){
        String result = "TREE:";
        int level = 1;
        char previus = 0;

        for (char ch:  test.toCharArray()) {
            if (previus == ch){
                if (ch == in){
                    level++;
                }else if(ch == out){
                    level--;
                }
            }
            result += "\n";
            for (int i = 0;i<level;i++){
                result += "\t";
            }
            result+=ch;
            previus = ch;
        }
        return result;
    }
}
