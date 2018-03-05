import java.util.ArrayList;
import java.util.List;

public class SchwimmbadMain {
  
  public static void main(String[] args) {
    List<Integer> personen = new ArrayList<>();
    personen.add(10);
    personen.add(2);
    personen.add(23);
    personen.add(11); 
    personen.add(2);
    personen.add(4);
    personen.add(1);
    try {
      for (String string : eintritt(false, false, 0, personen)) {
        System.out.println(string);
      }
    } catch (Exception e) {
      e.printStackTrace();
    }
  }
  
  
  

  public static List<String> eintritt(boolean wochenende, boolean ferien, int anzGutscheine, List<Integer> personen) throws Exception {
    List<String> karten = new ArrayList<String>();
    float preis = 0;
    for (int person : personen) {
      if (person > 16) {
        if (wochenende) {
          preis += 3.50f;
          karten.add("einzel ERW");
        } else if (!wochenende) {
          preis += 3.50f * 0.8f;
          karten.add("einzel ERW -20%");
        }
      } else if (person < 16 && person > 4) {
        if (wochenende) {
          preis += 2.50f;
          karten.add("einzel KIN");
        }
        if (!wochenende) {
          preis += 2.50f * 0.8f;
          karten.add("einzel KIN -20%");
        }
      } else if (person <= 4) {
        check: {
          for (int personCheck : personen) {
            if (personCheck > 16)
              break check;
          }
          throw new Exception("Kinder unter 4 Jahren, ohne Aufsicht");
        }
      karten.add("einzel KLEINKIN");
      }
    }
    karten.add("PREIS="+preis);
    return karten;
  }
}
