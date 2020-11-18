package entity;

import utilities.OrderedSetSudoku;

import java.util.Arrays;
import java.util.Collections;
import java.util.Objects;
import java.util.Set;

public class Square implements Comparable<Square> {

    protected Position position;
    protected int[] potentialNumbers;

    public Square(final Position position, final int number) {
        this.position = position;
        this.setChosenNumber(number);
    }

    public Square(final Position position) {
        this();
        this.position = position;
    }

    protected Square() {
        this.potentialNumbers = new int[OrderedSetSudoku.HEIGHT_SIDE * OrderedSetSudoku.WIDTH_SIDE];
        for (int i = 1; i < (potentialNumbers.length) + 1; i++) {
            this.potentialNumbers[i - 1] = i;
        }
    }

    /**
     * Delete a number from potential numbers without repercussion
     *
     * @param potentialNumber number to delete from potential
     */
    public boolean deletePotentialNumbers(final int potentialNumber) {
        if (isValidNumber()) {
            return false;
        }
        if (containsPotentialNumber(potentialNumber) && this.potentialNumbers.length > 1) {
            removeEffectivePotentialNumberInPotentialNumberArray(potentialNumber);
            if (this.potentialNumbers.length == 1) {
                this.setChosenNumber(this.potentialNumbers[0]);
                return false;
            }
            return true;
        }
        return false;
    }

    protected void removeEffectivePotentialNumberInPotentialNumberArray(final int potentialNumber) {
        this.potentialNumbers = Arrays.stream(this.potentialNumbers)
                .filter(x -> x != potentialNumber).toArray();
    }

    public Set<Square> treatmentForSudoku() {
        if (!this.isValidNumber()) {
            final int potentialNumber = this.potentialNumbers[0];
            final Set<Square> newAffectedSquares = OrderedSetSudoku.deletePotentialNumberFromZone(this.position, potentialNumber);
            newAffectedSquares.addAll(OrderedSetSudoku.deletePotentialNumberFromColumn(this.position, potentialNumber));
            newAffectedSquares.addAll(OrderedSetSudoku.deletePotentialNumberFromRow(this.position, potentialNumber));
            return newAffectedSquares;
        }
        return Collections.emptySet();
    }

    public int getChosenNumber() {
        if (!this.isValidNumber()) {
            return 0;
        }
        return this.potentialNumbers[0];
    }

    void setChosenNumber(final int number) {
        this.potentialNumbers = new int[1];
        this.potentialNumbers[0] = number;
        OrderedSetSudoku.deletePotentialNumberFromZone(this.position, number);
        OrderedSetSudoku.deletePotentialNumberFromColumn(this.position, number);
        OrderedSetSudoku.deletePotentialNumberFromRow(this.position, number);
    }

    public boolean isValidNumber() {
        return this.potentialNumbers.length == 1 && !(0 == this.potentialNumbers[0]);
    }

    protected boolean containsPotentialNumber(final int potentialNumber) {
        return Arrays.stream(this.potentialNumbers)
                .anyMatch(x -> x == potentialNumber);
    }


    @Override
    public int compareTo(final Square compared) {
        if (this.equals(compared)) {
            return 0;
        }
        if (this.isValidNumber() && compared.isValidNumber() || this.potentialNumbers.length == compared.potentialNumbers.length) {
            return this.position.compareTo(compared.position);
        }
        if (this.isValidNumber()) {
            return 1;
        }
        if (compared.isValidNumber()) {
            return -1;
        }
        if (this.potentialNumbers.length == compared.potentialNumbers.length) {
            return this.position.compareTo(compared.position);
        }
        return this.potentialNumbers.length < compared.potentialNumbers.length ? -1 : 1;
    }

    public Position getPosition() {
        return position;
    }

    public int[] getPotentialNumbers() {
        return potentialNumbers;
    }

    @Override
    public boolean equals(final Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        final Square square = (Square) o;
        return getPosition().equals(square.getPosition());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getPosition());
    }

    @Override
    public String toString() {
        return "[" + this.getPosition().rowPosition + ";" + this.getPosition().columnPosition + "] " +
                "| Valid : " + this.getChosenNumber() + " | Potentials : " + getPotentialNumbers().length;
    }
}
