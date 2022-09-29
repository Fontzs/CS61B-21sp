package gh2;
import edu.princeton.cs.algs4.StdAudio;
import edu.princeton.cs.algs4.StdDraw;

public class GuitarHero {
    public static final double CONCERT_A = 440.0;


    public static void main(String[] args) {

        final String keyboard = "q2we4r5ty7u8i9op-[=zxdcfvgbnjmk,.;/' ";
        GuitarString[] gs = new GuitarString[keyboard.length()];

        for (int i = 0; i < keyboard.length(); i++) {
            double r = CONCERT_A * Math.pow(2, (i - 24) / 12.0);
            gs[i] = new GuitarString(r);
        }

        while (true) {

            /* check if the user has typed a key; if so, process it */
            if (StdDraw.hasNextKeyTyped()) {
                char key = StdDraw.nextKeyTyped();
                int k = keyboard.indexOf(key);
                if (k > 0) {
                    gs[k].pluck();
                }
            }

            double sample = 0.0;

            for (GuitarString s : gs) {
                sample += s.sample();
            }

            StdAudio.play(sample);

            for (GuitarString s : gs) {
                s.tic();
            }


        }


    }
}
