

package com.example.lambdaroom;

import android.content.Context;
import android.os.Environment;
import android.util.Log;
import android.widget.Toast;

import androidx.annotation.Nullable;

import org.apache.poi.ss.usermodel.HorizontalAlignment;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.VerticalAlignment;
import org.apache.poi.ss.util.CellRangeAddress;
import org.apache.poi.ss.util.CellUtil;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFCellStyle;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;

public class College2 {
    private final ArrayList<Hole> Holes;
    private final ArrayList<Lab> Labs;
    private final ArrayList<Professor> Professors;
    private final ArrayList<Assistant> Assistants;
    private static ArrayList<Department> mDepartments= new ArrayList<>();
    private final HashMap<String ,Professor> professorsMap;
    private  ArrayList<String> Days;
    private  ArrayList<Integer> Hours;
    private Context cxt;


    public College2(Context context){
        Holes = new ArrayList<>();
        Labs = new ArrayList<>();
        Professors = new ArrayList<>();
        Assistants = new ArrayList<>();
        Days = new ArrayList<>();
        Hours = new ArrayList<>();
        professorsMap =new HashMap<>();
        cxt = context;
    }
    public void addHole(String Name,int Cap){
        Hole hole = new Hole();
        hole.setCapacity(Cap);
        hole.setName(Name);
        Holes.add(hole);
    }
    public void addLab(String Name, int Cap){
        Lab lab = new Lab();
        lab.setCapacity(Cap);
        lab.setName(Name);
        Labs.add(lab);
    }
    public void removeHole(int Indx){
        Holes.remove(Indx);
    }
    public void removeLab(int Indx){
        Holes.remove(Indx);
    }

    public ArrayList<Hole> getHoles() {
        return Holes;
    }

    public ArrayList<Lab> getLabs() {
        return Labs;
    }
    public String getHoleName(int Indx){
        return Holes.get(Indx).getName();
    }

    public int getHoleCap(int Indx) {
        return Holes.get(Indx).getCapacity();
    }
    public String getLabName(int Indx){
        return Labs.get(Indx).getName();
    }

    public int getLabCap(int Indx) {
        return Labs.get(Indx).getCapacity();
    }

    public String getProfName(int i) {
        return Professors.get(i).getName();
    }



    public ArrayList<Professor> getProfessors() {
        return Professors;
    }

    public String getProfDepart(int i) {
        return Professors.get(i).getDepartment();
    }

    public String getAssistantProfessor(int i) {
        return Assistants.get(i).getProfessor().getName();
    }

    public String getAssistantName(int i) {
        return Assistants.get(i).getName();
    }

    public ArrayList<Assistant> getAssistants() {
        return Assistants;
    }

    private static class Hole{
        private int Capacity;
        private String Name;

        public int getCapacity() {
            return Capacity;
        }

        public void setCapacity(int capacity) {
            Capacity = capacity;
        }

        public String getName() {
            return Name;
        }

        public void setName(String name) {
            Name = name;
        }

        @Override
        public String toString() {
            return "Hole{" +
                    "Capacity=" + Capacity +
                    ", Name='" + Name + '\'' +
                    '}';
        }
    }
    private static class Lab{
        private int Capacity;
        private String Name;

        public int getCapacity() {
            return Capacity;
        }

        public void setCapacity(int capacity) {
            Capacity = capacity;
        }

        public String getName() {
            return Name;
        }

        public void setName(String name) {
            Name = name;
        }

        @Override
        public String toString() {
            return "Lab{" +
                    "Capacity=" + Capacity +
                    ", Name='" + Name + '\'' +
                    '}';
        }
    }
    private static class Department{
        private String Name;
        private ArrayList<Section.Material> Materials;
        private ArrayList<Section> Sections;

        public Department() {
            Sections = new ArrayList<>();
        }

        public String getName() {
            return Name;
        }

        public void setName(String name) {
            Name = name;
        }

        public ArrayList<Section.Material> getMaterials() {
            return Materials;
        }

        public void setMaterials(ArrayList<Section.Material> materials) {
            Materials = materials;
        }

        public ArrayList<Section> getSections() {
            return Sections;
        }

        public void setSections(ArrayList<Section> sections) {
            Sections.addAll(sections);
        }
        public void addSection(String name, int Cap,ArrayList<Section.Material> Materials){
            Section section = new Section();
            section.setName(name);
            section.setCap(Cap);
            section.setMaterials(new ArrayList<>(Materials));
            Sections.add(section);



        }


        private static class Section{
            private String Name;
            private int Cap;
            private ArrayList<Material> Materials;

            public Section() {
                Materials = new ArrayList<>();
            }

            public String getName() {
                return Name;
            }

            public void setName(String name) {
                Name = name;
            }

            public int getCap() {
                return Cap;
            }

            public void setCap(int cap) {
                Cap = cap;
            }

