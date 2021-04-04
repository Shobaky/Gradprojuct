package com.example.lambdaroom;

import java.util.ArrayList;
import java.util.HashMap;

public class Probability {
    private HashMap<Integer,String> takenRow;
    private HashMap<String,Integer> takenHour;
    private HashMap<String,Integer> takenHole;

    private HashMap<Integer,String> takenRow2;
    private HashMap<Integer,String> takenHour2;
    private HashMap<Integer,String> takenHole2;
    private int startJ;
    private ArrayList<Probability> otherProbs;
    private HashMap<String,College.blankSpace> Prob;
    private HashMap<String,College2.blankSpace> Prob2;
    private int Conflict;

    public Probability() {

        this.takenRow = new HashMap<>();
        this.otherProbs = new ArrayList<>();
        Prob = new HashMap<>();
        Prob2 = new HashMap<>();
        takenHour = new HashMap<>();
        takenHole = new HashMap<>();
        takenRow2 =new HashMap<>();
        takenHole2 = new HashMap<>();
        takenHour2 = new HashMap<>();
        startJ = 0;
        Conflict=0;
    }

    public void addTakenRow(String Cols,int Row){
        takenRow.put(Row,Cols);
    }
    public void addTakenRow2(int Col,String Row){takenRow2.put(Col,Row);}
    public void addTakenHour2(int Col,String Row){takenHole2.put(Col,Row);}
    public void addTakenHole2(int Col, String Row){takenHole2.put(Col,Row);}
    public void addProb(Probability prob){
        otherProbs.add(prob);
    }



    public HashMap<Integer,String> getTakenRow() {
        return takenRow;
    }
    public int getConf(){
        return Conflict;
    }
    public ArrayList<Probability> getOtherProbs() {
        return otherProbs;
    }

    public HashMap<String, College.blankSpace> getProb() {
        return Prob;
    }
    public HashMap<String, College2.blankSpace> getProb2() {
        return Prob2;
    }
    public void addLec(String Mat,College.blankSpace Space){
        Prob.put(Mat,Space);
    }
    public void addLec2(String Mat,College2.blankSpace Space){
        Prob2.put(Mat,Space);
    }

    public HashMap<Integer, String> getTakenRow2() {
        return takenRow2;
    }

    public HashMap<Integer, String> getTakenHour2() {
        return takenHour2;
    }

    public HashMap<Integer, String> getTakenHole2() {
        return takenHole2;
    }

    @Override
    public String toString() {
        StringBuilder probBuilder = new StringBuilder();
        for(String material:Prob.keySet()){

                probBuilder.append("Material: "+material+" Space: "+Prob.get(material).toString()+"\n");


        }

        return probBuilder.toString();
    }
    public void addConf(){
        Conflict = Conflict++;
    }

    public HashMap< String,Integer> getTakenHour() {
        return takenHour;
    }

    public void addTakenHour(int col,String rowSec) {
        this.takenHour.put(rowSec,col);
    }

    public int getStartJ() {
        return startJ;
    }

    public void setStartJ(int i) {
        startJ = i;
    }
}

