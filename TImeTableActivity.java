package com.example.lambdaroom;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;

import android.Manifest;
import android.app.Activity;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.os.Bundle;
import android.os.Environment;
import android.provider.OpenableColumns;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.Transformation;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ScrollView;
import android.widget.TextView;

import com.google.android.material.textfield.TextInputLayout;

import java.util.ArrayList;
import java.util.HashMap;

public class TImeTableActivity extends AppCompatActivity {
    static {
        System.setProperty(
                "org.apache.poi.javax.xml.stream.XMLInputFactory",
                "com.fasterxml.aalto.stax.InputFactoryImpl"
        );
        System.setProperty(
                "org.apache.poi.javax.xml.stream.XMLOutputFactory",
                "com.fasterxml.aalto.stax.OutputFactoryImpl"
        );
        System.setProperty(
                "org.apache.poi.javax.xml.stream.XMLEventFactory",
                "com.fasterxml.aalto.stax.EventFactoryImpl"
        );
    }
    private static final int REQUEST_EXTERNAL_STORAGE = 1;
    private static String[] PERMISSIONS_STORAGE = {
            Manifest.permission.READ_EXTERNAL_STORAGE,
            Manifest.permission.WRITE_EXTERNAL_STORAGE
    };
    private final int hole_request_code=453;
    private College college;
    private TextView filePath;
    private int staff_request_code=452;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_time_table);
        ScrollView mainScroll = findViewById(R.id.timeTableScroller);

        college = new College(this);
        //----College system----
        EditText Holidays = findViewById(R.id.timeTableHolidays);
        LinearLayout boxGroup = findViewById(R.id.holidaysGroup);
        EditText Hours = findViewById(R.id.timeTableHours);




        //----College structure Group (Add file, one by one buttons)----
        View structureButtonsGroup = findViewById(R.id.addHolesButtonsGroup);
        //Excel layout
        View structureExcelLayout = findViewById(R.id.addCollegeStructureFile);
        //File button
        Button addHolesExcel = structureButtonsGroup.findViewById(R.id.buttonsGroupFile);
        //Done
        Button holeExcelDone = structureExcelLayout.findViewById(R.id.fileDone);
        ImageView browseHoleFile = structureExcelLayout.findViewById(R.id.addFileImg);
        //OneByOne structure
        View structureOneByOneLayout = findViewById(R.id.addCollegeStructureOneByOne);

        Button addHoleOneByOne = structureButtonsGroup.findViewById(R.id.buttonGroupOneByOne);
        Button submitHole = structureOneByOneLayout.findViewById(R.id.structureHoleSubmit);
        TextView holeCount = structureOneByOneLayout.findViewById(R.id.structureHoleNumber);
        EditText holeName = structureOneByOneLayout.findViewById(R.id.structureHoleNameText);
        EditText holeCap = structureOneByOneLayout.findViewById(R.id.structureHoleCapText);
        EditText holesNumber = findViewById(R.id.holesNumber);
        EditText labsNumber = findViewById(R.id.labsNumber);

        //----Staff Group----

        View staffButtonsGroup = findViewById(R.id.addStaffButtonGroup);
        //Excel layout
        View staffExcelLayout = findViewById(R.id.addStaffFile);

        //File button
        Button addStaffExcel = staffButtonsGroup.findViewById(R.id.buttonsGroupFile);
        ImageView browseStaff = staffExcelLayout.findViewById(R.id.addFileImg);
        //Done
        Button staffExcelDone = staffExcelLayout.findViewById(R.id.fileDone);

        //OneByOne staff
        View staffOneByOneLayout = findViewById(R.id.addStaffOneByOne);
        TextView staffNumber = staffOneByOneLayout.findViewById(R.id.staffNumber);
        //Staff one by one
        Button addStaffOneByOne = staffButtonsGroup.findViewById(R.id.buttonGroupOneByOne);
        EditText professorNum = findViewById(R.id.professorNumber);
        EditText assistantNumb = findViewById(R.id.teacherAssistantNumber);

        //----College levels buttons group-----

        View levelsButtonsGroup = findViewById(R.id.addLevelsButtonsGroup);
        //Excel layout
        View levelExcelLayout = findViewById(R.id.addLevelFile);

        Button levelExcelButton = levelsButtonsGroup.findViewById(R.id.buttonsGroupFile);
        //File button
        Button levelExcelDone = levelExcelLayout.findViewById(R.id.fileDone);
        ImageView levelBrowse = levelExcelLayout.findViewById(R.id.addFileImg);
        //OneByOne level layout contains department layout
        View levelOneByOneLayout = findViewById(R.id.addLevelOneByOne);

        //College levels one by one
        Button levelOneByOneButton = levelsButtonsGroup.findViewById(R.id.buttonGroupOneByOne);
        //Contains sections layout
        View departmentLayout = levelOneByOneLayout.findViewById(R.id.departmentOneByOneLayout);
        //Levels number department button
        Button levelSubmit = levelOneByOneLayout.findViewById(R.id.levelSubmit);
        EditText departmentsNumber = levelOneByOneLayout.findViewById(R.id.departmentsNumber);
        //Departments
        EditText departmentName = departmentLayout.findViewById(R.id.departmentName);
        //Sections number in departmentLayout
        EditText sectionsNumber = departmentLayout.findViewById(R.id.sectionsNumber);
        Button departmentSubmit = departmentLayout.findViewById(R.id.departmentSubmit);

        //Sections
        View sectionsLayout = departmentLayout.findViewById(R.id.sectionOneByOneLayout);
        Button sectionSubmit = sectionsLayout.findViewById(R.id.sectionSubmit);
        CheckBox matHasLabBox = sectionsLayout.findViewById(R.id.sectionLabOnlyBox);
        CheckBox matHasLecBox = sectionsLayout.findViewById(R.id.sectionLabHoleBox);
        CheckBox matHasSecBox = sectionsLayout.findViewById(R.id.sectionHasSectionBox);
        CheckBox labAssistantBox = sectionsLayout.findViewById(R.id.sectionAssistantOrProfBox);




        Holidays.addTextChangedListener(new TextWatcher() {
            boolean Expanded;

            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                if (!Expanded) {
                    Expanded = expandView(boxGroup);
                }
            }

            @Override
            public void afterTextChanged(Editable s) {
                if (s.length() == 0) {
                    collapseView(boxGroup);
                    Expanded = false;
                }
            }
        });

        addHolesExcel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                collapseView(structureOneByOneLayout);
                expandView(structureExcelLayout);
            }
        });
        addHoleOneByOne.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(college.getLabs().isEmpty()) {
                    holeCount.setText("Hole #1");
                }else{
                    holeCount.setText("Lab #"+college.getLabs().size());
                }
                collapseView(structureExcelLayout);

                if(!holesNumber.getText().toString().isEmpty()&&Integer.parseInt(holesNumber.getText().toString())!=0){

                    expandView(structureOneByOneLayout);

                }
            }
        });
        browseHoleFile.setOnClickListener(v->new AlertDialog.Builder(TImeTableActivity.this).setTitle("Excel note")
                .setMessage("The sheet must include the following columns in the same arrangement (Hole type(Lab/Hole),Hole name, Hole capacity) till now only xlsx extensions works")
                .setPositiveButton("Browse",(dialog,which)->{
                    verifyStoragePermissions(TImeTableActivity.this,hole_request_code);

                }).setNegativeButton("Cancel", (dialog, which) -> dialog.dismiss()).show());

        holeExcelDone.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                collapseView(structureExcelLayout);
            }
        });

        addStaffExcel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                collapseView(staffOneByOneLayout);
                expandView(staffExcelLayout);
            }
        });
        browseStaff.setOnClickListener(v1->new AlertDialog.Builder(TImeTableActivity.this).setTitle("Excel note")
                .setMessage("The sheet must include the following columns in the same arrangement (Teacher degree(professor/assistant),Hole name, Hole capacity) till now only xlsx extensions works")
                .setPositiveButton("Browse",(dialog,which)->{
                    verifyStoragePermissions(TImeTableActivity.this,staff_request_code);

                }).setNegativeButton("Cancel", (dialog, which) -> dialog.dismiss()).show());
        addStaffOneByOne.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(college.getAssistants().isEmpty()) {
                    staffNumber.setText("Professor #1");
                }else{
                    staffNumber.setText("Assistant #"+(college.getAssistants().size()));
                }
                collapseView(staffExcelLayout);
                if(!professorNum.getText().toString().isEmpty()&&Integer.parseInt(professorNum.getText().toString())!=0) {
                    expandView(staffOneByOneLayout);
                }
            }
        });
        levelExcelButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                expandView(levelExcelLayout);
                collapseView(levelOneByOneLayout);
            }
        });

        levelBrowse.setOnClickListener(v1->new AlertDialog.Builder(TImeTableActivity.this).setTitle("Excel note")
                .setMessage("The sheet must be as follows\n Department name, Sections number, Section name,Section capacity,Materials number, Material name like that" +
                        "Electricity| 5 | Computers and control| 25 | 6 | Image processing |\n|    |  |      |  |   | Machine learning till the materials end then " +
                        "move to the next section till the sections end then the next department")
                .setPositiveButton("Browse",(dialog,which)->{
                    verifyStoragePermissions(TImeTableActivity.this,451);

                }).setNegativeButton("Cancel", (dialog, which) -> dialog.dismiss()).show());

        //Gathering data
        weekDays[] Days = weekDays.values();



        //---Holes---
        TextInputLayout holeNameLayout = findViewById(R.id.structureHoleNameTextLayout);
        TextInputLayout holeCapLayout = findViewById(R.id.structureHoleCapTextLayout);
        TextView backToHole = findViewById(R.id.backToHoleString);
        filePath = structureExcelLayout.findViewById(R.id.filePath);

        submitHole.setOnClickListener(v -> {
            
            if (!labsNumber.getText().toString().isEmpty()) {
                String currHoleNumber = holeCount.getText().toString();
                String numberString = currHoleNumber.subSequence(currHoleNumber.indexOf("#")+1, currHoleNumber.length()).toString();
                int number = Integer.parseInt(numberString);
                if (number == Integer.parseInt(holesNumber.getText().toString())&&holeCount.getText().toString().contains("Hole")) {
                    college.addHole(holeName.getText().toString(),Integer.parseInt(holeCap.getText().toString()));
                    if(Integer.parseInt(labsNumber.getText().toString())==0){
                        collapseView(structureOneByOneLayout);
                    }else {
                        holeCount.setText("Lab #1");
                        holeNameLayout.setHint("Lab name");
                        holeCapLayout.setHint("Lab capacity");
                    }

                }else if(number == Integer.parseInt(labsNumber.getText().toString())&&holeCount.getText().toString().contains("Lab")){
                    if(number != 0) {
                        college.addLab(holeName.getText().toString(), Integer.parseInt(holeCap.getText().toString()));
                    }


                    collapseView(structureOneByOneLayout);
                }else{

                    number++;
                    if(holeCount.getText().toString().contains("Hole")){
                        college.addHole(holeName.getText().toString(),Integer.parseInt(holeCap.getText().toString()));
                        holeCount.setText("Hole #"+number);
                    }else{
                        college.addLab(holeName.getText().toString(),Integer.parseInt(holeCap.getText().toString()));

                        holeCount.setText("Lab #"+number);
                    }
                    backToHole.setVisibility(View.VISIBLE);


                    //Back if you changed your mind
                    backToHole.setOnClickListener(v1 -> {

                        String HoleCount = holeCount.getText().toString();
                        String NumberString = HoleCount.subSequence(HoleCount.indexOf("#")+1, HoleCount.length()).toString();
                        int Number = Integer.parseInt(NumberString);

                        if(holeCount.getText().toString().contains("Hole")){
                            holeCount.setText("Hole #"+(Number-1));
                            holeName.setText(college.getHoleName(Number-2));
                            holeCap.setText(String.valueOf(college.getHoleCap(Number-2)));
                            holeNameLayout.setHint("Hole name");
                            holeCapLayout .setHint("Hole capacity");
                            college.removeHole(Number-2);
                        }else if(holeCount.getText().toString().contains("Lab")){
                            if(Number == 1){
                                holeCount.setText("Hole #"+college.getHoles().size());
                                holeName.setText(college.getHoleName(college.getHoles().size()-1));
                                holeCap.setText(String.valueOf(college.getHoleCap(college.getHoles().size()-1)));
                                holeNameLayout.setHint("Hole name");
                                holeCapLayout .setHint("Hole capacity");
                                college.removeHole(college.getHoles().size()-1);
                            }else{
                                holeCount.setText("Lab #"+(Number-1));
                                holeName.setText(college.getLabName(Number-2));
                                holeCap.setText(college.getLabCap(Number-2));
                                holeNameLayout.setHint("Lab name");
                                holeCapLayout .setHint("Lab capacity");
                                college.removeLab(Number-2);
                            }
                        }


                    });
                }
            }else{
                holeName.setError("Enter labs number!");
            }
            holeName.setText("");
            holeCap.setText("");
        });

        //---Staff---
        TextInputLayout professorNameLayout = staffOneByOneLayout.findViewById(R.id.professorNameLayout);
        TextInputLayout professorDepartmentLayout = staffOneByOneLayout.findViewById(R.id.professorDepartmentLayout);
        //For assistants it's professor name
        TextInputLayout professorMaterialLayout = staffOneByOneLayout.findViewById(R.id.professorMaterialLayout);

        Button submitTeacher = staffOneByOneLayout.findViewById(R.id.submitTeacher);
        Button submitMat = staffOneByOneLayout.findViewById(R.id.addProfMat);
        TextView Note = staffOneByOneLayout.findViewById(R.id.professorOneByOneNote);
        TextView backTeacher = staffOneByOneLayout.findViewById(R.id.backTeacher);
        ArrayList<String> profMaterials = new ArrayList<>();
        submitMat.setOnClickListener(v -> {
            if(!professorMaterialLayout.getEditText().getText().toString().isEmpty()) {
                profMaterials.add(professorMaterialLayout.getEditText().getText().toString());
                professorMaterialLayout.getEditText().setText("");
            }
        });
        submitTeacher.setOnClickListener(v -> {

            String counterString = staffNumber.getText().toString();
            int Count = Integer.parseInt(counterString.subSequence(counterString.indexOf("#") + 1, counterString.length()).toString());
            if(!assistantNumb.getText().toString().isEmpty()) {
                if (Count == Integer.parseInt(professorNum.getText().toString())&&counterString.contains("Professor")) {

                    Note.setVisibility(View.GONE);

                    college.addProfessor(professorNameLayout.getEditText().getText().toString()
                    ,professorDepartmentLayout.getEditText().getText().toString()
                    ,profMaterials);

                    profMaterials.clear();
                    if(Integer.parseInt(assistantNumb.getText().toString())!=0) {
                        staffNumber.setText("Assistant #1");
                        professorNameLayout.setHint("Assistant name");
                        professorMaterialLayout.setHint("Assistant's professor");
                    }else {
                        collapseView(staffOneByOneLayout);
                    }

                    professorDepartmentLayout.setVisibility(View.GONE);
                    submitMat.setVisibility(View.GONE);
                    LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT);
                    params.setMargins(32,8,32,8);
                    professorMaterialLayout.setLayoutParams(params);
                    professorNameLayout.getEditText().setText("");
                    professorMaterialLayout.getEditText().setText("");
                }else if(Count == Integer.parseInt(assistantNumb.getText().toString())&&staffNumber.getText().toString().contains("Assistant")){
                    if(college.addAssistant(professorNameLayout.getEditText().getText().toString(),professorMaterialLayout.getEditText().getText().toString())) {
                        collapseView(staffOneByOneLayout);
                    }else{
                        professorMaterialLayout.getEditText().setError("No such a professor");
                    }
                }else{

                    Count++;
                    if(counterString.contains("Assistant")){
                        if(college.addAssistant(professorNameLayout.getEditText().getText().toString(),professorMaterialLayout.getEditText().getText().toString())) {

                            staffNumber.setText("Assistant #"+Count);
                            professorNameLayout.getEditText().setText("");
                            professorMaterialLayout.getEditText().setText("");
                        }else{

                            professorMaterialLayout.getEditText().setError("No such a professor");
                        }
                    }else{
                        staffNumber.setText("Professor #"+Count);

                        college.addProfessor(professorNameLayout.getEditText().getText().toString()
                                ,professorDepartmentLayout.getEditText().getText().toString()
                                ,profMaterials);
                        profMaterials.clear();
                        professorNameLayout.getEditText().setText("");
                        professorMaterialLayout.getEditText().setText("");
                        professorDepartmentLayout.getEditText().setText("");

                        backTeacher.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                String NumberString = staffNumber.getText().toString().subSequence(staffNumber.getText().toString().indexOf("#")+1, staffNumber.getText().toString().length()).toString();

                                int Number = Integer.parseInt(NumberString);
                                if(staffNumber.getText().toString().contains("Professor")){
                                    staffNumber.setText("Professor #"+(Number-1));
                                    professorNameLayout.getEditText().setText(college.getProfName(Number-2));
                                    professorDepartmentLayout.getEditText().setText(college.getProfDepart(Number-2));
                                    professorNameLayout.setHint("Professor name");
                                    professorDepartmentLayout.setHint("Professor capacity");
                                    college.removeProf(Number-2);

                                }else if(staffNumber.getText().toString().contains("Assistant")){
                                    if(Number == 1){
                                        staffNumber.setText("Professor #"+college.getProfessors().size());
                                        professorNameLayout.getEditText().setText(college.getProfName(college.getProfessors().size()-1));
                                        professorDepartmentLayout.getEditText().setText(college.getProfDepart(college.getProfessors().size()-1));
                                        professorNameLayout.setHint("Professor name");
                                        professorDepartmentLayout.setHint("Professor department");
                                        professorDepartmentLayout.setVisibility(View.VISIBLE);
                                        college.removeProf(college.getProfessors().size()-1);
                                        professorDepartmentLayout.setVisibility(View.VISIBLE);
                                        submitMat.setVisibility(View.VISIBLE);
                                    }else{
                                        staffNumber.setText("Assistant #"+(Number-1));
                                        professorNameLayout.getEditText().setText(college.getAssistantName(Number-2));
                                        professorDepartmentLayout.getEditText().setText(college.getAssistantProfessor(Number-2));
                                        professorNameLayout.setHint("Assistant name");
                                        professorDepartmentLayout .setHint("Assistant capacity");
                                        college.removeAssistant(Number-2);
                                    }
                                }
                            }
                        });

                    }
                }
            }
        });
        //Staff File
        addStaffExcel.setOnClickListener(v->expandView(staffExcelLayout));



        //---Levels---
        TextView departmentCounter = departmentLayout.findViewById(R.id.departmentNumberOneByOne);
        TextView sectionsCounter = sectionsLayout.findViewById(R.id.sectionNumberOneByOne);
        EditText sectionName = sectionsLayout.findViewById(R.id.sectionNameText);
        EditText sectionCap = sectionsLayout.findViewById(R.id.sectionCapText);
        EditText materialName = sectionsLayout.findViewById(R.id.materialNameText);
        Button submitMaterial = sectionsLayout.findViewById(R.id.submitMaterial);
        levelOneByOneButton.setOnClickListener(v -> {

            expandView(levelOneByOneLayout);
            collapseView(levelExcelLayout);


        });
        levelSubmit.setOnClickListener(View -> {

            if (!departmentsNumber.getText().toString().isEmpty()) {
                expandView(departmentLayout);
                departmentCounter.setText("Department #1");
                levelSubmit.setVisibility(android.view.View.GONE);

            } else {
                departmentsNumber.setError("Required");
            }

        });
        College.departmentBuilder departmentBuilder = new College.departmentBuilder();

        departmentSubmit.setOnClickListener(v->{
            if(departmentName.getText().toString().isEmpty()){
                departmentName.setError("Required");
            }else if(sectionsNumber.getText().toString().isEmpty()){
                sectionsNumber.setError("Required");
            }else{

                departmentBuilder.setName(departmentName.getText().toString());
                expandView(sectionsLayout);
                departmentSubmit.setVisibility(View.GONE);
                sectionsCounter.setText("Section #1");

            }

        });
        CompoundButton.OnCheckedChangeListener listener = (buttonView, isChecked) -> {
            if(isChecked){
                labAssistantBox.setEnabled(true);
            }
        };
        matHasLabBox.setOnCheckedChangeListener(listener);
        matHasLecBox.setOnCheckedChangeListener(listener);
        College.materialListBuilder matBuilder = new College.materialListBuilder();
        submitMaterial.setOnClickListener(v -> {
            if(!materialName.getText().toString().isEmpty()) {
                //Wrongly names matHasLec --> it should be matHasBothLabAndLec
                matBuilder.Add(materialName.getText().toString(),matHasLabBox.isChecked(),matHasLecBox.isChecked(),labAssistantBox.isChecked(),matHasSecBox.isChecked());
                materialName.setText("");
            }else{
                materialName.setError("Required");
            }
        });

        sectionSubmit.setOnClickListener(v -> {

            if (!sectionName.getText().toString().isEmpty()&&!sectionCap.getText().toString().isEmpty()) {
                String sectionCounterString = sectionsCounter.getText().toString();
                int Counter = Integer.parseInt(sectionCounterString.subSequence(sectionCounterString.indexOf("#")+1,sectionCounterString.length()).toString());
                if(Counter == Integer.parseInt(sectionsNumber.getText().toString())){
                    departmentBuilder.addSection(sectionName.getText().toString(),matBuilder.getMaterial(),Integer.parseInt(sectionCap.getText().toString()));
                    matBuilder.newSec();

                    sectionCap.setText("");
                    sectionName.setText("");
                    sectionSubmit.setVisibility(View.VISIBLE);

                    //Move to the next department
                    String departmentCounterString =departmentCounter.getText().toString();
                    int departmentCount = Integer.parseInt(departmentCounterString.subSequence(departmentCounterString.indexOf("#")+1,departmentCounterString.length()).toString());
                    departmentBuilder.submitDepartment();
                    if(departmentCount==Integer.parseInt(departmentsNumber.getText().toString())){
                        //Exit college levels
                        collapseView(levelOneByOneLayout);


                    }
                    departmentCount++;
                    departmentCounter.setText("Department #"+departmentCount);
                    departmentName.setText("");
                    sectionCap.setText("");
                    collapseView(sectionsLayout);
                    departmentSubmit.setVisibility(View.VISIBLE);
                }else{

                    departmentBuilder.addSection(sectionName.getText().toString(),matBuilder.getMaterial(),Integer.parseInt(sectionCap.getText().toString()));
                    matBuilder.newSec();

                    sectionName.setText("");
                    sectionCap.setText("");
                }
                Counter++;
                sectionsCounter.setText("Section #"+Counter);
            } else {
                if(sectionName.getText().toString().isEmpty()) {
                    materialName.setError("Required");
                }else{
                    sectionCap.setError("Required");
                }
            }
        });
        Button generate = findViewById(R.id.timeTableGenerate);
        generate.setOnClickListener(v -> {
            verifyStoragePermissions(TImeTableActivity.this,451);
            String hoursString = Hours.getText().toString();

            ArrayList<Integer> avHours = new ArrayList<>();

            if(!hoursString.isEmpty()) {

                int avHoursInt = Integer.parseInt(hoursString);
                while(avHoursInt!=0){
                    avHours.add(8+(8-avHoursInt));
                    avHoursInt=avHoursInt-2;
                }
            }


            //Assuming each lecture or section is 2 hours!

            ArrayList<String> availableDays = new ArrayList<>();
            ArrayList<String> Vacancies = loadVacancies(boxGroup);

            for(weekDays day : Days){

                if(!Vacancies.contains(day.toString())){
                    availableDays.add(day.toString());
                }
            }
            college.setDays(availableDays);
            college.setHours(avHours);

            college.generateTable();

        });




    }
    private boolean expandView(View v){

        int matchParentMeasureSpec = View.MeasureSpec.makeMeasureSpec(((View)v.getParent()).getWidth(),
        View.MeasureSpec.EXACTLY);
        int wrapContentMeasureSpec = View.MeasureSpec.makeMeasureSpec(0, View.MeasureSpec.UNSPECIFIED);
        v.measure(matchParentMeasureSpec, wrapContentMeasureSpec);
        final int targetHeight = v.getMeasuredHeight();

        // Older versions of android (pre API 21) cancel animations for views with a height of 0.
        v.getLayoutParams().height = 1;
        v.setVisibility(View.VISIBLE);
        Animation a = new Animation()
        {
            @Override
            protected void applyTransformation(float interpolatedTime, Transformation t) {
                v.getLayoutParams().height = interpolatedTime == 1
                        ? LinearLayout.LayoutParams.WRAP_CONTENT
                        : (int)(targetHeight * interpolatedTime);
                v.requestLayout();
            }

            @Override
            public boolean willChangeBounds() {
                return true;
            }
        };

        // Expansion speed of 1dp/ms
        a.setDuration((int)(targetHeight / v.getContext().getResources().getDisplayMetrics().density));
        v.startAnimation(a);



        return true;
    }
    private void collapseView(View v) {

        final int initialHeight = v.getMeasuredHeight();

        Animation a = new Animation()
        {
            @Override
            protected void applyTransformation(float interpolatedTime, Transformation t) {
                if(interpolatedTime == 1){
                    v.setVisibility(View.GONE);
                }else{
                    v.getLayoutParams().height = initialHeight - (int)(initialHeight * interpolatedTime);
                    v.requestLayout();
                }
            }

            @Override
            public boolean willChangeBounds() {
                return true;
            }
        };

        // Collapse speed of 1dp/ms
        a.setDuration((int)(initialHeight / v.getContext().getResources().getDisplayMetrics().density));
        v.startAnimation(a);
    }
    private ArrayList<String> loadVacancies(View holidayLayout){
        ArrayList<String> Vacs = new ArrayList<>();
        CheckBox satBox = holidayLayout.findViewById(R.id.satBox);
        CheckBox sunBox = holidayLayout.findViewById(R.id.sunBox);
        CheckBox monBox = holidayLayout.findViewById(R.id.monBox);
        CheckBox tueBox = holidayLayout.findViewById(R.id.tueBox);
        CheckBox wedBox = holidayLayout.findViewById(R.id.wedBox);
        CheckBox thuBox = holidayLayout.findViewById(R.id.thuBox);
        CheckBox friBox = holidayLayout.findViewById(R.id.friBox);

        ArrayList<CheckBox> Boxes=new ArrayList<>();
        Boxes.add(satBox);
        Boxes.add(sunBox);
        Boxes.add(monBox);
        Boxes.add(tueBox);
        Boxes.add(wedBox);
        Boxes.add(thuBox);
        Boxes.add(friBox);

        for(CheckBox box : Boxes){
            if(box.isChecked()) {
                Vacs.add(box.getText().toString());
            }
        }
        return Vacs;

    }
    public  void verifyStoragePermissions(Activity activity,int reqCode) {
        // Check if we have write permission
        int permission = ActivityCompat.checkSelfPermission(activity, Manifest.permission.READ_EXTERNAL_STORAGE);

        if (permission != PackageManager.PERMISSION_GRANTED) {
            // We don't have permission so prompt the user
            ActivityCompat.requestPermissions(
                    activity,
                            PERMISSIONS_STORAGE,
                    REQUEST_EXTERNAL_STORAGE
            );
        }else{
            Browse(reqCode);
        }
    }
    private void Browse(int my_request_code){
        // Choose a directory using the system's file picker.
        Intent intent = new Intent(Intent.ACTION_GET_CONTENT);
        intent.setType("application/vnd.openxmlformats-officedocument.spreadsheetml.sheet");

        // Provide read access to files and sub-directories in the user-selected
        // directory.
        intent.addFlags(Intent.FLAG_GRANT_READ_URI_PERMISSION);


        startActivityForResult(intent, my_request_code);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        College.fileReader Reader = new College.fileReader();

        Cursor cursor;
        if (data != null) {
            cursor = getContentResolver().query(data.getData(),null,null,null,null);
            cursor.moveToFirst();
            int fileNameIndx = cursor.getColumnIndex(OpenableColumns.DISPLAY_NAME);
            String File="";
            String fileName = cursor.getString(fileNameIndx);
            if(data.getDataString().contains("Download")){
                File = Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DOWNLOADS)+ java.io.File.separator+fileName;
            }else if(data.getDataString().contains("Documents")){
                File = Environment.getExternalStorageDirectory().getAbsolutePath()+ java.io.File.separator+"Documents"+ java.io.File.separator+fileName;
            }

            if(requestCode==hole_request_code){


                ArrayList<String[]> Holes = Reader.readHoleFile(TImeTableActivity.this,File);
                if(cursor != null){
                    cursor.close();
                }
                for(String[] holeInf:Holes){
                    if(holeInf[0].equals("Hole")) {
                        college.addHole(holeInf[1], (int) Double.parseDouble(holeInf[2]));
                    }else{
                        college.addLab(holeInf[1], (int) Double.parseDouble(holeInf[2
                                ]));
                    }
                }
            }else if(requestCode==staff_request_code){

                ArrayList<String[]> Staff = Reader.readStaffFile(TImeTableActivity.this,File);
                if(cursor != null){
                    cursor.close();
                }
                for(String[] teacherName:Staff){
                    if(teacherName[0].equals("Professor")) {

                        ArrayList<String> mats = new ArrayList<>();
                        while(teacherName[3].contains(",")){

                            String mat = teacherName[3].substring(0,teacherName[3].indexOf(",")+1);

                            teacherName[3] =teacherName[3].replaceAll(mat,"");

                            mats.add(mat.replace(",",""));
                        }
                        college.addProfessor(teacherName[1], teacherName[2], mats);

                    }else{
                        college.addAssistant(teacherName[1], teacherName[2]);
                    }
                }
            }else{

                if(cursor!=null){
                    cursor.close();
                }
                new College.departmentBuilder(new College.fileReader().readLevelFile(TImeTableActivity.this,File));


            }
        }

    }

}