            public void setMaterials(ArrayList<Material> materials) {
                Materials.addAll(materials);
            }

            public ArrayList<Material> getMaterials() {
                return Materials;
            }

            private static class Material{
                private String Name;
                private boolean hasLab;
                private boolean hasLec = true;
                private boolean hasSec;
                private boolean labByAssistant;

                public String getName() {
                    return Name;
                }

                public void setName(String name) {
                    Name = name;
                }

                public boolean isHasLab() {
                    return hasLab;
                }

                public void setHasLab(boolean hasLab) {
                    this.hasLab = hasLab;
                }

                public boolean isHasLec() {
                    return hasLec;
                }

                public void setHasLec(boolean hasLec) {
                    this.hasLec = hasLec;
                }

                public boolean isHasSec() {
                    return hasSec;
                }

                public void setHasSec(boolean hasSec) {
                    this.hasSec = hasSec;
                }

                public void setLabAssistant(boolean labByAssistant) {
                    this.labByAssistant = labByAssistant;
                }

                public boolean isLabByAssistant() {
                    return labByAssistant;
                }

                public void setLabByAssistant(boolean labByAssistant) {
                    this.labByAssistant = labByAssistant;
                }
            }
        }

        @Override
        public String toString() {
            return "Department{" +
                    "Name='" + Name;
        }

    }
    private class Professor{
        private String Name;
        private ArrayList<String> Materials;
        private String Department;

        public String getName() {
            return Name;
        }

        public void setName(String name) {
            Name = name;
        }

        public ArrayList<String> getMaterial() {
            return Materials;
        }

        public void setMaterial(ArrayList<String> material) {

            Materials = material;
        }

        public String getDepartment() {
            return Department;
        }

        public void setDepartment(String department) {
            Department = department;
        }

        @Override
        public String toString() {
            StringBuilder builder = new StringBuilder();
            builder.append("Professor{" + "Name='").append(Name).append('\'').append("Material: ");
            for(String mat : Materials){

                builder.append(mat).append(",");
            }
            builder.append("Department: ").append(Department);
            return builder.toString();

        }
    }

    private static class Assistant{
        private Professor professor;
        private String Name;

        public Professor getProfessor() {
            return professor;
        }

        public void setProfessor(Professor professor) {
            this.professor = professor;
        }

        public String getName() {
            return Name;
        }

        public void setName(String name) {
            Name = name;
        }

        @Override
        public String toString() {
            return "Assistant{" +
                    "professor=" + professor.getName() +
                    ", Name='" + Name + '\'' +
                    '}';
        }
    }

    public void addProfessor(String Name,@Nullable String department,@Nullable ArrayList<String> materials){
        Professor professor = new Professor();
        professor.setDepartment(department);

        professor.setMaterial(new ArrayList<>(materials));
        professor.setName(Name);
        professorsMap.put(Name,professor);
        Professors.add(professor);

    }
    public boolean addAssistant(String Name,String professorName){

        if(professorsMap.get(professorName) != null) {
            Assistant assistant = new Assistant();
            assistant.setProfessor(professorsMap.get(professorName));
            assistant.setName(Name);
            Assistants.add(assistant);
            return true;
        }
        return false;

    }
    public void removeProf(int Indx){
        Professors.remove(Indx);
        professorsMap.remove(Indx);
    }
    public void removeAssistant(int Indx){
        Assistants.remove(Indx);
    }
    public static ArrayList<Department> getmDepartments() {
        return mDepartments;
    }

    public ArrayList<String> getDays() {
        return Days;
    }

    public ArrayList<Integer> getHours() {
        return Hours;
    }


    public static class departmentBuilder{
        private Department mDepartment;
        public departmentBuilder() {
            mDepartment = new Department();
        }

        public departmentBuilder(ArrayList<Department> Departments) {
            for (Department department : Departments){
                Log.d("JOJO",department.getName());
                for(Department.Section section : department.getSections()){
                    Log.d("LOLO",section.getName());
                    for(Department.Section.Material material : section.getMaterials()){
                        Log.d("OGOG",material.getName());
                    }
                }
            }
            mDepartments.addAll(Departments);


        }

        public void setName(String Name){
            mDepartment.setName(Name);
        }
        public void addSection(String Name, ArrayList<Department.Section.Material> Materials, int Cap){

            mDepartment.setMaterials(Materials);
            mDepartment.addSection(Name,Cap,Materials);
        }
        public void submitDepartment(){
            mDepartments.add(mDepartment);
        }
    }
    public static class materialListBuilder{
        private ArrayList<Department.Section.Material> Materials;

        public materialListBuilder() {
            Materials = new ArrayList<>();
        }
        public void Add(String Name,boolean Lab,boolean LecLab,boolean labByAssistant, boolean Sec){
            Department.Section.Material Material = new Department.Section.Material();
            if(LecLab) {
                Material.setHasLab(true);

            } else if(Lab){
                Material.setHasLec(false);
                Material.setHasLab(true);
            }
            Material.setHasLec(LecLab);
            Material.setHasSec(Sec);
            Material.setLabAssistant(labByAssistant);
            Material.setName(Name);
            Materials.add(Material);
        }

