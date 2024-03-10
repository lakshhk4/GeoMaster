package com.mycompany.mavenproject1;

import java.util.ArrayList;
import java.util.Random;

public class SequenceInterpretor {

    // interprets gameSequence
    public static ArrayList interpretGameSequence(String sequence) {
        // interprets gameSequence

        // calls the dividor function, which splits the given screen by the breaking character. 
        // In this case, it is "!"
        ArrayList questions = dividor(sequence, "!");
        // at this point, the questions are divided into seperate items in a list.
        // ex. [ (Q1)--> [us.png,United States, India, Indones ia, Philippines], (Q2)--> [pk.png,Pakistan,Australia,Maldives,Nepal] ... ]

        // this array divides each question into its options
        ArrayList optionWise = new ArrayList();
        for (Object question : questions) {
            optionWise.add(dividor((String) question, ","));
        }
        // at this point, each question is divided into its options
        // [ (Q1) --> [ [flagFileName][option1] [option2] [option3] [option4] ], (Q2) --> [ [flagFileName] [option1] [option2] [option3] [option4] ] ]

        return optionWise;

    }
    
    
        // places the options provided in a random order, while also noting the position of the correct option
   
    public static String[] optionsPlacer(ArrayList question) {
        Random rand = new Random();
        // choosen a random button which will be the correct answer.
        int correctButton = rand.nextInt(4) + 1;

        // the array that this method will return. [positionOfCorrectAnswer,countryOnButton1, countryOnButton2, countryOnButton3,countryOnButton4]
        String[] returned = new String[5];

        // stores which button is the correctButton
        returned[0] = Integer.toString(correctButton);
        // sets the correct country to the correctButton
        returned[correctButton] = (String) question.get(1);

       
        ArrayList<Integer> nullIndices = new ArrayList();
        
        for (int i = 0; i < 5; i++) { 
            if (returned[i] == null) { 
                nullIndices.add(i);
            }
        }
        
        int count = 2;
        for (int index : nullIndices) { 
            String countryName = (String) question.get(count);
            returned[index] = countryName;
            count++;
        }
        
           
            /* 
        
         // this process allows to assign the other options a button as well.
        int[] indexesChosen = new int[4];
        indexesChosen[3] = correctButton;
        indexesChosen[0] = 100;
        indexesChosen[1] = 100;
        indexesChosen[2] = 100;

        
        for (int i = 2; i < 5; i++) {  // for each of the remaining items in the question input/
            
            boolean notUniqueIndex = true;
            
            while (notUniqueIndex) {
            int workingIndex = rand.nextInt(4) + 1;
            
            notUniqueIndex = false;
            for (int alreadyTakenIndex : indexesChosen) {
            if (workingIndex == alreadyTakenIndex) {
            notUniqueIndex = true;
            }
            }
            
            indexesChosen[i - 2] = workingIndex;
            // when while loop is exited, the workingIndex will be a unique one
            }
            // at this point, the false answer is assigned to an empty index
            // and is then put into the returned list.
            returned[indexesChosen[i - 2]] = (String) question.get(i);
            
            }*/

        return returned;
    }
        // divides given string by given character 
    public static ArrayList dividor(String sequence, String dividingChar) {
        // the following code will work correctly if the sequence is in the right format.

        // array will store the indexes where the "!" is located.
        ArrayList<Integer> breakPoints = new ArrayList<Integer>();

        for (int i = 0; i < sequence.length(); i++) {
            String c = "";
            c += sequence.charAt(i);

            if (dividingChar.equals(c)) {
                breakPoints.add(i);
            }

        }

        /*System.out.println("----------- Breakpoints -----------");
        
        for (int breakPoint : breakPoints) {
        System.out.println(breakPoint);
        }
        
        System.out.println("----------- End -----------");
         */
        ArrayList<String> listedQuestions = new ArrayList<String>();

        // this will tell how many questions there are 
        int numberOfParts = breakPoints.size() - 1;

        // int leftBreakPoint = 0;
        for (int i = 0; i < breakPoints.size() - 1; i++) { // doesnt take last element. runs as many times as there are parts/questions.

            int leftBreakPoint = breakPoints.get(i);
            int rightBreakPoint = breakPoints.get(i + 1);

            String question = "";

            // goes thru the stuff in between of the two breakpoints
            for (int z = leftBreakPoint + 1; z < rightBreakPoint; z++) {

                question += sequence.charAt(z);
            }

            listedQuestions.add(question);

        }

        return listedQuestions;

    }
}
