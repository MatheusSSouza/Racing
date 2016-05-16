import java.util.Collections;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;

public class MainArc {

    int ruas, esquinas, sol, ctotal;
    ArrayList<Arc> arv;
    int[] v;
    static long t;
    String[] rd;

    public static void main(String[] args){
        new MainArc().run();
        System.out.println(System.nanoTime()-t);
    }
    
    public void run() {
        Scanner sc = new Scanner(System.in);
        int cases = sc.nextInt();
        sc.nextLine();
        t = System.nanoTime();
        for (int i = 0; i < cases; i++) {
            ctotal = 0;
            rd = sc.nextLine().split(" ");
            esquinas = Integer.parseInt(rd[0]);
            ruas = Integer.parseInt(rd[1]);
            arv = new ArrayList<>();
//            int x, y, c;
            int c;
            for (int j = 0; j < ruas; j++) {
//                x = sc.nextInt() - 1;
//                y = sc.nextInt() - 1;
//                c = sc.nextInt();
//                arv.add(new Arc(x, y, c));
                rd = sc.nextLine().split(" ");
                arv.add(new Arc(Integer.parseInt(rd[0])-1, Integer.parseInt(rd[1])-1, Integer.parseInt(rd[2])));
                ctotal += Integer.parseInt(rd[2]);
            }
            //print(arv);
            resolve();
        }
    }

    public void resolve() {
        sol = 0;
        //ArrayList<Arc> arvMin = new ArrayList<>();
        ArrayList<Arc> franja = new ArrayList<>();
        Arc novo = null;
        v = new int[esquinas];
        
        for (int i = 0; i < arv.size(); i++) {
            if(arv.get(i).i == 0 || arv.get(i).f == 0){
                franja.add(arv.get(i));
                arv.remove(arv.get(i));
            }
        }
        v[0] = 1;
        while(true){
            //Collections.sort(franja);
            for (int i = 1; i <= franja.size(); i++) {
                //novo = franja.get(franja.size()-i);
                novo = getMax(franja);
                if(v[novo.i] == 1 && v[novo.f] == 1){
                    franja.remove(novo);
                    novo = null;
                }else{// if(v[novo.i] == 1 || v[novo.f] == 1){
                    break;
                }
            }
//            print(franja);
//            System.out.println(Arrays.toString(v)+"\n\n///////////////////");
            
//            if(franja.isEmpty()) break;
            if(novo == null) break;
            
            //arvMin.add(novo);
            franja.remove(novo);
            v[novo.f] = 1;
            v[novo.i] = 1;
            sol += novo.c;
            for (int i = 0; i < arv.size(); i++) {
                if(v[arv.get(i).i] == 1 && v[arv.get(i).f] == 1){
                    arv.remove(arv.get(i));
                }else if(arv.get(i).i == novo.f || arv.get(i).f == novo.f || arv.get(i).i == novo.i || arv.get(i).f == novo.i){
                    franja.add(arv.get(i));
                    arv.remove(arv.get(i));
                }
            }
//            print(arvMin);
        }
//        print(arvMin);
        
        System.out.println(ctotal - sol);
    }
    
    public Arc getMax(ArrayList<Arc> list){
        Arc max = list.get(0);
        Arc aux;
        for (int i = 0; i < list.size(); i++) {
            aux = list.get(i);
            if(aux.compareTo(max) == 1) max = aux;
        }
        return max;
    }

    public void print(ArrayList<Arc> gra) {
        for (Arc arco : gra) {
            System.out.println(arco);
        }
        System.out.println("");
        System.out.println("////////////////////////////////////");
    }
    
    class Arc implements Comparable<Arc>{
        int i, f, c;
        public Arc(int ini, int fim, int custo){
            i = ini;
            f = fim;
            c = custo;
        }

        public int compareTo(Arc obj) {
            if(c == obj.c){
                return 0;
            }else if(c > obj.c){
                return 1;
            }else{
                return -1;
            }
        }
        
        public String toString(){
            return ((i+1) + " - " + (f+1) + " (" + c + ")");
        }
    }
}
