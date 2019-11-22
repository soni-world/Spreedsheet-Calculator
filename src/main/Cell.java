package main;

/**
 * @author: sonikumari.b
 */
public class Cell {
   Double value;
   boolean IsCurrentEvaluation;
   boolean IsEvaluated;
   String cellContent;

   public Cell(String cellContent){

      this.cellContent = cellContent;
      this.IsCurrentEvaluation = false;
      this.IsEvaluated = false;
   }

   public Cell() {

   }

   public Double getValue() {

      return value;
   }

   public void setValue(Double value) {

      this.value = value;
   }

   public boolean isCurrentEvaluation() {

      return IsCurrentEvaluation;
   }

   public void setCurrentEvaluation(boolean currentEvaluation) {

      IsCurrentEvaluation = currentEvaluation;
   }

   public boolean isEvaluated() {

      return IsEvaluated;
   }

   public void setEvaluated(boolean evaluated) {

      IsEvaluated = evaluated;
   }

   public String getCellContent() {

      return cellContent;
   }

   public void setCellContent(String cellContent) {

      this.cellContent = cellContent;
   }

   @Override
   public String toString() {

      return "Cell{" + "value=" + value + ", IsCurrentEvaluation=" + IsCurrentEvaluation + ", IsEvaluated="
            + IsEvaluated + ", cellContent='" + cellContent + '\'' + '}';
   }
}
