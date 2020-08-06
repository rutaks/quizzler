package rw.rutaks.quizzler.util;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Random;

public class PlaceholderGenerator {
    private static final List<String> LOADING_PLACEHOLDERS = new ArrayList<>(Arrays.asList("Reticulating splines...", "Swapping time and space...", "Tokenizing real life...", "Bending da knee...", "Spinning the wheel of fortune...", "Computing chance of success", "Calling the almighty Gods for a question"));
    private static final List<String> BUTTON_PLACEHOLDERS = new ArrayList<>(Arrays.asList("How did you get here?", "Save water and shower together", "Life is Short – Wait Fast!!!!", "I’ve got problem for your solution...", "Well, this is embarrassing.", "Aw, snap! Here we go again", "You must be bored to use this app", "Don't touch me", "You can't touch this bruv", "I'm John Cena"));

    public static String getLoadingMessage() {
        Random rand = new Random();
        int upperBound = LOADING_PLACEHOLDERS.size() - 1;
        return LOADING_PLACEHOLDERS.get(rand.nextInt(upperBound));
    }

    public static String getBtnDisabledMessage() {
        Random rand = new Random();
        int upperBound = BUTTON_PLACEHOLDERS.size() - 1;
        return BUTTON_PLACEHOLDERS.get(rand.nextInt(upperBound));
    }
}