        public ArrayList<Department.Section.Material> getMaterial() {
            return Materials;
        }
        public void newSec(){
            Materials = new ArrayList<>();
        }
    }

    @Override
    public String toString() {
        StringBuilder collegeeBuilder = new StringBuilder();
        for(Hole h : Holes){
            collegeeBuilder.append(h.toString());
        }
        for(Department department : mDepartments){
            collegeeBuilder.append(department.toString());
        }
        for(Lab lab : Labs){
            collegeeBuilder.append(lab);
        }
        for(Professor professor : Professors){
            collegeeBuilder.append(professor.toString());
        }
        return collegeeBuilder.toString();
    }

    public static class blankSpace {
        //Professor, Assistant
        private Professor professor;
        private Assistant assistant;
        private String Day;
        private int Hour;
        private Hole Hole;
        private Lab lab;


        public String getDay() {
            return Day;
        }

        public void setDay(String day) {
            Day = day;

        }

        public int getHour() {
            return Hour;
        }

        public void setHour(int hour) {
            Hour = hour;
        }

        public Professor getProfessor() {
            return professor;
        }

        public void setProfessor(Professor professor) {

            this.professor = professor;
        }

        public Assistant getAssistant() {
            return assistant;
        }

        public void setAssistant(Assistant assistant) {
            this.assistant = assistant;
        }

        public College2.Hole getHole() {
            return Hole;
        }

        public void setHole(College2.Hole hole) {
            Hole = hole;
        }

        public Lab getLab() {
            return lab;
        }

        public void setLab(Lab lab) {
            this.lab = lab;
        }


        @Override
        public String toString() {
            String prof ="";
            String assist = "";
            String labString = "";
            String hole = "";
            if(professor!=null){
                prof = professor.toString();
            }else{
                assist = assistant.toString();
            }
            if(Hole != null){
                hole = Hole.toString();
            }else{
                labString = lab.toString();
            }

            return "blankSpace{" +
                    "professor=" + prof+
                    ", assistant=" + assist+
                    ", Day='" + Day + '\'' +
                    ", Hour='" + Hour + '\'' +
                    ", Hole=" + hole+
                    ", lab=" + labString +
                    '}';
        }
    }

    public void setDays(ArrayList<String> days) {
        Days = days;
    }

    public void setHours(ArrayList<Integer> hours) {
        Hours = hours;
    }

