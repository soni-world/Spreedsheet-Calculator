package main;

import main.Cell;

import java.util.LinkedHashSet;
import java.util.Set;
import java.util.Stack;

/**
 * @author: sonikumari.b
 */
public class CellEvaluator {

   public Double evaluateCell(Cell sheetCell, Set<Cell> currentEvaluationStack, Cell[][] sheetCellsGlobal) {
      if (currentEvaluationStack == null) {
         currentEvaluationStack = new LinkedHashSet<Cell>();
      }

      if (sheetCell.isEvaluated()) {
         //move to next cell
      } else if (!sheetCell.isEvaluated() && !currentEvaluationStack.contains(sheetCell)) {
         currentEvaluationStack.add(sheetCell);

         String[] fields = sheetCell.getCellContent().split(" ");
         Stack<Double> operands = new Stack<Double>();

         for (int i = 0; i < fields.length; i++) {
            String s = fields[i];

            if (isNumber(s))
               operands.push(Double.parseDouble(s));
               else if(s.equals("+") || s.equals("*") || s.equals("/") || s.equals("-") || s.equals("--") || s.equals("++")){
                  rpnEvaluator(s, operands);
               }
            else {
               Cell anotherCell = getCell(s, sheetCellsGlobal);
               operands.push(evaluateCell(anotherCell, currentEvaluationStack, sheetCellsGlobal));
            }

         }

         sheetCell.setValue(operands.pop());
         sheetCell.setEvaluated(true);

      } else {
         System.out.println("Cycle Occurred while evaluating Cell Value " + sheetCell.getCellContent());
         System.out.println("Loop trace:  ");
         for (Cell loopCell : currentEvaluationStack) {
            System.out.println(" cell with content : " + loopCell.getCellContent() + " ->");
         }
         System.exit(1);
      }

      return sheetCell.getValue();
   }

   private static boolean isNumber(String s) {

      try {
         Double.parseDouble(s);
         return true;
      } catch (NumberFormatException e) {
         return false;
      }
   }

   private Cell getCell(String s, Cell[][] sheetCellsGlobal) {

      try {
         int x = (int) s.charAt(0) % 65;
         int y = Integer.parseInt(s.substring(1, s.length())) - 1;
         return sheetCellsGlobal[x][y];
      } catch (NumberFormatException e) {
         System.out.println("Wrong Data format while evaluating Cell" + s);
         System.exit(1);
      }
      return null;

   }

   public Stack<Double> rpnEvaluator(String expr, Stack<Double> RPNEvaluation) throws ArithmeticException {

      switch (expr) {
      case "+":
         RPNEvaluation.push(RPNEvaluation.pop() + RPNEvaluation.pop());
         break;
      case "-":
         RPNEvaluation.push(-RPNEvaluation.pop() + RPNEvaluation.pop());
         break;
      case "*":
         RPNEvaluation.push(RPNEvaluation.pop() * RPNEvaluation.pop());
         break;
      case "/":
         double divisor = RPNEvaluation.pop();
         RPNEvaluation.push(RPNEvaluation.pop() / divisor);
         break;
      case "++":
         double incr = RPNEvaluation.pop();
         RPNEvaluation.push(++incr);
         break;
      case "--":
         double decr = RPNEvaluation.pop();
         RPNEvaluation.push(--decr);
         break;
      default:
         System.out.print("Push\t\t");
         RPNEvaluation.push(Double.parseDouble(expr));
         break;
      }
      return RPNEvaluation;
   }
}
