package com.geekbrains;


import java.util.Random;
import java.util.Scanner;

public class MainClass {
    private final static int SIZE = 3;
    private final static char DOT_EMPTY = '.';
    private final static char DOT_X = 'X';
    private final static char DOT_0 = '0';
    private final static char[][] MAP = new char[SIZE][SIZE];
    private final static Scanner SCANNER = new Scanner(System.in);
    private final static Random RANDOM = new Random();
    private final static int LINE = 3;
    public static int savedCoordX;
    public static int savedCoordY;

    public static void main(String[] args) {
        initMap();
        printMap();
        while (true){
            humanTurn();
            printMap();
            if(checkWin(DOT_X)){
                System.out.println("Победил человек");
                break;
            }
            if(isMapFull()){
                System.out.println("Ничья");
                break;
            }
            aiTurn();
            printMap();
            if(checkWin(DOT_0)){
                System.out.println("Победил ИИ");
                break;
            }
            if(isMapFull()){
                System.out.println("Ничья");
                break;
            }
        }
        System.out.println("Игра окончена");
        SCANNER.close();
    }

    private static boolean checkWin(char symbol){
        int countX = 0;
        int countY = 0;
        int countDiag = 0;
        int countAntiDiag = 0;
        for(int i = 0; i < SIZE; i++) { // проверка вертикальной линии
            if (MAP[savedCoordY][i] == symbol)
                countX++;
            else
                countX = 0;
        }
        for(int i = 0; i < SIZE; i++){ // проверка горизонтальной линии
            if(MAP[i][savedCoordX] == symbol)
                countY++;
            else
                countY = 0;
        }
        for (int i = 0; i < SIZE; i++){ // проверка диагонали
            if(MAP[i][i] == symbol)
                countDiag++;
            else
                countDiag = 0;
        }
        for (int i = 0; i < SIZE; i++){ // проверка анти-диагонали
            if(MAP[i][(SIZE - 1) - i] == symbol)
                countAntiDiag++;
            else
                countAntiDiag = 0;
        }

        if(countX == LINE || countY == LINE || countDiag == LINE || countAntiDiag == LINE)
            return true;
        return false;
    }

    private static boolean isMapFull(){
        for(int i = 0; i < SIZE; i++){
            for (int j = 0; j < SIZE; j++){
                if(MAP[i][j] == DOT_EMPTY){
                    return false;
                }
            }
        }
        return true;
    }

    private static void aiTurn(){
        int x;
        int y;

        do {
            x = RANDOM.nextInt(SIZE);
            y = RANDOM.nextInt(SIZE);
        }while (!isCellValid(x, y));
        System.out.println("ИИ сходил в точку" + (x + 1) + " " + (y + 1));
        MAP[y][x] = DOT_0;
        savedCoordX = x;
        savedCoordY = y;
    }

    private static void humanTurn(){
        int x;
        int y;

        do{
            System.out.println("Введите координаты в формате Х и У");
            x = SCANNER.nextInt() - 1;
            y = SCANNER.nextInt() - 1;
        }while (!isCellValid(x, y));
        MAP[y][x] = DOT_X;
        savedCoordX = x;
        savedCoordY = y;
    }

    private static boolean isCellValid(int x, int y){
        if(x < 0 || x >= SIZE || y < 0 || y >= SIZE){
            return false;
        }
        if(MAP[y][x] == DOT_EMPTY){
            return true;
        }
        return false;
    }

    private static void initMap() {
        for(int i = 0; i < SIZE; i++){
            for (int j = 0; j < SIZE; j++){
                MAP[i][j] = DOT_EMPTY;
            }
        }
    }

    private static void printMap(){
        for(int i = 0; i <= SIZE; i++){
            System.out.print(i + " ");
        }
        System.out.println();
        for(int i = 0; i < SIZE; i++){
            System.out.print((i + 1) + " ");
            for (int j = 0; j < SIZE; j++){
                System.out.print(MAP[i][j] + " ");
            }
            System.out.println();
        }
        System.out.println();
    }
}

