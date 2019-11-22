package main;

import java.util.Scanner;

/**
 * @author: sonikumari.b
 */
public class Spreadsheet {
   public static Cell[][] sheetCells;
   public static int sizeX;
   public static int sizeY;

   public static void main(String[] args) {

      Spreadsheet spreadSheetCell = new Spreadsheet();
      try {
         populateCellValues(spreadSheetCell);
         for (int i = 0; i < sizeX; i++) {
            for (int j = 0; j < sizeY; j++) {
               Cell toEvaluateCell = new Cell();
               toEvaluateCell = sheetCells[i][j];
               CellEvaluator cellEvaluator = new CellEvaluator();
               cellEvaluator.evaluateCell(toEvaluateCell, null, sheetCells);
            }
         }

         for (int i = 0; i < sizeX; i++) {
            for (int j = 0; j < sizeY; j++) {
               if (i == sizeX - 1 && j == sizeY - 1)
                  System.out.printf("%.5f", sheetCells[i][j].value);
               else
                  System.out.printf("%.5f%n", sheetCells[i][j].value);
            }
         }

      } catch (Exception e) {
         System.out.println("Error occurrend in main:" + e.getMessage());
      }
   }

   private static void populateCellValues(Spreadsheet spreadSheet) {
      // TODO Auto-generated method stub
      try
      {
         Scanner sc = new Scanner(System.in);

         spreadSheet.sheetCells= null;

         String[] fields;
         int[] size = new int[2];
         if (sc.hasNextLine()) {
            fields = sc.nextLine().split(" ");

            if (fields.length != 2) {
               throw new IllegalArgumentException("Invalid Size");
            } else {
               for (int i = 0; i < fields.length; i++)
                  size[i] = Integer.parseInt(fields[i]);
               spreadSheet.sheetCells = new Cell[size[1]][size[0]];
               spreadSheet.sizeY = size[0];
               spreadSheet.sizeX = size[1];
            }
         }

         int rowIndex = 0,colIndex = 0,cellCount=0;
         while (sc.hasNextLine()) {
            String line = sc.nextLine();
            if (line.isEmpty())
               break;
            spreadSheet.sheetCells[rowIndex][colIndex] = new Cell(line);
            cellCount++;
            colIndex++;
            if(colIndex==spreadSheet.sizeY)
            {
               colIndex = 0;
               rowIndex++;
            }
         }

         if (cellCount != size[0]*size[1])
            throw new IllegalArgumentException("No of cells doesn't match the given size");
      }
      catch(Exception e){
         System.out.println("Error occurred in while reading values");
         System.exit(1);
      }
   }
}
