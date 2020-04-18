import org.json.JSONObject;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Map;
import java.net.URL;

//Represent a link module
class Connect{

    private boolean isInSt = false; //Is Input System.in
    private BufferedReader in; //Input
    private BufferedWriter out; //Output

    //Constructor to working with String[] args
    //File objects or nothing are valid ( empty params are considered as STDIN and STDOUT)
    public Connect(String[] args) {
        try {
            switch (args.length) {
                case 0 -> {
                    stdIn(null);
                    stdOut(null);
                }
                case 1 -> {
                    if (args[0].equalsIgnoreCase("STDIN"))
                        stdIn(null);
                    else
                        in = Files.newBufferedReader(Paths.get(args[0]));
                    stdOut(null);
                }
                case 2 -> {
                    if (args[0].equalsIgnoreCase("STDIN"))
                        stdIn(null);
                    else
                        in = Files.newBufferedReader(Paths.get(args[0]));
                    if (args[1].equalsIgnoreCase("STDOUT"))
                        stdOut(null);
                    else
                        out = new BufferedWriter(new FileWriter(new File(args[1])));
                }
                default -> System.out.println("Invalid number of parameters!");
            }
        }
        catch (IOException ex){
            ex.printStackTrace();
        }
    }

    //Constructor for explicit params
    //Valid input param is String/File/InputStream/URL objects
    //Valid output param is  String/File/OutputStream objects
    //If there is an invalid param STDIN or STDOUT will be used
    public Connect(Object input, Object output){
        try{
            if (input.getClass().equals(String.class))
                in = Files.newBufferedReader(Paths.get((String)input));
            else if (input.getClass().equals(File.class))
                in = Files.newBufferedReader(((File) input).toPath());
            else if (InputStream.class.isAssignableFrom(input.getClass())){
                in = new BufferedReader(new InputStreamReader((InputStream) input));
                if (input.equals(System.in))
                    isInSt = true;
            }
            else if (input.getClass().equals(URL.class))
                in = new BufferedReader(new InputStreamReader(((URL) input).openStream()));
            else
                stdIn("Invalid input param,");

            if (output.getClass().equals(String.class))
                out = Files.newBufferedWriter(Paths.get((String)output));
            else if (output.getClass().equals(File.class))
                out = Files.newBufferedWriter(((File) output).toPath());
            else if (OutputStream.class.isAssignableFrom(output.getClass()))
                out = new BufferedWriter(new OutputStreamWriter((OutputStream) output));
            else
                stdOut("Invalid output param, ");
        }
        catch (IOException ex){
            ex.printStackTrace();
            stdIn("");
            stdOut("");
        }
    }

    public Connect(){
        stdIn(null);
        stdOut( null);
    }

    //Set STDIN as input in case of getting an invalid input param
    private void stdIn(String error){
        if (error != null)
            System.out.println( error + " !!! STDIN will be used !!!");
        in = new BufferedReader(new InputStreamReader(System.in));
        isInSt = true;
    }
    //Set STDOUT as output in case of getting an invalid output param
    private void stdOut(String error){
        if (error != null)
            System.out.println(error + "!!! STDOUT will be used !!!");
        out = new BufferedWriter(new OutputStreamWriter(System.out));
    }

    //Read a line from given input
    public String read(){
        try {
            if (isInSt){
                return in.readLine();
            }
            else if (in.ready()) {
                String str = in.readLine();
                write("] " + str);
                return str;
            }
            else {
                write("!] All input commands have red. Last command must be \"exit\". Forced termination.");
                return "exit";
            }
        }
        catch(IOException ex){
            ex.printStackTrace();
            write("!] Forced termination");
            return "exit";
        }
    }

    //Write a line in given output
    public void write(String string){
        try {
            if (string.charAt(0) == ']')
                out.write("[Client");
            else if (string.charAt(0) == '!')
                out.write("[Warning");
            else
                out.write("[Automate] ");
            out.write(string);
            out.newLine();
            out.flush();
        }
        catch(IOException ex){
            ex.printStackTrace();
        }
    }
}

//Represent an auxiliary module. Give supporting method.
class Stuff{
    private static JSONObject creatMenu(){
        JSONObject menu = new JSONObject();
        menu.put("coffee", 100);
        menu.put("tea", 50);
        menu.put("juice", 150);

        return menu;
    }

    public static Map<String, Object> getMenu(){
        return creatMenu().toMap();
    }
}