    private blankSpace[][] generateSpaces(){
        blankSpace[][] Spaces = new blankSpace[Days.size()][(Hours.size()*(Holes.size()*((Professors.size()+Assistants.size()))+Labs.size()*((Professors.size()+Assistants.size()))))];
        int Col = 0;
        for(String day : Days){
            Log.d("SPACESS",day);
            int Row = 0;
            for (int hour: Hours){
                Log.d("SPACESS",String.valueOf(hour));
                for(Hole h  : Holes){
                    Log.d("SPACESS",h.getName()+" Prof: ");
                    for(Professor professor : Professors) {
                        blankSpace space = new blankSpace();
                        space.setDay(day);
                        space.setHole(h);
                        space.setHour(hour);
                        space.setProfessor(professor);
                        Spaces[Col][Row] = space;
                        Row++;
                        Log.d("SPACESS",professor.getName()+" Prof: ");

                    }
                    for(Assistant assistant : Assistants){
                        blankSpace space = new blankSpace();
                        space.setDay(day);
                        space.setHole(h);
                        space.setHour(hour);
                        space.setAssistant(assistant);
                        Spaces[Col][Row]=space;
                        Row++;
                    }

                }
                for(Lab lab : Labs){
                    for(Professor professor : Professors){
                        blankSpace space = new blankSpace();
                        space.setDay(day);
                        space.setLab(lab);
                        space.setHour(hour);
                        space.setProfessor(professor);
                        Spaces[Col][Row]=space;
                        Row++;


                    }
                    for(Assistant assistant : Assistants){
                        blankSpace space = new blankSpace();
                        space.setDay(day);
                        space.setLab(lab);
                        space.setHour(hour);
                        space.setAssistant(assistant);
                        Spaces[Col][Row]=space;
                        Row++;

                    }
                }

            }

            Col++;
        }
        return Spaces;
    }
    public void generateTable(){
        XSSFWorkbook TableWorkBook = new XSSFWorkbook();
        XSSFSheet TableSheet = TableWorkBook.createSheet("Time Table");
        XSSFRow daysRow = TableSheet.createRow(0);

        XSSFRow hoursRow = TableSheet.createRow(1);
        XSSFCellStyle hourStyle = TableWorkBook.createCellStyle();
        hourStyle.setAlignment(HorizontalAlignment.CENTER);
        XSSFCell titlesCell = daysRow.createCell(0);
        titlesCell.setCellValue("Days\n /Section");
        blankSpace[][] Spaces = generateSpaces();
        ArrayList<Department.Section> Sections = new ArrayList<>();
        ArrayList<String> mergeSectionMaterial=new ArrayList<>();
        HashMap<String,Integer> secCap = new HashMap<>();
        HashMap<String, Department.Section.Material> matsMap = new HashMap<>();


        for(Department department : mDepartments){

            Sections.addAll(department.getSections());

            for(Department.Section sec: department.getSections()){
                secCap.put(sec.getName(),sec.getCap());
                XSSFRow sectionRow = TableSheet.createRow(department.getSections().indexOf(sec)+2);
                sectionRow.createCell(0).setCellValue(sec.getName());

                for(Department.Section.Material Mat : sec.getMaterials()){

                    mergeSectionMaterial.add("Material: "+Mat.getName()+",Section: "+sec.Name);
                    matsMap.put("Material: "+Mat.getName()+",Section: "+sec.Name,Mat);
                }
            }
        }
        Probability mainProb = new Probability();
        int mergedStart = 1;
        int mergedEnd = 4;

        for(int i = 0 ; i < Spaces.length ; i++){
            for(int j = 0 ; j < Spaces[i].length ; j++){
                Log.d("SPCPSA",Spaces[i][j].toString());
                if(j==0) {
                    CellRangeAddress rangeAddress = new CellRangeAddress(0, 0, mergedStart + (4 * i), mergedEnd + (4 * i));
                    if (!TableSheet.getMergedRegions().contains(rangeAddress)) {
                        TableSheet.addMergedRegion(rangeAddress);
                    }
                }
                for(int Hour : Hours){
                    XSSFCell dayCell = daysRow.createCell(((  i*Hours.size())+1+Hours.indexOf(Hour)));
                    dayCell.setCellValue(Spaces[i][j].getDay());
                    CellUtil.setAlignment(dayCell,HorizontalAlignment.CENTER);
                    XSSFCell hourCell = hoursRow.createCell(((i*Hours.size()))+1+Hours.indexOf(Hour));
                    hourCell.setCellStyle(hourStyle);
                    if(Hour/13==0) {
                        hourCell.setCellValue(String.valueOf((Hour % 13)));
                    }else{
                        hourCell.setCellValue(String.valueOf((Hour%13)+1));
                    }

                }
            }
        }

        for(String mergeSecMat : mergeSectionMaterial) {

            String Section = mergeSecMat.split("Material: ")[1].split(",Section: ")[1];

            String Material = mergeSecMat.split(",")[0].replaceAll("Material: ","");





            if(matsMap.get(mergeSecMat).hasLec) {
                int range = 1;


                outerloop:
                for(int i = 0 ; i < Spaces.length ; i ++){
                    Log.d("STARTRTRTR",""+mainProb.getStartJ());
                    mainProb.setStartJ(0);
                    for(int j = mainProb.getStartJ(); j < Spaces[i].length; j++){

                        int DivTeach = j%(Professors.size()+Assistants.size());
                        int DivStruct = j%((Holes.size()*(Professors.size()+Assistants.size()))+(Labs.size()*(Professors.size()+Assistants.size())));
                        int takenTeach = (j)/((Holes.size()*(Professors.size()+Assistants.size()))+(Labs.size()*(Professors.size()+Assistants.size())));
                        int staffCount = (Professors.size()+Assistants.size());
                        int structCount = Holes.size()*staffCount+(Labs.size()*staffCount);
                        int rowCount = Hours.size()*structCount;

                        if(j!=0&&DivStruct>((Holes.size()*(Professors.size()+Assistants.size())))){
                            //the last plus: To pass the assistant element
                            j += (Labs.size()*(Professors.size()+Assistants.size()));

                        }else if(j != 0 &&DivTeach>Professors.size()-1){
                            j += Assistants.size();

                        }
                        if(j>=Spaces[i].length){
                            break ;
                        }
                        if(!mainProb.getTakenRow2().keySet().contains(j/structCount)){
                            Log.d("JJJNO",j/structCount+"  "+Spaces[i][j].toString());
                            mainProb.setStartJ(0);
                        }else{
                            Log.d("JJJ",j/structCount+"  "+Spaces[i][j].toString());
                        }


                        Log.d("COUNTER","j: "+j+" DivTeach: "+DivTeach+" DivSt: "+DivStruct);


                        if(!(mainProb.getTakenRow2().get(j/structCount)!=null&&mainProb.getTakenRow2().get(j/structCount).contains(String.valueOf(j%staffCount)))&&Spaces[i][j].getProfessor().getMaterial().contains(Material)&&secCap.get(Section)<Spaces[i][j].getHole().getCapacity()){
                            mainProb.addLec2(mergeSecMat,Spaces[i][j]);
                            Log.d("MODULOOO","St: "+staffCount+" MOduk: "+(j%staffCount)+" j: "+j+" stt: "+(staffCount-(j%staffCount)));
                            mainProb.setStartJ((j+staffCount)-(j%staffCount));
                            if(mainProb.getTakenRow2().get(j/structCount)!=null) {
                                String Prev = mainProb.getTakenRow2().get(j/structCount);

                                mainProb.addTakenRow2(j/structCount,String.valueOf(j % staffCount).concat(Prev));
                            }else {
                                mainProb.addTakenRow2(j/structCount, String.valueOf(j % staffCount));
                            }

                          /*
                            if(mainProb.getTakenHole2().get(j/structCount)!=null) {
                                String Prev = mainProb.getTakenHole2().get(j/structCount);
                                mainProb.addTakenHole2(j/structCount,String.valueOf(j /staffCount).concat(Prev));
                                Log.d("Taken0: ","K: "+(j/structCount)+" V: "+String.valueOf(j  /staffCount).concat(Prev));
                            }else {
                                Log.d("Taken1: ","HOle: "+String.valueOf(j  /staffCount));
                                mainProb.addTakenHole2(j/structCount ,String.valueOf(j/ staffCount));
                            }
                            if(mainProb.getTakenHour2().get(i)!=null) {
                                String Prev = mainProb.getTakenHour2().get(i);
                                Log.d("Taken0: ","Hour: "+String.valueOf(j /structCount));
                                mainProb.addTakenHour2(i,String.valueOf(j/structCount).concat(Prev));
                            }else {
                                Log.d("Taken1: ","Hour: "+String.valueOf(j /structCount));
                                mainProb.addTakenHour2(i, String.valueOf(j/structCount));
                            }*/

                            break outerloop;
                        }
                    }
                }


            }

        }
        Probability Chosen = mainProb;
        for(Probability other :mainProb.getOtherProbs()){

            if(other.getConf() < mainProb.getConf()){
                Chosen = other;
                Log.d("JL","WE MOVED");
            }
        }


        for(String mergeSecMat : Chosen.getProb2().keySet()){

            String Section="";
            String Material="";
            boolean Assist=false;
            boolean Lab = false;

            if(mergeSecMat.contains("Lab")&&mergeSecMat.contains("Assist")){
                Lab = true;
                Assist = true;
                Section = mergeSecMat.split("Material: ")[1].split(",Section: ")[1].replaceAll("Assist","");
                Material = mergeSecMat.split(",")[0].replaceAll("Material: ", "").replaceAll("Assist","");
            }else if(mergeSecMat.contains("Lab")){
                Lab = true;
                Section = mergeSecMat.split("Material: ")[1].split(",Section: ")[1].replaceAll("Assist","");
                Material = mergeSecMat.split(",")[0].replaceAll("Material: ", "").replaceAll("Assist","");

            }else if(mergeSecMat.contains("Assist")){
                Section = mergeSecMat.split("Material: ")[1].split(",Section: ")[1].replaceAll("Assist","");
                Material = mergeSecMat.split(",")[0].replaceAll("Material: ", "").replaceAll("Assist","");
                Assist = true;
            }else {
                Section = mergeSecMat.split("Material: ")[1].split(",Section: ")[1];
                Material = mergeSecMat.split(",")[0].replaceAll("Material: ", "");
                Log.d("JOJO",mergeSecMat);
            }

            blankSpace Space = Chosen.getProb2().get(mergeSecMat);
            XSSFRow SecRow = TableSheet.getRow(2);
            for(Department.Section section : Sections) {
                if(section.getName().equals(Section)) {
                    SecRow = TableSheet.getRow(Sections.indexOf(section) + 2);
                }
            }

            outerloop:
            for(int col = 0 ; col < Days.size()*Hours.size(); col=col+Hours.size()) {
                for (int hourCol = 0; hourCol < Hours.size(); hourCol++) {

                    if (TableSheet.getRow(0).getCell(col+1).getStringCellValue().equals(Space.getDay())&&TableSheet.getRow(1).getCell(hourCol+1).getStringCellValue().equals(String.valueOf(Space.getHour()))) {

                        XSSFCell matCell=null;
                        if(SecRow.getCell(hourCol+1)!=null){

                            matCell = SecRow.getCell(hourCol+1);
                        }else{

                            matCell = SecRow.createCell(hourCol+1);
                        }
                        XSSFCell secCell = SecRow.getCell(0);
                        XSSFCellStyle cellStyle = TableWorkBook.createCellStyle();

                        XSSFCellStyle secStyle = TableWorkBook.createCellStyle();
                        secStyle.setVerticalAlignment(VerticalAlignment.CENTER);
                        secStyle.setAlignment(HorizontalAlignment.CENTER);
                        secCell.setCellStyle(secStyle);
                        cellStyle.setWrapText(true);
                        cellStyle.setAlignment(HorizontalAlignment.CENTER);
                        cellStyle.setVerticalAlignment(VerticalAlignment.CENTER);
                        matCell.setCellStyle(cellStyle);
                        if (Space != null) {

                            if (!Assist&&!Lab){

                                if(matCell.getStringCellValue().isEmpty()||matCell.getStringCellValue()==null) {

                                    matCell.setCellValue("Material: " + Material + "\nProfessor: " + Space.getProfessor().getName() + "\nHole: " + Space.getHole().getName());
                                    TableSheet.setColumnWidth(hourCol+1, (Material + "\nProfessor: " + Space.getProfessor().getName() + "\nHole: " + Space.getHole().getName()).length() * 256);

                                }else{
                                    String matPrevString = matCell.getStringCellValue();
                                    String startMat = "Material: ";
                                    String endMat = "";
                                    String endTeacher = "";
                                    if(matPrevString.contains("Assistant")){
                                        endMat = "Assistant: ";

                                    }else{
                                        endMat = "Professor: ";

                                    }
                                    if(matPrevString.contains("Hole: ")){
                                        endTeacher = "Hole: ";

                                    }else{
                                        endTeacher = "Lab: ";
                                    }
                                    String prevMat = matPrevString.substring(matPrevString.indexOf(startMat),matPrevString.indexOf(endMat));
                                    String prevProf= matPrevString.substring(matPrevString.indexOf(endMat),matPrevString.indexOf(endTeacher));
                                    String prevHole= matPrevString.substring(matPrevString.indexOf(endTeacher));
                                    matCell.setCellValue("Material: " + Material + "|"+prevMat+ "\nProfessor: " + Space.getProfessor().getName() + "|"+endMat+prevProf+"\nHole: " + Space.getHole().getName()+"|"+endTeacher+prevHole);
                                    TableSheet.setColumnWidth(hourCol+1, ("Material: " + Material + "|"+prevMat+ "\nProfessor: " + Space.getProfessor().getName() + "|"+endMat+prevProf+"\nHole: " + Space.getHole().getName()+"|"+endTeacher+prevHole).length() * 256);
                                }

                                break outerloop;
                            }else if(Lab&&Assist){
                                if(matCell.getStringCellValue().isEmpty()||matCell.getStringCellValue()==null) {

                                    matCell.setCellValue("Material: " + Material + "\nAssistant: " + Space.getAssistant().getName() + "\nLab: " + Space.getLab().getName());
                                    TableSheet.setColumnWidth(hourCol+1,("Material: " + Material + "\nAssistant: " + Space.getAssistant().getName() + "\nLab " + Space.getLab().getName()).length()*256);
                                }else{
                                    String matPrevString = matCell.getStringCellValue();
                                    String startMat = "Material: ";
                                    String endMat = "";
                                    String endTeacher = "";
                                    if(matPrevString.contains("Assistant")){
                                        endMat = "Assistant: ";

                                    }else{
                                        endMat = "Professor: ";

                                    }
                                    if(matPrevString.contains("Hole: ")){
                                        endTeacher = "Hole: ";

                                    }else{
                                        endTeacher = "Lab: ";
                                    }
                                    String prevMat = matPrevString.substring(matPrevString.indexOf(startMat),matPrevString.indexOf(endMat));
                                    String prevProf= matPrevString.substring(matPrevString.indexOf(endMat),matPrevString.indexOf(endTeacher));
                                    String prevHole= matPrevString.substring(matPrevString.indexOf(endTeacher));
                                    matCell.setCellValue("Material: " + Material + "|"+prevMat+ "\nAssistant: " + Space.getAssistant().getName() + "|"+endMat+prevProf+"\nLab: " + Space.getLab().getName()+"|"+endTeacher+prevHole);

                                }
                                TableSheet.setColumnWidth(hourCol+1, (Material + "\nAssistant: " + Space.getAssistant().getName() + "\nLab: " + Space.getLab().getName()).length() * 256);
                                break outerloop;

                            }else if(Lab){
                                if(matCell.getStringCellValue().isEmpty()||matCell.getStringCellValue()==null) {

                                    matCell.setCellValue("Material: " + Material + "\nProfessor: " + Space.getProfessor().getName() + "\nLab: " + Space.getLab().getName());
                                    TableSheet.setColumnWidth(hourCol+1,("Material: " + Material + "\nProfessor: " + Space.getProfessor().getName() + "\nLab: " + Space.getLab().getName()).length() * 256);


                                }else{
                                    String matPrevString = matCell.getStringCellValue();
                                    String startMat = "Material: ";
                                    String endMat = "";
                                    String endTeacher = "";
                                    if(matPrevString.contains("Assistant")){
                                        endMat = "Assistant: ";

                                    }else{
                                        endMat = "Professor: ";

                                    }
                                    if(matPrevString.contains("Hole: ")){
                                        endTeacher = "Hole: ";

                                    }else{
                                        endTeacher = "Lab: ";
                                    }
                                    String prevMat = matPrevString.substring(matPrevString.indexOf(startMat),matPrevString.indexOf(endMat));
                                    String prevProf= matPrevString.substring(matPrevString.indexOf(endMat),matPrevString.indexOf(endTeacher));
                                    String prevHole= matPrevString.substring(matPrevString.indexOf(endTeacher));
                                    matCell.setCellValue("Material: " + Material + "|"+prevMat+ "\nProfessor: " + Space.getProfessor().getName() + "|"+prevProf+"\nLab: " + Space.getLab().getName()+"|"+prevHole);
                                    TableSheet.setColumnWidth(hourCol+1,("Material: " + Material + "|"+prevMat).length() * 256);


                                }

                                break outerloop;
                            }else if(Assist) {

                                if(matCell.getStringCellValue().isEmpty()||matCell.getStringCellValue()==null) {

                                    matCell.setCellValue("Material: " + Material + "\nAssistant: " + Space.getAssistant().getName() + "\nHole: " + Space.getHole().getName());
                                    TableSheet.setColumnWidth(hourCol+1,("Material: " + Material + "\nAssistant: " + Space.getAssistant().getName() + "\nHole: " + Space.getHole().getName()).length()*256);
                                }else{
                                    String matPrevString = matCell.getStringCellValue();

                                    String endMat = "";
                                    String endTeacher = "";
                                    if(matPrevString.contains("Assistant")){
                                        endMat = "Assistant: ";

                                    }else{
                                        endMat = "Professor: ";

                                    }
                                    if(matPrevString.contains("Hole: ")){
                                        endTeacher = "Hole: ";

                                    }else{
                                        endTeacher = "Lab: ";
                                    }


                                    String prevMat = matPrevString.substring(matPrevString.indexOf("Material: "),matPrevString.indexOf(endMat));
                                    String prevProf= matPrevString.substring(matPrevString.indexOf(endMat),matPrevString.indexOf(endTeacher));
                                    String prevHole= matPrevString.substring(matPrevString.indexOf(endTeacher));
                                    matCell.setCellValue("Material: " + Material + "|"+prevMat+ "\nAssistant: " + Space.getAssistant().getName() + "|"+prevProf+"\nHole: " + Space.getHole().getName()+"|"+prevHole);
                                    TableSheet.setColumnWidth(hourCol+1,("Material: " + Material + "|"+prevMat+ "\nAssistant: " + Space.getAssistant().getName() + "|"+prevProf+"\nHole: " + Space.getHole().getName()+"|"+prevHole).length()*256);
                                }



                            }


                            secStyle.setVerticalAlignment(VerticalAlignment.CENTER);
                            secCell.setCellStyle(secStyle);
                            matCell.setCellStyle(cellStyle);
                            break outerloop;

                        }
                    }
                }
            }




        }


        Toast.makeText(cxt,"File has be been downloaded",Toast.LENGTH_LONG).show();
        File filePath = new File(Environment.getExternalStorageDirectory()+"/Time Table.xlsx");
        try {
            if (!filePath.exists()) {
                filePath.createNewFile();
            }
            FileOutputStream outputStream = new FileOutputStream(filePath);
            TableWorkBook.write(outputStream);
            if (outputStream!=null){
                outputStream.flush();
                outputStream.close();
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }


    }

    public static class fileReader {
        public ArrayList<String[]> readHoleFile(Context context, String filename) {
            ArrayList<String[]>Holes = new ArrayList<>();
            try {

                // Creating Input Stream
                File file = new File(filename);
                FileInputStream myInput = new FileInputStream(file);




                // Create a workbook using the File System
                XSSFWorkbook myWorkBook = new XSSFWorkbook(myInput);

                // Get the first sheet from workbook
                XSSFSheet mySheet = myWorkBook.getSheetAt(0);

                /** We now need something to iterate through the cells.**/
                Iterator<Row> rowIter = mySheet.rowIterator();
                while (rowIter.hasNext()) {
                    XSSFRow myRow = (XSSFRow) rowIter.next();
                    String[] holes = new String[3];

                    holes[0] = myRow.getCell(0).getStringCellValue();

                    holes[1] = myRow.getCell(1).getRawValue();
                    double Cap = myRow.getCell(2).getNumericCellValue();
                    holes[2] = String.valueOf(Cap);
                    Holes.add(holes);


                }
            } catch (Exception e) {
                e.printStackTrace();
            }
            return Holes;

        }
        public ArrayList<String[]> readStaffFile(Context context, String filename) {
            ArrayList<String[]> Staff = new ArrayList<>();
            try {

                // Creating Input Stream
                File file = new File(filename);
                FileInputStream myInput = new FileInputStream(file);

                // Create a workbook using the File System
                XSSFWorkbook myWorkBook = new XSSFWorkbook(myInput);

                // Get the first sheet from workbook
                XSSFSheet mySheet = myWorkBook.getSheetAt(0);

                /** We now need something to iterate through the cells.**/
                Iterator<Row> rowIter = mySheet.rowIterator();
                StringBuilder materialsBuilder = new StringBuilder();
                int matCount = 0;
                double matNum =0;
                String[] teacherInf = new String[4];
                while (rowIter.hasNext()) {
                    XSSFRow myRow = (XSSFRow) rowIter.next();

                    if(myRow.getCell(0)!=null) {
                        teacherInf = new String[4];
                        teacherInf[0] = myRow.getCell(0).getStringCellValue();

                    }
                    if(myRow.getCell(1)!=null) {
                        teacherInf[1] = myRow.getCell(1).getStringCellValue();
                        matCount = 0;
                        matNum = 0;
                        materialsBuilder.setLength(0);

                    }
                    if(myRow.getCell(2)!=null) {
                        teacherInf[2] = myRow.getCell(2).getStringCellValue();
                    }

                    if(myRow.getCell(3)!=null) {
                        matNum = myRow.getCell(3).getNumericCellValue();
                        }
                    if(myRow.getCell(4)!=null&&matNum>matCount){
                        Log.d("ROWMOW",myRow.getCell(4).getStringCellValue()+"  m: "+matCount+"  m: "+(matNum-1)  );
                        materialsBuilder.append(myRow.getCell(4).getStringCellValue()).append(",");
                    }
                    if(matCount == matNum-1||matNum==0) {
                        Log.d("ROMOW",materialsBuilder.toString());
                        teacherInf[3] = materialsBuilder.toString();
                        Staff.add(teacherInf);
                    }
                    matCount++;

                }
            } catch (Exception e) {
                e.printStackTrace();
            }
            return Staff;

        }
        public ArrayList<Department> readLevelFile(Context context, String filename) {
            ArrayList<Department> Departments = new ArrayList<>();
            ArrayList<Department.Section> Sections = new ArrayList<>();
            ArrayList<Department.Section.Material> Mats = new ArrayList<>();
            ArrayList<Department.Section.Material> deptMats = new ArrayList<>();
            try {

                // Creating Input Stream
                File file = new File(filename);

                FileInputStream myInput = new FileInputStream(file);

                // Create a workbook using the File System
                XSSFWorkbook myWorkBook = new XSSFWorkbook(myInput);

                // Get the first sheet from workbook
                XSSFSheet mySheet = myWorkBook.getSheetAt(0);

                /** We now need something to iterate through the cells.**/
                Iterator<Row> rowIter = mySheet.rowIterator();
                String department ="";
                String section ="";
                int secCap=0;

                double matNum=-1;
                double secNum=-1;
                while (rowIter.hasNext()) {
                    Row row = rowIter.next();

                    if(row.getCell(4)!=null){
                        Log.d("MAATNUM",""+matNum);
                        matNum = row.getCell(4).getNumericCellValue();
                    }
                    if(row.getCell(1)!=null){
                        secNum = row.getCell(1).getNumericCellValue();
                    }
                    if(row.getCell(5)!=null){
                        Department.Section.Material material = new Department.Section.Material();
                        material.setName(row.getCell(5).getStringCellValue());
                        if(row.getCell(6)!=null){
                            material.setHasLab(true);
                            material.setHasLec(false);
                        }else if(row.getCell(7)!= null){

                            material.setHasLab(true);
                        }
                        if(row.getCell(8)!=null){
                            material.setLabByAssistant(true);
                        }
                        if(row.getCell(9)!=null){
                            material.setHasSec(true);
                        }

                        Mats.add(material);

                        deptMats.add(material);

                    }
                    if(row.getCell(2)!=null){

                        section = row.getCell(2).getStringCellValue();
                        secCap = (int)row.getCell(3).getNumericCellValue();
                    }

                    if(Mats.size() == matNum||matNum == 0){
                        Department.Section Section = new Department.Section();
                        Section.setMaterials(Mats);

                        Mats.clear();

                        Section.setCap(secCap);
                        Section.setName(section);

                        Sections.add(Section);
                    }

                    if(row.getCell(0)!=null){

                        department = row.getCell(0).getStringCellValue();
                    }

                    if(Sections.size()== secNum||secNum ==0){

                        Department dept = new Department();
                        dept.setName(department);
                        dept.setSections(Sections);
                        for(Department.Section section1 : Sections){
                            Log.d("HJGH",section1.getName());
                        }
                        Sections.clear();
                        dept.setMaterials(deptMats);
                        deptMats.clear();
                        Departments.add(dept);

                    }


                }
            } catch (Exception e) {
                e.printStackTrace();
            }
            return Departments;

        }
    }
}
