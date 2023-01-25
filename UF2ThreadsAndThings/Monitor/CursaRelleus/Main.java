package UF2ThreadsAndThings.Monitor.CursaRelleus;

//Cursa de relleus
// TODO > He de crear tres equips que competeixin en una cursa de relleus i treure la classificació amb el temps que han trigat.
//  Amb les següents característiques i requisits:
//  Els Atletes han d'intentar fer-se amb el Testimoni del seu equip i córrer el tram que els hi pertoca. Per fer-ho tardaran un temps que simularem.
//  El Testimoni és l'element que cada equip té i que cada atleta ha d'agafar per poder fer la seva cursa.

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

public class Main {
    final static int numEquips = 3;
    public static void main(String[] args) {
        List<Equip> equipList = new ArrayList<>();
        for (int i = 1; i <= numEquips; i++) {
            Testimoni testimoni = new Testimoni();
            Atleta a1 = new Atleta("A1-"+i,testimoni);
            Atleta a2 = new Atleta("A2-"+i,testimoni);
            Atleta a3 = new Atleta("A3-"+i,testimoni);
            Atleta a4 = new Atleta("A4-"+i,testimoni);
            Equip equip = new Equip("equip"+(i),a1,a2,a3,a4);
            equipList.add(equip);
        }

        equipList.forEach(Thread::start);
        equipList.forEach(equip -> {
            try {
                equip.join();
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        });

        equipList.sort(new Comparator<Equip>() {
            @Override
            public int compare(Equip o1, Equip o2) {
                if (o1.getTemps_final() > o2.getTemps_final()) return 1;
                else return 0;
            }
        });

        equipList.forEach(e -> System.out.println(e.name +": "+e.getTemps_final()));
    }
}
