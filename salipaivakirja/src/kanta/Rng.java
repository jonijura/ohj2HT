package kanta;

/**
 * random number generator
 * @author Joona1
 * @version 1.3.2020
 *
 */
public class Rng {


    /**
     * arvotaan luku halutulata valilta
     * @param ala alaraja
     * @param yla ylaraja
     * @return satunnainen luku
     */
    public static int rand(int ala, int yla) {
        double n = (yla - ala) * Math.random() + ala;
        return (int) Math.round(n);
    }


}
