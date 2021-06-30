package homework03.task01.moreOrLessGameUpdated;

import java.util.ArrayList;
import java.util.List;

public class Model {
    private final List<String> statisticStorage = new ArrayList<>();
    private int pickedNumber;
    private int lowerBound;
    private int higherBound;

    //Business logic
    public int pickRandomNumber() {
       return  (int) Math.ceil(Math.random()
               * (higherBound - lowerBound - 1) + lowerBound);
    }

    public boolean checkValue(int numb) {
        addStatistics(numb);
        if (numb == pickedNumber) {
                 return true;
        } else if (numb > pickedNumber) {
            higherBound = numb;
            return false;
        } else {
            lowerBound = numb;
            return false;
        }
    }

    public boolean isMatchingTheRange(int numb) {
        return (numb > lowerBound && numb < higherBound);
    }

    public void setPrimaryBarrier(int lowerBound, int higherBound) {
        this.lowerBound = lowerBound;
        this.higherBound = higherBound;
    }

    public void addStatistics(int numb) {
        statisticStorage.add("Attempt №" + (statisticStorage.size() + 1) + ": " + numb
                + ". Range at the moment: [" + lowerBound + "; " + higherBound + "]");
    }

    //Getters and setters
    public int getLowerBound() {
        return lowerBound;
    }

    public int getHigherBound() {
        return higherBound;
    }

    public void setPickedNumber(int pickedNumber) {
        this.pickedNumber = pickedNumber;
    }

    public List<String> getStatisticStorage() {
        return statisticStorage;
    }

    public int getPickedNumber() {
        return pickedNumber;
    }
}