package com.example.lambdaroom;

import java.util.ArrayList;
import java.util.HashMap;

public class Probability {
    private HashMap<Integer,String> takenRow;
    private HashMap<String,Integer> takenHour;
    private ArrayList<Probability> otherProbs;
    private HashMap<String,College.blankSpace> Prob;
    private int Conflict;

    public Probability() {

        this.takenRow = new HashMap<>();
        this.otherProbs = new ArrayList<>();
        Prob = new HashMap<>();
        takenHour = new HashMap<>();
        Conflict=0;
    }

    public void addTakenRow(String Cols,int Row){
        takenRow.put(Row,Cols);
    }
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
    public void addLec(String Mat,College.blankSpace Space){
        Prob.put(Mat,Space);
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
}
