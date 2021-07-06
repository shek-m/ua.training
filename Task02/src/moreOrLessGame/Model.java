package moreOrLessGame;

import java.util.ArrayList;
import java.util.List;

public class Model {
    private final List<String> statisticStorage = new ArrayList<>();
    private int pickedNumber;
    private int lowerBound = 0;
    private int higherBound = 100;
    private boolean gameFinished;

    //Business logic
    public int pickRandomNumber() {
        return (int) (Math.random()*101);
    }

    public int compareUsersNumber(int numb) {
        return Integer.compare(numb, pickedNumber);
    }

    public boolean isMatchingTheRange(int numb) {
        return (numb >= lowerBound && numb <= higherBound);
    }

    public void addStatistics(int numb) {
        statisticStorage.add("Attempt №" + (statisticStorage.size() + 1) + ": " + numb
                + ". Range at the moment: [" + lowerBound + "; " + higherBound + "]");
    }

    //Getters and setters
    public int getLowerBound() {
        return lowerBound;
    }

    public void setLowerBound(int lowerBound) {
        this.lowerBound = lowerBound;
    }

    public int getHigherBound() {
        return higherBound;
    }

    public void setHigherBound(int higherBound) {
        this.higherBound = higherBound;
    }

    public int getPickedNumber() {
        return pickedNumber;
    }

    public void setPickedNumber(int pickedNumber) {
        this.pickedNumber = pickedNumber;
    }

    public List<String> getStatisticStorage() {
        return statisticStorage;
    }

    public boolean isGameFinished() {
        return gameFinished;
    }

    public void setGameFinished(boolean gameFinished) {
        this.gameFinished = gameFinished;
    }
}