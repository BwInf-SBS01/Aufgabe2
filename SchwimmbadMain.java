package de.schwimmbad.java;

import java.util.ArrayList;
import java.util.List;

public class SchwimmbadMain {
	private static final float ERWGP = 3.50f;
	private static final float JUGGP = 2.50f;
	private static final float GRUPGP = 11.0f;
	private static final float FAMGP = 8.00f;
	
	
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
	
	
	
	public static void eintritt2(boolean wochenende, boolean ferien, int anzGutscheine, List<Integer> personen) throws Exception {
		float erwKarte= ERWGP, jugKarte= JUGGP;
		if(!wochenende) {
			erwKarte *= 0.8f;
			jugKarte *= 0.8f;
		}
		int anzerw = 0, anzkin = 0, anzkleinkin = 0;
		for (Integer alter : personen) {
			if(alter<4) {
				anzkleinkin++;
			} else if(alter<16) {
				anzkin++;
			} else {
				anzerw++;
			}
		}
		if(anzkleinkin>0 && anzerw == 0) {
			throw new Exception("Kinder unter 4 Jahren, ohne Aufsicht!");
		}
		if(((anzerw*erwKarte + anzkin*jugKarte) > GRUPGP) && !wochenende) {
			
		}
		if((anzerw*erwKarte + anzkin * jugKarte) > FAMGP && anzerw <3 && anzkin < 4) {
			
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
