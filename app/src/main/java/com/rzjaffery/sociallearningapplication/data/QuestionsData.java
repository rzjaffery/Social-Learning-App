// QuestionsData.java
package com.rzjaffery.sociallearningapplication.data;

import com.rzjaffery.sociallearningapplication.model.Question;
import java.util.*;

public class QuestionsData {
    public static Map<String, List<Question>> getQuestions() {
        Map<String, List<Question>> data = new HashMap<>();

        // General Category
        List<Question> general = new ArrayList<>();
        general.add(new Question("What is the capital of France?",
                Arrays.asList("Paris", "London", "Rome", "Berlin"), 0));
        general.add(new Question("Which planet is known as the Red Planet?",
                Arrays.asList("Earth", "Mars", "Venus", "Jupiter"), 1));
        general.add(new Question("What is 2 + 2?",
                Arrays.asList("3", "4", "5", "6"), 1));
        general.add(new Question("Which is the largest mammal?",
                Arrays.asList("Elephant", "Blue Whale", "Shark", "Giraffe"), 1));
        general.add(new Question("Who wrote 'Hamlet'?",
                Arrays.asList("Shakespeare", "Dickens", "Tolstoy", "Homer"), 0));
        data.put("General", general);

        // Science Category
        List<Question> science = new ArrayList<>();
        science.add(new Question("What gas do plants produce?",
                Arrays.asList("Oxygen", "Carbon Dioxide", "Nitrogen", "Hydrogen"), 0));
        science.add(new Question("What is H2O?",
                Arrays.asList("Hydrogen Peroxide", "Water", "Salt", "Acid"), 1));
        science.add(new Question("Which organ pumps blood?",
                Arrays.asList("Liver", "Lungs", "Heart", "Brain"), 2));
        science.add(new Question("What force keeps us on the ground?",
                Arrays.asList("Magnetism", "Gravity", "Friction", "Inertia"), 1));
        science.add(new Question("What part of cell contains DNA?",
                Arrays.asList("Nucleus", "Cytoplasm", "Ribosome", "Mitochondria"), 0));
        data.put("Science", science);

        // Math Category
        List<Question> math = new ArrayList<>();
        math.add(new Question("5 × 6 = ?", Arrays.asList("11", "25", "30", "36"), 2));
        math.add(new Question("Square root of 49?", Arrays.asList("6", "7", "8", "9"), 1));
        math.add(new Question("100 ÷ 25 = ?", Arrays.asList("2", "3", "4", "5"), 2));
        math.add(new Question("12 – 5 = ?", Arrays.asList("5", "6", "7", "8"), 2));
        math.add(new Question("2³ = ?", Arrays.asList("4", "6", "8", "9"), 2));
        data.put("Math", math);

        return data;
    }
}
